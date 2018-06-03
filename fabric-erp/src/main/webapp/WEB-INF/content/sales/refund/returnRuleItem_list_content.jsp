<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#returnRuleItemTable tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#returnRuleItemTable tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
 
function deleteReturnRuleItemEntity(id){
	asyncbox.confirm('确定要删除该返款单明细么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/sales/refund/returnRuleItemAction!deleteById.action?id='+id,
				  cache: false,
				  success: function(html){
					asyncbox.success(html,"提示信息",function(action){
						pager("start","${vix}/sales/refund/returnRuleItemAction!goListContent.action?returnRuleId="+$("#id").val(),'returnRuleItem');
					});
				  }
			});
		}
	});
}

function choosePQSIAll(chk){
	if(null == chk){
		$("input[name='chkpqsiId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkpqsiId']").attr("checked", true);
		}else{
			$("input[name='chkpqsiId']").attr("checked", false);
		}
	}
	selectPQSICount();
}

function selectPQSICount(){
	var selectCount = 0;
	$.each($("input[name='chkpqsiId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectReturnRuleItemCount1").html(selectCount);
	$("#selectReturnRuleItemCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkpqsiIds']").attr("checked", false);
	}else{
		$("input[name='chkpqsiIds']").attr("checked", true);
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
loadOrderByImage("${vix}","returnRuleItem");
</script>
<input type="hidden" id="returnRuleItemTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="returnRuleItemPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="returnRuleItemTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="returnRuleItemOrderField" value="${pager.orderField}" />
<input type="hidden" id="returnRuleItemOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="returnRuleItemInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="returnRuleItemTable" class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkpqsiIds" onchange="choosePQSIAll(this)"></label>
						<ul>
							<li><a href="#" onclick="choosePQSIAll();"><s:text name="all" /></a></li>
							<li><a href="#"><s:text name="other" /></a></li>
							<li><a href="#"><s:text name="close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th onclick="returnRuleItemOrderBy('id');" width="10%"><s:text name="cmn_id" />&nbsp;<img id="returnRuleItemImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="returnRuleItemOrderBy('from');" width="20%">从&nbsp;<img id="returnRuleItemImg_from" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="returnRuleItemOrderBy('to');" width="10%">到&nbsp;<img id="returnRuleItemImg_to" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="returnRuleItemOrderBy('unit');" width="15%">计量单位&nbsp;<img id="returnRuleItemImg_unit" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="returnRuleItemOrderBy('ratio');" width="15%">返款比率&nbsp;<img id="returnRuleItemImg_ratio" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="returnRuleItemOrderBy('currencyType.name');" width="15%">币种&nbsp;<img id="returnRuleItemImg_currencyType" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%">操作</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkpqsiId" name="chkpqsiId" value="${entity.id}" type="checkbox" onchange="selectPQSICount()" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.from}</td>
				<td>${entity.to}</td>
				<td>${entity.unit}</td>
				<td>${entity.ratio}</td>
				<td>${entity.currencyType.name}</td>
				<td><a href="#" onclick="saveOrUpdateReturnRuleItem('${entity.id}');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="deleteReturnRuleItemEntity('${entity.id}');" title="<s:text name='delete'/>"> <img src="${vix}/common/img/icon_12.png" />
				</a></td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>