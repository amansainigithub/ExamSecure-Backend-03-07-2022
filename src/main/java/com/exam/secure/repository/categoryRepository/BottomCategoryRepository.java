package com.exam.secure.repository.categoryRepository;

import com.exam.secure.entities.categoryEntities.BottomCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BottomCategoryRepository extends JpaRepository<BottomCategoryModel,Long> {

    @Query("SELECT u FROM BottomCategoryModel u WHERE u.subCategoryModel.id = :id")
    List<BottomCategoryModel> getBottomCategoriesBySubCategoryId(@Param("id") Long id);


}
