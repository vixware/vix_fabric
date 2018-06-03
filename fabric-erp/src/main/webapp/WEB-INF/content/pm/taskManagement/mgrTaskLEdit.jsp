<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
<script type="text/javascript">
var editor;
$(function(){
	editor = KindEditor.create('textarea[id="content"]',{basePath:'${vix}/plugin/KindEditor/',width:750,height:150});
	
});


var isSaving = false;
function saveWbsTask(){
	if(isSaving)
		return;
	isSaving = true;
	
	if($('#bjxxForm #partCode').val()==''){
    	showMessage('请输入备件编码');
    	return false;
	}
	editor.sync(); 

	$('#taskContent').val($('#content').val());

	$("#wbsTaskForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/pm/taskManagementAction!saveWbsTask.action",
	     dataType: "text",
	     success: function(result){
	    	if(result != null){
		    	var dataId = result;
		    	$('#taskId').val(dataId);
		    	showMessage('信息保存完毕');

		    	$('#wbsTaskGrid').treegrid('reload');
		    	isSaving = false;
		    	_tab_close_current();

	    	}else{
	    		showErrorMessage('信息保存失败');
		    	isSaving = false;
	    	}
	     }
	 });
	return false;
}
</script>


<div class="" style="margin-top: 5px;">
	<form id="wbsTaskForm" method="post">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="taskProjectId" name="task.project.id" value="%{projectId}" theme="simple" />
				<s:hidden id="taskParentId" name="parentId" value="%{parentId}" theme="simple" />
				<s:hidden id="taskId" name="task.id" theme="simple" />
				<s:hidden id="finishPercent" name="" value="0" theme="simple" />
				<s:hidden id="taskContent" name="task.content" theme="simple"></s:hidden>
				<tr height="30">
					<td align="right">名称:&nbsp;</td>
					<td><input id="title" name="task.title" value="${task.title}" /></td>
					<td align="right">任务类型:&nbsp;</td>
					<td><s:select id="type" name="task.type" list="#{'0':'普通任务','1':'里程碑','2':'虚拟任务'}" theme="simple"></s:select></td>
				</tr>
				<tr height="30">
					<td align="right">负责单位:&nbsp;</td>
					<td><input id="taskMan" name="" value="${task.name}" readonly="readonly" /> <img onclick="" src="${vix}/common/img/user.png" width="16" height="16" align="absmiddle"></td>
					<td align="right">优先级:&nbsp;</td>
					<td><s:select id="priority" name="task.priority" list="#{'1':'重要','2':'普通'}" theme="simple"></s:select></td>
				</tr>
				<tr height="30">
					<td align="right">计划开始日期:&nbsp;</td>
					<td><input id="expectBeginTime" name="task.expectBeginTime" value="<s:date name="task.expectBeginTime" format="yyyy-MM-dd"/>" readonly="readonly" /> <img onclick="showTime('expectBeginTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">计划完成日期:&nbsp;</td>
					<td><input id="expectFinishTime" name="task.expectFinishTime" value="<s:date name="task.expectFinishTime" format="yyyy-MM-dd"/>" readonly="readonly" /> <img onclick="showTime('expectFinishTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="30">
					<td align="right">任务进度:&nbsp;</td>
					<td><input id="taskMan" name="task.finishPercent" value="${task.finishPercent}" /></td>
					<td align="right">&nbsp;</td>
					<td></td>
				</tr>
				<tr height="30">
					<td align="right">任务描述:&nbsp;</td>
					<td colspan="3"><textarea id="content" name=""><s:property value="task.content" escape="false" /></textarea></td>
				</tr>
				<tr height="30">
					<td align="right"></td>
					<td colspan="3"><input name="" id="" onclick="javascript:return saveWbsTask()" type="button" class="btn" value="保存" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>