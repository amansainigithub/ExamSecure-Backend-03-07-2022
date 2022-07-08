package com.exam.secure.services.categoryServices;

import com.amazonaws.services.importexport.model.InvalidCustomsException;
import com.exam.secure.bucket.bucketModels.BucketModel;
import com.exam.secure.bucket.bucketService.BucketService;
import com.exam.secure.customModels.CustomSubCategoryModel;
import com.exam.secure.entities.categoryEntities.QuestionSetsModel;
import com.exam.secure.entities.categoryEntities.RootCategoryModel;
import com.exam.secure.entities.categoryEntities.SubCategoryModel;
import com.exam.secure.interfaces.categoryInterfaces.SubCategoryInterface;
import com.exam.secure.repository.categoryRepository.QuestionSetsRepository;
import com.exam.secure.repository.categoryRepository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubCategoryService implements SubCategoryInterface {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private QuestionSetsRepository questionSetsRepository;

    @Autowired
    private BucketService bucketService;

    @Override
    public SubCategoryModel saveSubCategoyModel(SubCategoryModel subCategoryModel) {
        SubCategoryModel subCategory =null;

        try {
       subCategory  = this.subCategoryRepository.save(subCategoryModel);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return subCategory;
    }

    @Override
    public boolean deleteSubCategoryById(Long subCategoryId) {
        boolean flag = false;
        try {
                this.subCategoryRepository.deleteById(subCategoryId);
                flag=true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<SubCategoryModel> getAllSubCategory() {
       return this.subCategoryRepository.findAll();
    }

    @Override
    public SubCategoryModel getSubCategoryById(Long subCategoryId) {
        SubCategoryModel subCategoryModel = null;
        try {
              subCategoryModel =   this.subCategoryRepository.findById(subCategoryId).get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
       return subCategoryModel;
    }

    @Override
    public List<SubCategoryModel> getSubCategoriesByRootCategoryId(Long rootCategoryId) {
        List<SubCategoryModel> subCategoryListData = null;
        try {
               subCategoryListData =  this.subCategoryRepository.getSubCategoriesByRootCategoryId(rootCategoryId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return subCategoryListData;
    }

    @Override
    public SubCategoryModel uploadSubCategoryFile(MultipartFile file, Long id) {
        SubCategoryModel subCategoryModel = null;
        try {
            SubCategoryModel data =   this.subCategoryRepository.findById(id).orElseThrow(() -> new InvalidCustomsException("OBJECT_NULL"));

            //Delete File [HANDLE TO BUCKET SERVICE IF FILE_NOT_FOUND]
            this.bucketService.deleteFile(data.getFileName());

            //Upload File To S3-Bucket
            BucketModel bucketModel  =  this.bucketService.uploadFile(file);

            //Set Data
            data.setFileUrl(bucketModel.getBucketUrl());
            data.setFileName(bucketModel.getFileName());

            //update data
            subCategoryModel =  this.subCategoryRepository.save(data);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return subCategoryModel;
    }

    @Override
    public List<CustomSubCategoryModel> getAllSetsBySubId(Long id) {

        SubCategoryModel  subCategoryModel =  this.subCategoryRepository.findById(id).get();

        if(subCategoryModel != null)
        {

            if(subCategoryModel.getBottomCategoryModel() != null )
            {
                if(subCategoryModel.getBottomCategoryModel().get(0).getBranchModel() != null)
                {
                    if(subCategoryModel.getBottomCategoryModel().get(0).getBranchModel().get(0).getChaptersModels() != null)
                    {
                        if(subCategoryModel.getBottomCategoryModel().get(0).getBranchModel().get(0).getChaptersModels().get(0).getQuestionSetsModels() != null)
                        {
                            if(subCategoryModel.getBottomCategoryModel().get(0).getBranchModel().get(0).getChaptersModels().get(0).getQuestionSetsModels().get(0).getChaptersModel() != null)
                            {
                                if(subCategoryModel.getBottomCategoryModel().get(0).getBranchModel().get(0).getChaptersModels().get(0).getQuestionSetsModels().get(0).getChaptersModel() != null)
                                {
                                    Long ids =   subCategoryModel.getBottomCategoryModel().get(0).getBranchModel().get(0).getChaptersModels().get(0).getQuestionSetsModels().get(0).getChaptersModel().getId();

                                    List<QuestionSetsModel> questionSetsModelsList = this.questionSetsRepository.getQuestionSetByChapterId(ids);

                                    List<CustomSubCategoryModel> customSubList =new ArrayList<>();
                                    for(QuestionSetsModel questionSetsModel : questionSetsModelsList)
                                    {
                                        CustomSubCategoryModel customSubCategoryModel =new CustomSubCategoryModel();
                                        customSubCategoryModel.setId(questionSetsModel.getId());
                                        customSubCategoryModel.setQuestionSetName(questionSetsModel.getQuestionSetName());
                                        customSubCategoryModel.setStatus(questionSetsModel.isStatus());
                                        customSubCategoryModel.setPassPercentage(questionSetsModel.getPassPercentage());
                                        customSubCategoryModel.setQuestionLength(questionSetsModel.getQuestionsAnswerModel().size());
                                        customSubCategoryModel.setBgColor(questionSetsModel.getBgColor());
                                        customSubList.add(customSubCategoryModel);
                                    }

                                    return customSubList;

                                }
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

}
