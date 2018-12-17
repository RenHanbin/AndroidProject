package bean;

public class WriterBean {
	private int writerId;
	private String writerName;
	private int writerImage;
	public WriterBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WriterBean(int writerId, String writerName, int writerImage) {
		super();
		this.writerId = writerId;
		this.writerName = writerName;
		this.writerImage = writerImage;
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
	public int getWriterImage() {
		return writerImage;
	}
	public void setWriterImage(int writerImage) {
		this.writerImage = writerImage;
	}
	@Override
	public String toString() {
		return "WriterBean [writerId=" + writerId + ", writerName=" + writerName + ", writerImage=" + writerImage + "]";
	}
	
}
