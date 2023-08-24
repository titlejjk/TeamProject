import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import "./ProfileEdit.css";

const ProfileEdit = () => {
  const [email, setEmail] = useState("");
  const [nickname, setNickname] = useState("");
  const [gender, setGender] = useState(""); // "남" 또는 "여" 값 설정
  const [birthdate, setBirthdate] = useState("");
  const [profileImage, setProfileImage] = useState(""); // 이미지 파일 상태 관리
  const [defaultProfileImage, setDefaultProfileImage] = useState(
    "/images/default_profile.png"
  );
  const [bio, setBio] = useState("");

  const navigate = useNavigate();

  // 로그인된 유저의 토큰을 얻는 부분 (예를 들어, localStorage에 저장된 토큰을 사용한다고 가정합니다)
  useEffect(() => {
    const userToken = localStorage.getItem("userToken");
    if (userToken) {
      // axios 요청을 보낼 때 헤더에 토큰을 포함합니다.
      axios.defaults.headers.common["Authorization"] = `Bearer ${userToken}`;

      // 이메일 데이터를 가져오는 요청
      axios
        .get("/api/users/email")
        .then(function (response) {
          const userEmail = response.data.email;
          setEmail(userEmail);
        })
        .catch(function (error) {
          console.error("이메일 데이터 가져오기 실패", error);
        });
    } else {
      // 로그인되지 않은 상태를 처리하는 로직 (예: 로그인 페이지로 리디렉션)
      //      navigate("/login");
    }
  }, []);

  const handleNicknameChange = (e) => {
    setNickname(e.target.value);
  };

  const handleGenderChange = (e) => {
    setGender(e.target.value);
  };

  const handleBirthdateChange = (e) => {
    setBirthdate(e.target.value);
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

  const handleProfileUpdate = () => {
    // 프로필 정보를 서버에 전송
    const formData = new FormData();
    formData.append("userNickname", nickname);
    formData.append("userGender", gender);
    formData.append("userBirthday", birthdate);
    formData.append("userIntroduction", bio);
    if (profileImage) {
      formData.append("userProfile", profileImage);
    }

    axios
      .post("/api/updateProfile", formData)
      .then((response) => {
        console.log("프로필 정보 업데이트 성공:", response.data);
        // 성공 시 메시지 표시 및 마이페이지로 이동
        alert("프로필 정보가 업데이트되었습니다.");
        navigate("/mypage");
      })
      .catch((error) => {
        console.error("프로필 정보 업데이트 실패:", error);
        // 실패 시 오류 메시지 표시
        alert("프로필 정보 업데이트에 실패했습니다.");
      });
  };

  //비밀번호 변경페이지로 이동
  const navigatePasswordChange = () => {
    navigate("/changPwd");
  };
  const handleWithdrawal = () => {
    // TODO: 탈퇴 로직 작성
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
          <input className="box" type="email" defaultValue={email} readOnly />
        </div>
        <div className="profile-edit-input">
          <label>닉네임</label>
          <input
            className="box"
            type="text"
            value={nickname}
            onChange={handleNicknameChange}
          />
        </div>
        <div className="profile-gender-input">
          <label>성별</label>
          <label className="radio-man">
            <input
              type="radio"
              value="MALE"
              checked={gender === "남"}
              onChange={handleGenderChange}
            />
            남자
          </label>
          <label className="radio-woman">
            <input
              type="radio"
              value="FEMALE"
              checked={gender === "여"}
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
