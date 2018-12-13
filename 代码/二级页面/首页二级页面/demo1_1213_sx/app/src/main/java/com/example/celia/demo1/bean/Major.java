package com.example.celia.demo1.bean;

public class Major {
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
        private String majorIntroduce;
        private String majorSubject;
        private String majorWork;

    public String getMajorIntroduce() {
        return majorIntroduce;
    }
    public void setMajorIntroduce(String majorIntroduce) {
        this.majorIntroduce = majorIntroduce;
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

        public Major() {
            super();
            // TODO Auto-generated constructor stub
        }
        @Override
        public String toString() {
            return "MajorBean [majorId=" + majorId + ", majorName=" + majorName + ", majorWorkPercent=" + majorWorkPercent
                    + ", majorSalary=" + majorSalary + ", majorTypeId=" + majorTypeId + ",majorTypeName"+majorTypeName+"]";
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
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + majorId;
            result = prime * result + ((majorName == null) ? 0 : majorName.hashCode());
            result = prime * result + majorSalary;
            result = prime * result + majorTypeId;
            result = prime * result + ((majorTypeName == null) ? 0 : majorTypeName.hashCode());
            long temp;
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
            Major other = (Major) obj;
            if (majorId != other.majorId)
                return false;
            if (majorName == null) {
                if (other.majorName != null)
                    return false;
            } else if (!majorName.equals(other.majorName))
                return false;
            if (majorSalary != other.majorSalary)
                return false;
            if (majorTypeId != other.majorTypeId)
                return false;
            if (majorTypeName == null) {
                if (other.majorTypeName != null)
                    return false;
            } else if (!majorTypeName.equals(other.majorTypeName))
                return false;
            if (Double.doubleToLongBits(majorWorkPercent) != Double.doubleToLongBits(other.majorWorkPercent))
                return false;
            return true;
        }


}
