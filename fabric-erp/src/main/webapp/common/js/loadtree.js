// 目录树
	var userAgent = window.navigator.userAgent.toLowerCase();
	$.browser.msie8 = $.browser.msie && /msie 8\.0/i.test(userAgent);
	$.browser.msie7 = $.browser.msie && /msie 7\.0/i.test(userAgent);
	$.browser.msie6 = !$.browser.msie8 && !$.browser.msie7 && $.browser.msie && /msie 6\.0/i.test(userAgent);
	function load() {        
		var o = { showcheck: true
		//onnodeclick:function(item){alert(item.text);}
		};
		o.data = treedata;        
		$("#tree_00").treeview(o);
	}
	if($("#tree_00").length){
		if( $.browser.msie6)
		{
			load();
		}
		else{
			$(document).ready(load);
		}
	}