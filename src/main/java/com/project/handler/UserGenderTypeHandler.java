package com.project.handler;

import com.project.user.dto.UserEnumClass.UserGender;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//UserGender enum을 위한 TypeHandler를 정의
@MappedTypes(UserGender.class)
public class UserGenderTypeHandler extends BaseTypeHandler<UserGender> {

    //PreparedStatement에 enum을 설정할 때 호출
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UserGender parameter, JdbcType jdbcType) throws SQLException {
        //enum 값을 문자열로 변환하여 DB에 저장
        ps.setString(i, parameter.getValue());
    }

    /*
        ResultSet에서 컬럼 이름(columnName)으로 enum 값을 가져올 때 호출
        getNullableResult(ResultSet rs, String columnName)
        결과 세트 'ResultSet'에서 특정 컬럼 이름에 해당하는 값을 가져올 때 사용된다.
        컬럼 이름을 기준으로 해당 컬럼의 값을 읽어와서 enum타입으로 변환
     */
    @Override
    public UserGender getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : UserGender.fromValue(value);
    }

    /*
        ResultSet에서 컬럼 인덱스(columnIndex)로 enum 값을 가져올 때 호출
        getNullableResult(ResultSet rs, int columnIndex)
        결과 세트 'ResultSet'에서 특정 컬럼 인덱스에 해당하는 값을 가져올 때 사용된다.
        컬럼 인덱스는 컬럼의 순서를 나타내며 1부터 시작된다. 해당 컬럼 인덱스 값을 읽어와
        enum타입으로 변환
     */
    @Override
    public UserGender getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : UserGender.fromValue(value);
    }

    /*
        CallableStatement에서 컬럼 인덱스(columnIndex)로 enum값을 가져올 때 호출
        getNullableResult(CallableStatement cs, int columnIndex)
        'CallableStatement'에서 특정 컬럼 인덱스에 해당하는 값을 가져올 때 사용된다.
        저장 프로시저나 함수를 호출할 때 사용되며, 이 메서드는 저장 프로시저나 함수의 결과에서
        특정 컬럼 인덱스의 값을 읽어와서 enum타입으로 반환
    */
    @Override
    public UserGender getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : UserGender.fromValue(value);
    }


    /*
        SQL쿼리의 결과를 Java의 enum타입으로 변환하는 로직을 구현하는데 사용되며,
        각 메서드는 다른 상황에서 호출된다.
    */

}
