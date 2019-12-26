//ztree元素变量名称(name + id)
var IDMark_Switch = "_switch", IDMark_Icon = "_ico", IDMark_Span = "_span", IDMark_Input = "_input", IDMark_Check = "_check", IDMark_Edit = "_edit", IDMark_Remove = "_remove", IDMark_Ul = "_ul", IDMark_A = "_a";

/**
 * 取消树节点的选中状态
 * 
 * @param zTreeObj
 */
function cancelTreeSelect(zTreeObj) {
	var nodes = zTreeObj.getSelectedNodes();
	if (nodes.length > 0) {
		zTreeObj.cancelSelectedNode(nodes[0]);
	}
}

/**
 * 选中树节点
 * 
 * @param zTreeObj,pid
 */
function setTreeSelect(zTreeObj, pId) {
	var nodes = zTreeObj.getNodes();
	for ( var i = 0; i < nodes.length; i++) {
		if (nodes[i].id == pId) {
			zTreeObj.selectNode(nodes[i]);
			return;
		} else {
			setTreeChilNodesSelect(zTreeObj, nodes[i], pId);
		}
	}
}

function setTreeChilNodesSelect(zTreeObj, nodes, pId) {
	var chilNodes = nodes.children;
	if (chilNodes != undefined) {
		for ( var j = 0; j < chilNodes.length; j++) {
			if (chilNodes[j].id == pId) {
				zTreeObj.selectNode(chilNodes[j]);
				return;
			} else {
				setTreeChilNodesSelect(zTreeObj, chilNodes[j], pId);
			}
		}
	}
}

/**
 * 取消节点和节点所有子节点的选中状态
 * 
 * @param node
 */
function clearNodeCheck(node) {
	//alert(5);
	if (node.children != null) {
		var nodes = node.children;
		for ( var i = 0; i < nodes.length; i++) {
			clearNodeCheck(nodes[i]);
		}
	}
	node.checked = false;
}

/**
 * 树节点上移
 * 
 * @param node
 */
function upMoveNode(treeObj, node, preNode) {
	//alert(6);
	treeObj.moveNode(node, preNode, "next");
}

/**
 * 树节点下移
 * 
 * @param node
 */
function downMoveNode(treeObj, node, nextNode) {
	//alert(7);
	treeObj.moveNode(node, nextNode, "prev");
}
