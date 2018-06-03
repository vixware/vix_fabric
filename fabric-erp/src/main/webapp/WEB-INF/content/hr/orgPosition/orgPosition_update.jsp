<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>
<script type="text/javascript">
	$(".addtable .addtable_t").click(function(){
		$(this).toggleClass("addtable_td");
		$(this).next(".addtable_c").toggle();
	});
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
//提示
if($('input.sweet-tooltip').length){
	$('input.sweet-tooltip').focus(function() {
		tooltip				= $(this);
		tooltipText 		= tooltip.attr('data-text-tooltip');
		tooltipClassName	= 'tooltip';
		tooltipClass		= '.' + tooltipClassName;
		
		if(tooltip.hasClass('showed-tooltip')) return false;
		
		tooltip.addClass('showed-tooltip')
				 .after('<div class="'+tooltipClassName+'"><div class="tooltip_l"></div><div class="tooltip_r"></div><div class="tooltip_x">'+tooltipText+'</div><div class="tooltip_b"></div></div>');

		
		tooltipPosTop 	= tooltip.position().top - $(tooltipClass).outerHeight() - 10;
		tooltipPosLeft = tooltip.position().left;
		tooltipPosLeft = tooltipPosLeft - (($(tooltipClass).outerWidth()/2) - tooltip.outerWidth()/2);
		
		$(tooltipClass).css({ 'left': tooltipPosLeft, 'top': tooltipPosTop })
							.animate({'opacity':'1', 'marginTop':'0'}, 500);
		
	}).focusout(function() {
		
		$(tooltipClass).animate({'opacity':'0', 'marginTop':'-10px'}, 500, function() {
				
			$(this).remove();
			tooltip.removeClass('showed-tooltip');
				
		});
	});
	JT_init();
}
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
 
$("#order").validationEngine();
if($(".ms").length>0){
	$(".ms").hover(function(){
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">ul",this).stop().slideUp(100);
	});
	$(".ms ul li").hover(function(){
		$(">a",this).addClass("hover");
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">a",this).removeClass("hover");
		$(">ul",this).stop().slideUp(100);
	});
}
$(".newvoucher dt b").click(function(){
	$(this).toggleClass("downup");
	$(this).parent("dt").next("dd").toggle();
});

