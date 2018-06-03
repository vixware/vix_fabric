<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<link href="${vix}/common/js/vreport/vreport/css/vreport.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/js/vreport/jquery/css/jquery-ui-1.9.1.custom.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/vreport/vprint.js" type="text/javascript"></script>
<script src="${vix}/common/js/vreport/jquery/jquery-1.8.2.js" type="text/javascript"></script>
<script src="${vix}/common/js/vreport/jquery/jquery-ui-1.9.1.custom.min.js" type="text/javascript"></script>
<script src="${vix}/common/js/raphael-min.js" type="text/javascript"></script>
<script src="${vix}/common/js/vreport/flowtask.js" type="text/javascript"></script>

<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdate() {
		$.post('${vix}/crm/business/marketingActivitiesAction!saveOrUpdate.action', {
		'marketingActivities.id' : $("#id").val(),
		'marketingActivities.code' : $("#code").val(),
		'marketingActivities.name' : $("#name").val(),
		'marketingActivities.startTime' : $("#startTime").val(),
		'marketingActivities.endTime' : $("#endTime").val(),
		'marketingActivities.memo' : $("#memo").val(),
		'updateField' : updateField,
		'marketingActivities.activityFlow' : $("#activityFlow").val()
		}, function(result) {
			/* showMessage(result);
			setTimeout("", 1000); */
			loadContent('${vix}/crm/business/marketingProcessManagementAction!goList.action');
		});
	}
</script>

<style>
.client_selection span {
	display: inline-block;
	width: 90px;
}

.client_selection input {
	margin-top: 5px;
	height: 24px;
	width: 250px;
}

.client_selection select {
	margin-top: 5px;
	height: 24px;
	width: 250px;
}

.client_selection textarea {
	margin-top: 5px;
	width: 250px;
	height: 150px;
}
</style>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/money_26x26.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">会员交互管理 </a></li>
				<li><a href="#">会员管理 </a></li>
				<li><a href="#">智能营销 </a></li>
				<li><a href="#">营销流程管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${marketingActivities.id}" />
<input type="hidden" id="memberShipSubdivisionId" name="memberShipSubdivisionId" value="${memberShipSubdivisionId}" />
<div class="client_selection" id="client_selection" title="客户筛选" style="display: none; width: 450px;">
	<table style="width: 100%">
		<tr>
			<td align="right"><span>标题：</span></td>
			<td><input type="text" id="titleId-client_selection" /></td>
		</tr>
		<tr>
			<td align="right"><span>类型：</span></td>
			<td><select id="nodeType-client_selection" disabled="disabled">
					<option value="process">处理节点</option>
			</select></td>
		</tr>
		<tr>
			<td align="right"><span>图标：</span></td>
			<td><select id="nodeIcon-client_selection" disabled="disabled"><option value="human.png">人员</option></select></td>
		</tr>
		<tr>
			<td align="right"><span>操作：</span></td>
			<td><select id="nodeValue-client_selection">
					<option value="com.e6soft.vreport.processor.EmailProcessor">客户筛选</option>
			</select></td>
		</tr>
		<tr>
			<td align="right"><span>客户细分：</span></td>
			<td><s:select id="nodeParam-client_selection" list="%{membershipSubdivisionList}" listValue="name" listKey="id" value="" multiple="false" theme="simple">
				</s:select></td>
		</tr>
	</table>
</div>
<div class="client_selection" id="start_design" title="开始设计" style="display: none; width: 450px;">
	<table style="width: 100%">
		<tr>
			<td align="right"><span>标题：</span></td>
			<td><input type="text" id="titleId-start_design" /></td>
		</tr>
		<tr>
			<td align="right"><span>类型：</span></td>
			<td><select id="nodeType-start_design" disabled="disabled">
					<option value="start">开始节点</option>
			</select></td>
		</tr>
		<tr>
			<td align="right"><span>图标：</span></td>
			<td><select id="nodeIcon-start_design" disabled="disabled"><option value="start.png">开始</option></select></td>
		</tr>
		<tr>
			<td align="right"><span>操作：</span></td>
			<td><select id="nodeValue-start_design">
					<option value="quartz://report?cron=*/5 * * * * ?&stateful=true">立即执行</option>
			</select></td>
		</tr>
	</table>
