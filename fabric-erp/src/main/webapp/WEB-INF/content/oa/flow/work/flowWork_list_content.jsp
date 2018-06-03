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
			<th width="50%" onclick="orderBy('id');">工作&nbsp;<img src="${vix}/common/img/arrow.gif"></th>
			<th><s:text name="operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>收文</td>
				<td><a href="#" title="查看流程设计图"> 查看流程设计图 </a> <a href="#" title="查看流程表单"> 查看流程表单 </a> <a href="#" title="查看流程说明"> 查看流程说明 </a> <a href="#" title="创建"> 创建 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>工作交办</td>
				<td><a href="#" title="查看流程设计图"> 查看流程设计图 </a> <a href="#" title="查看流程表单"> 查看流程表单 </a> <a href="#" title="查看流程说明"> 查看流程说明 </a> <a href="#" title="创建"> 创建 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>督查督办</td>
				<td><a href="#" title="查看流程设计图"> 查看流程设计图 </a> <a href="#" title="查看流程表单"> 查看流程表单 </a> <a href="#" title="查看流程说明"> 查看流程说明 </a> <a href="#" title="创建"> 创建 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>值班登记</td>
				<td><a href="#" title="查看流程设计图"> 查看流程设计图 </a> <a href="#" title="查看流程表单"> 查看流程表单 </a> <a href="#" title="查看流程说明"> 查看流程说明 </a> <a href="#" title="创建"> 创建 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>办公用品申领</td>
				<td><a href="#" title="查看流程设计图"> 查看流程设计图 </a> <a href="#" title="查看流程表单"> 查看流程表单 </a> <a href="#" title="查看流程说明"> 查看流程说明 </a> <a href="#" title="创建"> 创建 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>会议申请</td>
				<td><a href="#" title="查看流程设计图"> 查看流程设计图 </a> <a href="#" title="查看流程表单"> 查看流程表单 </a> <a href="#" title="查看流程说明"> 查看流程说明 </a> <a href="#" title="创建"> 创建 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>用车申请</td>
				<td><a href="#" title="查看流程设计图"> 查看流程设计图 </a> <a href="#" title="查看流程表单"> 查看流程表单 </a> <a href="#" title="查看流程说明"> 查看流程说明 </a> <a href="#" title="创建"> 创建 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>请假申请</td>
				<td><a href="#" title="查看流程设计图"> 查看流程设计图 </a> <a href="#" title="查看流程表单"> 查看流程表单 </a> <a href="#" title="查看流程说明"> 查看流程说明 </a> <a href="#" title="创建"> 创建 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>出差申请</td>
				<td><a href="#" title="查看流程设计图"> 查看流程设计图 </a> <a href="#" title="查看流程表单"> 查看流程表单 </a> <a href="#" title="查看流程说明"> 查看流程说明 </a> <a href="#" title="创建"> 创建 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>加班登记</td>
				<td><a href="#" title="查看流程设计图"> 查看流程设计图 </a> <a href="#" title="查看流程表单"> 查看流程表单 </a> <a href="#" title="查看流程说明"> 查看流程说明 </a> <a href="#" title="创建"> 创建 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>奖惩拟案</td>
				<td><a href="#" title="查看流程设计图"> 查看流程设计图 </a> <a href="#" title="查看流程表单"> 查看流程表单 </a> <a href="#" title="查看流程说明"> 查看流程说明 </a> <a href="#" title="创建"> 创建 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>请款(借支)申请</td>
				<td><a href="#" title="查看流程设计图"> 查看流程设计图 </a> <a href="#" title="查看流程表单"> 查看流程表单 </a> <a href="#" title="查看流程说明"> 查看流程说明 </a> <a href="#" title="创建"> 创建 </a></td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>