package Modelos;

public class ModeloGrade {

	private Integer id_grade;
	private String grade;
	private String section;
	private String academic_level;
	private String tutor;
	private String state_grade;
	
	public ModeloGrade() {
		// TODO Auto-generated constructor stub
	}

	
	public ModeloGrade(Integer id_grade, String grade, String section, String academic_level, String tutor,
			String state_grade) {
		super();
		this.id_grade = id_grade;
		this.grade = grade;
		this.section = section;
		this.academic_level = academic_level;
		this.tutor = tutor;
		this.state_grade = state_grade;
	}
	
	public ModeloGrade(String grade, String section, String academic_level, String tutor,
			String state_grade) {
		super();
		this.grade = grade;
		this.section = section;
		this.academic_level = academic_level;
		this.tutor = tutor;
		this.state_grade = state_grade;
	}

	
	public ModeloGrade(Integer id_grade, String grade, String section, String academic_level, String tutor) {
		super();
		this.id_grade = id_grade;
		this.grade = grade;
		this.section = section;
		this.academic_level = academic_level;
		this.tutor = tutor;
	}

	public ModeloGrade(String grade, String section, String academic_level, String tutor) {
		super();
		this.grade = grade;
		this.section = section;
		this.academic_level = academic_level;
		this.tutor = tutor;
	}
	
	
	public Integer getId_grade() {
		return id_grade;
	}


	public void setId_grade(Integer id_grade) {
		this.id_grade = id_grade;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getSection() {
		return section;
	}


	public void setSection(String section) {
		this.section = section;
	}


	public String getAcademic_level() {
		return academic_level;
	}


	public void setAcademic_level(String academic_level) {
		this.academic_level = academic_level;
	}


	public String getTutor() {
		return tutor;
	}


	public void setTutor(String tutor) {
		this.tutor = tutor;
	}


	public String getState_grade() {
		return state_grade;
	}


	public void setState_grade(String state_grade) {
		this.state_grade = state_grade;
	}


	@Override
	public String toString() {
		String data = "[" + this.id_grade;
		data += ", " + this.grade;
		data += ", " + this.section;
		data += ", " + this.academic_level;
		data += ", " + this.tutor;
		data += ", " + this.state_grade +"]";;
		return data;
	}




}