</div>
<div class="client_selection" id="delay_time" title="延迟时间" style="display: none; width: 450px;">
	<table style="width: 100%">
		<tr>
			<td align="right"><span>标题：</span></td>
			<td><input type="text" id="titleId-delay_time" /></td>
		</tr>
		<tr>
			<td align="right"><span>类型：</span></td>
			<td><select id="nodeType-delay_time" disabled="disabled">
					<option value="delay">延时节点</option>
			</select></td>
		</tr>
		<tr>
			<td align="right"><span>图标：</span></td>
			<td><select id="nodeIcon-delay_time" disabled="disabled"><option value="timer.png">时间</option></select></td>
		</tr>
		<tr>
			<td align="right"><span>操作：</span></td>
			<td><select id="nodeValue-delay_time">
					<option value="10000">延迟时间</option>
			</select></td>
		</tr>
	</table>
</div>
<div class="client_selection" id="send_message" title="短信发送" style="display: none; width: 450px;">
	<table style="width: 100%">
		<tr>
			<td align="right"><span>标题：</span></td>
			<td><input type="text" id="titleId-send_message" /></td>
		</tr>
		<tr>
			<td align="right"><span>类型：</span></td>
			<td><select id="nodeType-send_message" disabled="disabled">
					<option value="process">短信发送</option>
			</select></td>
		</tr>
		<tr>
			<td align="right"><span>图标：</span></td>
			<td><select id="nodeIcon-send_message" disabled="disabled"><option value="email.png">邮件</option></select></td>
		</tr>
		<tr>
			<td align="right"><span>操作：</span></td>
			<td><select id="nodeValue-send_message">
					<option value="com.e6soft.vreport.processor.EmailProcessorc">短信发送</option>
			</select></td>
		</tr>
		<tr>
			<td align="right"><span>短信模板：</span></td>
			<td><s:select id="messageTemplateId" headerKey="" headerValue="--请选择--" list="%{messageTemplateList}" listValue="name" listKey="messageContent" value="" multiple="false" theme="simple" onchange="changeDisplay(this);">
				</s:select></td>
		</tr>
		<tr>
			<td align="right"><span>短信预览：</span></td>
			<td><textarea id="nodeExt-send_message"></textarea></td>
		</tr>
	</table>
</div>
<div class="client_selection" id="send_coupon" title="优惠券发放" style="display: none; width: 450px;">
	<table style="width: 100%">
		<tr>
			<td align="right"><span>标题：</span></td>
			<td><input type="text" id="titleId-send_coupon" /></td>
		</tr>
		<tr>
			<td align="right"><span>类型：</span></td>
			<td><select id="nodeType-send_coupon" disabled="disabled">
					<option value="process">优惠券发放</option>
			</select></td>
		</tr>
		<tr>
			<td align="right"><span>图标：</span></td>
			<td><select id="nodeIcon-send_coupon" disabled="disabled"><option value="money.png">优惠券</option></select></td>
		</tr>
		<tr>
			<td align="right"><span>操作：</span></td>
			<td><select id="nodeValue-send_coupon">
					<option value="com.e6soft.vreport.processor.EmailProcessorc">优惠券发放</option>
			</select></td>
		</tr>
		<tr>
			<td align="right"><span>优惠券：</span></td>
			<td><s:select id="messageTemplateId" headerKey="" headerValue="--请选择--" list="%{messageTemplateList}" listValue="name" listKey="messageContent" value="" multiple="false" theme="simple" onchange="changeDisplay(this);">
				</s:select></td>
		</tr>
	</table>
