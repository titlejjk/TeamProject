

import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart as faHeartSolid } from '@fortawesome/free-solid-svg-icons';
import { faHeart as faHeartRegular } from '@fortawesome/free-regular-svg-icons';
import { Link } from 'react-router-dom';

const Card = ({ card, showTitle }) => {

    console.log("showTitle value:", showTitle); // showTitle 값 로깅
    // 레시피를 like 버튼을 누르면 서버에 전송
    // const [likes, setLikes] = useState(item.likes);  
        
    // 아이콘 like를 눌렀을 때
    const [isLiked, setIsLiked] = useState(false);

    const handleToggleLike = () => {
        setIsLiked(prevIsLiked => !prevIsLiked);
    };

    return (
        <div className="card">
            <Link to={`/RecipeDetail/${card.id}`}>
                <img className="card-img" src={card.main_image_url} alt={card.title} />
            </Link>

            {showTitle && (
                <div className='card-title-box'>
                    <h2 className="cardList-title">{card.title}</h2>
                    <div className='like-box'>
                        <button className='heart-btn' onClick={handleToggleLike}>
                            <FontAwesomeIcon icon={isLiked ? faHeartSolid : faHeartRegular} style={{ color: isLiked ? '#ff6a10' : 'black' }} />
                        </button>
                        {/* <p>{likes}</p> */}
                    </div>
                </div>
            )}
        </div>
    );
};

export default Card;
