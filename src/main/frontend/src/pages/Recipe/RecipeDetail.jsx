// RecipeDetail.js
import "./RecipeDetail.css";
import ReplyItem from "../../component/ReplyItem/ReplyItem";
import Pagination from "../../lib/Pagination";
import { useState, useEffect, useRef, useCallback } from "react";
import axios from "axios";
import Slider from "react-slick";

//import { useParams } from 'react-router-dom';   //id값을 전달하기 위한 params
//const { id } = useParams(); // URL 파라미터에서 id 추출
// 백엔드와 연동할 데이터 모음


// 페이지 로딩 시 출력되는 화면내용
export default function Page() {

    const [list, setList] = useState([]);

    let inputReply = useRef();

    const [isFollowing, setIsFollowing] = useState(false);

    const [reply, setReply] = useState([]);
     //초기값을 빈 배열로 설정
    
    const [currentPage, setCurrentPage] = useState(0);
    // 페이징 처리에 관련한 로직 및 상태 추가

    const [totalReplyCount, setTotalReplyCount] = useState(0);
    //전체 댓글의 개수를 표시

    const replyPerPage = 6;
    //한 페이지에 표시할 댓글의 수를 정의 
    // 추가

    const getList = ()=>{
      axios.get('http://localhost:5000/recipedetail')
      .then(res=>{
        setList(res.data);
      })
      .catch(error=>{
        console.log(error);
      });
    }

    useEffect(()=>{
      getList();
    }, [])

    const getReply = ()=>{
      axios.get('http://localhost:5000/reply')
      .then(response => {
        setReply(response.data);
        console.log(response.data);
        setTotalReplyCount(response.data.length); // 레시피 개수 설정
      })
      .catch(error => {
        console.log(error);
      });
    }

    useEffect(() => { 
        getReply();
    }, [])
    //axios로 json데이터 가져오기    
 

    //현재 페이지에 표시 되어야 할 카드의 시작 위치 계산 
    //현재 페이지 * 한페이지에 표시할 카드 수 =  시작위치
    const offset = currentPage * replyPerPage;

    //현재 페이지에 표시되어야 할 카드들의 배열 구성 
    //cards 배열에서 offset ~ offeset+cardsperPages범위를 슬라이스해서 현재 페이지에 가져온다.
    const currentReply = reply.slice(offset, offset + replyPerPage);

     //페이지 변경을 처리하며, 현재 페이지에 맞게 표시할 카드들을 슬라이스하여 렌더링하는 함수 
     const handlePageChange = ({ selected }) => {        
        setCurrentPage(selected);
        console.log(setCurrentPage)
    };

  return (

    list.map((item, index)=>{
      return (
        <main className="recipe_detail_main" key={index}>
          <div className="recipe_detail_image_container">
            {/* 메인 이미지 */}
            <img src={item.imageFile} alt="main recipe" />
          </div>
          <div className="recipe_detail_summary">
            {/* 새 글 등록 시 제목 부분 */}
            <h2>{item.title}</h2>
            {/* 부제목이자 제목에 대한 부연 설명 */}
            <p>{item.content}</p>
            {/* 마리 수, 소요 시간, 난이도 모음 */}
            <div className="recipe_detail_info">
              <div>
                <div>
                  <img src="/images/amount.png" alt="amount logo"></img>
                </div>
                {/* 마리 수 */}
                <div>{item.servingSize}마리</div>
              </div>
              <div>
                <div>
                  <img src="/images/time.png" alt="info logo"></img>
                </div>
                {/* 소요 시간 */}
                <div>{item.cookingTime}분 이내</div>
              </div>
              <div>
                <div>
                  <img src="/images/diffi.png" alt="difficulty logo"></img>
                </div>
                {/* 난이도 */}
                <div>{item.cookingLevel}</div>
              </div>
            </div>
          </div>
          {/* 구분선 */}
          <Divider />
          {/* 요리에 필요한 재료와 양에 대한 정보 */}
          <div className="recipe_detail_ingre">
            <span className="title">재료</span>
            <span>{item.ingre}</span>
          </div>
          <Divider />
          <div className="recipe_detail_step">
            <div className="title">조리설명</div>
            <div className="recipe_detail_step_item">
                <p>조리는 1번 사진부터 5번 사진 순서대로 진행됩니다.</p>
                <DetailSlider />
            </div>
            {/* 해당 detail 페이지가 받은 좋아요, 댓글, 조회수 표시 */}
            <div className="recipe_detail_insight">
              <div>좋아요 {item.insight.like.toLocaleString()}</div>
              <div>댓글 {item.insight.reply.toLocaleString()}</div>
              <div>조회 {item.insight.viewCount.toLocaleString()}</div>
            </div>
            <div className="recipe_detail_user">
              <div>
                {/* 작성자 프로필 사진 */}
                <img src={item.user.thumb} alt="user thumb" />
              </div>
              {/* 작성자 닉네임 */}
              <div className="title">{item.user.writer}</div>
              {/* 팔로우 버튼 */}
              <button
                style={{backgroundColor: isFollowing ? '#ff6a10' : '#ba7149'}}
                onClick={()=>{
                  setIsFollowing(!isFollowing);            
                }}>
                {isFollowing ? <Following /> : <Follow />}
              </button>
            </div>
          </div>
          <Divider />
          <div className="recipe_detail_reply">
            {/* 총 댓글 수 표시 */}
            <div className="title">댓글 {totalReplyCount}</div>
            {/* 댓글 입력 창 */}
            <div className="input">
              <div>
                <img
                  src={"https://randomuser.me/api/portraits/med/men/47.jpg"}
                  alt="user thumb"
                />
              </div>
              {/* 댓글 입력 시 댓글 목록에 추가되도록 기능 구현 */}
              <input ref={inputReply} type="text" />
              <button onClick={(e)=>{
                const content = inputReply.current.value;
                axios.post("http://localhost:5000/reply", {
                  thumb: "https://randomuser.me/api/portraits/med/men/49.jpg",
                  name: "abcde",
                  content,
                  createdAt: "2023.08.24"
                })
                .then((res)=>{
                  console.log(res.data);
                  getReply();
                })
                .catch((error)=>{
                  console.log(error);
                });
              }}>등록</button>
            </div>
            {/* 등록된 댓글 나열 */}
            {currentReply.map((item, index) => (
              <ReplyItem key={index} {...item} />
            ))}

            <Pagination pageCount={Math.ceil(reply.length / replyPerPage)} onPageChange={handlePageChange} />

          </div>
        </main>
      );
    })
    
  );
}



