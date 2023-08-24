import React, { useState } from "react";
import axios from "axios";
import "./ResetPwd.css";

const PasswordReset = () => {
  const [email, setEmail] = useState("");
  const [emailError, setEmailError] = useState("");
  const [resetSuccess, setResetSuccess] = useState(false);

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
    setEmailError("");
  };

  const handleResetPassword = async () => {
    if (email === "") {
      setEmailError("이메일을 입력해주세요.");
      return;
    }

    try {
      const response = await axios.post("http://localhost:9999/resetpassword", {
        userEmail: email,
      });

      if (response.data.success) {
        setResetSuccess(true);
      } else {
        setEmailError("이메일을 찾을 수 없습니다.");
      }
    } catch (error) {
      console.error("비밀번호 재설정 오류:", error);
    }
  };

  return (
    <div className="resetPwd-container">
      <form className="resetPwd-form">
        {resetSuccess ? (
          <p>이메일을 확인해주세요. 비밀번호 재설정 링크가 전송되었습니다.</p>
        ) : (
          <div>
            <p>가입한 이메일 주소를 입력해주세요.</p>
            <input
              className="email-input"
              type="email"
              placeholder="이메일"
              value={email}
              onChange={handleEmailChange}
            />
            <p style={{ color: "red" }}>{emailError}</p>
            <button className="resetPwd-button" onClick={handleResetPassword}>
              이메일로 인증코드 받기
            </button>
          </div>
        )}
      </form>
    </div>
  );
};

export default PasswordReset;
