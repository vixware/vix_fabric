<%@ page language="java" pageEncoding="UTF-8"%>
<!-- added by wulei -->
<link href="${vix}/common/core/css/css_add_01.css" type="text/css" rel="stylesheet" />
<script src="${vix}/common/core/js/all_add_01.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all_pad.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/inc_grid_need.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/inc_tab_page.js" type="text/javascript"></script>
<%-- 
<link href="${vix}/module/eam/eam.css" type="text/css" rel="stylesheet"/>
<script src="${vix}/module/eam/eam.js" type="text/javascript"></script>
 --%>
<style type="text/css">
#page_content_loading_mask {
	position: fixed;
	bottom: 0px;
	left: 0px;
	right: 0px;
	top: 0px;
	background-color: grey;
	z-index: 9;
	opacity: 0.6;
	font-family: "微软雅黑";
	font-size: 20px;
	color: #ffffff;
	display: none;
}

#page_content_loading_mask .open_m {
	margin: auto;
	background-color: #ffffff;
	border: 0 solid red;
	border-radius: 10000px;
	height: 200px;
	width: 200px;
	margin-top: 250px;
}

#page_content_loading_mask .open_m .img_box {
	width: 156px;
	height: 95px;
	margin: auto;
}

#page_content_loading_mask .open_m .img_box img {
	margin-top: 50px;
}
</style>

<script type="text/javascript">
function _resouse_show_menu_mask(isShow){
	var mask = $('#page_content_loading_mask');
	if(mask.length==0)
		return;
	if(isShow){
		var duration = 1000;
		var openM = $('#page_content_loading_mask .open_m');
		var img = $('#page_content_loading_mask .open_m .img_box img');
		openM.stop();
		img.stop();
		openM.attr("style","");
		img.attr('style','');
		mask.show();
		openM.animate({ 
		    width: "1200px",
		    height: "1200px",
		    marginTop: "-250px"
		  }, duration );
		img.animate({ 
		    marginTop: "550px"
		  }, duration );
		mask.animate({ 
			opacity: "0.2"
		  }, duration );
	}else{
		mask.hide();
	}
}
</script>