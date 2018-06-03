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

function chooseOther(){
	$.each($("input[name='chkId']"), function(i, n){
		if($(n).attr('checked')){
			$(n).attr('checked',false);
		}else{
			$(n).attr('checked',true);
		}
	});
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
	asyncbox.confirm('确定要删除该${vv:varView('vix_mdm_item')}么?','提示信息',function(action){
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
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","item");
</script>
<input type="hidden" id="itemTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="itemPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="itemTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="itemOrderField" value="${pager.orderField}" />
<input type="hidden" id="itemOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="itemInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="50">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /></a></li>
							<li><a href="#" onclick="chooseOther();"><s:text name="cmn_other" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th style="cursor: pointer;" onclick="orderBy('code');">编码&nbsp;<img id="itemImg_code" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('name');">名称&nbsp;<img id="itemImg_name" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('specification');">规格型号&nbsp;<img id="itemImg_specification" src="${vix}/common/img/arrow.gif">
			</th>
			<%-- <th style="cursor: pointer;" onclick="orderBy('measureUnit.id');">
				计量单位&nbsp;<img id="itemImg_measureUnit" src="${vix}/common/img/arrow.gif">
			</th> --%>
			<th style="cursor: pointer;" onclick="orderBy('status');">价格&nbsp;<img id="itemImg_price" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('status');">状态&nbsp;<img id="itemImg_status" src="${vix}/common/img/arrow.gif">
			</th>
			<th><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.code}" type="checkbox" onchange="selectCount()" /></td>
				<td>${entity.code}</td>
				<td>${entity.name}</td>
				<td>${entity.specification}</td>
				<%-- <td>${entity.measureUnit.name}</td> --%>
				<td>${entity.price}</td>
				<td><s:if test="#entity.status == 0">
						禁用
					</s:if> <s:elseif test="#entity.status == 1">
						激活
					</s:elseif></td>
				<td><s:if test="tag != 'choose'">
						<s:if test="#entity.type == 'circulationIndustry'">
							<a href="#" onclick="fastAdd('${entity.id}');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
							</a>
						</s:if>
						<s:else>
							<a href="#" onclick="saveOrUpdate('${entity.id}',${pager.pageNo});" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
							</a>
						</s:else>
						<a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='delete'/>"> <img src="${vix}/common/img/icon_12.png" />
						</a>
					</s:if></td>
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
				<!-- 	<td>&nbsp;</td> -->
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>