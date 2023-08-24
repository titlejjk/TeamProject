import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Login.css"; // 기존 스타일 파일 임포트
import axios from "axios";

const Login = () => {
  const [email, setUserEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const navigate = useNavigate();

  // 이메일, 패스워드 정규식 표현
  const emailRegEx =
    /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.[A-Za-z]{2,3}$/i;
  const passwordRegEx = /^[A-Za-z0-9]{8,20}$/;

  const emailCheck = (username) => {
    return emailRegEx.test(username);
  };

  const passwordCheck = (password) => {
    if (password.match(passwordRegEx) === null) {
      setPasswordError("비밀번호 형식을 확인해주세요");
    } else {
      setPasswordError("");
    }
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    if (!emailCheck(email)) {
      setEmailError("올바른 이메일 형식이 아닙니다.");
      return;
    }

    if (password.match(passwordRegEx) === null) {
      setPasswordError("비밀번호 형식을 확인해주세요");
      return;
    }

    try {
      // 실제 로그인 요청 처리 (axios를 사용하여 백엔드 API 호출)
      const response = await axios.post("/auth/signin", {
        userEmail: email,
        userPassword: password,
      });

      if (response.data.token) {
        alert("로그인되었습니다!");
        navigate("/");
        // 로그인 성공 후 처리할 작업 추가
      }
    } catch (error) {
      alert("로그인 실패!");
      console.error("로그인 오류:", error);
    }
  };

  const handleEmailChange = (e) => {
    setUserEmail(e.target.value);
    if (!emailCheck(e.target.value)) {
      setEmailError("올바른 이메일 형식이 아닙니다.");
    } else {
      setEmailError("");
    }
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
    passwordCheck(e.target.value);
  };

  return (
    <div className="login-container">
      <form className="login-form">
        <img src="images/mnLogo04.png" alt="mnLogo01" className="login-logo" />
        <input
          type="text"
          placeholder="이메일"
          value={email}
          onChange={handleEmailChange}
        />
        {emailError && <p className="error-message">{emailError}</p>}
        <input
          type="password"
          placeholder="비밀번호"
          value={password}
          onChange={handlePasswordChange}
        />
        {passwordError && <p className="error-message">{passwordError}</p>}
        <button className="login-button" onClick={handleLogin}>
          로그인
        </button>
        <div className="login-links">
          <Link to="/ResetPwd" className="reset">
            비밀번호 재설정
          </Link>
          <Link to="/Signup">회원가입하기</Link>
        </div>
      </form>
    </div>
  );
};

export default Login;
