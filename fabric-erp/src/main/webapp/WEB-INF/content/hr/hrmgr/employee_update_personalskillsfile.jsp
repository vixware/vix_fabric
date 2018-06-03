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
					<li class="current"><a onclick="javascript:tab(8,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />语言能力</a></li>
					<li><a onclick="javascript:$('#a2').attr('style','');tab(8,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />计算机水平</a></li>
					<li><a onclick="javascript:$('#a3').attr('style','');tab(8,3,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />技术工人职业资格</a></li>
					<li><a onclick="javascript:$('#a4').attr('style','');tab(8,4,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />学历学位情况</a></li>
					<li><a onclick="javascript:$('#a5').attr('style','');tab(8,5,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />培训情况</a></li>
					<li><a onclick="javascript:$('#a6').attr('style','');tab(8,6,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />专业技术职务</a></li>
					<li><a onclick="javascript:$('#a7').attr('style','');tab(8,7,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />专业技术成果</a></li>
				</ul>
			</div>
			<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
				<div style="padding: 8px;">
					<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/hr/employeeOrgFileAction!getLanguageAbilityJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'id',align:'center',width:120,editor:'text',hidden:'true'">id</th>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'cassificationOfLanguage',width:200,align:'center',editor:'text'">语种</th>
								<th data-options="field:'skilledInChengdu',width:200,align:'center',editor:'text'">熟练程度</th>
								<th data-options="field:'masterLanguageLevel',width:200,align:'center',editor:'text'">掌握语种水平级别</th>
								<th data-options="field:'typeOfCertificate',width:200,align:'center',editor:'text'">证书类型</th>
								<th data-options="field:'otherTypeOfCertificate',width:200,align:'center',editor:'text'">其他证书类型</th>
								<th data-options="field:'certificateNumber',width:200,align:'center',editor:'text'">证书编号</th>
								<th data-options="field:'score',width:200,align:'center',editor:'text'">成绩</th>
								<th data-options="field:'certificateInTime',width:200,align:'center',editor:'databox',formatter:formatDatebox">证书取得时间</th>
								<th data-options="field:'certificationBody',width:200,align:'center',editor:'text'">认证机构</th>
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
									  url:'${vix}/hr/employeeOrgFileAction!goAddLanguageAbility.action?id=${entity.id}',
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
															$.post('${vix}/hr/employeeOrgFileAction!saveOrUpdateLanguageAbility.action?id=${entity.id}', {
																'languageAbility.id' : $("#daId").val(),
																'languageAbility.employeeCode' : $("#employeeCode").val(),
																'languageAbility.cassificationOfLanguage' : $("#cassificationOfLanguage").val(),
																'languageAbility.skilledInChengdu' : $("#skilledInChengdu").val(),
																'languageAbility.masterLanguageLevel' : $("#masterLanguageLevel").val(),
																'languageAbility.typeOfCertificate' : $("#typeOfCertificate").val(),
																'languageAbility.otherTypeOfCertificate' : $("#otherTypeOfCertificate").val(),
																'languageAbility.certificateNumber' : $("#certificateNumber").val(),
																'languageAbility.score' : $("#score").val(),
																'languageAbility.certificateInTime' : $("#certificateInTime").val(),
																'languageAbility.certificationBody' : $("#certificationBody").val(),
																'languageAbility.remarks' : $("#remarks").val(),
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
									  url:'${vix}/hr/employeeOrgFileAction!goAddLanguageAbility.action?id=${entity.id}&lid='+rows.id,
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
															$.post('${vix}/hr/employeeOrgFileAction!saveOrUpdateLanguageAbility.action?id=${entity.id}', {
																'languageAbility.id' : $("#daId").val(),
																'languageAbility.employeeCode' : $("#employeeCode").val(),
																'languageAbility.cassificationOfLanguage' : $("#cassificationOfLanguage").val(),
																'languageAbility.skilledInChengdu' : $("#skilledInChengdu").val(),
																'languageAbility.masterLanguageLevel' : $("#masterLanguageLevel").val(),
																'languageAbility.typeOfCertificate' : $("#typeOfCertificate").val(),
																'languageAbility.otherTypeOfCertificate' : $("#otherTypeOfCertificate").val(),
																'languageAbility.certificateNumber' : $("#certificateNumber").val(),
																'languageAbility.score' : $("#score").val(),
																'languageAbility.certificateInTime' : $("#certificateInTime").val(),
																'languageAbility.certificationBody' : $("#certificationBody").val(),
																'languageAbility.remarks' : $("#remarks").val(),
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
									$.ajax({url:'${vix}/hr/employeeOrgFileAction!deleteLanguageAbilityById.action?lId='+row.id,cache: false});
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
					<table id="dlReceivedAddress" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlReceivedAddressTb',url: '${vix}/hr/employeeOrgFileAction!getComputerLevelJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'computerProficiencyLevel',width:200,align:'center',editor:'text'">计算机水平级别</th>
								<th data-options="field:'obtainCertificateName',width:200,align:'center',editor:'text'">证书名称</th>
								<th data-options="field:'otherCertificateName',width:200,align:'center',editor:'text'">其他证书名称</th>
								<th data-options="field:'certificateToObtainTime',width:200,align:'center',editor:'databox',formatter:formatDatebox">证书取得时间</th>
								<th data-options="field:'certificationBody',width:200,align:'center',editor:'text'">认证机构</th>
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
									  url:'${vix}/hr/employeeOrgFileAction!goAddComputerLevel.action?id=${entity.id}',
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
															$.post('${vix}/hr/employeeOrgFileAction!saveOrUpdateComputerLevel.action?id=${entity.id}', {
																'computerLevel.id' : $("#daId").val(),
																'computerLevel.employeeCode' : $("#employeeCode").val(),
																'computerLevel.computerProficiencyLevel' : $("#computerProficiencyLevel").val(),
																'computerLevel.obtainCertificateName' : $("#obtainCertificateName").val(),
																'computerLevel.otherCertificateName' : $("#otherCertificateName").val(),
																'computerLevel.certificateToObtainTime' : $("#certificateToObtainTime").val(),
																'computerLevel.certificationBody' : $("#certificationBody").val(),
																'computerLevel.remarks' : $("#remarks").val(),
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
									  url:'${vix}/hr/employeeOrgFileAction!goAddComputerLevel.action?id=${entity.id}&cid='+rows.id,
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
															$.post('${vix}/hr/employeeOrgFileAction!saveOrUpdateComputerLevel.action?id=${entity.id}', {
																'computerLevel.id' : $("#daId").val(),
																'computerLevel.employeeCode' : $("#employeeCode").val(),
																'computerLevel.computerProficiencyLevel' : $("#computerProficiencyLevel").val(),
																'computerLevel.obtainCertificateName' : $("#obtainCertificateName").val(),
																'computerLevel.otherCertificateName' : $("#otherCertificateName").val(),
																'computerLevel.certificateToObtainTime' : $("#certificateToObtainTime").val(),
																'computerLevel.certificationBody' : $("#certificationBody").val(),
																'computerLevel.remarks' : $("#remarks").val(),
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
									$.ajax({url:'${vix}/hr/employeeOrgFileAction!deleteComputerLevelById.action?cId='+row.id,cache: false});
								}
							}
					</script>
				</div>
			</div>
			<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<div style="padding: 8px;">
					<table id="dlDeliveryPlan" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlDeliveryPlanTb',url: '${vix}/hr/employeeOrgFileAction!getWorkQualifyJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'professionalAndTechnicalQualifications',width:200,align:'center',editor:'text'">专业技术资格系列</th>
								<th data-options="field:'professionalAndTechnicalQualificationName',width:200,align:'center',editor:'text'">专业技术资格名称</th>
								<th data-options="field:'level',width:200,align:'center',editor:'text'">级别</th>
								<th data-options="field:'assessmentOrganization',width:200,align:'center',editor:'text'">评定机构</th>
								<th data-options="field:'accreditationInstitution',width:200,align:'center',editor:'text'">认定机构</th>
								<th data-options="field:'whetherTheNationalProfessionalQualificationExamination',width:200,align:'center',editor:'text'">是否国家专业技术资格考试</th>
								<th data-options="field:'whetherThroughTheIdentificationOf',width:200,align:'center',editor:'text'">是否通过认定</th>
								<th data-options="field:'ifTheHighestQualification',width:200,align:'center',editor:'text'">是否最高资格</th>
								<th data-options="field:'certificateNo',width:200,align:'center',editor:'text'">资格证号</th>
								<th data-options="field:'fileNumber',width:200,align:'center',editor:'text'">文件号</th>
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
									  url:'${vix}/hr/employeeOrgFileAction!goAddWorkQualify.action?id=${entity.id}',
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
															$.post('${vix}/hr/employeeOrgFileAction!saveOrUpdateWorkQualify.action?id=${entity.id}', {
																'workQualify.id' : $("#daId").val(),
																'workQualify.employeeCode' : $("#employeeCode").val(),
																'workQualify.professionalAndTechnicalQualifications' : $("#professionalAndTechnicalQualifications").val(),
																'workQualify.professionalAndTechnicalQualificationName' : $("#professionalAndTechnicalQualificationName").val(),
																'workQualify.level' : $("#level").val(),
																'workQualify.assessmentOrganization' : $("#assessmentOrganization").val(),
																'workQualify.accreditationInstitution' : $("#accreditationInstitution").val(),
																'workQualify.whetherTheNationalProfessionalQualificationExamination' : $("#whetherTheNationalProfessionalQualificationExamination").val(),
																'workQualify.whetherThroughTheIdentificationOf' : $("#whetherThroughTheIdentificationOf").val(),
																'workQualify.ifTheHighestQualification' : $("#ifTheHighestQualification").val(),
																'workQualify.certificateNo' : $("#certificateNo").val(),
																'workQualify.fileNumber' : $("#fileNumber").val(),
																'workQualify.remarks' : $("#remarks").val(),
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
									  url:'${vix}/hr/employeeOrgFileAction!goAddWorkQualify.action?id=${entity.id}&wid='+rows.id,
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
															$.post('${vix}/hr/employeeOrgFileAction!saveOrUpdateWorkQualify.action?id=${entity.id}', {
																'workQualify.id' : $("#daId").val(),
																'workQualify.employeeCode' : $("#employeeCode").val(),
																'workQualify.professionalAndTechnicalQualifications' : $("#professionalAndTechnicalQualifications").val(),
																'workQualify.professionalAndTechnicalQualificationName' : $("#professionalAndTechnicalQualificationName").val(),
																'workQualify.level' : $("#level").val(),
																'workQualify.assessmentOrganization' : $("#assessmentOrganization").val(),
																'workQualify.accreditationInstitution' : $("#accreditationInstitution").val(),
																'workQualify.whetherTheNationalProfessionalQualificationExamination' : $("#whetherTheNationalProfessionalQualificationExamination").val(),
																'workQualify.whetherThroughTheIdentificationOf' : $("#whetherThroughTheIdentificationOf").val(),
																'workQualify.ifTheHighestQualification' : $("#ifTheHighestQualification").val(),
																'workQualify.certificateNo' : $("#certificateNo").val(),
																'workQualify.fileNumber' : $("#fileNumber").val(),
																'workQualify.remarks' : $("#remarks").val(),
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
									$.ajax({url:'${vix}/hr/employeeOrgFileAction!deleteWorkQualifyById.action?wId='+row.id,cache: false});
								}
							}
					</script>
				</div>
			</div>
			<div id="a4" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<div style="padding: 8px;">
					<table id="dlInvoice" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlInvoiceTb',url: '${vix}/hr/employeeOrgFileAction!getDegreeJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'educationalBackground',width:200,align:'center',editor:'text'">学历</th>
								<th data-options="field:'academicCertificates',width:200,align:'center',editor:'text'">学历证书</th>
								<th data-options="field:'admissionTime',width:200,align:'center',editor:'databox',formatter:formatDatebox">入学时间</th>
								<th data-options="field:'educationCertificateNumber',width:200,align:'center',editor:'text'">学历证书编号</th>
								<th data-options="field:'professionalCategory',width:200,align:'center',editor:'text'">专业门类</th>
								<th data-options="field:'professionalDisciplinary',width:200,align:'center',editor:'text'">专业学科</th>
								<th data-options="field:'formsOfLearning',width:200,align:'center',editor:'text'">学习形式</th>
								<th data-options="field:'schoolSystem',width:200,align:'center',editor:'text'">学制</th>
								<th data-options="field:'schoolName',width:200,align:'center',editor:'text'">学校名称</th>
								<th data-options="field:'whether',width:200,align:'center',editor:'text'">是否211或985</th>
								<th data-options="field:'whetherTheHighestDegree',width:200,align:'center',editor:'text'">是否最高学历</th>
								<th data-options="field:'whetherTheOriginalQualifications',width:200,align:'center',editor:'text'">*是否原始学历</th>
								<th data-options="field:'degree',width:200,align:'center',editor:'text'">学位</th>
								<th data-options="field:'diplomaNumber',width:200,align:'center',editor:'text'">学位证书编号</th>
								<th data-options="field:'degreeGrantingCountries',width:200,align:'center',editor:'text'">学位授予国家</th>
								<th data-options="field:'degreeAwarding',width:200,align:'center',editor:'text'">学位授予单位</th>
								<th data-options="field:'degreeGrantedWhetherTheHighestDegree',width:200,align:'center',editor:'text'">是否最高学位</th>
								<th data-options="field:'whetherMinorInTheSecondDegree',width:200,align:'center',editor:'text'">是否辅修二学位</th>
								<th data-options="field:'minorSecondDegreeName',width:200,align:'center',editor:'text'">辅修二学位名称</th>
								<th data-options="field:'remark',width:200,align:'center',editor:'text'">备注</th>
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
									  url:'${vix}/hr/employeeOrgFileAction!goAddDegree.action?id=${entity.id}',
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
															$.post('${vix}/hr/employeeOrgFileAction!saveOrUpdateDegree.action?id=${entity.id}', {
																'degree.id' : $("#daId").val(),
																'degree.employeeCode' : $("#employeeCode").val(),
																'degree.educationalBackground' : $("#educationalBackground").val(),
																'degree.academicCertificates' : $("#academicCertificates").val(),
																'degree.admissionTime' : $("#admissionTime").val(),
																'degree.educationCertificateNumber' : $("#educationCertificateNumber").val(),
																'degree.professionalCategory' : $("#professionalCategory").val(),
																'degree.professionalDisciplinary' : $("#professionalDisciplinary").val(),
																'degree.formsOfLearning' : $("#formsOfLearning").val(),
																'degree.schoolSystem' : $("#schoolSystem").val(),
																'degree.schoolName' : $("#schoolName").val(),
																'degree.whether' : $("#whether").val(),
																'degree.whetherTheHighestDegree' : $("#whetherTheHighestDegree").val(),
																'degree.whetherTheOriginalQualifications' : $("#whetherTheOriginalQualifications").val(),
																'degree.degree' : $("#degree").val(),
																'degree.diplomaNumber' : $("#diplomaNumber").val(),
																'degree.degreeGrantingCountries' : $("#degreeGrantingCountries").val(),
																'degree.degreeAwarding' : $("#degreeAwarding").val(),
																'degree.degreeGrantedWhetherTheHighestDegree' : $("#degreeGrantedWhetherTheHighestDegree").val(),
																'degree.whetherMinorInTheSecondDegree' : $("#whetherMinorInTheSecondDegree").val(),
																'degree.minorSecondDegreeName' : $("#minorSecondDegreeName").val(),
																'degree.remark' : $("#remark").val(),
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
									  url:'${vix}/hr/employeeOrgFileAction!goAddDegree.action?id=${entity.id}&did='+rows.id,
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
															$.post('${vix}/hr/employeeOrgFileAction!saveOrUpdateDegree.action?id=${entity.id}', {
																'degree.id' : $("#daId").val(),
																'degree.employeeCode' : $("#employeeCode").val(),
																'degree.educationalBackground' : $("#educationalBackground").val(),
																'degree.academicCertificates' : $("#academicCertificates").val(),
																'degree.admissionTime' : $("#admissionTime").val(),
																'degree.educationCertificateNumber' : $("#educationCertificateNumber").val(),
																'degree.professionalCategory' : $("#professionalCategory").val(),
																'degree.professionalDisciplinary' : $("#professionalDisciplinary").val(),
																'degree.formsOfLearning' : $("#formsOfLearning").val(),
																'degree.schoolSystem' : $("#schoolSystem").val(),
																'degree.schoolName' : $("#schoolName").val(),
																'degree.whether' : $("#whether").val(),
																'degree.whetherTheHighestDegree' : $("#whetherTheHighestDegree").val(),
																'degree.whetherTheOriginalQualifications' : $("#whetherTheOriginalQualifications").val(),
																'degree.degree' : $("#degree").val(),
																'degree.diplomaNumber' : $("#diplomaNumber").val(),
																'degree.degreeGrantingCountries' : $("#degreeGrantingCountries").val(),
																'degree.degreeAwarding' : $("#degreeAwarding").val(),
																'degree.degreeGrantedWhetherTheHighestDegree' : $("#degreeGrantedWhetherTheHighestDegree").val(),
																'degree.whetherMinorInTheSecondDegree' : $("#whetherMinorInTheSecondDegree").val(),
																'degree.minorSecondDegreeName' : $("#minorSecondDegreeName").val(),
																'degree.remark' : $("#remark").val(),
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
									$.ajax({url:'${vix}/hr/employeeOrgFileAction!deleteDegreeById.action?dId='+row.id,cache: false});
								}
							}
					</script>
				</div>
			</div>
			<div id="a5" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<div style="padding: 8px;">
					<table id="dlPriceConditions" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlPriceConditionsTb',url: '${vix}/hr/employeeOrgFileAction!getTrainingJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'trainingClass',width:200,align:'center',editor:'text'">培训类别</th>
								<th data-options="field:'hostLevel',width:200,align:'center',editor:'text'">主办层次</th>
								<th data-options="field:'projectTraining',width:200,align:'center',editor:'text'">培训项目名称</th>
								<th data-options="field:'trainingObjects',width:200,align:'center',editor:'text'">培训对象</th>
								<th data-options="field:'trainingContent',width:200,align:'center',editor:'text'">培训内容</th>
								<th data-options="field:'trainingForm',width:200,align:'center',editor:'text'">培训形式</th>
								<th data-options="field:'trainingMaterials',width:200,align:'center',editor:'text'">培训教材</th>
								<th data-options="field:'trainingInstructor',width:200,align:'center',editor:'text'">培训讲师</th>
								<th data-options="field:'planTime',width:200,align:'center',editor:'databox',formatter:formatDatebox">计划时间</th>
								<th data-options="field:'cassHour',width:200,align:'center',editor:'text'">学时</th>
								<th data-options="field:'peopleNumber',width:200,align:'center',editor:'text'">人数</th>
								<th data-options="field:'periods',width:200,align:'center',editor:'text'">期数</th>
								<th data-options="field:'trainingAgency',width:200,align:'center',editor:'text'">培训机构</th>
								<th data-options="field:'contacts',width:200,align:'center',editor:'text'">联系人</th>
								<th data-options="field:'contactNumber',width:200,align:'center',editor:'text'">联系电话</th>
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
									  url:'${vix}/hr/employeeOrgFileAction!goAddTraining.action?id=${entity.id}',
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
															$.post('${vix}/hr/employeeOrgFileAction!saveOrUpdateTraining.action?id=${entity.id}', {
																'training.id' : $("#daId").val(),
																'training.employeeCode' : $("#employeeCode").val(),
																'training.trainingClass' : $("#trainingClass").val(),
																'training.hostLevel' : $("#hostLevel").val(),
																'training.projectTraining' : $("#projectTraining").val(),
																'training.trainingObjects' : $("#trainingObjects").val(),
																'training.trainingContent' : $("#trainingContent").val(),
																'training.trainingForm' : $("#trainingForm").val(),
																'training.trainingMaterials' : $("#trainingMaterials").val(),
																'training.trainingInstructor' : $("#trainingInstructor").val(),
																'training.planTime' : $("#planTime").val(),
																'training.cassHour' : $("#cassHour").val(),
																'training.peopleNumber' : $("#peopleNumber").val(),
																'training.periods' : $("#periods").val(),
																'training.trainingAgency' : $("#trainingAgency").val(),
																'training.contacts' : $("#contacts").val(),
																'training.contactNumber' : $("#contactNumber").val(),
																'training.remarks' : $("#remarks").val(),
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
									  url:'${vix}/hr/employeeOrgFileAction!goAddTraining.action?id=${entity.id}&tid='+rows.id,
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
															$.post('${vix}/hr/employeeOrgFileAction!saveOrUpdateTraining.action?id=${entity.id}', {
																'training.id' : $("#daId").val(),
																'training.employeeCode' : $("#employeeCode").val(),
																'training.trainingClass' : $("#trainingClass").val(),
																'training.hostLevel' : $("#hostLevel").val(),
																'training.projectTraining' : $("#projectTraining").val(),
																'training.trainingObjects' : $("#trainingObjects").val(),
																'training.trainingContent' : $("#trainingContent").val(),
																'training.trainingForm' : $("#trainingForm").val(),
																'training.trainingMaterials' : $("#trainingMaterials").val(),
																'training.trainingInstructor' : $("#trainingInstructor").val(),
																'training.planTime' : $("#planTime").val(),
																'training.cassHour' : $("#cassHour").val(),
																'training.peopleNumber' : $("#peopleNumber").val(),
																'training.periods' : $("#periods").val(),
																'training.trainingAgency' : $("#trainingAgency").val(),
																'training.contacts' : $("#contacts").val(),
																'training.contactNumber' : $("#contactNumber").val(),
																'training.remarks' : $("#remarks").val(),
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
									$.ajax({url:'${vix}/hr/employeeOrgFileAction!deleteTrainingById.action?tId='+row.id,cache: false});
								}
							}
					</script>
				</div>
			</div>
			<div id="a6" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<div style="padding: 8px;">
					<table id="dlTechtitle" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlTechtitleTb',url: '${vix}/hr/employeeOrgFileAction!getTechtitleJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'professionalAndTechnicalQualifications',width:200,align:'center',editor:'text'">专业技术职务系列</th>
								<th data-options="field:'professionalAndTechnicalQualificationName',width:200,align:'center',editor:'text'">专业技术职务名称</th>
								<th data-options="field:'professionallevel',width:200,align:'center',editor:'text'">专业级别</th>
								<th data-options="field:'assessmentOrganization',width:200,align:'center',editor:'text'">评定机构</th>
								<th data-options="field:'accreditationInstitution',width:200,align:'center',editor:'text'">认定机构</th>
								<th data-options="field:'whetherTheNationalProfessionalQualificationExamination',width:200,align:'center',editor:'text'">是否国家专业技术资格考试</th>
								<th data-options="field:'whetherThroughTheIdentificationOf',width:200,align:'center',editor:'text'">是否通过认定</th>
								<th data-options="field:'ifTheHighestQualification',width:200,align:'center',editor:'text'">是否最高资格</th>
								<th data-options="field:'certificateNo',width:200,align:'center',editor:'text'">资格证号</th>
								<th data-options="field:'fileNumber',width:200,align:'center',editor:'text'">文件号</th>
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
									  url:'${vix}/hr/employeeOrgFileAction!goAddTechtitle.action?id=${entity.id}',
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
															$.post('${vix}/hr/employeeOrgFileAction!saveOrUpdateTechtitle.action?id=${entity.id}', {
																'techtitle.id' : $("#daId").val(),
																'techtitle.employeeCode' : $("#employeeCode").val(),
																'techtitle.professionalAndTechnicalQualifications' : $("#professionalAndTechnicalQualifications").val(),
																'techtitle.professionalAndTechnicalQualificationName' : $("#professionalAndTechnicalQualificationName").val(),
																'techtitle.professionallevel' : $("#professionallevel").val(),
																'techtitle.assessmentOrganization' : $("#assessmentOrganization").val(),
																'techtitle.accreditationInstitution' : $("#accreditationInstitution").val(),
																'techtitle.whetherTheNationalProfessionalQualificationExamination' : $("#whetherTheNationalProfessionalQualificationExamination").val(),
																'techtitle.whetherThroughTheIdentificationOf' : $("#whetherThroughTheIdentificationOf").val(),
																'techtitle.ifTheHighestQualification' : $("#ifTheHighestQualification").val(),
																'techtitle.certificateNo' : $("#certificateNo").val(),
																'techtitle.fileNumber' : $("#fileNumber").val(),
																'techtitle.remarks' : $("#remarks").val(),
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
									  url:'${vix}/hr/employeeOrgFileAction!goAddTechtitle.action?id=${entity.id}&teid='+rows.id,
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
															$.post('${vix}/hr/employeeOrgFileAction!saveOrUpdateTechtitle.action?id=${entity.id}', {
																'techtitle.id' : $("#daId").val(),
																'techtitle.employeeCode' : $("#employeeCode").val(),
																'techtitle.professionalAndTechnicalQualifications' : $("#professionalAndTechnicalQualifications").val(),
																'techtitle.professionalAndTechnicalQualificationName' : $("#professionalAndTechnicalQualificationName").val(),
																'techtitle.professionallevel' : $("#professionallevel").val(),
																'techtitle.assessmentOrganization' : $("#assessmentOrganization").val(),
																'techtitle.accreditationInstitution' : $("#accreditationInstitution").val(),
																'techtitle.whetherTheNationalProfessionalQualificationExamination' : $("#whetherTheNationalProfessionalQualificationExamination").val(),
																'techtitle.whetherThroughTheIdentificationOf' : $("#whetherThroughTheIdentificationOf").val(),
																'techtitle.ifTheHighestQualification' : $("#ifTheHighestQualification").val(),
																'techtitle.certificateNo' : $("#certificateNo").val(),
																'techtitle.fileNumber' : $("#fileNumber").val(),
																'techtitle.remarks' : $("#remarks").val(),
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
									$.ajax({url:'${vix}/hr/employeeOrgFileAction!deleteTechtitleById.action?teId='+row.id,cache: false});
								}
							}
					</script>
				</div>
			</div>
			<div id="a7" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<div style="padding: 8px;">
					<table id="dlTechachieve" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlTechachieveTb',url: '${vix}/hr/employeeOrgFileAction!getTechachieveJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">员工编码</th>
								<th data-options="field:'expertName',width:200,align:'center',editor:'text'">专家名称</th>
								<th data-options="field:'professionalCategory',width:200,align:'center',editor:'text'">专业类别</th>
								<th data-options="field:'expertLevel',width:200,align:'center',editor:'text'">专家级别</th>
								<th data-options="field:'professionalName',width:200,align:'center',editor:'text'">专业名称</th>
								<th data-options="field:'professionalAndTechnicalAchievements',width:200,align:'center',editor:'text'">专业技术成果</th>
								<th data-options="field:'resultsOfTheAnnualAppraisal',width:200,align:'center',editor:'text'">年度考评结果</th>
								<th data-options="field:'satus',width:200,align:'center',editor:'text'">状态</th>
								<th data-options="field:'remarks',width:200,align:'center',editor:'text'">备注</th>
							</tr>
						</thead>
					</table>
					<div id="dlTechachieveTb" style="height: auto">
						<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addTechachieveItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
							data-options="iconCls:'icon-edit',plain:true" onclick="editTechachieveItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeTechachieveItem()"><span
							class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
					</div>
					<script type="text/javascript">
							$('#dlTechachieve').datagrid();
							function addTechachieveItem(){
								$.ajax({
									  url:'${vix}/hr/employeeOrgFileAction!goAddTechachieve.action?id=${entity.id}',
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
														if($('#dtechachieveForm').validationEngine('validate')){
															$.post('${vix}/hr/employeeOrgFileAction!saveOrUpdateTechachieve.action?id=${entity.id}', {
																'techachieve.id' : $("#daId").val(),
																'techachieve.employeeCode' : $("#employeeCode").val(),
																'techachieve.expertName' : $("#expertName").val(),
																'techachieve.professionalCategory' : $("#professionalCategory").val(),
																'techachieve.expertLevel' : $("#expertLevel").val(),
																'techachieve.professionalName' : $("#professionalName").val(),
																'techachieve.professionalAndTechnicalAchievements' : $("#professionalAndTechnicalAchievements").val(),
																'techachieve.resultsOfTheAnnualAppraisal' : $("#resultsOfTheAnnualAppraisal").val(),
																'techachieve.satus' : $("#satus").val(),
																'techachieve.remarks' : $("#remarks").val(),
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlTechachieve').datagrid("reload");
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
							function editTechachieveItem(){
								var rows = $('#dlTechachieve').datagrid('getSelected');
								if(null == rows){
									alert("请选择行！");
									return;
									
								}
								$.ajax({
									  url:'${vix}/hr/employeeOrgFileAction!goAddTechachieve.action?id=${entity.id}&ctcid='+rows.id,
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
														if($('#dtechachieveForm').validationEngine('validate')){
															$.post('${vix}/hr/employeeOrgFileAction!saveOrUpdateTechachieve.action?id=${entity.id}', {
																'techachieve.id' : $("#daId").val(),
																'techachieve.employeeCode' : $("#employeeCode").val(),
																'techachieve.expertName' : $("#expertName").val(),
																'techachieve.professionalCategory' : $("#professionalCategory").val(),
																'techachieve.expertLevel' : $("#expertLevel").val(),
																'techachieve.professionalName' : $("#professionalName").val(),
																'techachieve.professionalAndTechnicalAchievements' : $("#professionalAndTechnicalAchievements").val(),
																'techachieve.resultsOfTheAnnualAppraisal' : $("#resultsOfTheAnnualAppraisal").val(),
																'techachieve.satus' : $("#satus").val(),
																'techachieve.remarks' : $("#remarks").val(),
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlTechachieve').datagrid("reload");
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
							function removeTechachieveItem(){
								var row = $('#dlTechachieve').datagrid('getSelected');
								if(row){
									var index = $('#dlTechachieve').datagrid('getRowIndex',row);
									$('#dlTechachieve').datagrid('deleteRow', index);
									$.ajax({url:'${vix}/hr/employeeOrgFileAction!deleteTechachieveById.action?ctcId='+row.id,cache: false});
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