package bean;



public class Article {

	private int articleId;
	private String articleTitle;
	private String articleContent;
	private String articleTime;
	private String articleImg;
	private WriterBean writer;
	public Article(int articleId, String articleTitle, String articleContent, String articleTime, String articleImg,
			WriterBean writer) {
		super();
		this.articleId = articleId;
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.articleTime = articleTime;
		this.articleImg = articleImg;
		this.writer = writer;
	}
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public String getArticleTime() {
		return articleTime;
	}
	public void setArticleTime(String articleTime) {
		this.articleTime = articleTime;
	}
	public String getArticleImg() {
		return articleImg;
	}
	public void setArticleImg(String articleImg) {
		this.articleImg = articleImg;
	}
	public WriterBean getWriter() {
		return writer;
	}
	public void setWriter(WriterBean writer) {
		this.writer = writer;
	}
	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", articleTitle=" + articleTitle + ", articleContent="
				+ articleContent + ", articleTime=" + articleTime + ", articleImg=" + articleImg + ", writer=" + writer
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articleContent == null) ? 0 : articleContent.hashCode());
		result = prime * result + articleId;
		result = prime * result + ((articleImg == null) ? 0 : articleImg.hashCode());
		result = prime * result + ((articleTime == null) ? 0 : articleTime.hashCode());
		result = prime * result + ((articleTitle == null) ? 0 : articleTitle.hashCode());
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
		Article other = (Article) obj;
		if (articleContent == null) {
			if (other.articleContent != null)
				return false;
		} else if (!articleContent.equals(other.articleContent))
			return false;
		if (articleId != other.articleId)
			return false;
		if (articleImg == null) {
			if (other.articleImg != null)
				return false;
		} else if (!articleImg.equals(other.articleImg))
			return false;
		if (articleTime == null) {
			if (other.articleTime != null)
				return false;
		} else if (!articleTime.equals(other.articleTime))
			return false;
		if (articleTitle == null) {
			if (other.articleTitle != null)
				return false;
		} else if (!articleTitle.equals(other.articleTitle))
			return false;
		if (writer == null) {
			if (other.writer != null)
				return false;
		} else if (!writer.equals(other.writer))
			return false;
		return true;
	}
	
}
