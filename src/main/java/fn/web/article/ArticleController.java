/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package fn.web.article;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import fn.entity.Article;
import fn.entity.User;
import fn.service.account.ShiroDbRealm.ShiroUser;
import fn.service.article.ArticleService;
import org.springside.modules.web.Servlets;

import com.google.common.collect.Maps;

/**
 * Article管理的Controller, 使用Restful风格的Urls:
 * 
 * List page : GET /Article/
 * Create page : GET /Article/create
 * Create action : POST /Article/create
 * Update page : GET /Article/update/{id}
 * Update action : POST /Article/update
 * Delete action : GET /Article/delete/{id}
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/article")
public class ArticleController {

	private static final String PAGE_SIZE = "3";

	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		sortTypes.put("title", "标题");
	}

	@Autowired
	private ArticleService articleService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Long userId = getCurrentUserId();

		Page<Article> articles = articleService.getUserArticle(userId, searchParams, pageNumber, pageSize, sortType);

		model.addAttribute("articles", articles);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		return "article/articleList";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("article", new Article());
		model.addAttribute("action", "create");
		return "Article/articleForm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Article newArticle, RedirectAttributes redirectAttributes) {
		User user = new User(getCurrentUserId());
		newArticle.setAuthor(user);
		newArticle.setAddTime(new Date());

		articleService.saveArticle(newArticle);
		redirectAttributes.addFlashAttribute("message", "创建文章成功");
		return "redirect:/article/";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("Article", articleService.getArticle(id));
		model.addAttribute("action", "update");
		return "Article/articleForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("Article") Article Article, RedirectAttributes redirectAttributes) {
		Article.setUpdateTime(new Date());
		articleService.saveArticle(Article);
		redirectAttributes.addFlashAttribute("message", "更新文章成功");
		return "redirect:/article/";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		articleService.deleteArticle(id);
		redirectAttributes.addFlashAttribute("message", "删除文章成功");
		return "redirect:/article/";
	}

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出Article对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getArticle(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("Article", articleService.getArticle(id));
		}
	}

	/**
	 * 取出Shiro中的当前用户Id.
	 */
	private Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.id;
	}
}
