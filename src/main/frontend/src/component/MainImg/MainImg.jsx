import { useEffect,useState } from "react";
import React  from 'react';
import axios from "axios";

const MainImg = () => {

    const [mostLikedRecipe, setMostLikedRecipe] = useState();

    useEffect(() => {
        // JSON 데이터를 가져온 후 가장 좋아요가 많은 레시피를 찾습니다.
        axios.get('http://localhost:5000/recipe')
            .then(response => {
                const recipes = response.data;
                let maxLikes = 0;
                let mostLikedRecipe = null;
                
                recipes.forEach(recipe => {
                    if (recipe.likes > maxLikes) {
                        maxLikes = recipe.likes;
                        mostLikedRecipe = recipe;
                    }
                });

                setMostLikedRecipe(mostLikedRecipe);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);


    return (
        <div className='mainImg'>
        {mostLikedRecipe && <img src={mostLikedRecipe.main_image_url} alt={mostLikedRecipe.title} style={mainImg}/>}
    </div>
    );
};

const mainImg = {
    width: '1280px',
    height: '500px',
    margin: '5px 0 50px'
}

const titleStyle = {
    fontSize:'20px',
    margin:'50px 0 15px 0',
    fontWeight: 'bold',
  };

export default MainImg;