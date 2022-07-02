package com.exam.secure.services.categoryServices;

import com.amazonaws.services.importexport.model.InvalidCustomsException;
import com.exam.secure.bucket.bucketModels.BucketModel;
import com.exam.secure.bucket.bucketService.BucketService;
import com.exam.secure.entities.categoryEntities.ChaptersModel;
import com.exam.secure.interfaces.categoryInterfaces.ChaptersInterface;
import com.exam.secure.publicAllowanceController.modelTemp.ChapterTempModel;
import com.exam.secure.repository.categoryRepository.ChaptersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChaptersService implements ChaptersInterface {

    @Autowired
    private ChaptersRepository chaptersRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BucketService bucketService;


    @Override
    public ChaptersModel saveChapter(ChaptersModel chaptersModel) {
        ChaptersModel chaptersData = null;
        try {
              chaptersData =  this.chaptersRepository.save(chaptersModel);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return chaptersData;
    }

    @Override
    public List<ChaptersModel> getAllChapters() {
        return this.chaptersRepository.findAll();
    }

    @Override
    public boolean deleteChapterById(Long chapterId) {
       boolean flag =false;
       try {
            this.chaptersRepository.deleteById(chapterId);
            flag = true;
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       return flag;
    }

    @Override
    public ChaptersModel getChapterById(Long chapterId) {
        ChaptersModel chaptersModel = null;
        try {
             chaptersModel =  this.chaptersRepository.findById(chapterId).get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return chaptersModel;
    }

    @Override
    public List<ChaptersModel> getChaptersByBranchId(Long branchId) {
        List<ChaptersModel> list = null;
        try {
            list = this.chaptersRepository.getBottomCategoryByIdService(branchId);
        }
        catch (Exception e)
        {
                e.printStackTrace();
        }
        return list;
    }

    @Override
    public ChaptersModel uploadChapterFile(MultipartFile file, Long id) {
        ChaptersModel chaptersModel = null;
        try {
            ChaptersModel data =   this.chaptersRepository.findById(id).orElseThrow(() -> new InvalidCustomsException("OBJECT_NULL"));

            //Delete File [HANDLE TO BUCKET SERVICE IF FILE_NOT_FOUND]
            this.bucketService.deleteFile(data.getFileName());

            //Upload File To S3-Bucket
            BucketModel bucketModel  =  this.bucketService.uploadFile(file);

            //Set Data
            data.setFileUrl(bucketModel.getBucketUrl());
            data.setFileName(bucketModel.getFileName());

            //update data
            chaptersModel =  this.chaptersRepository.save(data);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return chaptersModel;
    }

    @Override
    public List<ChapterTempModel> getChaptersByBranchCategoryId(Long branchId, boolean status) {
        List<ChapterTempModel> ctm = null ;
        try {
            List<ChaptersModel> list = this.chaptersRepository.getChaptersByBranchCategoryId(branchId,status);

             ctm = new ArrayList<>();
            for(ChaptersModel cm : list)
            {
                ChapterTempModel chapterTempModel =   this.modelMapper.map(cm, ChapterTempModel.class);
                ctm.add(chapterTempModel);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ctm;
    }
}
