<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/projectCost.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">客户管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/project/projectCostAction!goList.action');">费用管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content" style="margin-top: 5px;">
	<form id="projectCostForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateProjectCost();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/project/projectCostAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="projectCost.id > 0">
								修改费用
							</s:if> <s:else>
								新增费用
							</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<s:hidden id="id" name="projectCost.id" value="%{projectCost.id}" theme="simple" />
									<s:hidden id="employeeId" name="projectCost.employee.id" value="%{projectCost.employee.id}" theme="simple" />
									<s:hidden id="crmProjectId" name="projectCost.crmProject.id" value="%{projectCost.crmProject.id}" theme="simple" />
									<table class="addtable_c">
										<tr height="30">
											<td align="right">发生日期:</td>
											<td><input type="text" id="happenDate" name="projectCost.happenDate" value="<s:property value='formatDateToString(projectCost.happenDate)'/>" /> <img onclick="showTime('happenDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">申请人:</td>
											<td><input type="text" id="employeeName" name="projectCost.employee.name" value="${projectCost.employee.name}" readonly="readonly" /> <a href="###" onclick="chooseEmployee();" class="abtn"><span>选择</span></a></td>
										</tr>
										<tr height="30">
											<td align="right">金额:</td>
											<td><input type="text" id="totalAmount" name="projectCost.totalAmount" value="${projectCost.totalAmount}" /></td>
											<td align="right">票据张数:</td>
											<td><input type="text" id="billCount" name="projectCost.billCount" value="${projectCost.billCount}" /></td>
										</tr>
										<tr>
											<td align="right">审批:</td>
											<td><s:if test="projectCost.isApproved == 0">
													<input type="radio" id="isApproved1" name="isApproved" value="1" />通过
													<input type="radio" id="isApproved2" name="isApproved" value="0" checked="checked" />未通过
												</s:if> <s:elseif test="projectCost.isApproved == 1">
													<input type="radio" id="isApproved1" name="isApproved" value="1" checked="checked" />通过
													<input type="radio" id="isApproved2" name="isApproved" value="0" />未通过
												</s:elseif> <s:else>
													<input type="radio" id="isApproved1" name="isApproved" value="1" />通过
													<input type="radio" id="isApproved2" name="isApproved" value="0" />未通过
												</s:else></td>
											<td align="right">项目:</td>
											<td><input type="text" id="crmProjectName" name="projectCost.crmProject.name" value="${projectCost.crmProject.subject}" readonly="readonly" /> <a href="###" onclick="chooseCrmProject();" class="abtn"><span>选择</span></a></td>
										</tr>
										<tr>
											<td align="right">费用类型:</td>
											<td><s:select id="projectCostTypeId" headerKey="" headerValue="--请选择--" list="%{projectCostTypeList}" listValue="name" listKey="id" value="%{projectCost.projectCostType.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">币种:</td>
											<td><s:select id="currencyTypeId" headerKey="" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="%{projectCost.currencyType.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr height="30">
											<td align="right">用途:</td>
											<td><textarea id="use" rows="3" cols="40" style="font-size: 12px;">${projectCost.use}</textarea></td>
											<td align="right">备注:</td>
											<td><textarea id="memo" rows="3" cols="40" style="font-size: 12px;">${projectCost.memo}</textarea></td>
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
<script type="text/javascript">
	//面包屑
	if($('.sub_menu').length){
		$("#breadCrumb").jBreadCrumb();
	}
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
	$("#projectCostForm").validationEngine();
	function chooseEmployee(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectEmpAction!goList.action?tag=choose',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 960,
						height : 500,
						title:"选择职员",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var result = returnValue.split(":");
									$("#employeeId").val(result[0].replace(",",""));
									$("#employeeName").val(result[1].replace(",",""));
								}else{
									$("#employeeId").val("");
									$("#employeeName").val("");
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	function chooseCrmProject(){
		$.ajax({
			  url:'${vix}/crm/project/crmProjectAction!goChooseCrmProject.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
					 	width : 960,
						height : 500,
						title:"选择项目",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var result = returnValue.split(":");
									$("#crmProjectId").val(result[0]);
									$("#crmProjectName").val(result[1]);
								}else{
									$("#crmProjectId").val("");
									$("#crmProjectName").val("");
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	function saveOrUpdateProjectCost(){
		if($('#projectCostForm').validationEngine('validate')){
			$.post('${vix}/crm/project/projectCostAction!saveOrUpdate.action',
				{'projectCost.id':$("#id").val(),
				  'projectCost.employee.id':$("#employeeId").val(),
				  'projectCost.crmProject.id':$("#crmProjectId").val(),
				  'projectCost.happenDate':$("#happenDate").val(),
				  'projectCost.totalAmount':$("#totalAmount").val(),
				  'projectCost.billCount':$("#billCount").val(),
				  'projectCost.isApproved':$(":radio[name=isApproved][checked]").val(),
				  'projectCost.projectCostType.id':$("#projectCostTypeId").val(),
				  'projectCost.currencyType.id':$("#currencyTypeId").val(),
				  'projectCost.use':$("#use").val(),
				  'projectCost.memo':$("#memo").val()
				},
				function(result){
					showMessage(result);
					loadContent('${vix}/crm/project/projectCostAction!goList.action');
				}
			);
		}
	}
</script>