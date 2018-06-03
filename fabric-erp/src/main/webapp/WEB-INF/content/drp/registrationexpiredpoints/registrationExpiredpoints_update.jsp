<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateExpiredIntegral() {
		if ($('#expiredIntegralForm').validationEngine('validate')) {
			$.post('${vix}/drp/expiredIntegralAction!saveOrUpdate.action', {
			'expiredIntegral.id' : $("#id").val(),
			'expiredIntegral.memberShipCard.id' : $("#memberShipCardId").val(),
			'expiredIntegral.expiredIntegral' : $("#expiredIntegral").val(),
			'updateField' : updateField,
			'expiredIntegral.expiryDate' : $("#expiryDate").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/expiredIntegralAction!goList.action');
			});
		}
	}
	function chooseMemberShipCard() {
		$.ajax({
		url : '${vix}/drp/expiredIntegralAction!goChooseMemberShipCard.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "选择会员卡",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var rv = returnValue.split(",");
						$("#memberShipCardId").val(rv[0]);
						$("#vipcardid").val(rv[2]);
					} else {
						asyncbox.success("请选择分类信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	$("#expiredIntegralForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">会员管理 </a></li>
				<li><a href="#">会员积分信息 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/expiredIntegralAction!goList.action');">过期积分记录</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${expiredIntegral.id }" />
<div class="content">
	<form id="expiredIntegralForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateExpiredIntegral()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/expiredIntegralAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>过期积分记录</b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">会员卡号：</td>
											<td><input id="vipcardid" name="vipcardid" value="${expiredIntegral.memberShipCard.vipcardid }" onchange="fieldChanged(this);" type="text" class="validate[required] text-input" /><input type="hidden" id="memberShipCardId" name="memberShipCardId" value="${expiredIntegral.memberShipCard.id}" /> <input class="btn" type="button"
												value="选择" onclick="chooseMemberShipCard();" /> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">过期积分：</td>
											<td><input id="expiredIntegral" name="expiredIntegral" value="${expiredIntegral.expiredIntegral }" onchange="fieldChanged(this);" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">过期时间：</td>
											<td><input id="expiryDate" name="expiryDate" value="<fmt:formatDate value='${expiredIntegral.expiryDate }' type='both' pattern='yyyy-MM-dd' />" onchange="fieldChanged(this);" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('expiryDate','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>