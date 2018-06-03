<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link href="${nvix}/common/js/vreport/vreport/css/vreport.css" rel="stylesheet" type="text/css" />
<link href="${nvix}/common/js/vreport/jquery/css/jquery-ui-1.9.1.custom.css" rel="stylesheet" type="text/css" />
<script src="${nvix}/common/js/vreport/vprint.js" type="text/javascript"></script>
<script src="${nvix}/common/js/raphael-min.js" type="text/javascript"></script>
<script src="${nvix}/common/js/vreport/flowtask.js" type="text/javascript"></script>

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

<input type="hidden" id="id" name="id" value="${marketingActivities.id}" />
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
					<option value="com.process.vreport.processor.ClientSelectionProcessor">客户筛选</option>
					<option value="com.process.vreport.processor.DistinctProcessor">排重</option>
			</select></td>
		</tr>
		<tr>
			<td align="right"><span>客户细分：</span></td>
			<td><select id="nodeParam-client_selection" name="nodeParam-client_selection">
					<c:forEach var="membershipSubdivision" items="${membershipSubdivisionList}">
						<option value="${membershipSubdivision.id}">${membershipSubdivision.name}</option>
					</c:forEach>
			</select></td>
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
					<option value="quartz://report?cron=*/50 * * * * ?&stateful=true">立即执行</option>
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
					<option value="3600000">1小时</option>
					<option value="600000">10分钟</option>
					<option value="60000">1分钟</option>
					<option value="10000">10秒</option>
					<option value="1000">1秒</option>
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
			<td><select id="nodeIcon-send_message" disabled="disabled"><option value="waiting.png">短信</option></select></td>
		</tr>
		<tr>
			<td align="right"><span>操作：</span></td>
			<td><select id="nodeValue-send_message">
					<option value="com.process.vreport.processor.MessageProcessor">短信发送</option>
			</select></td>
		</tr>
		<%-- <tr>
			<td align="right"><span>短信模板：</span></td>
			<td><s:select id="nodeParam-send_message" headerKey="" headerValue="--请选择--" list="%{messageTemplateList}" listValue="name" listKey="messageContent" value="" multiple="false" theme="simple" onchange="changeDisplay(this);">
				</s:select></td>
		</tr> --%>
		<tr>
			<td align="right"><span>短信预览：</span></td>
			<td><textarea id="nodeExt-send_message"></textarea></td>
		</tr>
	</table>
</div>
<div class="client_selection" id="send_email" title="邮件发送" style="display: none; width: 450px;">
	<table style="width: 100%">
		<tr>
			<td align="right"><span>标题：</span></td>
			<td><input type="text" id="titleId-send_email" /></td>
		</tr>
		<tr>
			<td align="right"><span>类型：</span></td>
			<td><select id="nodeType-send_email" disabled="disabled">
					<option value="process">邮件发送</option>
			</select></td>
		</tr>
		<tr>
			<td align="right"><span>图标：</span></td>
			<td><select id="nodeIcon-send_email" disabled="disabled"><option value="email.png">邮件</option></select></td>
		</tr>
		<tr>
			<td align="right"><span>操作：</span></td>
			<td><select id="nodeValue-send_email">
					<option value="com.process.vreport.processor.EmailProcessor">邮件发送</option>
			</select></td>
		</tr>
		<%-- <tr>
			<td align="right"><span>邮件模板：</span></td>
			<td><s:select id="nodeParam-send_email" headerKey="" headerValue="--请选择--" list="%{emailTemplateList}" listValue="name" listKey="emailContent" value="" multiple="false" theme="simple" onchange="changeemail(this);">
				</s:select></td>
		</tr> --%>
		<tr>
			<td align="right"><span>邮件预览：</span></td>
			<td><textarea id="nodeExt-send_email"></textarea></td>
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
			<td><select id="nodeValue-send_coupon" disabled="disabled">
					<option value="com.process.vreport.processor.CouponsProcessor">优惠券发放</option>
			</select></td>
		</tr>
		<%-- <tr>
			<td align="right"><span>优惠券：</span></td>
			<td><s:select id="nodeParam-send_coupon" headerKey="" headerValue="--请选择--" list="%{couponList}" listValue="name" listKey="id" value="" multiple="false" theme="simple">
				</s:select></td>
		</tr> --%>
	</table>
