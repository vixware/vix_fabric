<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
/**审批人*/
function chooseEmployees(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 800,
					height : 600,
					title:"选择审批人",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var selectIds = "";
								var selectNames = "";
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										
										selectIds += "," + v[0];
										selectNames = v[1];
									}
								}
								$("#approver").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>
<style>
.content {
	margin-bottom: 0;
}

.cardTable {
	padding: 0 10px;
}

.cardTable table th, .cardTable table td {
	padding: 5px;
	vertical-align: top;
	border: #CCC solid 1px;
}

.cardTable table th {
	background: #DCE7F1;
}

.cardTable table .tr {
	text-align: right;
}

.cardTable .popupArea {
	height: 300px;
}

.cardTable .checkbox {
	vertical-align: middle;
}

.cardTable label {
	margin-right: 10px;
}
</style>
<link href="${vix}/common/css/token-input.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/css/token-input-facebook.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/jquery.jnotify.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/grid.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/core.js" type="text/javascript"></script>
<script src="${vix}/common/js/mousewheel.js" type="text/javascript"></script>
<script src="${vix}/common/js/combo.js" type="text/javascript"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript"></script>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<div class="box cardTable">
		<form id="brandForm">
			<input type="hidden" id="id" name="id" value="${personalAttendance.id}" />
			<table width="100%">
				<tr>
					<th class="tr" width="150">出差人：</th>
					<td><input id="businessPeople" name="personalAttendance.businessPeople" value="${personalAttendance.businessPeople}" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr" width="150">出差地点：</th>
					<td><input id="businessLocation" name="personalAttendance.businessLocation" value="${personalAttendance.businessLocation}" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<!-- <tr>
    <th class="tr">申请时间：</th>
    	<td><input type="text" name="" class="time_aoto_input ipt" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'blue'})"></td>
    </tr> -->
				<tr>
					<th class="tr">外出时间：</th>
					<td><input id="businessstartDate" type="text" name="businessstartDate" value="${personalAttendance.businessstartDate}" class="time_aoto_input ipt" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'blue'})">&nbsp;&nbsp;至&nbsp;&nbsp; <input id="businessendDate" type="text" name="businessendDate"
						value="${personalAttendance.businessendDate}" class="time_aoto_input ipt" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'blue'})">&nbsp;&nbsp;共&nbsp;&nbsp;<input id="travelDays" type="text" name="travelDays" value="${personalAttendance.travelDays}" style="width: 25px;" class="ipt" />&nbsp;&nbsp;天&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<th class="tr">事由：</th>
					<td colspan="3"><textarea id="reason" class="example" rows="1" style="width: 508px; height: 75px;">${personalAttendance.reason}</textarea></td>
				</tr>
				<tr>
					<th class="tr">审批人：</th>
					<td><input id="approver" name="approver" value="${personalAttendance.approver}" type="text" style="width: 200px; height: 22px;" validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseEmployees();" /></td>
				</tr>
				<tr>
					<th align="right">是否用车：</th>
					<td><s:radio list="#{'0':'是','1':'否'}" name="isPublish" value="%{personalAttendance.isPublish}" theme="simple"></s:radio></td>
				</tr>
				<tr>
					<td class="tr">事务提醒：</td>
					<td><label> <input name="" type="checkbox" value="" class="checkbox" />发送事务提醒消息
					</label></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>