<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script type="text/javascript">

  function saveOrUpdateCustomerAccount(){
	$.post('${vix}/crm/customer/crmCustomerAccountAction!saveOrUpdate.action',
		{'customerAccount.id':$("#id").val(),
		  'contactPerson.directPhone':$("#directPhone").val(),
		  'customerAccount.name':$("#name").val(),
		  'customerAccount.customerAccountCategory.id':$("#customerAccountCategoryId").val(),
		  'customerAccount.type':$("#customerAccountType").val(),
		  'customerAccount.isHotCustomer':$(":radio[name=isHotCustomer][checked]").val(),
		  'contactPerson.sex':$(":radio[name=sex][checked]").val(),
		  'contactPerson.sicCode':$("#sicCode").val(),
		  'customerAccount.customerType.id':$("#customerTypeId").val(),
		  'customerAccount.relationshipClass.id':$("#relationshipClassId").val(),
		  'customerAccount.customerSource.id':$("#customerSourceId").val(),
		  'customerAccount.customerStage.id':$("#customerStageId").val(),
		  'contactPerson.credentialType.id':$("#credentialTypeId").val(),
		  'customerAccount.hotLevel.id':$("#hotLevelId").val(),
		  'contactPerson.credentialCode':$("#credentialCode").val(),
		  'contactPerson.contactPersonType.id':$("#contactPersonTypeId").val(),
		  'contactPerson.email':$("#email").val(),
		  'contactPerson.mobile':$("#mobile").val(),
		  'contactPerson.qqAccount':$("#qqAccount").val(),
		  'contactPerson.msnAccount':$("#msnAccount").val(),
		  'contactPerson.wangAccount':$("#wangAccount").val(),
		  'contactPerson.skypeAccount':$("#skypeAccount").val(),
		  'customerAccount.valueAssessment':$(":radio[name=valueAssessment][checked]").val(),
		  'customerAccount.creditLevel':$(":radio[name=creditLevel][checked]").val(),
		  'customerAccount.memo':$("#memo").text()
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/crm/customer/crmCustomerAccountAction!goList.action');
		}
	 );
}
function chooseCustomerAccountCategory(){
	$.ajax({
		  url:'${vix}/crm/customer/crmCustomerAccountCategoryAction!goChooseCustomerAccountCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 640,
					height : 340,
					title:"客户分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(typeof(returnValue) == undefined){
								$("#customerAccountCategoryId").val("");
								$("#customerAccountCategoryName").val("");
							}else{
								var result = returnValue.split(",");
								$("#customerAccountCategoryId").val(result[0]);
								$("#customerAccountCategoryName").val(result[1]);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
  $(window).scroll(function(){
		if($('#orderTitleFixd').parent('dl').offset().top - 43 < $(window).scrollTop()){
			if(!$('#orderTitleFixd').hasClass('fixed')){
				$('#orderTitleFixd').addClass('fixed');
				$('#orderTitleFixd').parent('dl').css('padding-top',30);
			}
		}else{
			$('#orderTitleFixd').removeClass('fixed');
			$('#orderTitleFixd').parent('dl').css('padding-top',0);
		}
	});

</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/customer/crmCustomerAccountAction!goList.action');">客户管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${customerAccount.id}" />
<input type="hidden" id="customerAccountType" name="customerAccountType" value="${customerAccountType}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateCustomerAccount();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/customer/crmCustomerAccountAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong> <b> <s:if test="customerAccount.id > 0">
							${customerAccount.name}
						</s:if> <s:else>
							新增个人客户
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
									<table class="addtable_c">
										<tr>
											<td align="right">姓名：</td>
											<td><input id="name" name="name" value="${customerAccount.name}" type="text" size="30" /></td>
											<td align="right">手机：</td>
											<td><input id="directPhone" name="directPhone" value="${contactPerson.directPhone}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">热点客户:</td>
											<td><s:if test="customerAccount.isHotCustomer == 0">
													<input type="radio" id="isHotCustomer1" name="isHotCustomer" value="1" />是
												<input type="radio" id="isHotCustomer2" name="isHotCustomer" value="0" checked="checked" />否
											</s:if> <s:elseif test="customerAccount.isHotCustomer == 1">
													<input type="radio" id="isHotCustomer1" name="isHotCustomer" value="1" checked="checked" />是
												<input type="radio" id="isHotCustomer2" name="isHotCustomer" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isHotCustomer1" name="isHotCustomer" value="1" />是
												<input type="radio" id="isHotCustomer2" name="isHotCustomer" value="0" />否
											</s:else></td>
											<td align="right">热点程度:</td>
											<td><s:select id="hotLevelId" headerKey="-1" headerValue="--请选择--" list="%{hotLevelList}" listValue="name" listKey="id" value="%{customerAccount.hotLevel.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">性别：</td>
											<td><s:if test="contactPerson.sex == 1">
													<input type="radio" id="sex1" name="sex" value="1" checked="checked" />男
												<input type="radio" id="sex1" name="sex" value="0" />女
											</s:if> <s:else>
													<input type="radio" id="sex1" name="sex" value="1" />男
												<input type="radio" id="sex1" name="sex" value="0" />女
											</s:else></td>
											<td align="right">客户种类：</td>
											<td><s:select id="customerTypeId" headerKey="-1" headerValue="--请选择--" list="%{customerTypeList}" listValue="name" listKey="id" value="%{customerAccount.customerType.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">阶段：</td>
											<td><s:select id="customerStageId" headerKey="-1" headerValue="--请选择--" list="%{customerStageList}" listValue="name" listKey="id" value="%{customerAccount.customerStage.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">编码：</td>
											<td><input id="sicCode" name="sicCode" value="${contactPerson.sicCode}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td colspan="4">联系方式</td>
										</tr>
										<tr>
											<td align="right">邮箱：</td>
											<td><input id="email" name="email" value="${contactPerson.email}" type="text" size="30" /></td>
											<td align="right">工作电话：</td>
											<td><input id="mobile" name="mobile" value="${contactPerson.mobile}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">QQ：</td>
											<td><input id="qqAccount" name="qqAccount" value="${contactPerson.qqAccount}" type="text" size="30" /></td>
											<td align="right">MSN：</td>
											<td><input id="msnAccount" name="msnAccount" value="${contactPerson.msnAccount}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">淘宝旺旺：</td>
											<td><input id="wangAccount" name="wangAccount" value="${contactPerson.wangAccount}" type="text" size="30" /></td>
											<td align="right">SKYPE：</td>
											<td><input id="skypeAccount" name="skypeAccount" value="${contactPerson.skypeAccount}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td colspan="4">商务信息</td>
										</tr>
										<tr>
											<td align="right">来源：</td>
											<td><s:select id="customerSourceId" headerKey="-1" headerValue="--请选择--" list="%{customerSourceList}" listValue="name" listKey="id" value="%{customerAccount.customerSource.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">关系等级：</td>
											<td><s:select id="relationshipClassId" headerKey="-1" headerValue="--请选择--" list="%{relationshipClassList}" listValue="name" listKey="id" value="%{customerAccount.relationshipClass.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">价值评估：</td>
											<td><s:if test="customerAccount.valueAssessment == 1">
													<input type="radio" id="valueAssessment1" name="valueAssessment" value="1" checked="checked" />高
												<input type="radio" id="valueAssessment1" name="valueAssessment" value="2" />中
												<input type="radio" id="valueAssessment2" name="valueAssessment" value="3" />低
											</s:if> <s:elseif test="customerAccount.valueAssessment == 2">
													<input type="radio" id="valueAssessment1" name="valueAssessment" value="1" />高
												<input type="radio" id="valueAssessment1" name="valueAssessment" value="2" checked="checked" />中
												<input type="radio" id="valueAssessment2" name="valueAssessment" value="3" />低
											</s:elseif> <s:elseif test="customerAccount.valueAssessment == 3">
													<input type="radio" id="valueAssessment1" name="valueAssessment" value="1" />高
												<input type="radio" id="valueAssessment1" name="valueAssessment" value="2" />中
												<input type="radio" id="valueAssessment2" name="valueAssessment" value="3" checked="checked" />低
											</s:elseif> <s:else>
													<input type="radio" id="valueAssessment1" name="valueAssessment" value="1" />高
												<input type="radio" id="valueAssessment1" name="valueAssessment" value="2" />中
												<input type="radio" id="valueAssessment2" name="valueAssessment" value="3" />低
											</s:else></td>
											<td align="right">信用等级：</td>
											<td><s:if test="customerAccount.creditLevel == 1">
													<input type="radio" id="creditLevel1" name="creditLevel" value="1" checked="checked" />高
												<input type="radio" id="creditLevel1" name="creditLevel" value="2" />中
												<input type="radio" id="creditLevel2" name="creditLevel" value="3" />低
											</s:if> <s:elseif test="customerAccount.creditLevel == 2">
													<input type="radio" id="creditLevel1" name="creditLevel" value="1" />高
												<input type="radio" id="creditLevel1" name="creditLevel" value="2" checked="checked" />中
												<input type="radio" id="creditLevel2" name="creditLevel" value="3" />低
											</s:elseif> <s:elseif test="customerAccount.creditLevel == 3">
													<input type="radio" id="creditLevel1" name="creditLevel" value="1" />高
												<input type="radio" id="creditLevel1" name="creditLevel" value="2" />中
												<input type="radio" id="creditLevel2" name="creditLevel" value="3" checked="checked" />低
											</s:elseif> <s:else>
													<input type="radio" id="creditLevel1" name="creditLevel" value="1" />高
												<input type="radio" id="creditLevel1" name="creditLevel" value="2" />中
												<input type="radio" id="creditLevel2" name="creditLevel" value="3" />低
											</s:else></td>
										</tr>
										<tr>
											<td align="right">联系人类型：</td>
											<td><s:select id="contactPersonTypeId" headerKey="-1" headerValue="--请选择--" list="%{contactPersonTypeList}" listValue="name" listKey="id" value="%{contactPerson.contactPersonType.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">客户分类：</td>
											<td><s:hidden id="customerAccountCategoryId" name="customerAccountCategoryId" value="%{customerAccount.customerAccountCategory.id}" theme="simple" /> <input id="customerAccountCategoryName" name="customerAccount.customerAccountCategory.name" value="${customerAccount.customerAccountCategory.name}" /> <span class="abtn"><span
													onclick="chooseCustomerAccountCategory();">选择</span></span></td>
										</tr>
										<tr>
											<td align="right">证件类型：</td>
											<td><s:select id="credentialTypeId" headerKey="-1" headerValue="--请选择--" list="%{credentialTypeList}" listValue="name" listKey="id" value="%{contactPerson.credentialType.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">证件号码：</td>
											<td><input id="credentialCode" name="credentialCode" value="${contactPerson.credentialCode}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td colspan="3"><textarea id="memo" name="memo" style="width: 706px; height: 84px;">${customerAccount.memo}</textarea></td>
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
<!-- content -->