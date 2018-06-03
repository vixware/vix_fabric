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
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});

function resize(){
	$('#test').datagrid('resize', {
		width:700,
		height:400
	});
}
function getSelected(){
	var selected = $('#test').datagrid('getSelected');
	if (selected){
		alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
	}
}
function getSelections(){
	var ids = [];
	var rows = $('#test').datagrid('getSelections');
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].code);
	}
	alert(ids.join(':'));
}
function clearSelections(){
	$('#test').datagrid('clearSelections');
}
function selectRow(){
	$('#test').datagrid('selectRow',2);
}
function selectRecord(){
	$('#test').datagrid('selectRecord','002');
}
function unselectRow(){
	$('#test').datagrid('unselectRow',2);
}
function mergeCells(){
	$('#test').datagrid('mergeCells',{
		index:2,
		field:'addr',
		rowspan:2,
		colspan:2
	});
}


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

/** 保存培训计划 */
function saveOrUpdateOrder(){
	var orderItemStr = "";
	if($('#replanForm').validationEngine('validate')){
		$.post('${vix}/contract/contractInquiryAction!saveOrUpdate.action',
			{
			'contractInquiry.id':$("#id").val(),
			'contractInquiry.masterContractCoding':$("#masterContractCoding").val(),
			'contractInquiry.contractGroupType.id':$("#contractGroupTypeId").val(),
			'contractInquiry.contractGroupType.id':$("#contractGroupTypeId").val(),
			'contractInquiry.contractTypeCombine.id':$("#contractTypeCombineId").val(),
			'contractInquiry.contractNatureType.id':$("#contractNatureTypeId").val(),
			'contractInquiry.viewIndustryType.id':$("#viewIndustryTypeId").val(),
			'contractInquiry.modeType.id':$("#modeTypeId").val(),
			'contractInquiry.enableStageType.id':$("#enableStageTypeId").val(),
			'contractInquiry.contractStageGroupType.id':$("#contractStageGroupTypeId").val(),
			'contractInquiry.currencyType.id':$("#currencyTypeId").val(),
			'contractInquiry.exchangeRate.id':$("#exchangeRateId").val(),
			'contractInquiry.contractCode':$("#contractCode").val(),
			'contractInquiry.contractName':$("#contractName").val(),
			'contractInquiry.projectCode':$("#projectCode").val(),
			'contractInquiry.projectName':$("#projectName").val(),
			'contractInquiry.signDate':$("#signDate").val(),
			'contractInquiry.contractStartTime':$("#contractStartTime").val(),
			'contractInquiry.contractEndTime':$("#contractEndTime").val(),
			'contractInquiry.operator':$("#operator").val(),
			'contractInquiry.negotiator':$("#negotiator").val(),
			'contractInquiry.approval':$("#approval").val(),
			'contractInquiry.approvalDate':$("#approvalDate").val(),
			'contractInquiry.departmentName':$("#departmentName").val(),
			'contractInquiry.contractTotalQuantity':$("#contractTotalQuantity").val(),
			'contractInquiry.numberContractExecution':$("#numberContractExecution").val(),
			'contractInquiry.firstParty':$("#firstParty").val(),
			'contractInquiry.firstPartyAddress':$("#firstPartyAddress").val(),
			'contractInquiry.partyUnitName':$("#partyUnitName").val(),
			'contractInquiry.firstPartyContact':$("#firstPartyContact").val(),
			'contractInquiry.firstPartyPhone':$("#firstPartyPhone").val(),
			'contractInquiry.firstPartyEmail':$("#firstPartyEmail").val(),
			'contractInquiry.secondPartyId':$("#secondPartyId").val(),
			'contractInquiry.secondParty':$("#secondParty").val(),
			'contractInquiry.currency':$("#currency").val(),
			'contractInquiry.baddress':$("#baddress").val(),
			'contractInquiry.bunitName':$("#bunitName").val(),
			'contractInquiry.bcontact':$("#bcontact").val(),
			'contractInquiry.bphone':$("#bphone").val(),
			'contractInquiry.email':$("#email").val(),
			'contractInquiry.totalAmount' : $("#totalAmount").val(),
			'contractInquiry.contractExecutionMoney' : $("#contractExecutionMoney").val(),
			'contractInquiry.prepaymentAmount' : $("#prepaymentAmount").val(),
			'contractInquiry.outstandingAmounts' : $("#outstandingAmounts").val(),
			'contractInquiry.retentionCalculation' : $("#retentionCalculation").val(),
			'contractInquiry.retentionsStartDate' : $("#retentionsStartDate").val(),
			'contractInquiry.retentionEndDate' : $("#retentionEndDate").val(),
			'contractInquiry.warrantyAmount' : $("#warrantyAmount").val(),
			'contractInquiry.warranty' : $("#warranty").val(),
			'contractInquiry.mainContent' : contents.html(),
			'contractInquiry.remark' : $("#remark").val(),
			'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/contract/contractInquiryAction!goList.action?menuId=menuOrder');
			}
		 );
	}
}

