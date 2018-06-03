<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
$("#contactPersonForm").validationEngine();
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
								$("#customerAccountName").val(result[1]);
							}else{
								$("#customerAccountId").val("");
								$("#customerAccountName").val("");
								asyncbox.success("<s:text name='pleaseChooseCustomerAccount'/>","<s:text name='vix_message'/>");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function setCrmContactTypeId(){
	$("#crmContactTypeId").val('${contactPerson.crmContactType.id}');
}
setCrmContactTypeId();
</script>
<input type="hidden" id="id" name="id" value="${contactPerson.id}" />
<div style="padding-top: 15px;" class="content">
	<form id="contactPersonForm">
		<div class="box order_table" style="line-height: normal; padding: 30px;">
			<table class="table-padding020">
				<tr align="left">
					<p align="left">
						<b><font color="blue">联系人信息:</font></b>
					</p>
				</tr>
				<tr height="30">
					<th align="right">客户:&nbsp;</th>
					<td><s:if test="customerAccount != null">
							<input id="customerAccountName" value="${customerAccount.name}" type="text" class="validate[required] text-input" />
							<span style="color: red;">*</span>
							<input id="customerAccountId" value="${customerAccount.id}" type="hidden" />
						</s:if> <s:else>
							<input id="customerAccountId" value="${contactPerson.customerAccount.id}" type="hidden" />
							<input id="customerAccountName" value="${contactPerson.customerAccount.name}" type="text" class="validate[required] text-input" />
							<span style="color: red;">*</span>
							<span><a class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span>
						</s:else></td>
					<%-- <td>
						<s:if test="contactPerson.primaryContact == 0">
							<input type="radio" id="primaryContact1" name="primaryContact" value="1"/>是
							<input type="radio" id="primaryContact2" name="primaryContact" value="0" checked="checked"/>否
						</s:if>
						<s:elseif test="contactPerson.primaryContact == 1">
							<input type="radio" id="primaryContact1" name="primaryContact" value="1" checked="checked"/>是
							<input type="radio" id="primaryContact2" name="primaryContact" value="0"/>否
						</s:elseif>
						<s:else>
							<input type="radio" id="primaryContact1" name="primaryContact" value="1"/>是
							<input type="radio" id="primaryContact2" name="primaryContact" value="0"/>否
						</s:else>
					</td> --%>
				</tr>
				<tr height="30">
					<th align="right">姓名:</th>
					<td><input id="fullName" name="contactPerson.name" value="${contactPerson.name}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<th align="right">性别:&nbsp;</th>
					<td><s:if test="contactPerson.sex == 0">
							<input type="radio" id="sex1" name="sex" value="1" />男
							<input type="radio" id="sex2" name="sex" value="0" checked="checked" />女
						</s:if> <s:elseif test="contactPerson.sex == 1">
							<input type="radio" id="sex1" name="sex" value="1" checked="checked" />男
							<input type="radio" id="sex2" name="sex" value="0" />女
						</s:elseif> <s:else>
							<input type="radio" id="sex1" name="sex" value="1" />男
							<input type="radio" id="sex2" name="sex" value="0" />女
						</s:else></td>
					<%-- <th align="right">:&nbsp;</th>
					<td><input id="firstName" name ="contactPerson.firstName" value="${contactPerson.firstName}" type="text" class="validate[required] text-input"/><span style="color: red;">*</span></td> --%>
				</tr>
				<tr height="30">
					<th align="right">联系人类型:</th>
					<td><s:select id="crmContactTypeId" headerKey="" headerValue="--请选择--" list="%{crmContactTypeList}" listValue="name" listKey="id" value="%{contactPerson.crmContactType.id}" multiple="false" theme="simple"></s:select></td>
					<th align="right">负责业务:&nbsp;</th>
					<td><input id="presideBusiness" name="contactPerson.presideBusiness" value="${contactPerson.presideBusiness}" type="text" /></td>
				</tr>
				<tr align="left">
					<th>
						<p align="left">
							<b><font color="blue">单位:</font></b>
						</p>
					</th>
				</tr>
				<tr height="30">
					<th align="right">称谓:&nbsp;</th>
					<td><input id="callTitle" name="contactPerson.callTitle" value="${contactPerson.callTitle}" type="text" /></td>
					<th align="right">公司:&nbsp;</th>
					<td><input id="company" name="contactPerson.company" value="${contactPerson.company}" type="text" /></td>
				</tr>
				<tr height="30">
					<th align="right">部门:&nbsp;</th>
					<td><input id="department" name="contactPerson.department" value="${contactPerson.department}" type="text" /></td>
					<th align="right">职务:&nbsp;</th>
					<td><input id="position" name="contactPerson.position" value="${contactPerson.position}" type="text" /></td>
				</tr>
				<tr align="left">
					<th>
						<p align="left">
							<b><font color="blue">联系:</font></b>
						</p>
					</th>
				</tr>
				<tr height="30">
					<th align="right">手机:&nbsp;</th>
					<td><input id="mobile" name="contactPerson.mobile" value="${contactPerson.mobile}" type="text" /></td>
					<th align="right">工作电话:&nbsp;</th>
					<td><input id="directPhone" name="directPhone" value="${contactPerson.directPhone}" type="text" /></td>
				</tr>
				<tr height="30">
					<th align="right">传真:&nbsp;</th>
					<td><input id="fax" name="contactPerson.fax" value="${contactPerson.fax}" type="text" /></td>
					<th align="right">邮件:&nbsp;</th>
					<td><input id="email" name="contactPerson.email" value="${contactPerson.email}" type="text" /></td>
				</tr>
				<tr align="left">
					<th>
						<p align="left">
							<b><font color="blue">其他:</font></b>
						</p>
					</th>
				</tr>
				<tr height="30">
					<th align="right">生日:&nbsp;</th>
					<td><input id="birthday" name="contactPerson.birthday" value="<s:property value='formatDateToString(contactPerson.birthday)'/>" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('birthday','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
						align="absmiddle"></td>
					<th align="right">家庭电话:&nbsp;</th>
					<td><input id="phoneHome" name="contactPerson.phoneHome" value="${contactPerson.phoneHome}" type="text" /></td>
				</tr>
				<tr height="30">
					<th align="right">QQ:&nbsp;</th>
					<td><input id="qqAccount" name="contactPerson.qqAccount" value="${contactPerson.qqAccount}" type="text" /></td>
					<th align="right">微信:&nbsp;</th>
					<td><input id="msnAccount" name="contactPerson.msnAccount" value="${contactPerson.msnAccount}" type="text" /></td>
				</tr>
				<tr height="30">
					<th align="right">SKYPE:&nbsp;</th>
					<td><input id="skypeAccount" name="contactPerson.skypeAccount" value="${contactPerson.skypeAccount}" type="text" /></td>
					<th align="right">旺旺:&nbsp;</th>
					<td><input id="wangAccount" name="contactPerson.wangAccount" value="${contactPerson.wangAccount}" type="text" /></td>
				</tr>
				<tr>
					<th align="right">创建人:&nbsp;</th>
					<td><input id="createdBy" name="contactPerson.createdBy" value="${contactPerson.createdBy}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<th align="right">创建时间:&nbsp;</th>
					<td><input id="dateEntered" name="contactPerson.dateEntered" value="<s:property value='formatDateToString(contactPerson.dateEntered)'/>" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('dateEntered','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
						align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<!-- content -->
