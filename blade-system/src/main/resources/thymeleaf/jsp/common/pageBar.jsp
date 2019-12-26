<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="panelBar">
	<div class="pages">
		<span>显示</span> 
		<select theme="simple" cssClass="combox" name="numPerPage" value="${pageBean.numPerPage}" onchange="navTabPageBreak({numPerPage:this.value})">
			<option value="20" <c:if test="${page.numPerPage==20}">selected="selected"</c:if>>20</option>
			<option value="50" <c:if test="${page.numPerPage==50}">selected="selected"</c:if>>50</option>
			<option value="100" <c:if test="${page.numPerPage==100}">selected="selected"</c:if>>100</option>
			<option value="200" <c:if test="${page.numPerPage==200}">selected="selected"</c:if>>200</option>
			<option value="500" <c:if test="${page.numPerPage==500}">selected="selected"</c:if>>500</option>
			<option value="1000" <c:if test="${page.numPerPage==1000}">selected="selected"</c:if>>1000</option>
		</select> 
		<span> 
			条,共<a style="color: red">${pageBean.totalCount}</a>条, 共${pageBean.totalPage}页, 当前第${pageBean.currentPage}页
		</span>
	</div>
	<div class="pagination" targetType="navTab" totalCount="${pageBean.totalCount}" numPerPage="${pageBean.numPerPage}" pageNumShown="10" currentPage="${pageBean.currentPage}"></div>
</div>
