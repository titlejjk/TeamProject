import React, { useEffect, useMemo, useRef, useState } from "react"
import ReactQuill, { Quill } from "react-quill"
import "react-quill/dist/quill.snow.css"
import "./Editor.css"

//react-quill Editor
function Editor({setTitle, setContent}) {
    const quillRef = useRef()

    //modules for quill toolbar
    //useMemo => to prevent editor disappear bug when module renders
    const modules = useMemo(() => {
      return {
        toolbar: {
          container: [
            [{ header: [1, 2, 3, 4, 5, false] }],
            ["bold", "italic", "underline", "strike"],
            [{ color: [] }, { background: [] }],
            ["blockquote"],
            [{ list: "ordered" }, { list: "bullet" }, { align: [] }],
            ["link", "image"],
          ],
        }
      }
    }, [])

    const onChangeContents = (contents) => {
      // const editorContents = quillRef.current.getEditor().getContents();
      // const contentText = editorContents.ops.map(op => op.insert).join("\n");
      // props.setContent(contentText);
      // console.log(editorContents);

      setContent(contents);
    }

    return (
      <>
          <div className="title-container">
            <input 
              className="title"
              type="text" 
              ref={quillRef}
              // value={title}
              onChange={(e) => setTitle(e.target.value)}
              placeholder="너는 뛰어난 제목이다"
            />
          </div>

          <div className="content-container">
            <ReactQuill
              placeholder="내용을 입력하세요"
              ref={quillRef}
              // value={props.content}
              onChange={onChangeContents}
              modules={modules}
              style={{
                height: "100px"
              }}
            />
          </div>
        </>
    )
}

export default Editor;