import { Link, useLocation } from "react-router-dom";
import React from "react";
import "./MyPageNav.css"; // CSS 파일 임포트

function MyPageNav() {
  const location = useLocation();

  return (
    <div>
      <div className="mypage-navbar">
        <Link
          className={`navbarMenu ${
            location.pathname.startsWith("/myPage") ? "active" : ""
          }`}
          to={"/myPage"}
        >
          마이페이지
        </Link>
        <Link
          className={`navbarMenu ${
            location.pathname === "/myContent" ? "active" : ""
          }`}
          to={"/myContent"}
        >
          나의 글
        </Link>
        <Link
          className={`navbarMenu ${
            location.pathname === "/myComment" ? "active" : ""
          }`}
          to={"/myComment"}
        >
          나의 댓글
        </Link>
      </div>
    </div>
  );
}

export default MyPageNav;
