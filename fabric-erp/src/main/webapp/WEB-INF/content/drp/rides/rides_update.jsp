<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateRides() {
		if ($('#ridesForm').validationEngine('validate')) {
			$.post('${vix}/drp/ridesAction!saveOrUpdate.action', {
			'rides.id' : $("#id").val(),
			'rides.channelDistributor.id' : $("#channelDistributorId").val(),
			'rides.parentid' : $("#parentid").val(),
			'rides.macid' : $("#macid").val(),
			'rides.macname' : $("#macname").val(),
			'rides.model' : $("#model").val(),
			'rides.picture' : $("#picture").val(),
			'rides.status' : $("#status").val(),
			'rides.producer' : $("#producer").val(),
			'rides.manufactureDate' : $("#manufactureDate").val(),
			'updateField' : updateField,
			'rides.description' : $("#description").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/ridesAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#ridesForm").validationEngine();
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
					} else {
						$("#channelDistributorId").val("");
						$("#channelDistributorName").val("");
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
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">连锁经营管理 </a></li>
				<li><a href="#">门店管理</a></li>
				<li><a href="#">游乐设施管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${rides.id}" />
<div class="content">
	<form id="ridesForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateRides();" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"
						onclick="loadContent('${vix}/drp/ridesAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>游乐设备信息</b> </strong>
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
											<td width="15%" align="right">编码：</td>
											<td width="35%"><input id="parentid" name="parentid" value="${rides.parentid }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td width="15%" align="right">机器编码：</td>
											<td width="35%"><input id="macid" name="macid" value="${rides.macid }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">名称：</td>
											<td><input id="macname" name="macname" value="${rides.macname }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">门店：</td>
											<td><input id="channelDistributorName" name="channelDistributorName" value="${rides.channelDistributor.name }" type="text" onchange="fieldChanged(this);" /><input type="hidden" id="channelDistributorId" name="channelDistributorId" value="${rides.channelDistributor.id}" /> <input class="btn" type="button" value="选择"
												onclick="chooseParentOrganization();" /></td>
										</tr>
										<tr>
											<td align="right">机种：</td>
											<td><input id="model" name="model" value="${rides.model }" type="text" size="30" onchange="fieldChanged(this);"></td>
											<td align="right">图片：</td>
											<td><input id="picture" name="picture" value="${rides.picture }" type="text" size="30" onchange="fieldChanged(this);"></td>
										</tr>
										<tr>
											<td align="right">厂家：</td>
											<td><input id="producer" name="producer" value="${rides.producer }" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">出厂日期：</td>
											<td><input id="manufactureDate" name="manufactureDate" onchange="fieldChanged(this);" value="<fmt:formatDate value='${rides.manufactureDate }' type='both' pattern='yyyy-MM-dd' />" type="text" /> <img onclick="showTime('manufactureDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">状态：</td>
											<td><input id="status" name="status" value="${rides.status }" type="text" size="30" onchange="fieldChanged(this);"></td>
											<td align="right">描述：</td>
											<td><input id="description" name="description" value="${rides.description }" type="text" size="30" onchange="fieldChanged(this);" /></td>
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