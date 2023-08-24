import React from 'react';
import { Link } from 'react-router-dom';
import BoardPartyCardList from '../../component/CardList/BoardPartyCardList';

const RecipeList = () => {
    return (
        <div className='recipe-list container'>
            
            <BoardPartyCardList />
        </div>
    );
};

export default RecipeList;