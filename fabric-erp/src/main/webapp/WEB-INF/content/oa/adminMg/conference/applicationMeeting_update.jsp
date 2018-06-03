<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$(function() {
	  //默认选择部门
    if($('input:radio[name=pubType]:checked').length==0){
    	$('input:radio[name=pubType]:first').trigger('click');
    }
})

$("#orderForm").validationEngine();
/** 保存会议记录 */
function saveOrUpdateOrder() {
	if ($('#orderForm').validationEngine('validate')) {
		$.post('${vix}/oa/applicationMeetingAction!saveOrUpdate.action', {
		'applicationMg.id' : $("#id").val(),
		'applicationMg.proposer' : $("#proposer").val(),
		'applicationMg.participants' : $("#participants").val(),
		'applicationMg.bookingSituation' : $("#bookingSituation").val(),
		'applicationMg.meetingRequest.id' : $("#meetingRequestId").val(),
		'applicationMg.meetingTheme' : $("#meetingTheme").val(),
		'applicationMg.meetingDescription' : $("#meetingDescription").val(),
		'applicationMg.meetingStartTime' : $("#meetingStartTime").val(),
		'applicationMg.pubType':$('input:radio[name=pubType]:checked').val(),
		'applicationMg.meetingEndTime' : $("#meetingEndTime").val()
		}, function(result) {
			showMessage(result);
			setTimeout("", 1000);
			loadContent('${vix}/oa/applicationMeetingAction!goList.action');
		});
	}
}
/** 保存并新增会议记录 */
function saveOrAdd() {
	if ($('#orderForm').validationEngine('validate')) {
		$.post('${vix}/oa/applicationMeetingAction!saveOrUpdate.action', {
		'applicationMg.id' : $("#id").val(),
		'applicationMg.proposer' : $("#proposer").val(),
		'applicationMg.participants' : $("#participants").val(),
		'applicationMg.bookingSituation' : $("#bookingSituation").val(),
		'applicationMg.meetingRequest.id' : $("#meetingRequestId").val(),
		'applicationMg.meetingTheme' : $("#meetingTheme").val(),
		'applicationMg.meetingDescription' : $("#meetingDescription").val(),
		'applicationMg.pubType':$('input:radio[name=pubType]:checked').val(),
		'applicationMg.meetingStartTime' : $("#meetingStartTime").val(),
		'applicationMg.meetingEndTime' : $("#meetingEndTime").val()
		}, function(result) {
			showMessage(result);
			setTimeout("", 1000);
			loadContent('${vix}/oa/applicationMeetingAction!goSaveOrUpdate.action');
		});
	}
}

/**申请人*/
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
					title:"选择申请人",
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
								$("#proposer").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

/**
 * 变更选择发布类型
 */
function changePubType(pubTypeValue){
	clearPubType();
}

/**
 * 清空选择对象
 */
function clearPubType(){
	$("#pubIds").val("");
	$("#participants").val("");
}

/**
 * 添加参与对象
 */
function addApplicationMg(){
	
	var pubTypeVal = $("input[name='pubType']:checked").val();
	//debugger;
	if(pubTypeVal=="O"){
		chooseBulletinOrgUnit($("#pubIds").val());
	}else if(pubTypeVal=="E"){
		chooseBulletinEmp($("#pubIds").val());
	}
}

