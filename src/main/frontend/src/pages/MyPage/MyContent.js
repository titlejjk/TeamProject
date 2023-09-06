import React from "react";
import MyPageNav from "../../component/MyPageNav/MyPageNav";
import Profile from "../../component/Profile/Profile";
import MyContentCardList from "../../component/CardList/MyContentCardList";
import "./MyContent.css";

const MyContent = () => {
  return (
    <div>
      <hr />
      <MyPageNav />
      <hr />
      <div className="mypage-content container">
        <Profile />
        <MyContentCardList />
      </div>
    </div>
  );
};

export default MyContent;
