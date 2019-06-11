package net.onest.go2school.entity;

public class Manager {
	
	private int managerId;
	private String managerName;
	private String managerPassword;
	private String managerTel;
	private String managerEmail;
	private String managerImg;
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPassword() {
		return managerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}
	public String getManagerTel() {
		return managerTel;
	}
	public void setManagerTel(String managerTel) {
		this.managerTel = managerTel;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public String getManagerImg() {
		return managerImg;
	}
	public void setManagerImg(String managerImg) {
		this.managerImg = managerImg;
	}
	public Manager(int managerId, String managerName, String managerPassword, String managerTel, String managerEmail,
			String managerImg) {
		super();
		this.managerId = managerId;
		this.managerName = managerName;
		this.managerPassword = managerPassword;
		this.managerTel = managerTel;
		this.managerEmail = managerEmail;
		this.managerImg = managerImg;
	}
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Manager [managerId=" + managerId + ", managerName=" + managerName + ", managerPassword="
				+ managerPassword + ", managerTel=" + managerTel + ", managerEmail=" + managerEmail + ", managerImg="
				+ managerImg + "]";
	}
	

}
