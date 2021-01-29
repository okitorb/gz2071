<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>人事管理系统 ——后台管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link href="fkjava.ico" rel="shortcut icon" type="image/x-icon" />
<link href="${ctx }/css/css.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="${ctx}/js/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript">
		
	    $(document).ready(function() {
	        
	        /** 表单提交的校验 */
	        $("#btn").click(function(){
	        	var name = $("#name").val(); 
	        
	   
	        	if($.trim(name).length <= 2){
	        		alert("请输入两个字符以上的标题");
	        		return ;
	        	}
	        	
	        	
	        	$("#noticeForm").submit();
	        	
	        })
	    });
			
			
		</script>
</head>
<body>

	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="10"></td>
		</tr>
		<tr>
			<td width="15" height="32"><img
				src="${ctx }/images/main_locleft.gif" width="15" height="32"></td>
			<td class="main_locbg font2"><img
				src="${ctx }/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：公告管理
				&gt; 添加公告</td>
			<td width="15" height="32"><img
				src="${ctx }/images/main_locright.gif" width="15" height="32"></td>
		</tr>
	</table>

	<table width="100%" height="90%" border="0" cellpadding="10"
		cellspacing="0" class="main_tabbor">
		<tr valign="top">
			<td>

				<form id="noticeForm" name="noticeForm"
					action="${ctx}/noticeaddsave.action" enctype="multipart/form-data"
					method="post">
					<!-- 隐藏表单，flag表示添加标记 -->
					<input type="hidden" name="id" value="${sessionScope.user_session.id}">
					<table width="100%" border="0" cellpadding="0" cellspacing="10"
						class="main_tab">

						<tr>
							<td class="font3 fftd">公告标题：<input type="text" name="name"
								size="30" id="name" /></td>
						</tr>
						<tr>
							<td class="main_tdbor"></td>
						</tr>


						<tr>
							<td class="font3 fftd">公告描述：<br /> <textarea name="content"
									cols="88" rows="11" id="content"></textarea>
							</td>
						</tr>
						<tr>
							<td class="main_tdbor"></td>
						</tr>

						<tr>
							<td class="font3 fftd">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：
								<select name="type_id" style="width: 143px;">
									<option value="0">--请选择类型--</option>
									<c:forEach items="${requestScope.typeList }" var="type">
										<option value="${type.id }">${type.name }</option>
									</c:forEach>
							</select>
							</td>
						</tr>
						<tr>
							<td><span style="color: red">${message}</span></td>
						</tr>
						<tr>
							<td class="main_tdbor"></td>
						</tr>

						<tr>
							<td class="font3 fftd"><input type="button" id="btn"
								value="添加"> <input type="reset" value="重置"></td>
						</tr>
						<tr>
							<td class="main_tdbor"></td>
						</tr>


					</table>
				</form>
			</td>
		</tr>
	</table>
	<div style="height: 10px;"></div>
</body>
</html>