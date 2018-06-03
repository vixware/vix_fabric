<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
 
$("#saleLeadForm").validationEngine("attach",{promptPosition:"topLeft"});
function saveOrUpdateSaleLead(){
	if($('#saleLeadForm').validationEngine('validate')){
		$.post('${vix}/crm/lead/saleLeadAction!saveOrUpdate.action',
			{'saleLead.id':$("#id").val(),
			  'saleLead.subject':$("#subject").val(),
			  'saleLead.lastName':$("#lastName").val(),
			  'saleLead.firstName':$("#firstName").val(),
			  'saleLead.title':$("#title").val(),
			  'saleLead.type':$("#type").val(),
			  'saleLead.findOutTime':$("#findOutTime").val(),
			  'saleLead.leadSource':$("#leadSource").val(),
			  'saleLead.sourcePerson':$("#sourcePerson").val(),
			  'saleLead.customerAccount.id':$("#customerAccountId").val(),
			  'saleLead.customerCode':$("#customerCode").val(),
			  'saleLead.customerName':$("#customerName").val(),
			  'saleLead.requirement':$("#requirement").val(),
			  'saleLead.dateEntered':$("#dateEntered").val(),
			  'saleLead.dateModified':$("#dateModified").val(),
			  'saleLead.modifiedUserId':$("#modifiedUserId").val(),
			  'saleLead.createdBy':$("#createdBy").val(),
			  'saleLead.assignedUserId':$("#assignedUserId").val(),
			  'saleLead.amount':$("#amount").val(),
			  'saleLead.currencyType.id':$("#currencyTypeId").val(),
			  'saleLead.contactPerson.id':$("#contactPersonId").val(),
			  'saleLead.dateClosed':$("#dateClosed").val(),
			  'saleLead.nextStepPlan':$("#nextStepPlan").val(),
			  'saleLead.probability':$("#probability").val(),
			  'saleLead.isDeleted':$("#isDeleted").val(),
			  'saleLead.memo':$("#memo").val()
			},
			function(result){
				showMessage(result);
				loadContent('${vix}/crm/lead/saleLeadAction!goList.action');
			}
		);
	}
}

