package com.exam.secure.publicAllowanceController.urlMappings;

public class UrlMappingPublic {

    public static final String API_PUBLIC_AUTH_CRED= "api/public/auth/cred/";

    public static final String PUBLIC_SIGN_UP= "publicSignUp";
    public static final String PUBLIC_SIGN_IN= "publicSignIn";


    public static final String GET_BRANCH_CATEGORY_PUBLIC= "getBranchCategoryPublic";


    public static final String GET_SUB_CATEGORY_PUBLIC= "getSubCategoryPublic";


    public static final String GET_CHAPTERS_BY_BRANCH_CATEGORY_ID= "getChaptersByBranchCategoryId/{branchId}";

    public static final String GET_BRANCH_LIST_BY_BOTTOM_CATEGORY_ID_PUBLIC= "getBranchListByBottomCategoryIdPublic/{bottomCategoryId}";

    public static final String GET_BRANCH_LIST_BY_SUB_ID= "getBranchListBySubId/{subId}";

    public static final String GET_QUESTION_ANSWER_LIST_BY_QUESTION_SET_ID_PUBLIC_RC  = "getQuestionAnswerListByQuestionSetIdPublic_RC/{id}";

    public static final String SUBMIT_QUIZ = "submitQuiz/{timeDuration}";

    public static final String SEND_REPORT_TO_EMAIL = "sendReportToEmail/{timeDuration}/{email}";

    public static final String GENERATE_QUIZ_RESULT_PDF = "generateQuizResultPdf/{timeDuration}";

    public static final String GET_ROOT_CATEGORY_LIST_PUBLIC= "getRootCategoryListPublic";

    public static final String GET_SUB_CATEGORY_List_BY_ROOT_CATEGORY_ID_PUBLIC= "getSubCategoryListByRootCategoryIdPublic/{id}";


}
