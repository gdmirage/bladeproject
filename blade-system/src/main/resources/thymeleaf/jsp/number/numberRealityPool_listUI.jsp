<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
	    <div class="layui-form">
	        <div class="layui-form-item">
	            <label class="layui-form-label">外显号码：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="realityNum" autocomplete="off" class="layui-input">
	            </div>
				
				<label class="layui-form-label">区号：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="areaCode" autocomplete="off" class="layui-input">
	            </div>
	            
				<label class="layui-form-label">运营商：</label>
	            <div class="layui-input-inline">
	                <select name="telecomOperatorId" id="telecomOperatorId">
						<option value="">请选择</option>
						<c:forEach var="item" items="${telecomOperatorList }">
							<option value="${item.id }">${item.telecomOperatorName }</option>
						</c:forEach>
					</select>
	            </div>
	            
	            <label class="layui-form-label">当前状态：</label>
	            <div class="layui-input-inline">
	                <select name="status" id="status">
						<option value="">请选择</option>
						<c:forEach var="item" items="${numberRealityStatusList }">
							<option value="${item.value }">${item.desc }</option>
						</c:forEach>
					</select>
	            </div>
	            
	            <button class="layui-btn" id="searchNumberRealityPool">
	                <i class="layui-icon">&#xe615;</i>查询
	            </button>
	        </div>
	    </div>
	</blockquote>
	
    <table class="layui-hide" id="numberRealityPoolTable" lay-filter="numberRealityPoolTable"></table>
	
	<script type="text/html" id="tableToolbar">
  		<div class="layui-btn-container">
  			<div class="layui-btn-group">
	    		<button class="layui-btn layui-btn-sm" lay-event="addNumberRealityPool"><i class="layui-icon">&#xe654;</i>增加</button>
	    		<button class="layui-btn layui-btn-sm" lay-event="editNumberRealityPool"><i class="layui-icon">&#xe642;</i>修改</button>
	    		<button class="layui-btn layui-btn-sm" lay-event="delNumberRealityPool"><i class="layui-icon">&#xe640;</i>删除</button>
    		</div>
  		</div>
	</script>
	
    <script type="text/html" id="dateTem">
        {{ timestampToTime(d.createdAt) }}
    </script>
    <script type="text/html" id="statusTem">
        {{# if (d.status === 'leisure') { }}
			<span class="layui-badge layui-bg-blue">空闲中</span>
		{{# } else { }}
			<span class="layui-badge" layui-bg-cyan>通话中</span>
		{{# } }}
    </script>


    <script>
        var table = layui.table;
        var $ = layui.jquery;
        var form = layui.form;
        form.render();
        
        var tableInit = table.render({
            elem: '#numberRealityPoolTable'
            , url: '${baseURL}/numberRealityPool/list'
            , toolbar: '#tableToolbar'
            , defaultToolbar :  ['filter', 'print']
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            	realityNum: $('#realityNum').val()
            	, areaCode: $('#areaCode').val()
            	, telecomOperatorId: $('#telecomOperatorId').val()
            	, status: $('#status').val()
            }
            , cols: [[
                {type: 'checkbox', width: '5%'}
                , {field: 'realityNum', width: '20%', title: '外显号码'}
                , {field: 'areaCode', width: '20%', title: '区号'}
                , {field: 'telecomOperatorName', width: '20%', title: '所属运营商'}
                , {field: 'status', width: '20%', title: '当前状态',templet:'#statusTem'}
                , {field: 'createdAt', title: '创建时间',templet:'#dateTem'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'numberRealityPoolTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                	realityNum: $('#realityNum').val()
                	, areaCode: $('#areaCode').val()
                	, telecomOperatorId: $('#telecomOperatorId').val()
                	, status: $('#status').val()
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

        $('#searchNumberRealityPool').on('click', function () {
            reloadTable(1);
        });
        
        table.on('toolbar(numberRealityPoolTable)', function (obj) {
        	var checkStatus = table.checkStatus(obj.config.id);
        	var data = checkStatus.data;
        	if (obj.event === 'addNumberRealityPool') {
        		openWin('新增', '${baseURL}/numberRealityPool/addUI', function () {
        			$("button.layui-laypage-btn").click();
                	// reloadTable(1);
            	});
            } else if (obj.event === 'editNumberRealityPool') {
		        var checkStatus = table.checkStatus('numberRealityPoolTableId'), data = checkStatus.data;
	        	if (data.length != 1) {
	            	parent.parent.layer.alert('请选择一条编辑数据');
	        	} else {
	        		editNumberRealityPool(data[0])
       	 		}
            } else if (obj.event === 'delNumberRealityPool') {
	            var checkStatus = table.checkStatus('numberRealityPoolTableId'), data = checkStatus.data;
            	if (data.length != 1) {
                	parent.parent.layer.alert('请选择一条数据');
            	} else {
                	delNumberRealityPool(data[0])
            	}
            } 
        });

      	function editNumberRealityPool(data){
      		openWin('修改', '${baseURL}/numberRealityPool/editUI?id=' + data.id, function () {
      			$("button.layui-laypage-btn").click();
            });
      	}

        //删除申请
        function delNumberRealityPool(data) {
            var deleteData = {
                id : data.id
            }
            parent.parent.layer.confirm('确认删除么', function (index) {
                var url = "${baseURL}/numberRealityPool/delete";
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
