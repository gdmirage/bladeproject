<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../../static/common/taglib/taglib.jsp" %>

<script type="text/javascript" charset="utf-8" src="${baseURL}/common/js/qrcode/jquery-3.2.1.min.js?time=${systemTime}"></script>
<script type="text/javascript" charset="utf-8" src="${baseURL}/common/js/qrcode/jquery.qrcode.min.js?time=${systemTime}"> </script>


<div style="padding: 20px;" id="urlDiv">${htmlUrl}boutiqueDetail.html?id=${id}</div>
<div id="qrcode" style="margin-left: 110px;">
</div>
<div style="text-align: center;margin-top:10px;">请用手机扫描二维码预览</div>
<script>
	$(function(){
		
		$('#qrcode').qrcode({
		    width: 100,
		    height: 100,
		    text: "${htmlUrl}boutiqueDetail.html?id=${id}"
		});
		
	})

</script>