// 구분선
function Divider() {
  return <div className="recipe_detail_divider" />;
}

// 조리 순서를 배열로 나타낸 function
// function StepItem({ desc, img, ingre }) {
//   return (
//     <div className="recipe_detail_step_item">
//       <p>{desc}</p>
//       <img src={img} alt="desc img" />
//       <div className="ingre_container">
//         <div>
//           <img src={ingre} alt="ingre img" />
//         </div>
//       </div>
//     </div>
//   );
// }

function Follow(){
  return(
    // 팔로잉 하지 않을 때 활성화되는 버튼
      <span>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          height="16"
          viewBox="0 -960 960 960"
          width="16"
        >
          <path
            fill="#f4f4f4"
            d="M450-450H200v-60h250v-250h60v250h250v60H510v250h-60v-250Z"
          />
        </svg>
        팔로우
      </span>
  )
}

function Following(){
  return(
    // 팔로잉 클릭 시 활성화되는 버튼 
      <span>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          x="0px"
          y="0px" 
          width="16" 
          height="16" 
          viewBox="0 0 30 30"
        >
          <path
            fill="#f4f4f4"
            d="M 26.980469 5.9902344 A 1.0001 1.0001 0 0 0 26.292969 6.2929688 L 11 21.585938 L 4.7070312 15.292969 A 1.0001 1.0001 0 1 0 3.2929688 16.707031 L 10.292969 23.707031 A 1.0001 1.0001 0 0 0 11.707031 23.707031 L 27.707031 7.7070312 A 1.0001 1.0001 0 0 0 26.980469 5.9902344 z"
          />
        </svg>
        팔로잉
      </span>
  )
}

function DetailSlider(){

  const slickRef = useRef(null);

  const prev = useCallback(() => slickRef.current.slickPrev(), [])
  const next = useCallback(() => slickRef.current.slickNext(), [])

  const settings = {
      dots: false,
      infinite: true,
      speed: 500,
      slidesToShow: 1,
      slidesToScroll: 1,
      arrows: true
  }

  return (
      <div>
          <Slider {...settings} ref={slickRef} nextArrow={<NextArrow />} prevArrow={<PrevArrow />}>
              <div>
                  <h3>1</h3>
              </div>
              <div>
                  <h3>2</h3>
              </div>
              <div>
                  <h3>3</h3>
              </div>
              <div>
                  <h3>4</h3>
              </div>
              <div>
                  <h3>5</h3>
              </div>
          </Slider>
      </div>
  )
}

function NextArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div
      className={className}
      style={{
        ...style,
        display: "block",
        filter: "opacity(0.5) drop-shadow(0 0 0 #625f5f)",
        zoom: "2.5"
      }}
      onClick={onClick}
    />
  );
}

function PrevArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div
      className={className}
      style={{ ...style, display: "block", filter: "opacity(0.5) drop-shadow(0 0 0 #625f5f)", zoom: "2.5"}}
      onClick={onClick}
    />
  );
}