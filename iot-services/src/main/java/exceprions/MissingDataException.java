package exceprions;

public class MissingDataException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	private static String msg = "no element ";
	private String objType;
	private String nameId;
	

	public MissingDataException(String msg) {
		this.msg = msg;
	}
	
	public MissingDataException(String objType , String nameId) {
		super(msg + objType + " with ID " + nameId);
		this.objType = objType;
		this.nameId = nameId;
	}

	public String getObjType() {
		return objType;
	}

	public String getNameId() {
		return nameId;
	}
	
	
}
