package com.exam.secure.services.categoryServices;

import com.amazonaws.services.importexport.model.InvalidCustomsException;
import com.exam.secure.bucket.bucketModels.BucketModel;
import com.exam.secure.bucket.bucketService.BucketService;
import com.exam.secure.entities.categoryEntities.QuestionSetsModel;
import com.exam.secure.entities.categoryEntities.RootCategoryModel;
import com.exam.secure.interfaces.categoryInterfaces.QuestionsSetsInterface;
import com.exam.secure.repository.categoryRepository.QuestionSetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class QuestionSetsService implements QuestionsSetsInterface {

    @Autowired
    private QuestionSetsRepository questionSetsRepository;

    @Autowired
    private BucketService bucketService;

    @Override
    public QuestionSetsModel saveQuestionSet(QuestionSetsModel questionSetsModel) {
        QuestionSetsModel questionSetsData = null;
        try {
               questionSetsData = this.questionSetsRepository.save(questionSetsModel);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return questionSetsData;
    }

    @Override
    public List<QuestionSetsModel> getAllQuestionSets() {
        return this.questionSetsRepository.findAll();
    }

    @Override
    public boolean deleteQuestionSetById(Long id) {
        boolean flag = false;
        try {
            this.questionSetsRepository.deleteById(id);
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    @Override
    public QuestionSetsModel getQuestionSetById(Long id) {
       QuestionSetsModel questionSetsModel = null;
       try {
           questionSetsModel =  this.questionSetsRepository.findById(id).get();
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       return questionSetsModel;
    }

    @Override
    public List<QuestionSetsModel> getQuestionSetByChapterId(Long id) {
        List<QuestionSetsModel> list = null;
        try {
            list = this.questionSetsRepository.getQuestionSetByChapterId(id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public QuestionSetsModel uploadQuestionSetFile(MultipartFile file, Long id) {
        QuestionSetsModel questionSetsModel = null;
        try {
            QuestionSetsModel data =   this.questionSetsRepository.findById(id).orElseThrow(() -> new InvalidCustomsException("OBJECT_NULL"));

            //Delete File [HANDLE TO BUCKET SERVICE IF FILE_NOT_FOUND]
            this.bucketService.deleteFile(data.getFileName());

            //Upload File To S3-Bucket
            BucketModel bucketModel  =  this.bucketService.uploadFile(file);

            //Set Data
            data.setFileUrl(bucketModel.getBucketUrl());
            data.setFileName(bucketModel.getFileName());

            //update data
            questionSetsModel =  this.questionSetsRepository.save(data);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return questionSetsModel;
    }
}
