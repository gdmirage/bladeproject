
var token;
var page = 1;
var pageSize = 10;
var id ;
var pay;
var flagSwith=0;
var mobRefer;
$(function(){
	// 分享者
	mobRefer = get.mobRefer ? get.mobRefer : '';
	token = localStorage.token;
 	id = get.articleId || '';
 	pay = get.pay || '';
	getArticle(token,id);
	if(mobRefer){
		getAgentInfo(mobRefer);
	}else{
		getCompanyInfo();
	}
	
	//文章点赞
	$(document).on("tap", ".articleLikeNumber", function() {
		if($(this).hasClass("completed")) {
			deleteLikeByArticle(token,id);
		} else {
			addLikeByArticle(token,id);
		}
	})
	//留言点赞
	$(document).on("tap",".likes label",function(){
		if($(this).attr("flagSwith")=="0"){
			$(this).css("color","#be2a25");
			$(this).attr("flagSwith","1");
			var num=parseInt($(this).parents(".likes").find(".num").html());
			num=num+1;
			$(this).parents(".likes").find(".num").html(num)
		}else{
			$(this).css("color","#8f8f94");
			$(this).attr("flagSwith","0");
			var num=parseInt($(this).parents(".likes").find(".num").html());
			num=num-1;
			$(this).parents(".likes").find(".num").html(num)
		}
	})
	
	$(document).on("tap", ".leaveMessage", function() {
		location.href = 'liveMessage.html?type=ARTICLE&productId=' + id
			+ '&time=' + new Date().getTime();
	})
	
	$(document).on("tap", ".readOriginal", function() {
		var _url = $(this).attr('_url');
		if(_url){
			location.href = _url;
		}
	})
	
	

	$(document).on("tap", ".mui-icon-arrowdown", function() {
		$(".add-more-box").hide();
		$(".content .txt").css({
			height: "auto"
		});
	})

	$(document).on("tap", "#return", function() {
		history.go(-1);
	})
	$(document).on("tap", ".readMore", function() {
		var isOnePrice = $(this).attr('isOnePrice');
		if(isOnePrice){
			//一口价支付
			submitOrderByarticlePayOnePrice(isOnePrice,id)
			
		}else{
			$(".gray").show();
			$(".redEnvelopes").show();
		}
		
	})
	
	$(document).on("tap", "#paymentDiv", function() {
		var btnArray = ['取消', '确定打赏'];
		mui.prompt('', '打赏金额', '请输入打赏金额', btnArray, function(e) {
			if (e.index == 1) {
				var price = parseFloat(e.value);
				if(price && price > 0){
					submitOrderByarticlePayFree(price,id);
				}else{
					mui.alert('请输入正确的打赏金额');
				}
				
			}
		})
	})

	$(document).on("tap", ".gray", function() {
		$(".gray").hide();
		$(".redEnvelopes").hide();
	})

	$(document).on("tap", ".amount li", function() {
		$(this).addClass("activeClass");
		$(".amount li").not($(this)).removeClass("activeClass");
		
		var priceCount = $(this).attr('priceCount');
		
		submitOrderByArticle(priceCount,id)
	})
	
	$(document).on("tap", ".viewAll span", function() {
		var productType='ARTICLE';
		location.href = 'liveMessageList.html?productId='+id+'&productType='+productType+'&time=' + new Date().getTime();
	})
	
	$(document).on("tap", ".gotoIndex", function() {
		location.href = 'index.html?time=' + new Date().getTime();
	})
	
	$(document).on("tap", ".recommend", function() {
		var articleId = $(this).attr('articleId');
		location.href = 'article_detail.html?articleId=' + articleId + '&time=' + new Date().getTime();
	})
//	mui.previewImage();
	//点击网校销售信
	$(document).on("tap", ".publicName", function() {
		flagSwith=1;
		if(mobRefer){
			getAgentInfo(mobRefer);
		}else{
			getCompanyInfo();
		}
	})
	//关闭二维码
	$(document).on("tap", ".gray", function() {
		$(".twocodeFloat").hide();
		$(".gray").hide();
		flagSwith=0;
	})
})

