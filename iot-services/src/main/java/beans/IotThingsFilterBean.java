package beans;

import jakarta.ws.rs.QueryParam;

public class IotThingsFilterBean {
	@QueryParam("type") String type;
    @QueryParam("model") String model;
    @QueryParam("manufacturer")String manufacturer;
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

}
