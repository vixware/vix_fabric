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
	asyncbox.confirm('确定要删除该分类么?','提示信息',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}

function chooseAll(chk){
	if(null == chk){
		$("input[name='chkCategoryId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkCategoryId']").attr("checked", true);
		}else{
			$("input[name='chkCategoryId']").attr("checked", false);
		}
	}
	selectCount();
}

function selectCount(){
	var selectCount = 0;
	$.each($("input[name='chkCategoryId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectCategoryCount1").html(selectCount);
	$("#selectCategoryCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkCategoryIds']").attr("checked", false);
	}else{
		$("input[name='chkCategoryIds']").attr("checked", true);
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
			<th width="50">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkCategoryIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();">所有</a></li>
							<li><a href="#">其他</a></li>
							<li><a href="#">式样</a></li>
							<li><a href="#">关闭</a></li>
						</ul>
					</div>
				</div>
			</th>
			<th style="cursor: pointer;" onclick="orderBy('code');">员工编号&nbsp;<img id="categoryImg_code" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('name');">姓名&nbsp;<img id="categoryImg_name" src="${vix}/common/img/arrow.gif">
			</th>
			<th><s:text name="hr_sex" /></th>
			<th style="cursor: pointer;" onclick="orderBy('qualificationsCode');"><s:text name="hr_degree" />&nbsp;<img id="categoryImg_qualificationsCode" src="${vix}/common/img/arrow.gif"></th>
			<th><s:text name="hr_department" /></th>
			<th style="cursor: pointer;" onclick="orderBy('telephone');"><s:text name="hr_contact" />&nbsp;<img id="categoryImg_telephone" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('birthday');"><s:text name="hr_birth_date" />&nbsp;<img id="categoryImg_birthday" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('idNumber');"><s:text name="hr_id_card" />&nbsp;<img id="categoryImg_idNumber" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('entityTime');"><s:text name="hr_entry_time" />&nbsp;<img id="categoryImg_entityTime" src="${vix}/common/img/arrow.gif"></th>
			<%-- <th><s:text name="hr_type"/></th> --%>
			<%-- <th><s:text name="cmn_operate"/></th> --%>
		</tr>
		<% int count =0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>

				<td><span style="color: gray;">${entity.code}</span></td>
				<td><span style="color: gray;">${entity.name}</span></td>
				<td><span style="color: gray;"> <c:if test="${null == entity.gender }">
						</c:if> <c:if test="${entity.gender == '1' }">
							男
						</c:if> <c:if test="${entity.gender == '0' }">
							女
						</c:if>

				</span></td>
				<td><span style="color: gray;">${entity.qualificationsCode}</span></td>
				<td><span style="color: gray;">${entity.organizationUnit.fs}</span></td>
				<td><span style="color: gray;">${entity.telephone}</span></td>
				<td><span style="color: gray;"><fmt:formatDate value="${entity.birthday}" type="both" pattern="yyyy-MM-dd" /></span></td>
				<td><span style="color: gray;">${entity.idNumber}</span></td>
				<td><span style="color: gray;"><fmt:formatDate value="${entity.entityTime}" type="both" pattern="yyyy-MM-dd" /></span></td>
				<%-- <td>
					<div class="untitled" style="position: static;display: inline;">
						<span><img alt="" src="img/icon_untitled.png"></span>
						<div class="popup" style="display: none; top: -7px;">
							<strong>
								<i class="close"><a href="#"></a></i>
								<i><a href="#" title="<s:text name='cmn_show'/>"></a></i>
								<i><a href="#" onclick="saveOrUpdate('${entity.id}',$('#parentId').val());" title="<s:text name='cmn_update'/>"></a></i>
								<b>${entity.code}</b>
							</strong>
							<p>${entity.name} ${entity.organizationUnit.fs} ${entity.qualificationsCode} ${entity.telephone} ${entity.idNumber} </p>
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