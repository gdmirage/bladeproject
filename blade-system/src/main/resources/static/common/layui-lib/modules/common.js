/** layuiAdmin.std-v1.2.1 LPPL License By http://www.layui.com/admin/ */
 ;layui.define(function(e){
	 var i=(layui.$,layui.layer,layui.laytpl,layui.setter,layui.view,layui.admin);
	 i.events.logout = function(){
		 var url = $(this).attr('url');
		 layer.confirm('确认退出系统?',{icon: 3, title:'提示'},function(index){
			location.href=url;
			layer.close(index);
		 });
	 },
	 e("common",{})
});