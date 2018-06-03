<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//面包屑
	if ($('.sub_menu').length) {
		$("#breadCrumb").jBreadCrumb();
	}
	var orderItemStr = "";
	var j = 1;
	function itemSave(count, isDorU) {
		if (count == 1) {
			if ($('#supplierAptitudeInfoForm').validationEngine('validate')) {
				$.post('${vix}/srm/registeredSupplierAction!saveOrUpdateSupplierAptitudeInfo.action', {
				'supplierAptitudeInfo.id' : $("#supplierAptitudeInfo_id").val(),
				'supplierAptitudeInfo.name' : $("#supplierAptitudeInfo_name").val(),
				'supplierAptitudeInfo.files' : $("#supplierAptitudeInfo_files").val(),
				'supplierAptitudeInfo.validPeriod' : $("#supplierAptitudeInfo_validPeriod").val(),
				'supplierAptitudeInfo.status' : $("#supplierAptitudeInfo_satus").val(),
				'supplierAptitudeInfo.description' : $("#supplierAptitudeInfo_description").val(),
				'orderItemStr' : orderItemStr
				}, function(result) {
					if (null == $("#supplierAptitudeInfo_id").val() || "" == $("#supplierAptitudeInfo_id").val()) {
						var rt = result.split(",");
						showMessage(rt[0] + rt[2]);
						setTimeout("", 1000);
						$("#supplierAptitudeInfo_id").val(rt[1]);
					} else {
						showMessage(result);
						setTimeout("", 1000);
					}
					$("#1").attr("class", "current");
					$("#a1").show();
					$("#2").attr("class", "");
					$("#a2").hide();
					$("#3").attr("class", "");
					$("#a3").hide();
					$("#4").attr("class", "");
					$("#a4").hide();
				});
			}
		}
		if (count == 2) {
			if (isDorU == "Dn") {
				if ($('#supplierForm').validationEngine('validate')) {
					$.post('${vix}/srm/registeredSupplierAction!saveOrUpdateSupplier.action', {
					'supplier.id' : $("#supplier_id").val(),
					'supplier.name' : $("#supplier_name").val(),
					'supplier.shortName' : $("#supplier_shortName").val(),
					'supplier.artificialPerson' : $("#supplier_artificialPerson").val(),
					'supplier.contacts' : $("#supplier_contacts").val(),
					'supplier.langCode' : $("#supplier_langCode").val(),
					'supplier.region' : $("#supplier_region").val(),
					'supplier.email' : $("#supplier_email").val(),
					'supplier.telephone' : $("#supplier_telephone").val(),
					'supplier.portraiture' : $("#supplier_portraiture").val(),
					'supplier.postalcode' : $("#supplier_postalcode").val(),
					'supplier.description' : $("#supplier_description").val(),
					'orderItemStr' : orderItemStr
					}, function(result) {
						if (null == $("#supplier_id").val() || "" == $("#supplier_id").val()) {
							var rt = result.split(",");
							showMessage(rt[0] + rt[2]);
							setTimeout("", 1000);
							$("#supplier_id").val(rt[1]);
						} else {
							showMessage(result);
							setTimeout("", 1000);
						}
						$("#1").attr("class", "");
						$("#a1").hide();
						$("#2").attr("class", "current");
						$("#a2").show();
						$("#3").attr("class", "");
						$("#a3").hide();
						$("#4").attr("class", "");
						$("#a4").hide();
					});
				}
			} else {
				if ($('#itemForm').validationEngine('validate')) {
					$.post('${vix}/srm/registeredSupplierAction!saveOrUpdateItem.action', {
					'item.id' : $("#item_id").val(),
					'item.name' : $("#item_name").val(),
					'item.type' : $("#item_type").val(),
					'item.catalog' : $("#item_catalog").val(),
					'item.specification' : $("#item_specification").val(),
					'orderItemStr' : orderItemStr
					}, function(result) {
						if (null == $("#item_id").val() || "" == $("#item_id").val()) {
							var rt = result.split(",");
							showMessage(rt[0] + rt[2]);
							setTimeout("", 1000);
							$("#item_id").val(rt[1]);
						} else {
							showMessage(result);
							setTimeout("", 1000);
						}
						$("#1").attr("class", "");
						$("#a1").hide();
						$("#2").attr("class", "current");
						$("#a2").show();
						$("#3").attr("class", "");
						$("#a3").hide();
						$("#4").attr("class", "");
						$("#a4").hide();
					});
				}
			}
		}
		if (count == 3) {
			if (isDorU == "Dn") {
				if ($('#supplierAptitudeInfoForm').validationEngine('validate')) {
					$.post('${vix}/srm/registeredSupplierAction!saveOrUpdateSupplierAptitudeInfo.action', {
					'supplierAptitudeInfo.id' : $("#supplierAptitudeInfo_id").val(),
					'supplierAptitudeInfo.name' : $("#supplierAptitudeInfo_name").val(),
					'supplierAptitudeInfo.files' : $("#supplierAptitudeInfo_files").val(),
					'supplierAptitudeInfo.validPeriod' : $("#supplierAptitudeInfo_validPeriod").val(),
					'supplierAptitudeInfo.status' : $("#supplierAptitudeInfo_satus").val(),
					'supplierAptitudeInfo.description' : $("#supplierAptitudeInfo_description").val(),
					'orderItemStr' : orderItemStr
					}, function(result) {
						if (null == $("#supplierAptitudeInfo_id").val() || "" == $("#supplierAptitudeInfo_id").val()) {
							var rt = result.split(",");
							showMessage(rt[0] + rt[2]);
							setTimeout("", 1000);
							$("#supplierAptitudeInfo_id").val(rt[1]);
						} else {
							showMessage(result);
							setTimeout("", 1000);
						}
						$("#1").attr("class", "");
						$("#a1").hide();
						$("#2").attr("class", "");
						$("#a2").hide();
						$("#3").attr("class", "current");
						$("#a3").show();
						$("#4").attr("class", "");
						$("#a4").hide();
					});
				}
			} else {
				$("#1").attr("class", "");
				$("#a1").hide();
				$("#2").attr("class", "");
				$("#a2").hide();
				$("#3").attr("class", "current");
				$("#a3").show();
				$("#4").attr("class", "");
				$("#a4").hide();
			}
		}
		if (count == 4) {
			if (isDorU == "Dn") {
				if ($('#itemForm').validationEngine('validate')) {
					$.post('${vix}/srm/registeredSupplierAction!saveOrUpdateItem.action', {
					'item.id' : $("#item_id").val(),
					'item.name' : $("#item_name").val(),
					'item.type' : $("#item_type").val(),
					'item.catalog' : $("#item_catalog").val(),
					'item.specification' : $("#item_specification").val(),
					'orderItemStr' : orderItemStr
					}, function(result) {
						if (null == $("#item_id").val() || "" == $("#item_id").val()) {
							var rt = result.split(",");
							showMessage(rt[0] + rt[2]);
							setTimeout("", 1000);
							$("#item_id").val(rt[1]);
						} else {
							showMessage(result);
							setTimeout("", 1000);
						}
						$("#1").attr("class", "");
						$("#a1").hide();
						$("#2").attr("class", "");
						$("#a2").hide();
						$("#3").attr("class", "");
						$("#a3").hide();
						$("#4").attr("class", "current");
						$("#a4").show();
					});
				}
			}
		}
	}
	function step() {
		if (j <= 4) {
			j++;
			itemSave(j, "Dn");
			$("#btnUp").bind('click', function() {
				stepup();
			});
		} else {
			return null;
		}
	}
	function stepup() {
		if (j > 0 && j <= 4) {
			j--;
			itemSave(j, "Up");
		} else {
			$("#btnUp").unbind('click', function() {
				stepup();
			});
		}
	}
	$("#supplierForm").validationEngine();
	$("#supplierAptitudeInfoForm").validationEngine();
	$("#itemForm").validationEngine();
