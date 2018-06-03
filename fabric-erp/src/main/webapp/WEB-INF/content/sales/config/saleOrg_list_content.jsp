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
	asyncbox.confirm('确定要删除该销售组织吗?','提示信息',function(action){
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
loadOrderByImage("${vix}","saleOrg");
</script>
<input type="hidden" id="saleOrgTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="saleOrgPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="saleOrgTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="saleOrgOrderField" value="${pager.orderField}" />
<input type="hidden" id="saleOrgOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="saleOrgInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="10%">
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
			<th onclick="orderBy('fullName');" width="40%" style="cursor: pointer;">全称&nbsp;<img id="saleOrgImg_fullName" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('fs');" width="10%" style="cursor: pointer;">简称&nbsp;<img id="saleOrgImg_fs" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('orgCode');" width="10%" style="cursor: pointer;">编码&nbsp;<img id="saleOrgImg_orgCode" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('unitType');" width="10%" style="cursor: pointer;">组织单元类型&nbsp;<img id="saleOrgImg_unitType" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%">操作</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${entity.fullName}</td>
				<td>${entity.fs}</td>
				<td>${entity.orgCode}</td>
				<td><s:if test="%{#entity.unitType.equals(\"JZBM\")}">部门</s:if> <s:elseif test="%{#entity.unitType.equals(\"XSBGS\")}">销售办公室</s:elseif> <s:elseif test="%{#entity.unitType.equals(\"XSZ\")}">销售组</s:elseif> <s:elseif test="%{#entity.unitType.equals(\"XSZZ\")}">销售组织</s:elseif> <s:else>部门</s:else></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"></span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"> <a href="#" onclick="deleteEntity('${entity.id}');" title="删除"> <img src="${vix}/common/img/icon_12.png" />
								</a>
							</i> <i><a href="#" title="<s:text name='cmn_show'/>"></a></i> <i> <a href="#" onclick="saveOrUpdate('${entity.id}','');" title="修改"> <img src="${vix}/common/img/icon_edit.png" />
								</a>
							</i> <!-- 小i标题 --> <b>我是标题</b>
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