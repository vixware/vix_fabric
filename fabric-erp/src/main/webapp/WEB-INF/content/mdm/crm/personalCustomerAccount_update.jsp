<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script type="text/javascript">

	var customerAccountCategorySetting = {
		view: {
			dblClickExpand: false
		},
		async: {
			enable: true,
			url:"${vix}/crm/customer/crmCustomerAccountCategoryAction!findTreeToJson.action",
			autoParam:["id", "name=n", "level=lv"]
		},
		callback: {
			onClick: onICClick
		}
	};
	function onICClick(e, treeId, treeNode) {
		$("#customerAccountCategoryId").attr("value", treeNode.id);
		$("#customerAccountCategoryName").attr("value", treeNode.name);
		hideICMenu();
	}
	function showICMenu() {
		var left = $('#customerAccountCategoryName').offset().left;
		var top = $('#customerAccountCategoryName').offset().top;
		$("#customerAccountCategoryMenuContent").css({left:left + "px", top:top + 30 + "px"}).slideDown("fast");
		$("body").bind("mousedown", onICBodyDown);
	}
	function hideICMenu() {
		$("#customerAccountCategoryMenuContent").fadeOut("fast");
		$("body").unbind("mousedown", onICBodyDown);
	}
	function onICBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "customerAccountCategoryMenuContent" || $(event.target).parents("#customerAccountCategoryMenuContent").length>0)) {
			hideICMenu();
		}
	}
	$.fn.zTree.init($("#customerAccountCategoryTree"), customerAccountCategorySetting);
	$(".addtable .addtable_t").click(function(){
		$(this).toggleClass("addtable_td");
		$(this).next(".addtable_c").toggle();
	});
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
  function saveOrUpdateCustomerAccount(){
	 if($('#customerAccountForm').validationEngine('validate')){
		$.post('${vix}/mdm/crm/customerAccountAction!saveOrUpdate.action',
			{'customerAccount.id':$("#id").val(),
			  'contactPerson.directPhone':$("#directPhone").val(),
			  'customerAccount.name':$("#name").val(),
			  'customerAccount.customerAccountCategory.id':$("#customerAccountCategoryId").val(),
			  'customerAccount.isHighSea':$("#isHighSea").val(),
			  'customerAccount.type':$("#customerAccountType").val(),
			  'customerAccount.isHotCustomer':$(":radio[name=isHotCustomer][checked]").val(),
			  'contactPerson.sex':$(":radio[name=sex][checked]").val(),
			  'contactPerson.birthday':$("#birthday").val(),
			  'customerAccount.customerType.id':$("#customerTypeId").val(),
			  'customerAccount.relationshipClass.id':$("#relationshipClassId").val(),
			  'customerAccount.customerSource.id':$("#customerSourceId").val(),
			  'customerAccount.customerStage.id':$("#customerStageId").val(),
			  'contactPerson.credentialType.id':$("#credentialTypeId").val(),
			  'customerAccount.hotLevel.id':$("#hotLevelId").val(),
			  'contactPerson.id':$("#cpId").val(),
			  'contactPerson.name':$("#cpName").val(),
			  'contactPerson.credentialCode':$("#credentialCode").val(),
			  'contactPerson.contactPersonType.id':$("#contactPersonTypeId").val(),
			  'contactPerson.email':$("#email").val(),
			  'contactPerson.mobile':$("#mobile").val(),
			  'contactPerson.qqAccount':$("#qqAccount").val(),
			  'contactPerson.msnAccount':$("#msnAccount").val(),
			  'contactPerson.wangAccount':$("#wangAccount").val(),
			  'contactPerson.skypeAccount':$("#skypeAccount").val(),
			  'customerAccount.levelId':$("#levelId").val(),
			  'customerAccount.pointHistory':$("#pointHistory").val(),
			  'customerAccount.pointFreeze':$("#pointFreeze").val(),
			  'customerAccount.point':$("#point").val(),
			  'customerAccount.isDeleted':$("#isDeleted").val(),
			  'customerAccount.valueAssessment':$(":radio[name=valueAssessment][checked]").val(),
			  'customerAccount.creditLevel':$(":radio[name=creditLevel][checked]").val(),
			  'customerAccount.memo':$("#memo").val(),
			  'contactPerson.tag':$("#tag").val(),
			  'contactPerson.title':$("#title").val(),
			  'contactPerson.outNumber':$("#outNumber").val(),
			  'contactPerson.isMarriage':$(":radio[name=isMarriage][checked]").val(),
			  'contactPerson.isAllowConnect':$(":radio[name=isAllowConnect][checked]").val(),
			  'contactPerson.integratePassword':$("#integratePassword").val(),
			  'contactPerson.education':$("#education").val(),
			  'contactPerson.incomeLevel':$("#incomeLevel").val(),
			  'contactPerson.identity':$("#identity").val(),
			  'contactPerson.isBlack':$("#isBlack").val(),
			  'contactPerson.blackReason':$("#blackReason").val()
			},
			function(result){
				showMessage(result);
				loadContent('${vix}/mdm/crm/customerAccountAction!goList.action');
			}
		 );
	  }
}
$("#customerAccountForm").validationEngine();

