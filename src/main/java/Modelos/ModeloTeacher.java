package Modelos;

public class ModeloTeacher {

	private Integer id_teacher;
	private String name_teacher;
	private String last_name;
	private String user_teacher;
	private String password_teacher;
	private String asset_teacher;
	
	public ModeloTeacher() {
		// TODO Auto-generated constructor stub
	}

	
	public ModeloTeacher(Integer id_teacher, String name_teacher, String last_name, String user_teacher,
			String password_teacher, String asset_teacher) {
		super();
		this.id_teacher = id_teacher;
		this.name_teacher = name_teacher;
		this.last_name = last_name;
		this.user_teacher = user_teacher;
		this.password_teacher = password_teacher;
		this.asset_teacher = asset_teacher;
	}


	public ModeloTeacher(String name_teacher, String last_name, String user_teacher,
			String password_teacher, String asset_teacher) {
		super();
		this.name_teacher = name_teacher;
		this.last_name = last_name;
		this.user_teacher = user_teacher;
		this.password_teacher = password_teacher;
		this.asset_teacher = asset_teacher;
	}

	public ModeloTeacher(Integer id_teacher, String name_teacher, String last_name, String user_teacher,
			String password_teacher) {
		super();
		this.id_teacher = id_teacher;
		this.name_teacher = name_teacher;
		this.last_name = last_name;
		this.user_teacher = user_teacher;
		this.password_teacher = password_teacher;
	}
	
	public ModeloTeacher(String name_teacher, String last_name, String user_teacher,
			String password_teacher) {
		super();
		this.name_teacher = name_teacher;
		this.last_name = last_name;
		this.user_teacher = user_teacher;
		this.password_teacher = password_teacher;
	}
	
	
	
	public Integer getId_teacher() {
		return id_teacher;
	}



	public void setId_teacher(Integer id_teacher) {
		this.id_teacher = id_teacher;
	}



	public String getName_teacher() {
		return name_teacher;
	}



	public void setName_teacher(String name_teacher) {
		this.name_teacher = name_teacher;
	}



	public String getLast_name() {
		return last_name;
	}



	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}



	public String getUser_teacher() {
		return user_teacher;
	}



	public void setUser_teacher(String user_teacher) {
		this.user_teacher = user_teacher;
	}



	public String getPassword_teacher() {
		return password_teacher;
	}



	public void setPassword_teacher(String password_teacher) {
		this.password_teacher = password_teacher;
	}



	public String getAsset_teacher() {
		return asset_teacher;
	}



	public void setAsset_teacher(String asset_teacher) {
		this.asset_teacher = asset_teacher;
	}


	@Override
	public String toString() {
		String data = "[" + this.id_teacher;
		data += ", " + this.name_teacher;
		data += ", " + this.last_name;
		data += ", " + this.user_teacher;
		data += ", " + this.password_teacher;
		data += ", " + this.asset_teacher +"]";;
		return data;
	}




}
