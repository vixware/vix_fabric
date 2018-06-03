<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	$("#billsTypeForm").validationEngine();
    $(function() {
    	if ($("#typeCode").val() == null) {
			$("#typeCode").val('${encodingRulesTableInTheMiddle.typeCode}');
		}
    });
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="billsTypeForm">
		<s:hidden id="billsTypeId" name="billsTypeId" value="%{billsType.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">分类:&nbsp;</td>
					<td><input type="hidden" id="billsPropertyId" name="billsPropertyId" value="${billsType.billsProperty.id}" /> <input type="text" id="categoryName" name="categoryName" value="${billsType.billsProperty.propertyName}" readonly="readonly" /></td>
				</tr>
				<tr height="40">
					<td align="right">类型编码:&nbsp;</td>
					<td><input type="text" id="typeCode" name="typeCode" class="validate[required] text-input" value="${billsType.typeCode}" /> <span style="color: red;">*</span></td>
					<td align="right">类型名称:&nbsp;</td>
					<td><input type="text" id="typeName" name="typeName" class="validate[required] text-input" value="${billsType.typeName}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">类型描述:&nbsp;</td>
					<td colspan="3"><input type="text" id="typeDescription" name="typeDescription" size="45" value="${billsType.typeDescription}" /></td>
				</tr>
			</table>
			<div class="npcontent clearfix">
				<div class="np_left_title">
					<h2>销售选项</h2>
				</div>
				<div id="por_right" class="clearfix" style="position: relative; margin-top: 15px;">
					<div id="lnp1">
						<ul class="np_tab clearfix">
							<li class="current" style="width: 80px; margin: 0 5px;"><a href="javascript:;" onclick="javascript:tab(5,1,'np',event)">业务控制</a></li>
							<li style="width: 80px; margin: 0 5px;"><a href="javascript:;" onclick="javascript:tab(5,2,'np',event)">其他控制</a></li>
							<li style="width: 80px; margin: 0 5px;"><a href="javascript:;" onclick="javascript:tab(5,3,'np',event)">信用控制</a></li>
							<li style="width: 80px; margin: 0 5px;"><a href="javascript:;" onclick="javascript:tab(5,4,'np',event)">可用量控制</a></li>
							<li style="width: 80px; margin: 0 5px;"><a href="javascript:;" onclick="javascript:tab(5,5,'np',event)">价格管理</a></li>
						</ul>
						<div class="np_clist" id="np1">
							<div class="nt">
								<div class="nt_title">基础选项</div>
								<div class="nt_line">
									<table>
										<tr>
											<td><label><input name="" type="checkbox" value="" />是否有零售日报业务</label></td>
											<td><label><input name="" type="checkbox" value="" />是否有销售调拨业务</label></td>
											<td><label><input name="" type="checkbox" value="" />是否有委托代销业务</label></td>
										</tr>
										<tr>
											<td><label><input name="" type="checkbox" value="" />是否有分期收款业务</label></td>
											<td><label><input name="" type="checkbox" value="" />是否有直运销售业务</label></td>
											<td><label><input name="" type="checkbox" value="" />是否有超订量发货控制</label></td>
										</tr>
										<tr>
											<td><label><input name="" type="checkbox" value="" />是否销售生成出库单</label></td>
											<td><label><input name="" type="checkbox" value="" />销售是否必填批号</label></td>
											<td><label><input name="" type="checkbox" value="" />报价是否含税</label></td>
										</tr>
										<tr>
											<td><label><input name="" type="checkbox" value="" />允许超发货量开票</label></td>
											<td><label><input name="" type="checkbox" value="" />订单变更保存历史记录</label></td>
											<td><label><input name="" type="checkbox" value="" />近期失效存货检查</label></td>
										</tr>
										<tr>
											<td><label><input name="" type="checkbox" value="" />折扣小数位数</label></td>
											<td><label><input name="" type="checkbox" value="" />红字单据允许红蓝混录</label></td>
											<td><label><input name="" type="checkbox" value="" />普通销售必有订单、委托代销必有订单、分期收款必有订单、直运销售必有订单</label></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<div class="np_clist" id="np2" style="display: none;">
							<div class="nt">
								<div class="nt_title">基础选项</div>
								<div class="nt_line">
									<table>
										<tr>
											<td><label><input name="" type="checkbox" value="" />新增发货默认</label></td>
											<td><label><input name="" type="checkbox" value="" />新增退货单默认</label></td>
											<td><label><input name="" type="checkbox" value="" />新增发票默认</label></td>
										</tr>
										<tr>
											<td><label><input name="" type="checkbox" value="" />追踪型存货参照对应入库单是否显示单价</label></td>
											<td colspan="2"><label><input name="" type="checkbox" value="" />订单批量生成发货时表体记录可生成的发货单数量</label></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<div class="np_clist" id="np3" style="display: none;">
							<div class="nt">
								<div class="nt_title">基础选项</div>
								<div class="nt_line">
									<table>
										<tr>
											<td><label><input name="" type="checkbox" value="" />是否有客户信用控制、是否有部门信用控制、是否有业务员信用控制</label></td>
											<td><label><input name="" type="checkbox" value="" />是否需要信用审批</label></td>
											<td><label><input name="" type="checkbox" value="" />信用控制范围</label></td>
										</tr>
										<tr>
											<td><label><input name="" type="checkbox" value="" />信用检查点</label></td>
											<td><label><input name="" type="checkbox" value="" />控制信用的单据</label></td>
											<td><label><input name="" type="checkbox" value="" />额度检查公式</label></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<div class="np_clist" id="np4" style="display: none;">
							<div class="nt">
								<div class="nt_title">基础选项</div>
								<div class="nt_line">
									<table>
										<tr>
											<td><label><input name="" type="checkbox" value="" />是否允许非批次存货超可用量发货</label></td>
											<td><label><input name="" type="checkbox" value="" />是否允许批次存货超可用量发货</label></td>
											<td><label><input name="" type="checkbox" value="" />发货单/发票非追踪型存货可用量控制公式</label></td>
										</tr>
										<tr>
											<td><label><input name="" type="checkbox" value="" />发货单/发票非追踪型存货可用量检查公式</label></td>
											<td colspan="2"><label><input name="" type="checkbox" value="" />订单追踪型存货可用量检查公式</label></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<div class="np_clist" id="np5" style="display: none;">
							<div class="nt">
								<div class="nt_title">基础选项</div>
								<div class="nt_line">
									<table>
										<tr>
											<td><label><input name="" type="checkbox" value="" />报价参照设置</label></td>
											<td><label><input name="" type="checkbox" value="" />价格参照过滤设置</label></td>
											<td><label><input name="" type="checkbox" value="" />是否有最低售价控制</label></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>