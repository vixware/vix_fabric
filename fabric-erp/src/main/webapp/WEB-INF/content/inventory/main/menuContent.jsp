<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventory/initInventoryAction!goList.action');"><img src="${vix}/common/img/inv_initialinventory.png" alt="" />期初录入</a></li>
<li><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goSaveOrUpdate.action');"><img src="${vix}/common/img/inv_inbound.png" alt="" />库存管理</a></li>
<li><a href="#" onclick="loadContent('${vix}/inventory/standingBookAction!goList.action');"><img src="${vix}/common/img/inv_standingbook.png" alt="" />台账管理</a></li>
<li><a href="#" onclick="loadContent('${vix}/inventory/allocateTransferAction!goList.action');"><img src="${vix}/common/img/inv_transfer.png" alt="" />调拨管理</a></li>
<li><a href="#" onclick="loadContent('${vix}/inventory/takeStockAction!goList.action');"><img src="${vix}/common/img/inv_takestock.png" alt="" />盘点管理</a></li>
<li><a href="#" onclick="loadContent('${vix}/inventory/warehouseAction!goList.action');"><img src="${vix}/common/img/inv_inbound.png" alt="" />仓库管理</a></li>
