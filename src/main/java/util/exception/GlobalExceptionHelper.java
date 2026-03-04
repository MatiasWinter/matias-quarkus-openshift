package util.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.enums.ErrorCode;

@Provider
public class GlobalExceptionHelper implements ExceptionMapper<Exception> {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHelper.class);

    @Override
    public Response toResponse(Exception exception) {

        log.error("Ocurrió un error inesperado: {}", exception.getMessage(), exception);

        ErrorResponse error = new ErrorResponse(
                exception.getMessage(),
                ErrorCode.RUNTIME_ERROR.getCode()
        );

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
    }
}