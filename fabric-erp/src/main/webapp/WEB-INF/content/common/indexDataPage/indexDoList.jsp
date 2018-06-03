<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	/* 载入内容区 */
	$ (document).ready (function (){
		$.ajax ({
		url : '${vix}/common/vixIndexDataAction!goIndexDoList.action' ,
		cache : false ,
		success : function (html){
			$ ("#indexDoList").html (html);
		}
		});
	});
</script>

<div id="indexDoList"></div>


