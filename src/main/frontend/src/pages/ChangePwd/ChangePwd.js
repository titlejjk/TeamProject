import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./ChangePwd.css"; // 스타일링 파일 임포트

const ChangePwd = () => {
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [passwordsMatch, setPasswordsMatch] = useState(true); // 비밀번호 일치 여부 상태
  const [validPassword, setValidPassword] = useState(true); // 비밀번호 유효성 여부 상태
  const navigate = useNavigate();

  // 비밀번호 확인 함수
  const checkPasswords = () => {
    if (newPassword === confirmPassword) {
      setPasswordsMatch(true);

      // 비밀번호 유효성 검사 (영문, 숫자를 포함한 8자 이상의 비밀번호를 입력)
      if (
        newPassword.length >= 8 &&
        /[0-9]/.test(newPassword) &&
        /[A-Za-z]/.test(newPassword)
      ) {
        setValidPassword(true);

        // Axios를 사용하여 백엔드로 변경된 비밀번호 데이터를 전송
        axios
          .post("/api/changePassword", { newPassword })
          .then((response) => {
            // 비밀번호 변경 성공 시 마이페이지로 이동
            navigate("/mypage");
          })
          .catch((error) => {
            console.error("비밀번호 변경 실패:", error);
          });
      } else {
        setValidPassword(false);
      }
    } else {
      setPasswordsMatch(false);
      alert("비밀번호가 일치하지 않습니다.");
    }
  };

  return (
    <div className="changepwd-container container">
      <form className="changepwd-form">
        <h2>비밀번호 변경</h2>
        <div className="changepwd-input">
          <label>새 비밀번호</label>
          <span className="changepwd-span">
            영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.
          </span>
          <input
            type="password"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
          />
        </div>
        <div className="changepwd-input">
          <label>새 비밀번호 확인</label>
          <input
            type="password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
        </div>
        {!passwordsMatch && (
          <div className="password-mismatch">비밀번호가 일치하지 않습니다.</div>
        )}
        {!validPassword && (
          <div className="password-invalid">
            영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.
          </div>
        )}
        <button className="changepwd-button" onClick={checkPasswords}>
          비밀번호 변경
        </button>
      </form>
    </div>
  );
};

export default ChangePwd;