/** 保存并新增培训计划 */
function saveOrAdd(){
	var orderItemStr = "";
		$.post('${vix}/contract/contractInquiryAction!saveOrUpdate.action',
		   {
			'contractInquiry.id':$("#id").val(),
			'contractInquiry.masterContractCoding':$("#masterContractCoding").val(),
			'contractInquiry.contractGroupType.id':$("#contractGroupTypeId").val(),
			'contractInquiry.contractGroupType.id':$("#contractGroupTypeId").val(),
			'contractInquiry.contractTypeCombine.id':$("#contractTypeCombineId").val(),
			'contractInquiry.contractNatureType.id':$("#contractNatureTypeId").val(),
			'contractInquiry.viewIndustryType.id':$("#viewIndustryTypeId").val(),
			'contractInquiry.modeType.id':$("#modeTypeId").val(),
			'contractInquiry.enableStageType.id':$("#enableStageTypeId").val(),
			'contractInquiry.contractStageGroupType.id':$("#contractStageGroupTypeId").val(),
			'contractInquiry.currencyType.id':$("#currencyTypeId").val(),
			'contractInquiry.exchangeRate.id':$("#exchangeRateId").val(),
			'contractInquiry.contractCode':$("#contractCode").val(),
			'contractInquiry.contractName':$("#contractName").val(),
			'contractInquiry.projectCode':$("#projectCode").val(),
			'contractInquiry.projectName':$("#projectName").val(),
			'contractInquiry.signDate':$("#signDate").val(),
			'contractInquiry.contractStartTime':$("#contractStartTime").val(),
			'contractInquiry.contractEndTime':$("#contractEndTime").val(),
			'contractInquiry.operator':$("#operator").val(),
			'contractInquiry.currency':$("#currency").val(),
			'contractInquiry.negotiator':$("#negotiator").val(),
			'contractInquiry.approval':$("#approval").val(),
			'contractInquiry.approvalDate':$("#approvalDate").val(),
			'contractInquiry.departmentName':$("#departmentName").val(),
			'contractInquiry.firstParty':$("#firstParty").val(),
			'contractInquiry.firstPartyAddress':$("#firstPartyAddress").val(),
			'contractInquiry.partyUnitName':$("#partyUnitName").val(),
			'contractInquiry.firstPartyContact':$("#firstPartyContact").val(),
			'contractInquiry.firstPartyPhone':$("#firstPartyPhone").val(),
			'contractInquiry.firstPartyEmail':$("#firstPartyEmail").val(),
			'contractInquiry.secondPartyId':$("#secondPartyId").val(),
			'contractInquiry.secondParty':$("#secondParty").val(),
			'contractInquiry.baddress':$("#baddress").val(),
			'contractInquiry.bunitName':$("#bunitName").val(),
			'contractInquiry.bcontact':$("#bcontact").val(),
			'contractInquiry.bphone':$("#bphone").val(),
			'contractInquiry.email':$("#email").val(),
			'contractInquiry.totalAmount' : $("#totalAmount").val(),
			'contractInquiry.contractExecutionMoney' : $("#contractExecutionMoney").val(),
			'contractInquiry.prepaymentAmount' : $("#prepaymentAmount").val(),
			'contractInquiry.outstandingAmounts' : $("#outstandingAmounts").val(),
			'contractInquiry.retentionCalculation' : $("#retentionCalculation").val(),
			'contractInquiry.retentionsStartDate' : $("#retentionsStartDate").val(),
			'contractInquiry.retentionEndDate' : $("#retentionEndDate").val(),
			'contractInquiry.warrantyAmount' : $("#warrantyAmount").val(),
			'contractInquiry.warranty' : $("#warranty").val(),
			'contractInquiry.mainContent' : contents.html(),
			'contractInquiry.remark' : $("#remark").val(),
			'orderItemStr':orderItemStr
		   },
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/contract/contractInquiryAction!goSaveOrUpdate.action');
			} 
		);
}

//页面首次加载
$(function(){
	$("#currency").val('${contractInquiry.currency}');
});

//选择项目名称
function chooseProjectName(){
		$.ajax({
			  url:'${vix}/contract/contractInquiryAction!goProjectNameList.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 1000,
						height : 500,
						title:"选择项目名称",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var rv = returnValue.split(",");
									$("#contractInquiryID").val(rv[0]);
									$("#projectName").val(rv[1]);
								}else{
									$("#projectName").val("");
									asyncbox.success("请选择项目名称!","提示信息");
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}

/**
 * 选择负责部门
 */
 function chooseBulletinOrgUnit(){
 	 $.ajax({
 		  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
 		  //data:{checkedObjIds:checkObjIds,chkStyle:"checkbox"},
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
 								var pubIdTmp = $("#pubIds").val();
 								
 								pubIdTmp = pubIdTmp + ",";
 								
 								/* if(resObj.length == 0 ){
 									return;
 								} */
 								//debugger;
 								for(var i=0;i<resObj.length;i++){
 									//alert(resObj[i].treeId+"--"+resObj[i].name);
 									if(resObj[i].treeType!="O"){
 										asyncbox.alert("只能选择部门信息！","提示");
 										return;
 									} 
 									if(!pubIdTmp.contains(resObj[i].treeId+",")){
 										selectIds += "," + resObj[i].treeId;
 										selectNames += "," + resObj[i].name;
 									}
 								}
 								selectIds = $("#pubIds").val()+selectIds;
 								selectNames = $("#departmentName").val()+selectNames;
 								
 								$("#pubIds").val(selectIds);
 								selectNames = selectNames.substring(1,selectNames.length);
 								$("#departmentName").val(selectNames);
 							}
 						}
 					},
 					btnsbar : $.btn.OKCANCEL
 				});
 		  }
 	});
 }
 
