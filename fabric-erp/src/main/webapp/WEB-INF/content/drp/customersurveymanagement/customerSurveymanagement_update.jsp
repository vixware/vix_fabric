<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateCustomerSurvey() {
		if ($('#customerSurveyForm').validationEngine('validate')) {
			$.post('${vix}/drp/customerSurveyAction!saveOrUpdate.action', {
			'customerSurvey.id' : $("#id").val(),
			'customerSurvey.name' : $("#name").val(),
			'customerSurvey.surveyObject' : $("#surveyObject").val(),
			'customerSurvey.surveyPerson' : $("#surveyPerson").val(),
			'customerSurvey.surveyDate' : $("#surveyDate").val(),
			'updateField' : updateField,
			'customerSurvey.surveyContent' : surveyContent.html()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/customerSurveyAction!goList.action');
			});
		}
	}
	$("#customerSurveyForm").validationEngine();
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
				<li><a href="#" onclick="loadContent('${vix}/drp/customerSurveyAction!goList.action');"> <s:text name="drp_customer_survey_management" />
				</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${customerSurvey.id }" />
<div class="content">
	<form id="customerSurveyForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateCustomerSurvey()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/drp/customerSurveyAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>客户调查信息</b></strong>
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
											<td><input id="name" name="name" value="${customerSurvey.name }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">调查对象：</td>
											<td><input id="surveyObject" name="surveyObject" value="${customerSurvey.surveyObject }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">调查人：</td>
											<td><input id="surveyPerson" name="surveyPerson" value="${customerSurvey.surveyPerson }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">调查时间：</td>
											<td><input id="surveyDate" name="surveyDate" value="<fmt:formatDate value='${customerSurvey.surveyDate }' type='both' pattern='yyyy-MM-dd' />" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span> <img onclick="showTime('surveyDate','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr height="40">
											<td align="right">调查内容：</td>
											<td colspan="3"><textarea id="surveyContent" name="surveyContent" onchange="fieldChanged(this);">${customerSurvey.surveyContent }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
												var surveyContent = KindEditor.create('textarea[name="surveyContent"]', {
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