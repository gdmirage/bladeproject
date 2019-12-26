<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib/taglib.jsp"%>



<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
     <form class="layui-form">
     	<input type="hidden" name="id" value="${merchantActivity.id }" />
     	<input type="hidden" name="merchantId" value="${merchantActivity.merchantId }" />
     	<div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">开始时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="startTime" required lay-verify="required" lay-verify="datetime" id="startTime"
                           placeholder="yyyy-MM-dd HH:mm:ss"
                           autocomplete="off"
                           class="layui-input" value="<fmt:formatDate value="${merchantActivity.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/>">
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">结束时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="endTime" required lay-verify="required" lay-verify="datetime" id="endTime"
                           placeholder="yyyy-MM-dd HH:mm:ss"
                           autocomplete="off"
                           class="layui-input" value="<fmt:formatDate value="${merchantActivity.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>">
                </div>
            </div>
        </div>
		<div class="layui-form-item">
            <label class="layui-form-label">裂变人数：</label>
            <div class="layui-input-inline">
            	<input type="number" name="userCount" required lay-verify="required" placeholder="请输入裂变人数"
                       autocomplete="off" class="layui-input" value="${merchantActivity.userCount }" >
            </div>
        </div>
        
        <div class="layui-form-item">
            <label class="layui-form-label">赠送积分：</label>
            <div class="layui-input-inline">
                <input type="number" name="point" required lay-verify="required" placeholder="请输入赠送积分"
                       autocomplete="off" class="layui-input" value="${merchantActivity.point }" >
            </div>
        </div>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="merchantActivityForm">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>

</div>
<script type="text/javascript">
	var $ = layui.jquery;
	var laydate = layui.laydate;
	var form = layui.form;
	form.render();
	

    //日期
    laydate.render({
        elem: '#startTime'
        , type: 'datetime'
    });
    laydate.render({
        elem: '#endTime'
        , type: 'datetime'
    });
    
	form.on('submit(merchantActivityForm)', function(data){
		var url = "${baseURL}/merchantActivity/edit";
		
		post(url,data.field,function(data){
			parent.parent.layer.msg('设置成功',{
                icon: 1,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            },function(){
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            });
		},function(data){
			parent.parent.layer.msg(data.msg,{
                icon: 2,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
			});
		});
	  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
	
	
	
</script>
