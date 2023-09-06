import React from "react";
import MyPageNav from "../../component/MyPageNav/MyPageNav";
import Profile from "../../component/Profile/Profile";
import MyCommentCardList from "../../component/CardList/MyCommentCardList";
import "./MyContent.css";

const MyComment = () => {
  return (
    <div>
      <hr />
      <MyPageNav />
      <hr />
      <div className="mypage-content container">
        <Profile />
        <MyCommentCardList />
      </div>
    </div>
  );
};

export default MyComment;
