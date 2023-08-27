package com.project.recipe.reply.service;

import com.project.recipe.reply.dao.ReplyMapper;
import com.project.recipe.reply.dto.ReplyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplySeviceImpl implements ReplyService {

    @Autowired
    private ReplyMapper rplMapper;

    @Override
    public void saveReply(ReplyDto dto) {
        rplMapper.insertRpl(dto);
    }

//    @Override
//    public void updateReply(RcpRplDto dto) {
//
//    }
//
//    @Override
//    public void deleteReply(int rplNum) {
//
//    }
//
//    @Override
//    public List<RcpRplDto> getList() {
//        return null;
//    }
}
