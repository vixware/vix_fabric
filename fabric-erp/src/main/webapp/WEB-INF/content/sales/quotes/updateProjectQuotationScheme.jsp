<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
<!--
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
 
$("#projectQuotationSchemeForm").validationEngine();
if($(".ms").length>0){
	$(".ms").hover(function(){
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">ul",this).stop().slideUp(100);
	});
	$(".ms ul li").hover(function(){
		$(">a",this).addClass("hover");
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">a",this).removeClass("hover");
		$(">ul",this).stop().slideUp(100);
	});
}
function saveOrUpdateSalesQuotation(){
	if($('#projectQuotationSchemeForm').validationEngine('validate')){
		$.post('${vix}/sales/quotes/projectQuotationSchemeAction!saveOrUpdate.action',
			{'projectQuotationScheme.id':$("#id").val(),
			  'projectQuotationScheme.billDate':$("#billDate").val(),
			  'projectQuotationScheme.customerAccount.id':$("#customerAccountId").val(),
			  'projectQuotationScheme.regional.id':$("#regionalId").val(),
			  'projectQuotationScheme.code':$("#pqsCode").val(),
			  'projectQuotationScheme.groupCode':$("#groupCode").val(),
			  'projectQuotationScheme.name':$("#name").val(),
			  'projectQuotationScheme.organizationUnit.id':$("#pqsOrganizationUnitId").val(),
			  'projectQuotationScheme.type':$("#typeId").val(),
			  'projectQuotationScheme.amount':$("#amount").val(),
			  'projectQuotationScheme.bizType':$("#bizType").val(),
			  'projectQuotationScheme.formType':$("#formType").val(),
			  'projectQuotationScheme.salesOrg.id':$("#saleOrgId").val(),
			  'projectQuotationScheme.salesMan.id':$("#salesManId").val(),
			  'projectQuotationScheme.tax':$("#tax").val(),
			  'projectQuotationScheme.dilveryDate':$("#dilveryDate").val(),
			  'projectQuotationScheme.city':$("#city").val(),
			  'projectQuotationScheme.isDeleted':$("#isDeleted").val(),
			  'projectQuotationScheme.validQuotationFrom':$("#validQuotationFrom").val(),
			  'projectQuotationScheme.validQuotationTo':$("#validQuotationTo").val(),
			  'billGroupCode.id':$("#billGroupCodeId").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/sales/quotes/projectQuotationSchemeAction!goList.action');
			}
		);
	}
}
function chooseSaleOrg(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action?canCheckComp=0',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择销售组织",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var resObj = $.parseJSON(returnValue);
								var uId = resObj[0].realId;
								$("#saleOrgId").val(uId);
								$("#saleOrgName").val(resObj[0].name);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
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
							}else{
								$("#customerAccountId").val("");
								$("#customerName").val("");
								asyncbox.success("<s:text name='pleaseChooseCustomerAccount'/>","<s:text name='vix_message'/>");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
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
								$("#salesManId").val(result[0]);
								$("#salesManName").val(result[1]);
							}else{
								$("#salesManId").val("");
								$("#salesManName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function addCustomerAccount(){
	$.ajax({
		  url:'${vix}/mdm/crm/customerAccountAction!goFastAddCustomerAccount.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 280,
					title:"添加客户",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if($(":radio[name=customerType][checked]").val() == 'enterPrise'){
								saveOrUpdateEnterPriceCustomerAccount();
							}else{
								saveOrUpdatePersonalCustomerAccount();
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function choosePqsOrgUnit(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择部门",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var resObj = $.parseJSON(returnValue);
								var uId = resObj[0].realId;
								$("#pqsOrganizationUnitId").val(uId);
								$("#pqsOrganizationUnitName").val(resObj[0].name);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
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
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="###"><img src="${vix}/common/img/sale/saleQuotes.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="###">销售管理</a></li>
				<li><a href="###">销售报价</a></li>
				<li><a href="###" onclick="loadContent('${vix}/sales/quotes/projectQuotationSchemeAction!goList.action');">项目式报价</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${projectQuotationScheme.id}" />
<input type="hidden" id="isDeleted" name="isDeleted" value="${projectQuotationScheme.isDeleted}" />
<input type="hidden" id="billGroupCodeId" name="billGroupCodeId" value="${billGroupCode.id}" />
<div class="content">
	<form id="projectQuotationSchemeForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateSalesQuotation();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/sales/quotes/projectQuotationSchemeAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="projectQuotationScheme.code != null ">
							${projectQuotationScheme.name}
						</s:if> <s:else>新增销售报价单</s:else>
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
									<table>
										<tr>
											<td align="right" width="15%">报价单编码:</td>
											<td width="35%"><input id="pqsCode" name="projectQuotationScheme.code" value="${projectQuotationScheme.code}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right" width="10%">主题:</td>
											<td width="40%"><input id="name" name="projectQuotationScheme.name" value="${projectQuotationScheme.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">客户名称:</td>
											<td><input id="customerName" name="projectQuotationScheme.customerAccount.name" value="${projectQuotationScheme.customerAccount.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="customerAccountId" name="customerAccountId"
												value="${projectQuotationScheme.customerAccount.id}" /> <span><a class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span> <span><a class="abtn" href="#" onclick="addCustomerAccount();"><span>新增</span></a></span></td>
											<td align="right">部门:</td>
											<td><input id="pqsOrganizationUnitName" name="projectQuotationScheme.organizationUnit.fullName" value="${projectQuotationScheme.organizationUnit.fullName}" type="text" /> <input type="hidden" id="pqsOrganizationUnitId" name="cpOrganizationUnitId" value="${projectQuotationScheme.organizationUnit.id}" /> <span><a class="abtn"
													href="#" onclick="choosePqsOrgUnit();"><span>选择</span></a></span></td>
										</tr>
										<tr>
											<td align="right">报价单类型:</td>
											<td><s:select id="typeId" headerKey="-1" headerValue="--请选择--" list="%{billsTypeList}" listValue="typeName" listKey="typeCode" value="%{projectQuotationScheme.type}" multiple="false" theme="simple"></s:select></td>
											<td align="right">地域:</td>
											<td><s:select id="regionalId" list="%{regionalList}" headerKey="-1" headerValue="--请选择--" listValue="name" listKey="id" value="%{projectQuotationScheme.regional.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">业务类型:</td>
											<td><input id="bizType" name="projectQuotationScheme.bizType" value="${projectQuotationScheme.bizType}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">单据类型:</td>
											<td><input id="formType" name="projectQuotationScheme.formType" value="${projectQuotationScheme.formType}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">销售组织:</td>
											<td><input type="hidden" id="saleOrgId" value="${projectQuotationScheme.salesOrg.id}" /> <input id="saleOrgName" name="salesOrder.saleOrg.fullName" value="${projectQuotationScheme.salesOrg.fullName}" type="text" size="30" /> <a class="abtn" href="#" onclick="chooseSaleOrg();"><span>选择</span></a></td>
											<td align="right">税率 :</td>
											<td><input id="tax" name="projectQuotationScheme.tax" value="${projectQuotationScheme.tax}" type="text" size="30" class="validate[required] text-input" />% 范围(1-100)<span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">单据日期:</td>
											<td colspan="3"><input type="hidden" id="bdString" value="<s:property value='formatDateToTimeString(projectQuotationScheme.billDate)'/>" /> <input id="billDate" name="projectQuotationScheme.billDate" value="<s:property value='formatDateToString(projectQuotationScheme.billDate)'/>" onfocus="showTime('billDate','yyyy-MM-dd HH:mm')"
												type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('billDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
					<div class="right_menu">
						<ul>
							<li class="current"><a href="###"><img alt="" src="img/mail.png">报价明细</a></li>
						</ul>
					</div>
					<div id="a1">
						<div id="left" style="height: 500px; width: 258px;">
							<div id="switch_box" class="switch_btn" style="top: 240px;"></div>
							<div id="tree_rootPQS" class="ztree" style="padding: 0;"></div>
							<script type="text/javascript">
							var zTree;			
							var setting = {
								async: {
									enable: true,
									url:"${vix}/sales/quotes/projectQuotationSchemeItemAction!findTreeToJson.action?projectQuotationSchemeId="+$("#id").val(),
									autoParam:["id", "name=n", "level=lv"]
								},
								callback: {
									onClick: onClick
								}
							};
							function onClick(event, treeId, treeNode, clickFlag) {
								$("#selectId").val(treeNode.id);
								$("#selectIdTreeId").val(treeNode.tId);
								pager("start","${vix}/sales/quotes/projectQuotationSchemeItemAction!goListContent.action?parentId="+treeNode.id+"&projectQuotationSchemeId="+$("#id").val(),"projectQuotationSchemeItem");
							}
							zTree = $.fn.zTree.init($("#tree_rootPQS"), setting);
							function saveOrUpdateProjectQuotationSchemeItem(id){
								$.ajax({
									  url:'${vix}/sales/quotes/projectQuotationSchemeItemAction!goSaveOrUpdate.action?id='+id,
									  cache: false,
									  success: function(html){
											asyncbox.open({
											 	modal:true,width : 920,height : 340,title:"项目式报价明细",html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#projectQuotationSchemeItemForm').validationEngine('validate')){
															$.post('${vix}/sales/quotes/projectQuotationSchemeItemAction!saveOrUpdate.action',
																 {'projectQuotationSchemeItem.id':$("#projectQuotationSchemeItemId").val(),
																  'projectQuotationSchemeItem.projectQuotationScheme.id':$("#id").val(),
															      'projectQuotationSchemeItem.quotationCharger.id':$("#quotationChargerId").val(),
																  'projectQuotationSchemeItem.item.id':$("#itemProjectQuotationSchemeItemId").val(),
																  'projectQuotationSchemeItem.name':$("#projectQuotationSchemeItemName").val(),
																  'projectQuotationSchemeItem.parentProjectQuotationSchemeItem.id':$("#parentProjectQuotationSchemeItemId").val(),
																  'projectQuotationSchemeItem.quotationDepartment':$("#quotationDepartment").val(),
																  'projectQuotationSchemeItem.amount':$("#pqsAmount").val(),
																  'projectQuotationSchemeItem.assitAmount':$("#assitAmount").val(),
																  'projectQuotationSchemeItem.unit':$("#unit").val(),
																  'projectQuotationSchemeItem.assitUnit':$("#assitUnit").val(),
																  'projectQuotationSchemeItem.unitExchange':$("#unitExchange").val(),
																  'projectQuotationSchemeItem.tax':$("#tax").val(),
																  'projectQuotationSchemeItem.taxAmount':$("#taxAmount").val(),
																  'projectQuotationSchemeItem.price':$("#price").val(),
																  'projectQuotationSchemeItem.discount':$("#discount").val(),
																  'projectQuotationSchemeItem.netPrice':$("#netPrice").val(),
																  'projectQuotationSchemeItem.taxPrice':$("#taxPrice").val()
																},
																function(result){
																	asyncbox.success(result,"<s:text name='vix_message'/>",function(){
																		zTree.reAsyncChildNodes(null, "refresh");
																		pager("start","${vix}/sales/quotes/projectQuotationSchemeItemAction!goListContent.action?projectQuotationSchemeId="+$("#id").val(),"projectQuotationSchemeItem");
																	});
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
							function projectQuotationSchemeItemOrderBy(orderField){
								loadQuoteSubject();
								var orderBy = $("#projectQuotationSchemeOrderBy").val();
								if(orderBy == 'desc'){
									orderBy = "asc";
								}else{
									orderBy = 'desc';
								}
								pager("start","${vix}/sales/quotes/projectQuotationSchemeItemAction!goListContent.action?projectQuotationSchemeId="+$("#id").val()+"&orderField="+orderField+"&orderBy="+orderBy ,'projectQuotationSchemeItem');
							}
							function projectQuotationSchemeItemPager(tag){
								pager(tag,"${vix}/sales/quotes/projectQuotationSchemeItemAction!goListContent.action?projectQuotationSchemeId="+$("#id").val(),'projectQuotationSchemeItem');
							}
							pager("start","${vix}/sales/quotes/projectQuotationSchemeItemAction!goListContent.action?projectQuotationSchemeId="+$("#id").val(),"projectQuotationSchemeItem");
						</script>
						</div>
						<div class="right_content">
							<div class="right_btn">
								<span><a onclick="saveOrUpdateProjectQuotationSchemeItem(0);" href="###"><img src="img/address_book.png" alt=""></a></span>
							</div>
							<div class="pagelist drop clearfix">
								<strong><s:text name="cmn_selected" />:<span id="selectProjectQuotationSchemeItemCount2">0</span></strong>
								<div>
									<span><a class="start" onclick="projectQuotationSchemeItemPager('start');"></a></span> <span><a class="previous" onclick="projectQuotationSchemeItemPager('previous');"></a></span> <em>(<b class="projectQuotationSchemeItemInfo"></b> <s:text name='cmn_to' /> <b class="projectQuotationSchemeItemTotalCount"></b>)
									</em> <span><a class="next" onclick="projectQuotationSchemeItemPager('next');"></a></span> <span><a class="end" onclick="projectQuotationSchemeItemPager('end');"></a></span>
								</div>
							</div>
							<div id="projectQuotationSchemeItem" class="table"></div>
							<div class="pagelist drop clearfix">
								<strong><s:text name="cmn_selected" />:<span id="selectProjectQuotationSchemeItemCount2">0</span></strong>
								<div>
									<span><a class="start" onclick="projectQuotationSchemeItemPager('start');"></a></span> <span><a class="previous" onclick="projectQuotationSchemeItemPager('previous');"></a></span> <em>(<b class="projectQuotationSchemeItemInfo"></b> <s:text name='cmn_to' /> <b class="projectQuotationSchemeItemTotalCount"></b>)
									</em> <span><a class="next" onclick="projectQuotationSchemeItemPager('next');"></a></span> <span><a class="end" onclick="projectQuotationSchemeItemPager('end');"></a></span>
								</div>
							</div>
						</div>
					</div>
				</dd>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td width="15%" align="right">报价人员:</td>
								<td width="35%"><input type="hidden" id="salesManId" value="${projectQuotationScheme.salesMan.id}" /> <input id="salesManName" name="projectQuotationScheme.salesMan.name" value="${projectQuotationScheme.salesMan.name}" type="text" size="30" /> <a class="abtn" href="###" onclick="chooseEmployee();"><span>选择</span></a></td>
								<td width="10%" align="right">金额:</td>
								<td width="40%"><input id="amount" name="amount" value="${projectQuotationScheme.amount}" type="text" size="30" /></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
