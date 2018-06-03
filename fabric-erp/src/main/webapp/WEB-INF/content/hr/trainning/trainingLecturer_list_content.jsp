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
	asyncbox.confirm('确定要删除么?','提示信息',function(action){
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
			<th width="70"><s:text name="讲师性质" /></th>
			<th style="cursor: pointer;" onclick="orderBy('lecturerCode');">编码&nbsp;<img id="categoryImg_lecturerCode" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('lecturerName');">姓名&nbsp;<img id="categoryImg_lecturerName" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('lecturerPosition');">职位&nbsp;<img id="categoryImg_lecturerPosition" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('branches');">部门&nbsp;<img id="categoryImg_branches" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="70"><s:text name="讲师级别" /></th>
			<th width="70"><s:text name="讲师类别" /></th>
			<th style="cursor: pointer;" onclick="orderBy('lecturerCost');">费用&nbsp;<img id="categoryImg_lecturerCost" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('lecturerIntroduction');">简介&nbsp;<img id="categoryImg_lecturerIntroduction" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="30"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count =0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td width="70"><span style="color: gray;"> <c:if test="${null == entity.lecturerNature }">
						</c:if> <c:if test="${entity.lecturerNature == '1' }">
							内部讲师
						</c:if> <c:if test="${entity.lecturerNature == '2' }">
							外部讲师
						</c:if>
				</span></td>
				<td><span style="color: gray;">${entity.lecturerCode}</span></td>
				<td><span style="color: gray;">${entity.lecturerName}</span></td>
				<td><span style="color: gray;">${entity.lecturerPosition}</span></td>
				<td><span style="color: gray;">${entity.branches}</span></td>
				<td width="70"><span style="color: gray;"> <c:if test="${null == entity.lecturerLevel }">
						</c:if> <c:if test="${entity.lecturerLevel == '1' }">
							助理讲师
						</c:if> <c:if test="${entity.lecturerLevel == '2' }">
							讲师
						</c:if> <c:if test="${entity.lecturerLevel == '3' }">
							高级讲师
						</c:if>
				</span></td>
				<td width="70"><span style="color: gray;"> <c:if test="${null == entity.lecturerType }">
						</c:if> <c:if test="${entity.lecturerType == '1' }">
							内聘讲师
						</c:if> <c:if test="${entity.lecturerType == '2' }">
							外聘讲师
						</c:if>
				</span></td>
				<td><span style="color: gray;">${entity.lecturerCost}</span></td>
				<td><span style="color: gray;">${entity.lecturerIntroduction}</span></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"></span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#" onclick="deleteEntity('${entity.id}');"></a></i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='查看修改'/>"></a></i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"></a></i> <!-- 小i标题 --> <b>${entity.lecturerName } </b>
							</strong>
							<!-- 小i内容 -->
							<p>${entity.lecturerIntroduction}</p>
						</div>
					</div>
				</td>
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
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>