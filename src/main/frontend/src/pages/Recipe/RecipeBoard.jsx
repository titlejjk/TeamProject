import React, { useState, useEffect } from 'react';
import SearchBar from '../../component/SearchBar/SearchBar';
import ImageCategory from '../../component/ImageCategory/ImageCategory';
import MainImg from '../../component/MainImg/MainImg';
import axios from 'axios';
import SlickSlider from '../../lib/slickSlide'; 
import { Arrow } from '../../lib/arrow'; 
import BoardRecipeCardList from '../../component/CardList/BoardRecipeCardList';
  

const RecipeBoard = () => {
     /*json이 저장된 주소를 불러와서 변수에 담기 */
 const categoriesUrl = "http://localhost:5000/categories";   
    /*게시판 카테고리 목록을 저장하고 각각의 배열에 담기게 초기값 설정 */
  const [categories, setCategories] = useState({ recipeBoardCategory: [], chefBoardCategory: [] });
    
    /*각각 카테고리목록을 axios로 불러오기 */
     useEffect(() => { 
    axios.get(categoriesUrl)
        .then((Response) => {
            setCategories(Response.data)
            //console.log(Response.data)
        })   
        .catch((error) => {        
          console.error('Error fetching data:', error);
        });
    }, []) 
    

    const Settings = {
      infinite: false,
      speed: 500,
      slidesToShow: 7,
      slidesToScroll: 1,
      nextArrow: <Arrow />,
      prevArrow: <Arrow />
    };
      //console.log(recipes)
      
      
    return ( 
      
      <div className='container'>
            {/* 메인 이미지 :  가장 좋아요가 많은 이미지가 출력된다. */}
          <div className='RecipeBoard-mainImg'>
          
              <MainImg/>
            </div>

            {/* 이미지 카테고리 */}
            <p style={titleStyle}>카테고리</p>
              <ImageCategory categories={categories.recipeBoardCategory} /> 
                          
                
              
              {/* 쉐프리스트 카테고리 : card list  + slider 적용 */}
              <p  style={titleStyle }>쉐프 소개</p> 
                {/* chef list  + slider 적용 */}
                <SlickSlider items={categories.chefBoardCategory.map((category, index) => (
                  <ImageCategory key={index} categories={[category]} />
                ))} settings={Settings} /> 

             
              {/* 레시피  검색창 */}
                <SearchBar />
                  
              {/* 레시피 전체 리스트 출력됨 */}
              <BoardRecipeCardList/>
              </div>
      
        
      );
  };


  //스타일 변수


  const titleStyle = {
    fontSize:'20px',
    margin:'0 0 27px 0',
    fontWeight: 'bold',
    
  };

export default RecipeBoard;