//选择供应商信息
 function chooseRadioSupplier(){
 		$.ajax({
 			  url:'${vix}/contract/contractInquiryAction!goSubRadioSingleList.action',
 			  cache: false,
 			  success: function(html){
 				  asyncbox.open({
 					 	modal:true,
 						width : 1000,
 						height : 500,
 						title:"选择供应商信息",
 						html:html,
 						callback : function(action,returnValue){
 							if(action == 'ok'){
 								if(returnValue != undefined){
 									var rv = returnValue.split(",");
 									$("#firstPartyId").val(rv[0]);
 									$("#firstParty").val(rv[1]);
 								}else{
 									$("#firstParty").val("");
 									asyncbox.success("请选择采购信息!","提示信息");
 								}
 							}
 						},
 						btnsbar : $.btn.OKCANCEL
 					});
 			  }
 		});
 	}
 
 /** 快速新建供应商 */
 function createSupplier(){
 	$.ajax({
 		  url:'${vix}/contract/contractInquiryAction!goAddQuicklySupplier.action?',
 		  cache: false,
 		  success: function(html){
 		 	  asyncbox.open({
 				 	modal:true,
 					width : 800,
 					height : 500,
 					title:"快速新建供应商",
 					html:html,
 					callback : function(action){
 						if(action == 'ok'){
 							if($('#supplierForm').validationEngine('validate')){
 								$.post('${vix}/contract/contractInquiryAction!saveOrUpdateSupplier.action', {
 										'supplier.id' : $("#srmId").val(),
 										'supplier.code': $("#newSupplierCode").val(),
 										'supplier.name': $("#newSupplierName").val()
 										}, function(result){
 											var rt = result.split(",");
 											showMessage(rt[0]+rt[3]);
 											setTimeout("",1000);
 											$("#id").val(rt[1]);
 											$("#firstParty").val(rt[2]);
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
 
$("#replanForm").validationEngine();
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
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/ctm_contract.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractInquiryAction!goList.action?pageNo=${pageNo}');"><s:text name="ctm_agreement" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractInquiryAction!goList.action?pageNo=${pageNo}');"><s:text name="ctm_contract_inquiries" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractInquiryAction!goList.action?pageNo=${pageNo}');"><s:text name="新增销售合同" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="contractType" name="contractType" value="${contractInquiry.contractType}" />
<div class="content">
	<form id="replanForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="javascript:void(0)" onclick="saveOrAdd()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/contract/contractInquiryAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增销售合同" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
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
										<%--检查ID，判断修改--%>
										<input type="hidden" id="id" name="id" value="${contractInquiry.id}" />
										<tr>
											<td align="right">主合同编码：</td>
											<td><input id="masterContractCoding" name="masterContractCoding" value="${contractInquiry.masterContractCoding }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<th align="right">所属合同组：</th>
											<td><s:select id="contractGroupTypeId" headerKey="-1" headerValue="--请选择--" list="%{contractGroupTypeList}" listValue="name" listKey="id" value="%{contractInquiry.contractGroupType.id}" multiple="false" theme="simple">
												</s:select></td>
										</tr>
										<tr>
											<th align="right">合同编码：</th>
											<td><input id="contractCode" name="contractCode" value="${contractInquiry.contractCode}" type="text" size="30" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
											<th align="right">合同名称：</th>
											<td><input id="contractName" name="contractName" value="${contractInquiry.contractName}" type="text" size="30" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">项目代码：</th>
											<td><input id="projectCode" name="projectCode" value="${contractInquiry.projectCode}" type="text" size="30" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
											<th align="right">项目名称：</th>
											<td><input id="projectName" name="projectName" value="${contractInquiry.projectName}" type="text" size="18" class="validate[required] text-input" /> <span style="color: red;">*</span> <input type="hidden" id="contractInquiryID" name="contractInquiryID" value="${contractInquiry.id}" /> <input class="btn" type="button" value="选择"
												onclick="chooseProjectName();" /></td>
										</tr>
										<tr>
											<td align="right">类型：</td>
											<td><s:select id="contractTypeCombineId" headerKey="" headerValue="--请选择--" list="%{contractTypeCombineList}" listValue="name" listKey="id" value="%{contractInquiry.contractTypeCombine.id}" multiple="false" theme="simple">
												</s:select></td>
											<th align="right">合同性质：</th>
											<td><s:select id="contractNatureTypeId" headerKey="" headerValue="--请选择--" list="%{contractNatureTypeList}" listValue="name" listKey="id" value="%{contractInquiry.contractNatureType.id}" multiple="false" theme="simple">
												</s:select></td>
										</tr>
										<tr>
											<th align="right">所属行业：</th>
											<td><s:select id="viewIndustryTypeId" headerKey="" headerValue="--请选择--" list="%{viewIndustryList}" listValue="name" listKey="id" value="%{contractInquiry.viewIndustryType.id}" multiple="false" theme="simple">
												</s:select></td>
											<th align="right">合同签订日期：</th>
											<td><input id="signDate" name="signDate" value="<fmt:formatDate value='${contractInquiry.signDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('signDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">合同开始日期：</th>
											<td><input id="contractStartTime" name="contractStartTime" value="<fmt:formatDate value='${contractInquiry.contractStartTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('contractStartTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
											<th align="right">合同终止日期：</th>
											<td><input id="contractEndTime" name="contractEndTime" value="<fmt:formatDate value='${contractInquiry.contractEndTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('contractEndTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">
											</td>
										</tr>
										<tr>
											<th align="right">经办人：</th>
											<td><input id="operator" name="operator" value="${contractInquiry.operator}" type="text" size="30" /></td>
											<th align="right">谈判人：</th>
											<td><input id="negotiator" name="negotiator" value="${contractInquiry.negotiator}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">审批人：</th>
											<td><input id="approval" name="approval" value="${contractInquiry.approval}" type="text" size="30" /></td>
											<th align="right">审批日期：</th>
											<td><input id="approvalDate" name="approvalDate" value="<fmt:formatDate value='${contractInquiry.approvalDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('approvalDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">合同履行状态：</th>
											<td><s:select id="modeTypeId" headerKey="" headerValue="--请选择--" list="%{modeTypeList}" listValue="name" listKey="id" value="%{contractInquiry.modeType.id}" multiple="false" theme="simple">
												</s:select></td>
											<th align="right">所属部门：</th>
											<td colspan="3"><input type="hidden" id="pubIds" name="pubIds" value="${pubIds}" /> <input type="hidden" id="treeType" name="treeType" value="${treeType}" /> <input type="text" id="departmentName" name="departmentName" readonly="readonly" value="${contractInquiry.departmentName}" /> <input class="btn" type="button" value="选择"
												onclick="chooseBulletinOrgUnit();" /></td>
										</tr>
										<tr>
											<td align="right">合同总数量：</td>
											<td><input id="contractTotalQuantity" name="contractTotalQuantity" value="${contractInquiry.contractTotalQuantity}" type="text" size="30" /></td>
											<td align="right">合同执行数量：</td>
											<td><input id="numberContractExecution" name="numberContractExecution" value="${contractInquiry.numberContractExecution}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">启用阶段：</th>
											<td><s:select id="enableStageTypeId" headerKey="" headerValue="--请选择--" list="%{enableStageTypeList}" listValue="name" listKey="id" value="%{contractInquiry.enableStageType.id}" multiple="false" theme="simple">
												</s:select></td>
											<th align="right">合同阶段组：</th>
											<td><s:select id="contractStageGroupTypeId" headerKey="" headerValue="--请选择--" list="%{contractStageGroupTypeList}" listValue="name" listKey="id" value="%{contractInquiry.contractStageGroupType.id}" multiple="false" theme="simple">
												</s:select></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>

						<div class="voucher newvoucher">
							<dl>
								<dt>
									<!-- <span style=""><input class="btn" type="button" value="新增丙方" onclick="NewContract();"/> -->
									</span> <strong>合同方</strong>
									<!-- <div  align="right">
										<ul> 
											<li>
												<input class="btn" type="button" value="新增丙方" onclick="NewContract();"/>
											</li>
										</ul> 
									</div> -->
								</dt>

								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">甲方：</th>
											<td><input id="firstParty" name="firstParty" value="${contractInquiry.firstParty}" type="text" size="8" class="validate[required] text-input" /> <span style="color: red;">*</span> <input type="hidden" id="firstParty" name="firstParty" value="${contractInquiry.id}" /> <input class="btn" type="button" value="选择信息"
												onclick="chooseRadioSupplier();" /> <input class="btn" type="button" value="新增" onclick="createSupplier();" /></td>
											<td align="right">乙方：</td>
											<td><input id="secondParty" name="secondParty" value="${contractInquiry.secondParty}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">甲方单位名称：</th>
											<td><input id="partyUnitName" name="partyUnitName" value="${contractInquiry.partyUnitName}" type="text" size="30" /></td>
											<th align="right">乙方单位名称：</th>
											<td><input id="bUnitName" name="bUnitName" value="${contractInquiry.bunitName}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">甲方单位地址：</th>
											<td><input id="firstPartyAddress" name="firstPartyAddress" value="${contractInquiry.firstPartyAddress}" type="text" size="30" /></td>
											<td align="right">乙方单位地址：</td>
											<td><input id="bAddress" name="bAddress" value="${contractInquiry.baddress}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">甲方联系人：</th>
											<td><input id="firstPartyContact" name="firstPartyContact" value="${contractInquiry.firstPartyContact}" type="text" size="30" /></td>
											<th align="right">乙方联系人：</th>
											<td><input id="bContact" name="bContact" value="${contractInquiry.bcontact}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">甲方电话：</th>
											<td><input id="firstPartyPhone" name="firstPartyPhone" value="${contractInquiry.firstPartyPhone}" type="text" size="30" class="validate[required,custom[integer]] text-input" /></td>
											<th align="right">乙方电话：</th>
											<td><input id="bPhone" name="bPhone" value="${contractInquiry.bphone}" type="text" size="30" class="validate[required,custom[integer]] text-input" /></td>
										</tr>
										<tr>
											<th align="right">甲方邮件：</th>
											<td><input id="firstPartyEmail" name="firstPartyEmail" value="${contractInquiry.firstPartyEmail}" type="text" size="30" class="validate[required,custom[email]] text-input" /></td>
											<th align="right">乙方邮件：</th>
											<td><input id="email" name="email" value="${contractInquiry.email}" type="text" size="30" class="validate[required,custom[email]] text-input" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>

						<div class="voucher newvoucher">
							<dl>
								<dt>
									<span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>金额</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">币种：</th>
											<td><select id="currency" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">美元</option>
													<option value="2">人民币</option>
													<option value="3">欧元</option>
													<option value="4">日元</option>
											</select> <span style="color: red;">*</span></td>
											<th align="right">汇率：</th>
											<td><s:select id="exchangeRateId" headerKey="" headerValue="--请选择--" list="%{exchangeRateList}" listValue="exchangeRate" listKey="id" value="%{contractInquiry.exchangeRate.id}" multiple="false" theme="simple">
												</s:select></td>
										</tr>
										<tr>
											<td align="right">合同总金额：</td>
											<td><input class="easyui-numberbox" id="totalAmount" name="totalAmount" value="${contractInquiry.totalAmount}" type="text" size="30" data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'$'"></input></td>
											<td align="right">合同执行金额：</td>
											<td><input class="easyui-numberbox" id="contractExecutionMoney" name="contractExecutionMoney" value="${contractInquiry.contractExecutionMoney}" type="text" size="30" data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'$'"></input></td>
										</tr>
										<tr>
											<th align="right">预付款金额：</th>
											<td><input class="easyui-numberbox" id="prepaymentAmount" name="prepaymentAmount" value="${contractInquiry.prepaymentAmount}" type="text" size="30" data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'$'"></input></td>
											<th align="right">未结金额：</th>
											<td><input class="easyui-numberbox" id="outstandingAmounts" name="outstandingAmounts" value="${contractInquiry.outstandingAmounts}" type="text" size="30" data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'$'"></input></td>
										</tr>
										<tr>
											<td align="right">质保金计算方式：</td>
											<td><input id="retentionCalculation" name="retentionCalculation" value="${contractInquiry.retentionCalculation}" type="text" size="30" /></td>
											<th align="right">质保金开始日期：</th>
											<td><input id="retentionsStartDate" name="retentionsStartDate" value="<fmt:formatDate value='${contractInquiry.retentionsStartDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('retentionsStartDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">质保金结束日期：</th>
											<td><input id="retentionEndDate" name="retentionEndDate" value="<fmt:formatDate value='${contractInquiry.retentionEndDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('retentionEndDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">
											</td>
											<td align="right">质保金比例（%）：</td>
											<td><input id="retentionRatio" name="retentionRatio" value="${contractInquiry.retentionRatio}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">质保金额度：</td>
											<td><input class="easyui-numberbox" id="warrantyAmount" name="warrantyAmount" value="${contractInquiry.warrantyAmount}" type="text" size="30" data-options="precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'$'"></input></td>
											<td align="right">保修期：</td>
											<td><input id="warranty" name="warranty" value="${contractInquiry.warranty}" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>

						<div class="voucher newvoucher">
							<dl>
								<dt>
									<span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>内容</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">合同内容：</th>
											<td colspan="3"><textarea id="mainContent" name="mainContent">${contractInquiry.mainContent}</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
												 var contents = KindEditor.create('textarea[name="mainContent"]',
													{basePath:'${vix}/plugin/KindEditor/',
														width:950,
														height:200,
														cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
														uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
														fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
														allowFileManager : true 
													}
									 				);
											  </script></td>
										</tr>
										<tr>
											<th align="right">备注：</th>
											<td colspan="3"><textarea id="remark" name="remark" class="example" rows="1" style="width: 950px">${contractInquiry.remark}</textarea></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(6,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />合同子项</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(6,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />合同标的</a></li>
							<li><a onclick="javascript:$('#a3').attr('style','');tab(6,3,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />合同预警</a></li>
							<li><a onclick="javascript:$('#a4').attr('style','');tab(6,4,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />合同审批</a></li>
							<li><a onclick="javascript:$('#a5').attr('style','');tab(6,5,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />价格条件</a></li>
							<li><a onclick="javascript:$('#a6').attr('style','');tab(6,6,'a',event)"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						</ul>
					</div>

					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
						function saveContractLine(url){
							$.ajax({
								  url:url,
								  cache: false,
								  success: function(html){
									  asyncbox.open({
										  	title:'合同子项明细 ',
										 	modal:true,
											width : 724,
											height :340,
											html:html,
											callback : function(action,returnValue){
												if(action == 'ok'){
													if($('#daForm').validationEngine('validate')){
														$.post('${vix}/contract/contractInquiryAction!saveOrUpdateContractLine.action',
																{'id':$("#id").val(),
																  'childItem.id':$("#daId").val(),
																  'childItem.contractStatus':$("#contractStatus").val(),
																  'childItem.contractType':$("#contractType").val(),
																  'childItem.theContract':$("#theContract").val(),
																},
																function(result){
																	showMessage(result);
																	setTimeout("",1000);
																	$('#contractLine').datagrid("reload");
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
						$('#contractLine').datagrid({
							url: '${vix}/contract/contractInquiryAction!getContractLineJson.action?id=${contractInquiry.id}',
							title: '合同子项明细',
							width: 'auto',
							height: '450',
							rownumbers : true,
							idField : 'id',
							fitColumns: true,
							columns:[[
								{field:'contractType',title:'类型',width:200,align:'center'},
								{field:'contractStatus',title:'状态',width:200,align:'center'},
								{field:'theContract',title:'内容',width:200,align:'center'},
							]],
							toolbar:[{
								id:'da2Btnadd',
								text:'添加',
								iconCls:'icon-add',
								handler:function(){
									$('#btnsave').linkbutton('enable');
									saveContractLine('${vix}/contract/contractInquiryAction!goAddContractLine.action?id=0');
								}
							},'-',{
								id:'btnedit',
								text:'修改',
								iconCls: 'icon-edit',
								handler: function(){
									var row = $('#contractLine').datagrid("getSelected");	//获取你选择的所有行
									if(row){
										saveContractLine('${vix}/contract/contractInquiryAction!goAddContractLine.action?id='+row.id)
									}
								}
							},'-',{
								text:'删除',
								iconCls:'icon-remove',
								handler:function(){
									var rows = $('#contractLine').datagrid("getSelections");	//获取你选择的所有行	
									//循环所选的行
									for(var i =0;i<rows.length;i++){
										var index = $('#contractLine').datagrid('getRowIndex',rows[i]);//获取某行的行号
										$('#contractLine').datagrid('deleteRow',index);	//通过行号移除该行
										$.ajax({url:'${vix}/contract/contractInquiryAction!deleteContractLineById.action?id='+rows[i].id,cache: false});
									}
								}
							}]
						});
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
						<div style="padding: 8px;">
							<table id="contractLine"></table>
						</div>
					</div>

					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
						function saveContractSubject(url){
							$.ajax({
								  url:url,
								  cache: false,
								  success: function(html){
									  asyncbox.open({
										  	title:'合同标的明细 ',
										 	modal:true,
											width : 724,
											height :340,
											html:html,
											callback : function(action,returnValue){
												if(action == 'ok'){
													if($('#subjectForm').validationEngine('validate')){
														$.post('${vix}/contract/contractInquiryAction!saveOrUpdateContractSubject.action',
																{'id':$("#id").val(),
																  'subject.id':$("#daId").val(),
																  'subject.subjectCode':$("#subjectCode").val(),
																  'subject.subjectName':$("#subjectName").val(),
																  'subject.source':$("#source").val(),
																  'subject.stockClassificationCode':$("#stockClassificationCode").val(),
																  'subject.correspondingInventoryCode':$("#correspondingInventoryCode").val(),
																  'subject.projectCatalog':$("#projectCatalog").val(),
																  'subject.inventoriesSpecification':$("#inventoriesSpecification").val(),
																  'subject.nnTOCurrencyPrice':$("#nnTOCurrencyPrice").val(),
																  'subject.ttIOriginalCurrencyPrice':$("#ttIOriginalCurrencyPrice").val(),
																  'subject.nnTaxAmountOriginalCurrency':$("#nnTaxAmountOriginalCurrency").val(),
																  'subject.ttATOriginalCurrency':$("#ttATOriginalCurrency").val(),
																  'subject.executionQuantity':$("#executionQuantity").val(),
																  'subject.eeTAOriginalCurrency':$("#eeTAOriginalCurrency").val(),
																  'subject.eeTAIncLriginalCurrency':$("#eeTAIncLriginalCurrency").val(),
																  'subject.startTime':$("#startTime").val(),
																  'subject.endTime':$("#endTime").val()
																},
																function(result){
																	showMessage(result);
																	setTimeout("",1000);
																	$('#contractSubject').datagrid("reload");
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
						$('#contractSubject').datagrid({
							url: '${vix}/contract/contractInquiryAction!getContractSubjectJson.action?id=${contractInquiry.id}',
							title: '合同标的明细',
							width: 'auto',
							height: '450',
							rownumbers : true,
							idField : 'id',
							fitColumns: true,
							columns:[[
								{field:'subjectCode',title:'标题编码',width:80,align:'center'},
								{field:'subjectName',title:'标题名称',width:80,align:'center'},
								{field:'source',title:'来源',width:100,align:'center'},
								{field:'stockClassificationCode',title:'存货分类编码',width:98,align:'center'},
								{field:'correspondingInventoryCode',title:'对应存货编码',width:98,align:'center'},
								{field:'projectCatalog',title:'项目大类',width:98,align:'center'},
								{field:'inventoriesSpecification',title:'存货规格型号',width:98,align:'center'},
								{field:'nnTOCurrencyPrice',title:'无税原币单价',width:98,align:'center'},
								{field:'ttIOriginalCurrencyPrice',title:'含税原币单价',width:98,align:'center'},
								{field:'nnTaxAmountOriginalCurrency',title:'无税原币金额',width:100,align:'center'},
								{field:'ttATOriginalCurrency',title:'含税原币金额',width:100,align:'center'},
								{field:'executionQuantity',title:'执行数量',width:98,align:'center'},
								{field:'eeTAOriginalCurrency',title:'执行无税金额原币',width:98,align:'center'},
								{field:'eeTAIncLriginalCurrency',title:'执行含税金额原币',width:98,align:'center'},
								{field:'startTime',title:'计划开始时间',width:120,align:'center',formatter:formatDatebox},
								{field:'endTime',title:'计划结束时间',width:120,align:'center',formatter:formatDatebox},
							]],
							toolbar:[{
								id:'da2Btnadd',
								text:'添加',
								iconCls:'icon-add',
								handler:function(){
									$('#btnsave').linkbutton('enable');
									saveContractSubject('${vix}/contract/contractInquiryAction!goAddContractSubject.action?id=0');
								}
							},'-',{
								id:'btnedit',
								text:'修改',
								iconCls: 'icon-edit',
								handler: function(){
									var row = $('#contractSubject').datagrid("getSelected");	//获取你选择的所有行
									if(row){
										saveContractSubject('${vix}/hr/contractInquiryAction!goAddContractSubject.action?id='+row.id)
									}
								}
							},'-',{
								text:'删除',
								iconCls:'icon-remove',
								handler:function(){
									var rows = $('#contractSubject').datagrid("getSelections");	//获取你选择的所有行	
									//循环所选的行
									for(var i =0;i<rows.length;i++){
										var index = $('#contractSubject').datagrid('getRowIndex',rows[i]);//获取某行的行号
										$('#contractSubject').datagrid('deleteRow',index);	//通过行号移除该行
										$.ajax({url:'${vix}/contract/contractInquiryAction!deleteContractSubjectById.action?id='+rows[i].id,cache: false});
									}
								}
							}]
						});
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
						<div style="padding: 8px;">
							<table id="contractSubject"></table>
						</div>
					</div>

					<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
							function saveContractWarning(url){
								$.ajax({
									  url:url,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											  	title:'合同预警明细 ',
											 	modal:true,
												width : 724,
												height :340,
												html:html,
												callback : function(action,returnValue){
													if(action == 'ok'){
														if($('#warningForm').validationEngine('validate')){
															$.post('${vix}/contract/contractInquiryAction!saveOrUpdateContractWarning.action',
																	{'id':$("#id").val(),
																	  'warning.id':$("#daId").val(),
																	  'warning.warnningContent':$("#warnningContent").val(),
																	  'warning.warnningTime':$("#warnningTime").val(),
																	  'warning.warnningType':$("#warnningType").val(),
																	},
																	function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#contractWarning').datagrid("reload");
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
							$('#contractWarning').datagrid({
								url: '${vix}/contract/contractInquiryAction!getContractWarningJson.action?id=${contractInquiry.id}',
								title: '合同预警明细',
								width: 'auto',
								height: '450',
								rownumbers : true,
								idField : 'id',
								fitColumns: true,
								columns:[[
									{field:'warnningContent',title:'预警信息',width:200,align:'center'},
									{field:'warnningTime',title:'预警时间',width:200,align:'center',formatter:formatDatebox},
									{field:'warnningType',title:'预警类型',width:200,align:'center'},
								]],
								toolbar:[{
									id:'da2Btnadd',
									text:'添加',
									iconCls:'icon-add',
									handler:function(){
										$('#btnsave').linkbutton('enable');
										saveContractWarning('${vix}/contract/contractInquiryAction!goAddContractWarning.action?id=0');
									}
								},'-',{
									id:'btnedit',
									text:'修改',
									iconCls: 'icon-edit',
									handler: function(){
										var row = $('#contractWarning').datagrid("getSelected");	//获取你选择的所有行
										if(row){
											saveContractWarning('${vix}/contract/contractInquiryAction!goAddContractWarning.action?id='+row.id)
										}
									}
								},'-',{
									text:'删除',
									iconCls:'icon-remove',
									handler:function(){
										var rows = $('#contractWarning').datagrid("getSelections");	//获取你选择的所有行	
										//循环所选的行
										for(var i =0;i<rows.length;i++){
											var index = $('#contractWarning').datagrid('getRowIndex',rows[i]);//获取某行的行号
											$('#contractWarning').datagrid('deleteRow',index);	//通过行号移除该行
											$.ajax({url:'${vix}/contract/contractInquiryAction!deleteContractWarningById.action?id='+rows[i].id,cache: false});
										}
									}
								}]
							});
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
						<div style="padding: 8px;">
							<table id="contractWarning"></table>
						</div>
					</div>

					<div id="a4" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
						function saveApplicationForm(url){
							$.ajax({
								  url:url,
								  cache: false,
								  success: function(html){
									  asyncbox.open({
										  	title:'合同审批明细 ',
										 	modal:true,
											width : 724,
											height :340,
											html:html,
											callback : function(action,returnValue){
												if(action == 'ok'){
													if($('#applicationFormss').validationEngine('validate')){
														$.post('${vix}/contract/contractInquiryAction!saveOrUpdateApplicationForm.action',
																{'id':$("#id").val(),
																  'applications.id':$("#daId").val(),
																  'applications.underTakePerson':$("#underTakePerson").val(),
																  'applications.underTakeDate':$("#underTakeDate").val(),
																},
																function(result){
																	showMessage(result);
																	setTimeout("",1000);
																	$('#applicationForm').datagrid("reload");
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
						$('#applicationForm').datagrid({
							url: '${vix}/contract/contractInquiryAction!getApplicationFormJson.action?id=${contractInquiry.id}',
							title: '合同审批明细',
							width: 'auto',
							height: '450',
							rownumbers : true,
							idField : 'id',
							fitColumns: true,
							columns:[[
								{field:'underTakePerson',title:'审批人',width:200,align:'center'},
								{field:'underTakeDate',title:'审批时间',width:200,align:'center',formatter:formatDatebox},
							]],
							toolbar:[{
								id:'da2Btnadd',
								text:'添加',
								iconCls:'icon-add',
								handler:function(){
									$('#btnsave').linkbutton('enable');
									saveApplicationForm('${vix}/contract/contractInquiryAction!goAddApplicationForm.action?id=0');
								}
							},'-',{
								id:'btnedit',
								text:'修改',
								iconCls: 'icon-edit',
								handler: function(){
									var row = $('#applicationForm').datagrid("getSelected");	//获取你选择的所有行
									if(row){
										saveApplicationForm('${vix}/contract/contractInquiryAction!goAddApplicationForm.action?id='+row.id)
									}
								}
							},'-',{
								text:'删除',
								iconCls:'icon-remove',
								handler:function(){
									var rows = $('#applicationForm').datagrid("getSelections");	//获取你选择的所有行	
									//循环所选的行
									for(var i =0;i<rows.length;i++){
										var index = $('#applicationForm').datagrid('getRowIndex',rows[i]);//获取某行的行号
										$('#applicationForm').datagrid('deleteRow',index);	//通过行号移除该行
										$.ajax({url:'${vix}/contract/contractInquiryAction!deleteApplicationFormById.action?id='+rows[i].id,cache: false});
									}
								}
							}]
						});
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
						<div style="padding: 8px;">
							<table id="applicationForm"></table>
						</div>
					</div>

					<div id="a5" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
						function savePriceConditions(url){
							$.ajax({
								  url:url,
								  cache: false,
								  success: function(html){
									  asyncbox.open({
										  	title:'价格条件 ',
										 	modal:true,
											width : 724,
											height :340,
											html:html,
											callback : function(action,returnValue){
												if(action == 'ok'){
													if($('#priceConditionsForm').validationEngine('validate')){
														$.post('${vix}/contract/contractInquiryAction!saveOrUpdatePriceConditions.action',
																{'id':$("#id").val(),
																  'price.id':$("#daId").val(),
																  'price.startQuantity':$("#startQuantity").val(),
																  'price.cutoffQuantity':$("#cutoffQuantity").val(),
																  'price.discount':$("#discount").val(),
																  'price.numberFrom':$("#numberFrom").val(),
																  'price.numberto':$("#numberto").val(),
																  'price.purchasepriceCondition':$("#purchasepriceCondition").val(),
																},
																function(result){
																	showMessage(result);
																	setTimeout("",1000);
																	$('#priceConditions').datagrid("reload");
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
						$('#priceConditions').datagrid({
							url: '${vix}/contract/contractInquiryAction!getPriceConditionsJson.action?id=${contractInquiry.id}',
							title: '价格条件明细',
							width: 'auto',
							height: '450',
							rownumbers : true,
							idField : 'id',
							fitColumns: true,
							columns:[[
								{field:'startQuantity',title:'开始数量',width:200,align:'center'},
								{field:'cutoffQuantity',title:'截止数量',width:200,align:'center'},
								{field:'discount',title:'折扣',width:200,align:'center'},
								{field:'numberFrom',title:'开始数量',width:200,align:'center'},
								{field:'numberto',title:'结束数量',width:200,align:'center'},
								{field:'purchasepriceCondition',title:'采购价格条件',width:200,align:'center'},
							]],
							toolbar:[{
								id:'da2Btnadd',
								text:'添加',
								iconCls:'icon-add',
								handler:function(){
									$('#btnsave').linkbutton('enable');
									savePriceConditions('${vix}/contract/contractInquiryAction!goAddPriceConditions.action?id=0');
								}
							},'-',{
								id:'btnedit',
								text:'修改',
								iconCls: 'icon-edit',
								handler: function(){
									var row = $('#priceConditions').datagrid("getSelected");	//获取你选择的所有行
									if(row){
										savePriceConditions('${vix}/contract/contractInquiryAction!goAddPriceConditions.action?id='+row.id)
									}
								}
							},'-',{
								text:'删除',
								iconCls:'icon-remove',
								handler:function(){
									var rows = $('#priceConditions').datagrid("getSelections");	//获取你选择的所有行	
									//循环所选的行
									for(var i =0;i<rows.length;i++){
										var index = $('#priceConditions').datagrid('getRowIndex',rows[i]);//获取某行的行号
										$('#priceConditions').datagrid('deleteRow',index);	//通过行号移除该行
										$.ajax({url:'${vix}/contract/contractInquiryAction!deletePriceConditionsById.action?id='+rows[i].id,cache: false});
									}
								}
							}]
						});
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
						<div style="padding: 8px;">
							<table id="priceConditions"></table>
						</div>
					</div>

					<div id="a6" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
						$('#soAttach').datagrid({
							url: '${vix}/contract/contractInquiryAction!getHrAttachmentsJson.action?id=${contractInquiry.id}',
							title: '附件',
							width: 'auto',
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
										  url:'${vix}/contract/contractInquiryAction!addHrAttachments.action',
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
															uploadFile('${vix}/contract/contractInquiryAction!uploadHrAttachments.action?id=${contractInquiry.id}','fileToUpload');
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
										$.ajax({url:'${vix}/contract/contractInquiryAction!deleteHrAttachments.action?afId='+rows[i].id,cache: false});
									}
								}
							}]
						});
					</script>
						<div style="padding: 8px;">
							<table id="soAttach"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>