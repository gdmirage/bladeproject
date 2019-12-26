<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../common/taglib/taglib.jsp"%>
<div class="pageContent">
	<form action="${baseURL }/system/setting/edit" cssClass="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);" method="post">
		<input type="hidden" name="id" value="${id }">
		<div class="tabsContent pageFormContent" layoutH="56">
			<div style="height: 100px">
				<legend>添加配置</legend>
				<dl style="width: 50%;">
					<dt>Key：</dt>
					<dd>
						<input type="text" name="rcKey" maxlength="128" value="${systemSettingPo.rcKey}" />
					</dd>
				</dl>
				<dl style="width: 50%;">
					<dt>Value：</dt>
					<dd>
						<textarea name="rcValue" rows="5" cols="60">${systemSettingPo.rcValue}</textarea>
					</dd>
				</dl>
			</div>
			<div style="height: 100px">
				<dl style="width: 50%;">
					<dt>规则描述：</dt>
					<dd>
						<textarea name="ruleRemark" rows="3" cols="40">${systemSettingPo.ruleRemark}</textarea>
					</dd>
				</dl>
				<dl style="width: 50%;">
					<dt>备注：</dt>
					<dd>
						<textarea name="remark" rows="3" cols="40">${systemSettingPo.remark}</textarea>
					</dd>
				</dl>
			</div>
		</div>
		<div class="formBar">
			<ul style="float: left;">
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">提交</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>