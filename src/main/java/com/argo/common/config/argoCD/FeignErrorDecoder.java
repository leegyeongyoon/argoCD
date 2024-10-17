package com.argo.common.config.argoCD;

import com.argo.common.config.exception.ArgoCDException;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.codec.StringDecoder;
import org.apache.coyote.BadRequestException;

import java.io.IOException;


public class FeignErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();
    private final StringDecoder stringDecoder = new StringDecoder();

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                return new BadRequestException("Bad Request");
            case 404:
                return new ClassNotFoundException("Resource not found");
            case 500:
                try {
                    return new ArgoCDException.InternalServerErrorException(stringDecoder.decode(response, String.class).toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            default:
                return defaultErrorDecoder.decode(methodKey, response);
        }
    }

}
