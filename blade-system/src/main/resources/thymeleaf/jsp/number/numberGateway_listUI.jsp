<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
	    <div class="layui-form">
	        <div class="layui-form-item">
	            <label class="layui-form-label">分机号码：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="callNum" autocomplete="off" class="layui-input">
	            </div>
				<label class="layui-form-label">线路：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="gatewayName" autocomplete="off" class="layui-input">
	            </div>
	            <button class="layui-btn" id="searchNumberGateway">
	                <i class="layui-icon">&#xe615;</i>查询
	            </button>
	        </div>
	    </div>
	</blockquote>
	
    <table class="layui-hide" id="numberGatewayTable" lay-filter="numberGatewayTable"></table>
	
	<script type="text/html" id="tableToolbar">
  		<div class="layui-btn-container">
  			<div class="layui-btn-group">
	    		<button class="layui-btn layui-btn-sm" lay-event="addNumberGateway"><i class="layui-icon">&#xe654;</i>增加</button>
	    		<button class="layui-btn layui-btn-sm" lay-event="editNumberGateway"><i class="layui-icon">&#xe642;</i>修改</button>
	    		<button class="layui-btn layui-btn-sm" lay-event="delNumberGateway"><i class="layui-icon">&#xe640;</i>删除</button>
    		</div>
  		</div>
	</script>
	
    <script type="text/html" id="dateTem">
        {{ timestampToTime(d.createTime) }}
    </script>
    <script type="text/html" id="numberNameTem">
        {{ d.numberPool.numberName }}
    </script>
    <script type="text/html" id="callNumTem">
        {{ d.numberPool.callNum }}
    </script>
    <script type="text/html" id="callNumTem">
        {{ d.numberPool.callNum }}
    </script>
    <script type="text/html" id="gatewayNameTem">
        {{ d.gateway.name }}
    </script>
    <script type="text/html" id="isShareTem">
		{{# if (d.gateway.isShare === 1) { }}
			<span class="layui-badge">共享线路</span> 
		{{# } else { }}
			<span class="layui-badge layui-bg-blue">私有线路</span>
		{{# } }}
    </script>
	<script type="text/html" id="isFixedTem">
		{{# if (d.isFixed === 0) { }}
			<span class="layui-badge layui-bg-orange">否</span>
		{{# } else { }}
			<span class="layui-badge">是</span>
		{{# } }}
	</script>

    <script>
        var table = layui.table;
        var $ = layui.jquery;
        var tableInit = table.render({
            elem: '#numberGatewayTable'
            , url: '${baseURL}/numberGateway/list'
            , toolbar: '#tableToolbar'
            , defaultToolbar :  ['filter', 'print']
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            	callNum: $('#callNum').val()
            	, gatewayName: $('#gatewayName').val()
            }
            , cols: [[
                {type: 'checkbox', width: '5%'}
               // , {field: 'numberName', width: '15%', title: '分机名称',templet:'#numberNameTem'}
                , {field: 'callNum', width: '15%', title: '分机号码',templet:'#callNumTem'}
                , {field: 'gatewayName', width: '20%', title: '线路',templet:'#gatewayNameTem'}
                , {field: 'isShare', width: '30%', title: '线路所属',templet:'#isShareTem'}
                , {field: 'isFixed', title: '是否固定线路',templet:'#isFixedTem'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'numberGatewayTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                	callNum: $('#callNum').val()
                	, gatewayName: $('#gatewayName').val()
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

        $('#searchNumberGateway').on('click', function () {
            reloadTable(1);
        });
        
        table.on('toolbar(numberGatewayTable)', function (obj) {
        	var checkStatus = table.checkStatus(obj.config.id);
        	var data = checkStatus.data;
        	if (obj.event === 'addNumberGateway') {
        		openWin('添加内线', '${baseURL}/numberGateway/addUI', function () {
                	$("button.layui-laypage-btn").click();
            	});
            } else if (obj.event === 'editNumberGateway') {
		        var checkStatus = table.checkStatus('numberGatewayTableId'), data = checkStatus.data;
	        	if (data.length != 1) {
	            	parent.parent.layer.alert('请选择一条编辑数据');
	        	} else {
	        		editNumberGateway(data[0])
       	 		}
            } else if (obj.event === 'delNumberGateway') {
	            var checkStatus = table.checkStatus('numberGatewayTableId'), data = checkStatus.data;
            	if (data.length != 1) {
                	parent.parent.layer.alert('请选择一条数据');
            	} else {
                	delNumberGateway(data[0])
            	}
            } 
        });

      	function editNumberGateway(data){
      		openWin('修改内线', '${baseURL}/numberGateway/editUI?id=' + data.id, function () {
                $("button.layui-laypage-btn").click();
            });
      	}

        //删除申请
        function delNumberGateway(data) {
            var deleteData = {
                id : data.id
            }
            parent.parent.layer.confirm('确认删除么', function (index) {
                var url = "${baseURL}/numberGateway/delete";
                post(url, deleteData, function (data) {
                    $("button.layui-laypage-btn").click();
                    parent.parent.layer.close(index);
                    parent.parent.layer.msg('删除成功', {
                        icon: 1,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    });
                }, function (data) {
                    parent.parent.layer.alert(data.msg, {
                        icon: 2
                    });
                });
            });
        }


    </script>
</div>
