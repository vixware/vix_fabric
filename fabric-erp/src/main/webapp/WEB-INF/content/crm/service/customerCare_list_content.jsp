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
	asyncbox.confirm('确定要删除该客户关怀么?','<s:text name='vix_message'/>',function(action){
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
loadOrderByImage("${vix}","customerCare");
</script>
<input type="hidden" id="customerCareTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="customerCarePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="customerCareTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="customerCareOrderField" value="${pager.orderField}" />
<input type="hidden" id="customerCareOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="customerCareInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="8%" onclick="orderBy('id');">编号&nbsp;<img id="customerCareImg_id" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="11%" onclick="orderBy('subject');">关怀主题&nbsp;<img id="customerCareImg_subject" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="15%" onclick="orderBy('careContent');">关怀内容&nbsp;<img id="customerCareImg_careContent" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="11%" onclick="orderBy('customerFeedback');">客户反馈&nbsp;<img id="customerCareImg_customerFeedback" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%" onclick="orderBy('employee.name');">执行人 &nbsp;<img id="customerCareImg_employee" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%" onclick="orderBy('customerAccount.name');">客户&nbsp;<img id="customerCareImg_customerAccount" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%" onclick="orderBy('contactPerson.name');">联系人&nbsp;<img id="customerCareImg_contactPerson" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="12%" onclick="orderBy('crmProject.name');">项目&nbsp;<img id="customerCareImg_crmProject" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="8%">操作</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.subject}</td>
				<td>${entity.careContent}</td>
				<td>${entity.customerFeedback}</td>
				<td>${entity.employee.name}</td>
				<td>${entity.customerAccount.name}</td>
				<td>${entity.contactPerson.name}</td>
				<td>${entity.crmProject.subject}</td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>