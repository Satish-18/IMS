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
@Requires(classes = {ImsException.class, ExceptionHandler.class})
public class ImsExceptionHandler implements ExceptionHandler<ImsException, MutableHttpResponse<RestResponse>> {

    @Override
    public MutableHttpResponse<RestResponse> handle(HttpRequest request, ImsException exception) {
        String code = exception.getExceptionType().getCode();
        String message = exception.getExceptionType().getMessage();
        return HttpResponse.badRequest(RestResponse.error(code, message));
    }
}
