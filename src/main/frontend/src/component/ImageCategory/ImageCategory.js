import React from 'react';
import { Link } from 'react-router-dom';
import './ImageCategory.css'

//만든 categories를 map으로 category 객체와 index 값을 가져와서 각각의 key값과 대입이 되어야 할 값을 넣어준다
const ImageCategory = ({ categories }) => {
    //console.log(categories)

    
    
    return (
        <div className="category-list">
            {categories && categories.map((category, index) => (
                <div key={index} className="images-category"> 
                    <Link to={`/recipes/list?userNum=${category.userNum}`}className="category">
                        <img src={category.userProfile} alt={category.userNickname} />
                        <p>{category.userNickname}</p>
                    </Link>
                </div>
            ))}
        </div>
    );
};

export default ImageCategory;