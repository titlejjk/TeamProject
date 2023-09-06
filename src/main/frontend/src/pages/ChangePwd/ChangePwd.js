import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import jwt_decode from "jwt-decode";
import axios from "axios";
import "./ChangePwd.css";

const ChangePwd = () => {
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [passwordsMatch, setPasswordsMatch] = useState(true);
  const [validPassword, setValidPassword] = useState(true);
  const navigate = useNavigate();

  const checkPasswords = async (e) => {
    e.preventDefault();
    if (newPassword === confirmPassword) {
      setPasswordsMatch(true);

      if (
        newPassword.length >= 8 &&
        /[0-9]/.test(newPassword) &&
        /[A-Za-z]/.test(newPassword)
      ) {
        setValidPassword(true);
        const userToken = localStorage.getItem("login-token");
        if (!userToken) {
          navigate("/login");
        } else {
          const decodedToken = jwt_decode(userToken);
          const userEmail = decodedToken.userEmail;
          console.log(userEmail, newPassword);
          try {
            await axios.post("/user/updatePassword", {
              userEmail: userEmail,
              userNewPassword: newPassword,
            });
            alert("비밀번호 변경 성공!");
            console.log(newPassword);
            navigate("/mypage");
          } catch (error) {
            console.error("비밀번호 변경 실패:", error);
          }
        }
      } else {
        setValidPassword(false);
      }
    } else {
      setPasswordsMatch(false);
      alert("비밀번호가 일치하지 않습니다.");
    }
  };

  useEffect(() => {}, [confirmPassword, navigate]);

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
