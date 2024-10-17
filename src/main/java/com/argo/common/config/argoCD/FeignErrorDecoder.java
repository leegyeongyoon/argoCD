package com.argo.common.config.argoCD;

import com.argo.common.config.exception.ArgoCDException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.coyote.BadRequestException;

import java.io.IOException;

public class FeignErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> new BadRequestException("Bad Request");
            case 404 -> new ClassNotFoundException("Resource not found");
            case 500 -> new ArgoCDException.InternalServerErrorException(response.toString());
            default -> defaultErrorDecoder.decode(methodKey, response);
        };
    }

}
