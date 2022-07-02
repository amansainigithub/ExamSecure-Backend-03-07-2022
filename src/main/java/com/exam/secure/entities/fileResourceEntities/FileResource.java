package com.exam.secure.entities.fileResourceEntities;

import com.exam.secure.commonEnums.FileResourceType;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class FileResource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 500)
    private String url;

    @Column(length = 500)
    private String fileName;

    @Column(length = 500)
    @Enumerated(EnumType.STRING)
    private FileResourceType fileResourceType;

}
