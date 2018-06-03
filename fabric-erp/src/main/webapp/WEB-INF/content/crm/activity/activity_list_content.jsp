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
	asyncbox.confirm('确定要删除该销售活动么?','<s:text name='vix_message'/>',function(action){
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
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","activity");
</script>
<input type="hidden" id="activityTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="activityPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="activityTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="activityOrderField" value="${pager.orderField}" />
<input type="hidden" id="activityOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="activityInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th onclick="orderBy('id');" width="10%" style="cursor: pointer;"><s:text name="cmn_id" />&nbsp;<img id="activityImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('title');" width="15%" style="cursor: pointer;">主题&nbsp;<img id="activityImg_title" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('type');" width="10%" style="cursor: pointer;">类型&nbsp;<img id="activityImg_type" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('content');" width="10%" style="cursor: pointer;">内容&nbsp;<img id="activityImg_content" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('created_by');" width="15%" style="cursor: pointer;">创建人&nbsp;<img id="activityImg_created_by" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('address');" width="15%" style="cursor: pointer;">活动地点&nbsp;<img id="activityImg_address" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.title}</td>
				<td>${entity.type}</td>
				<td>${entity.content}</td>
				<td>${entity.created_by.name}</td>
				<td>${entity.address}</td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>