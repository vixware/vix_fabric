<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#priceConditionArea tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#priceConditionArea tr:even").addClass("alt");
function chooseAreaChannelDistributor(part) {
	$.ajax({
		url : '${vix}/drp/distributionSystemRelationShipAction!goChooseOrganization.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择分销商",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#channelDistributorAreaId"+part).val(result[0]);
						$("#channelDistributorAreaName"+part).val(result[1]);
					} else {
						$("#channelDistributorAreaId").val("");
						$("#channelDistributorAreaName").val("");
						asyncbox.success("请选择分销商信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
	});
}
function chooseItem(part){
	$.ajax({
		  url:'${vix}/mdm/item/saleItemPriceAction!goList.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 960,
					height : 540,
					title:"选择${vv:varView('vix_mdm_item')}",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != ''){
								var i = returnValue.split(",");
								$("#itemId"+part).val(i[0]);
								$("#itemName"+part).val(i[1]);
								$("#itemPrice"+part).html(i[2]);
							}else{
								asyncbox.success("请选择${vv:varView('vix_mdm_item')}!","<s:text name='vix_message'/>");
								return false;
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseSupplier(part) {
	$.ajax({
		url : '${vix}/inventory/inboundWarehouseAction!goChooseSupplier.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "选择供应商",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var rv = returnValue.split(",");
						$("#supplierId"+part).val(rv[0]);
						$("#supplierName"+part).val(rv[1]);
					} else {
						asyncbox.success("请选择供应商信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
	});
}
function chooseCustomerAccount(part){
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
								$("#customerAccountId"+part).val(result[0]);
								$("#customerName"+part).val(result[1]);
							}else{
								$("#customerAccountId").val("");
								$("#customerName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function chooseSaleOrg(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
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
function loadPriceConditionDetail(priceType){
	var url = '';
	if(priceType == 'price'){
		url = '${vix}/mdm/item/salePriceConditionPriceAreaAction!goListContent.action?id='+$("#id").val();
	}else{
		url = '${vix}/mdm/item/salePriceConditionCountAreaAction!goListContent.action?id='+$("#id").val();
	}
	$.ajax({
		  url:url,
		  cache: false,
		  success: function(html){
			  if(priceType == 'price'){
			  	  $("#priceConditionPriceArea").html(html);
			  }else{
				  $("#priceConditionCountArea").html(html);
			  }
		  }
	});
};
loadPriceConditionDetail('count');
function savePriceCondition(tag){
	if($('#priceConditionForm').validationEngine('validate')){
		$.post('${vix}/mdm/item/salePriceConditionAction!saveOrUpdate.action',
			 {'priceCondition.id':$("#id").val(),
			  'priceCondition.code':$("#code").val(),
			  'priceCondition.defaultTax':$("#defaultTax").val(),
			  'priceCondition.name':$("#name").val(),
			  'priceCondition.saleOrg.id':$("#saleOrgId").val(),
			  'priceCondition.currencyType.id':$("#currencyTypeId").val(),
			  'priceCondition.startEffectiveTime':$("#startEffectiveTime").val(),
			  'priceCondition.endEffectiveTime':$("#endEffectiveTime").val()
			},
			function(result){
				asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
					if(tag == '0'){
						loadContent('${vix}/mdm/item/salePriceConditionAction!goList.action');
					}else{
						$.ajax({
							  url:'${vix}/mdm/item/salePriceConditionAction!goSaveOrUpdate.action?id='+id,
							  cache: false,
							  success: function(html){
								  $("#mainContent").html(html);
							  }
						});
					}
				});
			}
		 );
	}
}
function saveOrUpdatePriceConditionDetail(id,priceType){
	var url = '';
	var title = '';
	if(priceType == 'price'){
		url = '${vix}/mdm/item/salePriceConditionPriceAreaAction!goSaveOrUpdate.action?id='+id;
		title = '定价价格区域';
	}else{
		url = '${vix}/mdm/item/salePriceConditionCountAreaAction!goSaveOrUpdate.action?id='+id;
		title = '定价数量区域';
	}
	$.ajax({
		  url:url,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 880,
					height : 440,
					title:title,
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#priceConditionAreaForm').validationEngine('validate')){
								var ct = $("#conditionType").val();
								var variableData = '';
								if(ct == 'purchaseFrameworkAgreement'){
									variableData = 'supplierId:' + $("#supplierIdPfa").val() + ',itemId:'+$('#itemIdPfg').val();
								}else if(ct =='saleFrameworkAgreement'){
									variableData = 'customerAccountId:' + $("#customerAccountIdSfa").val() + ',itemId:'+$('#itemIdSfa').val();
								}else if(ct =='customerAccountCategory'){
									variableData = 'regionalId:' + $("#regionalId").val() + ',customerAccountCategoryId:'+$("#customerAccountCategoryId").val()+ ',itemId:'+$('#itemIdCac').val();
								}else if(ct =='itemCategory'){
									variableData = 'regionalId:' + $("#regionalId").val() + ',itemCatalogId:'+$("#itemCatalogId").val();
								}else if(ct =='itemGroup'){
									if($("#itemGroupId").val() == ""){
										asyncbox.success("请选择物料组!","<s:text name='vix_message'/>");
										return false;
									}
									variableData = 'regionalId:' + $("#regionalId").val() +  ',itemGroupId:'+$("#itemGroupId").val();
								}else if(ct =='customerAccountGroup'){
									if($("#customerAccountGroupId").val() == ""){
										asyncbox.success("请选择用户组!","<s:text name='vix_message'/>");
										return false;
									}
									variableData = 'regionalId:' + $("#regionalId").val() +  ',customerAccountGroupId:'+$("#customerAccountGroupId").val();
								}else if(ct =='channelDistributor'){
									variableData = 'regionalId:' + $("#regionalId").val() +  ',channelDistributorId:'+$("#channelDistributorAreaIdCd").val() + ',itemId:'+$('#itemIdCd').val();
								}else if(ct =='supplier'){
									variableData = 'regionalId:' + $("#regionalId").val() +  ',supplierId:'+$("#supplierIdS").val() + ',itemId:'+$('#itemIdS').val();
								}else if(ct =='customerAccount'){
									variableData = 'regionalId:' + $("#regionalId").val() + ',customerAccountId:' + $("#customerAccountIdCa").val();
								}else if(ct =='item'){
									variableData = 'regionalId:' + $("#regionalId").val() + ',itemId:'+$('#itemIdI').val();
								}else if(ct =='customerAccountAndItem'){
									variableData = 'regionalId:' + $("#regionalId").val() + ',customerAccountId:' + $("#customerAccountIdCaai").val() + ',itemId:'+$('#itemIdCaai').val();
								}
								if(priceType == 'price'){
									$.post('${vix}/mdm/item/salePriceConditionPriceAreaAction!saveOrUpdate.action',
											 {'priceConditionPriceArea.id':$("#pcpaId").val(),
											  'priceConditionPriceArea.conditionType':ct,
											  'variableData':variableData,
											  'priceConditionPriceArea.priceCondition.id':$("#id").val(),
											  'priceConditionPriceArea.startTotalAmount':$("#startTotalAmount").val(),
											  'priceConditionPriceArea.endTotalAmount':$("#endTotalAmount").val(),
											  'priceConditionPriceArea.areaPriceType':$(":radio[name=areaPriceType][checked]").val(),
											  'priceConditionPriceArea.refund':$("#refund").val(),
											  'priceConditionPriceArea.discount':$("#discount").val(),
											  'priceConditionPriceArea.memo':$("#memo").val()
											},
											function(result){
												asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
													loadPriceConditionDetail('price');
												});
											}
										 );
								}else{
									$.post('${vix}/mdm/item/salePriceConditionCountAreaAction!saveOrUpdate.action',
											 {'priceConditionCountArea.id':$("#pccaId").val(),
											  'priceConditionCountArea.conditionType':ct,
											  'variableData':variableData,
											  'priceConditionCountArea.priceCondition.id':$("#id").val(),
											  'priceConditionCountArea.startCount':$("#startCount").val(),
											  'priceConditionCountArea.endCount':$("#endCount").val(),
											  'priceConditionCountArea.areaPriceType':$(":radio[name=areaPriceType][checked]").val(),
											  'priceConditionCountArea.unit':$("#unit").val(),
											  'priceConditionCountArea.price':$("#price").val(),
											  'priceConditionCountArea.discount':$("#discount").val(),
											  'priceConditionCountArea.memo':$("#memo").val()
											},
											function(result){
												asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
													loadPriceConditionDetail('count');
												});
											}
										 ); 
								}
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
function changePriceConditionType(){
	var type = $("#conditionType").val();
	if(type == 'purchaseFrameworkAgreement'){
		$("td[name=regionalTbody]").attr('style','display:none;');
		$("tr[id=purchaseFrameworkAgreementTbody]").attr('style','display:;');
		$("tr[id=saleFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=customerAccountCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemGroupTbody]").attr('style','display:none;');
		$("tr[id=customerAccountGroupTbody]").attr('style','display:none;');
		$("tr[id=channelDistributorTbody]").attr('style','display:none;');
		$("tr[id=customerAccountTbody]").attr('style','display:none;');
		$("tr[id=itemTbody]").attr('style','display:none;');
		$("tr[id=customerAccountAndItemTbody]").attr('style','display:none;');
		$("tr[id=supplierTbody]").attr('style','display:none;');
		$("tr[id=priceTypeTbody]").attr('style','display:none;');
		$("#price").val("");
	}else if(type == 'saleFrameworkAgreement'){
		$("td[name=regionalTbody]").attr('style','display:none;');
		$("tr[id=purchaseFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=saleFrameworkAgreementTbody]").attr('style','display:;');
		$("tr[id=customerAccountCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemGroupTbody]").attr('style','display:none;');
		$("tr[id=customerAccountGroupTbody]").attr('style','display:none;');
		$("tr[id=channelDistributorTbody]").attr('style','display:none;');
		$("tr[id=customerAccountTbody]").attr('style','display:none;');
		$("tr[id=itemTbody]").attr('style','display:none;');
		$("tr[id=customerAccountAndItemTbody]").attr('style','display:none;');
		$("tr[id=supplierTbody]").attr('style','display:none;');
		$("tr[id=priceTypeTbody]").attr('style','display:none;');
		$("#price").val("");
	}else if(type == 'customerAccountCategory'){
		$("td[name=regionalTbody]").attr('style','display:;');
		$("tr[id=purchaseFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=saleFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=customerAccountCategoryTbody]").attr('style','display:;');
		$("tr[id=itemCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemGroupTbody]").attr('style','display:none;');
		$("tr[id=customerAccountGroupTbody]").attr('style','display:none;');
		$("tr[id=channelDistributorTbody]").attr('style','display:none;');
		$("tr[id=customerAccountTbody]").attr('style','display:none;');
		$("tr[id=itemTbody]").attr('style','display:none;');
		$("tr[id=customerAccountAndItemTbody]").attr('style','display:none;');
		$("tr[id=supplierTbody]").attr('style','display:none;');
		$("tr[id=priceTypeTbody]").attr('style','display:none;');
		$("#price").val("");
	}else if(type == 'itemCategory'){
		$("td[name=regionalTbody]").attr('style','display:;');
		$("tr[id=purchaseFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=saleFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=customerAccountCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemCategoryTbody]").attr('style','display:;');
		$("tr[id=itemGroupTbody]").attr('style','display:none;');
		$("tr[id=customerAccountGroupTbody]").attr('style','display:none;');
		$("tr[id=channelDistributorTbody]").attr('style','display:none;');
		$("tr[id=customerAccountTbody]").attr('style','display:none;');
		$("tr[id=itemTbody]").attr('style','display:none;');
		$("tr[id=customerAccountAndItemTbody]").attr('style','display:none;');
		$("tr[id=supplierTbody]").attr('style','display:none;');
		$("tr[id=priceTypeTbody]").attr('style','display:none;');
		$("#price").val("");
	}else if(type == 'itemGroup'){
		$("td[name=regionalTbody]").attr('style','display:;');
		$("tr[id=purchaseFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=saleFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=customerAccountCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemGroupTbody]").attr('style','display:;');
		$("tr[id=customerAccountGroupTbody]").attr('style','display:none;');
		$("tr[id=channelDistributorTbody]").attr('style','display:none;');
		$("tr[id=customerAccountTbody]").attr('style','display:none;');
		$("tr[id=itemTbody]").attr('style','display:none;');
		$("tr[id=customerAccountAndItemTbody]").attr('style','display:none;');
		$("tr[id=supplierTbody]").attr('style','display:none;');
		$("tr[id=priceTypeTbody]").attr('style','display:none;');
		$("#price").val("");
	}else if(type == 'channelDistributor'){
		$("td[name=regionalTbody]").attr('style','display:;');
		$("tr[id=purchaseFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=saleFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=customerAccountCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemGroupTbody]").attr('style','display:none;');
		$("tr[id=customerAccountGroupTbody]").attr('style','display:none;');
		$("tr[id=channelDistributorTbody]").attr('style','display:;');
		$("tr[id=customerAccountTbody]").attr('style','display:none;');
		$("tr[id=itemTbody]").attr('style','display:none;');
		$("tr[id=customerAccountAndItemTbody]").attr('style','display:none;');
		$("tr[id=supplierTbody]").attr('style','display:none;');
		$("tr[id=priceTypeTbody]").attr('style','display:none;');
		$("#price").val("");
	}else if(type == 'customerAccount'){
		$("td[name=regionalTbody]").attr('style','display:;');
		$("tr[id=purchaseFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=saleFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=customerAccountCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemGroupTbody]").attr('style','display:none;');
		$("tr[id=customerAccountGroupTbody]").attr('style','display:none;');
		$("tr[id=channelDistributorTbody]").attr('style','display:none;');
		$("tr[id=customerAccountTbody]").attr('style','display:;');
		$("tr[id=itemTbody]").attr('style','display:none;');
		$("tr[id=customerAccountAndItemTbody]").attr('style','display:none;');
		$("tr[id=supplierTbody]").attr('style','display:none;');
		$("tr[id=priceTypeTbody]").attr('style','display:none;');
		$("#price").val("");
	}else if(type == 'item'){
		$("td[name=regionalTbody]").attr('style','display:;');
		$("tr[id=purchaseFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=saleFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=customerAccountCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemGroupTbody]").attr('style','display:none;');
		$("tr[id=customerAccountGroupTbody]").attr('style','display:none;');
		$("tr[id=channelDistributorTbody]").attr('style','display:none;');
		$("tr[id=customerAccountTbody]").attr('style','display:none;');
		$("tr[id=itemTbody]").attr('style','display:;');
		$("tr[id=customerAccountAndItemTbody]").attr('style','display:none;');
		$("tr[id=supplierTbody]").attr('style','display:none;');
		$("tr[id=priceTypeTbody]").attr('style','display:;');
	}else if(type == 'customerAccountAndItem'){
		$("td[name=regionalTbody]").attr('style','display:;');
		$("tr[id=purchaseFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=saleFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=customerAccountCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemGroupTbody]").attr('style','display:none;');
		$("tr[id=customerAccountGroupTbody]").attr('style','display:none;');
		$("tr[id=channelDistributorTbody]").attr('style','display:none;');
		$("tr[id=customerAccountTbody]").attr('style','display:none;');
		$("tr[id=itemTbody]").attr('style','display:none;');
		$("tr[id=customerAccountAndItemTbody]").attr('style','display:;');
		$("tr[id=supplierTbody]").attr('style','display:none;');
		$("tr[id=priceTypeTbody]").attr('style','display:;');
	}else if(type == 'supplier'){
		$("td[name=regionalTbody]").attr('style','display:;');
		$("tr[id=purchaseFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=saleFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=customerAccountCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemGroupTbody]").attr('style','display:none;');
		$("tr[id=customerAccountGroupTbody]").attr('style','display:none;');
		$("tr[id=channelDistributorTbody]").attr('style','display:none;');
		$("tr[id=customerAccountTbody]").attr('style','display:none;');
		$("tr[id=itemTbody]").attr('style','display:none;');
		$("tr[id=customerAccountAndItemTbody]").attr('style','display:none;');
		$("tr[id=supplierTbody]").attr('style','display:;');
		$("tr[id=priceTypeTbody]").attr('style','display:none;');
		$("#price").val("");
	}else if(type == 'customerAccountGroup'){
		$("td[name=regionalTbody]").attr('style','display:;');
		$("tr[id=purchaseFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=saleFrameworkAgreementTbody]").attr('style','display:none;');
		$("tr[id=customerAccountCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemCategoryTbody]").attr('style','display:none;');
		$("tr[id=itemGroupTbody]").attr('style','display:none;');
		$("tr[id=customerAccountGroupTbody]").attr('style','display:;');
		$("tr[id=channelDistributorTbody]").attr('style','display:none;');
		$("tr[id=customerAccountTbody]").attr('style','display:none;');
		$("tr[id=itemTbody]").attr('style','display:none;');
		$("tr[id=customerAccountAndItemTbody]").attr('style','display:none;');
		$("tr[id=supplierTbody]").attr('style','display:none;');
		$("tr[id=priceTypeTbody]").attr('style','display:none;');
		$("#price").val("");
	}
}
function deletePriceConditionEntity(id,priceType){
	var url = '';
	if(priceType == 'price'){
		url = '${vix}/mdm/item/salePriceConditionPriceAreaAction!deleteById.action?id='+id;
	}else{
		url = '${vix}/mdm/item/salePriceConditionCountAreaAction!deleteById.action?id='+id;
	}
	asyncbox.confirm('确定要删除该定价条件么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:url,
				  cache: false,
				  success: function(html){
					asyncbox.success(html,"提示信息",function(action){
						loadPriceConditionDetail(priceType);
					});
				  }
			});
		}
	});
}
//面包屑
if($('.sub_menu').length)
{
	$("#breadCrumb").jBreadCrumb();
}
$("#priceConditionForm").validationEngine();
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
				<li><a href="#"><img src="${vix}/common/img/mdm/item.png" alt="" />系统管理</a></li>
				<li><a href="#">物料管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mdm/item/salePriceConditionAction!goList.action');">${vv:varView('vix_mdm_item')}销售定价</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="order">
		<dl>
			<dt id="orderTitleFixd">
				<span class="no_line"> <a onclick="savePriceCondition('0');" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a onclick="savePriceCondition('1');" href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
					onclick="loadContent('${vix}/mdm/item/salePriceConditionAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
				</span> <strong> <b> <s:if test="priceCondition.name != null">${priceCondition.name}</s:if> <s:else>新增${vv:varView('vix_mdm_item')}销售定价</s:else>
				</b> <i></i>
				</strong>
			</dt>
			<dd class="clearfix">
				<div class="order_table">
					<div class="voucher newvoucher">
						<dl>
							<dt>
								<b></b> <strong>${vv:varView('vix_mdm_item')}销售定价</strong>
							</dt>
							<dd style="display: block;">
								<form id="priceConditionForm">
									<div class="box order_table" style="line-height: normal;">
										<table>
											<s:hidden id="id" name="priceCondition.id" value="%{priceCondition.id}" theme="simple" />
											<tr height="30">
												<td align="right">销售组织:&nbsp;</td>
												<td colspan="3"><input type="hidden" id="saleOrgId" value="${priceCondition.saleOrg.id}" /> <input id="saleOrgName" name="priceCondition.saleOrg.fullName" value="${priceCondition.saleOrg.fullName}" type="text" class="validate[required] text-input" /> <a class="abtn" href="#" onclick="chooseSaleOrg();"><span>选择</span></a></td>
											</tr>
											<tr height="30">
												<td align="right">编码:&nbsp;</td>
												<td><input id="code" name="priceCondition.code" value="${priceCondition.code}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
												<td align="right">主题:&nbsp;</td>
												<td><input id="name" name="priceCondition.name" value="${priceCondition.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											</tr>
											<tr height="30">
												<td align="right">税率:&nbsp;</td>
												<td><input id="defaultTax" name="priceCondition.defaultTax" value="${priceCondition.defaultTax}" class="validate[required] text-input" />% 范围(100)<span style="color: red;">*</span></td>
												<td align="right">币种:&nbsp;</td>
												<td><s:select id="currencyTypeId" headerKey="" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="%{priceCondition.currencyType.id}" multiple="false" theme="simple"></s:select></td>
											</tr>
											<tr height="30">
												<td align="right">开始时间:&nbsp;</td>
												<td><input type="text" id="startEffectiveTime" name="priceCondition.startEffectiveTime" value="<s:property value='formatDateToTimeString(priceCondition.startEffectiveTime)'/>" onfocus="showTime('startEffectiveTime','yyyy-MM-dd HH:mm')" class="validate[required] text-input" /><span style="color: red;">*</span> <img
													onclick="showTime('startEffectiveTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
												<td align="right">结束时间:&nbsp;</td>
												<td><input type="text" id="endEffectiveTime" name="priceCondition.endEffectiveTime" value="<s:property value='formatDateToTimeString(priceCondition.endEffectiveTime)'/>" onfocus="showTime('endEffectiveTime','yyyy-MM-dd HH:mm')" class="validate[required] text-input" /><span style="color: red;">*</span> <img
													onclick="showTime('endEffectiveTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											</tr>
										</table>
									</div>
									<div class="clearfix" style="background: #FFF; position: relative; margin: 0 7px;">
										<div class="right_menu">
											<ul>
												<li class="current"><a onclick="javascript:tab(2,1,'a',event);loadPriceConditionDetail('count');"><img alt="" src="img/mail.png">数量区间</a></li>
												<li class=""><a onclick="javascript:tab(2,2,'a',event);loadPriceConditionDetail('price');"><img alt="" src="img/mail.png">价格区间</a></li>
											</ul>
										</div>
										<div id="a1" class="right_content" style="display: block;">
											<div class="list_table" style="margin: 0; width: 100%">
												<p>
													<a class="abtn" href="###" onclick="saveOrUpdatePriceConditionDetail(0,'count');"><span style="width: 55px;">添加明细</span></a>
												</p>
											</div>
											<div id="priceConditionCountArea" class="list_table" style="margin: 0; width: 100%"></div>
										</div>
										<div id="a2" class="right_content" style="display: none;">
											<div class="list_table" style="margin: 0; width: 100%">
												<p>
													<a class="abtn" href="###" onclick="saveOrUpdatePriceConditionDetail(0,'price');"><span style="width: 55px;">添加明细</span></a>
												</p>
											</div>
											<div id="priceConditionPriceArea" class="list_table" style="margin: 0; width: 100%"></div>
										</div>
									</div>
								</form>
							</dd>
						</dl>
					</div>
				</div>
			</dd>
		</dl>
	</div>
</div>