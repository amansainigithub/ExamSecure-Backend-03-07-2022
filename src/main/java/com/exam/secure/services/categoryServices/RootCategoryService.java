package com.exam.secure.services.categoryServices;

import com.amazonaws.services.importexport.model.InvalidCustomsException;
import com.exam.secure.adminControllers.adminUrlMappings.CategoryUrlMappings;
import com.exam.secure.bucket.bucketModels.BucketModel;
import com.exam.secure.bucket.bucketService.BucketService;
import com.exam.secure.entities.categoryEntities.RootCategoryModel;
import com.exam.secure.interfaces.categoryInterfaces.RootCategoryInterface;
import com.exam.secure.repository.categoryRepository.RootCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class RootCategoryService implements RootCategoryInterface {

    @Autowired
    private BucketService bucketService;

    @Autowired
    private RootCategoryRepository rootCategoryRepository;

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
}
