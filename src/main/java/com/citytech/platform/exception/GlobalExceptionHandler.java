package com.citytech.platform.exception;

import com.citytech.platform.rest.RestResponse;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {Exception.class, ExceptionHandler.class})
public class GlobalExceptionHandler implements ExceptionHandler<Exception, MutableHttpResponse<RestResponse>> {

    @Override
    public MutableHttpResponse<RestResponse> handle(HttpRequest request, Exception exception) {
        String message = exception.getMessage();
        exception.printStackTrace();
        return HttpResponse.badRequest(RestResponse.error("400", message));
    }
}
