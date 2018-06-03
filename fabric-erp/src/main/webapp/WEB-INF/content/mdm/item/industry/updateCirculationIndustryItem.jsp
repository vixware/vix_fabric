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
	//id为productCategory的id
	function generateSpecification(){
		var objectExpandId = $("#objectExpandId").val();
		if(objectExpandId == ''){
			asyncbox.success("请选择对象类型信息!","<s:text name='snow_message'/>");
			return;
		}
		$.ajax({
			url:'${vix}/system/specificationAction!chooseSpecification.action?id='+objectExpandId,
			cache: false,
			success: function(html){
				asyncbox.open({
					modal:true,
					width : 420,
					height : 280,
					title:"对象规格",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							var specificationDetailIds = "";
							var specIds = "";
							$("input[id^='specification_']").each(function(){
								var sId = $(this).attr("id").split('_')[1];
								if(specIds.indexOf(sId +",") == -1){
									specIds =specIds + sId +",";
								}
							});
							var specIdsArray = specIds.split(",");
							for(var i=0;i<specIdsArray.length;i++){
								if(specIdsArray[i] == ""){
									continue;
								}
								var specDetailIds = "";
								$("input[name^='specificationDetail_"+specIdsArray[i]+"_']").each(function(){
									if($(this).attr("checked") == "checked"){
										specDetailIds = specDetailIds + $(this).attr("value")+",";
									}
								});
								if(specDetailIds == ""){
									specificationDetailIds = specificationDetailIds +  "0,:";
								}else{
									specificationDetailIds = specificationDetailIds +  specDetailIds + ":";
								}
							}
							if(specificationDetailIds == ''){
								return;
							}
							var containsSpecDetail = getContainsSpecDetail();
							$.ajax({
								url:'${vix}/mdm/item/fastAddItemAction!generateSpecificationTable.action?specificationDetailIds='+specificationDetailIds+"&objectExpandId="+$("#objectExpandId").val()+"&containsSpecDetail="+containsSpecDetail,
								cache: false,
								success: function(html){
									$("#specTable tbody").append(html);
									$("#specTable tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
									$("#specTable tr:even").addClass("alt");
									$("#itemForm").validationEngine();
								}
							});
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
			}
		});
	}
$("#itemForm").validationEngine();
function saveOrUpdateFastAddItem(){
	if($('#itemForm').validationEngine('validate')){
		var appendFieldValue = "";
		$("input[type=hidden][id^='append_']").each(function(){
			appendFieldValue = appendFieldValue + $(this).attr("name") +":" + $(this).val()+",";
		});
		$("input[type=text][id^='append_']").each(function(){
			appendFieldValue = appendFieldValue + $(this).attr("name") +":" + $(this).val()+",";
		});
		$("select[id^='append_']").each(function(){
			appendFieldValue = appendFieldValue + $(this).attr("name") +":" + $(this).val()+",";
		});
		var checkboxName = ",";
		$("input[type=checkbox][id^='append_']").each(function(){
			var name = $(this).attr("name");
			if(checkboxName.indexOf(name) == -1){
				checkboxName += name+",";
			}
		});
		var cns = checkboxName.split(",");
		for(var i = 0;i<cns.length;i++){
			if(cns[i] != ''){
				var vs = "_";
				$("input[type=checkbox][id^='append_"+cns[i]+"']:checked").each(function(){
					vs += $(this).val() +"_";
				});
				appendFieldValue = appendFieldValue + cns[i] +":" + vs + ",";
			}
		}
		var radioName = ",";
		$("input[type=radio][id^='append_']").each(function(){
			var name = $(this).attr("name");
			if(radioName.indexOf(name) == -1){
				radioName += name+",";
			}
		});
		var rns = radioName.split(",");
		for(var i = 0;i<rns.length;i++){
			if(rns[i] != ''){
				appendFieldValue = appendFieldValue + rns[i] +":" + $("#append_"+rns[i]).val() + ",";
			}
		}
		var specificationDetail = "";
		try{
			if(getSpecificationDetail && typeof(getSpecificationDetail) == "function"){
				specificationDetail = getSpecificationDetail();
			}
		}catch(e){}
		$.post('${vix}/mdm/item/fastAddItemAction!saveOrUpdateCirculationIndustryItem.action',
			{'item.id':$("#id").val(),
			  'item.objectExpand.id':$("#objectExpandId").val(),
			  'appendFieldValue':appendFieldValue,
			  'specificationDetail':specificationDetail,
			  'itemCatalogIds':$("#itemCatalogIds").val(),
			  'item.name':$("#name").val(),
			  'item.code':$("#itemCode").val(),
			  'item.status':$(":radio[name=status][checked]").val(),
			  'item.itemBrand.id':$("#itemBrandId").val(),
			  'item.specification':$("#specification").val(),
			  'item.serviceContent':$("#serviceContent").val(),
			  'item.eanupc':$("#eanupc").val(),
			  //一般信息
			  'item.measureUnitGroup.id':$("#measureUnitGroupId").val(),
			  'item.measureUnit.id':$("#measureUnitId").val(),
			  'item.assitMeasureUnit.id':$("#assitMeasureUnitId").val(),
			  'item.statusCode':$("#statusCode").val(),
			  'item.fastCode':$("#fastCode").val(),
			  'item.searchText1':$("#searchText1").val(),
			  'item.searchText2':$("#searchText2").val(),
			  'item.barCode':$("#barCode").val(),
			  'item.chinaShortName':$("#chinaShortName").val(),
			  'item.currencyType.id':$("#currencyTypeId").val(),
			  'item.skuCode':$("#skuCode").val(),
			  //税与价格
			  'item.inTax':$("#inTax").val(),
			  'item.saleTax':$("#saleTax").val(),
			  'item.price':$("#price").val(),
			  'item.costPrice':$("#costPrice").val(),
			  'item.purchasePrice':$("#purchasePrice").val(),
			  'item.productCountry':$("#productCountry").val(),
			  'item.productEnterprise':$("#productEnterprise").val(),
			  'item.description':editor.html(),
			  'item.supplierId':$("#supplierIdItem").val(),
			  'item.supplierName':$("#supplierNameItem").val(),
			  'item.enterpriseCode':$("#enterpriseCode").val(),
			  'item.brand':$("#brand").val(),
			  'item.origin':$("#origin").val(),
			  'item.putOnDate':$("#putOnDate").val(),
			  'item.stopSellingDate':$("#stopSellingDate").val(),
			  //采购信息
			  'itemPurchaseProperties.item.id':$("#id").val(),
			  'itemPurchaseProperties.id':$("#itemPurchasePropertiesId").val(),
			  'itemPurchaseProperties.measureUnitGroup.id':$("#purchaseMeasureUnitGroupId").val(),
			  'itemPurchaseProperties.purBaseUnit.id':$("#purBaseUnitId").val(),
			  'itemPurchaseProperties.purAssitUnit.id':$("#purAssitUnitId").val(),
			  'itemPurchaseProperties.poUnit.id':$("#poUnitId").val(),
			  'itemPurchaseProperties.purchaseGroup':$("#purchaseGroup").val(),
			  'itemPurchaseProperties.purchasePerson.id':$("#purchasePersonId").val(),
			  'itemPurchaseProperties.orderType.id':$("#orderTypeId").val(),
			  'itemPurchaseProperties.recieveWarehouse.id':$("#recieveWarehouseId").val(),
			  'itemPurchaseProperties.invShelf.id':$("#purchaseInvShelfId").val(),
			  'itemPurchaseProperties.recieveLocation':$("#recieveLocation").val(),
			   //库存信息
			  'itemInventoryProperties.item.id':$("#id").val(),
			  'itemInventoryProperties.id':$("#itemInventoryPropertiesId").val(),
			  'itemInventoryProperties.maxStockAmount':$("#maxStockAmount").val(),
			  'itemInventoryProperties.minStockAmount':$("#minStockAmount").val(),
			  'itemInventoryProperties.safeStockAmount':$("#safeStockAmount").val(),
			  'itemInventoryProperties.defaultWarehouse.id':$("#defaultWarehouseId").val(),
			  'itemInventoryProperties.invShelf.id':$("#inventoryInvShelfId").val()
			},
			function(result){
				showMessage(result);
				if($("#source").val() == 'item'){
					loadContent('${vix}/mdm/item/itemAction!goList.action');
				}else{
					loadContent('${vix}/mdm/item/fastAddItemAction!goList.action');
				}
				
			}
		);
	}
}

function chooseItemCatagory(ids){
	$.ajax({
		  url:'${vix}/mdm/item/itemCatalogAction!goChooseMultiItemCatalog.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 340,
					title:"选择${vv:varView('vix_mdm_item')}分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							var r = returnValue.split(":");
							$("#itemCatalogIds").val(r[0]);
							$("#itemCatalogNames").val(r[1]);
						} 
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}//扩展表下拉列表变更
function objectExpandChange(id){
	$.ajax({
		  url:'${vix}/mdm/item/fastAddItemAction!loadObjectExpandField.action?id='+id+"&itemId="+$("#id").val(),
		  cache: false,
		  success: function(html){
			  $("#objectExpandField").html(html);
			  loadSpecification();
		  }
	});
}
/** 载入规格数据 */
function loadSpecification(){
	var oeId = $("#objectExpandId").val();
	if(oeId != ''){
		$.ajax({
			url:'${vix}/mdm/item/fastAddItemAction!loadSpecificationDetail.action?itemId='+$("#id").val()+"&objectExpandId="+oeId,
			cache: false,
			success: function(html){
				$("#specificationInformation").html(html);
			}
		});
	}
}
loadSpecification();
function loadItemMeasureUnit(){
	var mug = $("#measureUnitGroupId").val();
	if(mug != ""){
		$.ajax({
			  url:"${vix}/mdm/item/itemAction!loadMeasureUnit.action?type=itemMeasureUnit&id="+$("#id").val()+"&measureUnitGroupId="+mug,
			  cache: false,
			  success: function(html){
				  $("#measureUnitId").html(html);
			  }
		});
		$.ajax({
			  url:"${vix}/mdm/item/itemAction!loadMeasureUnit.action?type=itemAssitMeasureUnit&id="+$("#id").val()+"&measureUnitGroupId="+mug,
			  cache: false,
			  success: function(html){
				  $("#assitMeasureUnitId").html(html);
			  }
		});
	}
}
function loadPurchaseMeasureUnit(){
	var mug = $("#purchaseMeasureUnitGroupId").val();
	if(mug != ""){
		$.ajax({
			  url:"${vix}/mdm/item/itemAction!loadMeasureUnit.action?type=purBaseUnit&id="+$("#id").val()+"&measureUnitGroupId="+mug,
			  cache: false,
			  success: function(html){
				  $("#purBaseUnitId").html(html);
			  }
		});
		$.ajax({
			  url:"${vix}/mdm/item/itemAction!loadMeasureUnit.action?type=purAssitUnit&id="+$("#id").val()+"&measureUnitGroupId="+mug,
			  cache: false,
			  success: function(html){
				  $("#purAssitUnitId").html(html);
			  }
		});
		$.ajax({
			  url:"${vix}/mdm/item/itemAction!loadMeasureUnit.action?type=poUnit&id="+$("#id").val()+"&measureUnitGroupId="+mug,
			  cache: false,
			  success: function(html){
				  $("#poUnitId").html(html);
			  }
		});
	}
}
loadItemMeasureUnit();
loadPurchaseMeasureUnit();
function choosewarehouse(tag) {
	$.ajax({
		url : '${vix}/inventory/warehouseAction!goChooseWarehouse.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 960,
			height : 500,
			title : "选择仓库",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#"+tag+"Id").val(result[0]);
						$("#"+tag+"Name").val(result[1]);
						loadInvShelf(tag);
					} else {
						asyncbox.success("请选择仓库信息!", "提示信息");
						$("#"+tag+"Id").val("");
						$("#"+tag+"Name").val("");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
	});
}

function loadInvShelf(tag){
	if(tag == 'defaultWarehouse'){
		var iwhId = $("#defaultWarehouseId").val();
		if(iwhId != ""){
			$.ajax({
				  url:"${vix}/mdm/item/itemInventoryPropertiesAction!loadInvShelf.action?id="+$("#itemInventoryPropertiesId").val()+"&invWarehouseId="+iwhId,
				  cache: false,
				  success: function(html){
					  $("#inventoryInvShelfId").html(html);
				  }
			});
		}
	}
	if(tag == 'recieveWarehouse'){
		var iwhId = $("#recieveWarehouseId").val();
		if(iwhId != ""){
			$.ajax({
				  url:"${vix}/mdm/item/itemPurchasePropertiesAction!loadInvShelf.action?id="+$("#itemPurchasePropertiesId").val()+"&invWarehouseId="+iwhId,
				  cache: false,
				  success: function(html){
					  $("#purchaseInvShelfId").html(html);
				  }
			});
		}
	}
}
loadInvShelf('defaultWarehouse');
loadInvShelf('recieveWarehouse');
function chooseEmployee(tag){
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
								$("#"+tag+"Id").val(result[0]);
								$("#"+tag+"Name").val(result[1]);
							}else{
								$("#"+tag+"Id").val("");
								$("#"+tag+"Name").val("");
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

</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/mdm/item.png" alt="" />主数据</a></li>
				<li><a href="#">主数据管理</a></li>
				<li><a href="#">主数据维护</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mdm/item/itemAction!goList.action');">物料</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${item.id}" />
<input type="hidden" id="source" name="source" value="${source}" />
<input type="hidden" id="itemPurchasePropertiesId" name="itemPurchasePropertiesId" value="${itemPurchaseProperties.id}" />
<input type="hidden" id="itemInventoryPropertiesId" name="itemInventoryPropertiesId" value="${itemInventoryProperties.id}" />
<div class="content">
	<form id="itemForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateFastAddItem();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <s:if test="source == 'item'">
							<a href="#" onclick="loadContent('${vix}/mdm/item/itemAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
						</s:if> <s:else>
							<a href="#" onclick="loadContent('${vix}/mdm/item/itemAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
						</s:else>
					</span> <strong> <b> <s:if test="item.id > 0">
							${item.name}
						</s:if> <s:else>
							${vv:varView('vix_mdm_item')}基本信息
						</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix" style="border-bottom: 0px;">
					<div class="order_table">
						<table>
							<tr>
								<td width="15%" align="right">编码:</td>
								<td width="35%"><input id="itemCode" name="item.code" value="${item.code}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td width="10%" align="right">名称:</td>
								<td width="40%"><input id="name" name="item.name" value="${item.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td width="15%" align="right">${vv:varView('vix_mdm_item')}类型:</td>
								<td width="35%"><s:select id="objectExpandId" headerKey="" headerValue="--请选择--" list="%{objectExpandList}" listValue="name" listKey="id" value="%{item.objectExpand.id}" multiple="false" theme="simple" onchange="objectExpandChange($(this).val());"></s:select></td>
								<td width="10%" align="right">${vv:varView('vix_mdm_item')}分类:</td>
								<td width="40%"><input id="itemCatalogIds" value="${item.itemCatalogIds}" type="hidden" size="30" /> <input id="itemCatalogNames" value="${item.itemCatalogNames}" type="text" size="30" readonly="readonly" /> <a class="abtn" href="#" onclick="chooseItemCatagory();"><span>选择</span></a></td>
							</tr>
							<tr>
								<td align="right">${vv:varView('vix_mdm_item')}品牌：</td>
								<td><s:select id="itemBrandId" headerKey="" headerValue="--请选择--" list="%{itemBrandList}" listValue="name" listKey="id" value="%{item.itemBrand.id}" multiple="false" theme="simple"></s:select></td>
								<td align="right">规格型号:</td>
								<td><input id="specification" name="item.specification" value="${item.specification}" type="text" size="30" /></td>
							<tr>
								<td align="right">条形码：</td>
								<td><input id="barCode" name="item.barCode" value="${item.barCode}" type="text" size="30" /></td>
								<td align="right">EAN/UPC:</td>
								<td><input id="eanupc" name="item.eanupc" value="${item.eanupc}" type="text" size="30" /></td>
							</tr>
							<tr>
								<td align="right">计量单位组：</td>
								<td><s:select id="measureUnitGroupId" headerKey="" headerValue="--请选择--" list="%{measureUnitGroupList}" listValue="name" listKey="id" value="%{item.measureUnitGroup.id}" multiple="false" theme="simple" onchange="loadItemMeasureUnit();"></s:select></td>
								<td align="right">SKU码：</td>
								<td><input id="skuCode" name="item.skuCode" value="${item.skuCode}" type="text" size="30" /></td>
							</tr>
							<tr>
								<td align="right">主计量单位：</td>
								<td><select id="measureUnitId"><option value="">--------请选择------</option></select></td>
								<td align="right">辅助计量单位：</td>
								<td><select id="assitMeasureUnitId"><option value="">--------请选择------</option></select></td>
							</tr>
							<tr>
								<td align="right">快捷码:</td>
								<td><input id="fastCode" name="fastCode" value="${item.fastCode}" type="text" size="30" /></td>
								<td align="right">状态:</td>
								<td><s:if test="item.status == 0">
										<input type="radio" id="status1" name="status" value="1" />激活
									<input type="radio" id="status2" name="status" value="0" checked="checked" />禁用
								</s:if> <s:elseif test="item.status == 1">
										<input type="radio" id="status1" name="status" value="1" checked="checked" />激活
									<input type="radio" id="status2" name="status" value="0" />禁用
								</s:elseif> <s:else>
										<input type="radio" id="status1" name="status" value="1" checked="checked" />激活
									<input type="radio" id="status2" name="status" value="0" />禁用
								</s:else></td>
							</tr>
							<tr>
								<td align="right">搜索项1：</td>
								<td><input id="searchText1" name="item.searchText1" value="${item.searchText1}" type="text" size="30" /></td>
								<td align="right">搜索项2:</td>
								<td><input id="searchText2" name="searchText2" value="${item.searchText2}" type="text" size="30" /></td>
							</tr>
							<tr>
								<td align="right">服务内容:</td>
								<td colspan="3"><input id="serviceContent" name="item.serviceContent" value="${item.serviceContent}" type="text" size="30" /></td>
							</tr>
							<tr>
								<td colspan="4"><input id="chinaShortName" name="chinaShortName" value="${item.chinaShortName}" type="hidden" size="30" /></td>
							</tr>
							<tr>
								<td align="right">商品图片：</td>
								<td colspan="3">
									<div class="right_menu">
										<ul>
											<li class="current"><a onclick="javascript:tab(2,1,'a',event)"><img src="img/mail.png" alt="">本地上传</a></li>
											<li><a onclick="javascript:tab(2,2,'a',event)"><img src="img/mail.png" alt="">视频中心</a></li>
										</ul>
									</div>
									<div id="a1">
										<div>
											<table id="itemImage" class="easyui-datagrid" style="height: 200px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#itemImageTb',url: '${vix}/mdm/item/itemImageAction!getItemImageJson.action?id=${item.id}'">
												<thead>
													<tr>
														<th data-options="field:'id',align:'center',width:60">编号</th>
														<th data-options="field:'name',width:260,align:'center',editor:'text'">名称</th>
														<th data-options="field:'expandName',width:60,align:'center',editor:'text'">类型</th>
														<th data-options="field:'memo',width:320,align:'center',editor:'text'">备注</th>
													</tr>
												</thead>
											</table>
											<div id="itemImageTb" style="height: auto">
												<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-edit',plain:true" onclick="updateItemImage()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">下载</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
													data-options="iconCls:'icon-remove',plain:true" onclick="removeItemImage()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
											</div>
											<script type="text/javascript">
											$('#itemImage').datagrid();
											function removeItemImage(){
												var row = $('#itemImage').datagrid('getSelected');
												if(row){
													asyncbox.confirm('是否删除该附件信息?','提示信息',function(action){
														if(action == 'ok'){
															var index = $('#itemImage').datagrid('getRowIndex',row);
															$('#itemImage').datagrid('deleteRow', index);
															$.ajax({
																  url:'${vix}/mdm/item/itemImageAction!deleteAttachFile.action?id='+row.id,
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
											function updateItemImage(){
												var row = $('#itemImage').datagrid('getSelected');
												if(row){
													$.ajax({
														  url:'${vix}/mdm/item/itemImageAction!downloadAttachedFile.action?id=' + row.id,
														  cache: false,
														  success: function(html){
															  showMessage(html);
														  }
													});
												}else{
													showMessage("请选择一条数据!");
												}
											}
											function addItemImage(id){
													$("itemImageForm").ajaxSubmit({
													     type: "post",
													     url: "${vix}/mdm/item/itemImageAction!uploadAttachment.action?id="+$("#id").val(),
													     dataType: "text",
													     success: function(result){
													    	 showMessage(result);
													    	 $("#fileToUpload").val("");
													    	 $("#iiMemo").val("");
												    		$('#itemImage').datagrid("load");
														    	
													     }
													 });
											}
										</script>
											<form id="itemImageForm" method="post" enctype="multipart/form-data">
												<p>
													选择本地图片：<input type="file" id="fileToUpload" name="fileToUpload" />
												</p>
												<p>
													文件备注： <input id="iiMemo" name="itemImage.memo"></input>
												</p>
												<p>
													<a href="###" onclick="addItemImage(0);" class="abtn"><span>上传</span></a>
												</p>
											</form>
											<p class="gray">提示：1.本地上传图片大小不能超过500K</p>
											<p class="gray">2.本类目下最多可以上传5张图片。</p>
										</div>
									</div>
									<div id="a2" class="commodity_tab" style="display: none;">
										<div class="commodity_upload">
											<p>
												选择视频中心图片：<input name="" type="file" />
											</p>
											<p class="gray">提示：1.本地上传图片大小不能超过500K</p>
											<p class="gray">2.本类目下最多可以上传5张图片。</p>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<th align="right">商品规格</th>
								<td><a class="abtn" href="###" onclick="generateSpecification();"><span>创建规格</span></a></td>
							</tr>
							<tr>
								<td colspan="4">
									<div id="specificationInformation" style="padding-bottom: 5px;"></div>
								</td>
							</tr>
							<tr>
								<th align="right">产品描述：</th>
								<td colspan="3"><textarea id="description" name="description">${item.description}</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
									var editor = KindEditor.create('textarea[name="description"]', {
									basePath : '${vix}/plugin/KindEditor/',
									width : 830,
									height : 200,
									cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
									uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
									fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
									allowFileManager : true
									});
								</script></td>
							</tr>
						</table>
					</div>
				</dd>
				<div id="objectExpandField" class="clearfix" style="background: #FFF; position: relative;">
					<s:if test="objectExpandFieldList.size > 0">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>扩展信息</strong>
								</dt>
								<dd style="display: block;">
									<input id="append_ID" name="append_ID" value="<s:property value="loadObjectExpandFieldValue('ID')"/>" type="hidden" />
									<table style="border: none;">
										<s:iterator var="ef" value="objectExpandFieldList" status="s">
											<s:if test="#s.count%4 == 1 ">
												<tr height="30">
											</s:if>
											<td align="right">${ef.displayName}:&nbsp;</td>
											<td><s:if test="#ef.fieldType == 'date'">
													<input id="append_${ef.fieldName}" name="${ef.fieldName}" value="<s:property value="loadObjectExpandFieldValue(#ef.fieldName)"/>" type="text" size="30" />
													<img onclick="showTime('append_${ef.fieldName}','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">
												</s:if> <s:if test="#ef.fieldType == 'select'">
													<select id="append_${ef.fieldName}" name="${ef.fieldName}">
														<option value="0">----请选择----</option>
														<s:iterator var="ev" value="loadObjectExpandFieldSelectList(#ef.refTag)">
															<s:if test="loadObjectExpandFieldSelectValue(#ef.fieldName) == #ev.value">
																<option value="${ev.value}" selected="selected">${ev.displayName}</option>
															</s:if>
															<s:else>
																<option value="${ev.value}">${ev.displayName}</option>
															</s:else>
														</s:iterator>
													</select>
												</s:if> <s:if test="#ef.fieldType == 'checkbox'">
													<s:iterator var="ev" value="loadObjectExpandFieldSelectList(#ef.refTag)">
														<s:if test="loadObjectExpandFieldCheckboxValue(#ef.fieldName+','+#ev.value)">
															<input id="append_${ef.fieldName}" name="${ef.fieldName}" value="${ev.value}" type="checkbox" checked="checked" />${ev.displayName}
												</s:if>
														<s:else>
															<input id="append_${ef.fieldName}" name="${ef.fieldName}" value="${ev.value}" type="checkbox" />${ev.displayName}
												</s:else>
													</s:iterator>
												</s:if> <s:if test="#ef.fieldType == 'radio'">
													<s:iterator var="ev" value="loadObjectExpandFieldSelectList(#ef.refTag)">
														<s:if test="loadObjectExpandFieldRadioValue(#ef.fieldName+','+#ev.value)">
															<input id="append_${ef.fieldName}" name="${ef.fieldName}" value="${ev.value}" type="radio" checked="checked" />${ev.displayName}
												</s:if>
														<s:else>
															<input id="append_${ef.fieldName}" name="${ef.fieldName}" value="${ev.value}" type="radio" />${ev.displayName}
												</s:else>
													</s:iterator>
												</s:if> <s:if test="#ef.fieldType == 'text' || #ef.fieldType == 'number'">
													<input id="append_${ef.fieldName}" name="${ef.fieldName}" value="<s:property value="loadObjectExpandFieldValue(#ef.fieldName)"/>" type="text" size="30" />
												</s:if></td>
											<s:if test="#s.count%2 == 0 ">
												</tr>
											</s:if>
										</s:iterator>
									</table>
								</dd>
							</dl>
						</div>
					</s:if>
				</div>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="voucher newvoucher">
						<dl>
							<dt>
								<b></b> <strong>税与价格</strong>
							</dt>
							<dd style="display: block;">
								<table style="border: none;">
									<tr>
										<td width="15%" align="right">币种：</td>
										<td width="35%"><s:select id="currencyTypeId" headerKey="" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="%{item.currencyType.id}" multiple="false" theme="simple"></s:select></td>
										<td width="10%" align="right">利润率：</td>
										<td width="40%"><input id="profitMargin" value="${item.profitMargin}" type="text" size="30" readonly="readonly" />% 范围(1-100)</td>
									</tr>
									<tr>
										<td align="right">零售价格：</td>
										<td><input id="price" name="item.price" value="${item.price}" type="text" size="30" class="validate[required,custom[number]] text-input" /><span style="color: red;">*</span></td>
										<td align="right">直接采购价格：</td>
										<td><input id="purchasePrice" name="item.purchasePrice" value="${item.purchasePrice}" type="text" size="30" /></td>
									</tr>
									<tr>
										<td align="right">成本价：</td>
										<td><input id="costPrice" name="item.costPrice" value="${item.costPrice}" type="text" size="30" /></td>
										<td width="15%" align="right">进项税率(%)：</td>
										<td width="35%"><input id="inTax" name="item.inTax" value="${item.inTax}" type="text" size="30" class="validate[custom[number]] text-input" /> 范围(1-100)</td>
									</tr>
									<tr>
										<td align="right">销售税率(%)：</td>
										<td colspan="3"><input id="saleTax" name="item.saleTax" value="${item.saleTax}" type="text" size="30" class="validate[custom[number]] text-input" /> 范围(1-100)</td>
									</tr>
								</table>
							</dd>
						</dl>
					</div>
					<div class="voucher newvoucher">
						<dl>
							<dt>
								<b></b> <strong>价格预警</strong>
							</dt>
							<dd style="display: block; padding: 2px 0;">
								<div class="right_menu">
									<ul>
										<li class="current"><a onclick="javascript:tab(2,1,'price',event)"><img src="img/mail.png" alt="">采购价格</a></li>
										<li><a onclick="javascript:tab(2,2,'price',event)"><img src="img/mail.png" alt="">零售价格</a></li>
									</ul>
								</div>
								<div id="price1">
									<table id="itemPrice" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#itemPriceTb',url: '${vix}/mdm/item/itemPriceAction!getItemPriceJson.action?type=price&id=${item.id}'">
										<thead>
											<tr>
												<th data-options="field:'id',width:60">编号</th>
												<th data-options="field:'startDate',width:120,align:'center',editor:'text'">启用时间</th>
												<th data-options="field:'endDate',width:120,align:'center',editor:'datebox'">禁用时间</th>
												<th data-options="field:'minPrice',width:120,align:'center',editor:'text'">最低价格</th>
												<th data-options="field:'price',width:120,align:'center',editor:'text'">实际价格</th>
												<th data-options="field:'maxPrice',width:120,align:'center',editor:'text'">最高价格</th>
												<th data-options="field:'memo',width:280,align:'center',editor:'text'">备注</th>
											</tr>
										</thead>
									</table>
									<div id="itemPriceTb" style="height: auto">
										<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItemPrice(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
											data-options="iconCls:'icon-add',plain:true" onclick="updateItemPrice()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true"
											onclick="removeItemPrice()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
									</div>
									<script type="text/javascript">
										$('#itemPrice').datagrid();
										function removeItemPrice(){
											var row = $('#itemPrice').datagrid('getSelected');
											if(row){
												asyncbox.confirm('是否删除该价格?','提示信息',function(action){
													if(action == 'ok'){
														var index = $('#itemPrice').datagrid('getRowIndex',row);
														$('#itemPrice').datagrid('deleteRow', index);
														$.ajax({
															  url:'${vix}/mdm/item/itemPriceAction!deleteById.action?id='+row.id,
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
										function updateItemPrice(){
											var row = $('#itemPrice').datagrid('getSelected');
											if(row){
												addItemPrice(row.id);
											}else{
												showMessage("请选择一条数据!");
											}
										}
										function addItemPrice(id){
											$.ajax({
												  url:'${vix}/mdm/item/itemPriceAction!goSaveOrUpdate.action?id='+id,
												  cache: false,
												  success: function(html){
													  asyncbox.open({
														 	modal:true,
														 	width : 660,
															height :200,
															title:"零售价格",
															html:html,
															callback : function(action){
																if(action == 'ok'){
																	if($('#itemPriceForm').validationEngine('validate')){
																		$.post('${vix}/mdm/item/itemPriceAction!saveOrUpdate.action',
																				{'itemPrice.id':$("#ipId").val(),
																				  'itemPrice.startDate':$("#startDate").val(),
																				  'itemPrice.endDate':$("#endDate").val(),
																				  'itemPrice.maxPrice':$("#maxPrice").val(),
																				  'itemPrice.price':$("#price").val(),
																				  'itemPrice.minPrice':$("#minPrice").val(),
																				  'itemPrice.type':'price',
																				  'itemPrice.item.id':$("#id").val()
																				},
																				function(result){
																					showMessage(result);
																					setTimeout("",1000);
																					$('#itemPrice').datagrid("reload");
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
								<div id="price2" style="display: none;">
									<table id="itemPurchasePrice" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#itemPurchasePriceTb',url: '${vix}/mdm/item/itemPriceAction!getItemPriceJson.action?type=purchasePrice&id=${item.id}'">
										<thead>
											<tr>
												<th data-options="field:'id',width:60">编号</th>
												<th data-options="field:'startDate',width:120,align:'center',editor:'text'">启用时间</th>
												<th data-options="field:'endDate',width:120,align:'center',editor:'datebox'">禁用时间</th>
												<th data-options="field:'minPrice',width:120,align:'center',editor:'text'">最低价格</th>
												<th data-options="field:'price',width:120,align:'center',editor:'text'">实际价格</th>
												<th data-options="field:'maxPrice',width:120,align:'center',editor:'text'">最高价格</th>
												<th data-options="field:'memo',width:280,align:'center',editor:'text'">备注</th>
											</tr>
										</thead>
									</table>
									<div id="itemPurchasePriceTb" style="height: auto">
										<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItemPurchasePrice(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)"
											class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="updateItemPurchasePrice()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
											data-options="iconCls:'icon-remove',plain:true" onclick="removeItemPurchasePrice()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
									</div>
									<script type="text/javascript">
										$('#itemPurchasePrice').datagrid();
										function removeItemPurchasePrice(){
											var row = $('#itemPurchasePrice').datagrid('getSelected');
											if(row){
												asyncbox.confirm('是否删除该价格?','提示信息',function(action){
													if(action == 'ok'){
														var index = $('#itemPurchasePrice').datagrid('getRowIndex',row);
														$('#itemPurchasePrice').datagrid('deleteRow', index);
														$.ajax({
															  url:'${vix}/mdm/item/itemPriceAction!deleteById.action?id='+row.id,
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
										function updateItemPurchasePrice(){
											var row = $('#itemPurchasePrice').datagrid('getSelected');
											if(row){
												addItemPurchasePrice(row.id);
											}else{
												showMessage("请选择一条数据!");
											}
										}
										function addItemPurchasePrice(id){
											$.ajax({
												  url:'${vix}/mdm/item/itemPriceAction!goSaveOrUpdate.action?id='+id,
												  cache: false,
												  success: function(html){
													  asyncbox.open({
														 	modal:true,
															width : 660,
															height :200,
															title:"零售价格",
															html:html,
															callback : function(action){
																if(action == 'ok'){
																	if($('#itemPriceForm').validationEngine('validate')){
																		$.post('${vix}/mdm/item/itemPriceAction!saveOrUpdate.action',
																				{'itemPrice.id':$("#ipId").val(),
																				  'itemPrice.startDate':$("#startDate").val(),
																				  'itemPrice.endDate':$("#endDate").val(),
																				  'itemPrice.maxPrice':$("#maxPrice").val(),
																				  'itemPrice.price':$("#price").val(),
																				  'itemPrice.minPrice':$("#minPrice").val(),
																				  'itemPrice.type':'purchasePrice',
																				  'itemPrice.item.id':$("#id").val()
																				},
																				function(result){
																					showMessage(result);
																					setTimeout("",1000);
																					$('#itemPurchasePrice').datagrid("reload");
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
							</dd>
						</dl>
					</div>
					<div class="voucher newvoucher">
						<dl>
							<dt>
								<b></b> <strong>流通数据</strong>
							</dt>
							<dd style="display: block;">
								<table style="border: none;">
									<tr>
										<td width="15%" align="right">企业代码：</td>
										<td width="35%"><input id="enterpriseCode" name="item.enterpriseCode" value="${item.enterpriseCode}" type="text" size="30" /></td>
										<td width="10%" align="right">品牌：</td>
										<td width="40%"><input id="brand" name="item.brand" value="${item.brand}" type="text" size="30" /></td>
									</tr>
									<tr>
										<td align="right">生产国别：</td>
										<td><input id="productCountry" name="item.productCountry" value="${item.productCountry}" type="text" size="30" /></td>
										<td align="right">产地：</td>
										<td><input id="origin" name="item.origin" value="${item.origin}" type="text" size="30" /></td>
									</tr>
									<tr>
										<td align="right">投放日期：</td>
										<td><input id="putOnDate" name="item.putOnDate" value="${item.putOnDate}" type="text" size="30" /> <img onclick="showTime('putOnDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										<td align="right">停售日期：</td>
										<td><input id="stopSellingDate" name="item.stopSellingDate" value="${item.stopSellingDate}" type="text" size="30" /> <img onclick="showTime('stopSellingDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
									</tr>
								</table>
							</dd>
						</dl>
					</div>
					<div class="voucher newvoucher">
						<dl>
							<dt>
								<b></b> <strong>采购信息</strong>
							</dt>
							<dd style="display: block;">
								<table style="border: none;">
									<tr>
										<td width="15%" align="right">供应商：</td>
										<td width="35%"><input id="supplierIdItem" name="item.supplierId" value="${item.supplierId}" type="hidden" /> <input id="supplierNameItem" name="item.supplierName" value="${item.supplierName}" type="text" size="30" /> <a class="abtn" href="###" onclick="chooseSupplier('Item');"><span>选择</span></a></td>
										<td width="10%" align="right">计量单位组：</td>
										<td width="40%"><s:select id="purchaseMeasureUnitGroupId" headerKey="" headerValue="--------请选择--------" list="%{measureUnitGroupList}" listValue="name" listKey="id" value="%{itemPurchaseProperties.measureUnitGroup.id}" multiple="false" theme="simple" onchange="loadPurchaseMeasureUnit();"></s:select></td>
									</tr>
									<tr>
										<td align="right">采购基本计量单位：</td>
										<td><select id="purBaseUnitId"><option value="">--------请选择------</option></select></td>
										<td align="right">采购辅助计量单位：</td>
										<td><select id="purAssitUnitId"><option value="">--------请选择------</option></select></td>
									</tr>
									<tr>
										<td align="right">采购订单单位：</td>
										<td><select id="poUnitId"><option value="">--------请选择------</option></select></td>
										<td align="right">采购组：</td>
										<td><input id="purchaseGroup" name="itemPurchaseProperties.purchaseGroup" value="${itemPurchaseProperties.purchaseGroup}" type="text" size="30" /></td>
									</tr>
									<tr>
										<td align="right">采购员：</td>
										<td><input type="hidden" id="purchasePersonId" value="${itemPurchaseProperties.purchasePerson.id}" /> <input id="purchasePersonName" name="itemPurchaseProperties.purchasePerson.name" value="${itemPurchaseProperties.purchasePerson.name}" type="text" size="30" /> <a class="abtn" href="###" onclick="chooseEmployee('purchasePerson');"><span>选择</span></a>
										</td>
										<td align="right">采购类型：</td>
										<td><s:select id="orderTypeId" headerKey="" headerValue="--请选择--" list="%{orderTypeList}" listValue="name" listKey="id" value="%{itemPurchaseProperties.orderType.id}" multiple="false" theme="simple"></s:select></td>
									</tr>
									<tr>
										<td align="right">接收仓库：</td>
										<td><input type="hidden" id="recieveWarehouseId" value="${itemPurchaseProperties.recieveWarehouse.id}" /> <input id="recieveWarehouseName" name="itemPurchaseProperties.recieveWarehouse.name" value="${itemPurchaseProperties.recieveWarehouse.name}" type="text" size="30" /> <a class="abtn" href="###"
											onclick="choosewarehouse('recieveWarehouse');"><span>选择</span></a></td>
										<td align="right">库位:</td>
										<td><select id="purchaseInvShelfId"><option value="">--------请选择------</option></select></td>
									</tr>
									<tr>
										<td align="right">接收地址：</td>
										<td colspan="3"><input id="recieveLocation" name="itemPurchaseProperties.recieveLocation" value="${itemPurchaseProperties.recieveLocation}" type="text" size="30" /></td>
									</tr>
								</table>
							</dd>
						</dl>
					</div>
					<div class="voucher newvoucher">
						<dl>
							<dt>
								<b></b> <strong>库存信息</strong>
							</dt>
							<dd style="display: block;">
								<table style="border: none;">
									<tr>
										<td width="15%" align="right">最高库存：</td>
										<td width="35%"><input id="maxStockAmount" name="itemInventoryProperties.maxStockAmount" value="${itemInventoryProperties.maxStockAmount}" type="text" size="30" /></td>
										<td width="10%" align="right">最低库存：</td>
										<td width="40%"><input id="minStockAmount" name="itemInventoryProperties.minStockAmount" value="${itemInventoryProperties.minStockAmount}" type="text" size="30" size="30" /></td>
									</tr>
									<tr>
										<td align="right">安全库存：</td>
										<td><input id="safeStockAmount" name="itemInventoryProperties.safeStockAmount" value="${itemInventoryProperties.safeStockAmount}" type="text" size="30" /></td>
										<td align="right">默认仓库：</td>
										<td><input type="hidden" id="defaultWarehouseId" value="${itemInventoryProperties.defaultWarehouse.id}" /> <input id="defaultWarehouseName" name="itemInventoryProperties.defaultWarehouse.name" value="${itemInventoryProperties.defaultWarehouse.name}" type="text" size="30" /> <a class="abtn" href="###"
											onclick="choosewarehouse('defaultWarehouse');"><span>选择</span></a></td>
									</tr>
									<tr>
										<td align="right">库位:</td>
										<td colspan="3"><select id="inventoryInvShelfId"><option value="">--------请选择------</option></select></td>
									</tr>
								</table>
							</dd>
						</dl>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
