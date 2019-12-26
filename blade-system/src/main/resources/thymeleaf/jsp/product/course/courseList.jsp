<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../common/taglib/taglib.jsp" %>
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
	            	<select name="columnId" id="columnId" lay-search="">
	            		<option value="">请选择专栏</option>
						<c:forEach var="item" items="${courseColumnList }">
							<option value="${item.id }">${item.columnTitle }</option>
						</c:forEach>
					</select>
	            </div>
        		<div class="layui-input-inline">
	            	<select name="courseTypeId" id="courseTypeId">
	            		<option value="">请选择课程分类</option>
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
	                <input type="text" id="authorName" autocomplete="off" class="layui-input" placeholder="请输入作者姓名">
	            </div>
	            <div class="layui-input-inline">
	                <input type="text" id="courseTitle" autocomplete="off" class="layui-input" placeholder="请输入课程标题">
	            </div>
	            <br /><br />
	            <div class="layui-input-inline">
	            	<select name="isValid" id="isValid">
	            		<option value="">请选择是否上架</option>
						<option value="YES" selected>已上架</option>
						<option value="NO">未上架</option>
					</select>
	            </div>
				<button class="layui-btn" id="searchCourse">
					<i class="layui-icon">&#x1002;</i>查询
				</button>
				<button class="layui-btn" id="addCourse">
	               <i class="layui-icon">&#xe654;</i>新增
	           </button>
           </blockquote>
        </div>
        
        <table class="layui-hide" id="courseTable" lay-filter="courseTable"></table>

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
			
			{{# if (d.isConfirmReceipt === 'YES') { }}
            	<span class="layui-badge layui-bg-green">确认收货</span>
            {{# } }}

			{{# if (d.isSell === 'YES') { }}
				<span>{{ d.courseTitle }}</span>
				<del>￥{{ d.originalPrice }}</del>
				<span style="color:red;">￥{{ d.productPrice }}</span>
				{{# if (d.point && d.point > 0) { }}
					<span style="color:red;"> + {{ d.point }}积分</span>
				{{# } }}
			{{# } else { }}
				<span>{{ d.courseTitle }}</span>
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
				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="editCourse">编辑</a>
				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="shelvesCourse">上架</a>
			{{#	} else {	}}
				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="soldOutCourse">下架</a>
			{{#	}	}}
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="uploadCoupon">优惠券</a>
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="preView">预览</a>
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="view">查看</a>
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="showHomework">课程作业</a>
    	</script>
        <script>
            var table = layui.table;
            var $ = layui.jquery;
            var form = layui.form;
            form.render();
            
            var tableInit = table.render({
                elem: '#courseTable'
                , url: '${baseURL}/course/list'
                , request: {
                    pageName: 'pageNum' //页码的参数名称，默认：page
                    , limitName: 'numPerPage' //每页数据量的参数名，默认：limit
                }
                , where: {
                	courseTitle: $('#courseTitle').val()
                    , authorName: $('#authorName').val()
                    , courseTypeId: $('#courseTypeId').val()
                    , productLevelCode: $('#productLevelCode').val()
                    , isValid: $('#isValid').val()
                    , columnId: $('#columnId').val()
                }
                , cols: [[
                     { type: 'numbers',  title: '序号', width : '3%' }
                    , {field: 'authorName', width: '8%', title: '讲师'}
                    , {field: 'productLevelName', width: '8%', title: '等级', templet: '#levelTmp'}
                    , {field: 'courseTypeName', width: '15%', title: '课程分类'}
                    , {field: 'columnTitle', width: '15%', title: '专栏名称'}
                    , {field: 'courseTitle', width: '32%', title: '课程标题', templet: '#titelTmp'}
                    , {field: 'toolbar', title: '操作',width: '30%',toolbar: '#courseColumnBar' , fixed: 'right'}
                ]]
                , limits: [5, 10, 20, 50]//每页数据选择项
                , limit: 20 //默认采用10
                , page: true
                , id: "courseTableId"
            });

            //刷新table，带上搜索框条件
            function reloadTable(page) {
                var loadData = {
                    where: {
                    	courseTitle: $('#courseTitle').val()
                        , authorName: $('#authorName').val()
                        , courseTypeId: $('#courseTypeId').val()
                    	, productLevelCode: $('#productLevelCode').val()
                    	, isValid: $('#isValid').val()
                    	, columnId: $('#columnId').val()
                    }
                };
                if (page) {
                    loadData.page = {
                        curr: page //重新从第page页开始
                    }
                }

                tableInit.reload(loadData);
            }


            $('#searchCourse').on('click', function () {
                reloadTable(1);
            });


            //新增
            $('#addCourse').click(function () {
                openWin('添加课程', '${baseURL}/course/addUI/', function () {
                    reloadTable(1);
                });
            })
			
            //监控操作按钮
	        table.on('tool(courseTable)', function (obj) {
	            var data = obj.data;
	            if (obj.event === 'editCourse') {
	            	editCourse(data);
	            } else if (obj.event === 'shelvesCourse') {
	            	shelvesCourse(data);
	            } else if (obj.event === 'soldOutCourse') {
	            	soldOutCourse(data);
	            } else if (obj.event === 'uploadCoupon') {
	            	uploadCoupon(data);
	            } else if (obj.event === 'view') {
	            	viewCourse(data);
	            } else if (obj.event === 'showHomework') {
	            	showHomework(data);
	            } else if (obj.event === 'preView') {
	            	parent.parent.layer.closeAll();
	            	parent.parent.layer.open({
	                    type: 2
	                    ,offset: 'auto'
	                    ,area: ['320px', '350px']
	                    ,id: 'showUrl' //防止重复弹出
	                    ,content: "${baseURL}/course/qrcode?id=" + data.id
	                    ,btn: ['一键复制链接','一键复制支付链接']
	                    ,btnAlign: 'c' //按钮左对齐
	                    ,shade: 0.3 //不显示遮罩
	                    ,yes: function(){
	                    	$('#copyUrl').val('${htmlUrl}courseDetail.html?id=' + data.id);
	                    	var s = '${htmlUrl}courseDetail.html?id=' + data.id;
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
	                    	$('#copyUrl').val('${htmlUrl}orderConfirmByColumn.html?id=' + data.id + '&type=course');
	                    	var s = '${htmlUrl}orderConfirmByColumn.html?id=' + data.id + '&type=course';
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
            
          	//课程作业
    		function showHomework(data){
    			openWin('课程【' + data.courseTitle + '】的作业','${baseURL}/homework/editUI?courseId=' + data.id,function(){
    				// reloadTable(1);
    			});
    		}
          
          	//修改
    		function editCourse(data){
    			openWin('修改课程','${baseURL}/course/editUI?id=' + data.id,function(){
    				reloadTable(1);
    			});
    		}
          	
    		//优惠券
    		function uploadCoupon(data){
    			openWin('【' + data.courseTitle + '】的优惠券','${baseURL}/course/uploadCouponUI?id=' + data.id,function(){
    				reloadTable(1);
    			});
    		}
    		
    		//上架
    		function shelvesCourse(data){
    			var deleteData = {
    					courseId : data.id
    		    }
    			parent.parent.layer.confirm('确认上架商品【' + data.courseTitle + '】么', function(index){
    				var url = "${baseURL}/course/shelvesCourse";
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
    		function soldOutCourse(data){
    			var deleteData = {
    					courseId : data.id
    		    }
    			parent.parent.layer.confirm('确认下架商品【' + data.courseTitle + '】么', function(index){
    				var url = "${baseURL}/course/soldOutCourse";
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
    		
    		//删除
    		function delCourse(data){
    			var deleteData = {
    				id : data.id
    		    }
    			parent.parent.layer.confirm('确认删除分类【' + data.parentTypeName + ' -> ' + data.typeName + '】么', function(index){
    				var url = "${baseURL}/course/delete";
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
            
            //查看
            function viewCourse(data){
            	layer.open({
                    type: 2 //iframe
                    , title: "查看产品"
                    , area: ['100%', '100%']
                    , fixed: true  //不固定
                    , offset: '0px'
                    , shade: 0
                    , content: "${baseURL}/course/viewUI?id=" + data.id
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