
/*
 * 依赖js：jquery、layui
 */

var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
var $ = layui.jquery;
var layer = layui.layer;

function downByUrlAndParamsPost(url,params){
    var form=$("<form>");//定义一个form表单
    form.attr("style","display:none");
    form.attr("method","post");//请求类型
    form.attr("action",url);//请求地址
    $("body").append(form);//将表单放置在web中
	  for ( var p in params ){ // 方法 
		  if ( typeof ( params [p]) == " function " ){ 
		  } else { 
			  var input1=$("<input>");
			    input1.attr("type","hidden");
			    input1.attr("name",p);
			    input1.attr("value",params [p]);
			    form.append(input1);
		  } 
	  }
    form.submit();
}

/**
 * post请求
 * @param url
 * @param param
 * @param success
 * @param error
 */
function post(url, param, success, error) {
	parent.parent.layer.msg('数据提交中', {
	  icon: 16
	  ,shade: 0.01
	  ,time: 0
	});
	$.ajax({ 
		url: url, 
		type : 'POST',
		data: param, 
		success: function(data){
			parent.parent.layer.closeAll();
			if (data.code == 0) {
	            success(data);
	        } else {
	            error(data);
	        }
		}, 
		error: function(data){
			parent.parent.layer.closeAll();
			error(data);
		}
	});
}


/**
 * post请求
 * @param url
 * @param param
 * @param success
 * @param error
 */
function postNoMsg(url, param, success, error) {
	$.ajax({ 
		url: url, 
		type : 'POST',
		data: param, 
		success: function(data){
			if (data.code == 0) {
	            success(data);
	        } else {
	            error(data);
	        }
		}, 
		error: function(data){
			error(data);
		}
	});
}

/**
 * 根据时间戳，获取对应的字符串时间
 * @param timestamp
 * @returns {String}
 */
function timestampToTime(timestamp) {
	if(!timestamp){
		return "";
	}
	var util = layui.util;
    var date = new Date(timestamp);
    Y = date.getFullYear() + '-';
    M = util.digit((date.getMonth()+1),2)  + '-';
    D = util.digit(date.getDate(),2) + ' ';
    h = util.digit(date.getHours(),2) + ':';
    m = util.digit(date.getMinutes(),2) + ':';
    s = util.digit(date.getSeconds(),2);
    return Y+M+D+h+m+s;
}


/**
 * 根据时间戳，获取对应的字符串时间
 * @param timestamp
 * @returns {String}
 */
function timestampToDate(timestamp) {
	if(!timestamp){
		return "";
	}
	var util = layui.util;
    var date = new Date(timestamp);
    Y = date.getFullYear() + '-';
    M = util.digit((date.getMonth()+1),2)  + '-';
    D = util.digit(date.getDate(),2);
    return Y+M+D;
}

/**
 * 打开新窗口
 * @param title
 * @param url
 * @param endCallback
 */
function openWin(title,url,endCallback){
	layer.open({
        type: 2 //iframe
        ,title: title
        ,area: ['100%', '100%']
		,offset : '0px'
        ,shade: 0
        ,content: url
        ,zIndex: layer.zIndex //重点1
        ,success: function(layero){
          layer.setTop(layero); //重点2
        }
        ,end: function (index, layero) {
        	endCallback();
        }
      });
}

function openWinContent(title,content){
    layer.open({
        title: title
        ,content: content
    });
}


function getTableJson(data){
	var json = new Array();
	for(var i in data){
		json[json.length] = data[i];
	}
	return json;
}
