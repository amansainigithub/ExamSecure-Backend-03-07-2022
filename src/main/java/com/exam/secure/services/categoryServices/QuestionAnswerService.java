package com.exam.secure.services.categoryServices;

import com.exam.secure.entities.categoryEntities.QuestionSetsModel;
import com.exam.secure.entities.categoryEntities.QuestionsAnswerModel;
import com.exam.secure.interfaces.categoryInterfaces.QuestionAnswerInterface;
import com.exam.secure.publicAllowanceController.modelTemp.QuestionAnswerTempModel;
import com.exam.secure.repository.categoryRepository.QuestionAnswerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionAnswerService  implements QuestionAnswerInterface {

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public QuestionsAnswerModel saveQuestionAnswer(QuestionsAnswerModel questionsAnswerModel) {
        QuestionsAnswerModel qaModel = null;
        try {
            qaModel = this.questionAnswerRepository.save(questionsAnswerModel);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return qaModel;
    }

    @Override
    public List<QuestionsAnswerModel> getAllQuestionAnswer() {
       return this.questionAnswerRepository.findAll();
    }

    @Override
    public boolean deleteQuestionAnswerById(Long id) {
        boolean flag=false;
        try {
                this.questionAnswerRepository.deleteById(id);
                flag=true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public QuestionsAnswerModel getQuestionAnswerById(Long id) {
        QuestionsAnswerModel qaModel = null;
        try {
            qaModel = this.questionAnswerRepository.findById(id).get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return qaModel;
    }

    @Override
    public List<QuestionsAnswerModel> getQuestionAnswerByQuestionSetId(Long id) {
        List<QuestionsAnswerModel>  questionsAnswerModel = null;
        try {
            questionsAnswerModel  =     this.questionAnswerRepository.getQuestionAnswerByQuestionSetId(id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return questionsAnswerModel;
    }

    @Override
    public List<QuestionAnswerTempModel> getQuestionAnswerListByQuestionSetIdPublic_RC(Long id) {
        List<QuestionAnswerTempModel> list = new ArrayList<>();
        try {
            List<QuestionsAnswerModel>    questionAnswerTempModels  =  this.questionAnswerRepository.getQuestionAnswerByQuestionSetId(id);
            for(QuestionsAnswerModel qam : questionAnswerTempModels)
            {
                 list.add( this.modelMapper.map(qam, QuestionAnswerTempModel.class));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Page<QuestionsAnswerModel> getQuestionListPagination(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
       return this.questionAnswerRepository.findAll(paging);
    }
}
