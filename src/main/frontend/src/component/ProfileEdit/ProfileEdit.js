import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import jwt_decode from "jwt-decode";
import axios from "axios";
import "./ProfileEdit.css";

const ProfileEdit = () => {
  const [newToken, setNewToken] = useState(""); // 새로운 토큰 상태 추가
  const [email, setEmail] = useState("");
  const [nickname, setNickname] = useState("");
  const [nicknameError, setNicknameError] = useState(""); // 닉네임 오류 메세지
  const [gender, setGender] = useState(""); // "남" 또는 "여" 값 설정
  const [birthdate, setBirthdate] = useState("");
  const [birthdateError, setBirthdateError] = useState(""); // 생년월일 오류 메세지
  const [profileImage, setProfileImage] = useState(""); // 이미지 파일 상태 관리
  const [defaultProfileImage, setDefaultProfileImage] = useState(
    "/images/default_profile.png"
  );
  const [bio, setBio] = useState("");

  const navigate = useNavigate();

  //닉네임 길이 2~10 제한두는 코드
  const handleNicknameChange = (e) => {
      const newNickname = e.target.value;
      setNickname(newNickname);
      if (newNickname.length >= 2 && newNickname.length <= 10) {
        setNicknameError("");
      } else if (newNickname.length === 1) {
        setNicknameError("닉네임이 너무 짧습니다.");
      } else if (newNickname.length >= 11) {
        setNicknameError("닉네임이 너무 깁니다.");
      }
  };

  const handleGenderChange = (e) => {
    setGender(e.target.value);
  };

  const handleBirthdateChange = (e) => {
    const newBirthdate = e.target.value;
    setBirthdate(newBirthdate);

    if (/^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/.test(newBirthdate)) {
      setBirthdateError(null);
    } else {
      setBirthdateError("올바른 날짜 형식 (YYYY-MM-DD)으로 입력하세요.");
    }
  };

  const handleProfileImageChange = (e) => {
    e.preventDefault();
    const file = e.target.files[0];
    if (file) {
      setProfileImage(file); // 파일 자체를 상태에 저장
      // 파일이 선택되면 defaultProfileImage를 비웁니다.
      setDefaultProfileImage(null);
    } else {
      // 파일 선택이 해제되면 기본 이미지를 다시 표시합니다.
      setDefaultProfileImage("/images/default_profile.png");
    }
  };

  const handleBioChange = (e) => {
    setBio(e.target.value);
  };

  const handleProfileUpdate = (e) => {
    e.preventDefault();
    const userToken = localStorage.getItem('login-token');
    const decodedToken = jwt_decode(userToken);
    const userNum = decodedToken.userNum;
    console.log(userNum);

    const formData = new FormData();
    //forData에 데이터 전부 넣어주기
    formData.append("userNum", userNum);
    formData.append("userEmail", email);
    formData.append("userImage", profileImage); // 이미지 파일을 FormData에 추가
    formData.append("userNickname", nickname);
    formData.append("userGender", gender);
    formData.append("userBirthday", birthdate);
    formData.append("userIntroduction", bio);
    console.log(profileImage);

    axios
      .post("/user/updateuser", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then((response) => {
        console.log("프로필 정보 업데이트 성공:", response.data);

        // 새로운 토큰을 받아와서 상태로 설정
        const newToken = response.data;
        setNewToken(newToken);
        localStorage.setItem('login-token', newToken);
        console.log(response.data);
        // 성공 시 메시지 표시 및 마이페이지로 이동
        alert("프로필 정보가 업데이트되었습니다.");
        // 리다이렉트할 경로 설정
        const redirectPath = "/mypage"; // 원하는 경로로 수정
        navigate(redirectPath); // 페이지 리다이렉트
      })
      .catch((error) => {
        console.error("프로필 정보 업데이트 실패:", error);
        // 실패 시 오류 메시지 표시
        alert("프로필 정보 수정에 실패했습니다.");
      });
  };

  // 컴포넌트가 마운트될 때 사용자의 이메일을 로컬 스토리지에서 불러오기
  useEffect(() => {
    const newToken = localStorage.getItem('login-token');
    if (newToken) {
      // 토큰 해석
      const decodedToken = jwt_decode(newToken); // jwt 모듈을 사용하여 토큰 해석
      if (decodedToken && decodedToken.userNum) {
        // 해석한 토큰에 이메일 정보가 있는지 확인하고, 있다면 이메일 값과 생일, 닉네임을 가져와서 설정
        setEmail(decodedToken.userEmail);
        setBirthdate(decodedToken.userBirthday);
        setNickname(decodedToken.userNickname);
        setGender(decodedToken.userGender);
      } else {
        console.error("토큰에서 이메일 정보를 찾을 수 없습니다.");
      }
    } else {
      //만약, 로그인이 안되어있다면 로그인페이지로 이동w
      navigate("/login");
    }
  }, []);

  //비밀번호 변경페이지로 이동
  const navigatePasswordChange = () => {
    navigate("/changePwd");
  };

  return (
    <div className="profile-edit-form">
      <div className="profile-edit-top">
        <h2>회원정보 수정</h2>
        <Link className="withDrawal" to="/withDrawal">
          탈퇴하기
        </Link>
      </div>
      <form className="formStyle">
        <div className="profile-edit-input">
          <label>이메일</label>
          <input className="box" type="email" value={email} readOnly />
        </div>
        <div className="profile-edit-input">
          <label>닉네임</label>
          <input
            className="box"
            type="text"
            value={nickname}
            placeholder="2자 이상 10자 이하로 입력해주세요"
            onChange={handleNicknameChange}
          />
        </div>
        {nicknameError && <div className="error">{nicknameError}</div>}
        <div className="profile-gender-input">
          <label>성별</label>
          <label className="radio-man">
            <input
              type="radio"
              value="MALE"
              checked={gender === "MALE"}
              onChange={handleGenderChange}
            />
            남자
          </label>
          <label className="radio-woman">
            <input
              type="radio"
              value="FEMALE"
              checked={gender === "FEMALE"}
              onChange={handleGenderChange}
            />
            여자
          </label>
        </div>
        <div className="profile-edit-input">
          <label>생년월일</label>
          <input
            className="box"
            type="text"
            placeholder="YYYY-MM-DD"
            value={birthdate}
            onChange={handleBirthdateChange}
          />
        </div>
        {birthdateError && <div className="error">{birthdateError}</div>}
        <div className="profile-edit-image">
          <label>프로필 이미지</label>
          <input
            className="imgInput"
            type="file"
            accept="image/*"
            onChange={handleProfileImageChange}
          />
        </div>
        <div className="profile-image-preview">
          <img
            src={
              profileImage
                ? URL.createObjectURL(profileImage)
                : defaultProfileImage
            }
            alt="프로필 이미지"
            style={{
              border: "1px solid #d3d3d3",
              borderRadius: "10px",
              maxWidth: "180px",
              maxHeight: "180px",
            }}
          />
        </div>
        <div className="profile-edit-input">
          <label>한줄소개</label>
          <input
            className="box"
            type="text"
            value={bio}
            onChange={handleBioChange}
          />
        </div>
        <button className="users-edit-button" onClick={handleProfileUpdate}>
          회원정보 수정
        </button>
        <button className="pwd-edit-button" onClick={navigatePasswordChange}>
          비밀번호 변경
        </button>
      </form>
    </div>
  );
};

export default ProfileEdit;