<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
			<th style="text-align: left;">住宿费标准</th>
			<th style="text-align: left;">房价/天</th>
			<th style="text-align: left;">天数</th>
			<th style="text-align: left;">住宿地点</th>
			<th style="text-align: left;">开始入住时间</th>
			<th style="text-align: left;">结束入住时间</th>
			<th style="text-align: left;">备注</th>
			<th style="text-align: left;" width="5%">操作</th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="accommodationFeeList" var="entity" status="s">
			<%
				count++;
			%>

			<tr class="">
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${entity.lodgingFee}</td>
				<td>${entity.housePrices}</td>
				<td>${entity.days}</td>
				<td>${entity.accomodationsPlaces}</td>
				<td>${fn:substring(entity.startAccomodationsTime, 0,19)}</td>
				<td>${fn:substring(entity.endAccomodationsTime, 0,19)}</td>
				<td>${entity.memo}</td>
				<td><a href="#" onclick="saveOrUpdateTravelExpenseDetail(${entity.id},'accommodationFee');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="deleteTravelExpenseDetail(${entity.id},'accommodationFee');" title="<s:text name='delete'/>"> <img src="${vix}/common/img/icon_12.png" />
				</a></td>
			</tr>
		</s:iterator>
		<%
			/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			com.vix.core.web.Pager pager = (com.vix.core.web.Pager) request.getAttribute("pager");
			count = pager.getPageSize() - count;
			request.setAttribute("count", count);
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
			</tr>
		</c:forEach>
	</tbody>
</table>