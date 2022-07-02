package com.exam.secure.services.categoryServices;


import com.amazonaws.services.importexport.model.InvalidCustomsException;
import com.exam.secure.bucket.bucketModels.BucketModel;
import com.exam.secure.bucket.bucketService.BucketService;
import com.exam.secure.entities.categoryEntities.BottomCategoryModel;
import com.exam.secure.interfaces.categoryInterfaces.BottomCategoryInterface;
import com.exam.secure.publicAllowanceController.modelTemp.BottomTempModel;
import com.exam.secure.repository.categoryRepository.BottomCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class BottomCategoryService implements BottomCategoryInterface {

    @Autowired
    private BottomCategoryRepository bottomCategoryRepository;

    @Autowired
    private BucketService bucketService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BottomCategoryModel saveBottomCategory(BottomCategoryModel bottomCategoryModel) {
        BottomCategoryModel bottomCategoryData = null;
        try {
            bottomCategoryData =  this.bottomCategoryRepository.save(bottomCategoryModel);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bottomCategoryData;
    }

    @Override
    public List<BottomCategoryModel> getAllBottomCategory() {
        List<BottomCategoryModel> bottomCategoryList = null;
        try {
            bottomCategoryList = this.bottomCategoryRepository.findAll();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return bottomCategoryList;
    }

    @Override
    public boolean deleteBottomCategoryById(Long bottomCategoryId) {
       boolean flag =false;
       try {
            this.bottomCategoryRepository.deleteById(bottomCategoryId);
            flag =true;
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       return flag;
    }

    @Override
    public BottomCategoryModel getBottomCategoryById(Long bottomCategoryId) {
        BottomCategoryModel bottomCategoryModel = null;
        try {
             bottomCategoryModel =   this.bottomCategoryRepository.findById(bottomCategoryId).get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bottomCategoryModel;
    }

    @Override
    public List<BottomCategoryModel> getBottomCategoriesBySubCategoryId(Long subCategoryId) {
        List<BottomCategoryModel> bottomCategoryData = null;
        try {
            bottomCategoryData =  this.bottomCategoryRepository.getBottomCategoriesBySubCategoryId(subCategoryId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bottomCategoryData;
    }

    @Override
    public BottomCategoryModel uploadBottomCategoryFile(MultipartFile file, Long id) {
        BottomCategoryModel bottomCategoryModel = null;
        try {
            BottomCategoryModel data =   this.bottomCategoryRepository.findById(id).orElseThrow(() -> new InvalidCustomsException("OBJECT_NULL"));

            //Delete File [HANDLE TO BUCKET SERVICE IF FILE_NOT_FOUND]
            this.bucketService.deleteFile(data.getFileName());

            //Upload File To S3-Bucket
            BucketModel bucketModel  =  this.bucketService.uploadFile(file);

            //Set Data
            data.setFileUrl(bucketModel.getBucketUrl());
            data.setFileName(bucketModel.getFileName());

            //update data
            bottomCategoryModel =  this.bottomCategoryRepository.save(data);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return bottomCategoryModel;
    }

    @Override
    public List<BottomTempModel> getBottomCategoriesBySubCategoryId_RC(Long subCategoryId) {  //RESPONSE CHANGED
            List<BottomTempModel> list =new ArrayList<>();
        try {
            List<BottomCategoryModel> bottomCategoryData =  this.bottomCategoryRepository.getBottomCategoriesBySubCategoryId(subCategoryId);
            for(BottomCategoryModel bottomCategoryModel : bottomCategoryData)
            {
                  BottomTempModel bottomTempModel =   this.modelMapper.map(bottomCategoryModel, BottomTempModel.class);
                  list.add(bottomTempModel);
                  //System.out.println(bottomTempModel.toString());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

}
