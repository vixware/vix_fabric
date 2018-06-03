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
	asyncbox.confirm('确定要删除该公司么?','提示信息',function(action){
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
			<!-- <th width="10%">
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
			</th> -->
			<th onclick="orderBy('orgName');" width="30%"><span style="cursor: pointer;" onclick="orderBy('orgName');"> 名称&nbsp;<img id="categoryImg_orgName" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('orgType');" width="10%"><span style="cursor: pointer;" onclick="orderBy('orgType');"> 类型&nbsp;<img id="categoryImg_orgType" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('briefName');" width="10%"><span style="cursor: pointer;" onclick="orderBy('briefName');"> 简称&nbsp;<img id="categoryImg_briefName" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('country');" width="10%"><span style="cursor: pointer;" onclick="orderBy('country');"> 所属国家&nbsp;<img id="categoryImg_country" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('area');" width="10%"><span style="cursor: pointer;" onclick="orderBy('area');"> 地区&nbsp;<img id="categoryImg_area" src="${vix}/common/img/arrow.gif"></th>
			<th width="10%">操作</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<%-- 
				<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="selectCount()"/></td>
				 --%>

				<%-- <td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td> --%>
				<td>${entity.orgName}</td>
				<td><s:if test="%{#entity.orgType.equals(\"JTGS\")}">集团公司</s:if> <s:elseif test="%{#entity.orgType.equals(\"GS\")}">公司</s:elseif> <s:else>公司</s:else></td>
				<td>${entity.briefName}</td>
				<td>${entity.country}</td>
				<td>${entity.area}</td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"></span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#" onclick="deleteEntity('${entity.id}');" title="删除"><img src="${vix}/common/img/icon_12.png" /></a></i> <i><a href="#" onclick="showOrganization('${entity.id}');" title="<s:text name='cmn_show'/>"></a></i> <i><a href="#"
									onclick="saveOrUpdate('${entity.id}',${entity.parentOrganization.id});" title="修改"><img src="${vix}/common/img/icon_edit.png" /></a></i> <!-- 小i标题 --> <b>组织架构</b>
							</strong>
							<!-- 小i内容 -->
							<p></p>
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
			</tr>
		</c:forEach>
	</tbody>
</table>