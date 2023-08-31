import React from "react";
import MyPageNav from "../../component/MyPageNav/MyPageNav";
import Profile from "../../component/Profile/Profile";
import ProfileEdit from "../../component/ProfileEdit/ProfileEdit";
import "./MyPage.css"; // 위에서 작성한 CSS 파일을 임포트하고 있다고 가정합니다.

const MyPage = () => {
  return (
    <div>
      <hr />
      <MyPageNav />
      <hr />

      <div className="mypage-content container">
        <Profile />
        <ProfileEdit />
      </div>
    </div>
  );
};

export default MyPage;
