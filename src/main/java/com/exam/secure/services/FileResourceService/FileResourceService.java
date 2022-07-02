package com.exam.secure.services.FileResourceService;

import com.exam.secure.interfaces.fileResourceInterface.FileResourceInterface;
import com.exam.secure.bucket.bucketModels.BucketModel;
import com.exam.secure.bucket.bucketService.BucketService;
import com.exam.secure.commonEnums.FileResourceType;
import com.exam.secure.entities.fileResourceEntities.FileResource;
import com.exam.secure.repository.fileResourceRepository.FileResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileResourceService implements FileResourceInterface {

    @Autowired
    private FileResourceRepository fileResourceRepository;

    @Autowired
    private BucketService bucketService;


    @Override
    public FileResource saveFileResource(@RequestParam MultipartFile file) {
        FileResource resource = null;
        if(this.fileResourceRepository.findAll().isEmpty())
        {
            //Set Enum Value
            FileResource fileResource = new FileResource();
            fileResource.setFileResourceType(FileResourceType.QUIZ_REPORT);

            //Upload Image to S3
            BucketModel bucketModel = this.bucketService.uploadFile(file);

            //Set Data To Resource
            fileResource.setFileName(bucketModel.getFileName());
            fileResource.setUrl(bucketModel.getBucketUrl());

            try {
                resource =  this.fileResourceRepository.save(fileResource);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return resource;
        }else
        {
            //UPDATE RESOURCE
            resource = this.fileResourceRepository.findAll().get(0);
            //Set Enum Value

            //remove old File
            this.bucketService.deleteFile(resource.getFileName());

            resource.setFileResourceType(FileResourceType.QUIZ_REPORT);

            //Upload Image to S3
            BucketModel bucketModel = this.bucketService.uploadFile(file);

            //Set Data To Resource
            resource.setFileName(bucketModel.getFileName());
            resource.setUrl(bucketModel.getBucketUrl());
        }
        try {
            resource =  this.fileResourceRepository.save(resource);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resource;
    }

    @Override
    public List<FileResource> getAllFileResource() {
        List<FileResource> fileResourcesList = null;
        try {
            fileResourcesList =   this.fileResourceRepository.findAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return fileResourcesList;
    }
}
