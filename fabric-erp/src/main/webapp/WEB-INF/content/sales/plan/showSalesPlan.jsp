<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script src="${vix}/common/js/LodopFuncs.js" type="text/javascript"></script>
<script type="text/javascript">
//打印
function goPrintSalesPlan(id) {
	$.ajax({
	url : '${vix}/sales/plan/salePlanAction!goPrintSalesPlan.action?id=' + id,
	cache : false,
	success : function(html) {
		LODOP = getLodop();
		LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", html);
		LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
		LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);// 打印后自动关闭预览窗口
		LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
		// LODOP.SET_PRINT_PAGESIZE(3,"240mm","45mm","");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
		LODOP.SET_PREVIEW_WINDOW(1, 0, 0, 1024, 550, ""); // 2上下打印工具条都显示
		/* LODOP.PRINT(); */
		LODOP.PREVIEW();
	}
	});
};

</script>
<div class="sub_menu">
	<h2>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img style="width: 14px; height: 14px;" src="${vix}/common/img/sale/saleQuotes.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/plan/salePlanAction!goList.action');">销售计划</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="order">
		<dl>
			<dt id="orderTitleFixd">
				<span class="no_line"> <a href="#"><img width="22" height="22" title="禁用" src="${vix}/common/img/dt_disable.png" /> </a> <a href="#"><img width="22" height="22" title="锁定" src="${vix}/common/img/dt_locked.png" /> </a> <a href="#"><img width="22" height="22" title="删除" src="${vix}/common/img/dt_delete.png" /> </a> <a href="#"><img
						width="22" height="22" title="审批通过" src="${vix}/common/img/dt_aprroval.png" /> </a> <a href="#"><img width="22" height="22" title="驳回" src="${vix}/common/img/dt_reject.png"> </a> <a href="#" onclick="goShowBeforeAndAfter('${salePlan.id }','before');"><img width="22" height="22" title="上一条" src="${vix}/common/img/dt_before.png"> </a>
					<a href="#" onclick="goShowBeforeAndAfter('${salePlan.id }','after');"><img width="22" height="22" title="下一条" src="${vix}/common/img/dt_next.png"> </a> <a href="#" onclick="goPrintSalesPlan('${salePlan.id}');"><img width="22" height="22" title="打印" src="${vix}/common/img/dt_print.png"> </a>
					<div class="ms" style="float: none; display: inline;">
						<a href="#"><img width="22" height="22" alt="关联单据" src="${vix}/common/img/dt_report.png"> </a>
						<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
							<li><a href="#" onclick="goPrintSalesQuotation('${salePlan.id}');"><img src="${vix}/common/img/icon_10.png" alt="">引用单据</a></li>
						</ul>
					</div> <a href="#"><img width="22" height="22" title="导出" src="${vix}/common/img/dt_export.png"> </a> <a href="#" onclick="loadContent('${vix}/sales/plan/salePlanAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
				</span> <strong> <b> ${salePlan.name} </b> <i></i>
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
									<s:hidden id="id" name="salePlan.id" value="%{salePlan.id}" theme="simple" />
									<tr>
										<td align="right">计划名称:&nbsp;</td>
										<td>${salePlan.name}</td>
									</tr>
									<tr>
										<td align="right">上级计划:&nbsp;</td>
										<td>${salePlan.parentSalePlan.name}<s:hidden id="parentSalePlanId" name="parentSalePlanId" value="%{salePlan.parentSalePlan.id}" theme="simple" />
										</td>
										<td align="right">销售人员:&nbsp;</td>
										<td>${salePlan.salesMan.name}<s:hidden id="salesManId" name="salesManId" value="%{salePlan.salesMan.id}" theme="simple" />
										</td>
									</tr>
									<tr>
										<td align="right">销售组织:&nbsp;</td>
										<td>${salePlan.saleOrg.fs}<s:hidden id="saleOrgId" name="saleOrgId" value="%{salePlan.saleOrg.id}" theme="simple" />
										</td>
										<td align="right">销售部门:</td>
										<td>${salePlan.departmet.fullName}<s:hidden id="departmentId" name="departmetId" value="%{salePlan.departmet.id}" theme="simple" />
										</td>
									</tr>
									<tr>
										<td align="right">${vv:varView('vix_mdm_item')}名称:&nbsp;</td>
										<td>${salePlan.item.name}<s:hidden id="itemId" name="itemId" value="%{salePlan.item.id}" theme="simple" />
										</td>
										<td align="right">${vv:varView('vix_mdm_item')}规格:</td>
										<td>${salePlan.specifications}</td>
									</tr>
									<tr>
										<td align="right">数量:&nbsp;</td>
										<td>${salePlan.amount}</td>
										<td align="right">计量单位:&nbsp;</td>
										<td>${salePlan.measureUnitGroup.name}</td>
									</tr>
									<tr>
										<td align="right">计量单位:&nbsp;</td>
										<td>${salePlan.measureUnit.name}</td>
										<td align="right">辅助计量单位:&nbsp;</td>
										<td>${salePlan.assitMeasureUnit.name}</td>
									</tr>
									<tr>
										<td align="right">生产组织:&nbsp;</td>
										<td>${salePlan.produceOrg}</td>
									</tr>
									<tr>
										<td align="right">时间:&nbsp;</td>
										<td>${salePlan.time}</td>
										<td align="right">周期:&nbsp;</td>
										<td>${salePlan.cycle}</td>
									</tr>
								</table>
							</dd>
						</dl>
					</div>
				</div>
			</dd>
		</dl>
	</div>
</div>