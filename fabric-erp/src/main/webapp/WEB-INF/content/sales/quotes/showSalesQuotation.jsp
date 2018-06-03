<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script src="${vix}/common/js/LodopFuncs.js" type="text/javascript"></script>
<script type="text/javascript">
//打印
function goPrintDelivery(id) {
	$.ajax({
	url : '${vix}/sales/quotes/salesQuotationAction!goPrintSalesQuotation.action?id=' + id,
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
				<li><a href="#" onclick="loadContent('${vix}/sales/quotes/salesQuotationAction!goList.action');">销售报价</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="order">
		<dl>
			<dt id="orderTitleFixd">
				<span class="no_line"> <a href="#"><img width="22" height="22" title="禁用" src="${vix}/common/img/dt_disable.png" /> </a> <a href="#"><img width="22" height="22" title="锁定" src="${vix}/common/img/dt_locked.png" /> </a> <a href="#"><img width="22" height="22" title="删除" src="${vix}/common/img/dt_delete.png" /> </a> <a href="#"><img
						width="22" height="22" title="审批通过" src="${vix}/common/img/dt_aprroval.png" /> </a> <a href="#"><img width="22" height="22" title="驳回" src="${vix}/common/img/dt_reject.png"> </a> <a href="#" onclick="goShowBeforeAndAfter('${salesQuotation.id }','before');"><img width="22" height="22" title="上一条" src="${vix}/common/img/dt_before.png">
				</a> <a href="#" onclick="goShowBeforeAndAfter('${salesQuotation.id }','after');"><img width="22" height="22" title="下一条" src="${vix}/common/img/dt_next.png"> </a> <a href="#" onclick="goPrintDelivery('${salesQuotation.id}');"><img width="22" height="22" title="打印" src="${vix}/common/img/dt_print.png"> </a>
					<div class="ms" style="float: none; display: inline;">
						<a href="#"><img width="22" height="22" alt="关联单据" src="${vix}/common/img/dt_report.png"> </a>
						<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
							<li><a href="#" onclick="goPrintSalesQuotation('${salesQuotation.id}');"><img src="${vix}/common/img/icon_10.png" alt="">引用单据</a></li>
						</ul>
					</div> <a href="#"><img width="22" height="22" title="导出" src="${vix}/common/img/dt_export.png"> </a> <a href="#" onclick="loadContent('${vix}/sales/quotes/salesQuotationAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
				</span> <strong> <b> ${salesQuotation.quoteSubject} </b> <i></i>
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
										<td width="35%">${salesQuotation.code}</td>
										<td align="right" width="10%">报价主题:</td>
										<td width="40%">${salesQuotation.quoteSubject}</td>
									</tr>
									<tr>
										<td align="right">客户名称:</td>
										<td>${salesQuotation.customerAccount.name}</td>
										<td align="right">报价单类型:</td>
										<td>${salesQuotation.type}</td>
									</tr>
									<tr>
										<td align="right">部门:</td>
										<td>${salesQuotation.dept.fullName}</td>
										<td align="right">销售组织:</td>
										<td>${salesQuotation.salesOrg.fullName}</td>
									</tr>
									<tr>
										<td align="right">业务类型:</td>
										<td>${salesQuotation.bizType}</td>
										<td align="right">地域:</td>
										<td>${salesQuotation.regional.name}</td>
									</tr>
									<tr>
										<td align="right">城市:</td>
										<td>${salesQuotation.city}</td>
										<td align="right">税率 :</td>
										<td>${salesQuotation.tax}%范围(1-100)</td>
									</tr>
									<tr>
										<td align="right">交货日期:</td>
										<td><s:property value='formatDateToString(salesQuotation.dilveryDate)' /></td>
										<td align="right">单据日期:</td>
										<td><s:property value='formatDateToString(salesQuotation.billDate)' /></td>
									</tr>
									<tr>
										<td align="right">报价有效期从:</td>
										<td><s:property value='formatDateToString(salesQuotation.validQuotationFrom)' /></td>
										<td align="right">报价有效期至:</td>
										<td><s:property value='formatDateToString(salesQuotation.validQuotationTo)' /></td>
									</tr>
									<tr>
										<td align="right">备注:</td>
										<td colspan="3">${salesQuotation.memo}</td>
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
						<li class="current"><a href="###"><img src="${vix}/common/img/mail.png" alt="" />报价单明细</a></li>
					</ul>
				</div>
				<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
					<div style="padding: 8px;">
						<table id="salesQuotationItem" class="easyui-datagrid" style="height: 380px; width: 680px; margin: 6px;" data-options="fitColumns:true,singleSelect: true,url: '${vix}/sales/quotes/salesQuotationAction!getSalesQuotationItemJson.action?id=${salesQuotation.id}'">
							<thead>
								<tr>
									<th data-options="field:'id',align:'center',width:60">编号</th>
									<th data-options="field:'itemCode',width:150,align:'center'">编码</th>
									<th data-options="field:'itemName',width:120,align:'center'">名称</th>
									<th data-options="field:'unit',width:120,align:'center'">计量单位</th>
									<th data-options="field:'amount',width:120,align:'center'">数量</th>
									<th data-options="field:'price',width:120,align:'center'">价格</th>
									<th data-options="field:'netPrice',width:120,align:'center'">无税价格</th>
									<th data-options="field:'taxPrice',width:120,align:'center'">含税价格</th>
									<th data-options="field:'memo',width:180,align:'center'">备注</th>
								</tr>
							</thead>
						</table>
						<script type="text/javascript">
							$('#salesQuotationItem').datagrid();
						</script>
					</div>
				</div>
			</div>
			<dd class="clearfix">
				<div class="order_table">
					<table>
						<tr>
							<td width="15%" align="right">报价人:</td>
							<td width="35%">${salesQuotation.salesMan.name}</td>
							<td width="10%" align="right">金额:</td>
							<td width="40%">${salesQuotation.amount}</td>
						</tr>
					</table>
				</div>
			</dd>
		</dl>
	</div>
</div>
<!-- content -->
