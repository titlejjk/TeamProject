import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Signup.css";

const Signup = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [isEmailValid, setIsEmailValid] = useState(false); // 이메일 중복 확인 결과
  const [password, setPassword] = useState("");
  const [passwordError, setPasswordError] = useState(""); // 패스워드 오류 메세지
  const [confirmPassword, setConfirmPassword] = useState("");
  const [nickname, setNickname] = useState("");
  const [nicknameError, setNicknameError] = useState(""); // 닉네임 오류 메세지
  const [withAnimals, setWithAnimals] = useState([]); // 관심있는 음식 체크박스
  //약관 동의 체크박스
  const [agreed, setAgreed] = useState({
    all: false,
    age: false,
    privacy: false,
    promotion: false,
  });

  // 정규식을 이용한 이메일 유효성 검사
  const isEmailValidRegex = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;

  // DB로 회원가입 정보 보내기
  const handleSignup = async (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      setPasswordError("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
      return;
    }
    setPasswordError(""); // 일치하면 에러 메시지 초기화

    try {
      const response = await axios.post("auth/signup", {
        userEmail: email,
        userPassword: password,
        userNickname: nickname,
        petTypeIds: withAnimals,
      });

      console.log("성공!");
      alert("회원가입이 되었습니다!");
      // 회원가입 성공 후 로그인 페이지로 이동
      navigate("/login");
    } catch (error) {
      console.error("회원가입 오류:", error);
      alert("회원가입 불가");
    }
  };

  // DB로 이메일 정보 보내기
  const handleEmailCheck = async (e) => {
    e.preventDefault();

    if (!isEmailValidRegex.test(email)) {
      alert("올바른 이메일 형식이 아닙니다.");
      return;
    }

    try {
      // 이메일 일치 여부 확인 (axios를 사용하여 백엔드 API 호출)
      const response = await axios.get("/auth/check-email", {
        params: {
          userEmail: email,
        },
      });

      console.log(response);

      if (response.data === "Duplicated") {
        setIsEmailValid(false); // 중복된 이메일
        alert("사용할 수 없는 이메일입니다!");
      } else {
        setIsEmailValid(true); // 중복되지 않은 이메일
        alert("사용할 수 있는 이메일입니다!");
      }
    } catch (error) {
      console.error("이메일 중복확인 오류:", error);
    }
  };

  const ANIMAL_MAPPING = {
    반려견: 1,
    반려묘: 2,
    반려햄: 3,
    반려조: 4,
    기타: 5,
    없음: 6,
  };

  const handleWithAnimalChange = (animal) => {
    const value = ANIMAL_MAPPING[animal];

    if (withAnimals.includes(value)) {
      setWithAnimals(withAnimals.filter((item) => item !== value));
    } else {
      setWithAnimals([...withAnimals, value]);
    }
  };

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

  //전체동의를 눌렀을 때 밑 체크박스가 다 선택되게 하는 코드
  const handleAgreeAllChange = () => {
    const newAgreedAll = !agreed.all;
    setAgreed({
      all: newAgreedAll,
      age: newAgreedAll,
      privacy: newAgreedAll,
      promotion: newAgreedAll,
    });
  };
  //각자 체크 박스를 선택할 수 있는 코드
  const handleAgreeSingleChange = (target) => {
    setAgreed((prevAgreed) => ({
      ...prevAgreed,
      [target]: !prevAgreed[target],
    }));
  };

  return (
    <div className="signup-container container ">
      <h1>회원가입</h1>
      <hr />
      <form className="signup-form">
        <label>이메일</label>
        <input
          type="email"
          placeholder="이메일"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        <button className="emailCheck-button" onClick={handleEmailCheck}>
          이메일 중복확인
        </button>

        {isEmailValid ? (
          <span style={{ color: "green" }}>사용 가능한 이메일입니다.</span>
        ) : (
          <span style={{ color: "red" }}>중복된 이메일입니다.</span>
        )}

        <label>비밀번호</label>
        <span className="signup-span">
          영문,숫자를 포함한 8자이상의 비밀번호를 입력해주세요
        </span>
        <input
          type="password"
          placeholder="비밀번호"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <label>비밀번호 확인</label>
        <input
          type="password"
          placeholder="비밀번호 확인"
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
        />
        {passwordError && <span style={{ color: "red" }}>{passwordError}</span>}

        <label>닉네임</label>
        <span className="signup-span">2자 이상 10자 이하로 입력해주세요</span>
        <input
          type="text"
          placeholder="별명(2 - 10자)"
          value={nickname}
          onChange={handleNicknameChange}
        />
        {nicknameError && <span style={{ color: "red" }}>{nicknameError}</span>}

        <div>
          <span className="signup-span2">함께하고 있는 반려동물</span>
          <span className="signup-span">* 중복선택 가능</span>
          <div className="signup-pets">
            <label>
              <input
                type="checkbox"
                onChange={() => handleWithAnimalChange("반려견")}
              />
              반려견
            </label>
            <label>
              <input
                type="checkbox"
                onChange={() => handleWithAnimalChange("반려묘")}
              />
              반려묘
            </label>
            <label>
              <input
                type="checkbox"
                onChange={() => handleWithAnimalChange("반려햄")}
              />
              반려햄
            </label>
            <label>
              <input
                type="checkbox"
                onChange={() => handleWithAnimalChange("반려조")}
              />
              반려조
            </label>
            <label>
              <input
                type="checkbox"
                onChange={() => handleWithAnimalChange("기타")}
              />
              기타
            </label>
            <label>
              <input
                type="checkbox"
                onChange={() => handleWithAnimalChange("없음")}
              />
              없음
            </label>
          </div>
        </div>
        <span className="signup-span2">약관동의</span>
        <div className="signup-agree">
          <label>
            <input
              type="checkbox"
              checked={agreed.all}
              onChange={handleAgreeAllChange}
            />
            전체동의
          </label>
          <label>
            <input
              type="checkbox"
              checked={agreed.age}
              onChange={() => handleAgreeSingleChange("age")}
            />
            만 14세 이상입니다. (필수)
          </label>
          <label>
            <input
              type="checkbox"
              checked={agreed.privacy}
              onChange={() => handleAgreeSingleChange("privacy")}
            />
            개인정보수집 및 이용동의 (필수)
          </label>
          <label>
            <input
              type="checkbox"
              checked={agreed.promotion}
              onChange={() => handleAgreeSingleChange("promotion")}
            />
            이벤트, 쿠폰, 특가 알림 메일 및 SMS 등 수신 (선택)
          </label>
        </div>

        <button className="signup-button" onClick={handleSignup}>
          회원가입하기
        </button>
      </form>
    </div>
  );
};
export default Signup;
