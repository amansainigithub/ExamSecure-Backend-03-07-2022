package com.exam.secure.adminControllers.categoryController;

import com.exam.secure.adminControllers.adminUrlMappings.CategoryUrlMappings;
import com.exam.secure.adminControllers.adminUrlMappings.RootMapping;
import com.exam.secure.entities.categoryEntities.QuestionsAnswerModel;
import com.exam.secure.interfaces.categoryInterfaces.QuestionAnswerInterface;
import com.exam.secure.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(RootMapping.ROOT_ADMIN_MAPPING)
@PreAuthorize("hasRole('ADMIN')")
public class QuestionAnswerController {


    @Autowired
    private QuestionAnswerInterface questionAnswerInterface;

    @PostMapping(CategoryUrlMappings.SAVE_QUESTION_ANSWER)
    public ResponseEntity<?> saveQuestionAnswer(@RequestBody QuestionsAnswerModel questionsAnswerModel)
    {
        QuestionsAnswerModel data =  this.questionAnswerInterface.saveQuestionAnswer(questionsAnswerModel);

        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @PutMapping(CategoryUrlMappings.UPDATE_QUESTION_ANSWER)
    public ResponseEntity<?> updateQuestionAnswer(@RequestBody QuestionsAnswerModel questionsAnswerModel)
    {
        QuestionsAnswerModel data =  this.questionAnswerInterface.saveQuestionAnswer(questionsAnswerModel);

        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @DeleteMapping(CategoryUrlMappings.DELETE_QUESTION_ANSWER_BY_ID)
    public ResponseEntity<?> deleteQuestionAnswerById(@PathVariable Long id)
    {
        if(this.questionAnswerInterface.deleteQuestionAnswerById(id))
        {
            return ResponseEntity.ok(new MessageResponse("Delete Success"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(CategoryUrlMappings.GET_ALL_QUESTION_ANSWER)
    public ResponseEntity<?> getAllQuestionAnswer() {
        List<QuestionsAnswerModel> qaList =this.questionAnswerInterface.getAllQuestionAnswer();
        if( qaList != null)
        {
            return ResponseEntity.ok(qaList);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(CategoryUrlMappings.GET_QUESTION_LIST_PAGINATION)
    public ResponseEntity<?> getQuestionListPagination(@PathVariable int pageNo,@PathVariable int pageSize) {
        Page<QuestionsAnswerModel> questionListPage =this.questionAnswerInterface.getQuestionListPagination(pageNo,pageSize);
        if( questionListPage != null)
        {
            return ResponseEntity.ok(questionListPage);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(CategoryUrlMappings.GET_QUESTION_ANSWER_BY_ID)
    public ResponseEntity<?> getQuestionAnswerById(@PathVariable Long id) {
        QuestionsAnswerModel qaData =this.questionAnswerInterface.getQuestionAnswerById(id);
        if( qaData != null)
        {
            return ResponseEntity.ok(qaData);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(CategoryUrlMappings.GET_QUESTION_ANSWER_BY_QUESTION_SET_ID)
    public ResponseEntity<?> getQuestionAnswerByQuestionSetId(@PathVariable Long id) {
        List<QuestionsAnswerModel> data =this.questionAnswerInterface.getQuestionAnswerByQuestionSetId(id);
        if( data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
