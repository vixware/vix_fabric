<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateOrder() {
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/costsSetAction!saveOrUpdate.action', {
			'costItem.compoundTypes' : $(":radio[name=compoundTypes][checked]").val(),
			'costItem.id' : $("#id").val(),
			'costItem.costCode' : $("#costCode").val(),
			'costItem.costName' : $("#costName").val(),
			'costItem.groupCode' : $("#groupCode").val(),
			'costItem.costType' : $("#costType").val(),
			'costItem.calculationFormula' : $("#calculationFormula").val(),
			'costItem.payments' : $("#payments").val(),
			'costItem.unit' : $("#unit").val(),
			'costItem.memo' : $("#memo").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/costsSetAction!goList.action');
			});
		}
	}
	$("#order").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_option.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="dtbcenter" /> </a></li>
				<li><a href="#">设置 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/costsSetAction!goList.action');">费用设定 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="costItem.id" value="${costItem.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"
						onclick="loadContent('${vix}/dtbcenter/costsSetAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>费用设定 </b> </strong>
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
											<th>代码：</th>
											<td><input id="costCode" name="costCode" value="${costItem.costCode }" type="text" size="20" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>名称：</th>
											<td><input id="costName" name="costName" value="${costItem.costName}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>类型：</th>
											<td><select id="costType" name="costType" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1" selected="selected">直接成本</option>
													<option value="2">间接成本</option>
											</select><span style="color: red;">*</span></td>
											<th>组别代码：</th>
											<td><input id="groupCode" name="groupCode" value="${costItem.groupCode}" type="text" size="20" /></td>
										</tr>
										<tr>
											<th>复合类型：</th>
											<td><input id="compoundTypes1" name="compoundTypes" type="radio" value="是" />是 <input id="compoundTypes2" name="compoundTypes" type="radio" value="否" checked="checked" />否</td>
											<th>计算公式：</th>
											<td><input id="calculationFormula" name="calculationFormula" value="${costItem.calculationFormula}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>费用额度：</th>
											<td><input id="payments" name="payments" value="${costItem.payments }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<th>单位：</th>
											<td><input id="unit" name="unit" value="${costItem.unit }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>状态：</th>
											<td><select id="status" name="status" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1" selected="selected">启用</option>
													<option value="2">停用</option>
											</select><span style="color: red;">*</span></td>
											<th>备注：</th>
											<td><input id="memo" name="memo" value="${costItem.memo }" type="text" size="30"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>