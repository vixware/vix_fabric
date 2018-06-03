<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateCompetingProducts() {
		if ($('#competingProductsForm').validationEngine('validate')) {
			$.post('${vix}/drp/competitiveProductsAction!saveOrUpdate.action', {
			'competingProducts.id' : $("#id").val(),
			'competingProducts.code' : $("#code").val(),
			'competingProducts.eanupc' : $("#eanupc").val(),
			'competingProducts.batchCode' : $("#batchCode").val(),
			'competingProducts.serialCode' : $("#serialCode").val(),
			'competingProducts.itemGroup' : $("#itemGroup").val(),
			'competingProducts.name' : $("#name").val(),
			'competingProducts.xcode' : $("#xcode").val(),
			'competingProducts.itemType' : $("#itemType").val(),
			'competingProducts.industry' : $("#industry").val(),
			'competingProducts.status' : $("#status").val(),
			'competingProducts.specification' : $("#specification").val(),
			'competingProducts.specDescription' : $("#specDescription").val(),
			'competingProducts.gernalName' : $("#gernalName").val(),
			'competingProducts.engishName' : $("#engishName").val(),
			'competingProducts.catalog' : $("#catalog").val(),
			'competingProducts.masterUnit' : $("#masterUnit").val(),
			'competingProducts.saleTax' : $("#saleTax").val(),
			'competingProducts.productCountry' : $("#productCountry").val(),
			'competingProducts.productEnterprise' : $("#productEnterprise").val(),
			'competingProducts.enterpriseCode' : $("#enterpriseCode").val(),
			'competingProducts.productLine' : $("#productLine").val(),
			'competingProducts.productGroup' : $("#productGroup").val(),
			'competingProducts.brand' : $("#brand").val(),
			'competingProducts.origin' : $("#origin").val(),
			'competingProducts.operator' : $("#operator").val(),
			'competingProducts.department' : $("#department").val(),
			'competingProducts.operateTime' : $("#operateTime").val(),
			'competingProducts.isStatics' : $("#isStatics").val(),
			'competingProducts.isValid' : $("#isValid").val(),
			'competingProducts.description' : editor.html(),
			'updateField' : updateField,
			'competingProducts.price' : $("#price").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/competitiveProductsAction!goList.action');
			});
		}
	}
	$("#competingProductsForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#"><s:text name="drp_market_research_management" /> </a></li>
				<li><a href="#">竞争信息管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/competitiveProductsAction!goList.action');">竞争产品管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="competingProducts.id" value="${competingProducts.id}" />
<div class="content">
	<form id="competingProductsForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateCompetingProducts()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/competitiveProductsAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b><s:text name="drp_competitive_products" /></b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th width="15%">编码：</th>
											<td width="35%"><input id="code" name="code" value="${competingProducts.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th width="15%">名称：</th>
											<td width="35%"><input id="name" name="name" value="${competingProducts.name}" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>通用名称：</th>
											<td><input id="gernalName" name="gernalName" value="${competingProducts.gernalName}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>英文名：</th>
											<td><input id="engishName" name="engishName" value="${competingProducts.engishName}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>规格型号：</th>
											<td><input id="specification" name="specification" value="${competingProducts.specification}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>价格：</th>
											<td><input id="price" name="price" value="${competingProducts.price}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>EAN/UPC：</th>
											<td><input id="eanupc" name="eanupc" value="${competingProducts.eanupc }" type="text" size="30" onchange="fieldChanged(this);"></td>
											<th>${vv:varView("vix_mdm_item")}类型：</th>
											<td><select id="itemType" name="itemType" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="类型1" selected="selected">类型1</option>
													<option value="类型2">类型2</option>
											</select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>主计量单位：</th>
											<td><input id="masterUnit" name="masterUnit" value="${competingProducts.masterUnit}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>销售税率：</th>
											<td><input id="saleTax" name="saleTax" value="${competingProducts.saleTax}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <span> </span> <strong>其他信息</strong>
								</dt>
								<dd>
									<table style="border: none;">
										<tr>
											<th width="15%">品牌：</th>
											<td width="35%"><input id="brand" name="brand" value="${competingProducts.brand}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th width="15%">产地：</th>
											<td width="35%"><input id="origin" name="origin" value="${competingProducts.origin}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>生产企业：</th>
											<td><input id="productEnterprise" name="productEnterprise" value="${competingProducts.productEnterprise}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>生产企业代码：</th>
											<td><input id="enterpriseCode" name="enterpriseCode" value="${competingProducts.enterpriseCode}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>部门：</th>
											<td><input id="department" name="department" value="${competingProducts.department}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>${vv:varView("vix_mdm_item")}状态：</th>
											<td><select id="status" name="status" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="类型1" selected="selected">类型1</option>
													<option value="类型2">类型2</option>
											</select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>是否统计：</th>
											<td><input id="isStatics" name="isStatics" value="${competingProducts.isStatics}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>是否有效：</th>
											<td><input id="isValid" name="isValid" value="${competingProducts.isValid}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>操作人：</th>
											<td><input id="operator" name="operator" value="${competingProducts.operator}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>操作时间：</th>
											<td><input id="operateTime" name="operateTime" value="${competingProducts.operateTime}" type="text" readonly="readonly" onchange="fieldChanged(this);" /><img onclick="showTime('operateTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>行业领域：</th>
											<td><input id="industry" name="industry" value="${competingProducts.industry}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>生产国别：</th>
											<td><input id="productCountry" name="productCountry" value="${competingProducts.productCountry}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th align="right">产品描述：</th>
											<td colspan="3"><textarea id="description" name="description" onchange="fieldChanged(this);">${competingProducts.description }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
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
										<tr>
											<th align="right">产品图片：</th>
											<td>选择本地图片：<input name="" type="file" onchange="fieldChanged(this);" /></td>
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