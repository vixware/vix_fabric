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
function deleteEntity(id){
	asyncbox.confirm('确定要删除该代垫费用么?','提示信息',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
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
<input type="hidden" id="disbursementCostTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="disbursementCostPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="disbursementCostTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="disbursementCostOrderField" value="${pager.orderField}" />
<input type="hidden" id="disbursementCostOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="disbursementCostInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="50">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /></a></li>
							<li><a href="#" onclick="chooseOther();"><s:text name="cmn_other" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('employee.name');"> 业务员&nbsp; <s:if test="%{pager.orderField == 'employee.name' && pager.orderBy == 'asc' }">
						<img src="${vix}/common/img/arrow_down.gif">
					</s:if> <s:elseif test="%{pager.orderField == 'employee.name' && pager.orderBy == 'desc' }">
						<img src="${vix}/common/img/arrow_up.gif">
					</s:elseif> <s:else>
						<img src="${vix}/common/img/arrow.gif">
					</s:else>
			</span></th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('disbursementDate');"> 代垫日期&nbsp; <s:if test="%{pager.orderField == 'disbursementDate' && pager.orderBy == 'asc' }">
						<img src="${vix}/common/img/arrow_down.gif">
					</s:if> <s:elseif test="%{pager.orderField == 'disbursementDate' && pager.orderBy == 'desc' }">
						<img src="${vix}/common/img/arrow_up.gif">
					</s:elseif> <s:else>
						<img src="${vix}/common/img/arrow.gif">
					</s:else>
			</span></th>
			<th width="20%"><span style="cursor: pointer;"> 代垫单号&nbsp; <s:if test="%{pager.orderField == 'disbursementBillNumber' && pager.orderBy == 'asc' }">
						<img src="${vix}/common/img/arrow_down.gif">
					</s:if> <s:elseif test="%{pager.orderField == 'disbursementBillNumber' && pager.orderBy == 'desc' }">
						<img src="${vix}/common/img/arrow_up.gif">
					</s:elseif> <s:else>
						<img src="${vix}/common/img/arrow.gif">
					</s:else>
			</span></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('customerAccount.name');"> 客户&nbsp; <s:if test="%{pager.orderField == 'customerAccount.name' && pager.orderBy == 'asc' }">
						<img src="${vix}/common/img/arrow_down.gif">
					</s:if> <s:elseif test="%{pager.orderField == 'customerAccount.name' && pager.orderBy == 'desc' }">
						<img src="${vix}/common/img/arrow_up.gif">
					</s:elseif> <s:else>
						<img src="${vix}/common/img/arrow.gif">
					</s:else>
			</span></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('saleOrg');"> 销售部门&nbsp; <s:if test="%{pager.orderField == 'saleOrg' && pager.orderBy == 'asc' }">
						<img src="${vix}/common/img/arrow_down.gif">
					</s:if> <s:elseif test="%{pager.orderField == 'saleOrg' && pager.orderBy == 'desc' }">
						<img src="${vix}/common/img/arrow_up.gif">
					</s:elseif> <s:else>
						<img src="${vix}/common/img/arrow.gif">
					</s:else>
			</span></th>
			<th><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${entity.employee.name}</td>
				<td><s:property value="formatDateToString(#entity.disbursementDate)" /></td>
				<td>${entity.disbursementBillNumber}</td>
				<td>${entity.customerAccount.name}</td>
				<td>${entity.saleOrg}</td>
				<td><a href="#" onclick="saveOrUpdate(${entity.id},${pager.pageNo});" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
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
			</tr>
		</c:forEach>
	</tbody>
</table>