<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../../common/taglib/taglib.jsp" %>

<style>
    .layui-upload-img {
        width: 60px;
        height: 60px;
    }

    .layui-table-cell {
        height: 60px;
        line-height: 60px;
    }
    .layui-badge{
    	    margin-top: 20px !important;
    }
    .layui-table-view .layui-table td{
    	height: 60px;
    }
    
    th div{
    	height:auto !important;
    	line-height: 28px !important;
    }
    
</style>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    <div class="layui-form">
        <div class="layui-form-item">
        	<blockquote class="layui-elem-quote">
        		<div class="layui-input-inline">
					<select name="typeOneId" id="typeOneId" lay-filter="parentType">
						<option value="">请选择一级分类</option>
						<c:forEach var="item" items="${oneTypeList }">
							<option value="${item.id }">${item.oneTypeName }</option>
						</c:forEach>
					</select> 
			    </div>
        		<label class="layui-form-label">分类名称：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="twoTypeName" autocomplete="off" class="layui-input">
	            </div>
	            <button class="layui-btn" id="searchArticleTypeTwo">
	                <i class="layui-icon">&#x1002;</i>查询
	            </button>
	            <button class="layui-btn" id="addArticleTypeTwo">
	                <i class="layui-icon">&#xe654;</i>新增
	            </button>
            </blockquote>
        </div>
    </div>

    <table class="layui-hide" id="articleTypeTwoTable" lay-filter="articleTypeTwoTable"></table>

	<script type="text/html" id="articleTypeTwoBar">
		{{# if (d.isValid === 'YES') { }}
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="soldOut" style="margin-top: 20px;">失效</a> 
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
    <script type="text/html" id="isShowTem">
        {{# if (d.isShow === 'YES') { }}
            <span class="layui-badge layui-bg-green">是</span>
        {{# } else { }}
            <span class="layui-badge">否</span>
        {{# } }}
    </script>
	<script type="text/html" id="iconImgTem">
        <img class="layui-upload-img " src="{{d.typeImg}}">
   </script>
        
    <script>
        var table = layui.table; 
        var $ = layui.jquery;
        var form = layui.form;
        form.render();
        var tableInit = table.render({
            elem: '#articleTypeTwoTable'
            , url: '${baseURL}/articleTypeTwo/list/'
            , request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
            }
            , where: {
            	typeOneId: $('#typeOneId').val(),
            	twoTypeName: $('#twoTypeName').val()
            }
            , cols: [[
                {field:'', width:'20%', title: '图标', templet: '#iconImgTem'}
                , {field: 'oneTypeName', width: '15%', title: '一级分类'}
                , {field: 'twoTypeName', width: '15%', title: '二级分类'}
                , {field: 'typeIndex', width: '9%', title: '排序号'}
                , {field: 'isShow', width: '10%', title: '是否显示', templet: '#isShowTem'}
                , {field: 'isValid', width: '10%', title: '是否生效', templet: '#isValidTem'}
                , {field: 'toolbar', title: '操作',width: '20%',toolbar: '#articleTypeTwoBar' , fixed: 'right'}
            ]]
            , limits: [5, 10, 20, 50]//每页数据选择项
            , limit: 10 //默认采用10
            , page: true
            , id: 'articleTypeTwoTableId'
        });

        //刷新table，带上搜索框条件
        function reloadTable(page) {
            //定义重载table参数
            var loadData = {
                where: {
                	typeOneId: $('#typeOneId').val(),
                	twoTypeName: $('#twoTypeName').val()
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
		
        $('#searchArticleTypeTwo').on('click', function () {
            reloadTable(1);
        });


        //新增
        $('#addArticleTypeTwo').click(function () {
            openWin('添加文章二级分类', '${baseURL}/articleTypeTwo/addUI/', function () {
                reloadTable(1);
            });
        })
        
        //监控操作按钮
        table.on('tool(articleTypeTwoTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
            	editArticleTypeTwo(data);
            } else if (obj.event === 'del') {
            	delArticleTypeTwo(data);
            } else if (obj.event === 'soldOut') {
            	soldOut(data);
            } else if (obj.event === 'shelves') {
            	shelves(data);
            }
        });
        
        //修改
		function editArticleTypeTwo(data){
			openWin('修改文章二级分类','${baseURL}/articleTypeTwo/editUI?id=' + data.id,function(){
				reloadTable(1);
			});
		}
		
		//删除
		function delArticleTypeTwo(data){
			var deleteData = {
				id : data.id
		    }
			parent.parent.layer.confirm('确认删除分类【' + data.twoTypeName + '】么', function(index){
				var url = "${baseURL}/articleTypeTwo/delete";
				post(url,deleteData,function(data){
					reloadTable();
					parent.parent.layer.close(index);
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
				articleTypeTwoId : data.id
		    }
			parent.parent.layer.confirm('确认设置分类【' + data.twoTypeName + '】为失效', function(index){
				var url = "${baseURL}/articleTypeTwo/soldOutArticleTypeTwo";
				post(url,deleteData,function(data){
					reloadTable();
					parent.parent.layer.close(index);
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
				articleTypeTwoId : data.id
		    }
			parent.parent.layer.confirm('确认设置分类【' + data.twoTypeName + '】为生效', function(index){
				var url = "${baseURL}/articleTypeTwo/shelvesArticleTypeTwo";
				post(url,deleteData,function(data){
					reloadTable();
					parent.parent.layer.close(index);
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
