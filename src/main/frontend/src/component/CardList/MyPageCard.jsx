import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart as faHeartSolid } from "@fortawesome/free-solid-svg-icons";
import { faHeart as faHeartRegular } from "@fortawesome/free-regular-svg-icons";
import { Link } from "react-router-dom";

const MyPageCard = ({ card, showTitle }) => {
  console.log("showTitle value:", showTitle); // showTitle 값 로깅

  return (
    <div className="my-page-card">
      <Link to={`/RecipeDetail/${card.id}`}>
        <img
          className="my-page-card-img"
          src={card.main_image_url}
          alt={card.title}
        />
      </Link>

      {showTitle && (
        <div className="my-page-card-title-box">
          <h2 className="my-page-cardList-title">{card.title}</h2>
        </div>
      )}
    </div>
  );
};

export default MyPageCard;
