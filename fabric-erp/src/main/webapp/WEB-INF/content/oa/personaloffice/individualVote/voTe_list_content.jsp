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
loadOrderByImage("${vix}","voTe");
</script>
<input type="hidden" id="voTeTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="voTePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="voTeTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="voTeOrderField" value="${pager.orderField}" />
<input type="hidden" id="voTeOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="voTeInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"> </label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /></a></li>
							<li><a href="#"><s:text name="cmn_other" /></a></li>
							<li><a href="#"><s:text name="cmn_close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="14%" style="cursor: pointer;" onclick="orderBy('uploadPersonName');">发布人&nbsp;<img id="voTeImg_uploadPersonName" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="30%" style="cursor: pointer;" onclick="orderBy('title');">标题&nbsp;<img id="voTeImg_title" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%">是否匿名</th>
			<th width="10%">开始时间</th>
			<th width="10%">结束时间</th>
			<th width="10%">状态</th>
			<th width="10%"><s:text name="cmn_operate" /></th>
			<% int count = 0;%>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<% count++; %>
				<tr>
					<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<td><span style="color: gray;">${entity.uploadPersonName }</span></td>
					<td><span style="color: gray;">${entity.title }</span></td>
					<td><a href="#" style="color: gray;"> <s:if test="%{#entity.anonymity==0}">允许</s:if> <s:elseif test="%{#entity.anonymity==1}">不允许</s:elseif>
					</a></td>
					<td><span style="color: gray;"><fmt:formatDate value="${entity.startTime }" type="both" pattern="yyyy-MM-dd" /></span></td>
					<td><span style="color: gray;"><fmt:formatDate value="${entity.endTime }" type="both" pattern="yyyy-MM-dd" /></span></td>
					<td><a href="#" style="color: gray;"> <s:if test="%{#entity.publishType==0}">待发布</s:if> <s:elseif test="%{#entity.publishType==1}">发布</s:elseif> <s:elseif test="%{#entity.publishType==2}">终止</s:elseif>
					</a></td>
					<td>
						<div class="untitled" style="position: static; display: inline;">
							<s:if test="#entity.publishType ==1">
								<a href="#" onclick="saveOrUpdate1('${entity.id}');">投票</a>
							</s:if>
						</div>
						<div class="untitled" style="position: static; display: inline;">
							<s:if test="#entity.publishType ==1 || #entity.publishType ==2">
								<a href="#" onclick="saveOrUpdate('${entity.id}');">查看</a>
							</s:if>
						</div> <!-- <div class="untitled" style="position: static;display: inline;">
						<s:if test="#entity.publishType ==0" >
							<a href="#" onclick="">不能操作</a>
						</s:if>
					</div> -->
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
				</tr>
			</c:forEach>
	</tbody>
</table>