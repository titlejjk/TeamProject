import React, { useState } from "react"
import "react-quill/dist/quill.snow.css"
import "./RecipeWrite.css"
import Editor from '../../component/Editor/Editor'

function RecipeWrite() {
    //variable: react-quill Editor content
    const [content, setContent] = useState("")
    //variable: attachment
    const [selectedFiles, setSelectedFiles] = useState([]);

    //function: input onChange for attachments
    const handleFileChange = (e) => {
      setSelectedFiles([...e.target.files]);
    };

    return (
      <div className="container">
        <div className="editor-container">
          {/* 
            component: react-quill Editor
            props: content, setContent
            => to pass data from child component(Editor) to parent component(NoticeWrite)
          */}
          <Editor content={content} setContent={setContent}/>
          <div className="file-container">
            <input
              type="file"
              id="fileInput"
              multiple
              style={{ 
                display: "none"
              }}
              accept="image/*"
              onChange={handleFileChange}
            />
            <label htmlFor="fileInput" className="upload-button">
              첨부파일
            </label>
              
            {/* selected file names */}
            <div className="selected-files">
              {selectedFiles.map((file, index) => (
                <div className="file-name" key={index}>
                  {file.name}
                </div>                                                                                                                                                                                                                                                                                                        
              ))}
            </div>
          </div>

          <div className="button-container">
            <button 
              className="submit-button"
              onClick={() => alert(content)}>
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