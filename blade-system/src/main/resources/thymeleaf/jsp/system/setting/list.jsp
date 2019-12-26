<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../common/taglib/taglib.jsp"%>
<form id="pagerForm" method="post" action="${baseURL }/system/setting/list">
	<%@include file="../../common/pageParameter.jsp"%>
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${baseURL }/system/setting/list" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td colspan="2">
						Key：
						<input type="text" name="rcKey" value="${systemSettingPo.rcKey}" />
						&nbsp;&nbsp;
						Value：
						<input type="text" name="rcValue" value="${systemSettingPo.rcValue}" />
						&nbsp;&nbsp;
						规则描述：
						<input type="text" name="ruleRemark" value="${systemSettingPo.ruleRemark}" />
					</td>
					</td>
					<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<button title="查询" type="submit">查&nbsp;询</button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<shiro:hasPermission name="system:setting:add">
					<a title="添加配置" class="add" href="${baseURL }/system/setting/addUI" target="navTab">
						<span>添加配置</span>
					</a>
				</shiro:hasPermission>
			</li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width=100% layoutH="110">
		<thead>
			<tr>
				<th width="36">序号</th>
				<th width="180">Key</th>
				<th width="180">Value</th>
				<th width="400">规则描述</th>
				<th width="130">创建时间</th>
				<th width="130">修改时间</th>
				<th width="80">备注</th>
				
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${pageBean.recordList}" varStatus="s">
				<tr>
					<td>${s.index + 1}</td>
					<td>${item.rcKey}</td>
					<td>${item.rcValue}</td>
					<td>${item.ruleRemark}</td>
					<td>
						<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td>
						<fmt:formatDate value="${item.editTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td>${item.remark}</td>
					<td>
						<shiro:hasPermission name="system:setting:edit">
						[&nbsp;<a title="修改" target="navTab" href="${baseURL }/system/setting/editUI?id=${item.id}" style="color: blue;">修改</a>&nbsp;]
                        </shiro:hasPermission>
                        <shiro:hasPermission name="system:setting:delete">
                        [&nbsp;<a title="确定要删除吗?" target="ajaxTodo" method="remove" 
                        href="${baseURL }/system/setting/delete?id=${item.id}" style="color: blue;">删除</a>&nbsp;]
						</shiro:hasPermission>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${pageBean.totalCount==0}">
				<tr>
					<td>暂无数据</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<%@include file="../../common/pageBar.jsp"%>
</div>
