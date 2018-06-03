<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>
<script type="text/javascript">
</script>
<div class="order">
	<dl>
		<dt></dt>
		<dd class="clearfix">
			<div class="order_table" id="abc">
				<div class="voucher newvoucher">
					<dl>
						<dt>
							<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
								<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
								<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
								<s:text name="calculator" /></a>
							</span> <strong>基本信息</strong>
						</dt>
						<dd style="display: block;">
							<form id="employeeEssForm">
								<table style="border: none;">
									<tr height="40">
										<td align="right">姓名：</td>
										<td><input name="entityForm.name" type="text" size="30" value="${entity.name}" /></td>
										<td align="right">曾用名：</td>
										<td><input name="entityForm.oldName" type="text" size="30" value="${entity.oldName}" /></td>

									</tr>
									<tr height="40">
										<td align="right">身份证号：</td>
										<td><input name="entityForm.idNumber" type="text" size="30" value="${entity.idNumber}" /></td>
										<td align="right">出生日期：</td>
										<td><input id="birthDate" name="entityForm.birthday" value="<s:date name="%{entity.birthday}" format="yyyy-MM-dd"/>" type="text" /> <img onclick="showTime('birthDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
									</tr>
									<tr height="40">
										<td align="right">学历：</td>
										<td><input name="entityForm.qualificationsCode" type="text" size="30" value="${entity.qualificationsCode}" /></td>
										<td align="right">科系：</td>
										<td><input name="entityForm.departmentCode" type="text" size="30" value="${entity.departmentCode}" /></td>
									</tr>
									<tr height="40">
										<td align="right">性别：</td>
										<td><s:select list="#{'1':'男','0':'女'}" id="gender" name="entityForm.gender" value="%{entity.gender}" theme="simple"></s:select></td>
										<td align="right">血型：</td>
										<td><s:select list="#{'A':'A','B':'B','AB':'AB','O':'O'}" id="bloodType" name="entityForm.bloodType" value="%{entity.bloodType}" theme="simple"></s:select></td>
									</tr>
								</table>
							</form>
						</dd>
					</dl>
				</div>
			</div>
		</dd>

	</dl>
