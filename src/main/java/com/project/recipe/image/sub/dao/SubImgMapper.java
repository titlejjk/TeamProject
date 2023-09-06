package com.project.recipe.image.sub.dao;

import com.project.recipe.image.sub.dto.SubImgDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubImgMapper {
    //서브 이미지 추가
    void insertImg(SubImgDto dto);
    //서브 이미지 삭제
    void deleteImg(int num);
    //서브 이미지 목록
    List<SubImgDto> getImgs(int num);
}
