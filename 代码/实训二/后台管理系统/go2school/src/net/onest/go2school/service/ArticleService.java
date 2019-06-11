package net.onest.go2school.service;

import java.util.List;

import net.onest.go2school.entity.Article;

public interface ArticleService {

	public List<Article> findAllArticles();// 查询所有文章

	public List<Article> findArticleByLike(String str);// 模糊查询

	public int deleteArticle(int articleId);// 删除文章

}
