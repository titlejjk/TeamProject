import ReactPaginate from 'react-paginate';
import './PartyDetail.css';
import ReplyItem from '../../component/ReplyItem/ReplyItem';
import Pagination from '../../lib/Pagination';

// 백엔드와 연동할 데이터 모음
const data = {
    mainImg: "https://recipe1.ezmember.co.kr/cache/recipe/2019/08/21/f51404dc513ccc76be4b5668f5dd350b1.jpg",
    title: "오늘은 뽀삐의 생일입니다. 축하해주세요!",
    desc: "벌써 10살이 된 뽀삐, 항상 건강하게 오래 살아줘!",
    subImg: "https://recipe1.ezmember.co.kr/cache/recipe/2023/08/09/a393d884c9710311dca36a5e0a9bc1dc1.jpg",
    insight: {
        like: 150,
        reply: 5,
        view: 12500
    },
    user: {
        thumb: "https://randomuser.me/api/portraits/med/men/52.jpg",
        name: "블루베리"
    },
    reply: [
        {
            thumb: "https://randomuser.me/api/portraits/med/men/50.jpg",
            name: "strawberry",
            content:
                "생일 축하해 뽀삐야! 다음에 또 생일 축하하자!",
            createdAt: "2023.08.11"
        },
        {
            thumb: "https://randomuser.me/api/portraits/med/men/49.jpg",
            name: "bbbbbb",
            content:
                "ㅠㅠ 10살이라니 ㅠㅠ 저희 집 강아지도 10살인데 너무 공감되네요 ㅠㅠ\n항상 건강하게 살렴!",
            createdAt: "2023.08.11"
        },
        {
            thumb: "https://randomuser.me/api/portraits/med/men/49.jpg",
            name: "bbbbbb",
            content:
                "ㅠㅠ 10살이라니 ㅠㅠ 저희 집 강아지도 10살인데 너무 공감되네요 ㅠㅠ\n항상 건강하게 살렴!",
            createdAt: "2023.08.11"
        },
        {
            thumb: "https://randomuser.me/api/portraits/med/men/49.jpg",
            name: "bbbbbb",
            content:
                "ㅠㅠ 10살이라니 ㅠㅠ 저희 집 강아지도 10살인데 너무 공감되네요 ㅠㅠ\n항상 건강하게 살렴!",
            createdAt: "2023.08.11"
        },
        {
            thumb: "https://randomuser.me/api/portraits/med/men/49.jpg",
            name: "bbbbbb",
            content:
                "ㅠㅠ 10살이라니 ㅠㅠ 저희 집 강아지도 10살인데 너무 공감되네요 ㅠㅠ\n항상 건강하게 살렴!",
            createdAt: "2023.08.11"
        },
        {
            thumb: "https://randomuser.me/api/portraits/med/men/49.jpg",
            name: "bbbbbb",
            content:
                "ㅠㅠ 10살이라니 ㅠㅠ 저희 집 강아지도 10살인데 너무 공감되네요 ㅠㅠ\n항상 건강하게 살렴!",
            createdAt: "2023.08.11"
        },
        {
            thumb: "https://randomuser.me/api/portraits/med/men/49.jpg",
            name: "bbbbbb",
            content:
                "ㅠㅠ 10살이라니 ㅠㅠ 저희 집 강아지도 10살인데 너무 공감되네요 ㅠㅠ\n항상 건강하게 살렴!",
            createdAt: "2023.08.11"
        },
        {
            thumb: "https://randomuser.me/api/portraits/med/men/49.jpg",
            name: "bbbbbb",
            content:
                "ㅠㅠ 10살이라니 ㅠㅠ 저희 집 강아지도 10살인데 너무 공감되네요 ㅠㅠ\n항상 건강하게 살렴!",
            createdAt: "2023.08.11"
        },
        {
            thumb: "https://randomuser.me/api/portraits/med/men/49.jpg",
            name: "bbbbbb",
            content:
                "ㅠㅠ 10살이라니 ㅠㅠ 저희 집 강아지도 10살인데 너무 공감되네요 ㅠㅠ\n항상 건강하게 살렴!",
            createdAt: "2023.08.11"
        },
        {
            thumb: "https://randomuser.me/api/portraits/med/men/49.jpg",
            name: "bbbbbb",
            content:
                "ㅠㅠ 10살이라니 ㅠㅠ 저희 집 강아지도 10살인데 너무 공감되네요 ㅠㅠ\n항상 건강하게 살렴!",
            createdAt: "2023.08.11"
        },
        {
            thumb: "https://randomuser.me/api/portraits/med/men/49.jpg",
            name: "bbbbbb",
            content:
                "ㅠㅠ 10살이라니 ㅠㅠ 저희 집 강아지도 10살인데 너무 공감되네요 ㅠㅠ\n항상 건강하게 살렴!",
            createdAt: "2023.08.11"
        }
    ]
};

// 페이지 로딩 시 출력되는 화면내용
export default function Page(){
    return(
        <main className="party_detail_main">
            <div className="party_detail_main_image_container">
                {/* 축하파티 사진 */}
                <img src={data.mainImg} alt="main party" />
            </div>
            <div className="party_detail_summary">
                {/* 새 글 등록 시 제목 부분 */}
                <h2>{data.title}</h2>
                {/* 부제목이자 제목에 대한 부연 설명 */}
                <p>{data.desc}</p>
            </div>
            <div className="party_detail_sub_image_container">
                <div>
                    {/* 강아지 축하사진 */}
                    <img src={data.subImg} alt="sub party" />
                </div>
                {/* 해당 detail 페이지가 받은 좋아요, 댓글, 조회수 표시 */}
                <div className="party_detail_insight">
                    <div>좋아요 {data.insight.like.toLocaleString()}</div>
                    <div>댓글 {data.insight.reply.toLocaleString()}</div>
                    <div>조회 {data.insight.view.toLocaleString()}</div>
                </div>
                <div className="party_detail_user">
                    <div>
                        {/* 작성자 프로필 사진 */}
                        <img src={data.user.thumb} />
                    </div>
                    {/* 작성자 닉네임 */}
                    <div className="title">{data.user.name}</div>
                    {/* 팔로우 버튼 */}
                    <button>
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
                        </span>
                        팔로우
                    </button>
                </div>
            </div>
            {/* 구분선 */}
            <Divider />
            <div className="party_detail_reply">
                {/* 총 댓글 수 표시 */}
                <div className="title">댓글 {data.reply.length}</div>
                {/* 댓글 입력창 */}
                <div className="input">
                    <div>
                        <img
                            src={"https://randomuser.me/api/portraits/med/men/47.jpg"}
                            alt="user thumb"
                        />
                    </div>
                    <input />
                    <span>등록</span>
                </div>
                {/* 등록된 댓글 나열 */}
                {data.reply.map((item, index)=> (
                    <ReplyItem key={index} {...item} />
                ))}

                <Pagination />
            </div>
        </main>
    );
}

// 구분선
function Divider() {
    return <div className="party_detail_divider" />;
}