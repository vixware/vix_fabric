<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	asyncbox.confirm('确定要删除该合同么?','提示信息',function(action){
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

function chooseOther(){
	$.each($("input[name='chkId']"), function(i, n){
		if($(n).attr('checked')){
			$(n).attr('checked',false);
		}else{
			$(n).attr('checked',true);
		}
	});
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

/** 载入数据列排序图标 */
loadOrderByImage("${vix}","category");
</script>
<input type="hidden" id="categoryTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="categoryPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="categoryTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="categoryOrderField" value="${pager.orderField}" />
<input type="hidden" id="categoryOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="categoryInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="所有" /></a></li>
							<li><a href="#"><s:text name="其他" /></a></li>
							<li><a href="#"><s:text name="关闭" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th onclick="orderBy('id');" width="10%"><s:text name="cmn_id" />&nbsp; <s:if test="%{pager.orderField == 'id' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'id' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else></th>
			<th width="170" style="cursor: pointer;" onclick="orderBy('contractName');"><s:text name="ctm_theme" />&nbsp;<img id="categoryImg_contractName" src="${vix}/common/img/arrow.gif"></th>
			<th><s:text name="ctm_type" /></th>
			<th><s:text name="cmn_mode" /></th>
			<th><s:text name="ctm_amount" /></th>
			<th><s:text name="ctm_agent" /></th>
			<th><s:text name="ctm_for" /></th>
			<th><s:text name="ctm_signing_date" /></th>
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
					<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
					<td><a href="#" style="color: gray;">${entity.contractName}</a></td>
					<td><a href="#" style="color: gray;"> <s:if test="%{#entity.typeCode == 1}">供应商采购合同</s:if> <s:elseif test="%{#entity.typeCode == 2}">销售合同</s:elseif> <s:elseif test="%{#entity.typeCode == 3}">采购框架协议</s:elseif> <s:elseif test="%{#entity.typeCode == 4}">销售框架协议</s:elseif> <s:elseif test="%{#entity.typeCode == 5}">项目合同</s:elseif>
					</a></td>
					<%-- <td><a href="#" style="color: gray;">${entity.mode}</a>
					</td> --%>
					<td><a href="#" style="color: gray;"> <s:if test="%{#entity.mode==0}">未提交</s:if> <s:elseif test="%{#entity.mode==1}">待审核</s:elseif> <s:elseif test="%{#entity.mode==2}">审核通过</s:elseif>
					</a></td>
					<td><a href="#" style="color: gray;">${entity.totalAmount}</a></td>
					<td><a href="#" style="color: gray;">${entity.operator}</a></td>
					<td><a href="#" style="color: gray;">${entity.approval}</a></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.signDate}" type="both" pattern="yyyy-MM-dd" /> </a></td>
					<td>
						<div class="untitled" style="position: static; display: inline;">
							<span><img alt="" src="img/icon_untitled.png"></span>
							<div class="popup" style="display: none; top: -7px;">
								<strong> <i class="close"><a href="#" onclick="deleteEntity('${entity.id}');" title="删除"></a></i> <i><a href="#" title="<s:text name='cmn_show'/>"></a></i> <i><a href="#" onclick="saveOrUpdate(${entity.id},${entity.typeCode},${pager.pageNo});" title="<s:text name='cmn_update'/>"></a></i> <b>${entity.name}</b>
								</strong>
								<p>${entity.name}</p>
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
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
	</tbody>
</table>