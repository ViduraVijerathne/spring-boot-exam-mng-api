package com.example.exammngapi.validations;

import com.example.exammngapi.dto.SubjectDTO;
import com.example.exammngapi.responses.MethodResponse;
import com.example.exammngapi.utils.ResponseCodes;
import com.example.exammngapi.utils.ResponseMessages;

public class SubjectValidation {

    public static MethodResponse validate(SubjectDTO subject){
        MethodResponse methodResponse = new MethodResponse();
        if(subject.getSubjectName() == null || subject.getSubjectName().isEmpty()){
            methodResponse.setCode(ResponseCodes.RES_SUBJECT_NAME_EMPTY);
            methodResponse.setMessage(ResponseMessages.RES_SUBJECT_NAME_EMPTY);
            return methodResponse;
        }

        if(subject.getSubjectName().length() < 3){
            methodResponse.setCode(ResponseCodes.RES_SUBJECT_NAME_LENGTH);
            methodResponse.setMessage(ResponseMessages.RES_SUBJECT_NAME_LENGTH);
            return methodResponse;
        }
        if(subject.getSubjectName().length() > 50){
            methodResponse.setCode(ResponseCodes.RES_SUBJECT_NAME_LENGTH);
            methodResponse.setMessage(ResponseMessages.RES_SUBJECT_NAME_LENGTH);
            return methodResponse;
        }
        methodResponse.setCode(ResponseCodes.RES_SUCCESS);
        methodResponse.setMessage(ResponseMessages.RES_SUCCESS);
        return methodResponse;
    }
}
