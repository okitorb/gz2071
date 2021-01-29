<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 分页处理 -->
<style>
	a:link{
		text-decoration:none;
	}
</style>
<table width='100%' align='center' style='font-size: 13px;'
	class="yahoo">
	<tr>
		<td
			style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>
			
			<c:if test="${pageModel.totalPageSum > 1 }">
			总${pageModel.totalPageSum}页数&nbsp;&nbsp;总记录条数${pageModel.totalRecordSum}
				<c:choose>
					<c:when test="${pageModel.pageIndex==1}">
						<span class='disabled'>首页</span>
						<span class='disabled'>上一页</span>
						<a href="javascript:toPage(${pageModel.nextPage})">下一页</a>
						<a href="javascript:toPage(${pageModel.totalPageSum})">尾页</a>
					</c:when>
					<c:when test="${pageModel.pageIndex == pageModel.totalPageSum}">
						<a href="javascript:toPage(1)">首页</a>
						<a href="javascript:toPage(${pageModel.prePage})">上一页</a>
						<span class='disabled'>下一页</span>
						<span class='disabled'>尾页</span>
	
					</c:when>
					<c:otherwise>
						<a href="javascript:toPage(1)">首页</a>
						<a href="javascript:toPage(${pageModel.prePage})">上一页</a>
						<a href="javascript:toPage(${pageModel.nextPage})">下一页</a>
						<a href="javascript:toPage(${pageModel.totalPageSum})">尾页</a>
					</c:otherwise>
						
				</c:choose>		
			 &nbsp;跳转到&nbsp;&nbsp;<input
			style='text-align: center; BORDER-RIGHT: #aaaadd 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #aaaadd 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 2px; MARGIN: 2px; BORDER-LEFT: #aaaadd 1px solid; COLOR: #000099; PADDING-TOP: 2px; BORDER-BOTTOM: #aaaadd 1px solid; TEXT-DECORATION: none'
			type='text' size='2' id='pager_jump_page_size' /> &nbsp;<input
			type='button'
			style='text-align: center; BORDE R-RIGHT: #dedfde 1px solid; PADDING-RIGHT: 6px; BACKGROUND-POSITION: 50% bottom; BORDER-TOP: #dedfde 1px solid; PADDING-LEFT: 6px; PADDING-BOTTOM: 2px; BORDER-LEFT: #dedfde 1px solid; COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; BORDER-BOTTOM: #dedfde 1px solid; TEXT-DECORATION: none'
			value='确定' id='pager_jump_btn' onclick="pagerJump()"/>
			</c:if>
		</td>
	</tr>
</table>