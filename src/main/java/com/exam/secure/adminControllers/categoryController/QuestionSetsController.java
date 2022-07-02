package com.exam.secure.adminControllers.categoryController;

import com.exam.secure.adminControllers.adminUrlMappings.CategoryUrlMappings;
import com.exam.secure.adminControllers.adminUrlMappings.RootMapping;
import com.exam.secure.entities.categoryEntities.QuestionSetsModel;
import com.exam.secure.interfaces.categoryInterfaces.QuestionsSetsInterface;
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
public class QuestionSetsController {

    @Autowired
    private QuestionsSetsInterface questionsSetsInterface;

    @PostMapping(CategoryUrlMappings.SAVE_QUESTION_SET)
    public ResponseEntity<?> saveQuestionSet(@RequestBody QuestionSetsModel questionSetsModel)
    {
        QuestionSetsModel data =  this.questionsSetsInterface.saveQuestionSet(questionSetsModel);

        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @PutMapping(CategoryUrlMappings.UPDATE_QUESTION_SET)
    public ResponseEntity<?> updateQuestionSet(@RequestBody QuestionSetsModel questionSetsModel)
    {
        QuestionSetsModel data =  this.questionsSetsInterface.saveQuestionSet(questionSetsModel);
        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @DeleteMapping(CategoryUrlMappings.DELETE_QUESTION_SET_BY_ID)
    public ResponseEntity<?> deleteQuestionSetById(@PathVariable Long questionSetId)
    {
        if( this.questionsSetsInterface.deleteQuestionSetById(questionSetId))
        {
            return ResponseEntity.ok(new MessageResponse("delete success"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(CategoryUrlMappings.GET_ALL_QUESTION_SET)
    public ResponseEntity<?> getAllQuestionSet()
    {
        List<QuestionSetsModel> list =  this.questionsSetsInterface.getAllQuestionSets();
        if(list != null)
        {
            return ResponseEntity.ok(list);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @GetMapping(CategoryUrlMappings.GET_QUESTION_SET_BY_ID)
    public ResponseEntity<?> getQuestionSetById(@PathVariable Long questionSetId)
    {
        QuestionSetsModel data =  this.questionsSetsInterface.getQuestionSetById(questionSetId);
        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @GetMapping(CategoryUrlMappings.GET_QUESTION_SET_BY_CHAPTER_ID)
    public ResponseEntity<?> getQuestionSetByChapterId(@PathVariable Long chapterId)
    {
        List<QuestionSetsModel> data =  this.questionsSetsInterface.getQuestionSetByChapterId(chapterId);
        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @PostMapping(CategoryUrlMappings.UPLOAD_QUESTION_SET_FILE)
    public ResponseEntity<?> uploadQuestionSetFile(@RequestParam MultipartFile file, @PathVariable("id") Long id)
    {
        QuestionSetsModel questionSetData =  this.questionsSetsInterface.uploadQuestionSetFile(file,id);

        if(questionSetData != null)
        {
            return ResponseEntity.ok(questionSetData);
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


}
