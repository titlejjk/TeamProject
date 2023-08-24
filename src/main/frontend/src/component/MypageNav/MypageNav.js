import { Link, useLocation } from "react-router-dom";
import React from "react";
import "./MyPageNav.css"; // CSS 파일 임포트

function MypageNav() {
  const location = useLocation();

  return (
    <div>
      <div className="mypage-navbar">
        <Link
          className={`navarMenu ${
            location.pathname === "/MyPage" ? "active" : ""
          }`}
          to={"/MyPage"}
        >
          마이페이지
        </Link>
        <Link
          className={`navarMenu ${
            location.pathname === "/MyContent" ? "active" : ""
          }`}
          to={"/MyContent"}
        >
          나의 글
        </Link>
        <Link
          className={`navarMenu ${
            location.pathname === "/MyComment" ? "active" : ""
          }`}
          to={"/MyComment"}
        >
          나의 댓글
        </Link>
        {/* <Link
          className={`navarMenu ${
            location.pathname === "/Like" ? "active" : ""
          }`}
          to={"/Like"}
        >
          좋아요
        </Link> */}
      </div>
    </div>
  );
}

export default MypageNav;
