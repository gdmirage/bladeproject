<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/taglib/taglib.jsp" %>
<script type="text/javascript" src="../../../static/js/libs/jquery-3.2.1.min.js"></script>
<%--<link type="text/css" rel="stylesheet" href="../../common/css/zthree/zTreeStyle.css"/>--%>
<link type="text/css" rel="stylesheet" href="../../../static/css/zthreeStyle/metroStyle.css"/>
<link type="text/css" rel="stylesheet" href="../../../static/css/common.css"/>
<script type="text/javascript" src="../../../static/js/zthree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="../../../static/js/zthree/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="../../../static/js/zthree/jquery.ztree.exedit-3.5.min.js"></script>
<script type="text/javascript" src="../../../static/js/zthree/ztreeTool.js"></script>

<div class="page" style="height: 100%;margin: 10px 10px">
    <form class="layui-form" method="post" id="assignMenuFrom">
        <input type="hidden" name="roleId" value="${role.id }"/>
        <input type="hidden" name="selectVal" id="selectVal" value="">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>角色权限管理</legend>
        </fieldset>
<%--        <div class="layui-form-item">
            <label class="layui-form-label">菜单列表</label>
            <div class="layui-input-block">

            </div>
        </div>--%>
        <div class="layui-form-item" style="margin-left: 10px;">
            <div>
                <ul id="tree" class="ztree"></ul>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-btn" id="confirm" style="display: block;width: 8%;">
                <i class="layui-icon" >&#xe605;</i>确认
            </div>
        </div>

    </form>
</div>
<script type="text/javascript">

    var layer = layui.layer;
    var element = layui.element;

    var structureItemsZTree;
    var log, className = "dark", curDragNodes, autoExpandNode;

    var setting = {
        edit: {
            enable: true,
            showRemoveBtn: false,
            showRenameBtn: false
        },
        check: {
            enable: true
        },
        view: {
            showIcon: false,
            dblClickExpand: false
        },
        data: {
            key: {
                title: "id"
            },
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        },
        callback: {
            beforeClick:beforeClick,
            onCheck: onCheck
        }
    };

    var zNodes = ${permissionList};


    $(document).ready(function () {
        $.fn.zTree.init($("#tree"), setting, zNodes);
        structureItemsZTree = $.fn.zTree.getZTreeObj("tree");
        structureItemsZTree.expandAll(true);

    });
    function beforeClick(treeId, treeNode) {
        // console.log(treeNode);
        // layer.confirm('确定选择【' + treeNode.name + '】么', function (index) {
        //
        // })
        return false;
    }
    function onCheck(e, treeId, treeNode) {
        // console.log(treeNode)
    }
    var roleId = ${role.id};
    $("#confirm").click(function () {
        //获取有变化的checkbox
        var changeCheckList = structureItemsZTree.getChangeCheckedNodes();
        var checkArray = [];
        var cancelArray = [];
        circulation(checkArray,changeCheckList);
        function circulation(checkArray,list) {
            $.each(list, function(i, o){
                if(o.checkedOld == true){//取消关联的
                    cancelArray.push(o.id);
                }
                if(o.checkedOld == false){//添加关联的
                    checkArray.push(o.id);
                }
            });
        }
        var param = {
            roleId : roleId,
            checks : checkArray.join(","),
            cancels: cancelArray.join(",")
        };
        post("${baseURL}/pms/role/assignPermission",param,function(data){
        	parent.parent.layer.msg('分配成功',{
                icon: 1,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            },function(){
            	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            });
        },function(data){
        	parent.parent.layer.msg(data.msg,{
                icon: 2,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            })
        });

    });
    <%--//回显--%>
    <%--$(document).ready(function() {--%>
    <%--var str = "${menuIds}";--%>
    <%--var array = new Array();--%>
    <%--array = str.split(",");--%>
    <%--for ( var i = 0; i < array.length; i++) {--%>
    <%--$("#menuId" + array[i]).attr("checked", "checked");--%>
    <%--}--%>
    <%----%>
    <%--$("#selectAll").click(function(){--%>
    <%--if($("#selectAll").is(':checked')){--%>
    <%--$("input[name='selectMenu']").attr("checked","checked"); --%>
    <%--}else{--%>
    <%--$("input[name='selectMenu']").removeAttr("checked");--%>
    <%--}--%>
    <%--}); --%>
    <%--});--%>


    <%--function submitForm() {--%>
    <%--var str = "";--%>
    <%--$(":checkbox:checked").each(function() {--%>
    <%--if ($(this).hasClass('selectMenu')){--%>
    <%--// 加样式判断，避免与其他复选框冲突--%>
    <%--str += $(this).val() + ",";--%>
    <%--}--%>
    <%--});--%>
    <%--if(str == null || str == ""){--%>
    <%--alertMsg.error("关联的权限不能为空!");--%>
    <%--return;--%>
    <%--}--%>
    <%--$("#selectVal").val(str);--%>
    <%--$("#form").submit();--%>
    <%--}--%>
</script>
