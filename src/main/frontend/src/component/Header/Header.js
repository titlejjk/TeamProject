import { Link } from "react-router-dom";
import "./Header.css";
import { useState } from "react";

function Header() {
  // public 폴더까지의 상대 경로 계산: 빌드 환경에 따라 사용하는 것이 좋습니다.
  const publicPath = process.env.PUBLIC_URL;

  const [keyword, setKeyword] = useState("");

  const searchInputChange = (e) => {
    setKeyword(e.target.value);
  };

  const searchSubmit = (e) => {
    e.preventDefault();
    console.log("검색어: ", keyword);
  };

  return (
    <div className="header-container">
      <div className="header">
        <div className="header-left">
          <img
            className="logo"
            src={`${publicPath}/images/mnLogo02.png`}
            alt="logo"
          />
          <nav className="main-nav">
            <ul>
              <li>
                <Link to="/">홈</Link>
              </li>
              <li>
                <Link to="/notice">공지</Link>
              </li>
              <li>
                <Link to="/recipe">게시판</Link>
              </li>
              <li>
                <Link to="/party">축하파티</Link>
              </li>
            </ul>
          </nav>
        </div>

        <div className="header-right">
          <nav className="sub-nav">
            <ul>
              <li>
                <Link to="/login">로그인</Link>
              </li>
              <li>
                <Link to="/signup">회원가입</Link>
              </li>
            </ul>
          </nav>
          <form onSubmit={searchSubmit}>
            <div className="search">
              <input
                className="search-input"
                type="text"
                placeholder="통합 검색"
                value={keyword}
                onChange={searchInputChange}
              />
              <button id="searchBtn" type="submit"></button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Header;
