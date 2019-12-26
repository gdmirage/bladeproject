
var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
var $ = layui.jquery;
var layer = layui.layer;

$(function(){
	
	$('#logoutBut').click(function(){
		var url = $(this).attr('url');
		layer.confirm('确认退出系统?',{icon: 3, title:'提示'},function(index){
			location.href=url;
			layer.close(index);
		});
		
	});
	
	$('#userInfo').click(function () {
        var url = $(this).attr('url');
        openMenu('0-0',url,'修改登陆密码');
    })
	
});

//上边导航监听
element.on('nav(rightNav)', function(elem){
	var $ = layui.jquery;
	var id = $(this).attr('data-id');
	$('#leftNav').html($('#menu' + id).html());
	element.init();
//	layer.msg(elem.text());
});

//左边导航栏监听
element.on('nav(leftNav)', function(elem){
	var $ = layui.jquery;
	var url = $(this).attr('data-url');
	var id = $(this).attr('data-id');
	var name = $(this).attr('data-name');
	openMenu(id,url,name)
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
function openMenu(id,url,name){
	var element = layui.element;
	var $ = layui.jquery;
	
	var isActive = $('.main-layout-tab .layui-tab-title').find("li[lay-id=" + id + "]");
	if(isActive.length > 0) {
		//切换到选项卡
		element.tabChange('tab', id);
		refreshWindow('iframe' + id);
	} else {
		element.tabAdd('tab', {
			title: name,
			content: '<iframe src="' + url + '" name="iframe' + id + '" class="iframe" framborder="0" data-id="' + id + '" scrolling="auto" width="100%"  height="100%"></iframe>',
			id: id
		});
		element.tabChange('tab', id);
		
	}
	var mainLayout = $('#main-layout');
	mainLayout.removeClass('hide-side');
}




