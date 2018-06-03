<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	asyncbox.confirm('确定要删除该日志么?','<s:text name='日志'/>',function(action){
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
loadOrderByImage("${vix}","workLog");
</script>
<input type="hidden" id="workLogTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="workLogPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="workLogTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="workLogOrderField" value="${pager.orderField}" />
<input type="hidden" id="workLogOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="workLogInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="50">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /></a></li>
							<li><a href="#"><s:text name="cmn_other" /></a></li>
							<li><a href="#"><s:text name="cmn_close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="8%" style="cursor: pointer;" onclick="orderBy('uploadPersonName');">发布人&nbsp;<img id="workLogImg_uploadPersonName" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="27%" style="cursor: pointer;" onclick="orderBy('title');">标题&nbsp;<img id="workLogImg_title" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="33%" style="cursor: pointer;" onclick="orderBy('logContent');">内容&nbsp;<img id="workLogImg_logContent" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="8%" style="cursor: pointer;" onclick="orderBy('logType');">类型&nbsp;<img id="workLogImg_logType" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%" style="cursor: pointer;" onclick="orderBy('logDate');">日期&nbsp;<img id="workLogImg_logDate" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="8%"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><span style="color: gray;">${entity.uploadPersonName }</span></td>
				<td><span style="color: gray;">${entity.title }</span></td>
				<td title="${entity.logContent}"><span style="color: gray;"> <s:property value="cutLogContent(#entity.logContent,20)"></s:property>...
				</span></td>
				<td><a href="#" style="color: gray;"> <s:if test="%{#entity.logType==0}">
							<span style="color: red;">工作日志</span>
						</s:if> <s:elseif test="%{#entity.logType==1}">
							<span style="color: green;">个人日志</span>
						</s:elseif>
				</a></td>
				<td><span style="color: gray;"><fmt:formatDate value="${entity.logDate }" type="both" pattern="yyyy-MM-dd" /></span></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<a href="javascript:void(0);" onclick="lookworkLog('${entity.id}');">查看</a> <a href="#" onclick="deleteEntity('${entity.id}');"><span style="color: #8B0000;">删除</span></a>
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
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>