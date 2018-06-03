<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function initdate(){
		$("#pointType").val(${zoVipCardLog.pointType });
	    //设置单据类型选中(修改)
	    if (document.getElementById("createTime").value == "") {
		    var myDate = new Date();
		    $("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
	    }
	}
	initdate();
    function saveOrUpdateZoVipCardLog() {
	    if ($('#zoVipCardLogForm').validationEngine('validate')) {
		    $.post('${vix}/drp/membershipPointsregistrationAction!saveOrUpdate.action', {
		    'zoVipCardLog.id' : $("#id").val(),
		    'zoVipCardLog.memberShipCard.id' : $("#memberShipCardId").val(),
		    'zoVipCardLog.sumtopay' : $("#sumtopay").val(),
		    'zoVipCardLog.sumtorealpay' : $("#sumtorealpay").val(),
		    'zoVipCardLog.pointType' : $("#pointType").val(),
		    'zoVipCardLog.pointValue' : $("#pointValue").val(),
		    'updateField' : updateField,
		    'zoVipCardLog.createTime' : $("#createTime").val()
		    }, function(result) {
			    showMessage(result);
			    setTimeout("", 1000);
			    loadContent('${vix}/drp/membershipPointsregistrationAction!goList.action');
		    });
	    }
    }
    $("#zoVipCardLogForm").validationEngine();
    function chooseMemberShipCard() {
	    $.ajax({
	    url : '${vix}/drp/membershipPointsregistrationAction!goChooseMemberShipCard.action',
	    cache : false,
	    success : function(html) {
		    asyncbox.open({
		    modal : true,
		    width : 750,
		    height : 350,
		    title : "选择会员卡",
		    html : html,
		    callback : function(action,returnValue) {
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">会员管理 </a></li>
				<li><a href="#">会员积分信息 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/membershipPointsregistrationAction!goList.action');">会员积分记录</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${zoVipCardLog.id }" />
<div class="content">
	<form id="zoVipCardLogForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateZoVipCardLog()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"
						onclick="loadContent('${vix}/drp/membershipPointsregistrationAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>积分记录</b> </strong>
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
											<td width="15%" align="right">会员卡号：</td>
											<td width="35%"><input id="vipcardid" name="vipcardid" value="${zoVipCardLog.memberShipCard.vipcardid }" type="text" onchange="fieldChanged(this);" class="validate[required] text-input" size="30" /><input type="hidden" id="memberShipCardId" name="memberShipCardId" value="${zoVipCardLog.memberShipCard.id}" /> <input class="btn"
												type="button" value="选择" onclick="chooseMemberShipCard();" /> <span style="color: red;">*</span></td>
											<td width="15%" align="right">消费时间：</td>
											<td width="35%"><input id="createTime" name="createTime" value="<fmt:formatDate value='${zoVipCardLog.createTime }' type='both' pattern='yyyy-MM-dd HH:mm:ss' />" onchange="fieldChanged(this);" type="text" class="validate[required] text-input" size="30" /><span style="color: red;">*</span> <img
												onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">应收金额：</td>
											<td><input id="sumtopay" name="sumtopay" value="${zoVipCardLog.sumtopay }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">实收金额：</td>
											<td><input id="sumtorealpay" name="sumtorealpay" value="${zoVipCardLog.sumtorealpay }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">积分类型：</td>
											<td><select id="pointType" name="pointType" style="width: 50" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1">增加</option>
													<option value="2">减少</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">积分：</td>
											<td><input id="pointValue" name="pointValue" value="${zoVipCardLog.pointValue }" type="text" size="30" onchange="fieldChanged(this);" /></td>
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