</div>
<div class="content">
	<!-- sub menu -->

	<div class="order">
		<dl>
			<dt id="orderTitleFixd">
				<span class="no_line"> <a onclick="saveOrUpdate()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
						src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/crm/business/marketingActivitiesAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
				</span> <strong><b>营销流程 </b> </strong>
			</dt>
			<dd style="display: block;" class="clearfix">
				<div class="order_table">
					<form id="marketingActivitiesForm">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>编码：</th>
											<td><input id="code" name="code" size="30" value="${marketingActivities.code }" type="text" onchange="fieldChanged(this);" /></td>
											<th>主题：</th>
											<td><input id="name" name="name" size="30" value="${marketingActivities.name}" type="text" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>开始时间：</th>
											<td><input id="startTime" name="startTime" value="<s:date name="%{marketingActivities.startTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><img onclick="showTime('startTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"><span style="color: red;">*</span></td>
											<th>结束时间：</th>
											<td><input id="endTime" name="endTime" value="<s:date name="%{marketingActivities.endTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><img onclick="showTime('endTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>备注：</th>
											<td><input id="memo" name="memo" size="30" value="${marketingActivities.memo }" type="text" size="20" onchange="fieldChanged(this);" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<input type="hidden" id="activityFlow" name="activityFlow" value="${marketingActivities.activityFlow}" />
					</form>
					<div class="voucher newvoucher">
						<dl>
							<dt>
								<b class="downup"></b> <strong>流程信息</strong>
							</dt>
							<dd style="display: block;">
								<div id="contentDiv"></div>
								<script>
									   var report = new Rreport({
										contentDiv : 'contentDiv',
										isEdit : true,
										saveFun : function(data) {
											 $("#activityFlow").val(data); 
										},
										});
										$('#vreporttoolbar').removeClass('ui-widget-header ui-corner-all');
										if('${activityFlow}'==''){
										}else {
										 report.loadData(${activityFlow});
										}
										report.addNodeTemplate({
											'process-human.png':function(node){
												$("#client_selection").dialog('open');
												$("#client_selection").attr("nodeId",node.config.id);
												$('#titleId-client_selection').val(node.config.text);
												$("#nodeIcon-client_selection").val(node.config.icon);
												$("#nodeType-client_selection").val(node.config.type);
												$("#nodeValue-client_selection").val(node.config.value);
												$("#nodeParam-client_selection").val(node.config.param);
											}
										});
										$("#client_selection").dialog({
											modal: true,
											width: '450px',
											autoOpen: false ,
											resizable:false,
											buttons: {
												"确认": function() {
													var nodeId =  $("#client_selection").attr("nodeId");
													var node = report.nodeArr[nodeId];
													//保存配置
													node.setConfig({
														text:$('#titleId-client_selection').val(),
														icon:$("#nodeIcon-client_selection").val(),
														type:$("#nodeType-client_selection").val(),
														value:$("#nodeValue-client_selection").val(),
														param:$("#nodeParam-client_selection").val(),
													})
													$(this).dialog( "close" );
												},
												'关闭': function() {
													$(this).dialog( "close" );
												}
											}
										});
										report.addNodeTemplate({
											'start-start.png':function(node){
												$("#start_design").dialog('open');
												$("#start_design").attr("nodeId",node.config.id);
												$('#titleId-start_design').val(node.config.text);
												$("#nodeIcon-start_design").val(node.config.icon);
												$("#nodeType-start_design").val(node.config.type);
												$("#nodeValue-start_design").val(node.config.value);
											}
										});
										
										
										$("#start_design").dialog({
											modal: true,
											autoOpen: false ,
											width: '450px',
											resizable:false,
											buttons: {
												"确认": function() {
													var nodeId =  $("#start_design").attr("nodeId");
													var node = report.nodeArr[nodeId];
													//保存配置
													node.setConfig({
														text:$('#titleId-start_design').val(),
														icon:$("#nodeIcon-start_design").val(),
														type:$("#nodeType-start_design").val(),
														value:$("#nodeValue-start_design").val(),
													})
													$(this).dialog( "close" );
												},
												'关闭': function() {
													$(this).dialog( "close" );
												}
											}
										});
										
										report.addNodeTemplate({
											'delay-timer.png':function(node){
												$("#delay_time").dialog('open');
												$("#delay_time").attr("nodeId",node.config.id);
												$('#titleId-delay_time').val(node.config.text);
												$("#nodeIcon-delay_time").val(node.config.icon);
												$("#nodeType-delay_time").val(node.config.type);
												$("#nodeValue-delay_time").val(node.config.value);
											}
										});
										
										
										$("#delay_time").dialog({
											modal: true,
											autoOpen: false ,
											width: '450px',
											resizable:false,
											buttons: {
												"确认": function() {
													var nodeId =  $("#delay_time").attr("nodeId");
													var node = report.nodeArr[nodeId];
													//保存配置
													node.setConfig({
														text:$('#titleId-delay_time').val(),
														icon:$("#nodeIcon-delay_time").val(),
														type:$("#nodeType-delay_time").val(),
														value:$("#nodeValue-delay_time").val(),
													})
													$(this).dialog( "close" );
												},
												'关闭': function() {
													$(this).dialog( "close" );
												}
											}
										});
										report.addNodeTemplate({
											'process-email.png':function(node){
												$("#send_message").dialog('open');
												$("#send_message").attr("nodeId",node.config.id);
												$('#titleId-send_message').val(node.config.text);
												$("#nodeIcon-send_message").val(node.config.icon);
												$("#nodeType-send_message").val(node.config.type);
												$("#nodeValue-send_message").val(node.config.value);
												$("#nodeParam-send_message").val(node.config.param);
												$("#nodeExt-send_message").val(node.config.ext);
											}
										});
										
										
										$("#send_message").dialog({
											modal: true,
											autoOpen: false ,
											width: '450px',
											resizable:false,
											buttons: {
												"确认": function() {
													var nodeId =  $("#send_message").attr("nodeId");
													var node = report.nodeArr[nodeId];
													//保存配置
													node.setConfig({
														text:$('#titleId-send_message').val(),
														icon:$("#nodeIcon-send_message").val(),
														type:$("#nodeType-send_message").val(),
														value:$("#nodeValue-send_message").val(),
														param:$("#nodeParam-send_message").val(),
														ext:$("#nodeExt-send_message").val(),
													})
													$(this).dialog( "close" );
												},
												'关闭': function() {
													$(this).dialog( "close" );
												}
											}
										});
										report.addNodeTemplate({
											'process-money.png':function(node){
												$("#send_coupon").dialog('open');
												$("#send_coupon").attr("nodeId",node.config.id);
												$('#titleId-send_coupon').val(node.config.text);
												$("#nodeIcon-send_coupon").val(node.config.icon);
												$("#nodeType-send_coupon").val(node.config.type);
												$("#nodeValue-send_coupon").val(node.config.value);
												$("#nodeParam-send_coupon").val(node.config.param);
											}
										});
										
										
										$("#send_coupon").dialog({
											modal: true,
											autoOpen: false ,
											width: '450px',
											resizable:false,
											buttons: {
												"确认": function() {
													var nodeId =  $("#send_coupon").attr("nodeId");
													var node = report.nodeArr[nodeId];
													//保存配置
													node.setConfig({
														text:$('#titleId-send_coupon').val(),
														icon:$("#nodeIcon-send_coupon").val(),
														type:$("#nodeType-send_coupon").val(),
														value:$("#nodeValue-send_coupon").val(),
														param:$("#nodeParam-send_coupon").val(),
													})
													$(this).dialog( "close" );
												},
												'关闭': function() {
													$(this).dialog( "close" );
												}
											}
										});
							      </script>
							</dd>
						</dl>
					</div>
				</div>
			</dd>
		</dl>
	</div>
	<!-- dialog -->
</div>