package com.exam.secure.repository.fileResourceRepository;

import com.exam.secure.entities.fileResourceEntities.FileResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileResourceRepository  extends JpaRepository<FileResource,Long> {
}