function chooseCustomerAccount(){
	$.ajax({
		  url:'${vix}/mdm/crm/customerAccountAction!goChooseCustomerAccount.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择客户",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#customerAccountId").val(result[0]);
								$("#customerName").val(result[1]);
								$("#customerCode").val(result[2]);
								loadContactPerson();
							}else{
								$("#customerAccountId").val("");
								$("#customerName").val("");
								$("#customerCode").val("");
								asyncbox.success("<s:text name='pleaseChooseCustomerAccount'/>","<s:text name='vix_message'/>");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function loadContactPerson(){
	var customerAccountId = $("#customerAccountId").val();
	$.ajax({
		  url:"${vix}/crm/lead/saleLeadAction!loadCustomerContactPerson.action?id="+$("#id").val() + "&customerAccountId=" + customerAccountId,
		  cache: false,
		  success: function(html){
			  $("#contactPersonId").html(html);
		  }
	});
}
loadContactPerson();
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}

function fastAddContactPerson(id){
	var pageNo = $("#contactPersonPageNoHidden").val();
	var customerAccountId = $("#customerAccountId").val();
	if(null != customerAccountId && "" != customerAccountId){
		$.ajax({
			  url:'${vix}/crm/customer/crmContactPersonAction!goSingleSaveOrUpdate.action?id='+id+"&pageNo="+pageNo + "&customerAccountId=" + customerAccountId,
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 660,
						height : 430,
						title:"联系人信息",
						html:html,
						callback : function(action){
							if(action == 'ok'){
								if($('#contactPersonForm').validationEngine('validate')){
									$.post('${vix}/crm/customer/crmContactPersonAction!saveOrUpdate.action',
											{'contactPerson.id': $("#id").val(),
										      'contactPerson.lastName':$("#lastName").val(),
											  'contactPerson.firstName':$("#firstName").val(),
											  'contactPerson.callTitle':$("#callTitle").val(),
											  'contactPerson.sex':$(":radio[name=sex][checked]").val(),
											  'contactPerson.primaryContact':$(":radio[name=primaryContact][checked]").val(),
											  'contactPerson.birthday':$("#birthday").val(),
											  'contactPerson.company':$("#company").val(),
											  'contactPerson.mobile':$("#mobile").val(),
											  'contactPerson.presideBusiness':$("#presideBusiness").val(),
											  'contactPerson.directPhone':$("#directPhone").val(),
											  'contactPerson.email':$("#email").val(),
											  'contactPerson.phoneHome':$("#phoneHome").val(),
											  'contactPerson.msnAccount':$("#msnAccount").val(),
											  'contactPerson.qqAccount':$("#qqAccount").val(),
											  'contactPerson.skypeAccount':$("#skypeAccount").val(),
											  'contactPerson.wangAccount':$("#wangAccount").val(),
											  'contactPerson.fax':$("#fax").val(),
											  'contactPerson.customerAccount.id':$("#customerAccountId").val()
											},
											function(result){
												if (result != null && "" != result) {
													if(id == 0){
														id = result;
														loadContactPerson();
													}
													$("#contactPersonId").val(id)
													showMessage('联系人信息保存成功!');
												} else {
													showErrorMessage('联系人信息保存失败!');
												}
											}
										);
								}else{
				  					return false;
				  				}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}else {
		alert("请先选择客户！");
	}
}

</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/saleLead.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/lead/saleLeadAction!goList.action');">销售线索</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${saleLead.id}" />
<input type="hidden" id="isDeleted" name="isDeleted" value="${saleLead.isDeleted}" />
<div class="content">
	<form id="saleLeadForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateSaleLead();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/lead/saleLeadAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="saleLead.id > 0">
							${saleLead.subject}
						</s:if> <s:else>
							新增线索
						</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>线索信息</strong>
								</dt>
								<dd style="display: block;">
									<table class="addtable_c">
										<tr>
											<td width="15%" align="right">线索主题:</td>
											<td width="35%"><input id="subject" name="saleLead.subject" value="${saleLead.subject}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td width="10%" align="right">发现时间:</td>
											<td width="40%"><input id="findOutTime" name="saleLead.findOutTime" value="<s:property value='formatDateToString(saleLead.findOutTime)'/>" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('findOutTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif"
												width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">客户名称:</td>
											<td><input id="customerName" name="saleLead.customerName" value="${saleLead.customerName}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="customerAccountId" name="customerAccountId" value="${saleLead.customerAccount.id}" /> <span><a class="abtn"
													href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>基本情况</strong>
								</dt>
								<dd style="display: block;">
									<table class="addtable_c">
										<%-- <tr>
										<td width="15%" align="right">姓:</td>
										<td width="35%"><input id="lastName" name="saleLead.lastName" value="${saleLead.lastName}" type="text" size="30" class="validate[required] text-input"/><span style="color: red;">*</span></td>
										<td width="10%" align="right">名:</td>
										<td width="40%"><input id="firstName" name="saleLead.firstName" value="${saleLead.firstName}" type="text" size="30" class="validate[required] text-input"/><span style="color: red;">*</span></td>
									</tr> --%>
										<tr>
											<td width="15%" align="right">联系人:</td>
											<td width="35%"><s:if test="saleLead.contactPerson != null">
													<s:select id="contactPersonId" headerKey="" headerValue="--请选择--" list="%{contactPersonList}" listValue="name" listKey="id" value="%{saleLead.contactPerson.id}" multiple="false" theme="simple"></s:select>
												</s:if> <s:else>
													<s:select id="contactPersonId" headerKey="" headerValue="--请选择--" list="%{contactPersonList}" listValue="name" listKey="id" value="%{saleLead.contactPerson.id}" multiple="false" theme="simple"></s:select>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<span><a class="abtn" href="#" onclick="fastAddContactPerson(0);"><span>新增联系人</span></a></span>
												</s:else></td>
										</tr>
										<tr>
											<td width="15%" align="right">头衔:</td>
											<td width="35%"><input id="title" name="saleLead.title" value="${saleLead.title}" type="text" size="30" /></td>
											<td width="10%" align="right">部门:</td>
											<td width="40%"><input id="department" name="saleLead.department" value="${saleLead.department}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">类型:</td>
											<td><input id="type" name="saleLead.type" value="${saleLead.type}" type="text" size="30" /></td>
											<td align="right">提供人:</td>
											<td><input id="sourcePerson" name="saleLead.sourcePerson" value="${saleLead.sourcePerson}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">来源:</td>
											<td><input id="leadSource" name="saleLead.leadSource" value="${saleLead.leadSource}" type="text" size="30" /></td>
											<td align="right">概率:</td>
											<td><input id="probability" name="saleLead.probability" value="${saleLead.probability}" type="text" size="30" />% 范围(1-100)</td>
										</tr>
										<tr>
											<td align="right">客户需求:</td>
											<td><input id="requirement" name="saleLead.requirement" value="${saleLead.requirement}" type="text" size="30" /></td>
											<td align="right">输入日期 :</td>
											<td><input id="dateEntered" name="saleLead.dateEntered" value="${saleLead.dateEntered}" type="text" size="30" /> <img onclick="showTime('dateEntered','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">修改日期:</td>
											<td><input id="dateModified" name="saleLead.dateModified" value="${saleLead.dateModified}" type="text" size="30" /> <img onclick="showTime('dateModified','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">下一步计划:</td>
											<td><input id="nextStepPlan" name="saleLead.nextStepPlan" value="${saleLead.nextStepPlan}" type="text" size="30" /></td>
										</tr>
										<tr>
											<%-- <td align="right">所属用户:</td>
										<td><input id="assignedUserId" name="saleLead.assignedUserId" value="${saleLead.customerCode}" type="text" size="30"/></td> --%>
											<td align="right">备注:</td>
											<td><input id="memo" name="saleLead.memo" value="${saleLead.memo}" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>费用信息</strong>
								</dt>
								<dd style="display: block;">
									<table class="addtable_c">
										<tr>
											<td width="15%" align="right">金额:</td>
											<td width="35%"><input id="amount" name="saleLead.amount" value="${saleLead.amount}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <script type="text/javascript">
												$(function(){
													$('#amount').priceFormat({
													    prefix: '',
													    centsLimit: 1
													});
												});
											</script></td>
											<td width="10%" align="right">货币类型:</td>
											<td width="40%"><s:select id="currencyTypeId" headerKey="" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="%{saleLead.currencyType.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">关闭日期:</td>
											<td colspan="3"><input id="dateClosed" name="saleLead.dateClosed" value="${saleLead.dateClosed}" type="text" size="30" /> <img onclick="showTime('dateClosed','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
		<!--oder-->
	</form>
</div>
<!-- content -->
