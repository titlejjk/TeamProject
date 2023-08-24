import React, { useState,useEffect } from 'react';
import SearchBar from '../component/SearchBar/SearchBar';
import ImageCategory from '../component/ImageCategory/ImageCategory';
import CardList from '../component/CardList/CardList';
import MainImg from '../component/MainImg/MainImg';
import axios from 'axios';
import Slider from 'react-slick'; //슬라이더 라이브러리
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import Headers from '../component/Layout/Header/Header';
import Footer from '../component/Layout/Footer/Footer'



 /*json이 저장된 주소를 불러와서 변수에 담기 */
const categoriesUrl="http://localhost:5000/categories"
 

const RecipeBoard = () => {
    const [cards, setCards] = useState([]);
  /*게시판 카테고리 목록을 저장하고 각각의 배열에 담기게 초기값 설정 */
    const [categories, setCategories] = useState({ recipeBoardCategory: [], chefBoardCategory: [] });

/*각각 목록을 axios로 불러오기 */
useEffect(() => { 
axios.get(categoriesUrl)
    .then((Response) => {
        setCategories(Response.data)
        //console.log(Response.data)
    })
    .catch((error) => {        
    });    
}, [])
    
/* 슬라이더 사용을 위한 코드 */    
const [currentSlide, setCurrentSlide] = useState(0);
/* 슬라이더 설정 코드 */
  const settings = {
  arrow: true,
  infinite: false,
  speed: 500,
  slidesToShow: 8,
  slidesToScroll: 1,
  nextArrow: <NextArrow />,
  prevArrow: <PrevArrow />
};

function NextArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div
      className={className}
      style={{ ...style, display: "block", background:"red"}}
      onClick={onClick}
    />
  );
}

function PrevArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div
      className={className}
      style={{ ...style, display: "block", background:"red"}}
      onClick={onClick}
    />
  );
}



    
  return (
    <div>
      <Headers/>
        <div className='container'>
           <div className='RecipeBoard-mainImg'>
            <MainImg/>
            </div>
                       
            <SearchBar />
        
            <ImageCategory categories={categories.recipeBoardCategory} />
            
            <p style={titleStyle} className='board-title'>화제의 TOP 레시피</p>
            
            {/* TOP 10 카드 리스트 */}
            <CardList cards={cards.slice(0, 4)} showTitle={true} />
            
           
            {/* chef list  + slider 적용 */}
            <div style={sliderContainer} className="slider-container">
                
                    <Slider {...settings} initialSlide={currentSlide}>
                    {categories.chefBoardCategory.map((category, index) => (
                        <div key={index}>
                        <ImageCategory categories={[category]} />
                        </div>
                    ))}
                   
                    </Slider>
         
            </div>


            <p style={titleStyle} className='board-title'>이런 레시피를 찾고 있나요?</p>
            <p style={title2Style} className='board-title'>좋아하실만한 레시피를 추천드려요!</p>
            
        
            {/* 오늘의 레시피 추천 리스트 */}
            <CardList cards={cards.slice(4, 8)} showTitle={false}/>
      </div>
      <Footer/>
      </div>
    );
};


//스타일 변수
const titleStyle = {
    fontSize:'28px',
    margin:'30px 0 15px 0',
    fontWeight:'bold'
};


const title2Style = {
  fontSize:'20px',
  margin:'0 0 27px 0',
  fontWeight:'200'
};


const sliderContainer = {
    width: '1200px'
}

export default RecipeBoard;