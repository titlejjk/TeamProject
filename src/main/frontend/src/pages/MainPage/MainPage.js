import React from "react";
import "./MainPage.css";
import Banner from "../../component/Banner/Banner";
import MainRecipeCardList from "../../component/CardList/MainRecipeCardList";
import MainPartyCardList from "../../component/CardList/MainPartyCardList";

function MainPage() {
  return (
    <div className="MainPage container">
      <div className="imgWrapper">
        <Banner className="image" />
      </div>

      <div className="board-title">
        지금 <span style={{ color: "#ff6a10" }}>핫🔥한 </span>레시피
      </div>
      <MainRecipeCardList />

      <div className="board-title">
        Today <span style={{ color: "#ff6a10" }}>레시피 </span>
      </div>
      <MainRecipeCardList />

      <div className="board-title">
        오늘의 🎉<span style={{ color: "#ff6a10" }}>주인공 </span>
      </div>
      <MainPartyCardList />
    </div>
  );
}

export default MainPage;
