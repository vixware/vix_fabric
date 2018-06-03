<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="${vix}/common/js/LodopFuncs.js" type="text/javascript"></script>

<script type="text/javascript">
	var LODOP; //声明为全局变量 
	function prn1_preview() {
		CreateOneFormPage();
		LODOP.PREVIEW();
	};
	/* 直接打印 */
	function prn1_print() {
		CreateOneFormPage();
		LODOP.PRINT();
	};
	function CreateOneFormPage() {
		LODOP = getLodop();
		LODOP.SET_PRINT_MODE("WINDOW_DEFPRINTER", "HP LaserJet M1530 MFP Series PCL 6");
		LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
		LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", document.getElementById("div_print").innerHTML);
	};
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div id="div_print">
		<div>
			<table width="100%">
				<tbody>
					<tr>
						<td align="center" style="font-size: 15px">拣货单</td>
					</tr>
				</tbody>
			</table>
			<table width="100%" cellspacing="0" cellpadding="4" border="0">
				<tbody>
					<tr>
						<td width="50%" height="30" align="left" style="font-size: 10px">批次号:${pickingList.orderBatch.code}</td>
						<td width="50%" height="30" align="right" style="font-size: 10px">打印时间:<fmt:formatDate value="${pickingList.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
					</tr>
				</tbody>
			</table>
			<table width="100%" cellspacing="0" cellpadding="0" border="1" id="pickinglisttable">
				<tbody>
					<tr>
						<th width="7.5%" height="30" align="center">货位号</th>
						<th width="7.5%" height="30" align="center">拣货数量</th>
						<th width="15%" height="30" align="center">播种数量</th>
						<th width="15%" height="30" align="center">条码</th>
						<th width="55%" height="30" align="center">商品名称</th>
					</tr>
					<s:iterator value="pickingList.subpickingListDetails" var="orderDetail" status="s">
						<tr style="border-width: 1px; border-style: solid;">
							<td height="26" align="center">${orderDetail.invShelf.code}</td>
							<td height="26" align="center" style="font-size: 150%">${orderDetail.pickAmount}</td>
							<td height="26" align="center" style="font-size: 150%">${orderDetail.orderAmount}</td>
							<td height="26" align="center">${orderDetail.goodsCode}</td>
							<td height="26" align="center">${orderDetail.goodsName}</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<table width="100%" cellspacing="0" cellpadding="4" border="0">
				<tbody>
					<tr>
						<td width="30%" height="30" align="right" style="font-size: 10px">合计:</td>
						<td width="20%" height="30" style="font-size: 10px">${pickingList.packageTotal}</td>
						<td width="30%" height="30" align="right" style="font-size: 10px">拣货人:</td>
						<td width="20%" height="30" style="font-size: 10px">${pickingList.creator}</td>
					</tr>
					<tr>
						<td height="30" colspan="4" style="font-size: 10px">${pickingList.orderNoList}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- <br>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td align="center"><input name="" type="button" value="直接打印" class="btn-print" onclick="prn1_print();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="打印预览" class="btn-print" onclick="prn1_preview();" /></td>
			</tr>
		</table> -->
</div>
<style>
#pickinglisttable {
	font-size: 10px;
}

.btn-print {
	background: none repeat scroll 0 0 #336699;
	border: 2px solid #EFEFEF;
	color: #FFFFFF;
	cursor: pointer;
	font-size: 8px;
	font-weight: 700;
	line-height: 30px;
	text-align: center;
	width: auto;
	padding: 0 20px;
}
</style>