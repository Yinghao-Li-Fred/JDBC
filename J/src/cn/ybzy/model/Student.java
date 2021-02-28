package cn.ybzy.model;

public class Student {

	private int id;
	private String sname;
	private int ageId;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSname() {
		return sname;
	}
	
	public void setSname(String sname) {
		this.sname = sname;
	}
	
	public int getAgeId() {
		return ageId;
	}
	
	public void setAgeId(int ageId) {
		this.ageId = ageId;
	}

	public Student(int id, String sname, int ageId) {
		super();
		this.id = id;
		this.sname = sname;
		this.ageId = ageId;
	}

	public Student() {
		super();
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", sname=" + sname + ", ageId=" + ageId + "]";
	}
	
}
