/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package fn.service.article;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fn.entity.ArticleClassify;
import fn.repository.ArticleClassifyDao;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class ArticleClassifyService {

	private ArticleClassifyDao articleClassifyDao;

	public ArticleClassify getArticleClassify(Long id) {
		return articleClassifyDao.findOne(id);
	}

	public void saveArticleClassify(ArticleClassify entity) {
		articleClassifyDao.save(entity);
	}

	public void deleteArticleClassify(Long id) {
		articleClassifyDao.delete(id);
	}

	public List<ArticleClassify> getAllArticleClassify() {
		return (List<ArticleClassify>) articleClassifyDao.findAll();
	}

	public Page<ArticleClassify> getUserArticleClassify(Long fId, Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<ArticleClassify> spec = buildSpecification(fId, searchParams);

		return articleClassifyDao.findAll(spec, pageRequest);
	}
	
	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<ArticleClassify> buildSpecification(Long fId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("father", new SearchFilter("father.id", Operator.EQ, fId));
		Specification<ArticleClassify> spec = DynamicSpecifications.bySearchFilter(filters.values(), ArticleClassify.class);
		return spec;
	}

	@Autowired
	public void setArticleClassifyDao(ArticleClassifyDao ArticleClassifyDao) {
		this.articleClassifyDao = ArticleClassifyDao;
	}
}
