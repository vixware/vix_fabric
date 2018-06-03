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
	asyncbox.confirm('确定要删除该返款规则么?','<s:text name='vix_message'/>',function(action){
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
loadOrderByImage("${vix}","returnRule");
</script>
<input type="hidden" id="returnRuleTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="returnRulePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="returnRuleTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="returnRuleOrderField" value="${pager.orderField}" />
<input type="hidden" id="returnRuleOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="returnRuleInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<%-- <th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="all"/></a></li>
							<li><a href="#"><s:text name="other"/></a></li>
							<li><a href="#"><s:text name="close"/></a></li>
						</ul>
					</div>
				</div>
			</th> --%>
			<th onclick="orderBy('id');" width="10%"><s:text name="cmn_id" />&nbsp;<img id="returnRuleImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('rrType');" width="10%">规则类型&nbsp;<img id="returnRuleImg_rrType" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('customerAccount.name');" width="15%">客户&nbsp;<img id="returnRuleImg_customerAccount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('item.name');" width="15%">${vv:varView('vix_mdm_item')}&nbsp;<img id="returnRuleImg_item" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('lowerSaleCount');" width="10%">最低销售数量&nbsp;<img id="returnRuleImg_lowerSaleCount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('ratio');" width="15%">返款比率&nbsp;<img id="returnRuleImg_ratio" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('detailType');" width="15%">明细计算类型&nbsp;<img id="returnRuleImg_detailType" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%">操作</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<%-- <td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()"/></td> --%>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td><s:if test="#entity.rrType == 'customerAccount'">
						客户
					</s:if> <s:elseif test="#entity.rrType == 'item'">
						${vv:varView('vix_mdm_item')}
					</s:elseif> <s:else>
						未设定
					</s:else></td>
				<td>${entity.customerAccount.name}</td>
				<td>${entity.item.name}</td>
				<td>${entity.lowerSaleCount}</td>
				<td>${entity.ratio}</td>
				<td><s:if test="#entity.detailType == 'count'">
						数量
					</s:if> <s:elseif test="#entity.detailType == 'money'">
						金额
					</s:elseif> <s:else>
						未设定
					</s:else></td>
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
				<!-- <td>&nbsp;</td> -->
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