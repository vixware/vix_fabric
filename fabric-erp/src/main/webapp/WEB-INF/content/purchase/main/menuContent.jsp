<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<li><a href="#" onclick="purchaseGoPage('qk_pur_supplier');"> <img src="${vix}/common/img/srm_supplier.png" alt="" />供应商清单
</a></li>
<li><a href="#" onclick="purchaseGoPage('qk_pur_apply_add');"> <img src="${vix}/common/img/pur_apply.png" alt="" />新增采购申请
</a></li>
<li><a href="#" onclick="purchaseGoPage('qk_pur_inquire_add');"> <img src="${vix}/common/img/pur_inquire.png" alt="" />新增采购询价
</a></li>
<li><a href="#" onclick="purchaseGoPage('qk_pur_order_add');"> <img src="${vix}/common/img/pur_order.png" alt="" />新增采购订单
</a></li>
<li><a href="#" onclick="purchaseGoPage('qk_pur_arrival_add');"> <img src="${vix}/common/img/pur_arrival.png" alt="" />新增采购到货
</a></li>
<li><a href="#" onclick="purchaseGoPage('qk_pur_inbound_add');"> <img src="${vix}/common/img/pur_inbound.png" alt="" />新增采购入库
</a></li>
<li><a href="#" onclick="purchaseGoPage('qk_pur_return_add');"> <img src="${vix}/common/img/pur_return.png" alt="" />新增采购退货
</a></li>
<script type="text/javascript">
function purchaseGoPage(urlId){
	$('#'+urlId).click();
	//$('#breadCrumb').html('');
	//var menu = $('#nav_menu li a[onclick*="'+url+'"]');
	//alert(menu.length)
	//loadContent(url,'bg_02');
}
</script>