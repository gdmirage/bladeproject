<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../../../static/taglib/taglib.jsp" %>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    <div class="layui-form">
        <div class="layui-form-item">
        	<blockquote class="layui-elem-quote">
        		<label class="layui-form-label">分类名称：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="oneTypeName" autocomplete="off" class="layui-input">
	            </div>
	            <button class="layui-btn" id="searchArticleTypeOne">
	                <i class="layui-icon">&#x1002;</i>查询
	            </button>
	            <button class="layui-btn" id="addArticleTypeOne">
	                <i class="layui-icon">&#xe654;</i>新增
	            </button>
            </blockquote>
        </div>
    </div>

    <table class="layui-hide" id="articleTypeOneTable" lay-filter="articleTypeOneTable"></table>

	<script type="text/html" id="articleTypeOneBar">
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
            elem: '#articleTypeOneTable'
            , url: '${baseURL}/articleTypeOne/list/'
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            	oneTypeName: $('#oneTypeName').val()
            }
            , cols: [[
                // ,{field:'id', width:'10%', title: 'id'}
                {field: 'oneTypeName', width: '20%', title: '分类名称'}
                , {field: 'typeIndex', width: '9%', title: '排序号'}
                , {field: 'isValid', width: '10%', title: '是否生效', templet: '#isValidTem'}
                , {field: 'creater',  width: '20%',title: '创建人'}
                , {field: 'createTime',  width: '20%',title: '创建时间', templet: '#dateTem'}
                , {field: 'toolbar', title: '操作',width: '20%',toolbar: '#articleTypeOneBar' , fixed: 'right'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'articleTypeOneTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                	oneTypeName: $('#oneTypeName').val()
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
		
        $('#searchArticleTypeOne').on('click', function () {
            reloadTable(1);
        });


        //新增
        $('#addArticleTypeOne').click(function () {
            openWin('添加文章一级分类', '${baseURL}/articleTypeOne/addUI/', function () {
                reloadTable(1);
            });
        })
        
        //监控操作按钮
        table.on('tool(articleTypeOneTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
            	editArticleTypeOne(data);
            } else if (obj.event === 'del') {
            	delArticleTypeOne(data);
            } else if (obj.event === 'soldOut') {
            	soldOut(data);
            } else if (obj.event === 'shelves') {
            	shelves(data);
            }
        });
        
        //修改
		function editArticleTypeOne(data){
			openWin('修改文章一级分类','${baseURL}/articleTypeOne/editUI?id=' + data.id,function(){
				reloadTable(1);
			});
		}
		
		//删除
		function delArticleTypeOne(data){
			var deleteData = {
				id : data.id
		    }
			layer.confirm('确认删除分类【' + data.oneTypeName + '】么', function(index){
				var url = "${baseURL}/articleTypeOne/delete";
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
				articleTypeOneId : data.id
		    }
			layer.confirm('确认设置分类【' + data.oneTypeName + '】为失效', function(index){
				var url = "${baseURL}/articleTypeOne/soldOutArticleTypeOne";
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
				articleTypeOneId : data.id
		    }
			layer.confirm('确认设置分类【' + data.oneTypeName + '】为生效', function(index){
				var url = "${baseURL}/articleTypeOne/shelvesArticleTypeOne";
				post(url,deleteData,function(data){
					reloadTable();
					layer.close(index);
					parent.parent.layer.msg('操作成功',{
	  				  icon: 1,
	  				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
	  				});
	  			},function(data){
	  				layer.close(index);
	  				parent.parent.layer.alert(data.msg,{
					  icon: 2
					});
	  			});
			});
		}

    </script>
</div>
