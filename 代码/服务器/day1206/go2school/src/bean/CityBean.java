package bean;

public class CityBean {
	private int cityId;
	private String cityName;
	private double cityGdp;
	private double citySalary;
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public double getCityGdp() {
		return cityGdp;
	}
	public void setCityGdp(double cityGdp) {
		this.cityGdp = cityGdp;
	}
	public double getCitySalary() {
		return citySalary;
	}
	public void setCitySalary(double citySalary) {
		this.citySalary = citySalary;
	}
	@Override
	public String toString() {
		return "CityBean [cityId=" + cityId + ", cityName=" + cityName + ", cityGdp=" + cityGdp + ", citySalary="
				+ citySalary + "]";
	}
	
}
