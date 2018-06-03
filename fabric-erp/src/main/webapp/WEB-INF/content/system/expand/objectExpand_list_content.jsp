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
	asyncbox.confirm('确定要删除该对象类型么?','<s:text name='vix_message'/>',function(action){
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

</script>
<input type="hidden" id="objectExpandTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="objectExpandPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="objectExpandTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="objectExpandOrderField" value="${pager.orderField}" />
<input type="hidden" id="objectExpandOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="objectExpandInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();">所有</a></li>
							<li><a href="#">其他</a></li>
							<li><a href="#">关闭</a></li>
						</ul>
					</div>
				</div>
			</th>
			<th onclick="orderBy('id');" width="10%">编号&nbsp; <s:if test="%{pager.orderField == 'id' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'id' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else>
			</th>
			<th width="200px"><span style="cursor: pointer;" onclick="orderBy('name');"><s:text name="cmn_name" />&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
			<th width="100px"><span style="cursor: pointer;" onclick="orderBy('isReference');">是否已用&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
			<th width="160px"><span style="cursor: pointer;" onclick="orderBy('expandTableName');">扩展表&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
			<th width="80px"><span style="cursor: pointer;" onclick="orderBy('status');">状态&nbsp;<img src="${vix}/common/img/arrow.gif"></span></th>
			<th width="100px">操作</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.name}</td>
				<td><s:if test="#entity.isReference == 0">
						未用
					</s:if> <s:elseif test="#entity.isReference == 1">
						已用
					</s:elseif> <s:else>
						未用
					</s:else></td>
				<td style="text-align: left;">${entity.expandTableName}</td>
				<td><s:if test="#entity.status == 0">
						禁用
					</s:if> <s:elseif test="#entity.status == 1">
						激活
					</s:elseif></td>
				<td align="center"><a href="#" onclick="createOrUpdateTable('${entity.id}');" title="建表"> <img src="${vix}/common/img/table_add.png" />
				</a> <a href="#" onclick="saveOrUpdate('${entity.id}');" title="修改"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="deleteEntity('${entity.id}');" title="删除"> <img src="${vix}/common/img/icon_12.png" />
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