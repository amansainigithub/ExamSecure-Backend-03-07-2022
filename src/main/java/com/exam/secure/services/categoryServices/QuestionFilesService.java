package com.exam.secure.services.categoryServices;

import com.amazonaws.services.importexport.model.InvalidCustomsException;
import com.exam.secure.bucket.bucketModels.BucketModel;
import com.exam.secure.bucket.bucketService.BucketService;
import com.exam.secure.entities.categoryEntities.BranchModel;
import com.exam.secure.entities.categoryEntities.QuestionFiles;
import com.exam.secure.entities.categoryEntities.QuestionsAnswerModel;
import com.exam.secure.entities.categoryEntities.RootCategoryModel;
import com.exam.secure.interfaces.categoryInterfaces.QuestionFilesInterface;
import com.exam.secure.repository.categoryRepository.QuestionAnswerRepository;
import com.exam.secure.repository.categoryRepository.QuestionFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class QuestionFilesService implements QuestionFilesInterface {

    @Autowired
    private BucketService bucketService;

    @Autowired
    private QuestionFilesRepository questionFilesRepository;

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @Override
    public QuestionFiles uploadQuestionFiles(MultipartFile file, Long questionAnswerId) {
        QuestionFiles questionFiles = null ;
        try {
            //get Question-Answer By Id
            QuestionsAnswerModel questionsAnswerModel = this.questionAnswerRepository
                                                            .findById(questionAnswerId).
                                                             orElseThrow(()-> new InvalidCustomsException("Data Not Found !!"));

            //Upload File To S3-Bucket
            BucketModel bucketModel  =  this.bucketService.uploadFile(file);

            //Set data to questionFiles
            questionFiles =new QuestionFiles();
            questionFiles.setFileName(bucketModel.getFileName());
            questionFiles.setFileUrl(bucketModel.getBucketUrl());

            //Set QuestionAnswer Object
            questionFiles.setQuestionsAnswerModel(questionsAnswerModel);

            //save questionFiles
            this.questionFilesRepository.save(questionFiles);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return questionFiles;
    }

    @Override
    public List<QuestionFiles> getQuestionsFilesByQuestionAnswerId(Long questionAnswerId) {
        List<QuestionFiles> questionFiles = null;
        try {
            questionFiles =    this.questionFilesRepository.getQuestionFilesByQuestionAnswerId(questionAnswerId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
            return questionFiles;
    }

    @Override
    public boolean deleteQuestionFileById(Long id) {
       boolean flag = false;
       try {
          QuestionFiles questionFilesData =   this.questionFilesRepository.findById(id).get();

          //delete File To S3
           this.bucketService.deleteFile(questionFilesData.getFileName());

           //delete to DB
           this.questionFilesRepository.deleteById(id);
           flag = true;
       }catch (Exception e)
       {
           e.printStackTrace();
       }
       return flag;
    }

    @Override
    public QuestionFiles updateQuestionFile(MultipartFile file, Long id) {
        QuestionFiles questionFiles = null;
        try {
            QuestionFiles data =   this.questionFilesRepository.findById(id).orElseThrow(() -> new InvalidCustomsException("OBJECT_NULL"));

            //Delete File [HANDLE TO BUCKET SERVICE IF FILE_NOT_FOUND]
            this.bucketService.deleteFile(data.getFileName());

            //Upload File To S3-Bucket
            BucketModel bucketModel  =  this.bucketService.uploadFile(file);

            //Set Data
            data.setFileUrl(bucketModel.getBucketUrl());
            data.setFileName(bucketModel.getFileName());

            //update data
            questionFiles =  this.questionFilesRepository.save(data);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return questionFiles;
    }


}
