import { Link, useLocation } from "react-router-dom";
import React from "react";
import "./MyPageNav.css"; // CSS 파일 임포트

function MyPageNavAdmin() {
  const location = useLocation();

  return (
    <div>
      <div className="mypage-navbar">
        <Link
          className={`navbarMenu ${
            location.pathname.startsWith("/admin") ? "active" : ""
          }`}
          to={"/admin"}
        >
          관리자 페이지
        </Link>
      </div>
    </div>
  );
}

export default MyPageNavAdmin;
