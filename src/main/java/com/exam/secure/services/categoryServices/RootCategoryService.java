package com.exam.secure.services.categoryServices;

import com.amazonaws.services.importexport.model.InvalidCustomsException;
import com.exam.secure.adminControllers.adminUrlMappings.CategoryUrlMappings;
import com.exam.secure.bucket.bucketModels.BucketModel;
import com.exam.secure.bucket.bucketService.BucketService;
import com.exam.secure.customModels.CustomRootModel;
import com.exam.secure.entities.categoryEntities.QuestionSetsModel;
import com.exam.secure.entities.categoryEntities.RootCategoryModel;
import com.exam.secure.interfaces.categoryInterfaces.RootCategoryInterface;
import com.exam.secure.repository.categoryRepository.QuestionAnswerRepository;
import com.exam.secure.repository.categoryRepository.QuestionSetsRepository;
import com.exam.secure.repository.categoryRepository.RootCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class RootCategoryService implements RootCategoryInterface {

    @Autowired
    private BucketService bucketService;

    @Autowired
    private RootCategoryRepository rootCategoryRepository;


    @Autowired
    private QuestionAnswerRepository  questionAnswerRepository;

    @Autowired
    private QuestionSetsRepository questionSetsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @PostMapping(CategoryUrlMappings.CREATE_ROOT_CATEGORY)
    public RootCategoryModel saveRootCategory(@RequestBody RootCategoryModel rootCategoryModel) {
        RootCategoryModel rootData = null;
        try {
            rootData =   this.rootCategoryRepository.save(rootCategoryModel);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return rootData;
    }

    @Override
    public boolean deleteRootCategoryById(Long rootCategoryId) {
       boolean flag =false;
       try {
            this.rootCategoryRepository.deleteById(rootCategoryId);
            flag=true;
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       return flag;
    }

    @Override
    public List<RootCategoryModel> getAllRootCategory() {
        return  this.rootCategoryRepository.findAll();
//
//        List<CustomRootModel> list =new ArrayList<>();
//        for(RootCategoryModel rootCategoryModel : rootList)
//        {
//            list.add(this.modelMapper.map(rootCategoryModel,CustomRootModel.class));
//        }
//            return  list;
    }

    public List<CustomRootModel> getAllRootCategoryPublic() {
        List<RootCategoryModel> rootList =  this.rootCategoryRepository.findAll();

        List<CustomRootModel> list =new ArrayList<>();
        for(RootCategoryModel rootCategoryModel : rootList)
        {
            list.add(this.modelMapper.map(rootCategoryModel,CustomRootModel.class));
        }
            return  list;
    }

    @Override
    public RootCategoryModel getRootCategoryById(Long rootCategoryId) {
        RootCategoryModel rootCategoryModel = null;
        try {
            rootCategoryModel =   this.rootCategoryRepository.findById(rootCategoryId).get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
      return  rootCategoryModel;
    }

    @Override
    public RootCategoryModel uploadRootCategoryFile(MultipartFile file, Long id) {
       RootCategoryModel rootCategoryModel = null;
       try {
           RootCategoryModel data =   this.rootCategoryRepository.findById(id).orElseThrow(() -> new InvalidCustomsException("OBJECT_NULL"));

           //Delete File [HANDLE TO BUCKET SERVICE IF FILE_NOT_FOUND]
            this.bucketService.deleteFile(data.getFileName());

           //Upload File To S3-Bucket
           BucketModel bucketModel  =  this.bucketService.uploadFile(file);

           //Set Data
           data.setFileUrl(bucketModel.getBucketUrl());
           data.setFileName(bucketModel.getFileName());

           //update data
          rootCategoryModel =  this.rootCategoryRepository.save(data);

       }catch (Exception e)
       {
           e.printStackTrace();
       }
       return rootCategoryModel;
    }

    @Override
    public List<RootCategoryModel> searchByRootCategoryKey(String key) {
      return this.rootCategoryRepository.findByRootCategoryNameContaining(key);
    }

    @Override
    public List<QuestionSetsModel>  getAllSetsByRootId(Long id) {

        RootCategoryModel  rootCategoryModel =  this.rootCategoryRepository.findById(id).get();
        if(rootCategoryModel.getSubCategoryModel() != null)
        {
           if(rootCategoryModel.getSubCategoryModel().get(0).getBottomCategoryModel() != null )
           {
               if(rootCategoryModel.getSubCategoryModel().get(0).getBottomCategoryModel().get(0).getBranchModel() != null)
               {
                   if(rootCategoryModel.getSubCategoryModel().get(0).getBottomCategoryModel().get(0).getBranchModel().get(0).getChaptersModels() != null)
                   {
                       if(rootCategoryModel.getSubCategoryModel().get(0).getBottomCategoryModel().get(0).getBranchModel().get(0).getChaptersModels().get(0).getQuestionSetsModels() != null)
                       {
                           if(rootCategoryModel.getSubCategoryModel().get(0).getBottomCategoryModel().get(0).getBranchModel().get(0).getChaptersModels().get(0).getQuestionSetsModels().get(0).getChaptersModel() != null)
                           {
                               if(rootCategoryModel.getSubCategoryModel().get(0).getBottomCategoryModel().get(0).getBranchModel().get(0).getChaptersModels().get(0).getQuestionSetsModels().get(0).getChaptersModel() != null)
                               {
                                   Long ids =   rootCategoryModel.getSubCategoryModel().get(0).getBottomCategoryModel().get(0).getBranchModel().get(0).getChaptersModels().get(0).getQuestionSetsModels().get(0).getChaptersModel().getId();

                                   return  this.questionSetsRepository.getQuestionSetByChapterId(ids);

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
