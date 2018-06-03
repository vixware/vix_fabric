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

//页面首次加载
$(function(){
	$("#currency").val('${contractInquiry.currency}');
});

$(function(){ $("select").attr("disabled", "disabled");  
//如果和jquery1.6以上版本，可以使用以下语句：  
$("select").prop("disabled", true);}); 
 
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
				<li><a href="#" onclick="loadContent('${vix}/contract/contractApprovalAction!goList.action?pageNo=${pageNo}');"><s:text name="ctm_agreement" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractApprovalAction!goList.action?pageNo=${pageNo}');"><s:text name="ctm_contract_inquiries" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractApprovalAction!goList.action?pageNo=${pageNo}');"><s:text name="查看销售合同" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<form id="replanForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a href="#" onclick="loadContent('${vix}/contract/contractApprovalAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="查看销售合同" /> </b><i></i> </strong>
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
											<td><input id="masterContractCoding" name="masterContractCoding" readonly="value" value="${contractInquiry.masterContractCoding }" type="text" size="30"</td>
											<th align="right">所属合同组：</th>
											<td><input id="contractInquiry.contractGroupType.name" name="contractInquiry.contractGroupType.name" readonly="value" value="${contractInquiry.contractGroupType.name }" type="text" size="30"</td>
										</tr>
										<tr>
											<th align="right">合同编码：</th>
											<td><input id="contractCode" name="contractCode" readonly="value" value="${contractInquiry.contractCode}" type="text" size="30" /></td>
											<th align="right">合同名称：</th>
											<td><input id="contractName" name="contractName" readonly="value" value="${contractInquiry.contractName}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">项目代码：</th>
											<td><input id="projectCode" name="projectCode" readonly="value" value="${contractInquiry.projectCode}" type="text" size="30" /></td>
											<th align="right">项目名称：</th>
											<td><input id="projectName" name="projectName" readonly="value" value="${contractInquiry.projectName}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">类型：</td>
											<td><input id="contractInquiry.contractTypeCombine.name" name="contractInquiry.contractTypeCombine.name" readonly="value" value="${contractInquiry.contractTypeCombine.name}" type="text" size="30" /></td>
											<th align="right">合同性质：</th>
											<td><input id="contractInquiry.contractNatureType.name" name="contractInquiry.contractNatureType.name" readonly="value" value="${contractInquiry.contractNatureType.name}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">所属行业：</th>
											<td><input id="contractInquiry.viewIndustryType.name" name="contractInquiry.viewIndustryType.name" readonly="value" value="${contractInquiry.viewIndustryType.name}" type="text" size="30" /></td>
											<th align="right">合同签订日期：</th>
											<td><input id="signDate" name="signDate" value="<fmt:formatDate value='${contractInquiry.signDate }' type='both' pattern='yyyy-MM-dd' />" readonly="value" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">合同开始日期：</th>
											<td><input id="contractStartTime" name="contractStartTime" value="<fmt:formatDate value='${contractInquiry.contractStartTime }' type='both' pattern='yyyy-MM-dd' />" readonly="value" type="text" size="30" /></td>
											<th align="right">合同终止日期：</th>
											<td><input id="contractEndTime" name="contractEndTime" value="<fmt:formatDate value='${contractInquiry.contractEndTime }' type='both' pattern='yyyy-MM-dd' />" readonly="value" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">经办人：</th>
											<td><input id="operator" name="operator" readonly="value" value="${contractInquiry.operator}" type="text" size="30" /></td>
											<th align="right">谈判人：</th>
											<td><input id="negotiator" name="negotiator" readonly="value" value="${contractInquiry.negotiator}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">审批人：</th>
											<td><input id="approval" name="approval" readonly="value" value="${contractInquiry.approval}" type="text" size="30" /></td>
											<th align="right">审批日期：</th>
											<td><input id="approvalDate" name="approvalDate" value="<fmt:formatDate value='${contractInquiry.approvalDate }' type='both' pattern='yyyy-MM-dd' />" readonly="value" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">合同履行状态：</th>
											<td><input id="contractInquiry.modeType.name" name="contractInquiry.modeType.name" readonly="value" value="${contractInquiry.modeType.name}" type="text" size="30" />
											<th align="right">所属部门：</th>
											<td><input id="departmentName" " name="departmentName" readonly="value" value="${contractInquiry.departmentName}" type="text" size="30" />
										</tr>
										<tr>
											<td align="right">合同总数量：</td>
											<td><input id="contractTotalQuantity" name="contractTotalQuantity" readonly="value" value="${contractInquiry.contractTotalQuantity}" type="text" size="30" /></td>
											<td align="right">合同执行数量：</td>
											<td><input id="numberContractExecution" name="numberContractExecution" readonly="value" value="${contractInquiry.numberContractExecution}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">启用阶段：</th>
											<td><input id="contractInquiry.enableStageType.name" name="contractInquiry.enableStageType.name" readonly="value" value="${contractInquiry.enableStageType.name}" type="text" size="30" /></td>
											<th align="right">合同阶段组：</th>
											<td><input id="contractInquiry.contractStageGroupType.name" name="contractInquiry.contractStageGroupType.name" readonly="value" value="${contractInquiry.contractStageGroupType.name}" type="text" size="30" /></td>
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
											<td><input id="firstParty" name="firstParty" readonly="value" value="${contractInquiry.firstParty}" type="text" size="30" /></td>
											<td align="right">乙方：</td>
											<td><input id="secondParty" name="secondParty" readonly="value" value="${contractInquiry.secondParty}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">甲方单位名称：</th>
											<td><input id="partyUnitName" name="partyUnitName" readonly="value" value="${contractInquiry.partyUnitName}" type="text" size="30" /></td>
											<th align="right">乙方单位名称：</th>
											<td><input id="bUnitName" name="bUnitName" readonly="value" value="${contractInquiry.bunitName}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">甲方单位地址：</th>
											<td><input id="firstPartyAddress" name="firstPartyAddress" readonly="value" value="${contractInquiry.firstPartyAddress}" type="text" size="30" /></td>
											<td align="right">乙方单位地址：</td>
											<td><input id="bAddress" name="bAddress" readonly="value" value="${contractInquiry.baddress}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">甲方联系人：</th>
											<td><input id="firstPartyContact" name="firstPartyContact" readonly="value" value="${contractInquiry.firstPartyContact}" type="text" size="30" /></td>
											<th align="right">乙方联系人：</th>
											<td><input id="bContact" name="bContact" readonly="value" value="${contractInquiry.bcontact}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">甲方电话：</th>
											<td><input id="firstPartyPhone" name="firstPartyPhone" readonly="value" value="${contractInquiry.firstPartyPhone}" type="text" size="30" /></td>
											<th align="right">乙方电话：</th>
											<td><input id="bPhone" name="bPhone" readonly="value" value="${contractInquiry.bphone}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">甲方邮件：</th>
											<td><input id="firstPartyEmail" name="firstPartyEmail" readonly="value" value="${contractInquiry.firstPartyEmail}" type="text" size="30" /></td>
											<th align="right">乙方邮件：</th>
											<td><input id="email" name="email" readonly="value" value="${contractInquiry.email}" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>

						<div class="voucher newvoucher">
							<dl>
								<dt>
									<strong>金额</strong>
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
											<td><input class="easyui-numberbox" id="outstandingAmounts" name="outstandingAmounts" readonly="value" value="${contractInquiry.outstandingAmounts}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">质保金计算方式：</td>
											<td><input id="retentionCalculation" name="retentionCalculation" readonly="value" value="${contractInquiry.retentionCalculation}" type="text" size="30" /></td>
											<th align="right">质保金开始日期：</th>
											<td><input id="retentionsStartDate" name="retentionsStartDate" value="<fmt:formatDate value='${contractInquiry.retentionsStartDate }' type='both' pattern='yyyy-MM-dd' />" readonly="value" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">质保金结束日期：</th>
											<td><input id="retentionEndDate" name="retentionEndDate" value="<fmt:formatDate value='${contractInquiry.retentionEndDate }' type='both' pattern='yyyy-MM-dd' />" readonly="value" type="text" size="30" /></td>
											<td align="right">质保金比例（%）：</td>
											<td><input id="retentionRatio" name="retentionRatio" readonly="value" value="${contractInquiry.retentionRatio}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">质保金额度：</td>
											<td><input class="easyui-numberbox" id="warrantyAmount" name="warrantyAmount" readonly="value" value="${contractInquiry.warrantyAmount}" type="text" size="30" /></td>
											<td align="right">保修期：</td>
											<td><input id="warranty" name="warranty" readonly="value" value="${contractInquiry.warranty}" type="text" size="30" /></td>
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
											<td colspan="3"><textarea id="mainContent" readonly="readonly" name="mainContent" style="width: 950px">${contractInquiry.mainContent}</textarea>
										</tr>
										<tr>
											<th align="right">备注：</th>
											<td colspan="3"><textarea id="remark" readonly="readonly" name="remark" class="example" rows="1" style="width: 950px">${contractInquiry.remark}</textarea></td>
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