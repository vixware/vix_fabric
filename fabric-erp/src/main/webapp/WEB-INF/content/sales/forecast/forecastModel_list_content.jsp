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
	asyncbox.confirm('确定要删除该代垫费用么?','提示信息',function(action){
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
<input type="hidden" id="forecastModelTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="forecastModelPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="forecastModelTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="forecastModelOrderField" value="${pager.orderField}" />
<input type="hidden" id="forecastModelOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="forecastModelInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('name');"> 名称&nbsp; <s:if test="%{pager.orderField == 'name' && pager.orderBy == 'asc' }">
						<img src="${vix}/common/img/arrow_down.gif">
					</s:if> <s:elseif test="%{pager.orderField == 'name' && pager.orderBy == 'desc' }">
						<img src="${vix}/common/img/arrow_up.gif">
					</s:elseif> <s:else>
						<img src="${vix}/common/img/arrow.gif">
					</s:else>
			</span></th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('description');"> 描述&nbsp; <s:if test="%{pager.orderField == 'description' && pager.orderBy == 'asc' }">
						<img src="${vix}/common/img/arrow_down.gif">
					</s:if> <s:elseif test="%{pager.orderField == 'description' && pager.orderBy == 'desc' }">
						<img src="${vix}/common/img/arrow_up.gif">
					</s:elseif> <s:else>
						<img src="${vix}/common/img/arrow.gif">
					</s:else>
			</span></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('status');"> 状态&nbsp; <s:if test="%{pager.orderField == 'status' && pager.orderBy == 'asc' }">
						<img src="${vix}/common/img/arrow_down.gif">
					</s:if> <s:elseif test="%{pager.orderField == 'status' && pager.orderBy == 'desc' }">
						<img src="${vix}/common/img/arrow_up.gif">
					</s:elseif> <s:else>
						<img src="${vix}/common/img/arrow.gif">
					</s:else>
			</span></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('time');"> 时间&nbsp; <s:if test="%{pager.orderField == 'time' && pager.orderBy == 'asc' }">
						<img src="${vix}/common/img/arrow_down.gif">
					</s:if> <s:elseif test="%{pager.orderField == 'time' && pager.orderBy == 'desc' }">
						<img src="${vix}/common/img/arrow_up.gif">
					</s:elseif> <s:else>
						<img src="${vix}/common/img/arrow.gif">
					</s:else>
			</span></th>
			<th><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td>${entity.name}</td>
				<td>${entity.description}</td>
				<td>${entity.status}</td>
				<td>${entity.time}</td>
				<td><a href="#" onclick="saveOrUpdate(${entity.id},${pager.pageNo});" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
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
			</tr>
		</c:forEach>
	</tbody>
</table>