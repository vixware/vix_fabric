<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function deleteEntity(id){
	asyncbox.confirm('确定要删除该品牌么?','<s:text name='vix_message'/>',function(action){
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

</script>
<input type="hidden" id="configUrlTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="configUrlPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="configUrlTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="configUrlOrderField" value="${pager.orderField}" />
<input type="hidden" id="configUrlOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="configUrlInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
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
			<th>编码</th>
			<th>名称</th>
			<th>是否重定向</th>
			<th>URL</th>
			<th><s:text name="cmn_operate" /></th>
			<%
				int count = 0;
			%>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<%
					count++;
				%>
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<td><a href="#" style="color: gray;">${entity.code}</a></td>
					<td><a href="#" style="color: gray;">${entity.name}</a></td>
					<td><a href="#" style="color: gray;"> <s:if test="#entity.isRedirect == 'Y'">是</s:if> <s:else>否</s:else>
					</a></td>
					<td><a href="#" style="color: gray;">${entity.url}</a></td>
					<%-- 
					<td><a href="#" style="color: gray;"><s:property value="%{@com.vix.core.constant.BizConstant@COMMON_SECURITY_RESTYPE.get(#entity.configUrlType)}"/></a></td>
					 --%>
					<td>
						<div class="untitled" style="position: static; display: inline;">
							<span><img alt="" src="img/icon_untitled.png"> </span>
							<div class="popup" style="display: none; top: -7px;">
								<strong><i class="close"><a href="#"></a></i> <i><a href="#" title="<s:text name='cmn_show'/>"></a></i> <i> <a href="#" onclick="saveOrUpdate('${entity.id}','');" title="修改"> <img src="${vix}/common/img/icon_edit.png" />
									</a>
								</i> <b>${entity.name}</b> </strong>
								<p>${entity.name}</p>
							</div>
						</div> <a href="#" onclick="deleteById('${entity.id}');" title="删除"> <img src="${vix}/common/img/icon_12.png" />
					</a> <%-- <a href="#" onclick="toSetAuthority('${entity.id}');" title="配置">	
							配置
						</a> --%>
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
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
	</tbody>
</table>