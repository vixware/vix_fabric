<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="${vix}/common/css/aristo/jquery-ui-1.8.5.custom.css" type="text/css" media="screen" title="no title" charset="utf-8">
<script src="${vix}/common/js/jquery.tokeninput.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.jnotify.js" type="text/javascript"></script>
<script src="${vix}/common/js/underscore-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/backbone-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.tmpl.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-debug.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-tinyPubSub.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.mousewheel.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.ui.ipad.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.global.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	/** 车辆使用申请 */
	function saveOrUpdateOrder(){
		if($('#orderForm').validationEngine('validate')){
			$.post('${vix}/oa/vehicleApplicationsAction!saveOrUpdate.action',
				{
				'carUse.id' : $ ("#id").val () ,
				'carUse.carName' : $ ("#carName").val () ,
				'carUse.pubNames':$("#pubNames").val(),
				'carUse.remarks':$("#remarks").val(),
				'carUse.theme':$("#theme").val(),
				'carUse.pubType':$('input:radio[name=pubType]').val(),
				'carUse.bookingSituation':$('input:radio[name=bookingSituation]').val(), 
				'carUse.vehicleRequest.id':$("#vehicleRequestId").val(),
				'carUse.startTime' : $ ("#startTime").val () ,
				'carUse.endTime' : $ ("#endTime").val () ,
				'carUse.destination' : $ ("#destination").val () ,
				'carUse.mileage' : $ ("#mileage").val () ,
				'carUse.reasons' : $ ("#reasons").val () ,
				'carUse.physicalEnd' : $ ("#physicalEnd").val () 
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/oa/vehicleApplicationsAction!goList.action?menuId=menuContract');
				} 
			);
		}
	}
	
	/** 保存并新增车辆使用申请 */
	function saveOrAdd(){
		if($('#orderForm').validationEngine('validate')){
			$.post('${vix}/oa/vehicleApplicationsAction!saveOrUpdate.action',
				{
				'carUse.id' : $ ("#id").val () ,
				'carUse.carName' : $ ("#carName").val () ,
				'carUse.pubNames':$("#pubNames").val(),
				'carUse.remarks':$("#remarks").val(),
				'carUse.theme':$("#theme").val(),
				'carUse.pubType':$('input:radio[name=pubType]').val(),
				'carUse.bookingSituation':$('input:radio[name=bookingSituation]').val(), 
				'carUse.vehicleRequest.id':$("#vehicleRequestId").val(),
				'carUse.startTime' : $ ("#startTime").val () ,
				'carUse.endTime' : $ ("#endTime").val () ,
				'carUse.destination' : $ ("#destination").val () ,
				'carUse.mileage' : $ ("#mileage").val () ,
				'carUse.reasons' : $ ("#reasons").val () ,
				'carUse.physicalEnd' : $ ("#physicalEnd").val () 
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/oa/vehicleApplicationsAction!goSaveOrUpdate.action');
				} 
			);
		}
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
		$("#pubNames").val("");
	}

	/**
	 * 添加发布对象
	 */
	function addBulletinPubobject(){
		
		var pubTypeVal = $("input[name=carUse.pubType]:checked").val();
		//debugger;
		if(pubTypeVal=="O"){
			chooseBulletinOrgUnit($("#pubIds").val());
		}else if(pubTypeVal=="R"){
			chooseBulletinRole($("#pubIds").val());
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
										//alert(returnValue);
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
											//alert(selectIds)
											$("#pubIds").val(selectIds);
											$("#pubNames").val(selectNames);
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
	 * 选择角色
	 */
	function chooseBulletinRole(checkObjIds){
		$.ajax({
			  url:'${vix}/common/select/commonSelectRoleAction!goList.action',
			  data:{chkStyle:"checkbox"},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 340,
						title:"选择角色",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									//alert(returnValue);
									var selectIds = "";
									var selectNames = "";

									var pubIdTmp = $("#pubIds").val();
									
									pubIdTmp = pubIdTmp + ",";
									
									/* if(resObj.length == 0 ){
										return;
									} */
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
									selectNames = $("#pubNames").val()+selectNames;
									
									$("#pubIds").val(selectIds);
									selectNames = selectNames.substring(1,selectNames.length);
									$("#pubNames").val(selectNames);
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
						width : 560,
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
									
									/* if(resObj.length == 0 ){
										return;
									} */
									//debugger;
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
									selectNames = $("#pubNames").val()+selectNames;
									
									$("#pubIds").val(selectIds);
									selectNames = selectNames.substring(1,selectNames.length);
									$("#pubNames").val(selectNames);
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	
	/**用车人*/
	function chooseCarName(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
			  data:{chkStyle:"checkbox"},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 800,
						height : 600,
						title:"选择用车人",
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
									$("#carName").val(selectNames);
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	function chooseCar() {
		$.ajax({
		url : '${vix}/oa/vehicleApplicationsAction!goChooseCar.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "查看车辆",
			html:html,
			callback : function(action){
				if(action == 'ok'){
						}
					} ,
					btnsbar : [{
						text :'关闭',
						action :'cancel'
					}]
			});
		  }
		});
	};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="${vix}/common/img/icon_14.gif" alt="" />
			<s:text name="print" /></a> <a href="#" id="help"><img src="${vix}/common/img/icon_15.gif" alt="" />
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/mdm_vehicleRequest.png" alt="" /> 协同办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/vehicleApplicationsAction!goList.action?pageNo=${pageNo}');">行政办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/vehicleApplicationsAction!goList.action?pageNo=${pageNo}');">车辆申请安排</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/vehicleApplicationsAction!goList.action?pageNo=${pageNo}');">车辆使用管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<form id="orderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="javascript:void(0)" onclick="saveOrAdd()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/oa/vehicleApplicationsAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="车辆使用申请" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">主题：</th>
											<td><input id="theme" name="theme" value="${carUse.theme}" type="text" size="26" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">车牌号：</th>
											<td><s:select id="vehicleRequestId" headerKey="-1" headerValue="--请选择--" list="%{vehicleRequestList}" listValue="plateNumber" listKey="id" value="%{carUse.vehicleRequest.id}" multiple="false" theme="simple">
												</s:select> <span style="color: red;">*</span> <input class="btn" type="button" value="查看车辆" onclick="chooseCar();" /></td>
											<th align="right">用车人：</th>
											<td><input id="carName" name="carName" value="${carUse.carName}" type="text" size="29" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseCarName();" /> <span style="color: red;">*</span>
											</td>
										</tr>
										<tr style="display: none;">
											<td><s:radio list="#{'0':'待批车辆','1':'已准申请','2':'被驳回','3':'完成'}" id="bookingSituation" name="bookingSituation" value="%{carUse.bookingSituation==0}" theme="simple"></s:radio></td>
										</tr>
										<tr>
											<td class="tr">随便行人员：</td>
											<td colspan="3"><input type="hidden" id="id" name="id" value="${carUse.id}" /> <s:radio id="pubType" list="#{'O':'部门','R':'角色','E':'人员'}" name="pubType" value="%{carUse.pubType}" onchange="changePubType(this.value);" theme="simple"></s:radio> <a href="javascript:void(0);" onclick="addBulletinPubobject()"><img
													src="img/icon_25.gif" />新增</a>&nbsp;&nbsp; <a href="#" onclick="clearPubType()"><img src="img/delete.gif" />清空</a> <br /> <textarea rows="6" cols="6" id="pubNames" name="pubNames" style="width: 820px; height: 114px;">${carUse.pubNames}</textarea> <input type="hidden" id="pubIds" name="carUse" value="${carUse.pubIds}" /></td>
										</tr>
										<tr>
											<th align="right">开始日期：</th>
											<td><input id="startTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm:ss" name="startTime" onclick="showTime('startTime','yyyy-MM-dd HH:mm:ss')" value="<fmt:formatDate value='${carUse.startTime}' type='both' pattern='yyyy-MM-dd HH:mm:ss' />" type="text" size="26" /> <img
												onclick="showTime('startTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> <span style="color: red;">*</span></td>
											<th align="right">结束日期：</th>
											<td><input id="endTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm:ss" name="endTime" onclick="showTime('endTime','yyyy-MM-dd HH:mm:ss')" value="<fmt:formatDate value='${carUse.endTime}' type='both' pattern='yyyy-MM-dd HH:mm:ss' />" type="text" size="26" /> <img
												onclick="showTime('endTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">目的地：</th>
											<td><input id="destination" name="destination" value="${carUse.destination}" type="text" size="26" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
											<th align="right">申请里程：</th>
											<td><input id="mileage" name="mileage" value="${carUse.mileage}" type="text" size="26" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">事由：</th>
											<td colspan="3"><textarea id="reasons" name="reasons" class="example" rows="2" style="width: 823px; height: 74px;">${carUse.reasons}</textarea></td>
										</tr>
										<tr>
											<th align="right">备注：</th>
											<td colspan="3"><textarea id="remarks" name="remarks" class="example" rows="2" style="width: 823px; height: 74px;">${carUse.remarks}</textarea></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
			</dl>
		</div>
	</form>
</div>







