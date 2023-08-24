package com.project.project.user.dto.UserEnumClass;

public enum UserRole {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;

    UserRole(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
    //문자열 값을 해당 enum객체로 변환하는 역할
    public static UserRole fromValue(String value){
        for(UserRole role : UserRole.values()){
            if(role.getValue().equalsIgnoreCase(value)){
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid UserRole value: " + value);
    }
}
