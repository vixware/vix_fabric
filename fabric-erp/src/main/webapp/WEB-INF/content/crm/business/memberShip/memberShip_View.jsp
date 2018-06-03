<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />Print</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />Help</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/customerAccount.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">客服管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/business/memberShipAction!goList.action');">客户列表</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="npcontent clearfix">
		<div class="np_right">
			<div class="np_right_box">
				<div class="nprb_title">
					会员视图 <a style="cursor: help;"><img src="img/infild.png" width="16" height="16" style="vertical-align: middle;" />提示</a>
				</div>
				<div class="ld">
					<table border="0" width="100%;">
						<tr>
							<td>消费金额：</td>
							<td align="right">￥0.00</td>
						</tr>
						<tr>
							<td>积分：</td>
							<td align="right">￥0.00</td>
						</tr>
						<tr>
							<td>会员等级：</td>
							<td align="right">￥0.00</td>
						</tr>
						<tr>
							<td>储值余额：</td>
							<td align="right">￥0.00</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="np_right_box">
				<div class="nprb_title">
					会员标签</a>
				</div>
				<div class="ld">
					<table border="0" width="100%;">
						<tr>
							<td><a href="###">公务员</a></td>
						</tr>
						<tr>
							<td><a href="###">中等价值</a></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="np_right_box">
				<div class="nprb_title">
					联系人<a href="#">(新建/明细/审批)</a>
				</div>
				<s:iterator value="customerAccount.contactPersons" var="cp">
					<p align="center">
						<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">${cp.personName}</a><br /> <b>${cp.mobile}</b><img border="0" align="Baseline" src="img/sphone.png"><br />
					</p>
				</s:iterator>
			</div>
		</div>
		<div class="np_left clearfix" id="common_table">
			<div class="np_left_title">
				<h2 style="float: none;">会员视图</h2>
			</div>
			<s:hidden id="id" name="customerAccount.id" value="%{customerAccount.id}" theme="simple" />
			<div class="task_list">
				<table width="100%">
					<tr>
						<th colspan="4">会员名称：<b>李强</b></th>
					</tr>
					<tr>
						<td width="15%" align="right">性别：</td>
						<td width="35%">${customerAccount.customerSource.name }</td>
						<td width="10%" align="right">年龄：</td>
						<td width="40%">${customerAccount.customerStage.name }</td>
					</tr>
					<tr>
						<td align="right">QQ：</td>
						<td>${customerAccount.customerSource.name }</td>
						<td align="right">eMail：</td>
						<td>${customerAccount.customerStage.name }</td>
					</tr>
					<tr>
						<td align="right">教育程度：</td>
						<td></td>
						<td align="right">身份证号：</td>
						<td>${customerAccount.hotLevel.name}</td>
					</tr>
					<tr>
						<td align="right">收入水平：</td>
						<td></td>
						<td align="right">生日：</td>
						<td>${customerAccount.hotLevel.name}</td>
					</tr>
					<tr>
						<td align="right">会员种类：</td>
						<td>${customerAccount.customerType.name }</td>
						<td align="right">行业：</td>
						<td>${customerAccount.industry.name }</td>
					</tr>
					<tr>
						<td colspan="4"><strong>基本信息</strong></td>
					</tr>
					<tr>
						<td align="right">助记简称：</td>
						<td>${customerAccount.shorterName }</td>
						<td align="right">价值评估：</td>
						<td><s:if test="customerAccount.valueAssessment == 1">高</s:if> <s:elseif test="customerAccount.valueAssessment == 2">中</s:elseif> <s:elseif test="customerAccount.valueAssessment == 3">低</s:elseif> <s:else>中</s:else></td>
					</tr>
					<tr>
						<td align="right">编码：</td>
						<td>${customerAccount.code}</td>
						<td align="right">信用等级：</td>
						<td><s:if test="customerAccount.valueAssessment == 1">高</s:if> <s:elseif test="customerAccount.valueAssessment == 2">中</s:elseif> <s:elseif test="customerAccount.valueAssessment == 3">低</s:elseif> <s:else>中</s:else></td>
					</tr>
					<tr>
						<td align="right">积分：</td>
						<td>${customerAccount.relationshipClass.name }</td>
						<td align="right">地址：</td>
						<td>${customerAccount.staffScale.name }</td>
					</tr>
					<tr>
						<td align="right">国家或地区：</td>
						<td colspan="3">${customerAccount.shippingAddressCountry}</td>
					</tr>
					<tr>
						<td align="right">邮编：</td>
						<td>${customerAccount.shippingAddressPostalcode}</td>
						<td align="right">电话：</td>
						<td>${customerAccount.phoneOffice}</td>
					</tr>
					<tr>
						<td align="right">传真：</td>
						<td>${customerAccount.phoneFax}</td>
						<td align="right">网址：</td>
						<td><a href="${customerAccount.website}" target="_blank">${customerAccount.website}</a></td>
					</tr>
					<tr>
						<td colspan="4"><strong>营销属性</strong></td>
					</tr>
					<tr>
						<td align="right">身高：</td>
						<td></td>
						<td align="right">体型：</td>
						<td></td>
					</tr>
					<tr>
						<td align="right">着装风格：</td>
						<td></td>
						<td align="right">肤色：</td>
						<td></td>
					</tr>
					<tr>
						<td align="right">上衣号码：</td>
						<td></td>
						<td align="right">裤装号码：</td>
						<td></td>
					</tr>
					<tr>
						<td align="right">鞋号：</td>
						<td></td>
						<td align="right">收购门店：</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="4"><strong>备注</strong></td>
					</tr>
					<tr>
						<td align="right">备注：</td>
						<td colspan="3">${customerAccount.memo}</td>
					</tr>
					<tr>
						<td align="right">创建日期：</td>
						<td></td>
						<td align="right">更新日期：</td>
						<td></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="atable">
			<div class="task_list">
				<table width="100%" class="newtab  newtab_blue">
					<tr>
						<th colspan="3" style="text-align: center">消费数据</th>
					</tr>
					<tr>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td class="np_border">
							<table width="100%">
								<tr>
									<th>门店销售记录</th>
									<th style="text-align: right;"></th>
								</tr>

							</table>
							<table width="100%">
								<tr>
									<th>网店销售记录</th>
									<th style="text-align: right;"></th>
								</tr>

							</table>
							<table width="100%">
								<tr>
									<th>退货记录</th>
									<th style="text-align: right;"></th>
								</tr>
							</table>
						</td>
						<td></td>
						<td class="np_border">
							<table width="100%">
								<tr>
									<th>积分兑换记录</th>
									<th style="text-align: right;"></th>
								</tr>

							</table>
							<table width="100%">
								<tr>
									<th>优惠券发放记录</th>
									<th style="text-align: right;"></th>
								</tr>

							</table>
							<table width="100%">
								<tr>
									<th>开票记录
									<th style="text-align: right;"></th>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width="100%" class="newtab  newtab_yellow">
					<tr>
						<th colspan="3" style="text-align: center">售 后</th>
					</tr>
					<tr>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td class="np_border">
							<table width="100%">
								<tbody>
									<script type="text/javascript">
										function saveOrUpdateCustomerService(id) {
											$.ajax({
											url : '${vix}/crm/service/customerServiceAction!goSaveOrUpdateForCustomerAccount.action?id=' + id,
											cache : false,
											success : function(html) {
												asyncbox.open({
												modal : true,
												width : 920,
												height : 440,
												title : "会员服务",
												html : html,
												callback : function(action) {
													if (action == 'ok') {
														if ($('#customerServiceForm').validationEngine('validate')) {
															$.post('${vix}/crm/service/customerServiceAction!saveOrUpdateForCustomerAccount.action', {
															'customerService.id' : $("#customerServiceId").val(),
															'customerService.subject' : $("#subject").val(),
															'customerService.startDate' : $("#startDate").val(),
															'customerService.startTimeStr' : $("#startTimeStr").val(),
															'customerService.consumeTime.id' : $("#consumeTimeId").val(),
															'customerService.serviceContent' : $("#serviceContent").val(),
															'customerService.customerFeedback' : $("#customerFeedback").val(),
															'customerService.memo' : $("#memo").val(),
															'customerService.serviceMode.id' : $("#serviceModeId").val(),
															'customerService.serviceType.id' : $("#serviceTypeId").val(),
															'customerService.customerAccount.id' : $("#id").val(),
															'customerService.employee.id' : $("#employeeId").val(),
															'customerService.customerServiceStatus.id' : $("#customerServiceStatusId").val(),
															'customerService.customerFeedback' : $("#customerFeedback").val(),
															'customerService.serviceContent' : $("#serviceContent").val()
															}, function(result) {
																asyncbox.success(result, "<s:text name='vix_message'/>", function(action) {
																	loadCustomerService();
																});
															});
														} else {
															return false;
														}
													}
												},
												btnsbar : $.btn.OKCANCEL
												});
											}
											});
										}
										function loadCustomerService() {
											$.ajax({
											url : '${vix}/crm/service/customerServiceAction!goListContentForCustomerAccount.action?id=' + $("#id").val(),
											cache : false,
											success : function(html) {
												$("#customerService").html(html);
											}
											});
										}
										loadCustomerService();
									</script>
									<tr class="alt">
										<th>会员服务</th>
										<th style="text-align: right;"><a onclick="saveOrUpdateCustomerService(0);" href="###" class="abtn"><span>新建</span></a></th>
									</tr>
									<tr>
										<td id="customerService" colspan="2"></td>
									</tr>
								</tbody>
							</table>
							<table width="100%">
								<tbody>
									<script type="text/javascript">
										function saveOrUpdateCustomerCare(id) {
											$.ajax({
											url : '${vix}/crm/service/customerCareAction!goSaveOrUpdateForCustomerAccount.action?id=' + id,
											cache : false,
											success : function(html) {
												asyncbox.open({
												modal : true,
												width : 920,
												height : 340,
												title : "会员关怀",
												html : html,
												callback : function(action) {
													if (action == 'ok') {
														if ($('#customerCareForm').validationEngine('validate')) {
															$.post('${vix}/crm/service/customerCareAction!saveOrUpdateForCustomerAccount.action', {
															'customerCare.id' : $("#customerCareId").val(),
															'customerCare.subject' : $("#subject").val(),
															'customerCare.careContent' : $("#careContent").val(),
															'customerCare.customerFeedback' : $("#customerFeedback").val(),
															'customerCare.memo' : $("#memo").val(),
															'customerCare.customerAccount.id' : $("#id").val(),
															'customerCare.employee.id' : $("#employeeId").val()
															}, function(result) {
																asyncbox.success(result, "<s:text name='vix_message'/>", function(action) {
																	loadCustomerCare();
																});
															});
														} else {
															return false;
														}
													}
												},
												btnsbar : $.btn.OKCANCEL
												});
											}
											});
										}
										function loadCustomerCare() {
											$.ajax({
											url : '${vix}/crm/service/customerCareAction!goListContentForCustomerAccount.action?id=' + $("#id").val(),
											cache : false,
											success : function(html) {
												$("#customerCare").html(html);
											}
											});
										}
										loadCustomerCare();
									</script>
									<tr class="alt">
										<th>会员关怀</th>
										<th style="text-align: right;"><a onclick="saveOrUpdateCustomerCare(0);" href="###" class="abtn"><span>新建</span></a></th>
									</tr>
									<tr>
										<td id="customerCare" colspan="2"></td>
									</tr>
								</tbody>
							</table>
						</td>
						<td></td>
						<td class="np_border">
							<table width="100%">
								<tbody>
									<script type="text/javascript">
										function saveOrUpdateCustomerComplaint(id) {
											$.ajax({
											url : '${vix}/crm/service/customerComplaintAction!goSaveOrUpdateForCustomerAccount.action?id=' + id,
											cache : false,
											success : function(html) {
												asyncbox.open({
												modal : true,
												width : 920,
												height : 440,
												title : "会员投诉",
												html : html,
												callback : function(action) {
													if (action == 'ok') {
														if ($('#customerComplaintForm').validationEngine('validate')) {
															$.post('${vix}/crm/service/customerComplaintAction!saveOrUpdateForCustomerAccount.action', {
															'customerComplaint.id' : $("#customerComplaintId").val(),
															'customerComplaint.subject' : $("#subject").val(),
															'customerComplaint.description' : $("#description").val(),
															'customerComplaint.startDate' : $("#startDate").val(),
															'customerComplaint.startTimeStr' : $("#startTimeStr").val(),
															'customerComplaint.customerAccount.id' : $("#id").val(),
															'customerComplaint.contactPerson.id' : $("#contactPersonId").val(),
															'customerComplaint.employee.id' : $("#employeeId").val(),
															'customerComplaint.complaintType.id' : $("#complaintTypeId").val(),
															'customerComplaint.dealResult.id' : $("#dealResultId").val(),
															'customerComplaint.consumeTime.id' : $("#consumeTimeId").val(),
															'customerComplaint.emergencyLevelType.id' : $("#emergencyLevelTypeId").val(),
															'customerComplaint.dealProcess' : $("#dealProcess").val(),
															'customerComplaint.customerFeedback' : $("#customerFeedback").val(),
															'customerComplaint.visitConfirmed' : $("#visitConfirmed").val(),
															'customerComplaint.memo' : $("#memo").val()
															}, function(result) {
																asyncbox.success(result, "<s:text name='vix_message'/>", function(action) {
																	loadCustomerComplaint();
																});
															});
														} else {
															return false;
														}
													}
												},
												btnsbar : $.btn.OKCANCEL
												});
											}
											});
										}
										function loadCustomerComplaint() {
											$.ajax({
											url : '${vix}/crm/service/customerComplaintAction!goListContentForCustomerAccount.action?id=' + $("#id").val(),
											cache : false,
											success : function(html) {
												$("#customerComplaint").html(html);
											}
											});
										}
										loadCustomerComplaint();
									</script>
									<tr class="alt">
										<th>会员投诉</th>
										<th style="text-align: right;"><a href="###" class="abtn" onclick="saveOrUpdateCustomerComplaint(0);"><span>新建</span></a></th>
									</tr>
									<tr>
										<td id="customerComplaint" colspan="2"></td>
									</tr>
								</tbody>
							</table>
							<table width="100%">
								<tbody>
									<script type="text/javascript">
										function saveOrUpdateCustomerServiceNotepad(id) {
											$.ajax({
											url : '${vix}/crm/service/customerServiceNotepadAction!goSaveOrUpdateForCustomerAccount.action?id=' + id,
											cache : false,
											success : function(html) {
												asyncbox.open({
												modal : true,
												width : 820,
												height : 240,
												title : "会员记事",
												html : html,
												callback : function(action) {
													if (action == 'ok') {
														if ($('#customerServiceNotepadForm').validationEngine('validate')) {
															$.post('${vix}/crm/service/customerServiceNotepadAction!saveOrUpdateForCustomerAccount.action', {
															'customerServiceNotepad.id' : $("#customerServiceNotepadId").val(),
															'customerServiceNotepad.customerAccount.id' : $("#id").val(),
															'customerServiceNotepad.noteDate' : $("#noteDate").val(),
															'customerServiceNotepad.content' : $("#csnContent").val()
															}, function(result) {
																asyncbox.success(result, "<s:text name='vix_message'/>", function(action) {
																	loadCustomerServiceNotepad();
																});
															});
														} else {
															return false;
														}
													}
												},
												btnsbar : $.btn.OKCANCEL
												});
											}
											});
										}
										function loadCustomerServiceNotepad() {
											$.ajax({
											url : '${vix}/crm/service/customerServiceNotepadAction!goListContentForCustomerAccount.action?id=' + $("#id").val(),
											cache : false,
											success : function(html) {
												$("#customerServiceNotepad").html(html);
											}
											});
										}
										loadCustomerServiceNotepad();
									</script>
									<tr class="alt">
										<th>客服记事</th>
										<th style="text-align: right;"><a href="###" class="abtn" onclick="saveOrUpdateCustomerServiceNotepad(0);"><span>新建</span></a></th>
									</tr>
									<tr>
										<td id="customerServiceNotepad" colspan="2"></td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>