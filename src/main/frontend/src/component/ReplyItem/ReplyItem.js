// ReplyItem.js

import './ReplyItem.css';
import Pagination from '../../lib/Pagination';

// 댓글을 생성하는 function
function ReplyItem({ thumb, name, content, createdAt }) {
    return (
      <div className="recipe_detail_reply_item">
        <div className="image_container">
          <img src={thumb} alt="reply thumb" />
        </div>
        <div>
          <div className="insight">
            <span>{name}</span>
            <span>{createdAt}</span>
            <span>답글</span>
          </div>
          <p>{content}</p>
        </div>
      </div>
    );
}

export default ReplyItem;