package com.example.exammngapi.validations;

import com.example.exammngapi.dto.StudentRegisterDTO;
import com.example.exammngapi.dto.TeacherRegistrationDTO;
import com.example.exammngapi.responses.MethodResponse;
import com.example.exammngapi.utils.ResponseCodes;
import com.example.exammngapi.utils.ResponseMessages;

public class TeacherValidations {

    public static MethodResponse teacherRegistrationValidation(TeacherRegistrationDTO teacherRegistrationDTO){
        MethodResponse methodResponse = new MethodResponse();

        if(teacherRegistrationDTO.getFirstName().isEmpty()){
            methodResponse.setCode(ResponseCodes.RES_TEACHER_FIRST_NAME_IS_EMPTY);
            methodResponse.setMessage(ResponseMessages.RES_TEACHER_FIRST_NAME_IS_EMPTY);
        }

        else if(teacherRegistrationDTO.getLastName().isEmpty()){
            methodResponse.setCode(ResponseCodes.RES_TEACHER_LAST_NAME_IS_EMPTY);
            methodResponse.setMessage(ResponseMessages.RES_TEACHER_LAST_NAME_IS_EMPTY);
        }

        else if(teacherRegistrationDTO.getEmail().isEmpty()){
            methodResponse.setCode(ResponseCodes.RES_TEACHER_EMAIL_IS_EMPTY);
            methodResponse.setMessage(ResponseMessages.RES_TEACHER_EMAIL_IS_EMPTY);
        }

        else if(!GeneralValidation.validateEmail(teacherRegistrationDTO.getEmail())){
            methodResponse.setCode(ResponseCodes.RES_TEACHER_EMAIL_IS_NOT_VALID);
            methodResponse.setMessage(ResponseMessages.RES_TEACHER_EMAIL_IS_NOT_VALID);
        }

        else if(teacherRegistrationDTO.getPassword().isEmpty()){
            methodResponse.setCode(ResponseCodes.RES_TEACHER_PASSWORD_IS_EMPTY);
            methodResponse.setMessage(ResponseMessages.RES_TEACHER_PASSWORD_IS_EMPTY);
        }

        else if(teacherRegistrationDTO.getConfirmPassword().isEmpty()){
            methodResponse.setCode(ResponseCodes.RES_TEACHER_CONFIRM_PASSWORD_IS_EMPTY);
            methodResponse.setMessage(ResponseMessages.RES_TEACHER_CONFIRM_PASSWORD_IS_EMPTY);
        }

        else if(!teacherRegistrationDTO.getPassword().equals(teacherRegistrationDTO.getConfirmPassword())){
            methodResponse.setCode(ResponseCodes.RES_TEACHER_PASSWORD_AND_CONFIRM_PASSWORD_NOT_MATCH);
            methodResponse.setMessage(ResponseMessages.RES_TEACHER_PASSWORD_AND_CONFIRM_PASSWORD_NOT_MATCH);
        }

        else if(teacherRegistrationDTO.getProfileURL().isEmpty()){
            methodResponse.setCode(ResponseCodes.RES_TEACHER_PROFILE_URL_IS_EMPTY);
            methodResponse.setMessage(ResponseMessages.RES_TEACHER_PROFILE_URL_IS_EMPTY);
        }

        else{
            methodResponse.setCode(ResponseCodes.RES_SUCCESS);
            methodResponse.setMessage(ResponseMessages.RES_SUCCESS);
        }
        return  methodResponse;
    }
}
