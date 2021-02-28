package cn.ybzy.model;

import java.util.Date;
/**
 * User Entity Class
 * @author fred0
 *
 */
public class User {

	private int id;
	private String userName;
	private String password;
	private String phoneNo;
	private String address;
	private Date regDate;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public User(int id, String userName, String password, String phoneNo, String address, Date regDate) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.phoneNo = phoneNo;
		this.address = address;
		this.regDate = regDate;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", phoneNo=" + phoneNo
				+ ", address=" + address + ", regDate=" + regDate + "]";
	}
	
	
}
