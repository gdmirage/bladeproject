<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../../../static/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    <div class="layui-form">
        <div class="layui-form-item">
        	<blockquote class="layui-elem-quote">
	            <label class="layui-form-label">微信昵称：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="nickName" autocomplete="off" class="layui-input">
	            </div>
	            <label class="layui-form-label">用户编号：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="userNum" autocomplete="off" class="layui-input">
	            </div>
	            
	            <button class="layui-btn" id="searchPermission" style="margin-left: 10px">
	                <i class="layui-icon">&#x1002;</i>查询
	            </button>
	            <button class="layui-btn" id="mobileAgentAdd" style="margin-left: 10px">
	                <i class="layui-icon">&#x1002;</i>绑定账号
	            </button>
	         </blockquote> 
	     </div>
        
    </div>

    <table class="layui-hide" id="memberTable" lay-filter="memberTable"></table>

    <script type="text/html" id="showAgent">
		<span>{{d.userNum}}</span>
    </script>
    
    <script type="text/html" id="dateTem">
        {{ timestampToTime(d.createTime) }}
    </script>
    
    <script type="text/html" id="courseColumnBar"> 
				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="del">取消绑定</a>
    </script>
    	
    <script>
        var table = layui.table;
        var $ = layui.jquery;
        var form = layui.form;
        form.render();
        
        var tableInit = table.render({
            elem: '#memberTable'
            , url: '${baseURL}/agent/mobileAgentList/'
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            	grade : $('#grade').val()
            	, nickName: $('#nickName').val()
                , userNum: $('#userNum').val()
                , remark: $('#remark').val()
                , returnCount: $('#returnCount').val()
            }
            , cols: [[
                 {field: 'userNum', width: '30%', title: '用户编号',templet : showAgent}
				, {field: 'nickName', width: '50%', title: '微信昵称'} 
				//, {field: '', width: '7%', title: '是否关注',templet : isSubscribeTem}
				, {field: 'toolbar', title: '操作',toolbar: '#courseColumnBar' , fixed: 'right'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'memberTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                	grade : $('#grade').val()
                	, nickName: $('#nickName').val()
                    , userNum: $('#userNum').val()
                    , remark: $('#remark').val()
                    , returnCount: $('#returnCount').val()
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

        //监听工具条
        table.on('tool(memberTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
            	del(data)
            }
        });

        $('#searchPermission').on('click', function () {
            reloadTable(1);
        });


        function del(data) {
        	var deleteData = {
   				id : data.id
   		    }
   			parent.parent.layer.confirm('确认取消绑定【' + data.nickName + '】', function(index){
   				var url = "${baseURL}/agent/delMobileAgent";
   				post(url,deleteData,function(data){
   					reloadTable();
   					parent.parent.layer.close(index);
   					parent.parent.layer.msg('取消绑定成功',{
   	  				  icon: 1,
   	  				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
   	  				});
   	  			},function(data){
   	  				parent.parent.layer.alert(data.msg,{
   					  icon: 2
   					});
   	  			});
   			});
        }

        $("#mobileAgentAdd").click(function () {
             openWin('绑定账号', '${baseURL}/agent/mobileAgentAddUI', function () {
            	 reloadTable(1);
             });
        });
        

    </script>
</div>
