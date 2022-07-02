package com.exam.secure.repository.categoryRepository;

import com.exam.secure.entities.categoryEntities.SubCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryModel,Long> {

    @Query("SELECT u FROM SubCategoryModel u WHERE u.rootCategoryModel.id = :id")
    List<SubCategoryModel> getSubCategoriesByRootCategoryId(@Param("id") Long id);
}