</script>

<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/srm_supplier.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#">供应商关系管理</a></li>
				<li><a href="#">供应商门户</a></li>
				<li><a href="#" onclick="loadContent('${vix}/srm/registeredSupplierAction!goSaveOrUpdate.action');">网上自行注册</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop" style="display: none;">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"> <span><s:text name="cmn_add" /></span>
			</a> <a href="#" onclick="deleteByIds(0,$('#selectId').val());"> <span><s:text name='cmn_delete' /></span>
			</a>
		</p>
	</div>
</div>
<div class="content">
	<div class="order">
		<dl>
			<dt id="orderTitleFixd">
				<span class="no_line"> <a onclick="saveOrUpdate()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
					onclick="loadContent('${vix}/srm/registeredSupplierAction!goSaveOrUpdate.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>网上自行处理</b></strong>
			</dt>
			<dd class="clearfix">
				<div id="wizard" class="swMain">
					<ul class="clearfix jd jd4">
						<li><a id="1" href="#" onclick="itemSave(this);" class="current">1 基本信息<span></span></a></li>
						<li><a id="2" href="#" onclick="itemSave(this);">2 资质信息<span></span></a></li>
						<li><a id="3" href="#" onclick="itemSave(this);">3 产品信息<span></span></a></li>
						<li><a id="4" href="#" onclick="itemSave(this);">4 完成注册</a></li>
					</ul>
					<div class="order_table" id="a1">
						<input type="hidden" id="supplier_id" name="supplier_id" value="${supplier.id }" />
						<form id="supplierForm" action="">
							<table style="border: none; height: 350px;">
								<tr>
									<td align="right">名称：</td>
									<td><input id="supplier_name" name="" value="${supplier.name }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
									<td align="right">简称：</td>
									<td><input id="supplier_shortName" name="" value="${supplier.shortName }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								</tr>
								<tr>
									<td align="right">法人：</td>
									<td><input id="supplier_artificialPerson" name="" value="${supplier.artificialPerson }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
									<td align="right">联系人：</td>
									<td><input id="supplier_contacts" name="" value="${supplier.contacts }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								</tr>
								<tr>
									<td align="right">常用语言：</td>
									<td><input id="supplier_langCode" name="" value="${supplier.langCode }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
									<td align="right">所属地区：</td>
									<td><input id="supplier_region" name="" value="${supplier.region }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								</tr>
								<tr>
									<td align="right">电子邮件：</td>
									<td><input id="supplier_email" name="" value="${supplier.email }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
									<td align="right">联系电话：</td>
									<td><input id="supplier_telephone" name="" value="${supplier.telephone }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								</tr>
								<tr>
									<td align="right">传真电话：</td>
									<td><input id="supplier_portraiture" name="" value="${supplier.portraiture }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
									<td align="right">邮政编码：</td>
									<td><input id="supplier_postalcode" name="" value="${supplier.postalcode }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								</tr>
								<tr>
									<td align="right">备注：</td>
									<td colspan="3"><textarea id="supplier_description" name="" class="example" rows="3" style="width: 700px">${supplier.description }</textarea></td>
								</tr>
							</table>
						</form>
					</div>
					<div class="order_table" id="a2" style="display: none;">
						<input type="hidden" id="supplierAptitudeInfo_id" name="supplierAptitudeInfo_id" value="${supplierAptitudeInfo.id }" />
						<form id="supplierAptitudeInfoForm" action="">
							<table style="border: none; height: 250px;">
								<tr>
									<td align="right">资质名称：</td>
									<td><input id="supplierAptitudeInfo_name" name="" value="${supplierAptitudeInfo.name }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
									<td align="right">资质文件：</td>
									<td><input id="supplierAptitudeInfo_files" name="" value="${supplierAptitudeInfo.files }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								</tr>
								<tr>
									<td align="right">资质有效期：</td>
									<td><input id="supplierAptitudeInfo_validPeriod" name="${supplierAptitudeInfo.validPeriod }" value="" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
									<td align="right">状态：</td>
									<td><input id="supplierAptitudeInfo_satus" name="" value="${supplierAptitudeInfo.satus }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								</tr>
								<tr>
									<td align="right">描述：</td>
									<td colspan="3"><textarea id="supplierAptitudeInfo_description" name="" class="example" rows="3" style="width: 700px">${supplierAptitudeInfo.description }</textarea></td>
								</tr>
							</table>
						</form>
					</div>
					<div class="order_table" id="a3" style="display: none;">
						<input type="hidden" id="item_id" name="item_id" value="${item.id }" />
						<form id="itemForm" action="">
							<table style="border: none; height: 250px;">
								<tr>
									<td align="right">${vv:varView("vix_mdm_item")}名称：</td>
									<td><input id="item_name" name="" value="${item.name }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
									<td align="right">${vv:varView("vix_mdm_item")}类型：</td>
									<td><input id="item_type" name="" value="${item.type }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								</tr>
								<tr>
									<td align="right">${vv:varView("vix_mdm_item")}分类：</td>
									<td><input id="item_catalog" name="" value="${item.catalog }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
									<td align="right">规格型号：</td>
									<td><input id="item_specification" name="" value="${item.specification }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								</tr>
							</table>
						</form>
					</div>
					<div class="order_table" id="a4" style="display: none;">
						<table style="border: none; height: 250px;">
							<tr>
								<th align="center" valign="middle"><font color="red" size="5">恭喜，注册成功！</font></th>
							</tr>
						</table>
					</div>
				</div>
			</dd>
		</dl>
	</div>
	<!--oder-->
	<div class="sub_menu sub_menu_bot" style="display: none;">
		<div class="drop">
			<p>
				<a id="btnUp" href="#" class="buttonNext">上一步</a> <a id="btnDn" href="#" onclick="step();" class="buttonNext">下一步</a>
			</p>
		</div>
	</div>
</div>