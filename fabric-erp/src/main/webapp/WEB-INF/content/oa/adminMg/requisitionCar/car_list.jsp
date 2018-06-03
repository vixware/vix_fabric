<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="hidden" id="radioTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="radioPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="radioTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="radioOrderField" value="${pager.orderField}" />
<input type="hidden" id="radioOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="radioInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />

<div class="right_content">
	<div class="pagelist drop">
		<ul>
			<li class="tp"><a href="#">操作</a>
				<ul>
					<li><a href="#">Actions</a></li>
					<li><a href="#">Actions</a></li>
					<li><a href="#">Actions</a></li>
					<li><a href="#">Actions</a></li>
					<li><a href="#">Actions</a></li>
				</ul></li>
		</ul>
		<strong>选择:0</strong>
		<div>
			<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="radioInfo"></b>到 <b class="radioTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
		</div>
	</div>
	<div class="table">
		<table class="list">
			<tr class="alt">
				<th onclick="orderBy('model');">型号</th>
				<th>车辆类型</th>
				<th onclick="orderBy('plateNumber');">车牌号</th>
				<th onclick="orderBy('engineNumber');">发动机号码</th>
				<th>车辆颜色</th>
				<th onclick="orderBy('plateNumber');">状态</th>
			</tr>
			<%
				int count = 0;
			%>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<%
					count++;
				%>
				<tr>
					<td>${entity.model}</td>
					<td>${entity.vehicleType.name}</td>
					<td>${entity.plateNumber}</td>
					<td>${entity.engineNumber}</td>
					<td>${entity.vehicleColor.name}</td>
					<td><s:if test="#entity.carSituation == 0">
							<span style="color: DarkGreen;">正常车辆</span>
						</s:if> <s:elseif test="#entity.carSituation == 2">
							<span style="color: Blue;">已申请</span>
						</s:elseif> <s:elseif test="#entity.carSituation == 3">
							<span style="color: red;">维修中</span>
						</s:elseif></td>
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
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="pagelist drop">
		<ul>
			<li class="ed"><a href="#">操作</a>
				<ul>
					<li><a href="#">Actions</a></li>
					<li><a href="#">Actions</a></li>
					<li><a href="#">Actions</a></li>
					<li><a href="#">Actions</a></li>
					<li><a href="#">Actions</a></li>
				</ul></li>
		</ul>
		<strong>选择:0</strong>
		<div>
			<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="radioInfo"></b>到 <b class="radioTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
		</div>
	</div>
</div>
