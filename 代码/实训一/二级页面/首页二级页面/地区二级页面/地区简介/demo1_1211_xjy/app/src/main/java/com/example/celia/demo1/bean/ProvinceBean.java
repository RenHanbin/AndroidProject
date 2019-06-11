package com.example.celia.demo1.bean;
public class ProvinceBean {
	private int provinceId;
	private String provinceName;
	private PositionBean position;

	public ProvinceBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProvinceBean(int provinceId, String provinceName, PositionBean position) {
		super();
		this.provinceId = provinceId;
		this.provinceName = provinceName;
		this.position = position;
	}
	public PositionBean getPosition() {
		return position;
	}
	public void setPosition(PositionBean position) {
		this.position = position;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	@Override
	public String toString() {
		return " [provinceId=" + provinceId + ", provinceName=" + provinceName + ", position=" + position
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + provinceId;
		result = prime * result + ((provinceName == null) ? 0 : provinceName.hashCode());
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
		ProvinceBean other = (ProvinceBean) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (provinceId != other.provinceId)
			return false;
		if (provinceName == null) {
			if (other.provinceName != null)
				return false;
		} else if (!provinceName.equals(other.provinceName))
			return false;
		return true;
	}

}

