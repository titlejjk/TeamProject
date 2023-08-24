import React, { useState, useEffect} from 'react';
import './CardList.css';
import Card from './Card';
import axios from 'axios';

//paging 처리 없는 main 카드리스트
const RecipeCardList = () => {
    const [cards, setCards] = useState([]);
     //초기값을 빈 배열로 설정
    useEffect(() => { 
        axios.get('http://localhost:5000/recipe')
        .then(response => {
            setCards(response.data);
        })
        .catch(error => {
           //console.error('Error fetching data:', error);
        });
    }, [])
   
    return (
        <div className='recipe-card-list container'>
        <div className="card-list">
            {Array.isArray(cards) && cards.slice(0,4).map((card, index) => (
                <Card key={index} card={card} />
            ))}
                </div>
               
           
        </div>
    );
};

export default RecipeCardList;





