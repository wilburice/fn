<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>文章栏目管理</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/task/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${articleClassify.id}"/>
		<fieldset>
			<legend><small>管理文章栏目</small></legend>
			<div class="control-group">
				<label for="task_name" class="control-label">名称:</label>
				<div class="controls">
					<input type="text" id="task_name" name="name"  value="${articleClassify.name}" class="input-large required" minlength="3"/>
				</div>
			</div>	
		</fieldset>
	</form>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#task_name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</body>
</html>
