package com.project.project.user.dto.UserEnumClass;

public enum UserGender {
    MALE("MALE"), //남성
    FEMALE("FEMALE"); //여성

    private String value;

    UserGender(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    //문자열로부터 해당 enum 객체를 찾는 메서드
    public static UserGender fromValue(String value){
        for(UserGender gender : UserGender.values()){
            if(gender.getValue().equalsIgnoreCase(value)){
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid UserGender value: " +value);
    }
    /*
        리액트에서 사용법
        리액트에서 성별 선택을 위한 드롭다운 또는 라디오 버튼을 구성할 때, 서버에서 받은 값이나
        사용자가 선택한 값을 사용하여 이 enum 클래스와 일치시킬 수 있다.

        1. 옵션 렌더링 : 서버에서 성별 목록을 가져와서 드롭다운 또는 라디오 버튼의 옵션으로 렌더링
        2. 값 보내기 : 사용자가 선택한 성별을 서버로 보낼 때, 해당 선택값이 'MALE' or 'FEMALE'과
                     일치하는지 확인하고 보낸다.
        3. 값 받기 : 서버에서 받은 성별 정보를 사용하여 사용자 인터페이스에 적절히 표시한다.

        <select value={gender} onChange={e => setGender(e.target.value)}>
            <option value="MALE">남성</option>
            <option value="FEMALE">여성</option>
        </select>

        여기서 'gender'는 선택된 성별의 상태를 관리하고, 'setGender'는 그 값을 업데이트하는
        함수이다.
        이로써 사용자가 선택한 성별은 서버로 전송되어 처리될 수 있으며, 서버에서 받은 성별 정보도
        동일한 방식으로 처리가 된다.

     */
}
