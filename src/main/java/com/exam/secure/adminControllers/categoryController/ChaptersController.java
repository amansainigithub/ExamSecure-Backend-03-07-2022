package com.exam.secure.adminControllers.categoryController;

import com.exam.secure.adminControllers.adminUrlMappings.CategoryUrlMappings;
import com.exam.secure.adminControllers.adminUrlMappings.RootMapping;
import com.exam.secure.entities.categoryEntities.ChaptersModel;
import com.exam.secure.interfaces.categoryInterfaces.ChaptersInterface;
import com.exam.secure.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(RootMapping.ROOT_ADMIN_MAPPING)
@PreAuthorize("hasRole('ADMIN')")
public class ChaptersController {

    @Autowired
    private ChaptersInterface chaptersInterface;

    @PostMapping(CategoryUrlMappings.CREATE_CHAPTER)
    public ResponseEntity<?> createChapter(@RequestBody ChaptersModel chaptersModel)
    {
        ChaptersModel data =  this.chaptersInterface.saveChapter(chaptersModel);

        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @PutMapping(CategoryUrlMappings.UPDATE_CHAPTER)
    public ResponseEntity<?> updateChapter(@RequestBody ChaptersModel chaptersModel)
    {
        ChaptersModel data =  this.chaptersInterface.saveChapter(chaptersModel);

        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @GetMapping(CategoryUrlMappings.GET_ALL_CHAPTERS)
    public ResponseEntity<?> getAllChapters()
    {
        List<ChaptersModel> chaptersList =  this.chaptersInterface.getAllChapters();
        if( chaptersList != null)
        {
            return ResponseEntity.ok(chaptersList);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @DeleteMapping(CategoryUrlMappings.DELETE_CHAPTER_BY_ID)
    public ResponseEntity<?> deleteChapterById(@PathVariable Long chapterId)
    {
        if( this.chaptersInterface.deleteChapterById(chapterId))
        {
            return ResponseEntity.ok(new MessageResponse("delete success"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(CategoryUrlMappings.GET_CHAPTER_BY_ID)
    public ResponseEntity<?> getChapterById(@PathVariable Long chapterId)
    {
      ChaptersModel chaptersModel =    this.chaptersInterface.getChapterById(chapterId);
        if(chaptersModel != null)
        {
            return ResponseEntity.ok(chaptersModel);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(CategoryUrlMappings.GET_CHAPTERS_BY_BRANCH_ID)
    public ResponseEntity<?> getChaptersByBranchId(@PathVariable Long branchId)
    {
        List<ChaptersModel> chaptersModel =  this.chaptersInterface.getChaptersByBranchId(branchId);
        if(chaptersModel != null)
        {
            return ResponseEntity.ok(chaptersModel);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @PostMapping(CategoryUrlMappings.UPLOAD_CHAPTER_FILE)
    public ResponseEntity<?> uploadChapterFile(@RequestParam MultipartFile file, @PathVariable("id") Long id)
    {
        ChaptersModel chapterData =  this.chaptersInterface.uploadChapterFile(file,id);

        if(chapterData != null)
        {
            return ResponseEntity.ok(chapterData);
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

}
