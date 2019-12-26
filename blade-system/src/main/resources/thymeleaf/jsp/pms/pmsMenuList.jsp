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


<div class="page layui-anim layui-anim-upbit" style="margin: 10px 10px">
    <div class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <button class="layui-btn" id="addMenu">
                    <i class="layui-icon">&#xe608;</i>添加
                </button>
                <button class="layui-btn" id="addRootMenu">
                    <i class="layui-icon">&#xe608;</i>添加根目录
                </button>
                <button class="layui-btn" id="updateMenu">
                    <i class="layui-icon">&#xe608;</i> 修改
                </button>
                <button class="layui-btn" id="deleteMenu">
                    <i class="layui-icon">&#xe608;</i> 删除
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

    // 节点数组，用于加载树
    var structureItemsZNodes = null;
    // 当前选中节点
    var selectNode = null;
    // 保存修改节点的id
    var nodeId = 0;
    // 菜单树对象
    var structureItemsZTree;

    var log, className = "dark", curDragNodes, autoExpandNode;


    var setting = {
        edit: {
            drag: {
                autoExpandTrigger: true,
                prev: dropPrev,
                inner: dropInner,
                next: dropNext
            },
            enable: true,
            showRemoveBtn: false,
            showRenameBtn: false
        },
        view: {
            showIcon: false,
            dblClickExpand: false,
            // addHoverDom: addStructureItemsHoverDom,
            // removeHoverDom: removeStructureItemsHoverDom
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
            onClick: onClick,
            beforeDrag: beforeDrag,
            onDrag: onDrag,
            onDrop: onDrop,
        }
    };

    var zNodes = ${tree};
    console.log(${tree});

    function dropPrev(treeId, nodes, targetNode) {
        var pNode = targetNode.getParentNode();
        if (pNode && pNode.dropInner === false) {
            return false;
        } else {
            for (var i = 0, l = curDragNodes.length; i < l; i++) {
                var curPNode = curDragNodes[i].getParentNode();
                if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
                    return false;
                }
            }
        }
        return true;
    }

    function dropInner(treeId, nodes, targetNode) {
        if (targetNode && targetNode.dropInner === false) {
            return false;
        } else {
            for (var i = 0, l = curDragNodes.length; i < l; i++) {
                if (!targetNode && curDragNodes[i].dropRoot === false) {
                    return false;
                } else if (curDragNodes[i].parentTId && curDragNodes[i].getParentNode() !== targetNode && curDragNodes[i].getParentNode().childOuter === false) {
                    return false;
                }
            }
        }
        return true;
    }

    function dropNext(treeId, nodes, targetNode) {
        var pNode = targetNode.getParentNode();
        if (pNode && pNode.dropInner === false) {
            return false;
        } else {
            for (var i = 0, l = curDragNodes.length; i < l; i++) {
                var curPNode = curDragNodes[i].getParentNode();
                if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
                    return false;
                }
            }
        }
        return true;
    }


    function onDrag(event, treeId, treeNodes) {
        className = (className === "dark" ? "" : "dark");
    }

    function beforeDrag(treeId, treeNodes) {
        className = (className === "dark" ? "" : "dark");
        for (var i = 0, l = treeNodes.length; i < l; i++) {
            if (treeNodes[i].drag === false) {
                curDragNodes = null;
                return false;
            } else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
                curDragNodes = null;
                return false;
            }
        }
        curDragNodes = treeNodes;
        return true;
    }


    function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
        className = (className === "dark" ? "" : "dark");
        var url = "${baseURL}/pms/menu/drag";
        var param = {
                menuId: treeNodes[0].id,
                targetMenuId: targetNode.id
            }
        ;
        post(url, param, function () {
        	parent.parent.layer.msg('修改成功', {
                icon: 1,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            });
        }, function () {
        	parent.parent.layer.msg('修改失败', {
                icon: 2,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            });
        });
    }

    $(document).ready(function () {
        $.fn.zTree.init($("#tree"), setting, zNodes);
        structureItemsZTree = $.fn.zTree.getZTreeObj("tree");
    });

    function onClick(event, treeId, treeNode, clickFlag) {
        $("#id").val(treeNode.id);
    }


    function addStructureItemsHoverDom(treeId, treeNode) {
        var aObj = $("#" + treeNode.tId + IDMark_A);
        var preNode = treeNode.getPreNode();
        var nextNode = treeNode.getNextNode();

        if (preNode != null) {
            if ($("#diyBtn1_" + treeNode.id).length > 0) return;
            var editStr = "<span class='demoIcon' id='diyBtn1_" + treeNode.id + "' title='" + treeNode.name + "' onfocus='this.blur();'><img class='button iconUp' src='../../../static/image/tree/up.png'/></span>";
            aObj.append(editStr);
            var btn1 = $("#diyBtn1_" + treeNode.id);
            if (btn1) btn1.bind("click", function () {
                if (onUpdateStructureItemsNodeId(treeNode, preNode)) {
                    upMoveNode(structureItemsZTree, treeNode, preNode);
                    addStructureItemsHoverDom(0, treeNode);
                }
            });
        }
        if (nextNode != null) {
            if ($("#diyBtn2_" + treeNode.id).length > 0) return;
            var calssName1 = preNode == null ? "demoIcon" : "";
            var editStr = "<span class='" + calssName1 + "' id='diyBtn2_" + treeNode.id + "' title='" + treeNode.name + "' onfocus='this.blur();'><img class='button iconDown' src='../../../static/image/tree/down_arrow.png'/></span>";
            aObj.append(editStr);
            var btn2 = $("#diyBtn2_" + treeNode.id);
            if (btn2) btn2.bind("click", function () {
                if (onUpdateStructureItemsNodeId(treeNode, nextNode)) {
                    downMoveNode(structureItemsZTree, treeNode, nextNode);
                    addStructureItemsHoverDom(0, treeNode);
                }
            });
        }
    }

    /**
     * 交换修改nodeAId和noteBId
     * @param nodeAId
     * @param noteBId
     */
    function onUpdateStructureItemsNodeId(nodeA, nodeB) {
        var flag = true;
        var url = "${baseURL}/pms/menu/move";
        var param = {
                menuId: nodeA.id,
                targetMenuId: nodeB.id
            }
        ;
        post(url, param, function () {
            flag = true;
            parent.parent.layer.msg('修改成功', {
                icon: 1,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            });
        }, function () {
            flag = false;
            parent.parent.layer.msg('修改失败', {
                icon: 2,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            });
        });
        return flag;
    }

    function removeStructureItemsHoverDom(treeId, treeNode) {
        if (treeNode.getPreNode() != null)
            $("#diyBtn1_" + treeNode.id).unbind().remove();
        if (treeNode.getNextNode() != null)
            $("#diyBtn2_" + treeNode.id).unbind().remove();
    }

    $('#addMenu').on('click', function () {
        var id = $("#id").val();
        layer.open({
            type: 2 //iframe
            , title: "新增菜单"
            , area: ['80%', '70%']
            , fixed: false  //不固定
            , offset: '0px'
            , shade: 0
            , content: "${baseURL}/pms/menu/addUI?pid=" + id
            , zIndex: layer.zIndex //重点1
            , success: function (layero) {
                layer.setTop(layero); 	//弹窗置顶
            }
            , end: function (index, layero) {

            }
        });
    });

    $('#addRootMenu').on('click', function () {
        layer.open({
            type: 2 //iframe
            , title: "新增菜单"
            , area: ['80%', '70%']
            , fixed: false  //不固定
            , offset: '0px'
            , shade: 0
            , content: "${baseURL}/pms/menu/addUI?pid="
            , zIndex: layer.zIndex //重点1
            , success: function (layero) {
                layer.setTop(layero); 	//弹窗置顶
            }
            , end: function (index, layero) {

            }
        });
    });

    $('#updateMenu').on('click', function () {
        var id = $("#id").val();
        layer.open({
            type: 2//iframe
            , title: "修改菜单"
            , area: ['80%', '70%']
            , fixed: false  //不固定
            , offset: '0px'
            , shade: 0
            , content: "${baseURL}/pms/menu/editUI?id=" + id
            , zIndex: layer.zIndex //重点1
            , success: function (layero) {
                layer.setTop(layero); 	//弹窗置顶
            }
            , end: function (index, layero) {

            }
        });
    })


    $('#deleteMenu').on('click', function () {
        var id = $("#id").val();
        param = {
            menuId: id
        };
        parent.parent.layer.confirm('你确定要删除吗?', function (index) {
            var url = "${baseURL}/pms/menu/delete";
            post(url, param, function () {
                location.reload();
                parent.parent.layer.close(index);
                parent.parent.layer.msg('删除成功', {
                    icon: 1,
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                });
            }, function () {
            	parent.parent.layer.msg('删除失败', {
                    icon: 2,
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                });
            });
        });
    });
</SCRIPT>