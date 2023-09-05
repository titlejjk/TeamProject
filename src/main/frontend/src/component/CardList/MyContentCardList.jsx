import React, { useState, useEffect } from "react";
import "./MyPageCardList.css";
import MyPageCard from "./MyPageCard";
import axios from "axios";
import Pagination from "../../lib/Pagination.jsx";
import { Link } from "react-router-dom";

const MyContentCardList = () => {
  const [cards, setCards] = useState([]);
  //초기값을 빈 배열로 설정

  const [currentPage, setCurrentPage] = useState(0);
  // 페이징 처리에 관련한 로직 및 상태 추가

  const [totalRecipeCount, setTotalRecipeCount] = useState(0);
  //전체 글의 개수를 표시

  const cardsPerPage = 9;
  //한 페이지에 표시할 카드의 수를 정의
  // 추가
  const [activeButton, setActiveButton] = useState("my-recipe-button");

  useEffect(() => {
    axios
      .get("http://localhost:5000/partyList")
      .then((response) => {
        setCards(response.data);
        setTotalRecipeCount(response.data.length); // 레시피 개수 설정
      })
      .catch((error) => {
        //console.error('Error fetching data:', error);
      });
  }, [currentPage]);
  //axios로 json데이터 가져오기

  //페이지 변경을 처리하며, 현재 페이지에 맞게 표시할 카드들을 슬라이스하여 렌더링하는 함수
  const handlePageChange = ({ selected }) => {
    setCurrentPage(selected);
  };

  //현재 페이지에 표시 되어야 할 카드의 시작 위치 계산
  //현재 페이지 * 한페이지에 표시할 카드 수 =  시작위치
  const offset = currentPage * cardsPerPage;

  //현재 페이지에 표시되어야 할 카드들의 배열 구성
  //cards 배열에서 offset ~ offeset+cardsperPages범위를 슬라이스해서 현재 페이지에 가져온다.
  const currentCards = cards.slice(offset, offset + cardsPerPage);

  const handleButtonClick = (buttonType) => {
    setActiveButton(buttonType);
  };

  return (
    <div className="my-page-card-list container">
      <div className="my-page-list-top">
        <div>
          <button
            className={`my-recipe-button ${
              activeButton === "my-recipe-button" ? "active" : ""
            }`}
            onClick={() => handleButtonClick("my-recipe-button")}
          >
            레시피
          </button>
          <button
            className={`my-party-button ${
              activeButton === "my-party-button" ? "active" : ""
            }`}
            onClick={() => handleButtonClick("my-party-button")}
          >
            축하파티
          </button>
        </div>
        <p className="my-page-list-total-count">전체 {totalRecipeCount} 개 </p>
      </div>
      <div className="my-page-card-list">
        {Array.isArray(currentCards) &&
          currentCards.map((card, index) => (
            <MyPageCard key={index} card={card} showTitle={true} />
          ))}
      </div>
      <Pagination
        pageCount={Math.ceil(cards.length / cardsPerPage)}
        onPageChange={handlePageChange}
      />
    </div>
  );
};

export default MyContentCardList;
