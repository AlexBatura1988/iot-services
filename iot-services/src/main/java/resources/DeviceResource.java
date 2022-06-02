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
import models.Device;
@Path("/device")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeviceResource {
	
	DBService dbService = new MockDBService();

    @GET
    @Path("/{id}")
    public Response getDevice(@PathParam("id") String id) {
        Device device = dbService.getDeviceByUUID(UUID.fromString(id));
        if (device != null) return Response.ok().entity(device).build();
        else return Response.noContent().build();
    }

    @GET
	public Response getAllDevices(@BeanParam IotThingsFilterBean iotThingsFilterBean) {
        List<Device> result;
        if (iotThingsFilterBean.getType() != null ||
            iotThingsFilterBean.getModel() != null ||
            iotThingsFilterBean.getManufacturer() != null) {
            result = dbService.getAllDevicesFiltered(
                    iotThingsFilterBean.getType(),
                    iotThingsFilterBean.getModel(),
                    iotThingsFilterBean.getManufacturer()
            );
        } else result = dbService.getAllDevices();
        return Response.ok().entity(result).build();
    }

}
