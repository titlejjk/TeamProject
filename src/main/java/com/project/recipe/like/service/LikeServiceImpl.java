package com.project.recipe.like.service;

import com.project.recipe.like.dao.LikeMapper;
import com.project.recipe.like.dto.LikeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeMapper likeMapper;

    //좋아요 토글 기능
    @Override
    public String toogleLike(LikeDto dto) {
        int count = likeMapper.countLike(dto.getRcpNum());
        if(count > 0){
            //이미 좋아요를 누른 경우, 좋아요 기록 삭제
            likeMapper.deleteLike(dto);
            return "Like Deleted!";
        }else{
            //좋아요를 누르지 않은 경우, 좋아요 기록 저장
            likeMapper.insertLike(dto);
            return "Like Inserted!";
        }
    }

    //좋아요 개수 반환
    @Override
    public int countLike(int rcpNum) {

        return likeMapper.countLike(rcpNum);
    }

}
