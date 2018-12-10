package bean;

public class PositionBean {
	private int positionId;
	private String positionName;
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
		return "PositionBean [positionId=" + positionId + ", positionName=" + positionName + ", getPositionId()="
				+ getPositionId() + ", getPositionName()=" + getPositionName() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
	
	
	

}