</div>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 会员交互管理 <span onclick="">&gt; 智能营销</span><span onclick="">&gt; 营销活动</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/vixntMarketingActivitiesAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>营销流程</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="marketingActivitiesForm">
				<fieldset>
					<input type="hidden" id="id" name="marketingActivities.id" value="${marketingActivities.id}" />
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
						<div class="col-md-3">
							<input id="title" name="marketingActivities.title" value="${marketingActivities.title}" class="form-control" type="text" placeholder="日志标题" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
						<div class="col-md-3">
							<input id="title" name="marketingActivities.title" value="${marketingActivities.title}" class="form-control" type="text" placeholder="日志标题" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>开始时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="startTime" name="marketingActivities.startTime" value="${marketingActivities.startTimeTimeStr}" data-prompt-position="topLeft" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'startTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>结束时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="logDate" name="marketingActivities.logDate" value="${marketingActivities.logDateStr}" data-prompt-position="topLeft" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'logDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="marketingActivities.memo" class="form-control" rows="4">${marketingActivities.memo }</textarea>
						</div>
					</div>
					<input type="hidden" id="activityFlow" name="marketingActivities.activityFlow" value="${marketingActivities.activityFlow}" />
				</fieldset>
			</form>
			<fieldset>
				<div class="form-group">
					<label class="col-md-2 control-label">流程信息:</label>
					<div class="col-md-8">
						<div id="" class="jarviswidget">
							<header>
								<h2>流程信息</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="contentDiv"></div>
									<script>
									   var temp = true;
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
												
												$("#client_selection").dialog({
													modal: true,
													width: '450px',
													autoOpen: true ,
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
												
												//$("#client_selection").dialog('open');
												$("#client_selection").attr("nodeId",node.config.id);
												$('#titleId-client_selection').val(node.config.text);
												$("#nodeIcon-client_selection").val(node.config.icon);
												$("#nodeType-client_selection").val(node.config.type);
												$("#nodeValue-client_selection").val(node.config.value);
											}
										});
										//
										
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
												//$("#start_design").dialog('open');
												$("#start_design").dialog({
													modal: true,
													autoOpen: true ,
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
												
												$("#start_design").attr("nodeId",node.config.id);
												$('#titleId-start_design').val(node.config.text);
												$("#nodeIcon-start_design").val(node.config.icon);
												$("#nodeType-start_design").val(node.config.type);
												$("#nodeValue-start_design").val(node.config.value);
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
												//$("#delay_time").dialog('open');
												$("#delay_time").dialog({
													modal: true,
													autoOpen: true ,
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
											'process-waiting.png':function(node){
												//$("#send_message").dialog('open');
												$("#send_message").dialog({
													modal: true,
													autoOpen: true ,
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
															})
															$(this).dialog( "close" );
														},
														'关闭': function() {
															$(this).dialog( "close" );
														}
													}
												});
												$("#send_message").attr("nodeId",node.config.id);
												$('#titleId-send_message').val(node.config.text);
												$("#nodeIcon-send_message").val(node.config.icon);
												$("#nodeType-send_message").val(node.config.type);
												$("#nodeValue-send_message").val(node.config.value);
												$("#nodeParam-send_message").val(node.config.param);
												/* $("#nodeExt-send_message").val(node.config.ext); */
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
												//$("#send_coupon").dialog('open');
												$("#send_coupon").dialog({
													modal: true,
													autoOpen: true ,
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
										report.addNodeTemplate({
											'process-email.png':function(node){
												//$("#send_email").dialog('open');
												$("#send_email").dialog({
													modal: true,
													autoOpen: true ,
													width: '450px',
													resizable:false,
													buttons: {
														"确认": function() {
															var nodeId =  $("#send_email").attr("nodeId");
															var node = report.nodeArr[nodeId];
															//保存配置
															node.setConfig({
																text:$('#titleId-send_email').val(),
																icon:$("#nodeIcon-send_email").val(),
																type:$("#nodeType-send_email").val(),
																value:$("#nodeValue-send_email").val(),
																param:$("#nodeParam-send_email").val(),
															})
															$(this).dialog( "close" );
														},
														'关闭': function() {
															$(this).dialog( "close" );
														}
													}
												});
												$("#send_email").attr("nodeId",node.config.id);
												$('#titleId-send_email').val(node.config.text);
												$("#nodeIcon-send_email").val(node.config.icon);
												$("#nodeType-send_email").val(node.config.type);
												$("#nodeValue-send_email").val(node.config.value);
												$("#nodeParam-send_email").val(node.config.param);
											}
										});
										
										$("#send_email").dialog({
											modal: true,
											autoOpen: false ,
											width: '450px',
											resizable:false,
											buttons: {
												"确认": function() {
													var nodeId =  $("#send_email").attr("nodeId");
													var node = report.nodeArr[nodeId];
													//保存配置
													node.setConfig({
														text:$('#titleId-send_email').val(),
														icon:$("#nodeIcon-send_email").val(),
														type:$("#nodeType-send_email").val(),
														value:$("#nodeValue-send_email").val(),
														param:$("#nodeParam-send_email").val(),
													})
													$(this).dialog( "close" );
												},
												'关闭': function() {
													$(this).dialog( "close" );
												}
											}
										});
										
										report.addNodeTemplate({
											'summary-summary.png':function(node){
												$("#send_summary").dialog({
													modal: true,
													autoOpen: true ,
													width: '450px',
													resizable:false,
													buttons: {
														"确认": function() {
															var nodeId =  $("#send_summary").attr("nodeId");
															var node = report.nodeArr[nodeId];
															//保存配置
															node.setConfig({
																text:$('#titleId-send_summary').val(),
																icon:$("#nodeIcon-send_summary").val(),
																type:$("#nodeType-send_summary").val(),
																value:$("#nodeValue-send_summary").val(),
																param:$("#nodeParam-send_summary").val(),
															})
															$(this).dialog( "close" );
														},
														'关闭': function() {
															$(this).dialog( "close" );
														}
													}
												});
												$("#send_summary").attr("nodeId",node.config.id);
												$('#titleId-send_summary').val(node.config.text);
												$("#nodeIcon-send_summary").val(node.config.icon);
												$("#nodeType-send_summary").val(node.config.type);
												$("#nodeValue-send_summary").val(node.config.value);
												$("#nodeParam-send_summary").val(node.config.param);
											}
										});
										
										$("#send_summary").dialog({
											modal: true,
											autoOpen: false ,
											width: '450px',
											resizable:false,
											buttons: {
												"确认": function() {
													var nodeId =  $("#send_summary").attr("nodeId");
													var node = report.nodeArr[nodeId];
													//保存配置
													node.setConfig({
														text:$('#titleId-send_summary').val(),
														icon:$("#nodeIcon-send_summary").val(),
														type:$("#nodeType-send_summary").val(),
														value:$("#nodeValue-send_summary").val(),
														param:$("#nodeParam-send_summary").val(),
													})
													$(this).dialog( "close" );
												},
												'关闭': function() {
													$(this).dialog( "close" );
												}
											}
										});
							      </script>
								</div>
							</div>
						</div>
					</div>
				</div>
			</fieldset>
			<div class="form-actions">
				<div class="row">
					<div class="col-md-12">
						<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
							<i class="fa fa-save"></i> 保存
						</button>
						<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/vixntMarketingActivitiesAction!goList.action');">
							<i class="fa fa-rotate-left"></i> 返回
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#marketingActivitiesForm").validationEngine();
	function saveOrUpdate() {
		if ($("#marketingActivitiesForm").validationEngine('validate')) {
			$("#marketingActivitiesForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntMembershipSubdivisionAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/vixntMembershipSubdivisionAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	function changeDisplay(a) {
		$('#nodeExt-send_message').val(a.value);
	}
	function changeemail(a) {
		$('#nodeExt-send_email').val(a.value);
	}

</script>