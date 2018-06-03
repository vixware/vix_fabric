<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}

	function saveOrUpdateCustomerFeedback() {
		if ($('#customerFeedbackForm').validationEngine('validate')) {
			$.post('${vix}/drp/customerFeedbackmanagementAction!saveOrUpdate.action', {
			'customerFeedback.id' : $("#id").val(),
			'customerFeedback.customerName' : $("#customerName").val(),
			'customerFeedback.feedbackContent' : editor.html(),
			'customerFeedback.processResult' : processResult.html(),
			'customerFeedback.feedbackTime' : $("#feedbackTime").val(),
			'customerFeedback.processPerson' : $("#processPerson").val(),
			'updateField' : updateField,
			'customerFeedback.processTime' : $("#processTime").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/customerFeedbackmanagementAction!goList.action');
			});
		}
	}
	$("#customerFeedbackForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#"><s:text name="drp_customer_serviceandcare" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/customerFeedbackmanagementAction!goList.action');"><s:text name="drp_customer_feedback_management" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${customerFeedback.id }" />
<div class="content">
	<form id="customerFeedbackForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateCustomerFeedback()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/drp/customerFeedbackmanagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>反馈信息</b></strong>
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
										<tr height="40">
											<td align="right">客户姓名：</td>
											<td><input id="customerName" name="customerName" value="${customerFeedback.customerName }" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">反馈时间：</td>
											<td><input id="feedbackTime" name="feedbackTime" value="<fmt:formatDate value='${customerFeedback.feedbackTime }' type='both' pattern='yyyy-MM-dd' />" type="text" onchange="fieldChanged(this);" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('feedbackTime','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr height="40">
											<td align="right">处理人：</td>
											<td><input id="processPerson" name="processPerson" value="${customerFeedback.processPerson }" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">处理时间：</td>
											<td><input id="processTime" name="processTime" value="<fmt:formatDate value='${customerFeedback.processTime }' type='both' pattern='yyyy-MM-dd' />" type="text" onchange="fieldChanged(this);" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('processTime','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr height="40">
											<td align="right">反馈内容：</td>
											<td colspan="3"><textarea id="feedbackContent" name="feedbackContent" onchange="fieldChanged(this);">${customerFeedback.feedbackContent }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
												var editor = KindEditor.create('textarea[name="feedbackContent"]', {
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
										<tr height="40">
											<td align="right">处理结果：</td>
											<td colspan="3"><textarea id="processResult" name="processResult" onchange="fieldChanged(this);">${customerFeedback.processResult }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
												var processResult = KindEditor.create('textarea[name="processResult"]', {
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