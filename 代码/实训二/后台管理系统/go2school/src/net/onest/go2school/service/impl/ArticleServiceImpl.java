package net.onest.go2school.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.onest.go2school.dao.ArticleMapper;
import net.onest.go2school.entity.Article;
import net.onest.go2school.service.ArticleService;


@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	public ArticleMapper articleMapper;
	
	// 查询所有文章
	public List<Article> findAllArticles(){
		return articleMapper.findAllArticles();
	}

	// 模糊查询
	public List<Article> findArticleByLike(String str){
		return articleMapper.findArticleByLike(str);
	}

	// 删除文章
	public int deleteArticle(int articleId) {
		return articleMapper.deleteArticle(articleId);
	}

}
