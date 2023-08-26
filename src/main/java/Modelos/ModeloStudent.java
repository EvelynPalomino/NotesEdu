package Modelos;

public class ModeloStudent {

	private Integer id_student;
	private String names_student;
	private String last_namestudent;
	private String type_document;
	private String number_document;
	private String asset_student;
	private Integer grade_id;
	
	public ModeloStudent() {
		// TODO Auto-generated constructor stub
	}

	public ModeloStudent(Integer id_student, String names_student, String last_namestudent, String type_document,
			String number_document, String asset_student, Integer grade_id) {
		super();
		this.id_student = id_student;
		this.names_student = names_student;
		this.last_namestudent = last_namestudent;
		this.type_document = type_document;
		this.number_document = number_document;
		this.asset_student = asset_student;
		this.grade_id = grade_id;
	}

	public ModeloStudent(String names_student, String last_namestudent, String type_document,
			String number_document, String asset_student, Integer grade_id) {
		super();
		this.names_student = names_student;
		this.last_namestudent = last_namestudent;
		this.type_document = type_document;
		this.number_document = number_document;
		this.asset_student = asset_student;
		this.grade_id = grade_id;
	}

	public ModeloStudent(Integer id_student, String names_student, String last_namestudent, String type_document,
			String number_document, Integer grade_id) {
		super();
		this.id_student = id_student;
		this.names_student = names_student;
		this.last_namestudent = last_namestudent;
		this.type_document = type_document;
		this.number_document = number_document;
		this.grade_id = grade_id;
	}
	
	public ModeloStudent(String names_student, String last_namestudent, String type_document,
			String number_document, Integer grade_id) {
		super();
		
		this.names_student = names_student;
		this.last_namestudent = last_namestudent;
		this.type_document = type_document;
		this.number_document = number_document;
		this.grade_id = grade_id;
	}

	public Integer getId_student() {
		return id_student;
	}



	public void setId_student(Integer id_student) {
		this.id_student = id_student;
	}



	public String getNames_student() {
		return names_student;
	}



	public void setNames_student(String names_student) {
		this.names_student = names_student;
	}



	public String getLast_namestudent() {
		return last_namestudent;
	}



	public void setLast_namestudent(String last_namestudent) {
		this.last_namestudent = last_namestudent;
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




	public String getAsset_student() {
		return asset_student;
	}



	public void setAsset_student(String asset_student) {
		this.asset_student = asset_student;
	}



	public Integer getGrade_id() {
		return grade_id;
	}



	public void setGrade_id(Integer grade_id) {
		this.grade_id = grade_id;
	}

	@Override
	public String toString() {
		String data = "[" + this.id_student;
		data += ", " + this.names_student;
		data += ", " + this.last_namestudent;
		data += ", " + this.type_document;
		data += ", " + this.number_document;
		data += ", " + this.asset_student;
		data += ", " + this.grade_id +"]";;
		return data;
	}




}
