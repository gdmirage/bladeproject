<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../../static/common/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    <div class="layui-form">
    	<blockquote class="layui-elem-quote">
        	<div class="layui-form-item">
        		<label class="layui-form-label">会员等级：</label>
	            <div class="layui-input-inline">
	            	<select name="grade" id="grade">
	            		<option value="">请选择</option>
						<c:forEach var="item" items="${memberLevelList }">
							<option value="${item.levelCode }">${item.levelName }</option>
						</c:forEach>
					</select>
	            </div>
	            <label class="layui-form-label">微信昵称：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="nickName" autocomplete="off" class="layui-input">
	            </div>
	            <label class="layui-form-label">姓名备注：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="remark" autocomplete="off" class="layui-input">
	            </div>
	            <label class="layui-form-label">用户编号：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="userNum" autocomplete="off" class="layui-input">
	            </div>
	        </div>
	        <div class="layui-form-item">
				<label class="layui-form-label">退款次数：</label>
	            <div class="layui-input-inline">
	                <input type="number" id="returnCount" autocomplete="off" class="layui-input">
	            </div>
	            <button class="layui-btn" id="searchPermission" style="margin-left: 10px">
	                <i class="layui-icon">&#x1002;</i>查询
	            </button>
       	 	</div>
        </blockquote> 
    </div>

    <div class="layui-btn-group">
        <button class="layui-btn layui-btn-sm layui-btn-danger" id="view">查看</button>
        <button class="layui-btn layui-btn-sm layui-btn-danger" id="twoCodeClick">查看二维码</button>
        <button class="layui-btn layui-btn-sm layui-btn-danger" id="updateLevel">修改等级</button>
        <button class="layui-btn layui-btn-sm layui-btn-danger" id="updateRemark">设置备注</button>
        <!-- <button class="layui-btn layui-btn-sm layui-btn-danger" id="sendText">发送文字消息</button>
        <button class="layui-btn layui-btn-sm layui-btn-danger" id="sendPic">发送图文消息</button>
        <button class="layui-btn layui-btn-sm layui-btn-danger" id="sendTmp">发送模板消息</button> -->
    </div>

    <table class="layui-hide" id="memberTable" lay-filter="memberTable"></table>

    <script type="text/html" id="showAgent">
		<span>{{d.userNum}}</span>
        {{# if (d.isCheck === 'YES') { }}
        <span class="layui-badge layui-bg-blue">已认证</span>
        {{# } else { }}
        <span class="layui-badge layui-bg-red">未认证</span>
        {{# } }}
    </script>
    
    <script type="text/html" id="isSubscribeTem">
        {{# if (d.isSubscribe === 'YES') { }}
        <span class="layui-badge layui-bg-blue">已关注</span>
        {{# } else { }}
        <span class="layui-badge layui-bg-red">未关注</span>
        {{# } }}
    </script>

    <script type="text/html" id="dateTem">
        {{ timestampToTime(d.createTime) }}
    </script>
    
    <script>
        var table = layui.table;
        var $ = layui.jquery;
        var form = layui.form;
        form.render();
        
        var tableInit = table.render({
            elem: '#memberTable'
            , url: '${baseURL}/agent/memberlist/'
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
				{type: 'checkbox',width: '5%'}
				, {field: 'userNum', width: '12%', title: '用户编号',templet : showAgent}
				, {field: 'nickName', width: '15%', title: '微信昵称'}
				, {field: 'levelName', width: '7%', title: '等级'}
				, {field: 'remark', width: '10%', title: '备注'}
				, {field: '', width: '7%', title: '是否关注',templet : isSubscribeTem}
				, {field: 'recommendUserNum', width: '10%', title: '推荐人编号'}
				, {field: 'recommendAgentName' , width: '15%', title: '推荐人姓名'}
				, {field: 'point' , width: '7%', title: '佣金'} 
				, {field: 'jfPoint' , width: '7%', title: '积分'}
				, {field: 'returnCount' , title: '退款次数'} 
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
            if (obj.event === 'details') {
                agentDetails(data)
            }
        });

        $('#searchPermission').on('click', function () {
            reloadTable(1);
        });


        function agentDetails(data) {
            openWin('详情', '${baseURL}/agent/details?id=' + data.id, function () {
                // reloadTable(1);
                parent.reloadTable(1);
            });
        }

        $("#findInventory").click(function () {
            var checkStatus = table.checkStatus('memberTableId')
                , data = checkStatus.data;
            if (data.length != 1) {
                layer.alert('请选择一条数据');
                return;
            } else {
                openWin('库存', '${baseURL}/agent/toInventoryList?agentId=' + data[0].id, function () {
                });
            }
        });
        
        $("#updateRemark").click(function () {
            var checkStatus = table.checkStatus('memberTableId')
                , data = checkStatus.data;
            if (data.length != 1) {
                layer.alert('请选择一条数据');
                return;
            } else {
            	layer.prompt({title: '请输入用户备注', formType: 3}, function(remark, index){
           		  	layer.close(index);
	           		var url = "${baseURL}/agent/updateRemark";
	         		var postData = {
	         			remark : remark,
	         			id:data[0].id
	         		};
           			postNoMsg(url,postData,function(res){
           				layer.msg('设置成功',{
           	                icon: 1,
           	                time: 2000 //2秒关闭（如果不配置，默认是3秒）
           	            },function(){
           	            	reloadTable();
           	            });
           			},function(res){
           				layer.msg(res.msg,{
           		            icon: 2,
           		            time: 2000 //2秒关闭（如果不配置，默认是3秒）
           				});
           			});
           		});
            }
        });
        
        $("#sendText").click(function () {
            var checkStatus = table.checkStatus('memberTableId')
                , data = checkStatus.data;
            if (data.length != 1) {
                layer.alert('请选择一条数据');
                return;
            } else {
            	layer.open({
           			type: 2,
           			title:'发送文字消息',
           		  	//skin: 'layui-layer-rim', //加上边框
           		  	area: ['620px', '440px'], //宽高
           		 	content: '${baseURL}/agent/sendTextUI?id=' + data[0].id
	           		 ,zIndex: layer.zIndex //重点1
	                 ,success: function(layero){
	                   layer.setTop(layero); //重点2
	                 }
	                 ,end: function (index, layero) {
	                 }
           		});
            }
        });
        
        
        $("#sendPic").click(function () {
            var checkStatus = table.checkStatus('memberTableId')
                , data = checkStatus.data;
            if (data.length != 1) {
                layer.alert('请选择一条数据');
                return;
            } else {
            	layer.open({
           			type: 2,
           			title:'发送图文消息',
           		  	//skin: 'layui-layer-rim', //加上边框
           		  	area: ['620px', '540px'], //宽高
           		 	content: '${baseURL}/agent/sendPicAndTextUI?id=' + data[0].id
	           		 ,zIndex: layer.zIndex //重点1
	                 ,success: function(layero){
	                   layer.setTop(layero); //重点2
	                 }
	                 ,end: function (index, layero) {
	                 }
           		});
            }
        });
        
        
        $("#sendTmp").click(function () {
            var checkStatus = table.checkStatus('memberTableId')
                , data = checkStatus.data;
            if (data.length != 1) {
                layer.alert('请选择一条数据');
                return;
            } else {
            	openWin('发送模板消息', '${baseURL}/agent/sendTmpUI?id=' + data[0].id, function () {
                });
            }
        });
        
        $("#updateLevel").click(function () {
            var checkStatus = table.checkStatus('memberTableId')
                , data = checkStatus.data;
            if (data.length != 1) {
                layer.alert('请选择一条数据');
                return;
            } else {
            	layer.open({
           			type: 2,
           			title:'修改等级',
           		  	skin: 'layui-layer-rim', //加上边框
           		  	area: ['420px', '240px'], //宽高
           		 	content: '${baseURL}/agent/updateLevelUI?id=' + data[0].id
	           		 ,zIndex: layer.zIndex //重点1
	                 ,success: function(layero){
	                   layer.setTop(layero); //重点2
	                 }
	                 ,end: function (index, layero) {
	                	 reloadTable();
	                 }
           		});
            }
        });
        
        
        $("#twoCodeClick").click(function () {
            var checkStatus = table.checkStatus('memberTableId')
                , data = checkStatus.data;
            if (data.length != 1) {
                layer.alert('请选择一条数据');
                return;
            } else {
                
            }
        });
        
        $("#twoCodeClick").click(function () {
            var checkStatus = table.checkStatus('memberTableId')
                , data = checkStatus.data;
            if (data.length != 1) {
                layer.alert('请选择一条数据');
                return;
            } else {
                openWinContent('二维码', '<img class="layui-upload-img" width="320px" height="90%" src=' + data[0].inviteTwocode + '>');
            }
        });

        $("#view").click(function () {
            var checkStatus = table.checkStatus('memberTableId')
                , data = checkStatus.data;
            if (data.length != 1) {
                layer.alert('请选择一条数据');
                return;
            } else {

                openWin('详情', '${baseURL}/agent/details?id=' + data[0].id, function () {
                    // reloadTable(1);
                    parent.reloadTable(1);
                });
            }
        });

    </script>
</div>
