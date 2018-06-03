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
	asyncbox.confirm('确定要删除该业务组织么?','提示信息',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}

function chooseAll(chk){
	if(null == chk){
		$("input[name='chkBizOrgId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkBizOrgId']").attr("checked", true);
		}else{
			$("input[name='chkBizOrgId']").attr("checked", false);
		}
	}
	selectCount();
}

function selectCount(){
	var selectCount = 0;
	$.each($("input[name='chkBizOrgId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectBizOrgCount1").html(selectCount);
	$("#selectBizOrgCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkBizOrgIds']").attr("checked", false);
	}else{
		$("input[name='chkBizOrgIds']").attr("checked", true);
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
<input type="hidden" id="bizOrgTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="bizOrgPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="bizOrgTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="bizOrgOrderField" value="${pager.orderField}" />
<input type="hidden" id="bizOrgOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="bizOrgInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="10%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkBizOrgIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name='all' /></a></li>
							<li><a href="#"><s:text name='other' /></a></li>
							<li><a href="#">式样</a></li>
							<li><a href="#">关闭</a></li>
						</ul>
					</div>
				</div>
			</th>
			<!-- <th onclick="orderBy('businessOrgCode');" width="40%">
				编码&nbsp;
			</th> -->
			<th onclick="orderBy('businessOrgName');" width="70%">名称&nbsp;</th>
			<th width="20%">操作</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkBizOrgId" name="chkBizOrgId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<%-- <td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td> --%>
				<%-- <td>${entity.businessOrgCode}</td> --%>
				<td>${entity.businessOrgName}</td>
				<td><a href="#" onclick="saveOrUpdate('${entity.id}','');" title="修改"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="deleteEntity('${entity.id}');" title="删除"> <img src="${vix}/common/img/icon_12.png" />
				</a> <s:if test="%{#entity.isVirtualUnitNode.equals(\"1\")}">
						<a href="#" onclick="toSaveUnit('${entity.id}');" title="设置组织"> <img src="${vix}/common/img/icon_edit.png" />
						</a>
					</s:if> <%-- <div class="untitled" style="position: static;display: inline;">
						<span><img alt="" src="img/icon_untitled.png"></span>
						<div class="popup" style="display: none; top: -7px;">
							<strong>
								<i class="close"><a href="#"></a></i>
								<i><a href="#" title="查看"></a></i>
								<i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="修改"></a></i>
							</strong>
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
			</tr>
		</c:forEach>
	</tbody>
</table>