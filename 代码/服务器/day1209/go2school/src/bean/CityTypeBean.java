package bean;

public class CityTypeBean {
	private int citytypeId;
	private String citytypeName;
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
	

}
