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
					<li class="current"><a onclick="javascript:tab(8,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />离退情况</a></li>
					<li><a onclick="javascript:$('#a2').attr('style','');tab(8,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />流动情况</a></li>
					<li><a onclick="javascript:$('#a3').attr('style','');tab(8,3,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />出国/出境情况</a></li>
					<li><a onclick="javascript:$('#a4').attr('style','');tab(8,4,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />家庭成员及社会关系</a></li>
					<li><a onclick="javascript:$('#a5').attr('style','');tab(8,5,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />伤残病</a></li>
				</ul>
			</div>
			<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
				<div style="padding: 8px;">
					<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/hr/employeesAction!getRetireInfoJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'id',align:'center',width:120,editor:'text',hidden:'true'">id</th>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'retiredCategory',width:200,align:'center',editor:'text'">离退休类别</th>
								<th data-options="field:'rankSequence',width:200,align:'center',editor:'text'">职级序列</th>
								<th data-options="field:'retirementDate',width:200,align:'center',editor:'databox',formatter:formatDatebox">离退休日期</th>
								<th data-options="field:'pensions',width:200,align:'center',editor:'text'">离休金</th>
								<th data-options="field:'retirementPay',width:200,align:'center',editor:'text'">退休金</th>
								<th data-options="field:'subsistenceAllowanceFor',width:200,align:'center',editor:'text'">退职生活费</th>
								<th data-options="field:'medicalFee',width:200,align:'center',editor:'text'">医疗费</th>
								<th data-options="field:'supplementaryMedicalInsuranceToPayMedicalExpensesPart',width:200,align:'center',editor:'text'">补充医疗保险支付医疗费部分</th>
								<th data-options="field:'livingAllowanceEnterprise',width:200,align:'center',editor:'text'">生活补贴(企业)*/</th>
								<th data-options="field:'livingAllowanceIndustry',width:200,align:'center',editor:'text'">生活补贴(行业)</th>
								<th data-options="field:'livingAllowanceLocal',width:200,align:'center',editor:'text'">*生活补贴(地方)</th>
								<th data-options="field:'aoneTimeSubsidyLocal',width:200,align:'center',editor:'text'">一次性补贴(地方)</th>
								<th data-options="field:'aoneTimeSubsidySpecial',width:200,align:'center',editor:'text'">一次性补贴(特殊工种)</th>
								<th data-options="field:'aoneTimeSubsidyIllnessRetreat',width:200,align:'center',editor:'text'">一次性补贴(病退)</th>
								<th data-options="field:'shouldTheTotal',width:200,align:'center',editor:'text'">应发总额</th>
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
									  url:'${vix}/hr/employeesAction!goAddRetireInfo.action?id=${entity.id}',
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
															$.post('${vix}/hr/employeesAction!saveOrUpdateRetireInfo.action?id=${entity.id}', {
																'retireInfo.id' : $("#daId").val(),
																'retireInfo.employeeCode' : $("#employeeCode").val(),
																'retireInfo.retiredCategory' : $("#retiredCategory").val(),
																'retireInfo.rankSequence' : $("#rankSequence").val(),
																'retireInfo.retirementDate' : $("#retirementDate").val(),
																'retireInfo.pensions' : $("#pensions").val(),
																'retireInfo.retirementPay' : $("#retirementPay").val(),
																'retireInfo.subsistenceAllowanceFor' : $("#subsistenceAllowanceFor").val(),
																'retireInfo.medicalFee' : $("#medicalFee").val(),
																'retireInfo.supplementaryMedicalInsuranceToPayMedicalExpensesPart' : $("#supplementaryMedicalInsuranceToPayMedicalExpensesPart").val(),
																'retireInfo.livingAllowanceEnterprise' : $("#livingAllowanceEnterprise").val(),
																'retireInfo.livingAllowanceIndustry' : $("#livingAllowanceIndustry").val(),
																'retireInfo.livingAllowanceLocal' : $("#livingAllowanceLocal").val(),
																'retireInfo.aoneTimeSubsidyLocal' : $("#aoneTimeSubsidyLocal").val(),
																'retireInfo.aoneTimeSubsidySpecial' : $("#aoneTimeSubsidySpecial").val(),
																'retireInfo.aoneTimeSubsidyIllnessRetreat' : $("#aoneTimeSubsidyIllnessRetreat").val(),
																'retireInfo.shouldTheTotal' : $("#shouldTheTotal").val(),
																'retireInfo.remarks' : $("#remarks").val(),
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
									  url:'${vix}/hr/employeesAction!goAddRetireInfo.action?id=${entity.id}&lrid='+rows.id,
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
															$.post('${vix}/hr/employeesAction!saveOrUpdateRetireInfo.action?id=${entity.id}', {
																'retireInfo.id' : $("#daId").val(),
																'retireInfo.employeeCode' : $("#employeeCode").val(),
																'retireInfo.retiredCategory' : $("#retiredCategory").val(),
																'retireInfo.rankSequence' : $("#rankSequence").val(),
																'retireInfo.retirementDate' : $("#retirementDate").val(),
																'retireInfo.pensions' : $("#pensions").val(),
																'retireInfo.retirementPay' : $("#retirementPay").val(),
																'retireInfo.subsistenceAllowanceFor' : $("#subsistenceAllowanceFor").val(),
																'retireInfo.medicalFee' : $("#medicalFee").val(),
																'retireInfo.supplementaryMedicalInsuranceToPayMedicalExpensesPart' : $("#supplementaryMedicalInsuranceToPayMedicalExpensesPart").val(),
																'retireInfo.livingAllowanceEnterprise' : $("#livingAllowanceEnterprise").val(),
																'retireInfo.livingAllowanceIndustry' : $("#livingAllowanceIndustry").val(),
																'retireInfo.livingAllowanceLocal' : $("#livingAllowanceLocal").val(),
																'retireInfo.aoneTimeSubsidyLocal' : $("#aoneTimeSubsidyLocal").val(),
																'retireInfo.aoneTimeSubsidySpecial' : $("#aoneTimeSubsidySpecial").val(),
																'retireInfo.aoneTimeSubsidyIllnessRetreat' : $("#aoneTimeSubsidyIllnessRetreat").val(),
																'retireInfo.shouldTheTotal' : $("#shouldTheTotal").val(),
																'retireInfo.remarks' : $("#remarks").val(),
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
									$.ajax({url:'${vix}/hr/employeesAction!deleteRetireInfoById.action?lrId='+row.id,cache: false});
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
					<table id="dlReceivedAddress" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlReceivedAddressTb',url: '${vix}/hr/employeesAction!getRedeployInfoJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'secondedbeforeUnits',width:200,align:'center',editor:'text'">借调前原单位</th>
								<th data-options="field:'secondedbeforeDep',width:200,align:'center',editor:'text'">借调前原部门</th>
								<th data-options="field:'secondedbeforePost',width:200,align:'center',editor:'text'">借调前原岗位</th>
								<th data-options="field:'secondedDestinedForUnits',width:200,align:'center',editor:'text'">借调去往单位</th>
								<th data-options="field:'secondedDestinedSector',width:200,align:'center',editor:'text'">借调去往部门</th>
								<th data-options="field:'secondedPosts',width:200,align:'center',editor:'text'">借调去往岗位</th>
								<th data-options="field:'deadline',width:200,align:'center',editor:'text'">期限</th>
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
									  url:'${vix}/hr/employeesAction!goAddRedeployInfo.action?id=${entity.id}',
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
															$.post('${vix}/hr/employeesAction!saveOrUpdateRedeployInfo.action?id=${entity.id}', {
																'redeployInfo.id' : $("#daId").val(),
																'redeployInfo.employeeCode' : $("#employeeCode").val(),
																'redeployInfo.secondedbeforeUnits' : $("#secondedbeforeUnits").val(),
																'redeployInfo.secondedbeforeDep' : $("#secondedbeforeDep").val(),
																'redeployInfo.secondedbeforePost' : $("#secondedbeforePost").val(),
																'redeployInfo.secondedDestinedForUnits' : $("#secondedDestinedForUnits").val(),
																'redeployInfo.secondedDestinedSector' : $("#secondedDestinedSector").val(),
																'redeployInfo.secondedPosts' : $("#secondedPosts").val(),
																'redeployInfo.deadline' : $("#deadline").val(),
																'redeployInfo.remarks' : $("#remarks").val(),
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
									  url:'${vix}/hr/employeesAction!goAddRedeployInfo.action?id=${entity.id}&creid='+rows.id,
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
															$.post('${vix}/hr/employeesAction!saveOrUpdateRedeployInfo.action?id=${entity.id}', {
																'redeployInfo.id' : $("#daId").val(),
																'redeployInfo.employeeCode' : $("#employeeCode").val(),
																'redeployInfo.secondedbeforeUnits' : $("#secondedbeforeUnits").val(),
																'redeployInfo.secondedbeforeDep' : $("#secondedbeforeDep").val(),
																'redeployInfo.secondedbeforePost' : $("#secondedbeforePost").val(),
																'redeployInfo.secondedDestinedForUnits' : $("#secondedDestinedForUnits").val(),
																'redeployInfo.secondedDestinedSector' : $("#secondedDestinedSector").val(),
																'redeployInfo.secondedPosts' : $("#secondedPosts").val(),
																'redeployInfo.deadline' : $("#deadline").val(),
																'redeployInfo.remarks' : $("#remarks").val(),
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
									$.ajax({url:'${vix}/hr/employeesAction!deleteRedeployInfoById.action?creId='+row.id,cache: false});
								}
							}
					</script>
				</div>
			</div>
			<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<div style="padding: 8px;">
					<table id="dlDeliveryPlan" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlDeliveryPlanTb',url: '${vix}/hr/employeesAction!getAboardJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'purposeOfJourney',width:200,align:'center',editor:'text'">出国目的</th>
								<th data-options="field:'country',width:200,align:'center',editor:'text'">国家</th>
								<th data-options="field:'accessUnit',width:200,align:'center',editor:'text'">访问单位</th>
								<th data-options="field:'dateOfDeparture',width:200,align:'center',editor:'databox',formatter:formatDatebox">出国日期</th>
								<th data-options="field:'returnDate',width:200,align:'center',editor:'databox',formatter:formatDatebox">回国日期</th>
								<th data-options="field:'typeOfVisa',width:200,align:'center',editor:'text'">签证类型</th>
								<th data-options="field:'visaNumber',width:200,align:'center',editor:'text'">签证编号</th>
								<th data-options="field:'dispatchUnit',width:200,align:'center',editor:'text'">派遣单位</th>
								<th data-options="field:'goupsName',width:200,align:'center',editor:'text'">组团名称</th>
								<th data-options="field:'groupUnitName',width:200,align:'center',editor:'text'">组团单位名称</th>
								<th data-options="field:'groupsWithinTheIdentity',width:200,align:'center',editor:'text'">在组团内身份</th>
								<th data-options="field:'approvedBy',width:200,align:'center',editor:'text'">批准单位</th>
								<th data-options="field:'approvalDate',width:200,align:'center',editor:'databox',formatter:formatDatebox">批准日期</th>
								<th data-options="field:'approvalFileName',width:200,align:'center',editor:'text'">批准文件名</th>
								<th data-options="field:'approvalNumber',width:200,align:'center',editor:'text'">批准文号</th>
								<th data-options="field:'costSuppliers',width:200,align:'center',editor:'text'">费用供应商</th>
								<th data-options="field:'remarks',width:200,align:'center',editor:'text'">备注</th>
							</tr>
						</thead>
					</table>
					<div id="dlDeliveryPlanTb" style="height: auto">
						<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addwsItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
							data-options="iconCls:'icon-edit',plain:true" onclick="editwsItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removewsItem()"><span class="l-btn-left"><span
								class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
					</div>
					<script type="text/javascript">
							$('#dlDeliveryPlan').datagrid();
							function addwsItem(){
								$.ajax({
									  url:'${vix}/hr/employeesAction!goAddAboard.action?id=${entity.id}',
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
															$.post('${vix}/hr/employeesAction!saveOrUpdateAboard.action?id=${entity.id}', {
																'aboard.id' : $("#daId").val(),
																'aboard.employeeCode' : $("#employeeCode").val(),
																'aboard.purposeOfJourney' : $("#purposeOfJourney").val(),
																'aboard.country' : $("#country").val(),
																'aboard.accessUnit' : $("#accessUnit").val(),
																'aboard.dateOfDeparture' : $("#dateOfDeparture").val(),
																'aboard.returnDate' : $("#returnDate").val(),
																'aboard.typeOfVisa' : $("#typeOfVisa").val(),
																'aboard.visaNumber' : $("#visaNumber").val(),
																'aboard.dispatchUnit' : $("#dispatchUnit").val(),
																'aboard.goupsName' : $("#goupsName").val(),
																'aboard.groupUnitName' : $("#groupUnitName").val(),
																'aboard.groupsWithinTheIdentity' : $("#groupsWithinTheIdentity").val(),
																'aboard.approvedBy' : $("#approvedBy").val(),
																'aboard.approvalDate' : $("#approvalDate").val(),
																'aboard.approvalFileName' : $("#approvalFileName").val(),
																'aboard.approvalNumber' : $("#approvalNumber").val(),
																'aboard.costSuppliers' : $("#costSuppliers").val(),
																'aboard.remarks' : $("#remarks").val(),
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
							function editwsItem(){
								var rows = $('#dlDeliveryPlan').datagrid('getSelected');
								if(null == rows){
									alert("请选择行！");
									return;
									
								}
								$.ajax({
									  url:'${vix}/hr/employeesAction!goAddAboard.action?id=${entity.id}&abid='+rows.id,
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
															$.post('${vix}/hr/employeesAction!saveOrUpdateAboard.action?id=${entity.id}', {
																'aboard.id' : $("#daId").val(),
																'aboard.employeeCode' : $("#employeeCode").val(),
																'aboard.purposeOfJourney' : $("#purposeOfJourney").val(),
																'aboard.country' : $("#country").val(),
																'aboard.accessUnit' : $("#accessUnit").val(),
																'aboard.dateOfDeparture' : $("#dateOfDeparture").val(),
																'aboard.returnDate' : $("#returnDate").val(),
																'aboard.typeOfVisa' : $("#typeOfVisa").val(),
																'aboard.visaNumber' : $("#visaNumber").val(),
																'aboard.dispatchUnit' : $("#dispatchUnit").val(),
																'aboard.goupsName' : $("#goupsName").val(),
																'aboard.groupUnitName' : $("#groupUnitName").val(),
																'aboard.groupsWithinTheIdentity' : $("#groupsWithinTheIdentity").val(),
																'aboard.approvedBy' : $("#approvedBy").val(),
																'aboard.approvalDate' : $("#approvalDate").val(),
																'aboard.approvalFileName' : $("#approvalFileName").val(),
																'aboard.approvalNumber' : $("#approvalNumber").val(),
																'aboard.costSuppliers' : $("#costSuppliers").val(),
																'aboard.remarks' : $("#remarks").val(),
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
							function removewsItem(){
								var row = $('#dlDeliveryPlan').datagrid('getSelected');
								if(row){
									var index = $('#dlDeliveryPlan').datagrid('getRowIndex',row);
									$('#dlDeliveryPlan').datagrid('deleteRow', index);
									$.ajax({url:'${vix}/hr/employeesAction!deleteAboardById.action?abId='+row.id,cache: false});
								}
							}
					</script>
				</div>
			</div>
			<div id="a4" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<div style="padding: 8px;">
					<table id="dlInvoice" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlInvoiceTb',url: '${vix}/hr/employeesAction!getFamilyRelationshipJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'categoriesOfMembership',width:200,align:'center',editor:'text'">成员类别</th>
								<th data-options="field:'sex',width:200,align:'center',editor:'text'">性别</th>
								<th data-options="field:'dateOfBirth',width:200,align:'center',editor:'databox',formatter:formatDatebox">出生日期</th>
								<th data-options="field:'nation',width:200,align:'center',editor:'text'">民族</th>
								<th data-options="field:'idNumber',width:200,align:'center',editor:'text'">身份证号</th>
								<th data-options="field:'whetherHongKongMacaoAndTaiwanStaff',width:200,align:'center',editor:'text'">是否港澳台人员</th>
								<th data-options="field:'accountTheLocationOf',width:200,align:'center',editor:'text'">户口所在地</th>
								<th data-options="field:'politicalLandscape',width:200,align:'center',editor:'text'">政治面貌</th>
								<th data-options="field:'highestDegree',width:200,align:'center',editor:'text'">最高学历</th>
								<th data-options="field:'relationship',width:200,align:'center',editor:'text'">与本人关系</th>
								<th data-options="field:'address',width:200,align:'center',editor:'text'">地址</th>
								<th data-options="field:'tel',width:200,align:'center',editor:'text'">联系电话</th>
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
									  url:'${vix}/hr/employeesAction!goAddFamilyRelationship.action?id=${entity.id}',
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
															$.post('${vix}/hr/employeesAction!saveOrUpdateFamilyRelationship.action?id=${entity.id}', {
																'familyRelationship.id' : $("#daId").val(),
																'familyRelationship.employeeCode' : $("#employeeCode").val(),
																'familyRelationship.categoriesOfMembership' : $("#categoriesOfMembership").val(),
																'familyRelationship.sex' : $("#sex").val(),
																'familyRelationship.dateOfBirth' : $("#dateOfBirth").val(),
																'familyRelationship.nation' : $("#nation").val(),
																'familyRelationship.idNumber' : $("#idNumber").val(),
																'familyRelationship.whetherHongKongMacaoAndTaiwanStaff' : $("#whetherHongKongMacaoAndTaiwanStaff").val(),
																'familyRelationship.accountTheLocationOf' : $("#accountTheLocationOf").val(),
																'familyRelationship.politicalLandscape' : $("#politicalLandscape").val(),
																'familyRelationship.highestDegree' : $("#highestDegree").val(),
																'familyRelationship.relationship' : $("#relationship").val(),
																'familyRelationship.address' : $("#address").val(),
																'familyRelationship.tel' : $("#tel").val(),
																'familyRelationship.remarks' : $("#remarks").val(),
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
									  url:'${vix}/hr/employeesAction!goAddFamilyRelationship.action?id=${entity.id}&faid='+rows.id,
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
															$.post('${vix}/hr/employeesAction!saveOrUpdateFamilyRelationship.action?id=${entity.id}', {
																'familyRelationship.id' : $("#daId").val(),
																'familyRelationship.employeeCode' : $("#employeeCode").val(),
																'familyRelationship.categoriesOfMembership' : $("#categoriesOfMembership").val(),
																'familyRelationship.sex' : $("#sex").val(),
																'familyRelationship.dateOfBirth' : $("#dateOfBirth").val(),
																'familyRelationship.nation' : $("#nation").val(),
																'familyRelationship.idNumber' : $("#idNumber").val(),
																'familyRelationship.whetherHongKongMacaoAndTaiwanStaff' : $("#whetherHongKongMacaoAndTaiwanStaff").val(),
																'familyRelationship.accountTheLocationOf' : $("#accountTheLocationOf").val(),
																'familyRelationship.politicalLandscape' : $("#politicalLandscape").val(),
																'familyRelationship.highestDegree' : $("#highestDegree").val(),
																'familyRelationship.relationship' : $("#relationship").val(),
																'familyRelationship.address' : $("#address").val(),
																'familyRelationship.tel' : $("#tel").val(),
																'familyRelationship.remarks' : $("#remarks").val(),
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
									$.ajax({url:'${vix}/hr/employeesAction!deleteFamilyRelationshipById.action?faId='+row.id,cache: false});
								}
							}
					</script>
				</div>
			</div>
			<div id="a5" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<div style="padding: 8px;">
					<table id="dlPriceConditions" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlPriceConditionsTb',url: '${vix}/hr/employeesAction!getDisabilityDiseaseInforJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'disabilityDiseaseType',width:200,align:'center',editor:'text'">伤残病类型</th>
								<th data-options="field:'disabilityDiseaseName',width:200,align:'center',editor:'text'">伤残病名称</th>
								<th data-options="field:'disabilityDiseaseReasons',width:200,align:'center',editor:'text'">伤残病原因</th>
								<th data-options="field:'disabilityDiseaseExtent',width:200,align:'center',editor:'text'">伤残病程度</th>
								<th data-options="field:'dateOfInjury',width:200,align:'center',editor:'databox',formatter:formatDatebox">受伤日期</th>
								<th data-options="field:'dateOfConfirmation',width:200,align:'center',editor:'databox',formatter:formatDatebox">确诊日期</th>
								<th data-options="field:'rehabilitationDate',width:200,align:'center',editor:'databox',formatter:formatDatebox">康复日期</th>
								<th data-options="field:'diagnosisAgencies',width:200,align:'center',editor:'text'">诊断机构</th>
								<th data-options="field:'workInjuryCardNumber',width:200,align:'center',editor:'text'">工伤证号</th>
								<th data-options="field:'occupationalDiseaseName',width:200,align:'center',editor:'text'">职业病名称</th>
								<th data-options="field:'disabilityCardNumber',width:200,align:'center',editor:'text'">残疾证号</th>
								<th data-options="field:'ldentificationCategory',width:200,align:'center',editor:'text'">鉴定类别</th>
								<th data-options="field:'ldentificationDate',width:200,align:'center',editor:'databox',formatter:formatDatebox">鉴定日期</th>
								<th data-options="field:'disabilityDiseaseIdentificationLevel',width:200,align:'center',editor:'text'">伤残病鉴定等级</th>
								<th data-options="field:'careLevel',width:200,align:'center',editor:'text'">护理等级</th>
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
									  url:'${vix}/hr/employeesAction!goAddDisabilityDiseaseInfor.action?id=${entity.id}',
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
															$.post('${vix}/hr/employeesAction!saveOrUpdateDisabilityDiseaseInfor.action?id=${entity.id}', {
																'disabilityDiseaseInfor.id' : $("#daId").val(),
																'disabilityDiseaseInfor.employeeCode' : $("#employeeCode").val(),
																'disabilityDiseaseInfor.disabilityDiseaseType' : $("#disabilityDiseaseType").val(),
																'disabilityDiseaseInfor.disabilityDiseaseName' : $("#disabilityDiseaseName").val(),
																'disabilityDiseaseInfor.disabilityDiseaseReasons' : $("#disabilityDiseaseReasons").val(),
																'disabilityDiseaseInfor.disabilityDiseaseExtent' : $("#disabilityDiseaseExtent").val(),
																'disabilityDiseaseInfor.dateOfInjury' : $("#dateOfInjury").val(),
																'disabilityDiseaseInfor.dateOfConfirmation' : $("#dateOfConfirmation").val(),
																'disabilityDiseaseInfor.rehabilitationDate' : $("#rehabilitationDate").val(),
																'disabilityDiseaseInfor.diagnosisAgencies' : $("#diagnosisAgencies").val(),
																'disabilityDiseaseInfor.workInjuryCardNumber' : $("#workInjuryCardNumber").val(),
																'disabilityDiseaseInfor.occupationalDiseaseName' : $("#occupationalDiseaseName").val(),
																'disabilityDiseaseInfor.disabilityCardNumber' : $("#disabilityCardNumber").val(),
																'disabilityDiseaseInfor.ldentificationCategory' : $("#ldentificationCategory").val(),
																'disabilityDiseaseInfor.ldentificationDate' : $("#ldentificationDate").val(),
																'disabilityDiseaseInfor.disabilityDiseaseIdentificationLevel' : $("#disabilityDiseaseIdentificationLevel").val(),
																'disabilityDiseaseInfor.careLevel' : $("#careLevel").val(),
																'disabilityDiseaseInfor.remarks' : $("#remarks").val(),
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
									  url:'${vix}/hr/employeesAction!goAddDisabilityDiseaseInfor.action?id=${entity.id}&disaid='+rows.id,
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
															$.post('${vix}/hr/employeesAction!saveOrUpdateDisabilityDiseaseInfor.action?id=${entity.id}', {
																'disabilityDiseaseInfor.id' : $("#daId").val(),
																'disabilityDiseaseInfor.employeeCode' : $("#employeeCode").val(),
																'disabilityDiseaseInfor.disabilityDiseaseType' : $("#disabilityDiseaseType").val(),
																'disabilityDiseaseInfor.disabilityDiseaseName' : $("#disabilityDiseaseName").val(),
																'disabilityDiseaseInfor.disabilityDiseaseReasons' : $("#disabilityDiseaseReasons").val(),
																'disabilityDiseaseInfor.disabilityDiseaseExtent' : $("#disabilityDiseaseExtent").val(),
																'disabilityDiseaseInfor.dateOfInjury' : $("#dateOfInjury").val(),
																'disabilityDiseaseInfor.dateOfConfirmation' : $("#dateOfConfirmation").val(),
																'disabilityDiseaseInfor.rehabilitationDate' : $("#rehabilitationDate").val(),
																'disabilityDiseaseInfor.diagnosisAgencies' : $("#diagnosisAgencies").val(),
																'disabilityDiseaseInfor.workInjuryCardNumber' : $("#workInjuryCardNumber").val(),
																'disabilityDiseaseInfor.occupationalDiseaseName' : $("#occupationalDiseaseName").val(),
																'disabilityDiseaseInfor.disabilityCardNumber' : $("#disabilityCardNumber").val(),
																'disabilityDiseaseInfor.ldentificationCategory' : $("#ldentificationCategory").val(),
																'disabilityDiseaseInfor.ldentificationDate' : $("#ldentificationDate").val(),
																'disabilityDiseaseInfor.disabilityDiseaseIdentificationLevel' : $("#disabilityDiseaseIdentificationLevel").val(),
																'disabilityDiseaseInfor.careLevel' : $("#careLevel").val(),
																'disabilityDiseaseInfor.remarks' : $("#remarks").val(),
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
									$.ajax({url:'${vix}/hr/employeesAction!deleteTrainingById.action?disaId='+row.id,cache: false});
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