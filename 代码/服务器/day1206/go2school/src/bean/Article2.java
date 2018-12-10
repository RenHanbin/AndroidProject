package bean;

public class Article2 {

	private int article2Id;
	private String article2Title;
	private String article2Content;
	private String article2Time;
	private String article2Img;
	private Writer writer;
	
	public Article2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Article2(int article2Id, String article2Title, String article2Content, String article2Time,
			String article2Img, Writer writer) {
		super();
		this.article2Id = article2Id;
		this.article2Title = article2Title;
		this.article2Content = article2Content;
		this.article2Time = article2Time;
		this.article2Img = article2Img;
		this.writer = writer;
	}
	public int getArticle2Id() {
		return article2Id;
	}
	public void setArticle2Id(int article2Id) {
		this.article2Id = article2Id;
	}
	public String getArticle2Title() {
		return article2Title;
	}
	public void setArticle2Title(String article2Title) {
		this.article2Title = article2Title;
	}
	public String getArticle2Content() {
		return article2Content;
	}
	public void setArticle2Content(String article2Content) {
		this.article2Content = article2Content;
	}
	public String getArticle2Time() {
		return article2Time;
	}
	public void setArticle2Time(String article2Time) {
		this.article2Time = article2Time;
	}
	public String getArticle2Img() {
		return article2Img;
	}
	public void setArticle2Img(String article2Img) {
		this.article2Img = article2Img;
	}
	public Writer getWriter() {
		return writer;
	}
	public void setWriter(Writer writer) {
		this.writer = writer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article2Content == null) ? 0 : article2Content.hashCode());
		result = prime * result + article2Id;
		result = prime * result + ((article2Img == null) ? 0 : article2Img.hashCode());
		result = prime * result + ((article2Time == null) ? 0 : article2Time.hashCode());
		result = prime * result + ((article2Title == null) ? 0 : article2Title.hashCode());
		result = prime * result + ((writer == null) ? 0 : writer.hashCode());
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
		Article2 other = (Article2) obj;
		if (article2Content == null) {
			if (other.article2Content != null)
				return false;
		} else if (!article2Content.equals(other.article2Content))
			return false;
		if (article2Id != other.article2Id)
			return false;
		if (article2Img == null) {
			if (other.article2Img != null)
				return false;
		} else if (!article2Img.equals(other.article2Img))
			return false;
		if (article2Time == null) {
			if (other.article2Time != null)
				return false;
		} else if (!article2Time.equals(other.article2Time))
			return false;
		if (article2Title == null) {
			if (other.article2Title != null)
				return false;
		} else if (!article2Title.equals(other.article2Title))
			return false;
		if (writer == null) {
			if (other.writer != null)
				return false;
		} else if (!writer.equals(other.writer))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Article2 [article2Id=" + article2Id + ", article2Title=" + article2Title + ", article2Content="
				+ article2Content + ", article2Time=" + article2Time + ", article2Img=" + article2Img + ", writer="
				+ writer + "]";
	}
	
}