</div>
<div class="order">
	<dl>
		<div class="clearfix" style="background: #FFF; position: relative;">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="javascript:tab(8,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />工作履历</a></li>
					<li><a onclick="javascript:$('#a2').attr('style','');tab(8,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />政治面貌</a></li>
					<li><a onclick="javascript:$('#a3').attr('style','');tab(8,3,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />军转情况</a></li>
					<li><a onclick="javascript:$('#a4').attr('style','');tab(8,4,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />奖励情况</a></li>
					<li><a onclick="javascript:$('#a5').attr('style','');tab(8,5,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />惩戒情况</a></li>
					<li><a onclick="javascript:$('#a6').attr('style','');tab(8,6,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />兼任情况</a></li>
				</ul>
			</div>
			<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
				<div style="padding: 8px;">
					<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/hr/hrmgr/employeessAction!getWorkRecordJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'id',align:'center',width:120,editor:'text',hidden:'true'">id</th>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'unitToWhichTheyBelong',width:200,align:'center',editor:'text'">所在单位</th>
								<th data-options="field:'workdepartment',width:200,align:'center',editor:'text'">所在部门</th>
								<th data-options="field:'whereThePost',width:200,align:'center',editor:'text'">所在岗位</th>
								<th data-options="field:'workrank',width:200,align:'center',editor:'text'">职级</th>
								<th data-options="field:'professionalExperience',width:200,align:'center',editor:'text'">专业经历</th>
								<th data-options="field:'witnesses',width:200,align:'center',editor:'text'">证明人</th>
								<th data-options="field:'officeSymbol',width:200,align:'center',editor:'text'">任职文号</th>
								<th data-options="field:'remarks',width:200,align:'center',editor:'text'">备注</th>
							</tr>
						</thead>
					</table>
					<div id="dlLineItemTb" style="height: auto">
						<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
							data-options="iconCls:'icon-edit',plain:true" onclick="editItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem()"><span class="l-btn-left"><span
								class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
					</div>
					<script type="text/javascript">
							$('#dlLineItem').datagrid();
							function addItem(){
								$.ajax({
									  url:'${vix}/hr/hrmgr/employeessAction!goAddWorkRecord.action?id=${entity.id}',
									  cache: false,
									  success: function(html){
									 	  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#daForm').validationEngine('validate')){
															$.post('${vix}/hr/hrmgr/employeessAction!saveOrUpdateWorkRecord.action?id=${entity.id}', {
																'workRecord.id' : $("#daId").val(),
																'workRecord.employeeCode' : $("#employeeCode").val(),
																'workRecord.unitToWhichTheyBelong' : $("#unitToWhichTheyBelong").val(),
																'workRecord.workdepartment' : $("#workdepartment").val(),
																'workRecord.whereThePost' : $("#whereThePost").val(),
																'workRecord.workrank' : $("#workrank").val(),
																'workRecord.professionalExperience' : $("#professionalExperience").val(),
																'workRecord.witnesses' : $("#witnesses").val(),
																'workRecord.officeSymbol' : $("#officeSymbol").val(),
																'workRecord.remarks' : $("#remarks").val(),
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlLineItem').datagrid("reload");
																	});
														}else{
										  					return false;
										  				}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function editItem(){
								var rows = $('#dlLineItem').datagrid('getSelected');
								if(null == rows){
									alert("请选择行！");
									return;
									
								}
								$.ajax({
									  url:'${vix}/hr/hrmgr/employeessAction!goAddWorkRecord.action?id=${entity.id}&wordreid='+rows.id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#daForm').validationEngine('validate')){
															$.post('${vix}/hr/hrmgr/employeessAction!saveOrUpdateWorkRecord.action?id=${entity.id}', {
																'workRecord.id' : $("#daId").val(),
																'workRecord.employeeCode' : $("#employeeCode").val(),
																'workRecord.unitToWhichTheyBelong' : $("#unitToWhichTheyBelong").val(),
																'workRecord.workdepartment' : $("#workdepartment").val(),
																'workRecord.whereThePost' : $("#whereThePost").val(),
																'workRecord.workrank' : $("#workrank").val(),
																'workRecord.professionalExperience' : $("#professionalExperience").val(),
																'workRecord.witnesses' : $("#witnesses").val(),
																'workRecord.officeSymbol' : $("#officeSymbol").val(),
																'workRecord.remarks' : $("#remarks").val(),
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlLineItem').datagrid("reload");
																	});
														}else{
										  					return false;
										  				}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function removeItem(){
								var row = $('#dlLineItem').datagrid('getSelected');
								if(row){
									var index = $('#dlLineItem').datagrid('getRowIndex',row);
									$('#dlLineItem').datagrid('deleteRow', index);
									$.ajax({url:'${vix}/hr/hrmgr/employeessAction!deleteWorkRecordById.action?wordreId='+row.id,cache: false});
								}
							}
							
							//为原始Date类型拓展format一个方法，用于日期显示的格式化
							Date.prototype.format = function (format) 
							 {
							     var o = {
							         "M+": this.getMonth() + 1, //month 
							         "d+": this.getDate(),    //day 
							         "h+": this.getHours(),   //hour 
							         "m+": this.getMinutes(), //minute 
							         "s+": this.getSeconds(), //second 
							         "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter 
							         "S": this.getMilliseconds() //millisecond 
							     }
							     if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
							     (this.getFullYear() + "").substr(4 - RegExp.$1.length));
							     for (var k in o) if (new RegExp("(" + k + ")").test(format))
							         format = format.replace(RegExp.$1,
							       RegExp.$1.length == 1 ? o[k] :
							         ("00" + o[k]).substr(("" + o[k]).length));
							     return format;
							 }
							
							//格式化日期
							function formatDatebox(value) {
						         if (value == null || value == '') {
						             return '';
						         }
						     var dt;
						     if (value instanceof Date) {
						         dt = value;
						     }
						     else {
						         dt = new Date(value);
						         if (isNaN(dt)) {
						             value = value.replace(/\/Date\((-?\d+)\)\//, '$1'); //标红的这段是关键代码，将那个长字符串的日期值转换成正常的JS日期格式
						             dt = new Date();
						             dt.setTime(value);
						         }
						     }
						 
						    return dt.format("yyyy-MM-dd");  //这里用到一个javascript的Date类型的拓展方法，这个是自己添加的拓展方法，在后面的步骤3定义
						 }
					</script>
				</div>
			</div>
			<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<div style="padding: 8px;">
					<table id="dlReceivedAddress" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlReceivedAddressTb',url: '${vix}/hr/hrmgr/employeessAction!getPoliticalStatusJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'politicaName',width:200,align:'center',editor:'text'">姓名</th>
								<th data-options="field:'idNumber',width:200,align:'center',editor:'text'">身份证号</th>
								<th data-options="field:'politicalStatus',width:200,align:'center',editor:'text'">政治面貌</th>
								<th data-options="field:'participateInPartyTime',width:200,align:'center',editor:'databox',formatter:formatDatebox">参加党派时间</th>
								<th data-options="field:'joinAPartisanUnit',width:200,align:'center',editor:'text'">参加党派时所在单位</th>
								<th data-options="field:'remarks',width:200,align:'center',editor:'text'">备注</th>
							</tr>
						</thead>
					</table>
					<div id="dlReceivedAddressTb" style="height: auto">
						<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addcItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
							data-options="iconCls:'icon-edit',plain:true" onclick="editcItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removecItem()"><span class="l-btn-left"><span
								class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
					</div>
					<script type="text/javascript">
							$('#dlReceivedAddress').datagrid();
							function addcItem(){
								$.ajax({
									  url:'${vix}/hr/hrmgr/employeessAction!goAddPoliticalStatus.action?id=${entity.id}',
									  cache: false,
									  success: function(html){
									 	  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#caForm').validationEngine('validate')){
															$.post('${vix}/hr/hrmgr/employeessAction!saveOrUpdatePoliticalStatus.action?id=${entity.id}', {
																'politicalStatus.id' : $("#daId").val(),
																'politicalStatus.employeeCode' : $("#employeeCode").val(),
																'politicalStatus.politicaName' : $("#politicaName").val(),
																'politicalStatus.idNumber' : $("#idNumber").val(),
																'politicalStatus.politicalStatus' : $("#politicalStatus").val(),
																'politicalStatus.participateInPartyTime' : $("#participateInPartyTime").val(),
																'politicalStatus.joinAPartisanUnit' : $("#joinAPartisanUnit").val(),
																'politicalStatus.remarks' : $("#remarks").val(),
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlReceivedAddress').datagrid("reload");
																	});
														}else{
										  					return false;
										  				}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function editcItem(){
								var rows = $('#dlReceivedAddress').datagrid('getSelected');
								if(null == rows){
									alert("请选择行！");
									return;
									
								}
								$.ajax({
									  url:'${vix}/hr/hrmgr/employeessAction!goAddPoliticalStatus.action?id=${entity.id}&pid='+rows.id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#caForm').validationEngine('validate')){
															$.post('${vix}/hr/hrmgr/employeessAction!saveOrUpdatePoliticalStatus.action?id=${entity.id}', {
																'politicalStatus.id' : $("#daId").val(),
																'politicalStatus.employeeCode' : $("#employeeCode").val(),
																'politicalStatus.politicaName' : $("#politicaName").val(),
																'politicalStatus.idNumber' : $("#idNumber").val(),
																'politicalStatus.politicalStatus' : $("#politicalStatus").val(),
																'politicalStatus.participateInPartyTime' : $("#participateInPartyTime").val(),
																'politicalStatus.joinAPartisanUnit' : $("#joinAPartisanUnit").val(),
																'politicalStatus.remarks' : $("#remarks").val(),
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlReceivedAddress').datagrid("reload");
																	});
														}else{
										  					return false;
										  				}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function removecItem(){
								var row = $('#dlReceivedAddress').datagrid('getSelected');
								if(row){
									var index = $('#dlReceivedAddress').datagrid('getRowIndex',row);
									$('#dlReceivedAddress').datagrid('deleteRow', index);
									$.ajax({url:'${vix}/hr/hrmgr/employeessAction!deletePoliticalStatusById.action?pId='+row.id,cache: false});
								}
							}
					</script>
				</div>
			</div>
			<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<div style="padding: 8px;">
					<table id="dlDeliveryPlan" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlDeliveryPlanTb',url: '${vix}/hr/hrmgr/employeessAction!getSoldierTuneInfoJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'reTurnType',width:200,align:'center',editor:'text'">复转类型</th>
								<th data-options="field:'militaryRank',width:200,align:'center',editor:'text'">军衔名称</th>
								<th data-options="field:'militaryRankLevel',width:200,align:'center',editor:'text'">级别</th>
								<th data-options="field:'whereTheTroops',width:200,align:'center',editor:'text'">所在部队</th>
								<th data-options="field:'remarks',width:200,align:'center',editor:'text'">备注</th>
							</tr>
						</thead>
					</table>
					<div id="dlDeliveryPlanTb" style="height: auto">
						<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addwItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
							data-options="iconCls:'icon-edit',plain:true" onclick="editwItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removewItem()"><span class="l-btn-left"><span
								class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
					</div>
					<script type="text/javascript">
							$('#dlDeliveryPlan').datagrid();
							function addwItem(){
								$.ajax({
									  url:'${vix}/hr/hrmgr/employeessAction!goAddSoldierTuneInfo.action?id=${entity.id}',
									  cache: false,
									  success: function(html){
									 	  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#waForm').validationEngine('validate')){
															$.post('${vix}/hr/hrmgr/employeessAction!saveOrUpdateSoldierTuneInfo.action?id=${entity.id}', {
																'soldierTuneInfo.id' : $("#daId").val(),
																'soldierTuneInfo.employeeCode' : $("#employeeCode").val(),
																'soldierTuneInfo.reTurnType' : $("#reTurnType").val(),
																'soldierTuneInfo.militaryRank' : $("#militaryRank").val(),
																'soldierTuneInfo.militaryRankLevel' : $("#militaryRankLevel").val(),
																'soldierTuneInfo.whereTheTroops' : $("#whereTheTroops").val(),
																'soldierTuneInfo.remarks' : $("#remarks").val(),
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlDeliveryPlan').datagrid("reload");
																	});
														}else{
										  					return false;
										  				}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function editwItem(){
								var rows = $('#dlDeliveryPlan').datagrid('getSelected');
								if(null == rows){
									alert("请选择行！");
									return;
									
								}
								$.ajax({
									  url:'${vix}/hr/hrmgr/employeessAction!goAddSoldierTuneInfo.action?id=${entity.id}&soid='+rows.id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#waForm').validationEngine('validate')){
															$.post('${vix}/hr/hrmgr/employeessAction!saveOrUpdateSoldierTuneInfo.action?id=${entity.id}', {
																'soldierTuneInfo.id' : $("#daId").val(),
																'soldierTuneInfo.employeeCode' : $("#employeeCode").val(),
																'soldierTuneInfo.reTurnType' : $("#reTurnType").val(),
																'soldierTuneInfo.militaryRank' : $("#militaryRank").val(),
																'soldierTuneInfo.militaryRankLevel' : $("#militaryRankLevel").val(),
																'soldierTuneInfo.whereTheTroops' : $("#whereTheTroops").val(),
																'soldierTuneInfo.remarks' : $("#remarks").val(),
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlDeliveryPlan').datagrid("reload");
																	});
														}else{
										  					return false;
										  				}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function removewItem(){
								var row = $('#dlDeliveryPlan').datagrid('getSelected');
								if(row){
									var index = $('#dlDeliveryPlan').datagrid('getRowIndex',row);
									$('#dlDeliveryPlan').datagrid('deleteRow', index);
									$.ajax({url:'${vix}/hr/hrmgr/employeessAction!deleteSoldierTuneInfoById.action?soId='+row.id,cache: false});
								}
							}
					</script>
				</div>
			</div>
			<div id="a4" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<div style="padding: 8px;">
					<table id="dlInvoice" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlInvoiceTb',url: '${vix}/hr/hrmgr/employeessAction!getAwardInfoJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'awardsLevel',width:200,align:'center',editor:'text'">荣誉奖励级别</th>
								<th data-options="field:'awardsName',width:200,align:'center',editor:'text'">荣誉奖励名称</th>
								<th data-options="field:'awardsReasons',width:200,align:'center',editor:'text'">荣誉奖励原因</th>
								<th data-options="field:'awardsTheDateOfApproval',width:200,align:'center',editor:'databox',formatter:formatDatebox">荣誉奖励批准日期</th>
								<th data-options="field:'awardsApprovedFileName',width:200,align:'center',editor:'text'">荣誉奖励批准文件名</th>
								<th data-options="field:'awardsApprovedFileNumber',width:200,align:'center',editor:'text'">荣誉奖励批准文件号</th>
								<th data-options="field:'awardsApprovedFileNameOfTheAuthority',width:200,align:'center',editor:'text'">荣誉奖励批准机关名称</th>
								<th data-options="field:'remarks',width:200,align:'center',editor:'text'">备注</th>
							</tr>
						</thead>
					</table>
					<div id="dlInvoiceTb" style="height: auto">
						<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="adddItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
							data-options="iconCls:'icon-edit',plain:true" onclick="editdItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removedItem()"><span class="l-btn-left"><span
								class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
					</div>
					<script type="text/javascript">
							$('#dlInvoice').datagrid();
							function adddItem(){
								$.ajax({
									  url:'${vix}/hr/hrmgr/employeessAction!goAddAwardInfo.action?id=${entity.id}',
									  cache: false,
									  success: function(html){
									 	  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#deForm').validationEngine('validate')){
															$.post('${vix}/hr/hrmgr/employeessAction!saveOrUpdateAwardInfo.action?id=${entity.id}', {
																'awardInfo.id' : $("#daId").val(),
																'awardInfo.employeeCode' : $("#employeeCode").val(),
																'awardInfo.awardsLevel' : $("#awardsLevel").val(),
																'awardInfo.awardsName' : $("#awardsName").val(),
																'awardInfo.awardsReasons' : $("#awardsReasons").val(),
																'awardInfo.awardsTheDateOfApproval' : $("#awardsTheDateOfApproval").val(),
																'awardInfo.awardsApprovedFileName' : $("#awardsApprovedFileName").val(),
																'awardInfo.awardsApprovedFileNumber' : $("#awardsApprovedFileNumber").val(),
																'awardInfo.awardsApprovedFileNameOfTheAuthority' : $("#awardsApprovedFileNameOfTheAuthority").val(),
																'awardInfo.remarks' : $("#remarks").val(),
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlInvoice').datagrid("reload");
																	});
														}else{
										  					return false;
										  				}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function editdItem(){
								var rows = $('#dlInvoice').datagrid('getSelected');
								if(null == rows){
									alert("请选择行！");
									return;
									
								}
								$.ajax({
									  url:'${vix}/hr/hrmgr/employeessAction!goAddAwardInfo.action?id=${entity.id}&awid='+rows.id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#deForm').validationEngine('validate')){
															$.post('${vix}/hr/hrmgr/employeessAction!saveOrUpdateAwardInfo.action?id=${entity.id}', {
																'awardInfo.id' : $("#daId").val(),
																'awardInfo.employeeCode' : $("#employeeCode").val(),
																'awardInfo.awardsLevel' : $("#awardsLevel").val(),
																'awardInfo.awardsName' : $("#awardsName").val(),
																'awardInfo.awardsReasons' : $("#awardsReasons").val(),
																'awardInfo.awardsTheDateOfApproval' : $("#awardsTheDateOfApproval").val(),
																'awardInfo.awardsApprovedFileName' : $("#awardsApprovedFileName").val(),
																'awardInfo.awardsApprovedFileNumber' : $("#awardsApprovedFileNumber").val(),
																'awardInfo.awardsApprovedFileNameOfTheAuthority' : $("#awardsApprovedFileNameOfTheAuthority").val(),
																'awardInfo.remarks' : $("#remarks").val(),
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlInvoice').datagrid("reload");
																	});
														}else{
										  					return false;
										  				}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function removedItem(){
								var row = $('#dlInvoice').datagrid('getSelected');
								if(row){
									var index = $('#dlInvoice').datagrid('getRowIndex',row);
									$('#dlInvoice').datagrid('deleteRow', index);
									$.ajax({url:'${vix}/hr/hrmgr/employeessAction!deleteAwardInfoById.action?awId='+row.id,cache: false});
								}
							}
					</script>
				</div>
			</div>
			<div id="a5" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<div style="padding: 8px;">
					<table id="dlPriceConditions" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlPriceConditionsTb',url: '${vix}/hr/hrmgr/employeessAction!getPunInfoJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'disciplinaryCategory',width:200,align:'center',editor:'text'">惩罚级别</th>
								<th data-options="field:'disciplinaryName',width:200,align:'center',editor:'text'">惩罚名称</th>
								<th data-options="field:'disciplinaryReasons',width:200,align:'center',editor:'text'">惩罚原因</th>
								<th data-options="field:'disciplinaryDateOfApproval',width:200,align:'center',editor:'databox',formatter:formatDatebox">惩罚批准日期</th>
								<th data-options="field:'disciplinaryApprovalOfTheFileName',width:200,align:'center',editor:'text'">惩罚批准文件名</th>
								<th data-options="field:'disciplinaryApprovalDocument',width:200,align:'center',editor:'text'">惩罚批准文件号</th>
								<th data-options="field:'disciplinaryApprovalAuthorityName',width:200,align:'center',editor:'text'">惩罚批准机关名称</th>
								<th data-options="field:'revocationOfDisciplinaryDate',width:200,align:'center',editor:'databox',formatter:formatDatebox">撤销惩戒日期</th>
								<th data-options="field:'remarks',width:200,align:'center',editor:'text'">备注</th>
							</tr>
						</thead>
					</table>
					<div id="dlPriceConditionsTb" style="height: auto">
						<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addtItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
							data-options="iconCls:'icon-edit',plain:true" onclick="edittItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removetItem()"><span class="l-btn-left"><span
								class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
					</div>
					<script type="text/javascript">
							$('#dlPriceConditions').datagrid();
							function addtItem(){
								$.ajax({
									  url:'${vix}/hr/hrmgr/employeessAction!goAddPunInfo.action?id=${entity.id}',
									  cache: false,
									  success: function(html){
									 	  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#dtForm').validationEngine('validate')){
															$.post('${vix}/hr/hrmgr/employeessAction!saveOrUpdatePunInfo.action?id=${entity.id}', {
																'punInfo.id' : $("#daId").val(),
																'punInfo.employeeCode' : $("#employeeCode").val(),
																'punInfo.disciplinaryCategory' : $("#disciplinaryCategory").val(),
																'punInfo.disciplinaryName' : $("#disciplinaryName").val(),
																'punInfo.disciplinaryReasons' : $("#disciplinaryReasons").val(),
																'punInfo.disciplinaryDateOfApproval' : $("#disciplinaryDateOfApproval").val(),
																'punInfo.disciplinaryApprovalOfTheFileName' : $("#disciplinaryApprovalOfTheFileName").val(),
																'punInfo.disciplinaryApprovalDocument' : $("#disciplinaryApprovalDocument").val(),
																'punInfo.disciplinaryApprovalAuthorityName' : $("#disciplinaryApprovalAuthorityName").val(),
																'punInfo.revocationOfDisciplinaryDate' : $("#revocationOfDisciplinaryDate").val(),
																'punInfo.remarks' : $("#remarks").val(),
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlPriceConditions').datagrid("reload");
																	});
														}else{
										  					return false;
										  				}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function edittItem(){
								var rows = $('#dlPriceConditions').datagrid('getSelected');
								if(null == rows){
									alert("请选择行！");
									return;
									
								}
								$.ajax({
									  url:'${vix}/hr/hrmgr/employeessAction!goAddPunInfo.action?id=${entity.id}&punid='+rows.id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#dtForm').validationEngine('validate')){
															$.post('${vix}/hr/hrmgr/employeessAction!saveOrUpdatePunInfo.action?id=${entity.id}', {
																'punInfo.id' : $("#daId").val(),
																'punInfo.employeeCode' : $("#employeeCode").val(),
																'punInfo.disciplinaryCategory' : $("#disciplinaryCategory").val(),
																'punInfo.disciplinaryName' : $("#disciplinaryName").val(),
																'punInfo.disciplinaryReasons' : $("#disciplinaryReasons").val(),
																'punInfo.disciplinaryDateOfApproval' : $("#disciplinaryDateOfApproval").val(),
																'punInfo.disciplinaryApprovalOfTheFileName' : $("#disciplinaryApprovalOfTheFileName").val(),
																'punInfo.disciplinaryApprovalDocument' : $("#disciplinaryApprovalDocument").val(),
																'punInfo.disciplinaryApprovalAuthorityName' : $("#disciplinaryApprovalAuthorityName").val(),
																'punInfo.revocationOfDisciplinaryDate' : $("#revocationOfDisciplinaryDate").val(),
																'punInfo.remarks' : $("#remarks").val(),
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlPriceConditions').datagrid("reload");
																	});
														}else{
										  					return false;
										  				}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function removetItem(){
								var row = $('#dlPriceConditions').datagrid('getSelected');
								if(row){
									var index = $('#dlPriceConditions').datagrid('getRowIndex',row);
									$('#dlPriceConditions').datagrid('deleteRow', index);
									$.ajax({url:'${vix}/hr/hrmgr/employeessAction!deletePunInfoById.action?punId='+row.id,cache: false});
								}
							}
					</script>
				</div>
			</div>
			<div id="a6" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<div style="padding: 8px;">
					<table id="dlTechtitle" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlTechtitleTb',url: '${vix}/hr/hrmgr/employeessAction!getPartPostionInfoJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'partTimeType',width:200,align:'center',editor:'text'">兼职类型</th>
								<th data-options="field:'partTimeName',width:200,align:'center',editor:'text'">兼职岗位名称</th>
								<th data-options="field:'partTimeStartDate',width:200,align:'center',editor:'databox',formatter:formatDatebox">兼职开始日期</th>
								<th data-options="field:'partTimeEndDate',width:200,align:'center',editor:'databox',formatter:formatDatebox">兼职结束日期</th>
								<th data-options="field:'positionsSort',width:200,align:'center',editor:'text'">岗位排序</th>
								<th data-options="field:'remarks',width:200,align:'center',editor:'text'">备注</th>
							</tr>
						</thead>
					</table>
					<div id="dlTechtitleTb" style="height: auto">
						<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addTechtitleItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
							data-options="iconCls:'icon-edit',plain:true" onclick="editTechtitleItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeTechtitleItem()"><span
							class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
					</div>
					<script type="text/javascript">
							$('#dlTechtitle').datagrid();
							function addTechtitleItem(){
								$.ajax({
									  url:'${vix}/hr/hrmgr/employeessAction!goAddPartPostionInfo.action?id=${entity.id}',
									  cache: false,
									  success: function(html){
									 	  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#dechtitleForm').validationEngine('validate')){
															$.post('${vix}/hr/hrmgr/employeessAction!saveOrUpdatePartPostionInfo.action?id=${entity.id}', {
																'partPostionInfo.id' : $("#daId").val(),
																'partPostionInfo.employeeCode' : $("#employeeCode").val(),
																'partPostionInfo.partTimeType' : $("#partTimeType").val(),
																'partPostionInfo.partTimeName' : $("#partTimeName").val(),
																'partPostionInfo.partTimeStartDate' : $("#partTimeStartDate").val(),
																'partPostionInfo.partTimeEndDate' : $("#partTimeEndDate").val(),
																'partPostionInfo.positionsSort' : $("#positionsSort").val(),
																'partPostionInfo.remarks' : $("#remarks").val(),
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlTechtitle').datagrid("reload");
																	});
														}else{
										  					return false;
										  				}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function editTechtitleItem(){
								var rows = $('#dlTechtitle').datagrid('getSelected');
								if(null == rows){
									alert("请选择行！");
									return;
									
								}
								$.ajax({
									  url:'${vix}/hr/hrmgr/employeessAction!goAddPartPostionInfo.action?id=${entity.id}&partid='+rows.id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#dechtitleForm').validationEngine('validate')){
															$.post('${vix}/hr/hrmgr/employeessAction!saveOrUpdatePartPostionInfo.action?id=${entity.id}', {
																'partPostionInfo.id' : $("#daId").val(),
																'partPostionInfo.employeeCode' : $("#employeeCode").val(),
																'partPostionInfo.partTimeType' : $("#partTimeType").val(),
																'partPostionInfo.partTimeName' : $("#partTimeName").val(),
																'partPostionInfo.partTimeStartDate' : $("#partTimeStartDate").val(),
																'partPostionInfo.partTimeEndDate' : $("#partTimeEndDate").val(),
																'partPostionInfo.positionsSort' : $("#positionsSort").val(),
																'partPostionInfo.remarks' : $("#remarks").val(),
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlTechtitle').datagrid("reload");
																	});
														}else{
										  					return false;
										  				}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function removeTechtitleItem(){
								var row = $('#dlTechtitle').datagrid('getSelected');
								if(row){
									var index = $('#dlTechtitle').datagrid('getRowIndex',row);
									$('#dlTechtitle').datagrid('deleteRow', index);
									$.ajax({url:'${vix}/hr/hrmgr/employeessAction!deletePartPostionInfoById.action?partId='+row.id,cache: false});
								}
							}
					</script>
				</div>
			</div>
		</div>
	</dl>
</div>
<!--oder-->
<!--submenu-->
<!-- content -->