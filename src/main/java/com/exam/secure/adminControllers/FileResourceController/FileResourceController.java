package com.exam.secure.adminControllers.FileResourceController;

import com.exam.secure.adminControllers.adminUrlMappings.CategoryUrlMappings;
import com.exam.secure.adminControllers.adminUrlMappings.RootMapping;
import com.exam.secure.entities.fileResourceEntities.FileResource;
import com.exam.secure.interfaces.fileResourceInterface.FileResourceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(RootMapping.ROOT_ADMIN_MAPPING)
@PreAuthorize("hasRole('ADMIN')")
public class FileResourceController {

    @Autowired
    private FileResourceInterface fileResourceInterface;


    @PostMapping(CategoryUrlMappings.SAVE_FILE_RESOURCE)
    public ResponseEntity<?> saveFileResource(@RequestParam MultipartFile file)
    {
        FileResource resource =  this.fileResourceInterface.saveFileResource(file);

        if(resource != null)
        {
            return ResponseEntity.ok(resource);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(CategoryUrlMappings.GET_ALL_FILE_RESOURCE)
    public ResponseEntity<?> getAllFileResource()
    {
        List<FileResource> FileResourceList =  this.fileResourceInterface.getAllFileResource();

        if(FileResourceList != null)
        {
            return ResponseEntity.ok(FileResourceList);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

}
