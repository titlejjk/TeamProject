package com.project.user.dto.UserEnumClass;

public enum UserStatus {

    ACTIVE("ACTIVE"),//활동회원
    INACTIVE("INACTIVE"); //탈퇴회원

    private final String value;

    UserStatus(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
    //문자열 값을 해당 enum객체로 변환하는 역할
    public static UserStatus fromValue(String value){
        for(UserStatus status :  UserStatus.values()){
            if(status.getValue().equalsIgnoreCase(value)){
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid UserStatus value: " + value);
    }
}
