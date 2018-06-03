<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="text" id="categoryTotalCountHidden" value="${pager.totalCount}" />
<input type="text" id="categoryPageNoHidden" value="${pager.pageNo}" />
<input type="text" id="categoryTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="categoryOrderField" value="${pager.orderField}" />
<input type="hidden" id="categoryOrderBy" value="${pager.orderBy}" />
<input type="text" id="categoryInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<thead>
		<tr class="alt">
			<th width="50">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkCategoryIds" onchange="chooseAll(this)"> </label>
						<ul>
							<li><a href="#" onclick="chooseAll();">所有</a></li>
							<li><a href="#">其他</a></li>
							<li><a href="#">式样</a></li>
							<li><a href="#">关闭</a></li>
						</ul>
					</div>
				</div>
			</th>
			<!-- <th onclick="orderBy('id');">
				<s:text name="cmn_id"/>&nbsp;
				<s:if test="%{pager.orderField == 'id' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if>
				<s:elseif test="%{pager.orderField == 'id' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif>
				<s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th> -->

			<th><s:text name="eam_equipmentcode" /></th>
			<th width="17%"><s:text name="eam_devicename" /></th>
			<th><s:text name="eam_positionnumber" /></th>
			<th><s:text name="eam_parentdevicename" /></th>
			<th><s:text name="eam_eam_parentdevicenumber" /></th>
			<th><s:text name="eam_suppliername" /></th>
			<th><s:text name="cmn_operate" /></th>
		</tr>
	</thead>
	<tbody>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="item" status="s">
			<%
				count++;
			%>
			<tr>
				<td><input id="chkCategoryId" name="chkCategoryId" value="${item.id}" type="checkbox" onchange="selectCount()" /></td>
				<!--  	<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>-->
				<td><span style="color: gray;">${item.eqcode}</span></td>
				<td><span style="color: gray;">${item.eqname}</span></td>
				<td><span style="color: gray;">${item.eqplaceCode}</span></td>
				<td><span style="color: gray;">${item.parentEquipment.eqname}</span></td>
				<td><span style="color: gray;">${item.parentEquipment.eqcode}</span></td>
				<td><span style="color: gray;">系统管理员</span></td>
				<td>
					<!--  	<a href="#" onclick="saveOrUpdate(${item.id});" title="<s:text name='cmn_update'/>">	
						<img src="${vix}/common/img/icon_edit.png"/>
					</a>
					<a href="#" onclick="deleteEntity(${item.id});" title="<s:text name='cmn_delete'/>" >
						<img src="${vix}/common/img/icon_12.png"/>  </a>-->
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#"></a> </i> <i><a href="#" title="<s:text name='cmn_show'/>"></a> </i> <i><a href="#" onclick="saveOrUpdate(${item.id},$('#parentId').val());" title="<s:text name='cmn_update'/>"></a> </i> <b>${item.eqname}</b>
							</strong>
							<p>${item.memo}</p>
						</div>
					</div>
				</td>
			</tr>
		</s:iterator>
		<%
			/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			com.vix.core.web.Pager pager = (com.vix.core.web.Pager) request
					.getAttribute("pager");
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