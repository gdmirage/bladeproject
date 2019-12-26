<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../../static/taglib/taglib.jsp" %>
<style>
    .layui-upload-img {
        width: 60px;
        height: 60px;
    }

</style>

<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    <div class="layui-form">
        <div class="layui-form-item">
        	<blockquote class="layui-elem-quote">
        		<div class="layui-input-inline">
	            	<select name="columnTypeId" id="columnTypeId">
	            		<option value="">请选择专栏分类</option>
						<c:forEach var="item" items="${productTypeList }">
							<option value="${item.id }">${item.oneTypeName } -> ${item.typeName }</option>
						</c:forEach>
					</select>
	            </div>
	            <div class="layui-input-inline">
	            	<select name="productLevelCode" id="productLevelCode">
	            		<option value="">请选择等级</option>
						<c:forEach var="item" items="${productLevelList }">
							<option value="${item.levelCode }">${item.levelName }</option>
						</c:forEach>
					</select> 
	            </div>
	            <div class="layui-input-inline">
	                <input type="text" id="authorName" autocomplete="off" class="layui-input" placeholder="请输入作者姓名" >
	            </div>
	            <div class="layui-input-inline">
	                <input type="text" id="columnTitle" autocomplete="off" class="layui-input" placeholder="请输入专栏标题">
	            </div>
				<button class="layui-btn" id="searchCourseColumn">
					<i class="layui-icon">&#x1002;</i>查询
				</button>
				<button class="layui-btn" id="addCourseColumn">
	               <i class="layui-icon">&#xe654;</i>新增
	           </button>
           </blockquote>
        </div>
        
        <table class="layui-hide" id="courseColumnTable" lay-filter="courseColumnTable"></table>

        <script type="text/html" id="imgTmp">
            <img class="layui-upload-img " src="{{d.picPath}}">
        </script>

        <script type="text/html" id="isSellTmp">
            {{# if (d.isSell === 'YES') { }}
            	<span class="layui-badge layui-bg-green">是</span>
            {{# } else { }}
            	<span class="layui-badge">否</span>
            {{# } }}
        </script>
        <script type="text/html" id="titelTmp">
			{{# if (d.isValid === 'YES') { }}
            	<span class="layui-badge layui-bg-green">已上架</span>
            {{# } else { }}
            	<span class="layui-badge">未上架</span>
            {{# } }}

			{{# if (d.isConfirm === 'NO') { }}
            	<span class="layui-badge layui-bg-green">确认收货</span>
            {{# } }}

			{{# if (d.isSell === 'YES') { }}
				<span>{{ d.columnTitle }}</span>
				<del>￥{{ d.originalPrice }}</del>
				<span style="color:red;">￥{{ d.productPrice }}</span>
				{{# if (d.point && d.point > 0) { }}
					<span style="color:red;"> + {{ d.point }}积分</span>
				{{# } }}
			{{# } else { }}
				<span>{{ d.columnTitle }}</span>
				<span style="color:red;">(免费)</span>
			{{# } }}
        </script>
        <script type="text/html" id="levelTmp">
			{{# if (d.productLevelName) { }}
				<span>{{ d.productLevelName }}</span>
			{{# } else { }}
				<span>无</span>
			{{# } }}
			
			{{# if (d.isUp === 'YES') { }}
				<span class="layui-badge">核心</span>
			{{# }  }}
        </script>
		<script type="text/html" id="courseColumnBar">
			{{# if (d.isValid === 'NO') { }}
				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="editCourseColumn">编辑</a>
				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="shelvesCourseColumn">上架</a>
			{{#	} else {	}}
				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="soldOutCourseColumn">下架</a>
			{{#	}	}}
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="uploadCoupon">优惠券</a>
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="preView">预览</a>
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="view">查看</a>
    	</script>
        <script>
            var table = layui.table;
            var $ = layui.jquery;
            var form = layui.form;
            form.render();
            
            var tableInit = table.render({
                elem: '#courseColumnTable'
                , url: '${baseURL}/courseColumn/list'
                , request: {
                    pageName: 'pageNum' //页码的参数名称，默认：page
                    , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
                }
                , where: {
                	columnTitle: $('#columnTitle').val()
                    , authorName: $('#authorName').val()
                    , columnTypeId: $('#columnTypeId').val()
                    , productLevelCode: $('#productLevelCode').val()
                }
                , cols: [[
                    { type: 'numbers',  title: '序号', width : '3%' }
                    , {field: 'authorName', width: '10%', title: '讲师'}
                    , {field: 'productLevelName', width: '9%', title: '等级', templet: '#levelTmp'}
                    , {field: 'columnTitle', width: '35%', title: '专栏标题', templet: '#titelTmp'}
                    , {field: 'columnTypeName', width: '15%', title: '专栏分类'}
                    , {field: 'isSell', width: '8%', title: '是否收费', templet: '#isSellTmp'}
                    , {field: 'toolbar', title: '操作',width: '20%',toolbar: '#courseColumnBar' , fixed: 'right'}
                ]]
                , limits: [5, 10, 20, 50]//每页数据选择项
                , limit: 20 //默认采用10
                , page: true
                , id: "courseColumnTableId"
            });

            //刷新table，带上搜索框条件
            function reloadTable(page) {
                var loadData = {
                    where: {
                    	columnTitle: $('#columnTitle').val()
                        , authorName: $('#authorName').val()
                        , columnTypeId: $('#columnTypeId').val()
                    	, productLevelCode: $('#productLevelCode').val()
                    }
                };
                if (page) {
                    loadData.page = {
                        curr: page //重新从第page页开始
                    }
                }

                tableInit.reload(loadData);
            }


            $('#searchCourseColumn').on('click', function () {
                reloadTable(1);
            });


            //新增
            $('#addCourseColumn').click(function () {
                openWin('添加专栏课程', '${baseURL}/courseColumn/addUI/', function () {
                    reloadTable(1);
                });
            })
			
            //监控操作按钮
	        table.on('tool(courseColumnTable)', function (obj) {
	            var data = obj.data;
	            if (obj.event === 'editCourseColumn') {
	            	editCourseColumn(data);
	            } else if (obj.event === 'shelvesCourseColumn') {
	            	shelvesCourseColumn(data);
	            } else if (obj.event === 'soldOutCourseColumn') {
	            	soldOutCourseColumn(data);
	            } else if (obj.event === 'uploadCoupon') {
	            	uploadCoupon(data);
	            } else if (obj.event === 'view') {
	            	viewCourseColumn(data);
	            } else if (obj.event === 'preView') {
	            	parent.parent.layer.closeAll();
	            	parent.parent.layer.open({
	                    type: 2
	                    ,offset: 'auto'
	                    ,area: ['320px', '350px']
	                    ,id: 'showUrl' //防止重复弹出
	                    ,content: "${baseURL}/courseColumn/qrcode?id=" + data.id
	                    ,btn: ['一键复制链接','一键复制支付链接']
	                    ,btnAlign: 'c' //按钮居中
	                    ,shade: 0.3 //不显示遮罩
	                    ,yes: function(){
	                    	$('#copyUrl').val('${htmlUrl}boutiqueDetail.html?id=' + data.id);
	                    	var s = '${htmlUrl}boutiqueDetail.html?id=' + data.id;
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
	                    },
	                    btn2: function(index, layero){
	                    	$('#copyUrl').val('${htmlUrl}orderConfirmByColumn.html?id=' + data.id);
	                    	var s = '${htmlUrl}orderConfirmByColumn.html?id=' + data.id;
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
    		function editCourseColumn(data){
    			openWin('修改课程专栏','${baseURL}/courseColumn/editUI?id=' + data.id,function(){
    				reloadTable(1);
    			});
    		}
          	
    		//优惠券
    		function uploadCoupon(data){
    			openWin('【' + data.columnTitle + '】的优惠券','${baseURL}/courseColumn/uploadCouponUI?id=' + data.id,function(){
    				reloadTable(1);
    			});
    		}
    		
    		//上架
    		function shelvesCourseColumn(data){
    			var deleteData = {
    					courseColumnId : data.id
    		    }
    			parent.parent.layer.confirm('确认上架商品【' + data.columnTitle + '】么', function(index){
    				var url = "${baseURL}/courseColumn/shelves";
    				post(url,deleteData,function(data){
    					reloadTable();
    					parent.parent.layer.close(index);
    					parent.parent.layer.msg('上架成功',{
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
    		
    		//下架
    		function soldOutCourseColumn(data){
    			var deleteData = {
    					courseColumnId : data.id
    		    }
    			parent.parent.layer.confirm('确认下架商品【' + data.columnTitle + '】么', function(index){
    				var url = "${baseURL}/courseColumn/soldOut";
    				post(url,deleteData,function(data){
    					reloadTable();
    					parent.parent.layer.close(index);
    					parent.parent.layer.msg('下架成功',{
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
            
    		//查看
            function viewCourseColumn(data){
            	layer.open({
                    type: 2 //iframe
                    , title: "查看产品"
                    , area: ['100%', '100%']
                    , fixed: true  //不固定
                    , offset: '0px'
                    , shade: 0
                    , content: "${baseURL}/courseColumn/viewUI?id=" + data.id
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