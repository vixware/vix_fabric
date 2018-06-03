<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<table class="list">
	<tbody>
		<tr class="alt" style="background: none repeat scroll 0 0 #f1f1f1; border-bottom: 1px solid #dfdfdf; font-weight: 700; line-height: 30px; padding: 0 3px;">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"> </label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /> </a></li>
							<li><a href="#"><s:text name="cmn_other" /> </a></li>
							<li><a href="#"><s:text name="cmn_close" /> </a></li>
						</ul>
					</div>
				</div>
			</th>
			<th style="text-align: left;">订单编码</th>
			<th style="text-align: left;">买家昵称</th>
			<th style="text-align: left;">实付金额</th>
			<th style="text-align: left;">购买数量</th>
			<th style="text-align: left;">下单时间</th>
			<th style="text-align: left;">买家留言</th>
			<th style="text-align: left;">收货地址</th>
			<th style="text-align: left;" width="5%">操作</th>
		</tr>
		<s:iterator value="orderList" var="entity" status="s">
			<tr class="">
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${entity.outerId}</td>
				<td>${entity.buyerNick}</td>
				<td>${entity.totalFee}</td>
				<td>${entity.num}</td>
				<td>${entity.createTime}</td>
				<td>${entity.buyerMessage}</td>
				<td>${entity.receiverAddress}</td>
				<td><a href="#" onclick="goPrintDelivery('${entity.id}');" title="打印发货单"> <img src="${vix}/common/img/print_delivery.png" />
				</a> <a href="#" title="打印快递单" onclick="bahtPreviewPrintExpressList('${entity.id}');"><img src="${vix}/common/img/print_express.png" /></a></td>
			</tr>
		</s:iterator>
	</tbody>
</table>