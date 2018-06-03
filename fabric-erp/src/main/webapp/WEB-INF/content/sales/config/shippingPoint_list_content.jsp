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
	asyncbox.confirm('确定要删除该发运点么?','<s:text name='删除该发运点'/>',function(action){
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
loadOrderByImage("${vix}","shippingPoint");
</script>
<input type="hidden" id="shippingPointTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="shippingPointPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="shippingPointTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="shippingPointOrderField" value="${pager.orderField}" />
<input type="hidden" id="shippingPointOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="shippingPointInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="所有" /></a></li>
							<li><a href="#"><s:text name="其它" /></a></li>
							<li><a href="#"><s:text name="关闭" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th onclick="orderBy('id');" style="cursor: pointer;" width="10%">编号&nbsp;<img id="shippingPointImg_id" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('code');" style="cursor: pointer;" width="15%">编码&nbsp;<img id="shippingPointImg_code" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('name');" style="cursor: pointer;" width="15%">名称&nbsp;<img id="shippingPointImg_name" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('address');" style="cursor: pointer;" width="25%">地址&nbsp;<img id="shippingPointImg_address" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('phone');" style="cursor: pointer;" width="10%">电话&nbsp;<img id="shippingPointImg_phone" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('mobie');" style="cursor: pointer;" width="10%">手机&nbsp;<img id="shippingPointImg_mobie" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%">操作</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.code}</td>
				<td>${entity.name}</td>
				<td>${entity.address}</td>
				<td>${entity.phone}</td>
				<td>${entity.mobie}</td>
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
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>