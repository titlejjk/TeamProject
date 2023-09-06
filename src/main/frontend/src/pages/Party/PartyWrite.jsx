import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "react-quill/dist/quill.snow.css";
import "./PartyWrite.css";
import Editor from "../../component/Editor/Editor";

function PartyWrite() {
  const [title, setTitle] = useState(""); //제목
  const [content, setContent] = useState(""); //내용
  const [mainImg, setMainImg] = useState(""); //썸네일 사진
  const navigate = useNavigate();

  const handleMainImg = (e) => {
    setMainImg([...e.target.files]);
    console.log(mainImg);
  };

  const handleUploadRecipe = (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("title", title); //제목
    formData.append("content", content); //글 내용
    formData.append("mainImg", mainImg); //썸네일 사진
    console.log(formData);

    axios
      .post("/party/insert", formData, {
        headers: {
          "Content-Type": "multipart/subImg",
        },
      })
      .then((response) => {
        console.log("레시피 업로드 성공:", response.data);
        // 성공 시 메시지 표시 및 마이페이지로 이동
        alert("레시피가 업로드 되었습니다");
        navigate("/partyBoard");
      })
      .catch((error) => {
        console.error("레시피 업로드 실패:", error);
        // 실패 시 오류 메시지 표시
        alert("레시피 업로드가 실패했습니다.");
      });
  };

  return (
    <div className="container">
      <div className="main-container">
        <input
          type="file"
          id="fileInput"
          multiple
          style={{
            display: "none",
          }}
          accept="image/*"
          onChange={handleMainImg}
        />
        <label htmlFor="fileInput" className="mainImg-upload-button scroll">
          {mainImg.length > 0 ? (
            mainImg.map((img, index) => (
              <div key={index} className="mainImg">
                <img
                  src={URL.createObjectURL(img)}
                  alt={`Thumbnail ${index}`}
                />
              </div>
            ))
          ) : (
            <div className="mainImg-placeholder">썸네일 이미지 선택</div>
          )}
        </label>
      </div>
      <div className="recipe-editor">
        {/* 
            component: react-quill Editor
            props: content, setContent
            => to pass data from child component(Editor) to parent component(NoticeWrite)
          */}
        <Editor content={content} setContent={setContent} />

        <div className="button-container">
          <button
            className="submit-button"
            // onClick={() => alert(content)}
            onClick={handleUploadRecipe}
          >
            작성
          </button>
          <button className="cancel-button" onClick={() => alert("취소")}>
            취소
          </button>
        </div>
      </div>
    </div>
  );
}

export default PartyWrite;
