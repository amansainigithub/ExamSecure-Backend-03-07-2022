package com.exam.secure.publicAllowanceController.publicCategoryController;

import com.exam.secure.interfaces.categoryInterfaces.QuestionAnswerInterface;
import com.exam.secure.publicAllowanceController.modelTemp.QuestionAnswerTempModel;
import com.exam.secure.publicAllowanceController.urlMappings.UrlMappingPublic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(UrlMappingPublic.API_PUBLIC_AUTH_CRED)
public class PublicQuestionAnswerController {

    @Autowired
    private QuestionAnswerInterface questionAnswerInterface;

    @GetMapping(UrlMappingPublic.GET_QUESTION_ANSWER_LIST_BY_QUESTION_SET_ID_PUBLIC_RC)
    public ResponseEntity<?> getQuestionAnswerListByQuestionSetIdPublic_RC(@PathVariable Long id)
    {
        List<QuestionAnswerTempModel> list =  this.questionAnswerInterface.getQuestionAnswerListByQuestionSetIdPublic_RC(id);

        if(list != null)
        {
            return ResponseEntity.ok(list);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

}
