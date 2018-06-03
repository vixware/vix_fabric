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
	asyncbox.confirm('确定要删除该客户投诉么?','<s:text name='vix_message'/>',function(action){
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
loadOrderByImage("${vix}","customerComplaint");
</script>
<input type="hidden" id="customerComplaintTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="customerComplaintPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="customerComplaintTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="customerComplaintOrderField" value="${pager.orderField}" />
<input type="hidden" id="customerComplaintOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="customerComplaintInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="all" /></a></li>
							<li><a href="#"><s:text name="other" /></a></li>
							<li><a href="#"><s:text name="close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th onclick="orderBy('id');" width="8%">编号&nbsp; <img id="customerComplaintImg_id" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('subject');" width="12%">投诉主题&nbsp;<img id="customerComplaintImg_subject" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('complaintType.name');" width="10%">投诉分类&nbsp;<img id="customerComplaintImg_complaintType" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('customerAccount.name');" width="12%">客户&nbsp;<img id="customerComplaintImg_customerAccount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('emergencyLevelType.name');" width="8%">紧急程度&nbsp;<img id="customerComplaintImg_emergencyLevelType" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('complaintDate');" width="8%">日期&nbsp;<img id="customerComplaintImg_complaintDate" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('complaintTime');" width="8%">时间&nbsp;<img id="customerComplaintImg_complaintTime" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('employee.name');" width="8%">首次接待人&nbsp;<img id="customerComplaintImg_employee" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('dealResult.name');" width="8%">处理结果&nbsp;<img id="customerComplaintImg_dealResult" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('contactPerson.name');" width="8%">投诉人&nbsp;<img id="customerComplaintImg_contactPerson" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="8%">操作</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><s:if test="#entity.id != null ">
						<input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" />
					</s:if> <s:else>&nbsp;</s:else></td>
				<td><s:if test="#entity.id != null ">
						${s.count + (pager.pageSize * (pager.pageNo -1))}
					</s:if></td>
				<td>${entity.subject}</td>
				<td>${entity.complaintType.name}</td>
				<td>${entity.customerAccount.name}</td>
				<td>${entity.emergencyLevelType.name}</td>
				<td>${entity.complaintDate}</td>
				<td>${entity.complaintTime}</td>
				<td>${entity.employee.name}</td>
				<td>${entity.dealResult.name}</td>
				<td>${entity.contactPerson.name}</td>
				<td><s:if test="#entity.id != null ">
						<a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
						</a>
						<a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='delete'/>"> <img src="${vix}/common/img/icon_12.png" />
						</a>
					</s:if></td>
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