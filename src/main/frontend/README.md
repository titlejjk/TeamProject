# TeamProject - frontend팀


### # Start Project

cd src/main \
npx create-react-app frontend

cd frontend \
npm start


 <br/> 

# 사용기술
<div align="center">
	<img src="https://img.shields.io/badge/React-61DAFB?style=flat&logo=React&logoColor=white" style="width:85px"/>
	<img src="https://img.shields.io/badge/AXIOS-5A29E4?style=flat&logo=AXIOS&logoColor=white" style="width:90px"/>
	<img src="https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=CSS3&logoColor=white" style="width:80px"/>
  <img src="https://img.shields.io/badge/slides-E4637C?style=flat&logo=SlickSlide&logoColor=white" style="width:60px"/>
  <img src="https://img.shields.io/badge/reactrouter-CA4245?style=flat&logo=ReactRouter&logoColor=white" style="width:130px" />
  <img src="https://img.shields.io/badge/fontawesome-528DD7?style=flat&logo=FontAwesome&logoColor=white" style="width:140px"/>
  
</div>


 <br/> 

# 팀원 & 구현 기능
|백민규|이슬아|정혜영|조새은|
|----|----|----|-----|
|![front3](https://github.com/Saeunnnnni/mn_frontend/assets/108113552/f13c6227-22db-4d95-815e-d3520a98ece9) |![front4](https://github.com/Saeunnnnni/mn_frontend/assets/108113552/4f6aad9c-2a8e-4f9d-924c-6664201c5ffa)|![front2](https://github.com/Saeunnnnni/mn_frontend/assets/108113552/d5f7c15f-2d56-4040-8ad3-4fe85e6815d3)|![front1](https://github.com/Saeunnnnni/mn_frontend/assets/108113552/ec9d0788-c13d-4684-a4d1-75fa88a14466)|
|레시피, 공지사항 상세페이지|Header,Footer<br/>메인배너 <br/>공지사항, 레시피, 게시판 글작성페이지| 회원가입<br/> 로그인<br/> 회원정보페이지| 카드리스트 <br/> 카테고리 <br/> 레시피 및 공지사항 리스트| 


 <br/> 
 
# JSON서버를 이용해 데스트 환경 구축
프로젝트 테스트 단게에서 실제 서버와 연동하지 않고 데이터를 다룰 수 있는 환경을 구축하기 위해 json서버 사용
json서버를 통해 가상의 api요청 및 응답을 통해 데이터를 주고 받고 백앤드와 연동을 원활하게 하기 위해 사전 테스트를 진행

### #JSON server
npm install -g json-server \
json-server --watch data.json --port 5000

 <br/> 

### # React Library
- axios \
npm i axios

### # React component

- React slick: carousel component \
npm install react-slick slick-carousel --save

- react-router-dom \
npm i react-router-dom

- paginate \
npm install react-paginate

- React icon \
npm i @fortawesome/fontawesome-svg-core \
npm i @fortawesome/free-solid-svg-icons @fortawesome/free-regular-svg-icons @fortawesome/free-brands-svg-icons \
npm i @fortawesome/react-fontawesome

- React-Quill: rich-text editor \
npm install react-quill --save
