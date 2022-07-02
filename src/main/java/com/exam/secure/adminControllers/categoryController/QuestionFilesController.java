package com.exam.secure.adminControllers.categoryController;

import com.exam.secure.adminControllers.adminUrlMappings.CategoryUrlMappings;
import com.exam.secure.adminControllers.adminUrlMappings.RootMapping;
import com.exam.secure.entities.categoryEntities.QuestionFiles;
import com.exam.secure.interfaces.categoryInterfaces.QuestionFilesInterface;
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
public class QuestionFilesController {

    @Autowired
    private QuestionFilesInterface questionFilesInterface;


    @PostMapping(CategoryUrlMappings.UPLOAD_QUESTION_FILES)
    public ResponseEntity<?> uploadRootCategoryFile(@RequestParam MultipartFile file, @PathVariable("questionAnswerId") Long questionAnswerId)
    {
        QuestionFiles data =  this.questionFilesInterface.uploadQuestionFiles(file,questionAnswerId);

        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(CategoryUrlMappings.GET_QUESTION_FILES_BY_QUESTION_ANSWER_ID)
    public ResponseEntity<?> getQuestionsFilesByQuestionAnswerId(@PathVariable("questionAnswerId") Long questionAnswerId)
    {
        List<QuestionFiles> data =  this.questionFilesInterface.getQuestionsFilesByQuestionAnswerId(questionAnswerId);

        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @DeleteMapping(CategoryUrlMappings.DELETE_QUESTION_FILE_BY_ID)
    public ResponseEntity<?> deleteQuestionFileById(@PathVariable Long id)
    {
        if(this.questionFilesInterface.deleteQuestionFileById(id))
        {
            return ResponseEntity.ok(new MessageResponse("delete success"));
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @PostMapping(CategoryUrlMappings.UPDATE_QUESTION_FILE)
    public ResponseEntity<?> updateQuestionFile(@RequestParam MultipartFile file, @PathVariable("id") Long id)
    {
        QuestionFiles data =  this.questionFilesInterface.updateQuestionFile(file,id);

        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

}
