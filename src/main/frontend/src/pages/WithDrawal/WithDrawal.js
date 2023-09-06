import React from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import jwt_decode from "jwt-decode"; // jwt-decode 라이브러리를 불러옵니다.
import "./WithDrawal.css";

function WithDrawal() {
  const navigate = useNavigate();
  const handleWithDrawal = async () => {
    try {
      // 로컬 스토리지에서 토큰을 가져옵니다.
      const userToken = localStorage.getItem("login-token");

      // JWT 디코딩을 사용하여 토큰에서 사용자 번호를 추출합니다.
      const decodedToken = jwt_decode(userToken);
      const userNum = decodedToken.userNum;
      console.log(userNum);

      // 사용자 번호를 백엔드로 전송하여 회원 비활성화 요청을 보냅니다.
      const response = await axios.post(`/user/deactivate?userNum=${userNum}`);

      if (response.status === 200) {
        // 회원 탈퇴가 성공하면 로컬 스토리지를 클리어하고 홈페이지로 이동합니다.
        localStorage.clear();
        alert("회원 탈퇴가 성공적으로 처리되었습니다.");
        navigate("/");
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };

  const handleCancel = () => {
    navigate("/myPage");
  };

  return (
    <div className="withdrawal-container">
      <h2>정말 탈퇴하시겠습니까?</h2>
      <div className="withdrawal-button">
        <button className="yes-button" onClick={handleWithDrawal}>
          예
        </button>
        <button className="no-button" onClick={handleCancel}>
          아니오
        </button>
      </div>
    </div>
  );
}

export default WithDrawal;
