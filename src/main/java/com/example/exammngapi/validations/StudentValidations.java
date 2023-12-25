package com.example.exammngapi.validations;

import com.example.exammngapi.dto.AuthDTO;
import com.example.exammngapi.dto.StudentDTO;
import com.example.exammngapi.dto.StudentRegisterDTO;
import com.example.exammngapi.responses.MethodResponse;
import com.example.exammngapi.utils.ResponseCodes;
import com.example.exammngapi.utils.ResponseMessages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.exammngapi.validations.GeneralValidation.validateEmail;

public class StudentValidations {



    public static MethodResponse studentRegistrationValidation(StudentRegisterDTO studentRegisterDTO){
        MethodResponse methodResponse = new MethodResponse();

        if(studentRegisterDTO.getFirstName().isEmpty()){
            methodResponse.setCode(ResponseCodes.RES_STUDENT_FIRST_NAME_IS_EMPTY);
            methodResponse.setMessage(ResponseMessages.RES_STUDENT_FIRST_NAME_IS_EMPTY);
        }

        else if(studentRegisterDTO.getLastName().isEmpty()){
            methodResponse.setCode(ResponseCodes.RES_STUDENT_LAST_NAME_IS_EMPTY);
            methodResponse.setMessage(ResponseMessages.RES_STUDENT_LAST_NAME_IS_EMPTY);
        }

        else if(studentRegisterDTO.getEmail().isEmpty()){
            methodResponse.setCode(ResponseCodes.RES_STUDENT_EMAIL_IS_EMPTY);
            methodResponse.setMessage(ResponseMessages.RES_STUDENT_EMAIL_IS_EMPTY);
        }

        else if(!validateEmail(studentRegisterDTO.getEmail())){
            methodResponse.setCode(ResponseCodes.RES_STUDENT_EMAIL_IS_NOT_VALID);
            methodResponse.setMessage(ResponseMessages.RES_STUDENT_EMAIL_IS_NOT_VALID);
        }

        else if(studentRegisterDTO.getPassword().isEmpty()){
            methodResponse.setCode(ResponseCodes.RES_STUDENT_PASSWORD_IS_EMPTY);
            methodResponse.setMessage(ResponseMessages.RES_STUDENT_PASSWORD_IS_EMPTY);
        }

        else if(studentRegisterDTO.getConfirmPassword().isEmpty()){
            methodResponse.setCode(ResponseCodes.RES_STUDENT_CONFIRM_PASSWORD_IS_EMPTY);
            methodResponse.setMessage(ResponseMessages.RES_STUDENT_CONFIRM_PASSWORD_IS_EMPTY);
        }

        else if(!studentRegisterDTO.getPassword().equals(studentRegisterDTO.getConfirmPassword())){
            methodResponse.setCode(ResponseCodes.RES_STUDENT_PASSWORD_AND_CONFIRM_PASSWORD_NOT_MATCH);
            methodResponse.setMessage(ResponseMessages.RES_STUDENT_PASSWORD_AND_CONFIRM_PASSWORD_NOT_MATCH);

        }

        else if(studentRegisterDTO.getProfileURL().isEmpty()){
            methodResponse.setCode(ResponseCodes.RES_STUDENT_PROFILE_URL_IS_EMPTY);
            methodResponse.setMessage(ResponseMessages.RES_STUDENT_PROFILE_URL_IS_EMPTY);
        }

        else{
            methodResponse.setCode(ResponseCodes.RES_SUCCESS);
            methodResponse.setMessage(ResponseMessages.RES_SUCCESS);
        }

        return  methodResponse;
    }

    public static MethodResponse studentAuthDTOValidation(AuthDTO authDTO) {
        MethodResponse methodResponse = new MethodResponse();

        if (authDTO.getEmail().isEmpty()) {
            methodResponse.setCode(ResponseCodes.RES_STUDENT_EMAIL_IS_EMPTY);
            methodResponse.setMessage(ResponseMessages.RES_STUDENT_EMAIL_IS_EMPTY);
        } else if (!validateEmail(authDTO.getEmail())) {
            methodResponse.setCode(ResponseCodes.RES_STUDENT_EMAIL_IS_NOT_VALID);
            methodResponse.setMessage(ResponseMessages.RES_STUDENT_EMAIL_IS_NOT_VALID);
        } else if (authDTO.getPassword().isEmpty()) {
            methodResponse.setCode(ResponseCodes.RES_STUDENT_PASSWORD_IS_EMPTY);
            methodResponse.setMessage(ResponseMessages.RES_STUDENT_PASSWORD_IS_EMPTY);
        } else {
            methodResponse.setCode(ResponseCodes.RES_SUCCESS);
            methodResponse.setMessage(ResponseMessages.RES_SUCCESS);
        }

        return methodResponse;
    }
}
