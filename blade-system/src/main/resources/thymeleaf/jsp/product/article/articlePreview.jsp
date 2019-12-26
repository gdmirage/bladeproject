<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../../static/taglib/taglib.jsp" %>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>销售信网校</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<!--标准mui.css-->
		<link rel="stylesheet" type="text/css" href="${htmlUrl }css/mui.min.css?v=20180811" />
		<link rel="stylesheet" type="text/css" href="${htmlUrl }css/icons-extra-v-1.0.1.css?v=20180811" />
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="${htmlUrl }css/app-v-1.0.0.css?v=20180811" />
		<link rel="stylesheet" type="text/css" href="${htmlUrl }css/articleDetail-v-1.0.15.css?time=20180811" />
		<style>
			iframe{
				max-width: 100% !important;
    			width: 100% !important; 
			}
			.twocode1{
				width:70%;
				position:absolute;
				top:40px;
				z-index:9999;
			}
			.twocode1div{
				display:none;
				width:100%;
				height:100%;
				text-align:center;
				position:absolute;
				top:0px;
				background-color:#FAFAFA;
			}
			.twocode2{
				width:50%;
			}
		</style>
	</head>

	<body>
		<div class="mui-content">
			<!-- 文章 -->
			<div class="detail">
				<h2 class="articleTitle">
					<!--<span class="background-color-huang">名师</span>-->
					${article.articleTitle }
				</h2>
				<div class="articleMsg">
					<p class="original">原创：<span class="author">${article.authorName }</span></p>
					<span class="publicName">销售信网校</span>
					<span class="date">今天</span>
				</div>
				<p class="prompt">
					<span class="promptOprate">点击上方蓝字<img src="${htmlUrl }img/articleDetail_00.png" alt="" />关注</span>
					<span class="promptContent">百万营销人的订阅首选！</span>
				</p>
				<div class="img-box"><img src="${article.picPath }" /> </div>
				<p class="authorDiv">文/<span class="authorName">${article.authorName }</span></p>
				<div class="contentWrap">
					<div class="content border-top">
						${article.free }
					</div>
				</div>
				<div id="chargeInfo" class="content chargeInfo">
						${article.charge }
				</div>
				<div class="recommendDiv">
					<p class="recommendTitle"><span>其TA人还看了</span></p>
					<c:forEach items="${article.articlePushList }" var="item" >
						<div class="recommend">
							<img src="${item.picPath}" alt="" />
							<p class="recommendName">${item.articleTitle}</p>
						</div>
					</c:forEach>
				</div>
			
				<div class="recording">
					<span class="readOriginal" _url="${article.originalUrl}">阅读原文</span>
					<p class="reading">阅读<span class="readingNumber">${article.readCount}</span></p>
					<span class="likeNumber  articleLikeNumber">${article.likeCount}</span>
				</div>
				
			<div class="gray"></div>
			<div class="gotoIndex">
				<p class="first">返回</p>
				<p>主页</p>
			</div>
			</div>
			<div id="twocodeDiv" style="background-color: white;text-align:center;padding: 20px 0; display: none;">
				<p class="twocodeTitleNew">长按识别二维码关注</p>
				<img class="twocode2" src="" />
				<p class="mobReferUser"></p>
			</div>
			<!-- 留言 -->
			<div class="partBottom">
				
			</div>
			<div class="gotoIndex">
				<p class="first">返回</p>
				<p>主页</p>
			</div>
		</div>
		<div class="twocode1div" style="">
			<img class="twocode1" src="http://imgs.zqmos.com/userHead/6BDBE793A6704590A76247DBF24DEE6D.jpeg" />
		</div>
		<div class="twocodeFloat">
			<p class="twocodeTitle">长按识别二维码关注</p>
			<img class="twocode2" src="" />
			<p class="mobReferUser"></p>
		</div>
	</body>
	
	<!-- <script src="js/lib/get-v.1.0.3.js?v=20180811"></script>
	<script src="js/config-v1.0.26.js?v=20180811"></script>
	<script src="js/lib/redirect-v-1.0.3.js?v=20180811"></script>
	<script src="js/lib/mui.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/lib/mui.previewimage.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/lib/jquery-1.9.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/lib/layer.js"></script>
	<script src="js/lib/app.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/lib/underscore-min.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script src="js/lib/wxfenxiang-v-1.0.2.js?v=20180811"></script> 
	<script src="${baseURL}/common/js/article/articleDetail-v-1.0.11.js?v=20180811" type="text/javascript" charset="utf-8"></script>
	-->
</html>