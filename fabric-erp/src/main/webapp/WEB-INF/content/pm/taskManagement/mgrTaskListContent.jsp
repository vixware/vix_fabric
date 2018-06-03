<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$(function(){
	if($('.easyui-treegrid').length>0){
		$('.easyui-treegrid').width($('.easyui-treegrid').parent().width());
		var oHead = document.getElementsByTagName('head').item(0);
		var oScript= document.createElement("script");
		oScript.type = "text/javascript";
		oScript.src="js/jquery.easyui.min.js";
		oHead.appendChild( oScript);
	}
});


function formatProgress(value){
	if (value){
		var bcolor = "#cc0000";
		if(value>90)
			bcolor='#00A06B';
		else if(value>70)
				bcolor='#1B4F93';
		else if(value>50)
			bcolor='#EC870E';
		
    	var s = '<div style="width:100%;border:1px solid #ccc">' +
    			'<div style="width:' + value + '%;background:'+bcolor+';color:#fff">' + value + '%' + '</div>'
    			'</div>';
    	return s;
	} else {
    	return '';
	}
}
function adSubProgress(value,row){
	var link = '<a href="javascript:saveOrUpdate(<s:property value="projectId"/>,null,'+row.id+')">添加</a>';
	link = link + '&nbsp;<a href="javascript:saveOrUpdate(<s:property value="projectId"/>,'+row.id+')">查看</a>';
	return link;
}
</script>
<div class="tg_w_auto">
	<div class="right_btn clearfix" style="width: auto; border: 0; margin: 0;">
		<span><a href="javascript:void(0);" onclick="saveOrUpdate(<s:property value="projectId"/>)" title="添加任务里程碑"><img alt="" src="${vix}/common/css/images/icons/add.png">添加任务里程碑</a></span>
		<!-- 
		<span><a href="javascript:void(0);" onclick="saveNode('tt')" title="保存"><img alt="" src="img/wrench_screwdriver.png"></a></span>
		<span><a href="javascript:void(0);" onclick="cancelNode('tt')" title="取消"><img alt="" src="img/address_book.png"></a></span>
 -->
	</div>
	<table id="wbsTaskGrid" class="easyui-treegrid" style="height: 470px;" url="${vix}/pm/taskManagementAction!mgrTaskListContentJson.action?projectId=<s:property value="projectId"/>" idField="id" treeField="title" pagination="true" fitColumns="true">
		<thead>
			<tr>
				<th field="title" width="190" editor="text">任务名称</th>
				<th field="name" width="140" editor="text">负责人</th>
				<th field="expectBeginTime" width="90" editor="text">预计开始时间</th>
				<th field="expectFinishTime" width="90" editor="text">预计完成时间</th>
				<th field="beginTime" width="90" editor="text">实际开始时间</th>
				<th field="finishTime" width="90" editor="text">实际完成时间</th>
				<th field="finishPercent" width="150" formatter="formatProgress">任务进度</th>
				<th field="id" width="70" formatter="adSubProgress">添加子任务</th>
			</tr>
		</thead>
	</table>
</div>