package com.exam.secure.adminControllers.adminUrlMappings;

public class CategoryUrlMappings {

    public static final String CREATE_ROOT_CATEGORY= "createRootCategory";
    public static final String UPDATE_ROOT_CATEGORY= "updateRootCategory";
    public static final String GET_ROOT_CATEGORY_BY_ID= "getRootCategoryById/{rootCategoryId}";
    public static final String SEARCH_BY_ROOT_CATEGORY_KEY= "searchByRootCategoryKey/{key}";
    public static final String DELETE_ROOT_CATEGORY_BY_ID= "deleteRootCategoryById/{rootCategoryId}";
    public static final String GET_ALL_ROOT_CATEGORY= "getAllRootCategory";
    public static final String UPLOAD_ROOT_CATEGORY_FILE= "uploadRootCategoryFile/{id}";


    public static final String CREATE_SUB_CATEGORY= "createSubCategory";
    public static final String UPDATE_SUB_CATEGORY= "updateSubCategory";
    public static final String DELETE_SUB_CATEGORY_BY_ID= "deleteSubCategoryById/{subCategoryId}";
    public static final String GET_ALL_SUB_CATEGORY= "getAllSubCategory";
    public static final String GET_SUB_CATEGORY_BY_ID= "getSubCategoryById/{subCategoryId}";
    public static final String GET_SUB_CATEGORY_BY_ROOT_CATEGORY_ID= "getSubCategoriesByRootCategoryId/{rootCategoryId}";
    public static final String UPLOAD_SUB_CATEGORY_FILE= "uploadSubCategoryFile/{id}";



    public static final String CREATE_BOTTOM_CATEGORY= "createBottomCategory";
    public static final String UPDATE_BOTTOM_CATEGORY= "updateBottomCategory";
    public static final String GET_ALL_BOTTOM_CATEGORY= "getAllBottomCategory";
    public static final String DELETE_BOTTOM_CATEGORY_BY_ID= "deleteBottomCategoryById/{bottomCategoryId}";
    public static final String GET_BOTTOM_CATEGORY_BY_ID= "getBottomCategoryById/{bottomCategoryId}";
    public static final String GET_BOTTOM_CATEGORY_BY_SUB_CATEGORY_ID= "getBottomCategoriesBySubCategoryId/{subCategoryId}";
    public static final String GET_BOTTOM_CATEGORY_BY_SUB_CATEGORY_ID_RC= "getBottomCategoriesBySubCategoryIdRc/{subCategoryId}";
    public static final String UPLOAD_BOTTOM_CATEGORY_FILE= "uploadBottomCategoryFile/{id}";


    public static final String CREATE_BRANCH= "createBranch";
    public static final String UPDATE_BRANCH= "updateBranch";
    public static final String GET_BRANCH_BY_ID= "getBranchById/{branchId}";
    public static final String DELETE_BRANCH_BY_ID= "deleteBranchById/{branchId}";
    public static final String GET_ALL_BRANCH= "getAllBranch";
    public static final String GET_BRANCH_LIST_BY_BOTTOM_CATEGORY_ID= "getBranchListByBottomCategoryId/{bottomCategoryId}";
    public static final String UPLOAD_BRANCH_CATEGORY_FILE= "uploadBranchCategoryFile/{id}";


    public static final String CREATE_CHAPTER = "createChapter";
    public static final String UPDATE_CHAPTER = "updateChapter";
    public static final String GET_ALL_CHAPTERS= "getAllChapters";
    public static final String DELETE_CHAPTER_BY_ID= "deleteChapterById/{chapterId}";
    public static final String GET_CHAPTER_BY_ID= "getChapterById/{chapterId}";
    public static final String GET_CHAPTERS_BY_BRANCH_ID= "getChaptersByBranchId/{branchId}";
    public static final String UPLOAD_CHAPTER_FILE= "uploadChapterFile/{id}";



    public static final String SAVE_QUESTION_SET= "saveQuestionSet";
    public static final String UPDATE_QUESTION_SET= "updateQuestionSet";
    public static final String DELETE_QUESTION_SET_BY_ID= "deleteQuestionSetById/{questionSetId}";
    public static final String GET_ALL_QUESTION_SET= "getAllQuestionSet";
    public static final String GET_QUESTION_SET_BY_ID= "getQuestionSetById/{questionSetId}";
    public static final String GET_QUESTION_SET_BY_CHAPTER_ID= "getQuestionSetByChapterId/{chapterId}";
    public static final String UPLOAD_QUESTION_SET_FILE= "uploadQuestionSetFile/{id}";



    public static final String SAVE_QUESTION_ANSWER= "saveQuestionAnswer";
    public static final String UPDATE_QUESTION_ANSWER= "updateQuestionAnswer";
    public static final String DELETE_QUESTION_ANSWER_BY_ID= "deleteQuestionAnswerById/{id}";
    public static final String GET_ALL_QUESTION_ANSWER= "getAllQuestionAnswer";
    public static final String GET_QUESTION_LIST_PAGINATION= "getQuestionListPagination/{pageNo}/{pageSize}";
    public static final String GET_QUESTION_ANSWER_BY_ID= "getQuestionAnswerById/{id}";
    public static final String GET_QUESTION_ANSWER_BY_QUESTION_SET_ID= "getQuestionAnswerByQuestionSetId/{id}";


    public static final String UPLOAD_QUESTION_FILES= "uploadQuestionFiles/{questionAnswerId}";
    public static final String GET_QUESTION_FILES_BY_QUESTION_ANSWER_ID= "getQuestionsFilesByQuestionAnswerId/{questionAnswerId}";
    public static final String DELETE_QUESTION_FILE_BY_ID= "deleteQuestionFileById/{id}";
    public static final String UPDATE_QUESTION_FILE= "updateQuestionFile/{id}";


    public static final String SAVE_FILE_RESOURCE= "saveFileResource";
    public static final String GET_ALL_FILE_RESOURCE= "getAllFileResource";




}
