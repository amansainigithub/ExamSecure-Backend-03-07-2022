package com.exam.secure.interfaces.categoryInterfaces;

import com.exam.secure.entities.categoryEntities.ChaptersModel;
import com.exam.secure.publicAllowanceController.modelTemp.ChapterTempModel;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface ChaptersInterface {

    public ChaptersModel saveChapter(ChaptersModel chaptersModel);

    public List<ChaptersModel> getAllChapters();

    public boolean deleteChapterById(Long chapterId);

    public ChaptersModel getChapterById(Long chapterId);


    List<ChaptersModel> getChaptersByBranchId(Long branchId);

    ChaptersModel uploadChapterFile(MultipartFile file, Long id);

    List<ChapterTempModel> getChaptersByBranchCategoryId(Long branchId, boolean status);
}
