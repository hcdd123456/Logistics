﻿


//检测定义的宽和高，是否合适。
function checkWindow(){

	if(h>$(window).height()){
		h = (document.body.offsetHeight-50);
	}
	if(w>($(window).width()-50)){
		w = (document.body.offsetWidth-60);
	}
}

function getTableSelected(){
	var selected = table.checkStatus("table");
	console.log("getTableSelected:"+selected);
	if(selected.data.length<1){
		layer.msg('请选择内容!');
		return;
	}
	var ids = "";
	if(selected.data.length>0){
		$.each(selected.data,function(i,val){
			ids = ids+val.id+",";
		});
	}
	//去掉最后的逗号,
	ids = ids.substring(0,ids.length-1);
	global_ids = ids;
	global_ids_len = selected.data.length;
}

//删除
function delSelected(){
	global_ids="";
	global_ids_len=0;
	getTableSelected();
	if(global_ids_len>0){
		//询问框
		layer.confirm('您是否要删除这'+global_ids_len+'个吗？', {
		  btn: ['确定删除','取消'] //按钮
		}, function(){
			console.log(global_ids)
			del(global_ids);
		}, function(){
			layer.msg('您选择了取消');
		});
	}

}

