package com.example.exammngapi.utils;

import com.example.exammngapi.dto.AuthDTO;
import com.example.exammngapi.responses.MethodResponse;

import java.util.Base64;

public class AuthTokenConverter {

    public static AuthDTO convertToDto(String token){
//        MethodResponse methodResponse = new MethodResponse();
        AuthDTO authDTO = new AuthDTO();
        try{
            if (token != null && token.startsWith("Basic ")) {
                // Extract the base64-encoded credentials part (after "Basic ")
                String credentialsBase64 = token.substring("Basic ".length());

                // Decode the base64-encoded credentials
                String decodedCredentials = new String(Base64.getDecoder().decode(credentialsBase64));

                // Split the username and password (assuming they are separated by a colon)
                String[] credentialsArray = decodedCredentials.split(":");

                String username = credentialsArray[0];
                String password = credentialsArray[1];

                // Now you have the username and password, you can use them as needed

                authDTO.setEmail(username);
                authDTO.setPassword(password);
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
            } else {
                System.out.println("Username: " + "null");
                System.out.println("Password: " + "null");
                authDTO.setEmail("");
                authDTO.setPassword("");
            }

        }catch (Exception e){
            System.out.println("Username:  " + "null");
            System.out.println("Password: " + "null");
            System.out.println("Exception Handler: " + e.getMessage());
            authDTO.setEmail("");
            authDTO.setPassword("");
        }

        return authDTO;
    }
}
