package com.exam.secure.services.categoryServices;

import com.amazonaws.services.importexport.model.InvalidCustomsException;
import com.exam.secure.bucket.bucketModels.BucketModel;
import com.exam.secure.bucket.bucketService.BucketService;
import com.exam.secure.customModels.CustomBranchModel;
import com.exam.secure.entities.categoryEntities.BranchModel;
import com.exam.secure.entities.categoryEntities.RootCategoryModel;
import com.exam.secure.interfaces.categoryInterfaces.BranchInterface;
import com.exam.secure.repository.categoryRepository.BranchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchService implements BranchInterface {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private BucketService bucketService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BranchModel saveBranch(BranchModel branchModel) {
        BranchModel branchData = null;
        try {
          branchData  = this.branchRepository.save(branchModel);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return branchData;
    }



    @Override
    public boolean deleteBranchById(Long id) {
      boolean flag=false;
      try {
            this.branchRepository.deleteById(id);
            flag = true;
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
        return flag;
    }

    @Override
    public List<BranchModel> getAllBranch() {
       return  this.branchRepository.findAll();
    }

    @Override
    public BranchModel getBranchById(Long branchId) {
       BranchModel branchModel = null;
       try {
           branchModel = this.branchRepository.findById(branchId).get();
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       return branchModel;
    }

    @Override
    public List<BranchModel> getBranchListByBottomCategoryId(Long bottomCategoryId) {
        List<BranchModel> data = null;
        try {
            data =  this.branchRepository.getBranchListByBottomCategoryId(bottomCategoryId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }


    @Override
    public List<CustomBranchModel> getBranchListBySubId(Long subId) {
        ArrayList<CustomBranchModel> list = null;
        try {
            List<BranchModel> data  =  this.branchRepository.getBranchListBySubId(subId);
            list  = new ArrayList<>();

            if(data != null)
            {
                for(BranchModel branchModel : data)
                {
                    CustomBranchModel customBranchModel =new CustomBranchModel();
                    customBranchModel.setId(branchModel.getId());
                    customBranchModel.setBgColor(branchModel.getBgColor());
                    customBranchModel.setFileUrl(branchModel.getFileUrl());
                    customBranchModel.setStatus(branchModel.isStatus());
                    customBranchModel.setBranchName(branchModel.getBranchName());
                    customBranchModel.setChaptersModels(branchModel.getChaptersModels());
                    list.add(customBranchModel);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }



    @Override
    public BranchModel uploadBranchCategoryFile(MultipartFile file, Long id) {
        BranchModel branchModel = null;
        try {
            BranchModel data =   this.branchRepository.findById(id).orElseThrow(() -> new InvalidCustomsException("OBJECT_NULL"));

            //Delete File [HANDLE TO BUCKET SERVICE IF FILE_NOT_FOUND]
            this.bucketService.deleteFile(data.getFileName());

            //Upload File To S3-Bucket
            BucketModel bucketModel  =  this.bucketService.uploadFile(file);

            //Set Data
            data.setFileUrl(bucketModel.getBucketUrl());
            data.setFileName(bucketModel.getFileName());

            //update data
            branchModel =  this.branchRepository.save(data);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return branchModel;
    }
}
