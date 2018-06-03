<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script type="text/javascript">
	function saveOrUpdateMemberShipCard() {
		if ($('#memberShipCardForm').validationEngine('validate')) {
			$.post('${vix}/drp/membershipCardmanagementAction!batchSaveOrUpdate.action', {
			'memberShipCard.id' : $("#id").val(),
			'memberShipCard.customerAccount.id' : $("#customerAccountId").val(),
			'memberShipCard.channelDistributor.id' : $("#channelDistributorId").val(),
			'memberShipCard.channelDistributor.code' : $("#channelDistributorCode").val(),
			'memberShipCard.vipcardid' : $("#vipcardid").val(),
			'cardCount' : $("#cardCount").val(),
			'memberShipCard.opencarddate' : $("#opencarddate").val(),
			'memberShipCard.viptypeid' : $("#viptypeid").val(),
			'memberShipCard.balance_amount' : $("#balance_amount").val(),
			'memberShipCard.isstartuse' : $("#isstartuse").val(),
			'memberShipCard.start_date' : $("#start_date").val(),
			'memberShipCard.password' : $("#password").val(),
			'memberShipCard.checkNo' : $("#checkNo").val(),
			'memberShipCard.isstop' : $(":radio[name=isstop][checked]").val(),
			'memberShipCard.isdestruct' : $(":radio[name=isdestruct][checked]").val(),
			'memberShipCard.remark' : remark.html()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/membershipCardmanagementAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#memberShipCardForm").validationEngine();

	function chooseCustomerAccount() {
		$.ajax({
		url : '${vix}/drp/membershipCardmanagementAction!goChooseCustomerAccount.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 650,
			height : 450,
			title : "选择会员",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var rv = returnValue.split(",");
						$("#customerAccountId").val(rv[0]);
						$("#customerAccountName").val(rv[1]);
					} else {
						asyncbox.success("请选择会员信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}

	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/drp/distributionSystemRelationShipAction!goChooseOrganization.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择父公司",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#channelDistributorId").val(result[0]);
						$("#channelDistributorName").val(result[1]);
						$("#channelDistributorCode").val(result[3]);
					} else {
						$("#channelDistributorId").val("");
						$("#channelDistributorName").val("");
						$("#channelDistributorCode").val("");
						asyncbox.success("请选择公司信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_distribution_management.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#"><s:text name="cbm_membership_management" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/membershipCardmanagementAction!goList.action');"><s:text name="drp_membership_card_management" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${memberShipCard.id}" />
<div class="content">
	<form id="memberShipCardForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateMemberShipCard()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"
						onclick="loadContent('${vix}/drp/membershipCardmanagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>新增</b> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b><strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">会员姓名：</td>
											<td><input id="customerAccountName" name="customerAccountName" value="${memberShipCard.customerAccount.name }" type="text" class="validate[required] text-input" size="30" /><input type="hidden" id="customerAccountId" name="customerAccountId" value="${memberShipCard.customerAccount.id}" /> <input class="btn" type="button"
												value="选择" onclick="chooseCustomerAccount();" /><span style="color: red;">*</span></td>
											<td align="right">所属门店：</td>
											<td><input id="channelDistributorName" name="channelDistributorName" value="${memberShipCard.channelDistributor.name }" type="text" class="validate[required] text-input" size="30" /><input type="hidden" id="channelDistributorId" name="channelDistributorId" value="${memberShipCard.channelDistributor.id}" /> <input type="hidden"
												id="channelDistributorCode" name="channelDistributorCode" /><input class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">开卡张数：</td>
											<td><input id="cardCount" name="cardCount" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">发卡日期：</td>
											<td><input id="opencarddate" name="opencarddate" value="${memberShipCard.opencarddate}" type="text" size="30" readonly="readonly" /><img onclick="showTime('opencarddate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">会员卡类型：</td>
											<td><s:select list="cardTypeList" listKey="vipTypeId" listValue="vipTypeName" theme="simple" id="viptypeid" name="viptypeid"></s:select></td>
											<td align="right">余额：</td>
											<td><input id="balance_amount" name="balance_amount" value="${memberShipCard.balance_amount }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">是否启用：</td>
											<td><s:if test="memberShipCard.isstop == 1">
													<input type="radio" id="isstop1" name="isstop" value="2" />是 <input type="radio" id="isstop2" name="isstop" value="1" checked="checked" />否
											</s:if> <s:elseif test="memberShipCard.isstop == 2">
													<input type="radio" id="isstop1" name="isstop" value="2" checked="checked" />是 <input type="radio" id="isstop2" name="isstop" value="1" />否
											</s:elseif> <s:else>
													<input type="radio" id="isstop1" name="isstop" value="2" />是 <input type="radio" id="isstop2" name="isstop" value="1" checked="checked" />否
											</s:else></td>
											<td align="right">启用日期：</td>
											<td><input id="start_date" name="start_date" value="${memberShipCard.start_date}" type="text" readonly="readonly" size="30" /><img onclick="showTime('start_date','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">验证码(手机后4位)：</td>
											<td><input id="checkNo" name="checkNo" value="${memberShipCard.checkNo }" type="password" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">是否销卡：</td>
											<td><s:if test="memberShipCard.isdestruct == 1">
													<input type="radio" id="isdestruct1" name="isdestruct" value="1" checked="checked" />是 <input type="radio" id="isdestruct2" name="isdestruct" value="2" />否
											</s:if> <s:elseif test="memberShipCard.isdestruct == 2">
													<input type="radio" id="isdestruct1" name="isdestruct" value="1" />是 <input type="radio" id="isdestruct2" name="isdestruct" value="2" checked="checked" />否
											</s:elseif> <s:else>
													<input type="radio" id="isdestruct1" name="isdestruct" value="1" />是 <input type="radio" id="isdestruct2" name="isdestruct" value="2" checked="checked" />否
											</s:else></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td colspan="3"><textarea id="remark" name="remark">${memberShipCard.remark }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
												var remark = KindEditor.create('textarea[name="remark"]', {
												basePath : '${vix}/plugin/KindEditor/',
												width : 685,
												height : 200,
												cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
												uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
												fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
												allowFileManager : true
												});
											</script></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>