import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import jwt_decode from "jwt-decode";
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
  const [nickname, setNickname] = useState("");
  useEffect(() => {
    const userToken = localStorage.getItem("login-token");
    if (userToken) {
      // 토큰 해석
      const decodedToken = jwt_decode(userToken); // jwt 모듈을 사용하여 토큰 해석

      if (decodedToken && decodedToken.userEmail) {
        // 해석한 토큰에 이메일 정보가 있는지 확인하고, API를 통해 프로필 데이터를 가져옵니다.
        axios
          .get(`/profile/${decodedToken.userEmail}`)
          .then((response) => {
            const userData = response.data;
            setProfileData({
              nickname: decodedToken.userNickname,
              followers: userData.followers,
              following: userData.following,
              bio: userData.bio,
              profileImage: userData.profileImage,
            });
          })
          .catch((error) => {
            console.error("프로필 데이터를 가져오는 중 오류 발생:", error);
          });

        // 팔로워 수 조회
        axios
          .get(`/followers/count/${decodedToken.userEmail}`)
          .then((response) => {
            const followerCount = response.data;
            setProfileData((prevData) => ({
              ...prevData,
              followers: followerCount,
            }));
          })
          .catch((error) => {
            console.error("팔로워 수 조회 중 오류 발생:", error);
          });

        // 팔로잉 수 조회
        axios
          .get(`/followings/count/${decodedToken.userEmail}`)
          .then((response) => {
            const followingCount = response.data;
            setProfileData((prevData) => ({
              ...prevData,
              following: followingCount,
            }));
          })
          .catch((error) => {
            console.error("팔로잉 수 조회 중 오류 발생:", error);
          });
      } else {
        console.error("토큰에서 이메일 정보를 찾을 수 없습니다.");
      }
    }
  }, []);

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
