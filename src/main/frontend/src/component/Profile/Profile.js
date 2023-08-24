import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Profile.css"; // 스타일 파일을 임포트하고 있다고 가정합니다.

const Profile = () => {
  const [profileData, setProfileData] = useState({
    nickname: "",
    followers: 0,
    following: 0,
    bio: "",
    profileImage: null, // 기본 프로필 이미지 경로
  });

  // // 백엔드에서 프로필 데이터를 가져오는 함수
  // const UsersProfileData = () => {
  //   axios
  //     .get("/api/usersData") // 백엔드 API 엔드포인트를 사용하세요.
  //     .then((response) => {
  //       // 백엔드에서 받아온 프로필 데이터를 설정합니다.
  //       setProfileData(response.data);
  //     })
  //     .catch((error) => {
  //       console.error("프로필 데이터 가져오기 실패:", error);
  //     });
  // };

  // useEffect(() => {
  //   // 컴포넌트가 마운트될 때 프로필 데이터를 가져옵니다.
  //   UsersProfileData();
  // }, []); // 빈 배열을 전달하여 한 번만 호출되도록 설정

  return (
    <div className="profile-container">
      <div className="profile-image">
        <img
          src={profileData.profileImage || "images/default_profile.png"}
          alt="Profile"
        />
      </div>
      <div className="profile-info">
        <h2>{profileData.nickname || "닉네임을 설정해주세요"}</h2>
        <div className="followers">
          <span>팔로워 {profileData.followers || 0}</span>
          <span>팔로잉 {profileData.following || 0}</span>
        </div>
        <hr />
        <p className="bio">{profileData.bio || "비어있음"}</p>
      </div>
    </div>
  );
};

export default Profile;
