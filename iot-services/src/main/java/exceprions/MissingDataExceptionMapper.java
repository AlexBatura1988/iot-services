package exceprions;



import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import models.ErrorMessage;

//@Provider
//public class MissingDataExceptionMapper implements ExceptionMapper<MissingDataException> {
//
//	@Override
//	public Response toResponse(MissingDataException e) {
//		ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), Status.NOT_FOUND);
//		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
//	}
//
//	
//}
