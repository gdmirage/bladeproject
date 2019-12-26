<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../../../static/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    <div class="layui-form">
        <div class="layui-form-item">
        	<blockquote class="layui-elem-quote">
	            <div class="layui-input-inline">
					<select name="typeOneId" id="typeOneId" lay-filter="parentType">
						<option value="">请选择</option>
						<c:forEach var="item" items="${list }">
							<option value="${item.id }">${item.typeName }</option>
						</c:forEach>
					</select> 
			    </div>
	            <div class="layui-input-inline">
	                <input type="text" id="typeName" autocomplete="off" placeholder="请输入分类名称" class="layui-input">
	            </div>
	            <button class="layui-btn" id="searchProductType">
	                <i class="layui-icon">&#x1002;</i>查询
	            </button>
	            <button class="layui-btn" id="addProductType">
	                <i class="layui-icon">&#xe654;</i>新增
	            </button>
            </blockquote>
        </div>
    </div>

    <table class="layui-hide" id="productTypeTable" lay-filter="productTypeTable"></table>

	<script type="text/html" id="productTypeBar">
		{{# if (d.isValid === 'YES') { }}
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="soldOut">失效</a>
        {{# } else { }}
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">编辑</a>
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="shelves">生效</a>
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="del">删除</a>
        {{# } }}
    </script>

    <script type="text/html" id="dateTem">
        {{ timestampToTime(d.createTime) }}
    </script>
	<script type="text/html" id="isValidTem">
        {{# if (d.isValid === 'YES') { }}
            <span class="layui-badge layui-bg-green">已生效</span>
        {{# } else { }}
            <span class="layui-badge">未生效</span>
        {{# } }}
    </script>
    
    <script>
        var table = layui.table; 
        var $ = layui.jquery;
        var form = layui.form;
        form.render();
        var tableInit = table.render({
            elem: '#productTypeTable'
            , url: '${baseURL}/productType/list/'
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            	typeOneId: $('#typeOneId').val()
            	,typeName: $('#typeName').val()
            }
            , cols: [[
                // ,{field:'id', width:'10%', title: 'id'}
                {field: 'oneTypeName', width: '20%', title: '一级分类'}
                , {field: 'typeName', width: '20%', title: '分类名称'}
                , {field: 'isValid', width: '20%', title: '是否生效', templet: '#isValidTem'}
                , {field: 'creater',  width: '10%',title: '创建人'}
                , {field: 'createTime',  width: '15%',title: '创建时间', templet: '#dateTem'}
                , {field: 'toolbar', title: '操作',toolbar: '#productTypeBar' , fixed: 'right'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'productTypeTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                	typeOneId: $('#typeOneId').val()
                	,typeName: $('#typeName').val()
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
		
        form.on('select(parentType)', function(data){
	   		 var type = data.value;
	   		reloadTable(1);
	   	});
        
        $('#searchProductType').on('click', function () {
            reloadTable(1);
        });


        //新增
        $('#addProductType').click(function () {
            openWin('添加分类', '${baseURL}/productType/addUI/', function () {
                reloadTable(1);
            });
        })
        
        //监控操作按钮
        table.on('tool(productTypeTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
            	editProductType(data);
            } else if (obj.event === 'del') {
            	delProductType(data);
            } else if (obj.event === 'soldOut') {
            	soldOut(data);
            } else if (obj.event === 'shelves') {
            	shelves(data);
            }
        });
        
        //修改
		function editProductType(data){
			openWin('修改分类','${baseURL}/productType/editUI?id=' + data.id,function(){
				reloadTable(1);
			});
		}
		
		//删除
		function delProductType(data){
			var deleteData = {
				id : data.id
		    }
			layer.confirm('确认删除分类【' + data.parentTypeName + ' -> ' + data.typeName + '】么', function(index){
				var url = "${baseURL}/productType/delete";
				post(url,deleteData,function(data){
					reloadTable();
					layer.close(index);
					parent.parent.layer.msg('删除成功',{
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
		
		//失效
		function soldOut(data){
			var deleteData = {
				id : data.id
		    }
			layer.confirm('确认设置分类【' + data.typeName + '】为失效', function(index){
				var url = "${baseURL}/productType/soldOutProductType";
				post(url,deleteData,function(data){
					reloadTable();
					layer.close(index);
					parent.parent.layer.msg('操作成功',{
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
		
		//生效
		function shelves(data){
			var deleteData = {
				id : data.id
		    }
			layer.confirm('确认设置分类【' + data.typeName + '】为生效', function(index){
				var url = "${baseURL}/productType/shelvesProductType";
				post(url,deleteData,function(data){
					reloadTable();
					layer.close(index);
					parent.parent.layer.msg('操作成功',{
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

    </script>
</div>
