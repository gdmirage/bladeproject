<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../../static/common/taglib/taglib.jsp" %>
<style>
    .layui-upload-img {
        width: 60px;
        height: 60px;
    }

</style>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
	<input id="copyUrl" type="hidden" value="" />
    <div class="layui-form">
        <div class="layui-form-item">
        	<blockquote class="layui-elem-quote">
	            <label class="layui-form-label">活动标题：</label>
	            <div class="layui-input-inline">
	                <input type="text" id="articleTitle" autocomplete="off" class="layui-input">
	            </div>
				<button class="layui-btn" id="searchArticle">
					<i class="layui-icon">&#x1002;</i>查询
				</button>
				<button class="layui-btn" id="addArticle">
	               <i class="layui-icon">&#xe654;</i>新增
	           </button>
           </blockquote>
        </div>
        
        
        <table class="layui-hide" id="articleTable" lay-filter="articleTable"></table>

        <script type="text/html" id="imgTmp">
            <img class="layui-upload-img " src="{{d.picPath}}">
        </script>

        <script type="text/html" id="isTopTmp">
            {{# if (d.isTop === 'YES') { }}
            	<span class="layui-badge layui-bg-green">是</span>
            {{# } else { }}
            	<span class="layui-badge">否</span>
            {{# } }}
        </script>
        
        <script type="text/html" id="isSellTmp">
            {{# if (d.isSell === 'YES') { }}
            	<span class="layui-badge layui-bg-green">是</span>
            {{# } else { }}
            	<span class="layui-badge">否</span>
            {{# } }}
        </script>
        <script type="text/html" id="isValidTmp"> 
			{{# if (d.isValid === 'YES') { }}
            	<span class="layui-badge layui-bg-green">已上架</span>
            {{# } else { }}
            	<span class="layui-badge">未上架</span>
            {{# } }}
        </script>
        
        <script type="text/html" id="typeTmp"> 
			<span style="color:red;">【{{d.productLevelName}}】</span>
			<span>{{ d.typeName }}</span>
        </script>
        
		<script type="text/html" id="courseColumnBar"> 
			{{# if (d.isValid === 'NO') { }}
				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="editArticle">编辑</a>
				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="shelvesArticle">上架</a>
				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="del">删除</a>
			{{#	} else {	}}
				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="soldOutArticle">下架</a>
				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="view">查看</a>
			{{#	}	}}
			
    	</script>
    	
        <script>
            var table = layui.table;
            var $ = layui.jquery;
            var form = layui.form;
            form.render();
            
            var tableInit = table.render({
                elem: '#articleTable'
                , url: '${baseURL}/article/list'
                , request: {
                    pageName: 'pageNum' //页码的参数名称，默认：page
                    , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
                }
                , where: {
                	articleTitle: $('#articleTitle').val()
                    , authorName: $('#authorName').val()
                    , typeId: $('#typeId').val()
                }
                , cols: [[ 
                    { type: 'numbers',  title: '序号', width : '3%' }
                    , {field: 'articleTitle', width: '33%', title: '活动标题'}
                    , {field: 'articleSubtitle', width: '40%', title: '简要描述'}
                    , {field: '', width: '9%', title: '是否上架显示', templet: '#isValidTmp'}
                    , {field: 'toolbar', title: '操作',width: '14%',toolbar: '#courseColumnBar' , fixed: 'right'}
                ]]
                , limits: [5, 10, 20, 50]//每页数据选择项
                , limit: 20 //默认采用10
                , page: true
                , id: "articleTableId"
            });

            //刷新table，带上搜索框条件
            function reloadTable(page) {
                var loadData = {
                    where: {
                    	articleTitle: $('#articleTitle').val()
                        , authorName: $('#authorName').val()
                        , typeId: $('#typeId').val()
                    }
                };
                if (page) {
                    loadData.page = {
                        curr: page //重新从第page页开始
                    }
                }
                
                tableInit.reload(loadData);
            }


            $('#searchArticle').on('click', function () {
                reloadTable(1);
            });


            //新增
            $('#addArticle').click(function () {
                openWin('新增', '${baseURL}/article/addUI/', function () {
                    reloadTable(1);
                });
            })
			
            //监控操作按钮
	        table.on('tool(articleTable)', function (obj) {
	            var data = obj.data;
	            if (obj.event === 'editArticle') {
	            	editArticle(data);
	            } else if (obj.event === 'shelvesArticle') {
	            	shelvesArticle(data);
	            } else if (obj.event === 'soldOutArticle') {
	            	soldOutArticle(data);
	            } else if (obj.event === 'view') {
	            	viewArticle(data);
	            } else if (obj.event === 'del') {
	            	delArticle(data);
	            } else if (obj.event === 'push') {
	            	openWin('【' + data.articleTitle + '】的智能推荐','${baseURL}/articlePushByArticle/listUI?selfArticleId=' + data.id,function(){
	    			});
	            } else if (obj.event === 'preView') {
	            	parent.parent.layer.closeAll();
	            	// $('#urlDiv').html('http://www.baidu.com?1=111');
	            	// $('#qrcodeImg').attr('src',"${baseURL}/article/preview?id=" + data.id);
	            	parent.parent.layer.open({
	                    type: 2
	                    ,offset: 'auto'
	                    ,area: ['300px', '350px']
	                    ,id: 'showUrl' //防止重复弹出
	                    ,content: "${baseURL}/article/qrcode?id=" + data.id
	                    ,btn: '一键复制链接'
	                    ,btnAlign: 'c' //按钮居中
	                    ,shade: 0.3 //不显示遮罩
	                    ,yes: function(){
	                    	$('#copyUrl').val('${htmlUrl}article_detail.html?articleId=' + data.id);
	                    	var s = '${htmlUrl}article_detail.html?articleId=' + data.id;
	                    	if(window.clipboardData){
                    	      window.clipboardData.setData('text',s);
                    	   	}else{
                    	      document.oncopy=function(e){
                    	         e.clipboardData.setData('text',s);
                    	         e.preventDefault();
                    	         document.oncopy=null;
                    	      }
                    	      document.execCommand('Copy');
                    	   	}
	                    	parent.parent.layer.closeAll();
	                    	parent.parent.layer.msg('复制成功');
	                    }
	                });
	            }
	        });
            
          	//修改
    		function editArticle(data){
    			openWin('编辑','${baseURL}/article/editUI?id=' + data.id,function(){
    				reloadTable(1);
    			});
    		}
    		
    		//删除
    		function delArticle(data){
    			var deleteData = {
    				id : data.id
    		    }
    			parent.parent.layer.confirm('确认删除推文【' + data.articleTitle + '】么', function(index){
    				var url = "${baseURL}/article/delete";
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
    		
    		//上架
    		function shelvesArticle(data){
    			var deleteData = {
    					articleId : data.id
    		    }
    			parent.parent.layer.confirm('确认上架推文【' + data.articleTitle + '】么', function(index){
    				var url = "${baseURL}/article/shelvesArticle";
    				post(url,deleteData,function(data){
    					reloadTable();
    					parent.parent.layer.close(index);
    					parent.parent.layer.msg('上架成功',{
    	  				  icon: 1,
    	  				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
    	  				});
    	  			},function(data){
    	  				parent.parent.layer.msg(data.msg,{
    					  icon: 2,
    					  time: 2000 //2秒关闭（如果不配置，默认是3秒）
    					});
    	  			});
    			});
    		}
    		
    		//下架
    		function soldOutArticle(data){
    			var deleteData = {
    					articleId : data.id
    		    }
    			parent.parent.layer.confirm('确认下架推文【' + data.articleTitle + '】么', function(index){
    				var url = "${baseURL}/article/soldOutArticle";
    				post(url,deleteData,function(data){
    					reloadTable();
    					parent.parent.layer.close(index);
    					parent.parent.layer.msg('下架成功',{
    	  				  icon: 1,
    	  				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
    	  				});
    	  			},function(data){
    	  				parent.parent.layer.alert(data.msg,{
    					  icon: 2,
    					  time: 2000 //2秒关闭（如果不配置，默认是3秒）
    					});
    	  			});
    			});
    		}
            
    		//查看
            function viewArticle(data){
            	layer.open({
                    type: 2 //iframe
                    , title: "查看"
                    , area: ['100%', '100%']
                    , fixed: true  //不固定
                    , offset: '0px'
                    , shade: 0
                    , content: "${baseURL}/article/viewUI?id=" + data.id
                    , zIndex: layer.zIndex //重点1
                    , success: function (layero) {
                        layer.setTop(layero); 	//弹窗置顶
                    }
                    , end: function (index, layero) {
                    }
                });
            }

        </script>
    </div>
    
</div>