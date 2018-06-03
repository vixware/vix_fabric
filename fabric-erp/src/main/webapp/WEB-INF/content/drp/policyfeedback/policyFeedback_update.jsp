<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdatePolicyInformation() {
		if ($('#policyInformationForm').validationEngine('validate')) {
			$.post('${vix}/drp/policyFeedbackAction!saveOrUpdate.action', {
			'policyInformation.id' : $("#id").val(),
			'policyInformation.name' : $("#name").val(),
			'policyInformation.type' : $("#type").val(),
			'policyInformation.departmentCode' : $("#departmentCode").val(),
			'policyInformation.createTime' : $("#createTime").val(),
			'policyInformation.priority' : $("#priority").val(),
			'updateField' : updateField,
			'policyInformation.policyContent' : policyContent.html()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/policyFeedbackAction!goList.action');
			});
		}
	}
	$("#policyInformationForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#"><s:text name="drp_store_management" /> </a></li>
				<li><a href="#">政策下达与反馈 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/policyFeedbackAction!goList.action');">政策管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${policyInformation.id }" />
<div class="content">
	<form id="policyInformationForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdatePolicyInformation()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/policyFeedbackAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>政策信息 </b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">主题：</td>
											<td><input id="name" name="name" value="${policyInformation.name }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">类型：</td>
											<td><select id="type" name="type" style="width: 50" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1">惠民</option>
													<option value="2">民生</option>
											</select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">发布时间：</td>
											<td><input id="createTime" name="createTime" value="<fmt:formatDate value='${policyInformation.createTime }' type='both' pattern='yyyy-MM-dd' />" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span> <img onclick="showTime('createTime','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">优先级：</td>
											<td><input id="priority" name="priority" value="${policyInformation.priority }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">政策内容：</td>
											<td colspan="3"><textarea id="policyContent" name="policyContent" onchange="fieldChanged(this);">${policyInformation.policyContent }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
												var policyContent = KindEditor.create('textarea[name="policyContent"]', {
												basePath : '${vix}/plugin/KindEditor/',
												width : 830,
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
	</form>
</div>