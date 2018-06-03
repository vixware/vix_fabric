<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$("schedule tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("schedule tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
   
function deleteEntity(id){
	asyncbox.confirm('确定要删除该分类么?','提示信息',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}

function chooseAll(chk){
	if(null == chk){
		$("input[name='chknewtabsId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chknewtabsId']").attr("checked", true);
		}else{
			$("input[name='chknewtabsId']").attr("checked", false);
		}
	}
	selectCount();
}

function selectCount(){
	var selectCount = 0;
	$.each($("input[name='chknewtabsId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectnewtabsCount1").html(selectCount);
	$("#selectnewtabsCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chknewtabsIds']").attr("checked", false);
	}else{
		$("input[name='chknewtabsIds']").attr("checked", true);
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

$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});


//排序 
function orderBy(orderField){
loadName();
var orderBy = $("#newtabsOrderBy").val();
if(orderBy == 'desc'){
	orderBy = "asc";
}else{
	orderBy = 'desc';
}
pager("start","${vix}/oa/messageManagementAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'newtabs');
}

bindSearch();
function currentPager(tag){
loadName();
pager(tag,"${vix}/oa/messageManagementAction!goSingleList.action?name="+name,'newtabs');
}

$(".drop>ul>li").bind('mouseover',function(){
$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
$(this).children('ul').slideDown('fast').stop(true, true);
$(this).children('ul').slideUp('fast');
});


/** 载入数据列排序图标 */
loadOrderByImage("${vix}","newtabs");
</script>
<input type="hidden" id="newtabsTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="newtabsPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="newtabsTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="newtabsOrderField" value="${pager.orderField}" />
<input type="hidden" id="newtabsOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="newtabsInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<div class="right_content">
	<div class="pagelist drop">
		<div>
			<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="newtabsInfo"></b> <s:text name='cmn_to' /> <b class="newtabsTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
		</div>
	</div>
	<table id="schedule" class="list">
		<tbody>
			<tr class="alt">
				<th width="50">
					<div class="list_check">
						<div class="drop">
							<label><input type="checkbox" name="chknewtabsIds" onchange="chooseAll(this)"></label>
							<ul>
								<li><a href="#" onclick="chooseAll();">所有</a></li>
								<li><a href="#">其他</a></li>
								<li><a href="#">式样</a></li>
								<li><a href="#">关闭</a></li>
							</ul>
						</div>
					</div>
				</th>
				<th style="cursor: pointer;" onclick="orderBy('uploadPersonName');"><s:text name="oa_sender" />&nbsp;<img id="newtabsImg_uploadPersonName" src="${vix}/common/img/arrow.gif"></th>
				<th style="cursor: pointer;" onclick="orderBy('senderPeople');"><s:text name="收信人" />&nbsp;<img id="newtabsImg_senderPeople" src="${vix}/common/img/arrow.gif"></th>
				<th style="cursor: pointer;" onclick="orderBy('ccPeople');"><s:text name="抄送人" />&nbsp;<img id="newtabsImg_ccPeople" src="${vix}/common/img/arrow.gif"></th>
				<th style="cursor: pointer;" onclick="orderBy('sendDate');"><s:text name="oa_transmission_time" />&nbsp;<img id="newtabsImg_sendDate" src="${vix}/common/img/arrow.gif"></th>
				<th style="cursor: pointer;" onclick="orderBy('newscontent');"><s:text name="oa_content" />&nbsp;<img id="newtabsImg_newscontent" src="${vix}/common/img/arrow.gif"></th>
				<th><s:text name="状态" /></th>
				<th><s:text name="oa_operating" /></th>
			</tr>
			<% int count =0; %>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<% count++; %>
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<td><a href="#" style="color: gray;">${entity.uploadPersonName}</a></td>
					<td><a href="#" style="color: gray;">${entity.senderPeople}</a></td>
					<td><a href="#" style="color: gray;">${entity.ccPeople} ${entity.ccPeople1}</a></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.sendDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></a></td>
					<td><a href="#" style="color: gray;">${entity.newscontent}</a></td>
					<td><a href="#" style="color: gray;"> <s:if test="%{#entity.isTop == 0}">已发送</s:if> <s:elseif test="%{#entity.isTop == 1}">未发送</s:elseif>
					</a></td>
					<td>
						<div class="untitled">
							<a href="#" onclick="saveCommentMessage('${entity.id}',0);">评论</a> <a href="javascript:;" onclick="looknewsComment('${entity.id}');">查看全文 </a> <a href="#" onclick="deleteEntity('${entity.id}');">删除</a>
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
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagelist drop">
		<div>
			<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="newtabsInfo"></b> <s:text name='cmn_to' /> <b class="newtabsTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
		</div>
	</div>
</div>