</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<s:if test="source =='mdm' ">
					<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />主数据管理</a></li>
					<li><a href="#">主数据维护</a></li>
					<li><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action?source=${source}');">客户</a></li>
				</s:if>
				<s:elseif test="source =='member'">
					<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />供应链</a></li>
					<li><a href="#">会员交互管理</a></li>
					<li><a href="#">会员管理</a></li>
					<li><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action?source=member');">会员</a></li>
				</s:elseif>
				<s:else>
					<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />供应链</a></li>
					<li><a href="#">客户关系管理</a></li>
					<li><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action?source=${source}');">客户</a></li>
				</s:else>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${customerAccount.id}" />
<input type="hidden" id="isHighSea" name="isHighSea" value="${customerAccount.isHighSea}" />
<input type="hidden" id="cpId" name="cpId" value="${contactPerson.id}" />
<input type="hidden" id="isDeleted" name="isDeleted" value="${contactPerson.isDeleted}" />
<input type="hidden" id="cpName" name="cpName" value="${contactPerson.name}" />
<input type="hidden" id="customerAccountType" name="customerAccountType" value="${customerAccountType}" />
<input type="hidden" id="source" value="${source}" />
<div class="content">
	<form id="customerAccountForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateCustomerAccount();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action?source=${source}&pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="customerAccount.id !=''">
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
											<td align="right">客户分类：</td>
											<td colspan="3"><input id="customerAccountCategoryName" value="${customerAccount.customerAccountCategory.name}" type="text" onfocus="showICMenu(); return false;" size="30" /> <input id="customerAccountCategoryId" type="hidden" value="${customerAccount.customerAccountCategory.id}" /></td>
										</tr>
										<tr>
											<td align="right">姓名：</td>
											<td><input id="name" name="name" value="${customerAccount.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">手机：</td>
											<td><input id="directPhone" name="directPhone" value="${contactPerson.directPhone}" type="text" size="30" /></td>
										</tr>
										<s:if test="source =='member'">
											<tr>
												<td align="right">标识：</td>
												<td><input id="tag" name="tag" value="${contactPerson.tag}" type="text" size="30" /></td>
												<td align="right">标题：</td>
												<td><input id="title" name="title" value="${contactPerson.title}" type="text" size="30" /></td>
											</tr>
											<tr>
												<td align="right">外部编号：</td>
												<td><input id="outNumber" outNumber="outNumber" value="${contactPerson.outNumber}" type="text" size="30" /></td>
												<td align="right">积分密码：</td>
												<td><input id="integratePassword" name="integratePassword" value="${contactPerson.integratePassword}" type="password" size="30" /></td>
											</tr>
										</s:if>
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
												<input type="radio" id="isHotCustomer2" name="isHotCustomer" value="0" checked="checked" />否
											</s:else></td>
											<td align="right">热点程度:</td>
											<td><s:select id="hotLevelId" headerKey="" headerValue="--请选择--" list="%{hotLevelList}" listValue="name" listKey="id" value="%{customerAccount.hotLevel.id}" multiple="false" theme="simple"></s:select></td>
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
											<td><s:select id="customerTypeId" headerKey="" headerValue="--请选择--" list="%{customerTypeList}" listValue="name" listKey="id" value="%{customerAccount.customerType.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">阶段：</td>
											<td><s:select id="customerStageId" headerKey="" headerValue="--请选择--" list="%{customerStageList}" listValue="name" listKey="id" value="%{customerAccount.customerStage.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">生日：</td>
											<td><input id="birthday" name="birthday" value="${contactPerson.birthday}" type="text" size="30" /> <img onclick="showTime('birthday','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">等级标识：</td>
											<td><input id="levelId" name="levelId" value="${customerAccount.levelId}" type="text" size="30" /></td>
											<td align="right">积分历史：</td>
											<td><input id="pointHistory" name="pointHistory" value="${customerAccount.pointHistory}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">冻结积分：</td>
											<td><input id="pointFreeze" name="levelId" value="${customerAccount.levelId}" type="text" size="30" /></td>
											<td align="right">可用积分：</td>
											<td><input id="point" name="point" value="${customerAccount.point}" type="text" size="30" /></td>
										</tr>
										<s:if test="source =='member'">
											<tr>
												<td align="right">是否已婚：</td>
												<td><input type="radio" id="isMarriage1" name="isMarriage" value="1" <s:if test="contactPerson.isMarriage == 1">checked="checked"</s:if> />是 <input type="radio" id="isMarriage2" name="isMarriage" value="0" <s:if test="contactPerson.isMarriage == 0">checked="checked"</s:if> />否</td>
												<td align="right">是否允许联系：</td>
												<td><input type="radio" id="isAllowConnect1" name="isAllowConnect" value="1" <s:if test="contactPerson.isAllowConnect == 1">checked="checked"</s:if> />是 <input type="radio" id="isAllowConnect2" name="isAllowConnect" value="0" <s:if test="contactPerson.isAllowConnect == 0">checked="checked"</s:if> />否</td>
											</tr>
											<tr>
												<td align="right">教育程度：</td>
												<td><input id="education" outNumber="education" value="${contactPerson.education}" type="text" size="30" /></td>
												<td align="right">收入水平：</td>
												<td><input id="incomeLevel" name="incomeLevel" value="${contactPerson.incomeLevel}" type="password" size="30" /></td>
											</tr>
											<tr>
												<td align="right">客户身份：</td>
												<td colspan="3"><input id="identity" outNumber="identity" value="${contactPerson.identity}" type="text" size="30" /></td>
											</tr>
										</s:if>
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
											<td><s:select id="customerSourceId" headerKey="" headerValue="--请选择--" list="%{customerSourceList}" listValue="name" listKey="id" value="%{customerAccount.customerSource.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">关系等级：</td>
											<td><s:select id="relationshipClassId" headerKey="" headerValue="--请选择--" list="%{relationshipClassList}" listValue="name" listKey="id" value="%{customerAccount.relationshipClass.id}" multiple="false" theme="simple"></s:select></td>
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
												<input type="radio" id="valueAssessment2" name="valueAssessment" value="3" checked="checked" />低
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
												<input type="radio" id="creditLevel2" name="creditLevel" value="3" checked="checked" />低
											</s:else></td>
										</tr>
										<tr>
											<td align="right">联系人类型：</td>
											<td colspan="3"><s:select id="contactPersonTypeId" headerKey="" headerValue="--请选择--" list="%{contactPersonTypeList}" listValue="name" listKey="id" value="%{contactPerson.contactPersonType.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">证件类型：</td>
											<td><s:select id="credentialTypeId" headerKey="" headerValue="--请选择--" list="%{credentialTypeList}" listValue="name" listKey="id" value="%{contactPerson.credentialType.id}" multiple="false" theme="simple"></s:select></td>
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
						<s:if test="source =='member'">
							<input type="hidden" id="isBlack" vlaue="${contactPerson.isBlack}" />
							<input type="hidden" id="blackReason" vlaue="${contactPerson.blackReason}" />
						</s:if>
						<s:else>
							<input type="hidden" id="tag" value="${contactPerson.tag}" />
							<input type="hidden" id="title" value="${contactPerson.title}" />
							<input type="hidden" id="outNumber" value="${contactPerson.outNumber}" />
							<input type="hidden" id="integratePassword" value="${contactPerson.integratePassword}" />
							<input type="hidden" id="isMarriage" value="${contactPerson.isMarriage}" />
							<input type="hidden" id="isAllowConnect" value="${contactPerson.isAllowConnect}" />
							<input type="hidden" id="education" value="${contactPerson.education}" />
							<input type="hidden" id="incomeLevel" value="${contactPerson.incomeLevel}" />
							<input type="hidden" id="identity" value="${contactPerson.identity}" />
							<input type="hidden" id="memberBirthday" value="${contactPerson.birthday}" />
						</s:else>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<div id="customerAccountCategoryMenuContent" class="customerAccountCategoryMenuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf;">
	<ul id="customerAccountCategoryTree" class="ztree" style="margin-top: 0; width: 155px;"></ul>
</div>
<!-- content -->