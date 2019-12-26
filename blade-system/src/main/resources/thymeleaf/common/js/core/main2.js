
var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

//监听导航点击
element.on('nav(rightNav)', function(elem){
	var $ = layui.jquery;
	console.log(elem)
	console.log("elem.data('id') = " + elem.data('id') )
 
//	layer.msg(elem.text());
});



/**
 * 刷新子页,关闭弹窗
 */
function refreshWindow(id) {
	//根据传递的name值，获取子iframe窗口，执行刷新
	if(window.frames[id]) {
		window.frames[id].location.reload();
	} else {
		window.location.reload();
	}

	layer.closeAll();
}

/**
 * 刷新面包屑
 */
function refreshBreadcrumb(){
	var $ = layui.jquery;
	$('#mbx').html();
}

/**
 * 打开菜单
 * @param item
 */
function openMenu(item){
	var element = layui.element;
	var $ = layui.jquery;
	
	var mainLayout = $('#main-layout');
	
	var id = item.id;
	var url = item.menuHref;
	var text = item.name;
	if(!url){
		return;
	}
	var isActive = $('.main-layout-tab .layui-tab-title').find("li[lay-id=" + id + "]");
	if(isActive.length > 0) {
		//切换到选项卡
		element.tabChange('tab', id);
		refreshWindow('iframe' + id);
	} else {
		element.tabAdd('tab', {
			title: text,
			content: '<iframe src="' + url + '" name="iframe' + id + '" class="iframe" framborder="0" data-id="' + id + '" scrolling="auto" width="100%"  height="100%"></iframe>',
			id: id
		});
		element.tabChange('tab', id);
		
	}
	mainLayout.removeClass('hide-side');
}




