<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../../static/common/taglib/taglib.jsp" %>

<script type="text/javascript" src="../../../static/common/js/libs/jquery-3.2.1.min.js"></script>
<%--<link type="text/css" rel="stylesheet" href="../../common/css/zthree/zTreeStyle.css"/>--%>
<link type="text/css" rel="stylesheet" href="../../../static/common/css/zthreeStyle/metroStyle.css"/>
<link type="text/css" rel="stylesheet" href="../../../static/common/css/common.css"/>
<script type="text/javascript" src="../../../static/common/js/zthree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="../../../static/common/js/zthree/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="../../../static/common/js/zthree/jquery.ztree.exedit-3.5.min.js"></script>
<script type="text/javascript" src="../../../static/common/js/zthree/ztreeTool.js"></script>


<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    <div class="layui-form">

        <div class="layui-form-item">
            <label class="layui-form-label">用户编码：</label>
            <div class="layui-input-inline">
                <input type="text" id="userNum" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-inline">
                <button class="layui-btn" id="query">
                    <i class="layui-icon">&#xe608;</i>查询
                </button>
                <input type="hidden" name="id" id="id">
            </div>
        </div>

        <div class="layui-form-item">
            <div>
                <ul id="tree" class="ztree"></ul>
            </div>
        </div>
    </div>
</div>
<SCRIPT type="text/javascript">

    var layer = layui.layer;
    // 菜单树对象
    var structureItemsZTree;

    var setting = {
        edit: {
            drag: {
                autoExpandTrigger: true
            },
            enable: false,
            showRemoveBtn: false,
            showRenameBtn: false
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
                enable: true
            }
        },
        callback: {
        }
    };

    var zNodes = ${tree};
    console.log(${tree});

    $(document).ready(function () {
        // $.fn.zTree.init($("#tree"), setting, zNodes);
        // structureItemsZTree = $.fn.zTree.getZTreeObj("tree");

        initTree(zNodes)
    });

    function initTree(data) {
        $.fn.zTree.init($("#tree"), setting, data);
        structureItemsZTree = $.fn.zTree.getZTreeObj("tree");
    }


    $('#query').on('click', function () {
        var id = $("#userNum").val();
        console.log(id);
        var param = {
            userNum: id,
        };
        var url = "${baseURL}/agent/recommend/list";
        post(url, param, function (data) {
            var obj = eval('(' + data.data + ')');
            console.log(obj);
            initTree(obj);
        }, function () {
            layer.msg('查询失败', {
                icon: 2,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            });
        });

    });


</SCRIPT>