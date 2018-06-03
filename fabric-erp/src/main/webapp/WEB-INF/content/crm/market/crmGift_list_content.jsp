<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
 
function deleteEntity(id){
	asyncbox.confirm('确定要删除该礼品么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}
function chooseAll(chk){
	if(null == chk){
		$("input[name='chkId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkId']").attr("checked", true);
		}else{
			$("input[name='chkId']").attr("checked", false);
		}
	}
	selectCount();
}

function selectCount(){
	var selectCount = 0;
	$.each($("input[name='chkId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectCount1").html(selectCount);
	$("#selectCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkIds']").attr("checked", false);
	}else{
		$("input[name='chkIds']").attr("checked", true);
	}
}

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
loadOrderByImage("${vix}","crmGift");
</script>
<input type="hidden" id="crmGiftTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="crmGiftPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="crmGiftTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="crmGiftOrderField" value="${pager.orderField}" />
<input type="hidden" id="crmGiftOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="crmGiftInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="all" /></a></li>
							<li><a href="#"><s:text name="other" /></a></li>
							<li><a href="#"><s:text name="close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th onclick="orderBy('id');" width="8%">编号&nbsp;<img id="crmGiftzid" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('name');" width="15%">名称&nbsp;<img id="crmGiftImg_name" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('unitPrice');" width="10%">单价&nbsp;<img id="crmGiftImg_unitPrice" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('buyCount');" width="10%">数量&nbsp;<img id="crmGiftImg_buyCount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('stockNumber');" width="10%">库存数量&nbsp;<img id="crmGiftImg_stockNumber" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('giftCompany');" width="15%">礼品厂商&nbsp;<img id="crmGiftImg_giftCompany" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('contactPerson');" width="10%">接洽人&nbsp;<img id="crmGiftImg_contactPerson" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('contactStyle');" width="10%">联系方式&nbsp;<img id="crmGiftImg_contactStyle" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%">操作</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.name}</td>
				<td>${entity.unitPrice}</td>
				<td>${entity.buyCount}</td>
				<td>${entity.stockNumber}</td>
				<td>${entity.giftCompany}</td>
				<td>${entity.contactPerson}</td>
				<td>${entity.contactStyle}</td>
				<td><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='delete'/>"> <img src="${vix}/common/img/icon_12.png" />
				</a></td>
			</tr>
		</s:iterator>
		<%	/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			com.vix.core.web.Pager pager = (com.vix.core.web.Pager)request.getAttribute("pager");
			count = pager.getPageSize() - count;
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>