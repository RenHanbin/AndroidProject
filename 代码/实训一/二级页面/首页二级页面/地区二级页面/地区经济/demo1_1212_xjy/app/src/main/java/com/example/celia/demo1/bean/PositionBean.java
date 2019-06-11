package com.example.celia.demo1.bean;

public class PositionBean {
	private int positionId;
	private String positionName;

	public PositionBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PositionBean(int positionId, String positionName) {
		super();
		this.positionId = positionId;
		this.positionName = positionName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + positionId;
		result = prime * result + ((positionName == null) ? 0 : positionName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PositionBean other = (PositionBean) obj;
		if (positionId != other.positionId)
			return false;
		if (positionName == null) {
			if (other.positionName != null)
				return false;
		} else if (!positionName.equals(other.positionName))
			return false;
		return true;
	}
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	@Override
	public String toString() {
		return "[positionId=" + positionId + ", positionName=" + positionName + ", getPositionId()="
				+ getPositionId() + ", getPositionName()=" + getPositionName() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
	

