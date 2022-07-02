package com.exam.secure.services.categoryServices;

import com.amazonaws.services.importexport.model.InvalidCustomsException;
import com.exam.secure.bucket.bucketModels.BucketModel;
import com.exam.secure.bucket.bucketService.BucketService;
import com.exam.secure.entities.categoryEntities.QuestionSetsModel;
import com.exam.secure.entities.categoryEntities.RootCategoryModel;
import com.exam.secure.entities.categoryEntities.SubCategoryModel;
import com.exam.secure.interfaces.categoryInterfaces.SubCategoryInterface;
import com.exam.secure.repository.categoryRepository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SubCategoryService implements SubCategoryInterface {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

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

}
