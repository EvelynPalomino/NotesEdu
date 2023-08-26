package Modelos;

public class ModeloCourse {

	private Integer id_course;
	private String name_course;
	private Integer grade_id;
	private String state_course;
	
	public ModeloCourse() {
		// TODO Auto-generated constructor stub
	}

	
	public ModeloCourse(Integer id_course, String name_course, Integer grade_id, String state_course) {
		super();
		this.id_course = id_course;
		this.name_course = name_course;
		this.grade_id = grade_id;
		this.state_course = state_course;
	}

	public ModeloCourse(String name_course, Integer grade_id, String state_course) {
		super();
		this.name_course = name_course;
		this.grade_id = grade_id;
		this.state_course = state_course;
	}
	
	public ModeloCourse(Integer id_course, String name_course, Integer grade_id) {
		super();
		this.id_course = id_course;
		this.name_course = name_course;
		this.grade_id = grade_id;
	}
	
	public ModeloCourse(String name_course, Integer grade_id) {
		super();
		this.name_course = name_course;
		this.grade_id = grade_id;
	}


	public Integer getId_course() {
		return id_course;
	}




	public void setId_course(Integer id_course) {
		this.id_course = id_course;
	}




	public String getName_course() {
		return name_course;
	}




	public void setName_course(String name_course) {
		this.name_course = name_course;
	}




	public Integer getGrade_id() {
		return grade_id;
	}




	public void setGrade_id(Integer grade_id) {
		this.grade_id = grade_id;
	}




	public String getState_course() {
		return state_course;
	}




	public void setState_course(String state_course) {
		this.state_course = state_course;
	}




	@Override
	public String toString() {
		String data = "[" + this.id_course;
		data += ", " + this.name_course;
		data += ", " + this.grade_id;
		data += ", " + this.state_course +"]";;
		return data;
	}




}
