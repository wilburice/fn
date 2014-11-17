<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>任务管理</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/article/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${article.id}"/>
		<fieldset>
			<legend><small>管理文章</small></legend>
			<div class="control-group">
				<label for="article_title" class="control-label">标题:</label>
				<div class="controls">
					<input type="text" id="article_title" name="title"  value="${article.title}" class="input-large required" minlength="3"/>
				</div>
			</div>	
			<div class="control-group">
				<label for="descri" class="control-label">描述:</label>
				<div class="controls">
					<textarea id="descri" name="descri" class="input-large">${article.descri}</textarea>
				</div>
			</div>	
			<div class="control-group">
				<label for="keyword" class="control-label">关键字:</label>
				<div class="controls">
					<textarea id="keyword" name="keyword" class="input-large">${article.keyword}</textarea>
				</div>
			</div>
			<div class="control-group">
				<label for="content" class="control-label">正文:</label>
				<div class="controls">
					<textarea id="content" name="content" class="input-large">${article.content}</textarea>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#article_title").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</body>
</html>
