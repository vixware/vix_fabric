<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script type="text/javascript">
<!--
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
	$.post('${vix}/crm/customer/crmCustomerAccountAction!saveOrUpdate.action',
		{'customerAccount.id':$("#id").val(),
		  'customerAccount.code':$("#code").val(),
		  'customerAccount.name':$("#name").val(),
		  'customerAccount.customerAccountCategory.id':$("#customerAccountCategoryId").val(),
		  'customerAccount.type':$("#customerAccountType").val(),
		  'customerAccount.isHotCustomer':$(":radio[name=isHotCustomer][checked]").val(),
		  'customerAccount.englishName':$("#englishName").val(),
		  'customerAccount.shorterName':$("#shorterName").val(),
		  'customerAccount.indexWord':$("#indexWord").val(),
		  'customerAccount.trademark':$("#trademark").val(),
		  'customerAccount.valueAssessment':$(":radio[name=valueAssessment][checked]").val(),
		  'customerAccount.creditLevel':$(":radio[name=creditLevel][checked]").val(),
		  'customerAccount.customerType.id':$("#customerTypeId").val(),
		  'customerAccount.relationshipClass.id':$("#relationshipClassId").val(),
		  'customerAccount.hotLevel.id':$("#hotLevelId").val(),
		  'customerAccount.accountType':$("#accountType").val(),
		  'customerAccount.industry.id':$("#industryId").val(),
		  'customerAccount.annualRevenue':$("#annualRevenue").val(),
		  'customerAccount.phoneFax':$("#phoneFax").val(),
		  'customerAccount.customerSource.id':$("#customerSourceId").val(),
		  'customerAccount.customerStage.id':$("#customerStageId").val(),
		  'customerAccount.companyBrief':$("#companyBrief").text(),
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
//-->
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
							${customerAccount.code}
						</s:if> <s:else>
							新增企业客户
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
											<td align="right">编码：</td>
											<td><input id="code" name="code" value="${customerAccount.code}" type="text" size="30" /></td>
											<td align="right">名称：</td>
											<td><input id="name" name="name" value="${customerAccount.name}" type="text" size="30" /></td>
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
										<tr>
											<td align="right">英文名称：</td>
											<td><input id="englishName" name="englishName" value="${customerAccount.englishName}" type="text" size="30" /></td>
											<td align="right">简称：</td>
											<td><input id="shorterName" name="shorterName" value="${customerAccount.shorterName}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">检索词：</td>
											<td><input id="indexWord" name="indexWord" value="${customerAccount.indexWord}" type="text" size="30" /></td>
											<td align="right">商标：</td>
											<td><input id="trademark" name="trademark" value="${customerAccount.trademark}" type="text" size="30" /></td>
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
											<td align="right">客户种类：</td>
											<td><s:select id="customerTypeId" headerKey="-1" headerValue="--请选择--" list="%{customerTypeList}" listValue="name" listKey="id" value="%{customerAccount.customerType.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">关系等级：</td>
											<td><s:select id="relationshipClassId" headerKey="-1" headerValue="--请选择--" list="%{relationshipClassList}" listValue="name" listKey="id" value="%{customerAccount.relationshipClass.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">账户类型：</td>
											<td><input id="accountType" name="accountType" value="${customerAccount.accountType}" type="text" size="30" />
											<td align="right">行业：</td>
											<td><s:select id="industryId" headerKey="-1" headerValue="--请选择--" list="%{industryList}" listValue="name" listKey="id" value="%{customerAccount.industry.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">年收入：</td>
											<td><input id="annualRevenue" name="annualRevenue" value="${customerAccount.annualRevenue}" type="text" size="30" /></td>
											<td align="right">电话传真：</td>
											<td><input id="phoneFax" name="phoneFax" value="${customerAccount.phoneFax}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">来源：</td>
											<td><s:select id="customerSourceId" headerKey="-1" headerValue="--请选择--" list="%{customerSourceList}" listValue="name" listKey="id" value="%{customerAccount.customerSource.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">阶段：</td>
											<td><s:select id="customerStageId" headerKey="-1" headerValue="--请选择--" list="%{customerStageList}" listValue="name" listKey="id" value="%{customerAccount.customerStage.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">客户分类：</td>
											<td colspan="3"><s:hidden id="customerAccountCategoryId" name="customerAccountCategoryId" value="%{customerAccount.customerAccountCategory.id}" theme="simple" /> <input id="customerAccountCategoryName" name="customerAccount.customerAccountCategory.name" value="${customerAccount.customerAccountCategory.name}" /> <span class="abtn"><span
													onclick="chooseCustomerAccountCategory();">选择</span></span></td>
										</tr>
										<tr>
											<td align="right">公司简介：</td>
											<td colspan="3"><textarea id="companyBrief" name="companyBrief" style="width: 706px; height: 84px;">${customerAccount.companyBrief}</textarea></td>
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
			<div class="clearfix" style="background: #FFF; position: relative;">
				<div class="right_menu">
					<ul>
						<li class="current"><a onclick="javascript:$('#a1').attr('style','');tab(5,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />联系人</a></li>
					</ul>
				</div>
				<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
					<div style="padding: 8px;">
						<table id="contactPerson" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="fitColumns:true,singleSelect: true,toolbar: '#itemTb',url: '${vix}/crm/customer/crmCustomerAccountAction!getContactPersonJson.action?id=${customerAccount.id}'">
							<thead>
								<tr>
									<th data-options="field:'id',align:'center',width:100">编号</th>
									<th data-options="field:'lastName',width:140,align:'center'">姓</th>
									<th data-options="field:'lastName',width:140,align:'center'">名</th>
									<th data-options="field:'presideBusiness',width:100,align:'center'">负责业务</th>
									<th data-options="field:'email',width:100,align:'center'">邮件</th>
									<th data-options="field:'mobile',width:180,align:'center'">联系电话</th>
									<th data-options="field:'directPhone',width:100,align:'center'">工作电话</th>
									<th data-options="field:'company',width:260,align:'center'">公司</th>
								</tr>
							</thead>
						</table>
						<div id="itemTb" style="height: auto">
							<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addContactPerson()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
								data-options="iconCls:'icon-add',plain:true" onclick="updateContactPerson()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true"
								onclick="removeContactPerson()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
						</div>
						<script type="text/javascript">
						$('#contactPerson').datagrid();
						function removeContactPerson(){
							var row = $('#contactPerson').datagrid('getSelected');
							if(row){
								var index = $('#contactPerson').datagrid('getRowIndex',row);
								$('#contactPerson').datagrid('deleteRow', index);
								$.ajax({
									  url:'${vix}/crm/customer/crmContactPersonAction!deleteById.action?id='+row.id,
									  cache: false,
									  success: function(html){
										  showMessage(html);
									  }
								});
							}else{
								showMessage("请选择一条数据!");
								return;
							}
						}
						
						function updateContactPerson(){
							var contactPersonId = "";
							var row = $('#contactPerson').datagrid('getSelected');
							if(row){
								contactPersonId = row.id;
							}else{
								showMessage("请选择一条数据!");
								return;
							}
							$.ajax({
								  url:'${vix}/crm/customer/crmContactPersonAction!goSaveOrUpdate.action?id='+contactPersonId,
								  cache: false,
								  success: function(html){
									  asyncbox.open({
										 	modal:true,
											width : 660,
											height : 360,
											title:"修改联系人",
											html:html,
											callback : function(action){
												if(action == 'ok'){
													if($('#contactPersonForm').validationEngine('validate')){
														$.post('${vix}/crm/customer/crmContactPersonAction!saveOrUpdate.action',
																{'contactPerson.id': contactPersonId,
																  'contactPerson.lastName':$("#lastName").val(),
																  'contactPerson.firstName':$("#firstName").val(),
																  'contactPerson.sex':$(":radio[name=sex][checked]").val(),
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
																  'contactPerson.customerAccount.id':$("#id").val()
																},
																function(result){
																	showMessage(result);
																	setTimeout("",1000);
																	$('#contactPerson').datagrid("reload");
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
						}
						
						function addContactPerson(){
							$.ajax({
								  url:'${vix}/crm/customer/crmContactPersonAction!goSaveOrUpdate.action',
								  cache: false,
								  success: function(html){
									  asyncbox.open({
										 	modal:true,
											width : 660,
											height : 360,
											title:"添加联系人",
											html:html,
											callback : function(action){
												if(action == 'ok'){
													if($('#contactPersonForm').validationEngine('validate')){
														$.post('${vix}/crm/customer/crmContactPersonAction!saveOrUpdate.action',
																{'contactPerson.lastName':$("#lastName").val(),
																	  'contactPerson.lastName':$("#lastName").val(),
																	  'contactPerson.firstName':$("#firstName").val(),
																	  'contactPerson.sex':$(":radio[name=sex][checked]").val(),
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
																	  'contactPerson.customerAccount.id':$("#id").val()
																},
																function(result){
																	showMessage(result);
																	setTimeout("",1000);
																	$('#contactPerson').datagrid("reload");
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
						}
				</script>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<!-- content -->