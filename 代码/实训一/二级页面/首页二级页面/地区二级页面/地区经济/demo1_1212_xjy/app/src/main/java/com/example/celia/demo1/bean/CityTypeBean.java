package com.example.celia.demo1.bean;

public class CityTypeBean {
	private int citytypeId;
	private String citytypeName;

	public CityTypeBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CityTypeBean(int citytypeId, String citytypeName) {
		super();
		this.citytypeId = citytypeId;
		this.citytypeName = citytypeName;
	}
	public int getCitytypeId() {
		return citytypeId;
	}
	public void setCitytypeId(int citytypeId) {
		this.citytypeId = citytypeId;
	}
	public String getCitytypeName() {
		return citytypeName;
	}
	public void setCitytypeName(String citytypeName) {
		this.citytypeName = citytypeName;
	}
	@Override
	public String toString() {
		return "CityTypeBean [citytypeId=" + citytypeId + ", citytypeName=" + citytypeName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + citytypeId;
		result = prime * result + ((citytypeName == null) ? 0 : citytypeName.hashCode());
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
		CityTypeBean other = (CityTypeBean) obj;
		if (citytypeId != other.citytypeId)
			return false;
		if (citytypeName == null) {
			if (other.citytypeName != null)
				return false;
		} else if (!citytypeName.equals(other.citytypeName))
			return false;
		return true;
	}
}
