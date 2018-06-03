<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
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
 
$("#projectQuotationTemplateForm").validationEngine();
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
function saveOrUpdateProjectQuotationTemplate(){
	if($('#projectQuotationTemplateForm').validationEngine('validate')){
		$.post('${vix}/sales/quotes/projectQuotationTemplateAction!saveOrUpdate.action',
			{'projectQuotationTemplate.id':$("#id").val(),
			  'projectQuotationTemplate.code':$("#pqsCode").val(),
			  'projectQuotationTemplate.name':$("#name").val(),
			  'projectQuotationTemplate.amount':$("#amount").val(),
			  'projectQuotationTemplate.bizType':$("#bizType").val(),
			  'projectQuotationTemplate.formType':$("#formType").val(),
			  'projectQuotationTemplate.tax':$("#tax").val(),
			  'projectQuotationTemplate.currency':$("#currency").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/sales/quotes/projectQuotationTemplateAction!goList.action');
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
function showPrice(itemId,count,customerAccount){
	$.ajax({
		  url:'${vix}/mdm/item/saleItemPriceAction!goFixedPrice.action?id='+itemId+"&count="+count+"&billCreateDate="+$("#billDate").val()+"&customerAccountId="+customerAccount,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 580,
					title:"定价",
					html:html,
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


function saveOrUpdateProjectQuotationTemplateItem(id){
	var id = $("#projectQuotationTemplateItemId").val();
	$.ajax({
		  url:'${vix}/sales/quotes/projectQuotationTemplateItemAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
				asyncbox.open({
				 	modal:true,width : 920,height : 340,title:"项目式报价明细",html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#projectQuotationTemplateItemForm').validationEngine('validate')){
								$.post('${vix}/sales/quotes/projectQuotationTemplateItemAction!saveOrUpdate.action',
									 {'projectQuotationTemplateItem.id':$("#projectQuotationTemplateItemId").val(),
									  'projectQuotationTemplateItem.projectQuotationTemplate.id':id,
									  'projectQuotationTemplateItem.item.id':$("#itemProjectQuotationTemplateItemId").val(),
									  'projectQuotationTemplateItem.name':$("#projectQuotationTemplateItemName").val(),
									  'projectQuotationTemplateItem.parentProjectQuotationTemplateItem.id':$("#parentProjectQuotationTemplateItemId").val(),
									  'projectQuotationTemplateItem.amount':$("#projectQuotationTemplateItemAmount").val(),
									  'projectQuotationTemplateItem.assitAmount':$("#assitAmount").val(),
									  'projectQuotationTemplateItem.unit':$("#unit").val(),
									  'projectQuotationTemplateItem.assitUnit':$("#assitUnit").val(),
									  'projectQuotationTemplateItem.unitExchange':$("#unitExchange").val(),
									  'projectQuotationTemplateItem.tax':$("#projectQuotationTemplateItemTax").val(),
									  'projectQuotationTemplateItem.price':$("#projectQuotationTemplateItemPrice").val(),
									  'projectQuotationTemplateItem.measureUnitGroup.id':$("#measureUnitGroupId").val(),
									  'projectQuotationTemplateItem.measureUnit.id':$("#measureUnitId").val(),
									  'projectQuotationTemplateItem.assitMeasureUnit.id':$("#assitMeasureUnitId").val(),
									  'projectQuotationTemplateItem.discount':$("#projectQuotationTemplateItemDiscount").val()
									},
									function(result){
										asyncbox.success(result,"<s:text name='vix_message'/>",function(){
											zTree.reAsyncChildNodes(null, "refresh");
											pager("start","${vix}/sales/quotes/projectQuotationTemplateItemAction!goListContent.action?projectQuotationTemplateId="+$("#id").val(),"projectQuotationTemplateItem");
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

function projectQuotationTemplateItemOrderBy(orderField){
	loadQuoteSubject();
	var orderBy = $("#projectQuotationTemplateOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/sales/quotes/projectQuotationTemplateItemAction!goListContent.action?projectQuotationTemplateId="+$("#id").val()+"&orderField="+orderField+"&orderBy="+orderBy ,'projectQuotationTemplateItem');
}
function projectQuotationTemplateItemPager(tag){
	pager(tag,"${vix}/sales/quotes/projectQuotationTemplateItemAction!goListContent.action?projectQuotationTemplateId="+$("#id").val(),'projectQuotationTemplateItem');
}
pager("start","${vix}/sales/quotes/projectQuotationTemplateItemAction!goListContent.action?projectQuotationTemplateId="+$("#id").val(),"projectQuotationTemplateItem");

</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sale/saleQuotes.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#">销售报价</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/quotes/projectQuotationTemplateAction!goList.action');">项目式报价模板</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${projectQuotationTemplate.id}" />
<div class="content">
	<form id="projectQuotationTemplateForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateProjectQuotationTemplate();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/sales/quotes/projectQuotationTemplateAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="projectQuotationTemplate.code != null ">
							${projectQuotationTemplate.name}
						</s:if> <s:else>
							新增项目式报价模板
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
									<table>
										<tr>
											<td align="right" width="15%">报价单编码:</td>
											<td width="35%"><input id="pqsCode" name="projectQuotationTemplate.code" value="${projectQuotationTemplate.code}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right" width="10%">主题:</td>
											<td width="40%"><input id="name" name="projectQuotationTemplate.name" value="${projectQuotationTemplate.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">币种:</td>
											<td><input id="currency" name="projectQuotationTemplate.currency" value="${projectQuotationTemplate.currency}" type="text" size="30" /></td>
											<td align="right">税率 :</td>
											<td><input id="tax" name="projectQuotationTemplate.tax" value="${projectQuotationTemplate.tax}" type="text" size="30" />% 范围(1-100)</td>
										</tr>
										<tr>
											<td align="right">业务类型:</td>
											<td><input id="bizType" name="projectQuotationTemplate.bizType" value="${projectQuotationTemplate.bizType}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">单据类型:</td>
											<td><input id="formType" name="projectQuotationTemplate.formType" value="${projectQuotationTemplate.formType}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(1,1,'a',event)"><img alt="" src="img/mail.png">报价明细</a></li>
						</ul>
					</div>
					<div id="a1">
						<div id="left" style="height: 500px; width: 258px;">
							<div id="switch_box" class="switch_btn" style="top: 240px;"></div>
							<div class="left_content" style="height: 500px;">
								<div id="tree_rootPQS" class="ztree" style="padding: 0; padding-top: 10px;"></div>
							</div>
							<script type="text/javascript">
							//debugger;
							var zTree;			
							var setting = {
								async: {
									enable: true,
									url:"${vix}/sales/quotes/projectQuotationTemplateItemAction!findTreeToJson.action?projectQuotationTemplateId="+$("#id").val(),
									autoParam:["id", "name=n", "level=lv"]
								},
								callback: {
									onClick: onClick
								}
							};
							function onClick(event, treeId, treeNode, clickFlag) {
								$("#selectId").val(treeNode.id);
								$("#selectIdTreeId").val(treeNode.tId);
								pager("start","${vix}/sales/quotes/projectQuotationTemplateItemAction!goListContent.action?parentId="+treeNode.id+"&projectQuotationTemplateId="+$("#id").val(),"projectQuotationTemplateItem");
							}
							
							zTree = $.fn.zTree.init($("#tree_rootPQS"), setting);
							
						</script>
						</div>
						<div class="right_content">
							<div class="right_btn">
								<li class="current"><span><a onclick="saveOrUpdateProjectQuotationTemplateItem();" href="###"><img src="img/mail.png" alt="">添加明细</a></span></li>
							</div>
							<div class="pagelist drop clearfix">
								<strong><s:text name="cmn_selected" />:<span id="selectProjectQuotationTemplateItemCount2">0</span></strong>
								<div>
									<span><a class="start" onclick="projectQuotationTemplateItemPager('start');"></a></span> <span><a class="previous" onclick="projectQuotationTemplateItemPager('previous');"></a></span> <em>(<b class="projectQuotationTemplateItemInfo"></b> <s:text name='cmn_to' /> <b class="projectQuotationTemplateItemTotalCount"></b>)
									</em> <span><a class="next" onclick="projectQuotationTemplateItemPager('next');"></a></span> <span><a class="end" onclick="projectQuotationTemplateItemPager('end');"></a></span>
								</div>
							</div>
							<div id="projectQuotationTemplateItem" class="table"></div>
							<div class="pagelist drop clearfix">
								<strong><s:text name="cmn_selected" />:<span id="selectProjectQuotationTemplateItemCount2">0</span></strong>
								<div>
									<span><a class="start" onclick="projectQuotationTemplateItemPager('start');"></a></span> <span><a class="previous" onclick="projectQuotationTemplateItemPager('previous');"></a></span> <em>(<b class="projectQuotationTemplateItemInfo"></b> <s:text name='cmn_to' /> <b class="projectQuotationTemplateItemTotalCount"></b>)
									</em> <span><a class="next" onclick="projectQuotationTemplateItemPager('next');"></a></span> <span><a class="end" onclick="projectQuotationTemplateItemPager('end');"></a></span>
								</div>
							</div>
						</div>
					</div>
				</dd>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td width="10%" align="right">金额:</td>
								<td colspan="3"><input id="amount" name="amount" value="${projectQuotationTemplate.amount}" type="text" size="30" /></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
