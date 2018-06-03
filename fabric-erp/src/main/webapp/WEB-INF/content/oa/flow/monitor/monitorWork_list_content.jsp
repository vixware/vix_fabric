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
	asyncbox.confirm('确定要删除该订单么?','提示信息',function(action){
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
<input type="hidden" id="orderTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="orderPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="orderTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="orderOrderField" value="${pager.orderField}" />
<input type="hidden" id="orderOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="orderInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="50">
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
			<th onclick="orderBy('id');">流水号&nbsp;<img src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('id');">流程类型&nbsp;<img src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('id');">工作名称/文号&nbsp;<img src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('id');">当前步骤&nbsp;<img src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('id');">当前办理人&nbsp;<img src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('id');">办理时间&nbsp;<img src="${vix}/common/img/arrow.gif"></th>
			<th><s:text name="operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>18604</td>
				<td>收文</td>
				<td>收文(2013-11-30 19:07:07)</td>
				<td>收文</td>
				<td>张三</td>
				<td>2013-11-30 19:07:07</td>
				<td></td>
			</tr>

			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>18605</td>
				<td>产品修改申请</td>
				<td>产品修改申请(2013-11-30 19:07:07)</td>
				<td>产品修改</td>
				<td>张三</td>
				<td>2013-11-30 19:27:07</td>
				<td></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>18602</td>
				<td>加班登记</td>
				<td>加班登记(2013-11-30 18:27:16)</td>
				<td>加班登记</td>
				<td>张三</td>
				<td>2013-11-30 18:27:16</td>
				<td></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>18594</td>
				<td>工作交办</td>
				<td>工作交办(2013-11-30 18:06:31</td>
				<td>加班登记</td>
				<td>张三</td>
				<td>2013-11-30 18:06:42</td>
				<td></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>18594</td>
				<td>工作交办</td>
				<td>工作交办(2013-11-30 19:06:31</td>
				<td>工作交办</td>
				<td>张三</td>
				<td>2013-11-30 19:06:42</td>
				<td></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>18590</td>
				<td>请假申请</td>
				<td>请假申请(2013-11-30 17:12:23)</td>
				<td>请假申请</td>
				<td>张三</td>
				<td>2013-11-30 17:12:28</td>
				<td></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>18588</td>
				<td>订单、生产、发货</td>
				<td>订单、生产、发货(2013-11-30 15:30:38)</td>
				<td>订单、生产、发货</td>
				<td>张三</td>
				<td>2013-11-30 15:30:57</td>
				<td></td>

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