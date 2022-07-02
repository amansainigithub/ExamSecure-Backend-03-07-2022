package com.exam.secure.interfaces.fileResourceInterface;

import com.exam.secure.entities.fileResourceEntities.FileResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Component
public interface FileResourceInterface {

    FileResource saveFileResource(MultipartFile file);

    List<FileResource> getAllFileResource();
}
