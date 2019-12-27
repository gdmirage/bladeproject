<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/common/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<blockquote class="layui-elem-quote" style="padding-bottom: 1px;">
	    <div class="layui-form">
	        <div class="layui-form-item">
	            <label class="layui-form-label">名称：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="telecomOperatorName" autocomplete="off" class="layui-input">
	            </div>
	
	            <button class="layui-btn" id="searchTelecomOperator">
	                <i class="layui-icon">&#xe615;</i>查询
	            </button>
	        </div>
	    </div>
	</blockquote>
	
    <table class="layui-hide" id="telecomOperatorTable" lay-filter="telecomOperatorTable"></table>
	
	<script type="text/html" id="tableToolbar">
  		<div class="layui-btn-container">
  			<div class="layui-btn-group">
	    		<button class="layui-btn layui-btn-sm" lay-event="addTelecomOperator"><i class="layui-icon">&#xe654;</i>增加</button>
	    		<button class="layui-btn layui-btn-sm" lay-event="editTelecomOperator"><i class="layui-icon">&#xe642;</i>修改</button>
	    		<button class="layui-btn layui-btn-sm" lay-event="delTelecomOperator"><i class="layui-icon">&#xe640;</i>删除</button>
    		</div>
  		</div>
	</script>
	
    <script type="text/html" id="dateTem">
        {{ timestampToTime(d.createAt) }}
    </script>


    <script>
        var table = layui.table;
        var $ = layui.jquery;
        var tableInit = table.render({
            elem: '#telecomOperatorTable'
            , url: '${baseURL}/telecomOperator/list'
            , toolbar: '#tableToolbar'
            , defaultToolbar :  ['filter', 'print']
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            	telecomOperatorName: $('#telecomOperatorName').val()
            }
            , cols: [[
                {type: 'checkbox', width: '5%'}
                , {field: 'id', width: '5%', title: 'ID'}
                , {field: 'telecomOperatorName', width: '30%', title: '运营商名称'}
                , {field: 'description', width: '40%', title: '运营商描述'}
                , {field: 'createAt',  title: '创建时间',templet:'#dateTem'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'telecomOperatorTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                	telecomOperatorName: $('#telecomOperatorName').val()
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

        $('#searchTelecomOperator').on('click', function () {
            reloadTable(1);
        });
        
        table.on('toolbar(telecomOperatorTable)', function (obj) {
        	var checkStatus = table.checkStatus(obj.config.id);
        	var data = checkStatus.data;
        	if (obj.event === 'addTelecomOperator') {
        		openWin('新增', '${baseURL}/telecomOperator/addUI', function () {
                	reloadTable(1);
            	});
            } else if (obj.event === 'editTelecomOperator') {
		        var checkStatus = table.checkStatus('telecomOperatorTableId'), data = checkStatus.data;
	        	if (data.length != 1) {
	            	parent.parent.layer.alert('请选择一条编辑数据');
	        	} else {
	        		editTelecomOperator(data[0])
       	 		}
            } else if (obj.event === 'delTelecomOperator') {
	            var checkStatus = table.checkStatus('telecomOperatorTableId'), data = checkStatus.data;
            	if (data.length != 1) {
                	parent.parent.layer.alert('请选择一条数据');
            	} else {
                	delTelecomOperator(data[0])
            	}
            } 
        });

      	function editTelecomOperator(data){
      		openWin('修改', '${baseURL}/telecomOperator/editUI?id=' + data.id, function () {
                reloadTable(1);
            });
      	}

        //删除申请
        function delTelecomOperator(data) {
            var deleteData = {
                id : data.id
            }
            parent.parent.layer.confirm('确认删除么', function (index) {
                var url = "${baseURL}/telecomOperator/delete";
                post(url, deleteData, function (data) {
                    reloadTable();
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
