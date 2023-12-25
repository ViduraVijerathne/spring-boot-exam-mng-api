package com.example.exammngapi.utils;

public class ResponseCodes {
    public static  final int RES_SUCCESS = 0;
    public static  final int RES_USER_ALREADY_EXIST= 1;

    public static final int RES_STUDENT_FIRST_NAME_IS_EMPTY = 2;
    public static final int RES_STUDENT_LAST_NAME_IS_EMPTY = 3;
    public static final int RES_STUDENT_EMAIL_IS_EMPTY = 4;
    public static final int RES_STUDENT_EMAIL_IS_NOT_VALID = 5;
    public static final int RES_STUDENT_PASSWORD_IS_EMPTY = 6;
    public static final int RES_STUDENT_CONFIRM_PASSWORD_IS_EMPTY = 7;
    public static final int RES_STUDENT_PASSWORD_AND_CONFIRM_PASSWORD_NOT_MATCH = 8;
    public static final int RES_STUDENT_PROFILE_URL_IS_EMPTY = 9;


    public static final int RES_PASSWORD_NOT_MATCH = 10 ;
    public static final int RES_USER_NOT_FOUND = 11;
    public static final int RES_TEACHER_FIRST_NAME_IS_EMPTY =12 ;
    public static final int RES_TEACHER_LAST_NAME_IS_EMPTY = 13 ;
    public static final int RES_TEACHER_EMAIL_IS_EMPTY = 14 ;

    public static final int RES_TEACHER_EMAIL_IS_NOT_VALID = 15 ;

    public static final int RES_TEACHER_PASSWORD_IS_EMPTY = 16 ;
    public static final int RES_TEACHER_CONFIRM_PASSWORD_IS_EMPTY = 17 ;
    public static final int RES_TEACHER_PASSWORD_AND_CONFIRM_PASSWORD_NOT_MATCH = 18 ;

    public static final int RES_TEACHER_PROFILE_URL_IS_EMPTY = 19 ;
    public static final int RES_SUBJECT_NOT_FOUND = 20 ;
    public static final int RES_INVALID_PASSWORD = 21 ;
    public static final int RES_NO_ACCESS_TO_REQUEST_PAVILAGE = 22 ;
    public static final int RES_UNAUTHORIZED = 23;
    public static final int RES_SUBJECT_NAME_EMPTY = 24;
    public static final int RES_SUBJECT_NAME_LENGTH = 25;
    public static final int RES_SUBJECT_ALREADY_EXIST = 26;
}
