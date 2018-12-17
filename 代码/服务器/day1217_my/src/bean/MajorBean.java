package bean;

public class MajorBean {

	private int majorId;
	private String majorName;
	private double majorWorkPercent;
	private int majorSalary;
	private int majorTypeId;
	private String majorTypeName;
	private int majorWant;
    private int majorNeed;
    private double majorStudy;
    private double majorGo;
    private String majorInf;
    private String majorSubject;
    private String majorWork;
	
	public MajorBean(int majorId, String majorName, double majorWorkPercent, int majorSalary, int majorTypeId,
			String majorTypeName, int majorWant, int majorNeed, double majorStudy, double majorGo) {
		super();
		this.majorId = majorId;
		this.majorName = majorName;
		this.majorWorkPercent = majorWorkPercent;
		this.majorSalary = majorSalary;
		this.majorTypeId = majorTypeId;
		this.majorTypeName = majorTypeName;
		this.majorWant = majorWant;
		this.majorNeed = majorNeed;
		this.majorStudy = majorStudy;
		this.majorGo = majorGo;
	}
	public MajorBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "MajorBean [majorId=" + majorId + ", majorName=" + majorName + ", majorWorkPercent=" + majorWorkPercent
				+ ", majorSalary=" + majorSalary + ", majorTypeId=" + majorTypeId + ", majorTypeName=" + majorTypeName
				+ ", majorWant=" + majorWant + ", majorNeed=" + majorNeed + ", majorStudy=" + majorStudy + ", majorGo="
				+ majorGo + "]";
	}
	public int getMajorId() {
		return majorId;
	}
	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public double getMajorWorkPercent() {
		return majorWorkPercent;
	}
	public void setMajorWorkPercent(double majorWorkPercent) {
		this.majorWorkPercent = majorWorkPercent;
	}
	public int getMajorSalary() {
		return majorSalary;
	}
	public void setMajorSalary(int majorSalary) {
		this.majorSalary = majorSalary;
	}
	public int getMajorTypeId() {
		return majorTypeId;
	}
	public void setMajorTypeId(int majorTypeId) {
		this.majorTypeId = majorTypeId;
	}
	public String getMajorTypeName() {
		return majorTypeName;
	}
	public void setMajorTypeName(String majorTypeName) {
		this.majorTypeName = majorTypeName;
	}
	
	public int getMajorWant() {
		return majorWant;
	}
	public void setMajorWant(int majorWant) {
		this.majorWant = majorWant;
	}
	public int getMajorNeed() {
		return majorNeed;
	}
	public void setMajorNeed(int majorNeed) {
		this.majorNeed = majorNeed;
	}
	public double getMajorStudy() {
		return majorStudy;
	}
	public void setMajorStudy(double majorStudy) {
		this.majorStudy = majorStudy;
	}
	public double getMajorGo() {
		return majorGo;
	}
	public void setMajorGo(double majorGo) {
		this.majorGo = majorGo;
	}
	public String getMajorInf() {
		return majorInf;
	}
	public void setMajorInf(String majorInf) {
		this.majorInf = majorInf;
	}
	public String getMajorSubject() {
		return majorSubject;
	}
	public void setMajorSubject(String majorSubject) {
		this.majorSubject = majorSubject;
	}
	public String getMajorWork() {
		return majorWork;
	}
	public void setMajorWork(String majorWork) {
		this.majorWork = majorWork;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(majorGo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + majorId;
		result = prime * result + ((majorName == null) ? 0 : majorName.hashCode());
		result = prime * result + majorNeed;
		result = prime * result + majorSalary;
		temp = Double.doubleToLongBits(majorStudy);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + majorTypeId;
		result = prime * result + ((majorTypeName == null) ? 0 : majorTypeName.hashCode());
		result = prime * result + majorWant;
		temp = Double.doubleToLongBits(majorWorkPercent);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		MajorBean other = (MajorBean) obj;
		if (Double.doubleToLongBits(majorGo) != Double.doubleToLongBits(other.majorGo))
			return false;
		if (majorId != other.majorId)
			return false;
		if (majorName == null) {
			if (other.majorName != null)
				return false;
		} else if (!majorName.equals(other.majorName))
			return false;
		if (majorNeed != other.majorNeed)
			return false;
		if (majorSalary != other.majorSalary)
			return false;
		if (Double.doubleToLongBits(majorStudy) != Double.doubleToLongBits(other.majorStudy))
			return false;
		if (majorTypeId != other.majorTypeId)
			return false;
		if (majorTypeName == null) {
			if (other.majorTypeName != null)
				return false;
		} else if (!majorTypeName.equals(other.majorTypeName))
			return false;
		if (majorWant != other.majorWant)
			return false;
		if (Double.doubleToLongBits(majorWorkPercent) != Double.doubleToLongBits(other.majorWorkPercent))
			return false;
		return true;
	}
	
	
	
}