/**
 * 选择部门
 */
 function chooseBulletinOrgUnit(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
		  data:{chkStyle:"checkbox",canCheckComp:0},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择部门",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var selectIds = "";
								var selectNames = "";
							
								var resObj = $.parseJSON(returnValue);
								
								for(var i=0;i<resObj.length;i++){
									selectIds += "," + resObj[i].treeId;
									selectNames += "," + resObj[i].name;
								}

								if(selectIds!=''){
									selectIds = selectIds.substring(1);
									selectNames = selectNames.substring(1);
									$("#pubIds").val(selectIds);
									$("#participants").val(selectNames);
								}
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
 
/**
 * 选择人员
 */
function chooseBulletinEmp(checkObjIds){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 340,
					title:"选择人员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//alert(returnValue);
								var selectIds = "";
								var selectNames = "";
							
								var pubIdTmp = $("#pubIds").val();
								
								pubIdTmp = pubIdTmp + ",";
								
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										if(!pubIdTmp.contains(v[0]+",")){
											selectIds += "," + v[0];
											selectNames += "," + v[1];
										}
									}
								}
								
								selectIds = $("#pubIds").val()+selectIds;
								selectNames = $("#participants").val()+selectNames;
								
								$("#pubIds").val(selectIds);
								selectNames = selectNames.substring(1,selectNames.length);
								$("#participants").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}


