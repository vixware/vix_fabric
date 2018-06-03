<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","productDetails");
</script>
<input type="hidden" id="productDetailsTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="productDetailsPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="productDetailsTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="productDetailsOrderField" value="${pager.orderField}" />
<input type="hidden" id="productDetailsOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="productDetailsInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="itemTable" class="list">
	<tbody>
		<tr class="alt">
			<th width="25%" style="cursor: pointer;" onclick="orderBy('code');">编码&nbsp;<img id="productDetailsImg_code" src="${vix}/common/img/arrow.gif"> </span></th>
			<th width="55%" style="cursor: pointer;" onclick="orderBy('name');">名称&nbsp;<img id="productDetailsImg_name" src="${vix}/common/img/arrow.gif"> </span></th>
			<th width="20%">规格</th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity">
			<%
				count++;
			%>
			<tr>
				<td style="cursor: pointer" onclick="chooseItemForPrice('${entity.id}','${entity.code}','${entity.name}','${entity.price}','${entity.measureUnit.name}','${entity.skuCode}')">${entity.code}</td>
				<td style="cursor: pointer" onclick="chooseItemForPrice('${entity.id}','${entity.code}','${entity.name}','${entity.price}','${entity.measureUnit.name}','${entity.skuCode}')">${entity.name}</td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"></span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#"></a></i>
							</strong>
							<p>
								<s:if test="#entity.objectExpand != null && #entity.objectExpand.hasSpecifications">
									<s:iterator value="#entity.objectExpand.specifications" var="spec">
										<s:if test="#spec.isTemp != 1">
											<span style="font-weight: bold; width: 50px;">${spec.name}</span>
											<s:iterator value="#spec.specificationDetails" var="spd">
												<input type="radio" id="${entity.objectExpand.code}_${spec.code}" name="${entity.objectExpand.code}_${spec.code}" value="${spd.name}," />${spd.name}
											</s:iterator>
											<br />
										</s:if>
									</s:iterator>
									<span class="abtn" style="cursor: pointer;" onclick="chooseSpecification('${entity.objectExpand.code}');"><span>确定</span></span>
								</s:if>
							</p>
						</div>
					</div>
				</td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>