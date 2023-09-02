package com.project.recipe.reply.service;

import com.project.recipe.reply.dto.ReplyDto;

import java.util.List;
import java.util.Map;

public interface ReplyService {
    void saveReply(ReplyDto dto);

    void updateReply(ReplyDto dto);

    void deleteReply(int rplNum);

    List<ReplyDto> getList(int rcpNum, Map<String, Object> result);

}
