package net.onest.go2school.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.onest.go2school.entity.Article;
import net.onest.go2school.service.impl.ArticleServiceImpl;

@Controller
@RequestMapping("article")
public class ArticleController {
	@Autowired
	private ArticleServiceImpl articleServiceImpl;

	
	//获得所有文章
	@RequestMapping(value = "getArticleList", produces = "application/json;charset=utf-8")
	public String getArticleList(Model model) {
		List<Article> articleList = articleServiceImpl.findAllArticles();
		model.addAttribute("articleList", articleList);
		model.addAttribute("remark", "getArticleList");
		System.out.println("ִ执行了findAllArticles");
		return "articles";
	}

	//通过关键字搜索文章
	@RequestMapping(value = "getLikeArticleList", produces = "application/json;charset=utf-8")
	public String getLikeArticleList(@RequestParam("searchstr") String str, Model model, HttpSession session) {
		List<Article> articleListLike = articleServiceImpl.findArticleByLike(str);
		session.setAttribute("search", str);
		model.addAttribute("articleListLike", articleListLike);
		model.addAttribute("remark", "getLikeArticleList");
		System.out.println("ִ执行了findArticleByLike" + articleListLike);
		return "articles";
	}

	@RequestMapping(value = "deleteArticle", produces = "application/json;charset=utf-8")
	public String deleteArticle(@RequestParam("articleId") int articleId, @RequestParam("remark1") String remark1,
			HttpSession session, Model model) {
		articleServiceImpl.deleteArticle(articleId);
		if ("all".equals(remark1)) {
			List<Article> articleList = articleServiceImpl.findAllArticles();
			model.addAttribute("articleList", articleList);
			model.addAttribute("remark", "getArticleList");
			System.out.println("ִ执行了findAllArticles");
		} else if ("like".equals(remark1)) {
			String str = (String) session.getAttribute("search");
			System.out.println("searchsearchsearchsearch" + str);
			List<Article> articleListLike = articleServiceImpl.findArticleByLike(str);
			model.addAttribute("articleListLike", articleListLike);
			model.addAttribute("remark", "getLikeArticleList");
			System.out.println("ִ执行了findArticleByLike" + articleListLike);
		}
		return "articles";
	}
}
