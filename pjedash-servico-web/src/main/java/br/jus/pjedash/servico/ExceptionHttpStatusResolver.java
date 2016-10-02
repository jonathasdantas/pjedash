package br.jus.pjedash.servico;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHttpStatusResolver implements ExceptionMapper<Throwable> {
 
    @Override
    public Response toResponse(Throwable exception) {
        Response.Status httpStatus = Response.Status.INTERNAL_SERVER_ERROR;
        return Response.status(httpStatus).entity(exception.getMessage()).type("text/plain").build();
    }
}