<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
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
<input type="hidden" id="industryCategoryTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="industryCategoryPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="industryCategoryTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="industryCategoryOrderField" value="${pager.orderField}" />
<input type="hidden" id="industryCategoryOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="industryCategoryInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="10%">编码</th>
			<th width="45%">名称</th>
			<th width="30%">行业</th>
			<th width="10%" style="text-align: center"><s:text name="cmn_operate" /></th>
			<%
				int count = 0;
			%>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<%
					count++;
				%>
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<td><a href="#" style="color: gray;">${entity.module.moduleCode }</a></td>
					<td><a href="#" style="color: gray;">${entity.module.name }</a></td>
					<td><a href="#" style="color: gray;">${entity.industryManagement.name }</a></td>
					<%-- 
					<td><a href="#" style="color: gray;"><s:property value="%{@com.vix.core.constant.BizConstant@COMMON_SECURITY_RESTYPE.get(#entity.moduleType)}"/></a></td>
					 --%>
					<td style="text-align: center">
						<%-- <div class="untitled" style="position: static; display: inline;">
							<span><img alt="" src="img/icon_untitled.png"> </span>
							<div class="popup" style="display: none; top: -7px;">
								<strong><i class="close"><a href="#"></a></i> 
										<i><a href="#" title="<s:text name='cmn_show'/>"></a></i>
										<i>
											<a href="#" onclick="saveOrUpdate('${entity.id}','');" title="修改">	
												<img src="${vix}/common/img/icon_edit.png"/>
											</a>
										</i>
										<b>${entity.name}</b>
								</strong>
								<p>${entity.name}</p>
							</div>
						</div> --%> <a href="#" onclick="deleteIndustryMgtModuleById('${entity.id}');" title="删除"> <img src="${vix}/common/img/icon_12.png" />
					</a> <a href="#" onclick="toSetAuthority('${entity.id}');" title="配置权限"> <img src="${vix}/common/img/icon_sec_auth.png" />
					</a> <a href="#" onclick="toSetMetaData('${entity.id}');" title="配置元数据"> <img src="${vix}/common/img/icon_met_auth.png" />
					</a>
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
				</tr>
			</c:forEach>
	</tbody>
</table>