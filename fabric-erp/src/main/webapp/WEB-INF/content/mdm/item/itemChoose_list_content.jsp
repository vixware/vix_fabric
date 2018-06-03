<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#itemChoose tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#itemChoose tr:even").addClass("alt");
 
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
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","itemForChoose");
</script>
<input type="hidden" id="itemForChooseTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="itemForChoosePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="itemForChooseTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="itemForChooseOrderField" value="${pager.orderField}" />
<input type="hidden" id="itemForChooseOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="itemForChooseInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="itemChoose" class="list">
	<tbody>
		<tr class="alt">
			<th width="50" style="height: 20px;">选择</th>
			<th style="cursor: pointer;" onclick="orderBy('code');">编码&nbsp;<img id="itemForChooseImg_code" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('name');">名称&nbsp;<img id="itemForChooseImg_name" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('serialCode');">序列号&nbsp;<img id="itemForChooseImg_serialCode" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('price');">价格&nbsp;<img id="itemForChooseImg_price" src="${vix}/common/img/arrow.gif">
			</th>
			<!-- <th>规格</th> -->
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td style="height: 20px;"><input id="chkId" name="chkId" value="${entity.id}" type="radio" onchange="chkChange(this,'${entity.id}','${entity.code}','${entity.name}','${entity.price}');" /></td>
				<td>${entity.code}&nbsp;</td>
				<td>${entity.name}</td>
				<td>${entity.serialCode}</td>
				<td>${entity.price}</td>
				<%-- <td>
					<div class="untitled" style="position: static;display: inline;">
						<span><img alt="" src="img/icon_untitled.png"></span>
						<div class="popup" style="display: none; top: -7px;">
							<strong>
								<i class="close" style="cursor: pointer;"></i>
								<b>规格明细</b>
							</strong>
							<p id="itemSpecDetail_${entity.id}"></p>
						</div>
					</div>
				</td> --%>
			</tr>
		</s:iterator>
		<%	/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			com.vix.core.web.Pager pager = (com.vix.core.web.Pager)request.getAttribute("pager");
			count = pager.getPageSize() - count;
			request.setAttribute("count",count);
		%>
		<c:forEach begin="1" end="${count}">
			<tr>
				<td style="height: 20px;">&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<!-- <td>&nbsp;</td> -->
			</tr>
		</c:forEach>
	</tbody>
</table>