//选择部门编码
function chooseParentCategory(tag){
	$.ajax({
		  url:'${vix}/hr/probationAction!goChooseCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择部门编码",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
									$("#parentId").val(result[0]);
									
									if(tag==0){
									$("#id").val(result[1]);
									}
									else if (tag==1){
										$("#probationPost").val(result[1]);
									}
									else if (tag==2){
										$("#afterThePromotionDepartment").val(result[1]);
									}
									else if (tag==3){
										$("#afterThePromotionPost").val(result[1]);
									}
									else{
									$("#applicationDepartment").val(result[1]);
									}
							}else{
								$("#parentId").val("");
								$("#categoryName").html("");
								showErrorMessage("请选择岗位信息!");
								setTimeout("", 1000);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
/**
 * 选择公司的岗位
 */
function chooseParentOrgPosition(orgId){
	$.ajax({
		  url:'${vix}/hr/position/orgPositionAction!goChoosePosition.action',
		  data:"orgId="+orgId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择上级",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								if(result[0] == $("#entityId").val()){
									showErrorMessage("不允许引用岗位为父岗位!");
									setTimeout("", 1000);
									return false;
								}else{
									$("#parentPosId").val(result[0]);
									$("#parentPosName").val(result[1]);
								}
							}else{
								$("#parentPosId").val("");
								$("#parentPosName").val("");
								showErrorMessage("请选择岗位信息!");
								setTimeout("", 1000);
								return false;
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

$(function(){
	$("#orgPositionForm").validationEngine();
});

if($(".ms").length>0){
	$(".ms").hover(function(){
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">ul",this).stop().slideUp(100);
	});
	$(".ms ul li").hover(function(){
		$(">a",this).addClass("hover");
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">a",this).removeClass("hover");
		$(">ul",this).stop().slideUp(100);
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/hr_poisition.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/position/orgPositionAction!goList.action');">组织管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/position/orgPositionAction!goList.action');"> 岗位管理</a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<div class="order">
		<dl>
			<dt>
				<span class="no_line"> <a onclick="saveOrUpdateOrgPosition();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
						src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/hr/position/orgPositionAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
				</span> <strong> <b>岗位信息</b> <i></i>
				</strong>
			</dt>
			<dd class="clearfix">
				<div class="order_table">
					<form id="orgPositionForm">

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
									<table style="border: none;">
										<tr>
											<td align="right">岗位编码:&nbsp;</td>
											<%-- <td>
											<input type="hidden" id="entityId" name="entityForm.id" value="${entity.id}" />
											<input id="OrgCode" name="" type="text" value="${entity.organizationUnit.orgCode}"/>
											<input type="hidden" id="orgUnitId" name="entityForm.organizationUnit.id" value="${entity.organizationUnit.id}" />
											<input type="hidden" id="orgId" name="entityForm.organization.id" value="${entity.organization.id}" />
										</td>  --%>
											<td><input id="code" name="entityForm.code" value="${entity.code}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">岗位名称:&nbsp;</td>
											<td><input id="posName" name="entityForm.posName" value="${entity.posName}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">岗位序号:&nbsp;</td>
											<td><input id="postNumber" name="entityForm.postNumber" type="text" class="validate[optional,custom[number]] text-input" value="${entity.postNumber}" /></td>
											<td align="right">岗位性质:&nbsp;</td>
											<td><input id="jobNature" name="entityForm.jobNature" type="text" value="${entity.jobNature}" /></td>
										</tr>
										<tr>
											<td align="right">所属部门:&nbsp;</td>
											<td><input type="hidden" id="entityId" name="entityForm.id" value="${entity.id}" /> <span id="parentUnitName">${entity.organizationUnit.fs}</span> <input type="hidden" id="orgUnitId" name="entityForm.organizationUnit.id" value="${entity.organizationUnit.id}" /> <input type="hidden" id="orgId" name="entityForm.organization.id"
												value="${entity.organization.id}" /></td>
											<td align="right">上级岗位:&nbsp;</td>
											<td colspan="3"><input type="hidden" id="parentPosId" name="entityForm.parentOrgPosition.id" value="${entity.parentOrgPosition.id}" /> <input type="text" id="parentPosName" name="entityForm.parentOrgPosition.posName" readonly="readonly" value="${entity.parentOrgPosition.posName}" /> <input class="btn" type="button" value="选择"
												onclick="chooseParentOrgPosition('${orgId}');" /></td>
										</tr>
										<tr>
											<td align="right">编制人数:&nbsp;</td>
											<td><input id="personAmount" name="entityForm.personAmount" type="text" value="${entity.personAmount}" /></td>
											<td align="right">职务:&nbsp;</td>
											<td><s:select list="%{orgJobList}" listKey="id" listValue="jobName" name="entityForm.orgJob.id" value="%{entity.orgJob.id}" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">岗位薪级标准:&nbsp;</td>
											<td><input id="postSalaryScale" name="entityForm.postSalaryScale" type="text" value="${entity.postSalaryScale}" /></td>
											<td align="right">标准薪点:&nbsp;</td>
											<td><input id="standardPayPoint" name="entityForm.standardPayPoint" type="text" value="${entity.standardPayPoint}" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>

						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>其他信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">标准点值:&nbsp;</td>
											<td><input id="standardPointValue" name="entityForm.standardPointValue" type="text" value="${entity.standardPointValue}" /></td>
											<td align="right">岗位薪级:&nbsp;</td>
											<td><input id="postPayScale" name="entityForm.postPayScale" type="text" value="${entity.postPayScale}" /></td>
										</tr>
										<tr>
											<td align="right">有毒有害作业种类:&nbsp;</td>
											<td><input id="toxicAndHazardousTypes" name="entityForm.toxicAndHazardousTypes" type="text" value="${entity.toxicAndHazardousTypes}" /></td>
											<td align="right">发放标准:&nbsp;</td>
											<td><input id="paymentStandards" name="entityForm.paymentStandards" type="text" value="${entity.paymentStandards}" /></td>
										</tr>
										<tr>
											<td align="right">有毒有害接害类型:&nbsp;</td>
											<td><input id="toxicAndHarmfulToDamageType" name="entityForm.toxicAndHarmfulToDamageType" type="text" value="${entity.toxicAndHarmfulToDamageType}" /></td>
											<td align="right">干部职级:&nbsp;</td>
											<td><input id="ranksOfCadres" name="entityForm.ranksOfCadres" type="text" value="${entity.ranksOfCadres}" /></td>
										</tr>
										<tr>
											<td align="right">是否特殊工种岗位:&nbsp;</td>
											<td><s:radio list="#{'0':'是','1':'否'}" name="entityForm.whetherParticularTypesOfWorkStatus" value="%{entity.whetherParticularTypesOfWorkStatus}" theme="simple"></s:radio></td>
											<td align="right">是否有毒害岗位:&nbsp;</td>
											<td><s:radio list="#{'0':'是','1':'否'}" name="entityForm.arePoisonedJobs" value="%{entity.arePoisonedJobs}" theme="simple"></s:radio></td>
										</tr>
										<tr>
											<td align="right">是否点检岗位:&nbsp;</td>
											<td><s:radio list="#{'0':'是','1':'否'}" name="entityForm.whetherPointInspectionPosts" value="%{entity.whetherPointInspectionPosts}" theme="simple"></s:radio></td>
											<td align="right">是否虚构岗位:&nbsp;</td>
											<td><s:radio list="#{'0':'是','1':'否'}" name="entityForm.whetherFictitiousJobs" value="%{entity.whetherFictitiousJobs}" theme="simple"></s:radio></td>
										</tr>
										<tr>
											<td align="right">是否运行岗位:&nbsp;</td>
											<td><s:radio list="#{'0':'是','1':'否'}" name="entityForm.whetherToRunTheJob" value="%{entity.whetherToRunTheJob}" theme="simple"></s:radio></td>
											<td align="right">是否定员岗位:&nbsp;</td>
											<td><s:radio list="#{'0':'是','1':'否'}" name="entityForm.whetherCapacityPosts" value="%{entity.whetherCapacityPosts}" theme="simple"></s:radio></td>
										</tr>
										<tr>
											<td align="right">是否检修岗位:&nbsp;</td>
											<td><s:radio list="#{'0':'是','1':'否'}" name="entityForm.whetherMaintenanceJobs" value="%{entity.whetherMaintenanceJobs}" theme="simple"></s:radio></td>
											<td align="right">是否班组长:&nbsp;</td>
											<td><s:radio list="#{'0':'是','1':'否'}" name="entityForm.whetherTheTeamLeader" value="%{entity.whetherTheTeamLeader}" theme="simple"></s:radio></td>
										</tr>
										<tr>
											<td align="right">是否部门负责岗位:&nbsp;</td>
											<td><s:radio list="#{'0':'是','1':'否'}" name="entityForm.isChargeDep" value="%{entity.isChargeDep}" theme="simple"></s:radio></td>
											<td align="right">定员编码:&nbsp;</td>
											<td><input id="capacityCoding" name="entityForm.capacityCoding" type="text" value="${entity.capacityCoding}" /></td>
										</tr>
										<tr>
											<td align="right">备注:&nbsp;</td>
											<td colspan="1"><textarea id="memo" name="entityForm.memo" class="example" rows="1" style="width: 200px">${entity.memo}</textarea></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</form>
				</div>
			</dd>
			<div class="clearfix" style="background: #FFF; position: relative;">
				<div class="right_menu">
					<ul>
						<li class="current"><a onclick="javascript:tab(8,1,'a',event);"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						<li><a onclick="javascript:$('#a2').attr('style','');tab(8,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />KPI指标</a></li>
						<li><a onclick="javascript:$('#a3').attr('style','');tab(8,3,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />绩效考核</a></li>
					</ul>
				</div>
				<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
					<script type="text/javascript">
					$('#soAttach').datagrid({
						url: '${vix}/hr/position/orgPositionAction!getHrAttachmentsJson.action?id=${entity.id}',
						width: 900,
						height: '450',
						fitColumns: true,
						columns:[[
							{field:'id',title:'编号',width:80},
							{field:'name',title:'名称',width:180},
						]],
						toolbar:[{
							id:'saBtnadd',
							text:'添加',
							iconCls:'icon-add',
							handler:function(){
								$('#btnsave').linkbutton('enable');
								$.ajax({
									  url:'${vix}/hr/position/orgPositionAction!addHrAttachments.action',
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 364,
												height : 160,
												title:"上传附件",
												html:html,
												callback : function(action,returnValue){
													if(action == 'ok'){
														uploadFile('${vix}/hr/position/orgPositionAction!uploadHrAttachments.action?id=${entity.id}','fileToUpload');
														$('#soAttach').datagrid("reload");
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
						},'-',{
							text:'删除',
							iconCls:'icon-remove',
							handler:function(){
								var rows = $('#soAttach').datagrid("getSelections");	//获取你选择的所有行	
								//循环所选的行
								for(var i =0;i<rows.length;i++){
									var index = $('#soAttach').datagrid('getRowIndex',rows[i]);//获取某行的行号
									$('#soAttach').datagrid('deleteRow',index);	//通过行号移除该行
									$.ajax({url:'${vix}/hr/position/orgPositionAction!deleteHrAttachments.action?afId='+rows[i].id,cache: false});
								}
							}
						}]
					});
				</script>
					<div style="padding: 8px;">
						<table id="soAttach"></table>
					</div>
				</div>
				<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
					<div style="padding: 8px;">
						<table id="dlReceivedAddress" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlReceivedAddressTb',url: '${vix}/hr/position/orgPositionAction!getKpiIndexsJson.action?orgpositionId=${entity.id}'">
							<thead>
								<tr>
									<th data-options="field:'kpiCode',align:'center',width:120,editor:'text'">编码</th>
									<th data-options="field:'kpiName',width:200,align:'center',editor:'text'">名称</th>
									<th data-options="field:'kpiNameDepict',width:200,align:'center',editor:'text'">名称描述</th>
									<th data-options="field:'formula',width:200,align:'center',editor:'text'">公式</th>
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
									  url:'${vix}/hr/position/orgPositionAction!goAddKpiIndexs.action?orgpositionId=${entity.id}',
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
															$.post('${vix}/hr/position/orgPositionAction!saveOrUpdateKpiIndexs.action?orgpositionId=${entity.id}', {
																'kpiIndexs.id' : $("#daId").val(),
																'kpiIndexs.kpiCode' : $("#kpiCode").val(),
																'kpiIndexs.kpiName' : $("#kpiName").val(),
																'kpiIndexs.kpiNameDepict' : $("#kpiNameDepict").val(),
																'kpiIndexs.formula' : $("#formula").val(),
																'kpiIndexs.remarks' : $("#remarks").val(),
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
									  url:'${vix}/hr/position/orgPositionAction!goAddKpiIndexs.action?orgpositionId=${entity.id}&kpId='+rows.id,
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
															$.post('${vix}/hr/position/orgPositionAction!saveOrUpdateKpiIndexs.action?orgpositionId=${entity.id}', {
																'kpiIndexs.id' : $("#daId").val(),
																'kpiIndexs.kpiCode' : $("#kpiCode").val(),
																'kpiIndexs.kpiName' : $("#kpiName").val(),
																'kpiIndexs.kpiNameDepict' : $("#kpiNameDepict").val(),
																'kpiIndexs.formula' : $("#formula").val(),
																'kpiIndexs.remarks' : $("#remarks").val(),
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
									$.ajax({url:'${vix}/hr/position/orgPositionAction!deleteKpiIndexsById.action?kpId='+row.id,cache: false});
								}
							}
					</script>
					</div>
				</div>
				<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
					<div style="padding: 8px;">
						<table id="dlDeliveryPlan" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlDeliveryPlanTb',url: '${vix}/hr/position/orgPositionAction!getPerformanceAppraisalJson.action?orgpositionId=${entity.id}'">
							<thead>
								<tr>
									<th data-options="field:'perCode',align:'center',width:120,editor:'text'">编码</th>
									<th data-options="field:'perName',width:200,align:'center',editor:'text'">指标名称</th>
									<th data-options="field:'perType',width:200,align:'center',editor:'text'">指标分类</th>
									<th data-options="field:'perContent',width:200,align:'center',editor:'text'">考核标准</th>
									<th data-options="field:'perScore',width:200,align:'center',editor:'text'">考核周期</th>
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
									  url:'${vix}/hr/position/orgPositionAction!goAddPerformanceAppraisalIndexs.action?orgpositionId=${entity.id}',
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
															$.post('${vix}/hr/position/orgPositionAction!saveOrUpdatePerformanceAppraisalIndexs.action?orgpositionId=${entity.id}', {
																'performanceAppraisal.id' : $("#daId").val(),
																'performanceAppraisal.perCode' : $("#perCode").val(),
																'performanceAppraisal.perName' : $("#perName").val(),
																'performanceAppraisal.perType' : $("#perType").val(),
																'performanceAppraisal.perContent' : $("#perContent").val(),
																'performanceAppraisal.perScore' : $("#perScore").val(),
																'performanceAppraisal.remarks' : $("#remarks").val(),
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
									  url:'${vix}/hr/position/orgPositionAction!goAddPerformanceAppraisalIndexs.action?orgpositionId=${entity.id}&perid='+rows.id,
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
															$.post('${vix}}/hr/position/orgPositionAction!saveOrUpdatePerformanceAppraisalIndexs.action?orgpositionId=${entity.id}', {
																'performanceAppraisal.id' : $("#daId").val(),
																'performanceAppraisal.perCode' : $("#perCode").val(),
																'performanceAppraisal.perName' : $("#perName").val(),
																'performanceAppraisal.perType' : $("#perType").val(),
																'performanceAppraisal.perContent' : $("#perContent").val(),
																'performanceAppraisal.perScore' : $("#perScore").val(),
																'performanceAppraisal.remarks' : $("#remarks").val(),
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
									$.ajax({url:'${vix}/hr/position/orgPositionAction!deletePerformanceAppraisalById.action?perId='+row.id,cache: false});
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
			</div>
	</div>
	<!--oder-->

	<!--submenu-->
	<script src="${vix}/common/js/jtip.js" type="text/javascript"></script>
	<script type="text/javascript">
<!--
//提示
if($('input.sweet-tooltip').length){
	$('input.sweet-tooltip').focus(function() {
		tooltip				= $(this);
		tooltipText 		= tooltip.attr('data-text-tooltip');
		tooltipClassName	= 'tooltip';
		tooltipClass		= '.' + tooltipClassName;
		
		if(tooltip.hasClass('showed-tooltip')) return false;
		
		tooltip.addClass('showed-tooltip')
				 .after('<div class="'+tooltipClassName+'"><div class="tooltip_l"></div><div class="tooltip_r"></div><div class="tooltip_x">'+tooltipText+'</div><div class="tooltip_b"></div></div>');
		tooltipPosTop 	= tooltip.position().top - $(tooltipClass).outerHeight() - 10;
		tooltipPosLeft = tooltip.position().left;
		tooltipPosLeft = tooltipPosLeft - (($(tooltipClass).outerWidth()/2) - tooltip.outerWidth()/2);
		$(tooltipClass).css({ 'left': tooltipPosLeft, 'top': tooltipPosTop }).animate({'opacity':'1', 'marginTop':'0'}, 500);
	}).focusout(function() {
		$(tooltipClass).animate({'opacity':'0', 'marginTop':'-10px'}, 500, function() {
			$(this).remove();
			tooltip.removeClass('showed-tooltip');
				
		});
	});
}
//-->
</script>