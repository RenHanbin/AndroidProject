package bean;

public class CityTypeBean {
	private int cityTypeId;
	private String cityTypeName;
	
	public CityTypeBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CityTypeBean(int citytypeId, String citytypeName) {
		super();
		this.cityTypeId = citytypeId;
		this.cityTypeName = citytypeName;
	}
	
	public int getCityTypeId() {
		return cityTypeId;
	}
	public void setCityTypeId(int cityTypeId) {
		this.cityTypeId = cityTypeId;
	}
	public String getCityTypeName() {
		return cityTypeName;
	}
	public void setCityTypeName(String cityTypeName) {
		this.cityTypeName = cityTypeName;
	}
	@Override
    public String toString() {
        return "{" +
                "cityTypeId=" + cityTypeId +
                ", cityTypeName='" + cityTypeName + '\'' +
                '}';
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cityTypeId;
		result = prime * result + ((cityTypeName == null) ? 0 : cityTypeName.hashCode());
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
		if (cityTypeId != other.cityTypeId)
			return false;
		if (cityTypeName == null) {
			if (other.cityTypeName != null)
				return false;
		} else if (!cityTypeName.equals(other.cityTypeName))
			return false;
		return true;
	}
	
	

}
