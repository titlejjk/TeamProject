import React, { useState, useEffect } from "react";
import axios from "axios";
import MyPageNavAdmin from "../../component/MyPageNav/MyPageNavAdmin";
import "./AdminPage.css";

const AdminPage = () => {
  const [users, setUsers] = useState([]);
  const [userStatusList, setUserStatusList] = useState([]);

  useEffect(() => {
    // API에서 회원 정보를 가져옵니다.
    axios
      .get("/user/list")
      .then((response) => {
        const userData = response.data;
        setUsers(userData);
        //console.log(response.data);
        setUserStatusList(userData.map((user) => user.userStatus));
      })
      .catch((error) => {
        console.error("Error fetching members:", error);
      });
  }, []);

  const handleStatus = (index, userNum) => {
    const updatedStatusList = [...userStatusList];
    if (updatedStatusList[index] === "ACTIVE") {
      updatedStatusList[index] = "INACTIVE";

      axios
        .post("/user/deactivate", null, {
          params: {
            userNum: userNum,
          },
        }) // API 엔드포인트와 데이터를 전달하세요.
        .then((response) => {})
        .catch((error) => {
          console.error("Error toggling member status:", error);
        });
    } else {
      updatedStatusList[index] = "ACTIVE";

      axios
        .post("/user/active", null, {
          params: {
            userNum: userNum,
          },
        })
        .catch((error) => {
          console.error("Error toggling member status:", error);
        });
    }
    setUserStatusList(updatedStatusList);
  };

  return (
    <div>
      <hr />
      <MyPageNavAdmin />
      <hr />
      <div className="page-container">
        <table>
          <thead>
            <tr>
              <th>이메일</th>
              <th>이름</th>
              <th>성별</th>
              <th>생일</th>
              <th>계정 생성일</th>
              <th>상태</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user, index) => {
              return (
                <tr key={user.userNum}>
                  <td>{user.userEmail}</td>
                  <td>{user.userNickname}</td>
                  <td>{user.userGender}</td>
                  <td>{user.userBirthday}</td>
                  <td>{user.userCreatedAt}</td>
                  <td>
                    <button
                      className={`status-button ${userStatusList[
                        index
                      ].toLowerCase()}`}
                      onClick={() => handleStatus(index, user.userNum)}
                    >
                      {userStatusList[index]}
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default AdminPage;
