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
	asyncbox.confirm('确定要删除该项目么?','<s:text name='vix_message'/>',function(action){
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
loadOrderByImage("${vix}","crmProject");
</script>
<input type="hidden" id="crmProjectTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="crmProjectPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="crmProjectTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="crmProjectOrderField" value="${pager.orderField}" />
<input type="hidden" id="crmProjectOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="crmProjectInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th onclick="orderBy('id');" width="8%">编号&nbsp;<img id="crmProjectImg_id" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('subject');" width="18%">项目主题&nbsp;<img id="crmProjectImg_subject" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('personInCharge.name');" width="10%">负责人&nbsp;<img id="crmProjectImg_personInCharge" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('projectEstablishDate');" width="8%">立项日期&nbsp;<img id="crmProjectImg_projectEstablishDate" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('customerAccount.name');" width="15%">客户&nbsp;<img id="crmProjectImg_customerAccount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('projectStage.name');" width="10%">项目阶段&nbsp;<img id="crmProjectImg_projectStage" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('projectStatus.name');" width="10%">项目状态&nbsp;<img id="crmProjectImg_projectStatus" src="${vix}/common/img/arrow.gif">
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
				<td>${entity.personInCharge.name}</td>
				<td><s:property value="formatDateToString(#entity.projectEstablishDate)" /></td>
				<td>${entity.customerAccount.name}</td>
				<td>${entity.projectStage.name}</td>
				<td>${entity.projectStatus.name}</td>
				<td><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="goView('${entity.id}');" title="<s:text name='view'/>"> <img src="${vix}/common/img/view.png" />
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
			</tr>
		</c:forEach>
	</tbody>
</table>