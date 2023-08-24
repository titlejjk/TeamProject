import { Link } from 'react-router-dom';
import './Footer.css';

function Footer(){
    const year = new Date().getFullYear();

    return(
        <div className="footer-container">
            <div className="footer">
                <div className="left">
                    <h3>(주)멍냥키친</h3>
                    <h2>4485-0850<span>평일 09:00 ~ 18:00</span></h2>
                    <div className="btnContent"> 
                        <span>카카오톡 문의</span><p>평일 09:00 ~ 18:00시</p>
                    </div>
                    <div className="btnContent">
                        <span>1:1 문의</span><p>365일<br/>고객센터 운영시간에 순차적으로 답변드리겠습니다.</p>
                    </div>
                </div>

                <div className="middle">
                    <ul className="navbar">
                        <li><Link to="/">멍냥소개</Link></li>
                        <li><Link to="/notice">공지사항</Link></li>
                        <li><Link to="/login">로그인</Link></li>
                        <li><Link to="/signup">회원가입</Link></li>
                    </ul> 
                </div>

                <div className="right">
                    <div className="member">
                        <p>Front-end</p>
                        <ul>
                            <li>
                                <a href="https://github.com/Saeunnnnni">
                                <div className="member-info">
                                    <img src="/images/front1.png" alt="조새은" />
                                    <span>조새은</span>
                                </div>
                            </a>
                            </li>
                            <li>
                                <a href="https://github.com/junghyeyoung">
                                    <div className="member-info">
                                        <img src="/images/front2.png" alt="정혜영" />
                                        <span>정혜영</span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="https://github.com/baekcoding">
                                    <div className="member-info">
                                        <img src="/images/front3.png" alt="백민규" />
                                        <span>백민규</span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="https://github.com/GAM3BO1">
                                    <div className="member-info">
                                        <img src="/images/front4.png" alt="이슬아" />
                                        <span>이슬아</span>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div className="member">
                        <p>Back-end</p>
                        <ul>
                            <li>
                                <a href="https://github.com/titlejjk">
                                    <div className="member-info">
                                        <img src="/images/back1.png" alt="조준근" />
                                        <span>조준근</span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="https://github.com/jungyu777">
                                    <div className="member-info">
                                        <img src="/images/back2.png" alt="이준규" />
                                        <span>이준규</span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="https://github.com/Krystal025">
                                    <div className="member-info">
                                        <img src="/images/back3.png" alt="김수정" />
                                        <span>김수정</span>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <p className="copy">© {year}. Front-end Components All rights reserved.</p>
            </div>
           
    )    
}

export default Footer;