function getAgentInfo(mobRefer){
	var data={
	};
	$.ajax({
		type:"get",
		url: config.getAgentInfo,
		async:true,
		data:data,
		headers: {
           	token : mobRefer
       	},
		success:function(obj){
			console.log(obj);
			if(obj.re == 0){
				$('.twocode2').attr('src',obj.data.twocode);
				$('.mobReferUser').html(obj.data.nickName);
				if(flagSwith==1){
					$(".twocodeFloat").show();
					$(".gray").show();
				}
			}
			
		},
		error:function(e){
			
		}
	});
}


function getCompanyInfo(){
	var data={
	};
	$.ajax({
		type:"get",
		url: config.getCompanyInfo,
		async:true,
		data:data,
		headers: {
       	},
		success:function(obj){
			console.log(obj);
			if(obj.re == 0){
				$('.twocode2').attr('src',obj.data.twocode);
				$('.mobReferUser').html(obj.data.nickName);
				if(flagSwith==1){
					$(".twocodeFloat").show();
					$(".gray").show();
				}
			}
			
		},
		error:function(e){
			
		}
	});
}

/**
 * 查询文章信息
 * flag  是否支付成功后
 */
function getArticle(token,id,flag){
	var data={
		id : id
	};
	openMask();
	$.ajax({
		type:"get",
		url:config.getArticle,
		async:true,
		data:data,
		headers: {
           	token : token
       	},
		success:function(obj){
			console.log(obj);
			closeMask();
			if(obj.re == 0){
				if(!flag){
					var compiled = _.template($('#article_tmp').html());
					$('.detail').html(compiled({info:obj.data}));	
					
					var compiled = _.template($('#leaveMessage_tmp').html());
					$('.partBottom').html(compiled({info:obj.data}));	
					
					
					mui.toast('阅读    +1')
				}
				if(pay){
					location.href = "#chargeInfo"; 
				}
				
				if(mobRefer){
					$('#twocodeDiv').show();
				}
				
			}else if(obj.re == -99){
				resetLogin();
			}else if(obj.re == -999){
				resetLogin();
			}
			closeMask();
		},
		error:function(e){
			closeMask();
		}
	});
}


/**
 * 文章点赞
 */
function addLikeByArticle(token,id){
	var data={
		id : id
	};
	openMask();
	$.ajax({
		type:"post",
		url:config.addLikeByArticle,
		async:true,
		data:data,
		headers: {
           	token : token
       	},
		success:function(obj){
			console.log(obj);
			closeMask();
			if(obj.re == 0){
				$('.articleLikeNumber').addClass("completed");
				var num = parseInt($('.articleLikeNumber').html());
				num = num + 1;
				$('.articleLikeNumber').html(num);		
			}else if(obj.re == -99){
				resetLogin();
			}
			closeMask();
		},
		error:function(e){
			closeMask();
		}
	});
}


/**
 * 文章取消点赞
 */
function deleteLikeByArticle(token,id){
	var data={
		id : id
	};
	openMask();
	$.ajax({
		type:"post",
		url:config.deleteLikeByArticle,
		async:true,
		data:data,
		headers: {
           	token : token
       	},
		success:function(obj){
			console.log(obj);
			closeMask();
			if(obj.re == 0){
				$('.articleLikeNumber').removeClass("completed");
				var num = parseInt($('.articleLikeNumber').html());
				num = num - 1;
				$('.articleLikeNumber').html(num);		
			}else if(obj.re == -99){
				resetLogin();
			}
			closeMask();
		},
		error:function(e){
			closeMask();
		}
	});
}


/**
 * 提交订单(浮动价格)
 */
