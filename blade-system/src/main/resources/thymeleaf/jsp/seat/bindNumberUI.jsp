<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/common/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
	    <div class="layui-form">
	    	<input type="hidden" id="seatId" value="${seatId }" />
	        <div class="layui-form-item">
	            <label class="layui-form-label">分机号：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="callNum" autocomplete="off" class="layui-input">
	            </div>
	
	            <button class="layui-btn" id="searchUser">
	                <i class="layui-icon">&#xe615;</i>查询
	            </button>
	        </div>
	    </div>
	</blockquote>
	
    <table class="layui-hide" id="userTable" lay-filter="userTable"></table>
	
	<script type="text/html" id="butTem">
		<a class="layui-btn layui-btn-xs" lay-event="bindNumber"><i class="layui-icon">&#xe64d;</i>绑定</a>
	</script>
	
    <script>
        var table = layui.table;
        var $ = layui.jquery;
        var form = layui.form;
    	form.render();
        
        var tableInit = table.render({
            elem: '#userTable'
            , url: '${baseURL}/numberPool/list'
            , defaultToolbar :  ['filter', 'print']
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            	callNum: $('#callNum').val(),
            	status : 'un_bind'
            }
            , cols: [[
                {field: 'callNum', width: '80%', title: '分机号码'}
                , {field: '',  title: '操作',templet:'#butTem'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'userTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                	callNum: $('#callNum').val(),
                	status : 'un_bind'
                }
            };
            //组装分页参数,page为空时，取当前页数
            if (page) {
                loadData.page = {
                    curr: page //重新从第page页开始
                }
            }

            tableInit.reload(loadData);
        }

        $('#searchUser').on('click', function () {
            reloadTable(1);
        });
        
        //列按钮监听
        table.on('tool(userTable)', function (obj) {
        	var data = obj.data;
            if(obj.event === 'bindNumber') {
            	bindNumber(data);
            }
        });
        
        function bindNumber(data){
        	var url = "${baseURL}/seat/bindNumber";
        	var paramData = {
        			seatId:$('#seatId').val(),
        			numberId : data.id
        	};
        	post(url,paramData,function(data){
				parent.parent.layer.msg('绑定成功',{
  				  icon: 1,
  				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
  				},function(){
  					parent.table.reload();
  	                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
  	                parent.layer.close(index); //再执行关闭
  				});
  			},function(data){
  				parent.parent.layer.msg(data.msg,{
				  icon: 2,
				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
				});
  			});
        }

    </script>
</div>
