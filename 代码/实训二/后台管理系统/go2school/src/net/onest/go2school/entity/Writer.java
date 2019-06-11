package net.onest.go2school.entity;

public class Writer {
	private int writerId;
	private String writerName;
	private String writerImg;
	
	public Writer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Writer(int writerId, String writerName, String writerImg) {
		super();
		this.writerId = writerId;
		this.writerName = writerName;
		this.writerImg = writerImg;
	}

	public int getWriterId() {
		return writerId;
	}

	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getWriterImg() {
		return writerImg;
	}

	public void setWriterImg(String writerImg) {
		this.writerImg = writerImg;
	}

	@Override
	public String toString() {
		return "Writer [writerId=" + writerId + ", writerName=" + writerName + ", writerImg=" + writerImg + "]";
	}
	
}
