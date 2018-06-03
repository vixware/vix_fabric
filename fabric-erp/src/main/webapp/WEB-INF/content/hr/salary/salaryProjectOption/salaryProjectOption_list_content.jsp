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
	asyncbox.confirm('确定要删除该工资辅助项么?','<s:text name='cmn_message'/>',function(action){
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
</script>
<input type="hidden" id="roleTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="rolePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="roleTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="roleOrderField" value="${pager.orderField}" />
<input type="hidden" id="roleOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="roleInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<%-- <th onclick="orderBy('id');" width="10%">
				<s:text name="id"/>&nbsp;
				<s:if test="%{pager.orderField == 'id' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if>
				<s:elseif test="%{pager.orderField == 'id' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif>
				<s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th> --%>
			<th onclick="orderBy('optionName');" width="25%">名称&nbsp; <s:if test="%{pager.orderField == 'optionName' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'optionName' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
			<th onclick="orderBy('optionCode');" width="10%">编码&nbsp; <s:if test="%{pager.orderField == 'optionCode' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'optionCode' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
			<th onclick="orderBy('optionType');" width="20%">类型&nbsp; <s:if test="%{pager.orderField == 'optionType' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'optionType' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
			<th onclick="orderBy('metaDataName');" width="30%">业务对象&nbsp; <s:if test="%{pager.orderField == 'metaDataName' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'metaDataName' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
			<th width="10%"><s:text name="operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<%-- <td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td> --%>
				<td>${entity.optionName}</td>
				<td>${entity.optionCode}</td>
				<td><s:property value="%{@com.vix.core.constant.BizConstant@HR_SAL_OPTYPE.get(#entity.optionType)}" /></td>
				<td>${entity.metaDataName}</td>
				<%-- <td>
					<s:if test="%{#entity.isAudit=='Y'}">是</s:if> 
					<s:elseif test="%{#entity.isAudit=='N'}">否</s:elseif>
					<s:else>否</s:else>
				</td> --%>
				<td><s:if test="tag != 'choose'">
						<a href="#" onclick="saveOrUpdate('${entity.id}');" title="更新"> <img src="${vix}/common/img/icon_edit.png" />
						</a>
						<%-- <a href="#" onclick="toAuthority('${entity.id}');" title="发布">	
							<img src="${vix}/common/img/icon_edit.png"/>
						</a> --%>
						<a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='delete'/>"> <img src="${vix}/common/img/icon_12.png" />
						</a>
					</s:if> <%-- <div class="untitled" style="position: static;display: inline;">
						<span><img alt="" src="img/icon_untitled.png"></span>
						<div class="popup" style="display: none; top: -7px;">
							<strong>
								<i class="close"><a href="#"></a></i>
								<i><a href="#" title="<s:text name='show'/>"></a></i>
								<i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='update'/>"></a></i>
								<b></b>
							</strong>
							<p></p>
						</div>
					</div> --%></td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>