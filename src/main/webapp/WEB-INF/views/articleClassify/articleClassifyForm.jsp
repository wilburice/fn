<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>文章栏目管理</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/articleClassify/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${articleClassify.id}"/>
		<fieldset>
			<legend><small>管理文章栏目</small></legend>
			<div class="control-group">
				<label for="articleClassify_name" class="control-label">名称:</label>
				<div class="controls">
					<input type="text" id="articleClassify_name" name="name"  value="${articleClassify.name}" class="input-large required" minlength="3"/>
				</div>
			</div>	
			<div class="control-group">
				<label for="articleClassify_father" class="control-label">父栏目:</label>
				<div class="controls">
					<select id="articleClassify_father" name="father.id" class="input-large required" >
						<option value="0">首页</option>
						<c:forEach items="${articleClassifyList.content}" var="artCls">
							<option value="${artCls.id}">${artCls.name}</option>
						</c:forEach>
					</select>
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
			$("#articleClassify_name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</body>
</html>