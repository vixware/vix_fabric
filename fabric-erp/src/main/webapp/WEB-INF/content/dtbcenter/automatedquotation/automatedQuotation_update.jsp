<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var j = 1;
	function itemSave(href, k) {
		$(".jd li a").each(function(i) {
			$(this).attr("class", "");
		});
		$(".order_table").each(function(i) {
			$(this).attr("style", "display: none;");
		});
		$(href).attr("class", "current");
		$('#a' + k).attr('style', '');
		tab(4, k, 'a', event);
		j = href.id;
	}
	function step() {
		if (j < 4)
			j++;
		var next = document.getElementById(j);
		itemSave(next, j);
	}
	function stepup() {
		if (j > 1 && j <= 4)
			j--;
		var next = document.getElementById(j);
		itemSave(next, j);
	}
</script>

<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/receiveorders.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="ldm_dtbcenter_management" /> </a></li>
				<li><a href="#"><s:text name="ldm_order_receiving" /> </a></li>
				<li><a href="#"><s:text name="ldm_automated_quotation" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="order">
		<dl>
			<dt id="orderTieleFixd">
				<span class="no_line"> <a onclick="step();" href="javascript:void(0);"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a onclick="stepup();" href="javascript:void(0);"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
				</span> <strong> <b><s:text name="ldm_automated_quotation" /> </b>
				</strong>
			</dt>
			<dd class="clearfix">
				<div id="wizard" class="swMain">
					<ul class="clearfix jd jd4">
						<li><a id="1" href="#" onclick="itemSave(this,1);javascript:$('#a1').attr('style','');tab(4,1,'a',event);" class="current">1 客户信息<span></span>
						</a></li>
						<li><a id="2" href="#" onclick="itemSave(this,2);javascript:$('#a2').attr('style','');tab(4,2,'a',event);">2 客户订单<span></span>
						</a></li>
						<li><a id="3" href="#" onclick="itemSave(this,3);javascript:$('#a3').attr('style','');tab(4,3,'a',event);">3 配载资源<span></span>
						</a></li>
						<li><a id="4" href="#" onclick="itemSave(this,4);javascript:$('#a4').attr('style','');tab(4,4,'a',event);">4报价单</a></li>
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
					</div>
				</div>
			</dd>
		</dl>
	</div>
</div>