function submitOrderByArticle(priceCount,id){
	var data={
		priceCount : priceCount,
		id : id
	};
	$.ajax({
		type:"post",
		url:config.submitOrderByArticle,
		async:true,
		data:data,
		headers: {
           	token : token
       	},
		success:function(obj){
			console.log(obj)
			if(obj.appId){
				_appId = obj.appId;//公众号名称，由商户传入     
				_timeStamp = obj.timeStamp;//时间戳，自1970年以来的秒数     
				_nonceStr = obj.nonceStr;//随机串     
				_packageStr = obj.packageStr;
				_signType = obj.signType;//微信签名方式
				_paySign = obj.paySign;//微信签名 
				_orderId = obj.orderId;//id
				if (typeof WeixinJSBridge == "undefined") {
					if (document.addEventListener) {
						document.addEventListener(
								'WeixinJSBridgeReady', onBridgeReady,
								false);
					} else if (document.attachEvent) {
						document.attachEvent('WeixinJSBridgeReady',
								onBridgeReady);
						document.attachEvent('onWeixinJSBridgeReady',
								onBridgeReady);
					}
				} else {
					onBridgeReady();
				}
			}else{
				mui.alert(obj.msg);
			}
		},
		error:function(e){
			closeMask();
		}
	});
}


/**
 * 提交订单(一口价)
 */
function submitOrderByarticlePayOnePrice(onePrice,id){
	var data={
		onePrice : onePrice,
		id : id
	};
	$.ajax({
		type:"post",
		url:config.submitOrderByarticlePayOnePrice,
		async:true,
		data:data,
		headers: {
           	token : token
       	},
		success:function(obj){
			console.log(obj)
			if(obj.appId){
				_appId = obj.appId;//公众号名称，由商户传入     
				_timeStamp = obj.timeStamp;//时间戳，自1970年以来的秒数     
				_nonceStr = obj.nonceStr;//随机串     
				_packageStr = obj.packageStr;
				_signType = obj.signType;//微信签名方式
				_paySign = obj.paySign;//微信签名 
				_orderId = obj.orderId;//id
				if (typeof WeixinJSBridge == "undefined") {
					if (document.addEventListener) {
						document.addEventListener(
								'WeixinJSBridgeReady', onBridgeReady,
								false);
					} else if (document.attachEvent) {
						document.attachEvent('WeixinJSBridgeReady',
								onBridgeReady);
						document.attachEvent('onWeixinJSBridgeReady',
								onBridgeReady);
					}
				} else {
					onBridgeReady();
				}
			}else{
				mui.alert(obj.msg);
			}
		},
		error:function(e){
			closeMask();
		}
	});
}



/**
 * 提交订单(免费打赏)
 */
function submitOrderByarticlePayFree(price,id){
	var data={
		price : price,
		id : id
	};
	$.ajax({
		type:"post",
		url:config.submitOrderByarticlePayFree,
		async:true,
		data:data,
		headers: {
           	token : token
       	},
		success:function(obj){
			console.log(obj)
			if(obj.appId){
				_appId = obj.appId;//公众号名称，由商户传入     
				_timeStamp = obj.timeStamp;//时间戳，自1970年以来的秒数     
				_nonceStr = obj.nonceStr;//随机串     
				_packageStr = obj.packageStr;
				_signType = obj.signType;//微信签名方式
				_paySign = obj.paySign;//微信签名 
				_orderId = obj.orderId;//id
				if (typeof WeixinJSBridge == "undefined") {
					if (document.addEventListener) {
						document.addEventListener(
								'WeixinJSBridgeReady', onBridgeReady,
								false);
					} else if (document.attachEvent) {
						document.attachEvent('WeixinJSBridgeReady',
								onBridgeReady);
						document.attachEvent('onWeixinJSBridgeReady',
								onBridgeReady);
					}
				} else {
					onBridgeReady();
				}
			}else{
				mui.alert(obj.msg);
			}
		},
		error:function(e){
			closeMask();
		}
	});
}


function onBridgeReady() {
	WeixinJSBridge.invoke('getBrandWCPayRequest', {
		"appId" : _appId, //公众号名称，由商户传入     
		"timeStamp" : _timeStamp, //时间戳，自1970年以来的秒数     
		"nonceStr" : _nonceStr, //随机串     
		"package" : _packageStr,
		"signType" : _signType, //微信签名方式：     
		"paySign" : _paySign //微信签名 
	}, function(res) {
		closeMask();
		if (res.err_msg == "get_brand_wcpay_request:ok") {
			// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
			// 查询文章
			location.replace('article_detail.html?articleId=' + id + '&pay=true&time=' + new Date().getTime())
		} else if (res.err_msg == "get_brand_wcpay_request:cancel") {
		} else {
			mui.alert('支付失败');
		}
	});
}
