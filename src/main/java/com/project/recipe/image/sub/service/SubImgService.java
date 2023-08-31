package com.project.recipe.image.sub.service;

import com.project.recipe.image.sub.dto.SubImgDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SubImgService {

    void saveImg(int rcpNum, List<MultipartFile> subImgs);

    void deleteImg(int subNum);

    List<SubImgDto> getImgs(int subNum);
}
