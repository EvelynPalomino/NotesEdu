package Modelos;

public class ModeloUsers {

	private Integer id;
	private String names, last_name, type_document, number_document, type_user, email, cellphone, active;

	public ModeloUsers() {
		// TODO Auto-generated constructor stub
	}

	public ModeloUsers(Integer id, String names, String last_name, String type_document, String number_document,
			String type_user, String email, String cellphone, String active) {
		super();
		this.id = id;
		this.names = names;
		this.last_name = last_name;
		this.type_document = type_document;
		this.number_document = number_document;
		this.type_user = type_user;
		this.email = email;
		this.cellphone = cellphone;
		this.active = active;
	}

	public ModeloUsers(String names, String last_name, String type_document, String number_document, String type_user,
			String email, String cellphone, String active) {
		super();
		this.names = names;
		this.last_name = last_name;
		this.type_document = type_document;
		this.number_document = number_document;
		this.type_user = type_user;
		this.email = email;
		this.cellphone = cellphone;
		this.active = active;
	}

	public ModeloUsers(Integer id, String names, String last_name, String type_document, String number_document,
			String type_user, String email, String cellphone) {
		super();
		this.id = id;
		this.names = names;
		this.last_name = last_name;
		this.type_document = type_document;
		this.number_document = number_document;
		this.type_user = type_user;
		this.email = email;
		this.cellphone = cellphone;

	}

	public ModeloUsers(String names, String last_name, String type_document, String number_document, String type_user,
			String email, String cellphone) {
		super();
		this.names = names;
		this.last_name = last_name;
		this.type_document = type_document;
		this.number_document = number_document;
		this.type_user = type_user;
		this.email = email;
		this.cellphone = cellphone;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getType_document() {
		return type_document;
	}

	public void setType_document(String type_document) {
		this.type_document = type_document;
	}

	public String getNumber_document() {
		return number_document;
	}

	public void setNumber_document(String number_document) {
		this.number_document = number_document;
	}

	public String getType_user() {
		return type_user;
	}

	public void setType_user(String type_user) {
		this.type_user = type_user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		String data = "[" + this.id;
		data += ", " + this.names;
		data += ", " + this.last_name;
		data += ", " + this.type_document;
		data += ", " + this.number_document;
		data += ", " + this.type_user;
		data += ", " + this.email;
		data += ", " + this.cellphone;
		data += ", " + this.active + "]";
		return data;
	}
}
