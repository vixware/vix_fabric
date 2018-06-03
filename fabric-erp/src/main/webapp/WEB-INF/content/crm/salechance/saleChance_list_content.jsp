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
	asyncbox.confirm('确定要删除该销售机会么?','<s:text name='vix_message'/>',function(action){
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
loadOrderByImage("${vix}","saleChance");
</script>
<input type="hidden" id="saleChanceTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="saleChancePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="saleChanceTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="saleChanceOrderField" value="${pager.orderField}" />
<input type="hidden" id="saleChanceOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="saleChanceInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="8%"><span style="cursor: pointer;" onclick="orderBy('id');"><s:text name="cmn_id" />&nbsp;<img id="saleChanceImg_id" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="12%"><span style="cursor: pointer;" onclick="orderBy('subject');">主题&nbsp;<img id="saleChanceImg_subject" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('customerAccount.name');">客户&nbsp;<img id="saleChanceImg_customerAccount" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="5%"><span style="cursor: pointer;" onclick="orderBy('saleStage.name');">销售阶段&nbsp;<img id="saleChanceImg_saleStage" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="7%"><span style="cursor: pointer;" onclick="orderBy('charger');">负责人&nbsp;<img id="saleChanceImg_charger" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('preOrderDate');">预计签单日期&nbsp;<img id="saleChanceImg_preOrderDate" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('expectedValue');">预期金额&nbsp;<img id="saleChanceImg_expectedValue" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="5%"><span style="cursor: pointer;" onclick="orderBy('possibility');">可能性&nbsp;<img id="saleChanceImg_possibility" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="5%"><span style="cursor: pointer;" onclick="orderBy('phase');">阶段&nbsp;<img id="saleChanceImg_phase" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('phaseStay');">阶段停留&nbsp;<img id="saleChanceImg_phaseStay" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('updateTime');">更新时间&nbsp;<img id="saleChanceImg_updateTime" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="8%"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.subject}</td>
				<td>${entity.customerAccount.name}</td>
				<td>${entity.saleStage.name}</td>
				<td>${entity.charger}</td>
				<td><s:property value="formatDateToString(#entity.preOrderDate)" /></td>
				<td>${entity.expectedValue}</td>
				<td>${entity.possibility}</td>
				<td>${entity.phase}</td>
				<td>${entity.phaseStay}</td>
				<td><s:property value="formatDateToString(#entity.dateModified)" /></td>
				<td><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='delete'/>"> <img src="${vix}/common/img/icon_12.png" />
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>