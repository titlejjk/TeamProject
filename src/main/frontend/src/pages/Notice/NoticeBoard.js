import axios from 'axios';
import React, { useEffect,useState} from 'react';
import './NoticeBoard.css';
import { Link } from 'react-router-dom';

const NoticeBoard = ({ user}) => {
    const [posts, setPosts] = useState([]);
    
    const [isAdmin, setIsAdmin] = useState(false);

    // user 정보에 따라 관리자 여부 확인
    // if (user && user.role === 'admin') {
    //     setIsAdmin(true);
    // }
      
        useEffect(() => {
          axios.get('http://localhost:5000/posts')
            .then(response => {
              setPosts(response.data);
            })
            .catch(error => {
              console.error('Error fetching posts:', error);
            });
        }, []);
      
        return (
            <div className="notice-board container">
                <div className='notice-top'>
                <h1 className='notice-title'>공지사항</h1>
              {/*   {isAdmin && <Link to="/noticeWrite" className='go-notice-write'>글작성</Link>} */}
                    {/* 관리자 계정으로 로그인했을경우 글작성 링크가 나온다. */}
                    
                    </div>
            <ul className="notice-list">
              {posts.map(post => (
                <li className="notice-item" key={post.id}>
                    <Link to="/noticeDetail" className='post-title'>{post.title}</Link>
                      {/*    <p className='post-content'>{post.content}</p> */}
                      <p className='post-date'>{post.createdDate}</p>
                    
                </li>
              ))}
            </ul>
          </div>
        );
    
};

export default NoticeBoard;