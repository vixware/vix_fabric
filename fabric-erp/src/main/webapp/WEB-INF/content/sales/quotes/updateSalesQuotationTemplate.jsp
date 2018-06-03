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
 
$("#salesQuotationTemplateForm").validationEngine();
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
function saveOrUpdateSalesQuotationTemplate(){
	if($('#salesQuotationTemplateForm').validationEngine('validate')){
		$.post('${vix}/sales/quotes/salesQuotationTemplateAction!saveOrUpdate.action',
			{'salesQuotationTemplate.id':$("#id").val(),
			  'salesQuotationTemplate.code':$("#code").val(),
			  'salesQuotationTemplate.quoteSubject':$("#quoteSubject").val(),
			  'salesQuotationTemplate.amount':$("#amount").val(),
			  'salesQuotationTemplate.bizType':$("#bizType").val(),
			  'salesQuotationTemplate.formType':$("#formType").val(),
			  'salesQuotationTemplate.tax':$("#tax").val(),
			  'salesQuotationTemplate.version':$("#version").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/sales/quotes/salesQuotationTemplateAction!goList.action');
			}
		);
	}
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
function showPrice(itemId,count){
	$.ajax({
		  url:'${vix}/mdm/item/saleItemPriceAction!goFixedPrice.action?id='+itemId+"&count="+count+"&billCreateDate="+$("#billDate").val(),
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
				<li><a href="#"><img src="${vix}/common/img/sale/saleQuotes.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#">销售报价</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/quotes/salesQuotationTemplateAction!goList.action');">销售报价单模板</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${salesQuotationTemplate.id}" />
<div class="content">
	<form id="salesQuotationTemplateForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateSalesQuotationTemplate();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/sales/quotes/salesQuotationTemplateAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="salesQuotationTemplate.code != null ">
							${salesQuotationTemplate.quoteSubject}
						</s:if> <s:else>
							新增销售报价单模板
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
											<td width="35%"><input id="code" name="salesQuotationTemplate.code" value="${salesQuotationTemplate.code}" type="text" size="30" /></td>

											<td align="right" width="10%">报价主题:</td>
											<td width="40%"><input id="quoteSubject" name="salesQuotationTemplate.quoteSubject" value="${salesQuotationTemplate.quoteSubject}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">业务类型:</td>
											<td><input id="bizType" name="salesQuotationTemplate.bizType" value="${salesQuotationTemplate.bizType}" type="text" size="30" /></td>
											<td align="right">单据类型:</td>
											<td><input id="formType" name="salesQuotationTemplate.formType" value="${salesQuotationTemplate.formType}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">税率 :</td>
											<td><input id="tax" name="salesQuotationTemplate.tax" value="${salesQuotationTemplate.tax}" type="text" size="30" />% 范围(1-100)</td>
											<td align="right">版本:</td>
											<td><input id="version" name="salesQuotationTemplate.version" value="${salesQuotationTemplate.version}" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:$('#a1').attr('style','');tab(5,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />报价单明细模板</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="salesQuotationTemplateItem" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="fitColumns:true,singleSelect: true,toolbar: '#salesQuotationTemplateItemTb',url: '${vix}/sales/quotes/salesQuotationTemplateAction!getSalesQuotationTemplateItemJson.action?id=${salesQuotationTemplate.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60">编号</th>
										<th data-options="field:'item.id'"></th>
										<th data-options="field:'item.code',width:150,align:'center'">编码</th>
										<th data-options="field:'item.name',width:120,align:'center'">名称</th>
										<th data-options="field:'unit',width:120,align:'center'">计量单位</th>
										<th data-options="field:'amount',width:120,align:'center'">数量</th>
										<th data-options="field:'price',width:120,align:'center'">价格</th>
										<th data-options="field:'taxPrice',width:120,align:'center'">含税价格</th>
									</tr>
								</thead>
							</table>
							<div id="salesQuotationTemplateItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="updateItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-edit',plain:true" onclick="showItemPrice()"><span
									class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">定价</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text
												name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#salesQuotationTemplateItem').datagrid();
							$('#salesQuotationTemplateItem').datagrid('hideColumn','item.id');
							function removeItem(){
								var row = $('#salesQuotationTemplateItem').datagrid('getSelected');
								if(row){
									asyncbox.confirm('是否删除该报价单明细模板?','提示信息',function(action){
										if(action == 'ok'){
											var index = $('#salesQuotationTemplateItem').datagrid('getRowIndex',row);
											$('#salesQuotationTemplateItem').datagrid('deleteRow', index);
											$.ajax({
												  url:'${vix}/sales/quotes/salesQuotationTemplateAction!deleteSalesQuotationTemplateItemById.action?id='+row.id,
												  cache: false,
												  success: function(html){
													  showMessage(html);
												  }
											});
										}
									});
								}else{
									showMessage("请选择一条数据!");
								}
							}
							function showItemPrice(){
								var row = $('#salesQuotationTemplateItem').datagrid('getSelected');
								if(row){
									var itemId = row.item.id;
									var count = row.amount;
									showPrice(itemId,count);
								}else{
									showMessage("请选择一条数据!");
								}
							}
							
							function updateItem(){
								var row = $('#salesQuotationTemplateItem').datagrid('getSelected');
								if(row){
									addItem(row.id);
								}else{
									showMessage("请选择一条数据!");
								}
							}
							function addItem(id){
								$.ajax({
									  url:'${vix}/sales/quotes/salesQuotationTemplateAction!goSaveOrUpdateSalesQuotationTemplateItem.action?id='+id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 660,
												height : 240,
												title:"添加${vv:varView('vix_mdm_item')}",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#salesQuotationTemplateItemForm').validationEngine('validate')){
															$.post('${vix}/sales/quotes/salesQuotationTemplateAction!saveOrUpdateSalesQuotationTemplateItem.action',
																	{'salesQuotationTemplateItem.id':$("#sqId").val(),
																	  'salesQuotationTemplateItem.item.id':$("#itemId").val(),
																	  'salesQuotationTemplateItem.price':$("#price").val(),
																	  'salesQuotationTemplateItem.amount':$("#sqiAmount").val(),
																	  'salesQuotationTemplateItem.salesQuotationTemplate.id':$("#id").val()
																	},
																	function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#salesQuotationTemplateItem').datagrid("reload");
																		$.ajax({
																			  url:'${vix}/sales/quotes/salesQuotationTemplateAction!getSalesQuotationTemplateItemTotal.action?id='+$("#id").val(),
																			  cache: false,
																			  success: function(json){
																				  $("#amount").val(json);
																			  }
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
					</script>
						</div>
					</div>
				</div>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td width="10%" align="right">金额</td>
								<td width="90%"><input id="amount" name="amount" value="${salesQuotationTemplate.amount}" type="text" size="30" /></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
