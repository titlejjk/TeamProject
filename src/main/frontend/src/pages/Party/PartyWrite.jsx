import React from "react"
import "react-quill/dist/quill.snow.css"
import "./PartyWrite.css"
import Editor from '../../component/Editor/Editor'

function NoticeWrite() {
    return (
      <div className="container">
        <div className="editor-container">
          <Editor/>
        </div> 
      </div>
    )
}

export default NoticeWrite;