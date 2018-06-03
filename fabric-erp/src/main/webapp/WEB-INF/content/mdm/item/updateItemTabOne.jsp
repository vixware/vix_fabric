<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
function saveOrUpdateItemTabOne(func){
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
	$.post('${vix}/mdm/item/itemAction!saveOrUpdate.action',
		{'item.id':$("#id").val(),
		  'item.objectExpand.id':$("#objectExpandId").val(),
		  'appendFieldValue':appendFieldValue,
		  'item.itemClass':$("#itemClass").val(),
		  'itemCatalogIds':$("#itemCatalogIds").val(),
		  'item.name':$("#name").val(),
		  'item.code':$("#itemCode").val(),
		  'item.specification':$("#specification").val(),
		  'item.serviceContent':$("#serviceContent").val(),
		  'item.oldItemCode':$("#oldItemCode").val(),
		  'item.eanupc':$("#eanupc").val(),
		  'item.serialCode':$("#serialCode").val(),
		  'item.isDeleted':$("#isDeleted").val(),
		  'item.description':editor.html(),
		  //一般信息
		  'item.status':$(":radio[name=status][checked]").val(),
		  'item.drawingCode':$("#drawingCode").val(),
		  'item.itemGroup.id':$("#itemGroupId").val(),
		  'item.itemBrand.id':$("#itemBrandId").val(),
		  'item.scode':$("#scode").val(),
		  'item.xcode':$("#xcode").val(),
		  'item.statusCode':$("#statusCode").val(),
		  'item.barCode':$("#barCode").val(),
		  'item.skuCode':$("#skuCode").val(),
		  'item.industry':$("#industry").val(),
		  'item.fastCode':$("#fastCode").val(),
		  'item.searchText1':$("#searchText1").val(),
		  'item.searchText2':$("#searchText2").val(),
		  'item.chinaShortName':$("#chinaShortName").val(),
		  //流通数据
		  'item.productCountry':$("#productCountry").val(),
		  'item.productEnterprise':$("#productEnterprise").val(),
		  'item.enterpriseCode':$("#enterpriseCode").val(),
		  'item.productLine':$("#productLine").val(),
		  'item.viceProductLine':$("#viceProductLine").val(),
		  'item.productLocation':$("#productLocation").val(),
		  'item.brand':$("#brand").val(),
		  'item.origin':$("#origin").val(),
		  'item.putOnDate':$("#putOnDate").val(),
		  'item.stopSellingDate':$("#stopSellingDate").val(),
		  //计量数据
		  'item.measureUnitGroup.id':$("#measureUnitGroupId").val(),
		  'item.measureUnit.id':$("#measureUnitId").val(),
		  'item.assitMeasureUnit.id':$("#assitMeasureUnitId").val(),
		  'item.produceUnit':$("#produceUnit").val(),
		  'item.stockDefaultUnit':$("#stockDefaultUnit").val(),
		  'item.costDefaultUnit':$("#costDefaultUnit").val(),
		  'item.retailUnit':$("#retailUnit").val(),
		  'item.customsCode':$("#customsCode").val(),
		  'item.customsUnit':$("#customsUnit").val(),
		  'item.customsUnitExchange':$("#customsUnitExchange").val(),
		  'item.weightMensurationGroup':$("#weightMensurationGroup").val(),
		  'item.weightUnit':$("#weightUnit").val(),
		  'item.grossWeight':$("#netWeight").val(),
		  'item.volumeMensurationGroup':$("#volumeMensurationGroup").val(),
		  'item.volumeUnit':$("#volumeUnit").val(),
		  'item.length':$("#length").val(),
		  'item.width':$("#width").val(),
		  'item.height':$("#height").val(),
		  'item.unitVolume':$("#unitVolume").val(),
		  'item.currencyType.id':$("#currencyTypeId").val(),
		  //税与价格
		  'item.inTax':$("#inTax").val(),
		  'item.saleTax':$("#saleTax").val(),
		  'item.price':$("#price").val(),
		  'item.costPrice':$("#costPrice").val(),
		  'item.purchasePrice':$("#purchasePrice").val(),
		  'item.wholesalePrice':$("#wholesalePrice").val(),
		  //配置数据
		  'item.isVirtual':$(":radio[name=isVirtual][checked]").val(),
		  'item.isMPS':$(":radio[name=isMPS][checked]").val(),
		  'item.isConfig':$(":radio[name=isConfig][checked]").val(),
		  'item.isQualityCheck':$(":radio[name=isQualityCheck][checked]").val(),
		  'item.isSaleItem':$(":radio[name=isSaleItem][checked]").val(),
		  'item.isPurchaseItem':$(":radio[name=isPurchaseItem][checked]").val(),
		  'item.isProduceItem':$(":radio[name=isProduceItem][checked]").val(),
		  'item.isOutSourcingItem':$(":radio[name=isOutSourcingItem][checked]").val(),
		  'item.isSelfProduce':$(":radio[name=isVirtual][isSelfProduce]").val(),
		  'item.isInProduce':$(":radio[name=isInProduce][checked]").val(),
		  'item.isDomesticMarket':$(":radio[name=isDomesticMarket][checked]").val(),
		  'item.isOverseaMarket':$(":radio[name=isOverseaMarket][checked]").val(),
		  'item.isPurchase':$(":radio[name=isPurchase][checked]").val(),
		  'item.isProduceConsumption':$(":radio[name=isProduceConsumption][checked]").val(),
		  'item.isPlanProduct':$(":radio[name=isPlanProduct][checked]").val(),
		  'item.isOptions':$(":radio[name=isOptions][checked]").val(),
		  'item.isSparePart':$(":radio[name=isSparePart][checked]").val(),
		  'item.isPTO':$(":radio[name=isPTO][checked]").val(),
		  'item.isATO':$(":radio[name=isATO][checked]").val(),
		  'item.isModel':$(":radio[name=isModel][checked]").val(),
		  'item.isAsset':$(":radio[name=isAsset][checked]").val(),
		  'item.isEngineeringItem':$(":radio[name=isEngineeringItem][checked]").val(),
		  'item.isPiecework':$(":radio[name=isPiecework][checked]").val(),
		  'item.isServiceItem':$(":radio[name=isServiceItem][checked]").val(),
		  'item.isServiceParts':$(":radio[name=isServiceParts][checked]").val(),
		  'item.isServiceProduct':$(":radio[name=isServiceProduct][checked]").val(),
		  'item.isDiscount':$(":radio[name=isDiscount][checked]").val(),
		  'item.isConsignmentsale':$(":radio[name=isConsignmentsale][checked]").val(),
		  'item.isProductSuite':$(":radio[name=isProductSuite][checked]").val(),
		  //扩展属性
		  'item.referenceNumber':$("#referenceNumber").val(),
		  'item.packageSpecs':$("#packageSpecs").val(),
		  'item.certificateOfApprovalNumber':$("#certificateOfApprovalNumber").val(),
		  'item.registerBrand':$("#registerBrand").val(),
		  'item.enterCustomsNumber':$("#enterCustomsNumber").val(),
		  'item.licenseNumber':$("#licenseNumber").val(),
		  'item.patentName':$("#patentName").val(),
		  'item.nonPatentName':$("#nonPatentName").val(),
		  'item.qualityRequirement':$("#qualityRequirement").val()
		},
		function(result){
			showMessage(result);
			func();
		}
	);
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
							$("#itemCatalogIds").val(r[0].replace(",",""));
							$("#itemCatalogNames").val(r[1].replace(",",""));
						} 
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
$(".newvoucher dt b").click(function(){
	$(this).toggleClass("downup");
	$(this).parent("dt").next("dd").toggle();
});
//扩展表下拉列表变更
function objectExpandChange(id){
	$.ajax({
		  url:'${vix}/mdm/item/itemAction!loadObjectExpandField.action?id='+id+"&itemId="+$("#id").val(),
		  cache: false,
		  success: function(html){
			  $("#objectExpandField").html(html);
		  }
	});
}
function loadMeasureUnit(){
	var mugId = $("#measureUnitGroupId").val();
	if(mugId != '-1'){	
		$.ajax({
			  url:"${vix}/mdm/item/itemAction!loadMeasureUnit.action?type=itemMeasureUnit&id="+$("#id").val()+"&measureUnitGroupId="+mugId,
			  cache: false,
			  success: function(html){
				  $("#measureUnitId").html(html);
			  }
		});
		$.ajax({
			  url:"${vix}/mdm/item/itemAction!loadMeasureUnit.action?type=itemAssitMeasureUnit&id="+$("#id").val()+"&measureUnitGroupId="+mugId,
			  cache: false,
			  success: function(html){
				  $("#assitMeasureUnitId").html(html);
			  }
		});
	}
}
loadMeasureUnit();
$("#one").validationEngine();
</script>
<input id="isDeleted" value="${item.isDeleted}" type="hidden" />
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="one">
		<div class="order">
			<dl>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td align="right">编码:</td>
								<td><input id="itemCode" name="item.code" value="${item.code}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">名称:</td>
								<td><input id="name" name="item.name" value="${item.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">旧${vv:varView('vix_mdm_item')}编码:</td>
								<td><input id="oldItemCode" name="oldItemCode" value="${item.oldItemCode}" type="text" size="30" /></td>
								<td align="right">EAN/UPC:</td>
								<td><input id="eanupc" name="item.eanupc" value="${item.eanupc}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td width="15%" align="right">${vv:varView('vix_mdm_item')}类型:</td>
								<td width="35%"><s:select id="objectExpandId" headerKey="" headerValue="--请选择--" list="%{objectExpandList}" listValue="name" listKey="id" value="%{item.objectExpand.id}" multiple="false" theme="simple" onchange="objectExpandChange($(this).val());"></s:select></td>
								<td align="right" width="10%">${vv:varView('vix_mdm_item')}类别:</td>
								<td width="40%"><select id="itemClass">
										<option value="-1">------请选择------</option>
										<s:iterator value="itemTypeMap.entrySet().iterator()" var="key">
											<s:if test="item.itemClass == key">
												<option value="<s:property value='key'/>" selected="selected"><s:text name="getText(value)" /></option>
											</s:if>
											<s:else>
												<option value="<s:property value='key'/>"><s:text name="getText(value)" /></option>
											</s:else>
										</s:iterator>
								</select></td>
							</tr>
							<tr>
								<td align="right">${vv:varView('vix_mdm_item')}分类:</td>
								<td><input id="itemCatalogIds" value="${item.itemCatalogIds}" type="hidden" /> <input id="itemCatalogNames" value="${item.itemCatalogNames}" type="text" size="30" readonly="readonly" /> <a class="abtn" href="###" onclick="chooseItemCatagory();"><span>选择</span></a></td>
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
								<td align="right">规格型号:</td>
								<td><input id="specification" name="item.specification" value="${item.specification}" type="text" size="30" /></td>
								<td align="right">服务内容:</td>
								<td><input id="serviceContent" name="item.serviceContent" value="${item.serviceContent}" type="text" size="30" /></td>
							</tr>

							<tr>
								<td align="right">系列号:</td>
								<td><input id="serialCode" name="item.serialCode" value="${item.serialCode}" type="text" size="30" /></td>
								<td align="right">计量单位组：</td>
								<td><s:select id="measureUnitGroupId" headerKey="" headerValue="--请选择--" list="%{measureUnitGroupList}" listValue="name" listKey="id" value="%{item.measureUnitGroup.id}" multiple="false" theme="simple" onchange="loadMeasureUnit();"></s:select></td>
							</tr>
							<tr>
								<td align="right">主计量单位：</td>
								<td><select id="measureUnitId"><option value="-1">--------请选择------</option></select></td>
								<td align="right">辅助计量单位：</td>
								<td><select id="assitMeasureUnitId"><option value="-1">--------请选择------</option></select></td>
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
									<div id="a2" style="display: none;">
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
						<div id="objectExpandField">
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
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>一般信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">${vv:varView('vix_mdm_item')}品牌：</td>
											<td><s:select id="itemBrandId" headerKey="" headerValue="--请选择--" list="%{itemBrandList}" listValue="name" listKey="id" value="%{item.itemBrand.id}" multiple="false" theme="simple"></s:select></td>
											<td width="10%" align="right">${vv:varView('vix_mdm_item')}组：</td>
											<td width="40%"><s:select id="itemGroupId" headerKey="" headerValue="--请选择--" list="%{itemGroupList}" listValue="name" listKey="id" value="%{item.itemGroup.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">状态码：</td>
											<td><input id="statusCode" name="item.statusCode" value="${item.statusCode}" type="text" size="30" /> <input id="chinaShortName" name="chinaShortName" value="${item.chinaShortName}" type="hidden" /></td>
											<td align="right">条形码：</td>
											<td><input id="barCode" name="item.barCode" value="${item.barCode}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">快捷码:</td>
											<td><input id="fastCode" name="fastCode" value="${item.fastCode}" type="text" size="30" /></td>
											<td align="right">SKU码：</td>
											<td colspan="3"><input id="skuCode" name="item.skuCode" value="${item.skuCode}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">助记码：</td>
											<td><input id="scode" name="item.scode" value="${item.scode}" type="text" size="30" /></td>
											<td align="right">内部代码：</td>
											<td><input id="xcode" name="item.xcode" value="${item.xcode}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">行业领域：</td>
											<td><input id="industry" name="item.industry" value="${item.industry}" type="text" size="30" /></td>
											<!-- <td align="right">${vv:varView('vix_mdm_item')}状态码：</td>
										<td><input value="${item.status}" type="text" size="30"/></td> -->
											<td width="15%" align="right">图号：</td>
											<td width="35%"><input id="drawingCode" name="item.drawingCode" value="${item.drawingCode}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">搜索项1：</td>
											<td><input id="searchText1" name="item.searchText1" value="${item.searchText1}" type="text" size="30" /></td>
											<td align="right">搜索项2:</td>
											<td><input id="searchText2" name="searchText2" value="${item.searchText2}" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>税与价格</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">币种：</td>
											<td colspan="3"><s:select id="currencyTypeId" headerKey="" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="%{item.currencyType.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">零售价格1：</td>
											<td><input id="price" name="item.price" value="${item.price}" type="text" size="30" class="validate[required,custom[number]] text-input" /><span style="color: red;">*</span></td>
											<td align="right">零售价格2：</td>
											<td><input id="price" name="item.price" value="${item.price}" type="text" size="30" class="validate[required,custom[number]] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">移动端价格：</td>
											<td><input id="costPrice" name="item.costPrice" value="${item.costPrice}" type="text" size="30" /></td>
											<td align="right">批发价格：</td>
											<td><input id="wholesalePrice" name="item.wholesalePrice" value="${item.wholesalePrice}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">采购价格：</td>
											<td><input id="purchasePrice" name="item.purchasePrice" value="${item.purchasePrice}" type="text" size="30" /></td>
											<td align="right">成本价格：</td>
											<td><input id="costPrice" name="item.costPrice" value="${item.costPrice}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td width="15%" align="right">进项税率(%)：</td>
											<td width="35%"><input id="inTax" name="item.inTax" value="${item.inTax}" type="text" size="30" class="validate[custom[number]] text-input" /></td>
											<td width="10%" align="right">销售税率(%)：</td>
											<td width="40%"><input id="saleTax" name="item.saleTax" value="${item.saleTax}" type="text" size="30" class="validate[custom[number]] text-input" /></td>
										</tr>
									</table>
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
											<td width="15%" align="right">生产国别：</td>
											<td width="35%"><input id="productCountry" name="item.productCountry" value="${item.productCountry}" type="text" size="30" /></td>
											<td width="10%" align="right">生产企业：</td>
											<td width="40%"><input id="productEnterprise" name="item.productEnterprise" value="${item.productEnterprise}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">企业代码：</td>
											<td><input id="enterpriseCode" name="item.enterpriseCode" value="${item.enterpriseCode}" type="text" size="30" /></td>
											<td align="right">产品线：</td>
											<td><input id="productLine" name="item.productEnterprise" value="${item.productEnterprise}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">次产品线：</td>
											<td><input id="viceProductLine" name="item.viceProductLine" value="${item.viceProductLine}" type="text" size="30" /></td>
											<td align="right">生产地点：</td>
											<td><input id="productLocation" name="item.productLocation" value="${item.productLocation}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">品牌：</td>
											<td><input id="brand" name="item.brand" value="${item.brand}" type="text" size="30" /></td>
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
									<b></b> <strong>配置数据</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right" width="15%">是否虚拟件:</td>
											<td width="35%"><s:if test="item.isVirtual == 0">
													<input type="radio" id="isVirtual1" name="isVirtual" value="1" />是
												<input type="radio" id="isVirtual2" name="isVirtual" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isVirtual == 1">
													<input type="radio" id="isVirtual1" name="isVirtual" value="1" checked="checked" />是
												<input type="radio" id="isVirtual2" name="isVirtual" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isVirtual1" name="isVirtual" value="1" />是
												<input type="radio" id="isVirtual2" name="isVirtual" value="0" checked="checked" />否
											</s:else></td>
											<td align="right" width="10%">是否主生产计划${vv:varView('vix_mdm_item')}:</td>
											<td width="40%"><s:if test="item.isMPS == 0">
													<input type="radio" id="isMPS1" name="isMPS" value="1" />是
												<input type="radio" id="isMPS2" name="isMPS" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isMPS == 1">
													<input type="radio" id="isMPS1" name="isMPS" value="1" checked="checked" />是
												<input type="radio" id="isMPS2" name="isMPS" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isMPS1" name="isMPS" value="1" />是
												<input type="radio" id="isMPS2" name="isMPS" value="0" checked="checked" />否
											</s:else></td>
										</tr>
										<tr>
											<td align="right" width="12%">是否需要配置:</td>
											<td><s:if test="item.isConfig == 0">
													<input type="radio" id="isConfig1" name="isConfig" value="1" />是
												<input type="radio" id="isConfig2" name="isConfig" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isConfig == 1">
													<input type="radio" id="isConfig1" name="isConfig" value="1" checked="checked" />是
												<input type="radio" id="isConfig2" name="isConfig" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isConfig1" name="isConfig" value="1" />是
												<input type="radio" id="isConfig2" name="isConfig" value="0" checked="checked" />否
											</s:else></td>
											<td align="right" width="12%">是否质量检查:</td>
											<td><s:if test="item.isQualityCheck == 0">
													<input type="radio" id="isQualityCheck1" name="isQualityCheck" value="1" />是
												<input type="radio" id="isQualityCheck2" name="isQualityCheck" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isQualityCheck == 1">
													<input type="radio" id="isQualityCheck1" name="isQualityCheck" value="1" checked="checked" />是
												<input type="radio" id="isQualityCheck2" name="isQualityCheck" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isQualityCheck1" name="isQualityCheck" value="1" />是
												<input type="radio" id="isQualityCheck2" name="isQualityCheck" value="0" checked="checked" />否
											</s:else></td>
										</tr>
										<tr>
											<td align="right" width="12%">是否销售${vv:varView('vix_mdm_item')}:</td>
											<td><s:if test="item.isSaleItem == 0">
													<input type="radio" id="isSaleItem1" name="isSaleItem" value="1" />是
												<input type="radio" id="isSaleItem2" name="isSaleItem" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isSaleItem == 1">
													<input type="radio" id="isSaleItem1" name="isSaleItem" value="1" checked="checked" />是
												<input type="radio" id="isSaleItem2" name="isSaleItem" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isSaleItem1" name="isSaleItem" value="1" />是
												<input type="radio" id="isSaleItem2" name="isSaleItem" value="0" checked="checked" />否
											</s:else></td>
											<td align="right" width="12%">是否外购${vv:varView('vix_mdm_item')}:</td>
											<td><s:if test="item.isPurchaseItem == 0">
													<input type="radio" id="isPurchaseItem1" name="isPurchaseItem" value="1" />是
												<input type="radio" id="isPurchaseItem2" name="isPurchaseItem" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isPurchaseItem == 1">
													<input type="radio" id="isPurchaseItem1" name="isPurchaseItem" value="1" checked="checked" />是
												<input type="radio" id="isPurchaseItem2" name="isPurchaseItem" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isPurchaseItem1" name="isPurchaseItem" value="1" />是
												<input type="radio" id="isPurchaseItem2" name="isPurchaseItem" value="0" checked="checked" />否
											</s:else></td>
										</tr>
										<tr>
											<td align="right" width="12%">是否生产耗用:</td>
											<td><s:if test="item.isProduceItem == 0">
													<input type="radio" id="isProduceItem1" name="isProduceItem" value="1" />是
												<input type="radio" id="isProduceItem2" name="isProduceItem" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isProduceItem == 1">
													<input type="radio" id="isProduceItem1" name="isProduceItem" value="1" checked="checked" />是
												<input type="radio" id="isProduceItem2" name="isProduceItem" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isProduceItem1" name="isProduceItem" value="1" />是
												<input type="radio" id="isProduceItem2" name="isProduceItem" value="0" checked="checked" />否
											</s:else></td>
											<td align="right" width="12%">是否委外:</td>
											<td><s:if test="item.isOutSourcingItem == 0">
													<input type="radio" id="isOutSourcingItem1" name="isOutSourcingItem" value="1" />是
												<input type="radio" id="isOutSourcingItem2" name="isOutSourcingItem" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isOutSourcingItem == 1">
													<input type="radio" id="isOutSourcingItem1" name="isOutSourcingItem" value="1" checked="checked" />是
												<input type="radio" id="isOutSourcingItem2" name="isOutSourcingItem" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isOutSourcingItem1" name="isOutSourcingItem" value="1" />是
												<input type="radio" id="isOutSourcingItem2" name="isOutSourcingItem" value="0" checked="checked" />否
											</s:else></td>
										</tr>
										<tr>
											<td align="right" width="12%">是否自制:</td>
											<td><s:if test="item.isSelfProduce == 0">
													<input type="radio" id="isSelfProduce1" name="isSelfProduce" value="1" />是
												<input type="radio" id="isSelfProduce2" name="isSelfProduce" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isSelfProduce == 1">
													<input type="radio" id="isSelfProduce1" name="isSelfProduce" value="1" checked="checked" />是
												<input type="radio" id="isSelfProduce2" name="isSelfProduce" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isSelfProduce1" name="isSelfProduce" value="1" />是
												<input type="radio" id="isSelfProduce2" name="isSelfProduce" value="0" checked="checked" />否
											</s:else></td>
											<td align="right" width="12%">是否为在制:</td>
											<td><s:if test="item.isInProduce == 0">
													<input type="radio" id="isInProduce1" name="isInProduce" value="1" />是
												<input type="radio" id="isInProduce2" name="isInProduce" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isInProduce == 1">
													<input type="radio" id="isInProduce1" name="isInProduce" value="1" checked="checked" />是
												<input type="radio" id="isInProduce2" name="isInProduce" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isInProduce1" name="isInProduce" value="1" />是
												<input type="radio" id="isInProduce2" name="isInProduce" value="0" checked="checked" />否
											</s:else></td>
										</tr>
										<tr>
											<td align="right" width="12%">是否内销:</td>
											<td><s:if test="item.isDomesticMarket == 0">
													<input type="radio" id="isDomesticMarket1" name="isDomesticMarket" value="1" />是
												<input type="radio" id="isDomesticMarket2" name="isDomesticMarket" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isDomesticMarket == 1">
													<input type="radio" id="isDomesticMarket1" name="isDomesticMarket" value="1" checked="checked" />是
												<input type="radio" id="isDomesticMarket2" name="isDomesticMarket" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isDomesticMarket1" name="isDomesticMarket" value="1" />是
												<input type="radio" id="isDomesticMarket2" name="isDomesticMarket" value="0" checked="checked" />否
											</s:else></td>
											<td align="right" width="12%">是否外销:</td>
											<td><s:if test="item.isOverseaMarket == 0">
													<input type="radio" id="isOverseaMarket1" name="isOverseaMarket" value="1" />是
												<input type="radio" id="isOverseaMarket2" name="isOverseaMarket" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isOverseaMarket == 1">
													<input type="radio" id="isOverseaMarket1" name="isOverseaMarket" value="1" checked="checked" />是
												<input type="radio" id="isOverseaMarket2" name="isOverseaMarket" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isOverseaMarket1" name="isOverseaMarket" value="1" />是
												<input type="radio" id="isOverseaMarket2" name="isOverseaMarket" value="0" checked="checked" />否
											</s:else></td>
										</tr>
										<tr>
											<td align="right" width="12%">是否外购:</td>
											<td><s:if test="item.isPurchase == 0">
													<input type="radio" id="isPurchase1" name="isPurchase" value="1" />是
												<input type="radio" id="isPurchase2" name="isPurchase" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isPurchase == 1">
													<input type="radio" id="isPurchase1" name="isPurchase" value="1" checked="checked" />是
												<input type="radio" id="isPurchase2" name="isPurchase" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isPurchase1" name="isPurchase" value="1" />是
												<input type="radio" id="isPurchase2" name="isPurchase" value="0" checked="checked" />否
											</s:else></td>
											<td align="right" width="12%">是否生产耗用:</td>
											<td><s:if test="item.isProduceConsumption == 0">
													<input type="radio" id="isProduceConsumption1" name="isProduceConsumption" value="1" />是
												<input type="radio" id="isProduceConsumption2" name="isProduceConsumption" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isProduceConsumption == 1">
													<input type="radio" id="isProduceConsumption1" name="isProduceConsumption" value="1" checked="checked" />是
												<input type="radio" id="isProduceConsumption2" name="isProduceConsumption" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isProduceConsumption1" name="isProduceConsumption" value="1" />是
												<input type="radio" id="isProduceConsumption2" name="isProduceConsumption" value="0" checked="checked" />否
											</s:else></td>
										</tr>
										<tr>
											<td align="right" width="12%">是否计划品:</td>
											<td><s:if test="item.isPlanProduct == 0">
													<input type="radio" id="isPlanProduct1" name="isPlanProduct" value="1" />是
												<input type="radio" id="isPlanProduct2" name="isPlanProduct" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isPlanProduct == 1">
													<input type="radio" id="isPlanProduct1" name="isPlanProduct" value="1" checked="checked" />是
												<input type="radio" id="isPlanProduct2" name="isPlanProduct" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isPlanProduct1" name="isPlanProduct" value="1" />是
												<input type="radio" id="isPlanProduct2" name="isPlanProduct" value="0" checked="checked" />否
											</s:else></td>
											<td align="right" width="12%">是否选项类:</td>
											<td><s:if test="item.isOptions == 0">
													<input type="radio" id="isOptions1" name="isOptions" value="1" />是
												<input type="radio" id="isOptions2" name="isOptions" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isOptions == 1">
													<input type="radio" id="isOptions1" name="isOptions" value="1" checked="checked" />是
												<input type="radio" id="isOptions2" name="isOptions" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isOptions1" name="isOptions" value="1" />是
												<input type="radio" id="isOptions2" name="isOptions" value="0" checked="checked" />否
											</s:else></td>
										</tr>
										<tr>
											<td align="right" width="12%">是否备件:</td>
											<td><s:if test="item.isSparePart == 0">
													<input type="radio" id="isSparePart1" name="isSparePart" value="1" />是
												<input type="radio" id="isSparePart2" name="isSparePart" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isSparePart == 1">
													<input type="radio" id="isSparePart1" name="isSparePart" value="1" checked="checked" />是
												<input type="radio" id="isSparePart2" name="isSparePart" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isSparePart1" name="isSparePart" value="1" />是
												<input type="radio" id="isSparePart2" name="isSparePart" value="0" checked="checked" />否
											</s:else></td>
											<td align="right" width="12%">是否PTO:</td>
											<td><s:if test="item.isPTO == 0">
													<input type="radio" id="isPTO1" name="isPTO" value="1" />是
												<input type="radio" id="isPTO2" name="isPTO" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isPTO == 1">
													<input type="radio" id="isPTO1" name="isPTO" value="1" checked="checked" />是
												<input type="radio" id="isPTO2" name="isPTO" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isPTO1" name="isPTO" value="1" />是
												<input type="radio" id="isPTO2" name="isPTO" value="0" checked="checked" />否
											</s:else></td>
										</tr>
										<tr>
											<td align="right" width="12%">是否ATO:</td>
											<td><s:if test="item.isATO == 0">
													<input type="radio" id="isATO1" name="isATO" value="1" />是
												<input type="radio" id="isATO2" name="isATO" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isATO == 1">
													<input type="radio" id="isATO1" name="isATO" value="1" checked="checked" />是
												<input type="radio" id="isATO2" name="isATO" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isATO1" name="isATO" value="1" />是
												<input type="radio" id="isATO2" name="isATO" value="0" checked="checked" />否
											</s:else></td>
											<td align="right" width="12%">是否模型:</td>
											<td><s:if test="item.isModel == 0">
													<input type="radio" id="isModel1" name="isModel" value="1" />是
												<input type="radio" id="isModel2" name="isModel" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isModel == 1">
													<input type="radio" id="isModel1" name="isModel" value="1" checked="checked" />是
												<input type="radio" id="isModel2" name="isModel" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isModel1" name="isModel" value="1" />是
												<input type="radio" id="isModel2" name="isModel" value="0" checked="checked" />否
											</s:else></td>
										</tr>
										<tr>
											<td align="right" width="12%">是否资产:</td>
											<td><s:if test="item.isAsset == 0">
													<input type="radio" id="isAsset1" name="isAsset" value="1" />是
												<input type="radio" id="isAsset2" name="isAsset" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isAsset == 1">
													<input type="radio" id="isAsset1" name="isAsset" value="1" checked="checked" />是
												<input type="radio" id="isAsset2" name="isAsset" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isAsset1" name="isAsset" value="1" />是
												<input type="radio" id="isAsset2" name="isAsset" value="0" checked="checked" />否
											</s:else></td>
											<td align="right" width="12%">是否工程${vv:varView('vix_mdm_item')}:</td>
											<td><s:if test="item.isEngineeringItem == 0">
													<input type="radio" id="isEngineeringItem1" name="isEngineeringItem" value="1" />是
												<input type="radio" id="isEngineeringItem2" name="isEngineeringItem" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isEngineeringItem == 1">
													<input type="radio" id="isEngineeringItem1" name="isEngineeringItem" value="1" checked="checked" />是
												<input type="radio" id="isEngineeringItem2" name="isEngineeringItem" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isEngineeringItem1" name="isEngineeringItem" value="1" />是
												<input type="radio" id="isEngineeringItem2" name="isEngineeringItem" value="0" checked="checked" />否
											</s:else></td>
										</tr>
										<tr>
											<td align="right" width="12%">是否计件:</td>
											<td><s:if test="item.isPiecework == 0">
													<input type="radio" id="isPiecework1" name="isPiecework" value="1" />是
												<input type="radio" id="isPiecework2" name="isPiecework" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isPiecework == 1">
													<input type="radio" id="isPiecework1" name="isPiecework" value="1" checked="checked" />是
												<input type="radio" id="isPiecework2" name="isPiecework" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isPiecework1" name="isPiecework" value="1" />是
												<input type="radio" id="isPiecework2" name="isPiecework" value="0" checked="checked" />否
											</s:else></td>
											<td align="right" width="12%">是否服务项目:</td>
											<td><s:if test="item.isServiceItem == 0">
													<input type="radio" id="isServiceItem1" name="isServiceItem" value="1" />是
												<input type="radio" id="isServiceItem2" name="isServiceItem" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isServiceItem == 1">
													<input type="radio" id="isServiceItem1" name="isServiceItem" value="1" checked="checked" />是
												<input type="radio" id="isServiceItem2" name="isServiceItem" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isServiceItem1" name="isServiceItem" value="1" />是
												<input type="radio" id="isServiceItem2" name="isServiceItem" value="0" checked="checked" />否
											</s:else></td>
										</tr>
										<tr>
											<td align="right" width="12%">是否服务配件:</td>
											<td><s:if test="item.isServiceParts == 0">
													<input type="radio" id="isServiceParts1" name="isServiceParts" value="1" />是
												<input type="radio" id="isServiceParts2" name="isServiceParts" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isServiceParts == 1">
													<input type="radio" id="isServiceParts1" name="isServiceParts" value="1" checked="checked" />是
												<input type="radio" id="isServiceParts2" name="isServiceParts" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isServiceParts1" name="isServiceParts" value="1" />是
												<input type="radio" id="isServiceParts2" name="isServiceParts" value="0" checked="checked" />否
											</s:else></td>
											<td align="right" width="12%">是否服务产品:</td>
											<td><s:if test="item.isServiceProduct == 0">
													<input type="radio" id="isServiceProduct1" name="isServiceProduct" value="1" />是
												<input type="radio" id="isServiceProduct2" name="isServiceProduct" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isServiceProduct == 1">
													<input type="radio" id="isServiceProduct1" name="isServiceProduct" value="1" checked="checked" />是
												<input type="radio" id="isServiceProduct2" name="isServiceProduct" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isServiceProduct1" name="isServiceProduct" value="1" />是
												<input type="radio" id="isServiceProduct2" name="isServiceProduct" value="0" checked="checked" />否
											</s:else></td>
										</tr>
										<tr>
											<td align="right" width="12%">是否折扣:</td>
											<td><s:if test="item.isDiscount == 0">
													<input type="radio" id="isDiscount1" name="isDiscount" value="1" />是
												<input type="radio" id="isDiscount2" name="isDiscount" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isDiscount == 1">
													<input type="radio" id="isDiscount1" name="isDiscount" value="1" checked="checked" />是
												<input type="radio" id="isDiscount2" name="isDiscount" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isDiscount1" name="isDiscount" value="1" />是
												<input type="radio" id="isDiscount2" name="isDiscount" value="0" checked="checked" />否
											</s:else></td>
											<td align="right" width="12%">是否委外代销:</td>
											<td><s:if test="item.isConsignmentsale == 0">
													<input type="radio" id="isConsignmentsale1" name="isConsignmentsale" value="1" />是
												<input type="radio" id="isConsignmentsale2" name="isConsignmentsale" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isConsignmentsale == 1">
													<input type="radio" id="isConsignmentsale1" name="isConsignmentsale" value="1" checked="checked" />是
												<input type="radio" id="isConsignmentsale2" name="isConsignmentsale" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isConsignmentsale1" name="isConsignmentsale" value="1" />是
												<input type="radio" id="isConsignmentsale2" name="isConsignmentsale" value="0" checked="checked" />否
											</s:else></td>
										</tr>
										<tr>
											<td align="right" width="12%">是否成套件:</td>
											<td><s:if test="item.isProductSuite == 0">
													<input type="radio" id="isProductSuite1" name="isProductSuite" value="1" />是
												<input type="radio" id="isProductSuite2" name="isProductSuite" value="0" checked="checked" />否
											</s:if> <s:elseif test="item.isProductSuite == 1">
													<input type="radio" id="isProductSuite1" name="isProductSuite" value="1" checked="checked" />是
												<input type="radio" id="isProductSuite2" name="isProductSuite" value="0" />否
											</s:elseif> <s:else>
													<input type="radio" id="isProductSuite1" name="isProductSuite" value="1" />是
												<input type="radio" id="isProductSuite2" name="isProductSuite" value="0" checked="checked" />否
											</s:else></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>扩展属性</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td width="15%" align="right">批注文号：</td>
											<td width="35%"><input id="referenceNumber" name="item.referenceNumber" value="${item.referenceNumber}" type="text" size="30" /></td>
											<td width="10%" align="right">包装规格：</td>
											<td width="40%"><input id="packageSpecs" name="item.packageSpecs" value="${item.packageSpecs}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">合格证号：</td>
											<td><input id="certificateOfApprovalNumber" name="item.certificateOfApprovalNumber" value="${item.certificateOfApprovalNumber}" type="text" size="30" /></td>
											<td align="right">注册商标：</td>
											<td><input id="registerBrand" name="item.registerBrand" value="${item.registerBrand}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">入关证号：</td>
											<td><input id="enterCustomsNumber" name="item.enterCustomsNumber" value="${item.enterCustomsNumber}" type="text" size="30" /></td>
											<td align="right">许可证号：</td>
											<td><input id="licenseNumber" name="item.licenseNumber" value="${item.licenseNumber}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">专利名称：</td>
											<td><input id="patentName" name="item.patentName" value="${item.patentName}" type="text" size="30" /></td>
											<td align="right">国际非专利名：</td>
											<td><input id="nonPatentName" name="item.nonPatentName" value="${item.nonPatentName}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">质量要求：</td>
											<td colspan="3"><input id="qualityRequirement" name="item.qualityRequirement" value="${item.qualityRequirement}" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>替代${vv:varView('vix_mdm_item')}</strong>
								</dt>
								<dd style="display: block;">
									<div style="padding: 4px;">
										<table id="replaceItem" class="easyui-datagrid" style="height: 260px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#replaceItemTb',url: '${vix}/mdm/item/itemAction!getItemReplaceJson.action?id=${item.id}'">
											<thead>
												<tr>
													<th data-options="field:'id',align:'center',width:80">编号</th>
													<th data-options="field:'item.code',width:100,align:'center'">编码</th>
													<th data-options="field:'item.name',width:140,align:'center'">名称</th>
													<th data-options="field:'priority',width:100,align:'center'">优先级</th>
													<th data-options="field:'memo',width:240,align:'center',editor:'text'">备注</th>
												</tr>
											</thead>
										</table>
										<div id="replaceItemTb" style="height: auto">
											<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addReplaceItem(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
												data-options="iconCls:'icon-add',plain:true" onclick="updateReplaceItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true"
												onclick="removeReplaceItem()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
										</div>
										<script type="text/javascript">
										$('#replaceItem').datagrid();
										function removeReplaceItem(){
											var row = $('#replaceItem').datagrid('getSelected');
											if(row){
												asyncbox.confirm('是否删除该替代${vv:varView('vix_mdm_item')}信息?','提示信息',function(action){
													if(action == 'ok'){
														var index = $('#replaceItem').datagrid('getRowIndex',row);
														$('#replaceItem').datagrid('deleteRow', index);
														$.ajax({
															  url:'${vix}/mdm/item/itemAction!deleteItemReplace.action?id='+row.id,
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
										function updateReplaceItem(){
											var row = $('#replaceItem').datagrid('getSelected');
											if(row){
												addReplaceItem(row.id);
											}else{
												showMessage("请选择一条数据!");
											}
										}
										function addReplaceItem(id){
											$.ajax({
												  url:'${vix}/mdm/item/itemAction!goSaveOrUpdateItemReplace.action?id='+id,
												  cache: false,
												  success: function(html){
													  asyncbox.open({
														 	modal:true,
															width : 660,
															height : 360,
															title:"替换${vv:varView('vix_mdm_item')}",
															html:html,
															callback : function(action){
																if(action == 'ok'){
																	if($('#itemReplaceForm').validationEngine('validate')){
																		$.post('${vix}/mdm/item/itemAction!saveOrUpdateItemReplace.action',
																				{'itemReplace.id':$("#irId").val(),
																				  'itemReplace.item.id':$("#id").val(),
																				  'itemReplace.priority':$("#priority").val(),
																				  'itemReplace.replaceItem.id':$("#replaceItemId").val(),
																				  'itemReplace.memo':$("#memo").val()
																				},
																				function(result){
																					showMessage(result);
																					$('#replaceItem').datagrid("reload");
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
									<b></b> <strong>${vv:varView('vix_mdm_item')}附件</strong>
								</dt>
								<dd style="display: block;">
									<div style="padding: 4px;">
										<table id="itemAttach" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#itemAttachTb',url: '${vix}/mdm/item/itemAction!getAttachFileJson.action?id=${item.id}'">
											<thead>
												<tr>
													<th data-options="field:'id',align:'center',width:60">编号</th>
													<th data-options="field:'name',width:260,align:'center',editor:'text'">名称</th>
													<th data-options="field:'type',width:60,align:'center',editor:'text'">类型</th>
													<th data-options="field:'memo',width:320,align:'center',editor:'text'">备注</th>
												</tr>
											</thead>
										</table>
										<div id="itemAttachTb" style="height: auto">
											<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItemAttachFile()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
												data-options="iconCls:'icon-edit',plain:true" onclick="updateItemAttachFile()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">下载</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItemAttachFile()"><span
												class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
										</div>
										<script type="text/javascript">
										$('#itemAttach').datagrid();
										function removeItemAttachFile(){
											var row = $('#itemAttach').datagrid('getSelected');
											if(row){
												asyncbox.confirm('是否删除该附件信息?','提示信息',function(action){
													if(action == 'ok'){
														var index = $('#itemAttach').datagrid('getRowIndex',row);
														$('#itemAttach').datagrid('deleteRow', index);
														$.ajax({
															  url:'${vix}/mdm/item/itemAttachFileAction!deleteAttachFile.action?id='+row.id,
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
										function updateItemAttachFile(){
											var row = $('#itemAttach').datagrid('getSelected');
											if(row){
												$.ajax({
													  url:'${vix}/mdm/item/itemAttachFileAction!downloadAttachedFile.action?id=' + row.id,
													  cache: false,
													  success: function(html){
														  showMessage(html);
													  }
												});
											}else{
												showMessage("请选择一条数据!");
											}
										}
										function addItemAttachFile(){
											$.ajax({
												  url:'${vix}/mdm/item/itemAttachFileAction!goSaveOrUpdate.action?id='+$("#id").val(),
												  cache: false,
												  success: function(html){
													  asyncbox.open({
														 	modal:true,
															width : 660,
															height : 360,
															title:"附件信息",
															html:html,
															callback : function(action){
																if(action == 'ok'){
																	$("#itemAttachFileForm").ajaxSubmit({
																	     type: "post",
																	     url: "${vix}/mdm/item/itemAttachFileAction!uploadAttachment.action?id=${item.id}",
																	     dataType: "text",
																	     success: function(result){
																	    	 showMessage(result);
																    		$('#itemAttach').datagrid("load");
																		    	
																	     }
																	 });
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
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>