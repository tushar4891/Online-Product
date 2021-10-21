package com.user.userservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        switch(response.status()){

            case 500:
            {
                if(methodKey.contains("getProductByTheirCategory"))
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()),
                                                             "Product is not found");
                break;
            }
        }
        return null;
    }
    
    
}
