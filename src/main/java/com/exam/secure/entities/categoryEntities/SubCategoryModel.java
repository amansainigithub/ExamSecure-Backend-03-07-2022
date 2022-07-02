package com.exam.secure.entities.categoryEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class SubCategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String subCategoryName;

    @Column(length = 500)
    private String defaultName;

    @Column(length = 3000)
    private String shortDescription;

    @Column(length = 5000)
    private String longDescription;

    private boolean status = false;

    @Column(length = 1000)
    private String fileUrl;

    @Column(length = 1000)
    private String fileName;

    private String bgColor;

    private String storingDateTime;

    private String sequenceNum;

    @ManyToOne
    private RootCategoryModel rootCategoryModel;

    @OneToMany(mappedBy = "subCategoryModel",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BottomCategoryModel> bottomCategoryModel ;
}
