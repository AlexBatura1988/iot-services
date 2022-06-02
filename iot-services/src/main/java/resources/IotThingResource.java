package resources;

import java.util.List;
import java.util.UUID;

import beans.IotThingsFilterBean;
import dbService.DBService;
import dbService.MockDBService;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.IOTThing;

@Path("/iot-thing")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IotThingResource {

	DBService dbService = new MockDBService();

	@GET
	public Response getAllIotThings(@BeanParam IotThingsFilterBean iotThingsFilterBean) {
		List<IOTThing> result;
		if (iotThingsFilterBean.getType() != null || iotThingsFilterBean.getModel() != null
				|| iotThingsFilterBean.getManufacturer() != null) {
			result = dbService.getAllIotThingsFiltered(iotThingsFilterBean.getType(), iotThingsFilterBean.getModel(),
					iotThingsFilterBean.getManufacturer());
		} else
			result = dbService.getAllIotThings();
		return Response.ok().entity(result).build();
	}

	@GET
	@Path("/{id}")
	public Response getIotThing(@PathParam("id") String id) {
		IOTThing iotThing = dbService.getIotThingByUUID(UUID.fromString(id));
		return Response.ok().entity(iotThing).build();
	}

}
