import React, { useState, useEffect } from 'react';
import SearchBar from '../../component/SearchBar/SearchBar';
import ImageCategory from '../../component/ImageCategory/ImageCategory';
import MainImg from '../../component/MainImg/MainImg';
import axios from 'axios';
import SlickSlider from '../../lib/slickSlide'; 
import { Arrow } from '../../lib/arrow'; 
import BoardRecipeCardList from '../../component/CardList/BoardRecipeCardList';

const RecipeBoard = () => {
     /* 사용자들의 data가 담겨있는 url 변수에 넣기! */
     const usersCategoriesUrl = "http://localhost:9999/user/list";

     const [UsersCategories, setUsersCategories] = useState([]);

    /*각각 카테고리목록을 axios로 불러오기 */
     useEffect(() => { 
    axios.get(usersCategoriesUrl)
        .then((Response) => {
            setUsersCategories(Response.data)
            //console.log(Response.data)
        })   
        .catch((error) => {        
          console.error('Error fetching data:', error);
        });
    }, [])

    //각 카테고리별 이미지 변수에 담기
    const ImagesCategories = [
        { userNickname: '반려묘',userProfile: '/images/animal01.png' },
        { userNickname: '반려견', userProfile: '/images/animal02.png'},
        { userNickname: '반려조', userProfile: '/images/animal03.png'},
        { userNickname: '반려햄', userProfile: '/images/animal04.png' },
        { userNickname: '기타', userProfile: '/images/animal05.png' },
    ];

     //사용자 카테고리 slide setting값
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
                <ImageCategory categories={ImagesCategories} />
                          
                
              
              {/* 쉐프리스트 카테고리 : card list  + slider 적용 */}
              <p  style={titleStyle }>쉐프 소개</p> 
                {/* chef list  + slider 적용 */}
                <SlickSlider items={UsersCategories.map((UserCategory, index) => (
                  <ImageCategory key={index} categories={[UserCategory]} />
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