/* 根据会议室的ID查询看会议室的申请使用时间 */
function getMeetingRequestId(){
	$.ajax({
		  url:'${vix}/oa/applicationMeetingAction!goMeetingRequest.action',
		  data:{meetingRequestId:$("#meetingRequestId").val()},
		  cache: false,
		  dataType:"json",
		  success: function(data){
			  if(data.success){
				   var htmlsMeetingStartTime = "";
				   var htmlsMeetingEndTime = "";
				   $("#meetingStartTimeID").html("");
				   $("#meetingEndTimeID").html("");
				   var jsonValue = eval(data);
					  //alert(jsonValue.useTime[0].meetingStartTime);
					for(var i=0;i<jsonValue.useTime.length;i++){
						htmlsMeetingStartTime += "<input class='sweet-tooltip' data-text-tooltip='时间格式 yyyy-MM-dd HH:mm:ss' name='meetingStartTime' value='"+jsonValue.useTime[i].meetingStartTime+"' type='text' size='29' /><br />";
						htmlsMeetingEndTime += "<input class='sweet-tooltip' data-text-tooltip='时间格式 yyyy-MM-dd HH:mm:ss' name='meetingEndTime' value='"+jsonValue.useTime[i].meetingEndTime+"' type='text' size='29' /><br />";
				    }
					$("#meetingStartTimeID").html(htmlsMeetingStartTime);
					$("#meetingEndTimeID").html(htmlsMeetingEndTime);
			  }
		  }
	   });
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a></i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_inbound.png" alt="" /> <s:text name="协同办公" /> </a></li>
				<li><a href="#"><s:text name="行政办公中心" /> </a></li>
				<li><a href="#"><s:text name="会议室申请安排" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/applicationMeetingAction!goList.action?menuId=menuInbound');"><s:text name="会议室申请" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${applicationMg.id}" />
<input type="hidden" id="bookingSituation" name="bookingSituation" value="0" />
<div class="content">
	<form id="orderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="javascript:void(0)" onclick="saveOrAdd()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/oa/applicationMeetingAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="会议室申请" /> </b><i></i> </strong>
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
											<th>会议主题：</th>
											<td><input id="meetingTheme" name="applicationMg.meetingTheme" size="29" value="${applicationMg.meetingTheme}" class="validate[required] text-input" type="text" /> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>申请人：</th>
											<td><input id="proposer" name="proposer" value="${applicationMg.proposer}" type="text" size="29" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseEmployees();" /></td>
											<th>会议室：</th>
											<td><s:select id="meetingRequestId" headerKey="-1" headerValue="--请选择--" list="%{meetingRequestList}" listValue="meetingName" listKey="id" value="%{applicationMg.meetingRequest.id}" multiple="false" theme="simple">
												</s:select> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">会议描述：</th>
											<td><br /> <textarea rows="6" cols="6" id="meetingDescription" name="meetingDescription" style="width: 281px; height: 47px;">${applicationMg.meetingDescription}</textarea></td>
											<th align="right">参对象：</th>
											<td><input type="hidden" id="id" name="id" value="${applicationMg.id}" /> <s:radio id="pubType" list="#{'O':'参与部门','E':'参与人'}" name="pubType" value="%{applicationMg.pubType}" onchange="changePubType(this.value);" theme="simple"></s:radio> <a href="javascript:void(0);" onclick="addApplicationMg()"><img src="img/icon_25.gif" />新增</a>&nbsp;&nbsp;
												<a href="#" onclick="clearPubType()"><img src="img/delete.gif" />清空</a> <br /> <textarea rows="6" cols="6" id="participants" name="participants" style="width: 281px; height: 47px;">${applicationMg.participants}</textarea></td>
										</tr>
										<!-- 暂时屏蔽业务更改暂时不需要 -->
										<%-- <th>会议室：</th>
											<td>
												<s:select id="meetingRequestId" headerKey="-1" headerValue="--请选择--" 
													list="%{meetingRequestList}" listValue="meetingName" listKey="id" 
													value="%{applicationMg.meetingRequest.id}" multiple="false" theme="simple" onchange="getMeetingRequestId()">
												</s:select>
												<span style="color: red;">*</span>
											</td> --%>
										<!-- <tr>
											<th align="right">已选开始时间：</th>
											<td id="meetingStartTimeID"></td>
											<th align="right">已选结束时间：</th>
											<td id="meetingEndTimeID"></td>
										</tr> -->
										<%-- <tr>
											<th align="right">开始时间：</th>
											<td>
												<input id="meetingStartTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm:ss" name="meetingStartTime" onclick="showTime('meetingStartTime','yyyy-MM-dd HH:mm:ss')"
												value="<fmt:formatDate value='${applicationMg.meetingStartTime}' type='both' pattern='yyyy-MM-dd HH:mm:ss' />"
												type="text"  size="29" /> 
												<img onclick="showTime('meetingStartTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">
												<span style="color: red;">*</span>
											</td>
											<th align="right">结束时间：</th>
											<td>
												<input id="meetingEndTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm:ss" name="meetingEndTime" onclick="showTime('meetingEndTime','yyyy-MM-dd HH:mm:ss')"
												value="<fmt:formatDate value='${applicationMg.meetingEndTime}' type='both' pattern='yyyy-MM-dd HH:mm:ss' />"
												type="text" size="29" /> 
												<img onclick="showTime('meetingEndTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">
												<span style="color: red;">*</span>
											</td>
										</tr>	 --%>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />申请设备明细</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveDeliveryAddress(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									title : '申请设备明细',
									modal : true,
									width : 724,
									height : 400,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#orderItemForm').validationEngine('validate')) {
												$.post('${vix}/oa/applicationMeetingAction!saveOrUpdateEquipmentDetails.action',
														{'id':$("#id").val(),
														'equipmentDetails.id' : $("#daId").val(),
														'equipmentDetails.equipmentName' : $("#equipmentName").val()
														},
														function(result){
															showMessage(result);
															setTimeout("",1000);
															$('#dlAddress2').datagrid("reload");
														}
												);
											}
											}
											},
											btnsbar : $.btn.OKCANCEL
										});
									  }
								});
							}
							$('#dlAddress2').datagrid({
							url : '${vix}/oa/applicationMeetingAction!getEquipmentDetailsJson.action?id=${applicationMg.id}',
							width : 'auto',
							height : 450,
							pagination : true,
							rownumbers : true,
							sortOrder : 'desc',
							striped : true,
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							}, {
							field : 'equipmentName',
							title : '申请设备名称',
							width : 900,
							align : 'center'
							}] ],
							
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress('${vix}/oa/applicationMeetingAction!goAddEquipmentDetails.action?=${applicationMg.id}');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/oa/applicationMeetingAction!getEquipmentDetailsJson.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#dlAddress2').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#dlAddress2').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#dlAddress2').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/oa/applicationMeetingAction!deleteEquipmentDetailsById.action?id=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
							function format(date) {
								var y = date.getFullYear();
								var m = date.getMonth() + 1;
								var d = date.getDate();
								return y + '-' + m + '-' + d;
							}
						</script>
						<div style="padding: 8px;">
							<table id="dlAddress2"></table>
						</div>
					</div>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
