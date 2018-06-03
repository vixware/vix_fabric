<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
function deleteEntity(id){
	asyncbox.confirm('确定要删除该Bom节点么?','<s:text name='vix_message'/>',function(action){
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
	loadOrderByImage("${vix}","bomNode");
</script>
<!--物料编码，物料名称，规格型号，子件单位，数量，子件阶别  -->
<input type="hidden" id="bomNodeTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="bomNodePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="bomNodeTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="bomNodeOrderField" value="${pager.orderField}" />
<input type="hidden" id="bomNodeOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="bomNodeInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="table" class="list">
	<tr>
		<th width="100px"><span style="cursor: pointer;" onclick="orderBy('id');"> 编号&nbsp; <img id="bomNodeImg_id" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="180px"><span style="cursor: pointer;" onclick="orderBy('bomStruct.configItemBomName');"> Bom结构&nbsp;<img id="bomNodeImg_comStruct" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="180px"><span style="cursor: pointer;" onclick="orderBy('subject');"> 主题&nbsp;<img id="bomNodeImg_subject" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="100px"><span style="cursor: pointer;" onclick="orderBy('nodeType');"> 节点类型&nbsp;<img id="bomNodeImg_nodeType" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="180px"><span style="cursor: pointer;" onclick="orderBy('amount');"> 数量&nbsp;<img id="bomNodeImg_amount" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="10%">操作</th>
	</tr>
	<% int count = 0; %>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<% count++; %>
		<tr>
			<td>${entity.id}</td>
			<td>${entity.bomStruct.configItemBomName}</td>
			<td>${entity.subject}</td>
			<td>${entity.nodeType}</td>
			<td>${entity.amount}</td>
			<td align="center"><a href="#" onclick="saveOrUpdate('${entity.id}');"> <img src="${vix}/common/img/icon_edit.png" alt="修改" />
			</a> <a href="#" onclick="deleteEntity('${entity.id}');"> <img src="${vix}/common/img/icon_12.png" alt="删除" />
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
		</tr>
	</c:forEach>
</table>