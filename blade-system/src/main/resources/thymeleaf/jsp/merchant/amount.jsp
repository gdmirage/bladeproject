<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../static/taglib/taglib.jsp"%>

<link rel="stylesheet" type="text/css" href="${baseURL}/common/css/admin.css"/>

<div class="wrap-container welcome-container">
	<div class="row">
		<div class="welcome-left-container col-lg-9">
			<div class="data-show">
				<ul class="clearfix">
				
					<li class="col-sm-12 col-md-4 col-xs-12">
						<a href="javascript:;" class="clearfix">
							<div class="icon-bg bg-blue f-l">
								<span class="iconfont">&#xe604;</span>
							</div>
							<div class="right-text-con">
								<p class="name">浏览数</p>
								<p><span class="color-blue">待统计</span></p>
							</div>
						</a>
					</li>
					<li class="col-sm-12 col-md-4 col-xs-12">
						<a href="javascript:;" class="clearfix">
							<div class="icon-bg bg-org f-l">
								<span class="iconfont">&#xe606;</span>
							</div>
							<div class="right-text-con">
								<p class="name">会员数</p>
								<p><span class="color-org">待统计</span></p>
							</div>
						</a>
					</li>
					<li class="col-sm-12 col-md-4 col-xs-12">
						<a href="javascript:;" class="clearfix">
							<div class="icon-bg bg-blue f-l">
								<span class="iconfont">&#xe602;</span>
							</div>
							<div class="right-text-con">
								<p class="name">订单数</p>
								<p><span class="color-blue">待统计</span></p>
							</div>
						</a>
					</li>
					
					<li class="col-sm-12 col-md-4 col-xs-12" id="allAmount">
						<a href="javascript:;" class="clearfix">
							<div class="icon-bg bg-org f-l">
								<span class="iconfont">&#xe600;</span>
							</div>
							<div class="right-text-con">
								<p class="name">总金额</p>
								<p><span class="color-org">${merchant.amountUsed +  merchant.amountUnused}</span></p>
							</div>
						</a>
					</li>
					
					<li class="col-sm-12 col-md-4 col-xs-12" id="withdrawalAmount">
						<a href="javascript:;" class="clearfix">
							<div class="icon-bg bg-org f-l">
								<span class="iconfont">&#xe600;</span>
							</div>
							<div class="right-text-con">
								<p class="name">可提现金额</p>
								<p><span class="color-org">${merchant.amountUsed}</span></p>
							</div>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>

<script>
var $ = layui.jquery;

//总金额
$('#allAmount').click(function(){
	parent.openMenu('999999_amount','merchantAmountDetail/listUI','总金额');
});

//可提现金额
$('#withdrawalAmount').click(function(){
	parent.openMenu('999999_amount','merchantAmountDetail/listUI','总金额');
});

</script>
