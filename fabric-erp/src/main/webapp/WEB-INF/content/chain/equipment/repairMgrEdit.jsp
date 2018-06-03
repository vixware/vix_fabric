<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style type="text/css">
.order dd table td {
	padding: 3px 5px;
}
</style>
<script type="text/javascript">
$(function(){
	_p_a_load('${vix}/chain/mmxEquipmentAction!repairMgrFeedbackList.action?id=${repairRecord.id}','feedback_data');
	setEqTitle();
	
	if($('#mendianId').val()!=''){
		changeMendian('${repairRecord.eqCode}');
	}
});

var isSaving = false;
function saveOrUpdateRepairRecord(){
	if(isSaving)
		return;
	isSaving = true;
	
	if($('#repairRecordForm #eqTitle').val()==''){
    	showMessage('请选择机台设备');
    	return false;
	}
	if($('#repairRecordForm #eqCode').val()==''){
    	showMessage('请选择机台设备');
    	return false;
	}

	$("#repairRecordForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/chain/mmxEquipmentAction!saveOrUpdateRepairRecord.action",
	     dataType: "text",
	     success: function(result){
	    	if(result != null){
		    	var dataId = result;
		    	$('#repairRecordForm #repairRecordId').val(dataId);
		    	showMessage('信息保存完毕');
		    
		    	_tab_close_current_and_reload_opener_grid();
		    	//_pad_grid_loadPage('tab_home');
		    	
		    	isSaving = false;
	    	}else{
	    		showErrorMessage('信息保存失败');
		    	isSaving = false;
	    	}
	     }
	 });
	return false;
}

function setEqTitle(){
	$('#eqTitle').val($("#repairRecordForm #eqCode option:selected").text());
}

function chooseParentOrganization() {
	$.ajax({
	url : '${vix}/drp/storeInformationAction!goChooseOrganization.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 560,
		height : 340,
		title : "选择门店",
		html : html,
		callback : function(action, returnValue) {
			if (action == 'ok') {
				if (returnValue != undefined) {
					var result = returnValue.split(",");
					$("#mendian").val(result[1]);
					$("#mendianId").val(result[0]);
					$("#mendianCode").val(result[2]);
					changeMendian();
				} else {
					$("#mendian").val("");
					$("#mendianId").val("");
					$("#mendianCode").val("");
					asyncbox.success("请选择公司信息!", "提示信息");
				}
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
}

function changeMendian(selectedVal){
	var mendianId = $("#mendianId").val();

	$('#eqCode').html('');
	$('#eqTitle').val('');
	
	$.post("${vix}/chain/mmxEquipmentAction!loadRidesByChannelDistributorId.action",
		{'id':mendianId},
		function(json){
			if(json){
				var rides = json.rows;
				$.each(rides,function(i,ride){
					var newRides = $('<option/>');
					newRides.val(ride.macid);
					newRides.text(ride.macname);
					$('#eqCode').append(newRides)
					if(i==0){
						$('#eqTitle').val(newRides.text());
					}
				});
				if(selectedVal)
					$('#eqCode').val(selectedVal);
			}
		},
		'json'
	);
}
</script>

<div class="content" style="margin-top: 10px;">
	<form id="repairRecordForm" action="" method="post">
		<input type="hidden" id="repairRecordId" name="repairRecord.id" value="${repairRecord.id}" />
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateRepairRecord()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a>
					</span>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">门店：</td>
											<td><input id="mendian" name="repairRecord.mendian" onclick="chooseParentOrganization();" readonly="readonly" type="text" size="30" value="${repairRecord.mendian}" readonly="readonly" /> <input id="mendianId" name="repairRecord.mendianId" value="${repairRecord.mendianId}" type="hidden" /> <input class="btn" type="button"
												value="选择" onclick="chooseParentOrganization();" /></td>
											<td align="right">门店编码：</td>
											<td><input id="mendianCode" name="repairRecord.mendianCode" readonly="readonly" type="text" size="30" value="${repairRecord.mendianCode}" readonly="readonly" /></td>
										</tr>
										<tr>
											<td align="right">设备：</td>
											<td><select name="repairRecord.eqCode" id="eqCode" onchange="setEqTitle()" style="width: 230px;">
													<s:iterator value="rideList" var="entity">
														<option value="${entity.macid}" <s:if test="#entity.macid==repairRecord.eqCode">selected</s:if>>${entity.macname}</option>
													</s:iterator>
											</select> <input id="eqTitle" name="repairRecord.eqTitle" type="hidden" size="30" value="${repairRecord.eqTitle}" /></td>
											<td align="right"></td>
											<td></td>
										</tr>
										<tr>
											<td align="right">报修说明：</td>
											<td colspan="3"><textarea id="repairNote" name="repairRecord.note" style="width: 450px; height: 230px;">${repairRecord.note}</textarea></td>
										</tr>
										<tr>
											<td align="right">维修级别：</td>
											<td><s:select id="repairLevel" name="repairRecord.repairLevel" value="repairRecord.repairLevel" list="#{'0':'普通','1':'优先 ','2':'紧急','-1':'低级'}" theme="simple"></s:select></td>
											<td align="right">期望完成期限：</td>
											<td><input id="hopeRepairBefore" name="repairRecord.hopeRepairBefore" value="<s:date name="%{repairRecord.hopeRepairBefore}" format="yyyy-MM-dd"/>" readonly="readonly" onclick="showTime('hopeRepairBefore','yyyy-MM-dd')" type="text" /> <img onclick="showTime('hopeRepairBefore','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif"
												width="16" height="22" align="middle"></td>
										</tr>
										<tr>
											<td align="right">相关附件：</td>
											<td colspan="3"><input type="file" id="fileToUpload" name="fileToUpload" style="width: 450px;" /> <s:if test="repairRecord.docFileName!=null">
												&nbsp;
												<a href="${vix}/chain/mmxEquipmentAction!downloadRepairDoc.action?id=${repairRecord.id}">下载文件</a>
												&nbsp;
												<span style="color: #777777;">新上传文件将覆盖以上传文件</span>
												</s:if></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>


						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong>反馈信息</strong>
								</dt>
								<dd id="feedback_data" style="display: block;"></dd>
							</dl>
						</div>

					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>