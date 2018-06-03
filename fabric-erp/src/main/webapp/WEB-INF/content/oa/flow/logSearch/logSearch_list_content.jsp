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
			<th onclick="orderBy('id');">工作名称/文号&nbsp;<img src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('id');">步骤名称&nbsp;<img src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('id');">相关人员&nbsp;<img src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('id');">发生时间&nbsp;<img src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('id');">地址&nbsp;<img src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('id');">内容&nbsp;<img src="${vix}/common/img/arrow.gif"></th>
			<th><s:text name="operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>1002</td>
				<td>收文(2013-11-30 19:07:07)</td>
				<td>签收</td>
				<td>张三</td>
				<td>2013-12-01 14:23:13</td>
				<td>192.168.2.102</td>
				<td>根据自动委托规则把工作委托给李四</td>

				<td><a href="#" title="删除"> 删除 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>18596</td>
				<td>产品修改申请(2013-11-30 19:02:35)</td>
				<td>审批与处理</td>
				<td>张三</td>
				<td>2013-12-01 10:55:07</td>
				<td>192.168.2.102</td>
				<td>结束流程</td>

				<td><a href="#" title="删除"> 删除 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>10034</td>
				<td>请假申请(2013-12-01 08:41:29)</td>
				<td>请假申请</td>
				<td>张三</td>
				<td>2013-12-01 08:41:55</td>
				<td>192.168.2.109</td>
				<td>上传附件</td>

				<td><a href="#" title="删除"> 删除 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>18596</td>
				<td>产品修改申请(2013-11-30 19:02:35)</td>
				<td>审批与处理</td>
				<td>张三</td>
				<td>2013-12-01 10:55:07</td>
				<td>192.168.2.102</td>
				<td>结束流程</td>

				<td><a href="#" title="删除"> 删除 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>10025</td>
				<td>工作交办(2013-11-30 18:06:31)</td>
				<td>交办人交待工作</td>
				<td>张三</td>
				<td>2013-11-30 18:07:08</td>
				<td>192.168.2.102</td>
				<td>根据自动委托规则把工作委托给李四</td>

				<td><a href="#" title="删除"> 删除 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>10021</td>
				<td>用车申请(2013-11-30 18:06:20)</td>
				<td>申请用车</td>
				<td>张三</td>
				<td>2013-11-30 18:07:00</td>
				<td>192.168.2.102</td>
				<td>转交至步骤2,办理人:系统管理员</td>

				<td><a href="#" title="删除"> 删除 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>10025</td>
				<td>工作交办(2013-11-30 18:06:31)</td>
				<td>交办人交待工作</td>
				<td>张三</td>
				<td>2013-11-30 18:07:08</td>
				<td>192.168.2.102</td>
				<td>根据自动委托规则把工作委托给李四</td>

				<td><a href="#" title="删除"> 删除 </a></td>
			</tr>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>1002</td>
				<td>收文(2013-11-30 19:07:07)</td>
				<td>签收</td>
				<td>张三</td>
				<td>2013-12-01 14:23:13</td>
				<td>192.168.2.102</td>
				<td>根据自动委托规则把工作委托给李四</td>

				<td><a href="#" title="删除"> 删除 </a></td>
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