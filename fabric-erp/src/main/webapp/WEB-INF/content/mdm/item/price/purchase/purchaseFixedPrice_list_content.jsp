<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#caTable tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#caTable tr:even").addClass("alt");
var gifts = "";
function radioCheck(price){
	gifts = "";
	$.each($("input[name^='gift_']"), function(i, n){
		$(n).attr('checked',false);
	});
	$.returnValue = price +":"+gifts;
}
$.returnValue = $(":radio[name=chkPriceId][checked]").val();
$(".untitled").hover(
		function () {
			$(this).css({ "position": "relative"});
			$(this).children('.popup').css({ "display": "block"});
			var bh = $("body").height(); 
			var pt = $(this).offset();
			if(( bh - pt.top) < 165){$(this).children('.popup').css({ "bottom": "0"});}else{$(this).children('.popup').css({ "top": "-7px"});};
	  	},
		function () {
			$(this).css({ "position": "static"});
			$(this).children('.popup').css({ "display": "none"});
	 	}
	);
	$(".close").click(
		function () {
			$(this).parent().parent().css({ "display": "none"});
		}
	);
function chooseGift(code){
	gifts = "";
	$.each($("input[name='"+code+"']"), function(i, n){
		if($(n).attr('checked')){
			gifts += $(this).val() + "_";
		}
	});
	$.returnValue = $(":radio[name=chkPriceId][checked]").val()+":"+gifts;
}
</script>
<table id="caTable" class="list">
	<tbody>
		<tr class="alt">
			<th>选择</th>
			<th>条件类型</th>
			<th>${vv:varView('vix_mdm_item')}名称</th>
			<th>类型</th>
			<th>原价</th>
			<th>折扣</th>
			<th>现价</th>
			<th>赠品</th>
		</tr>
		<% int count =0; %>
		<s:iterator value="priceFixEntityList" var="entity" status="s">
			<% count++; %>
			<s:if test="#s.count == 1">
				<tr>
					<td style="background-color: #d3f7fa;"><input id="chkPriceId" name="chkPriceId" value="${entity.disCountPrice}" type="radio" onclick="radioCheck(${entity.disCountPrice});" checked="checked" /></td>
					<td style="background-color: #d3f7fa;">${entity.type}</td>
					<td style="background-color: #d3f7fa;">${entity.name}</td>
					<td style="background-color: #d3f7fa;">${entity.discountType}</td>
					<td style="background-color: #d3f7fa;">${entity.price}</td>
					<td style="background-color: #d3f7fa;">${entity.disCount}</td>
					<td style="background-color: #d3f7fa;">${entity.disCountPrice}</td>
					<td style="background-color: #d3f7fa;"><s:if test="#entity.hasGifts">
							<div class="untitled" style="position: static; display: inline;">
								<span><img alt="" src="img/icon_untitled.png"></span>
								<div class="popup" style="display: none; top: -7px;">
									<strong> <i class="close"><a href="###"></a></i> <b>赠品列表</b>
									</strong>
									<p>
										<s:iterator value="#entity.pfgList" var="gift">
											<input type="checkbox" name="gift_${s.count}" value="${gift.id}-,${gift.specification}-${gift.count}-${gift.price}" onchange="chooseGift('gift_${s.count}');">
											<span style="font-weight: bold; width: 50px;">名称：${gift.name}</span>
											<span style="font-weight: bold; width: 50px;">规格：${gift.specification}</span>
											<span style="font-weight: bold; width: 50px;">数量：${gift.count}</span>
											<span style="font-weight: bold; width: 50px;">价格：${gift.price}</span>
											<br />
										</s:iterator>
									</p>
								</div>
							</div>
						</s:if></td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td><input id="chkPriceId" name="chkPriceId" value="${entity.disCountPrice}" type="radio" onclick="radioCheck(${entity.disCountPrice});" /></td>
					<td>${entity.type}</td>
					<td>${entity.name}</td>
					<td>${entity.discountType}</td>
					<td>${entity.price}</td>
					<td>${entity.disCount}</td>
					<td>${entity.disCountPrice}</td>
					<td><s:if test="#entity.hasGifts">
							<div class="untitled" style="position: static; display: inline;">
								<span><img alt="" src="img/icon_untitled.png"></span>
								<div class="popup" style="display: none; top: -7px;">
									<strong> <i class="close"><a href="###"></a></i> <b>赠品列表</b>
									</strong>
									<p>
										<s:iterator value="#entity.pfgList" var="gift">
											<input type="checkbox" name="gift_${s.count}" value="${gift.id}-,${gift.specification}-${gift.count}-${gift.price}" onchange="chooseGift('gift_${s.count}');">
											<span style="font-weight: bold; width: 50px;">名称：${gift.name}</span>
											<span style="font-weight: bold; width: 50px;">规格：${gift.specification}</span>
											<span style="font-weight: bold; width: 50px;">数量：${gift.count}</span>
											<span style="font-weight: bold; width: 50px;">价格：${gift.price}</span>
											<br />
										</s:iterator>
									</p>
								</div>
							</div>
						</s:if></td>
				</tr>
			</s:else>
		</s:iterator>
		<%	/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			java.util.List<com.vix.mdm.item.action.transport.PriceFixEntity> list = (java.util.List<com.vix.mdm.item.action.transport.PriceFixEntity>)request.getAttribute("priceFixEntityList");
			count = 10 -list.size();
			if(count < 0){
				count = 0;
			}
			request.setAttribute("count",count);
		%>
		<c:forEach begin="1" end="${count}">
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>