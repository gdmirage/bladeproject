<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
	    <div class="layui-form">
	        <div class="layui-form-item">
	            <label class="layui-form-label">规则名称：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="name" autocomplete="off" class="layui-input">
	            </div>
	
	            <button class="layui-btn" id="searchDialplanGateway">
	                <i class="layui-icon">&#xe615;</i>查询
	            </button>
	        </div>
	    </div>
	</blockquote>
	
    <table class="layui-hide" id="dialplanGatewayTable" lay-filter="dialplanGatewayTable"></table>
	
	<script type="text/html" id="tableToolbar">
  		<div class="layui-btn-container">
  			<div class="layui-btn-group">
	    		<button class="layui-btn layui-btn-sm" lay-event="addDialplanGateway"><i class="layui-icon">&#xe654;</i>增加</button>
	    		<button class="layui-btn layui-btn-sm" lay-event="editDialplanGateway"><i class="layui-icon">&#xe642;</i>修改</button>
	    		<button class="layui-btn layui-btn-sm" lay-event="delDialplanGateway"><i class="layui-icon">&#xe640;</i>删除</button>
    		</div>
  		</div>
	</script>
	
    <script type="text/html" id="dateTem">
        {{ timestampToTime(d.createTime) }}
    </script>


    <script>
        var table = layui.table;
        var $ = layui.jquery;
        var tableInit = table.render({
            elem: '#dialplanGatewayTable'
            , url: '${baseURL}/dialplanGateway/list'
            , toolbar: '#tableToolbar'
            , defaultToolbar :  ['filter', 'print']
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            	name: $('#name').val()
            }
            , cols: [[
                {type: 'checkbox', width: '5%'}
                , {field: 'id', width: '15%', title: '序号'}
                , {field: 'name', width: '20%', title: '规则名称'}
                , {field: 'localMobile', width: '15%', title: '本地手机前缀'}
                , {field: 'nonlocalMobile', width: '15%', title: '外地手机前缀'}
                , {field: 'localLandline', width: '15%', title: '本地座机前缀'}
                , {field: 'nonlocalLandline',  title: '外地座机前缀'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'dialplanGatewayTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                	name: $('#name').val()
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

        $('#searchDialplanGateway').on('click', function () {
            reloadTable(1);
        });
        
        table.on('toolbar(dialplanGatewayTable)', function (obj) {
        	var checkStatus = table.checkStatus(obj.config.id);
        	var data = checkStatus.data;
        	if (obj.event === 'addDialplanGateway') {
        		openWin('新增', '${baseURL}/dialplanGateway/addUI', function () {
                	$("button.layui-laypage-btn").click();
            	});
            } else if (obj.event === 'editDialplanGateway') {
		        var checkStatus = table.checkStatus('dialplanGatewayTableId'), data = checkStatus.data;
	        	if (data.length != 1) {
	            	parent.parent.layer.alert('请选择一条编辑数据');
	        	} else {
	        		editDialplanGateway(data[0])
       	 		}
            } else if (obj.event === 'delDialplanGateway') {
	            var checkStatus = table.checkStatus('dialplanGatewayTableId'), data = checkStatus.data;
            	if (data.length != 1) {
                	parent.parent.layer.alert('请选择一条数据');
            	} else {
                	delDialplanGateway(data[0])
            	}
            } 
        });

      	function editDialplanGateway(data){
      		openWin('修改', '${baseURL}/dialplanGateway/editUI?id=' + data.id, function () {
                $("button.layui-laypage-btn").click();
            });
      	}

        //删除申请
        function delDialplanGateway(data) {
            var deleteData = {
                id : data.id
            }
            parent.parent.layer.confirm('确认删除么', function (index) {
                var url = "${baseURL}/dialplanGateway/delete";
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
