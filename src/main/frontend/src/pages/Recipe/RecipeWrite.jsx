import React, { useState } from "react"
import axios from "axios"
import { useNavigate } from "react-router-dom"
import "react-quill/dist/quill.snow.css"
import "./RecipeWrite.css"
import Editor from '../../component/Editor/Editor'

function RecipeWrite() {
    const [title, setTitle] = useState(""); //제목
    const [servingSize, setServingSize] = useState(0);  //n인분
    const [cookingTime, setCookingTime] = useState(0);  //조리 시간
    const [cookingLevel, setCookingLevel] = useState("");  //조리 난이도
    const [ingredients, setIngredients] = useState("");
    const [content, setContent] = useState(""); //내용
    const [mainImg, setMainImg] = useState("")
    const [subImgs, setSubImgs] = useState([]); //서브 이미지
    const navigate = useNavigate();

    const handleMainImg = (e) => {
      setMainImg([...e.target.files]);
      console.log(mainImg);
    };

    //function: input onChange for attachments
    const handleSubImgs = (e) => {
      setSubImgs([...e.target.files]);
      console.log(subImgs);
    };

    const handleUploadRecipe = (e) => {
      e.preventDefault();
      const formData = new FormData();
      formData.append("title", title);  //제목
      formData.append("servingSize", servingSize);  //제공 분량
      formData.append("cookingTime", cookingTime);  //조리 시간
      formData.append("cookingLevel", cookingLevel);  //난이도
      formData.append("ingredients", ingredients);  //재료
      formData.append("content", content);  //글 내용
      formData.append("mainImg", mainImg);  //썸네일 사진
      formData.append("subImgs", subImgs);  //조리 사진
      console.log(formData);
  
      axios
        .post("/recipe/insert", formData, {
          headers: {
            "Content-Type": "multipart/subImg",
        }})
        .then((response) => {
          console.log("레시피 업로드 성공:", response.data);
          // 성공 시 메시지 표시 및 마이페이지로 이동
          alert("레시피가 업로드 되었습니다");
          navigate("/recipeBoard");
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
              display: "none"
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
              <div className="mainImg-placeholder">
                썸네일 이미지 선택
              </div>
            )}
          </label>
        </div>

        <div className="recipe-editor">
          {/* 
            component: react-quill Editor
            props: content, setContent
            => to pass data from child component(Editor) to parent component(NoticeWrite)
          */}
          <Editor 
            setTitle={setTitle}
            setContent={setContent}
          />
          <div className="sub-container">
            <input
              type="file"
              id="fileInput"
              multiple
              style={{ 
                display: "none"
              }}
              accept="image/*"
              onChange={handleSubImgs}
            />
            <label htmlFor="fileInput" className="subImgs-upload-button">
              첨부파일
            </label>
              
            {/* selected file names */}
            <div className="selected-files scroll">
              {subImgs.map((subImg, index) => (
                <div className="file-name" key={index}>
                  {subImg.name}
                </div>                                                                                                                                                                                                                                                                                                        
              ))}
            </div>
          </div>

          <div className="button-container">
            <button 
              className="submit-button"
              onClick={() => alert("제목: " + title + "\n" + "내용: " + content)}
              // onClick={handleUploadRecipe}
            >
              작성
            </button>
            <button 
              className="cancel-button"
              onClick={() => alert("취소")}>
              취소
            </button>
          </div>
        </div> 
      </div>
    )
}

export default RecipeWrite;