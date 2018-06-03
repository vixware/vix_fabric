<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#tableChoose tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#tableChoose tr:even").addClass("alt");
$.returnValue = "";
function chkChange(chk,id,name){
	$.returnValue = id+","+name;
}
subChangePage("start","${vix}/system/employeeAction!goSubSingleList.action?temp=1");
//排序 
function subOrderBy(orderField){
	var orderBy = $("#orderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	subChangePage("start","${vix}/system/employeeAction!goSubSingleList.action?orderField="+orderField+"&orderBy="+orderBy);
}
</script>
<div class="table" style="margin: 5px;">
	<div id="subTableContent"></div>
	<div class="pagelist">
		<strong>共有<i id="subTotalCount">${pager.totalCount}</i>条数据,当前第<i id="subPageNo">${pager.pageNo}</i>页,共<i id="subTotalPage">${pager.totalPage}</i>页
		</strong> <span> <b><a id="start" href="#" onclick="subChangePage('start','${vix}/system/employeeAction!goSubSingleList.action?temp=1');">首页</a></b> <b><a id="previous" href="#" onclick="subChangePage('previous','${vix}/system/employeeAction!goSubSingleList.action?temp=1');">上一页</a></b> <b><input id="subPageNo_text" type="text"
				style="text-align: center;" size="4" value="${pager.pageNo}" onkeydown="subPageNoChangeClick(this,event,'${vix}/system/employeeAction!goSubSingleList.action?temp=1');" /></b> <b><a id="next" href="#" onclick="subChangePage('next','${vix}/system/employeeAction!goSubSingleList.action?temp=1');">下一页</a></b> <b><a id="end" href="#"
				onclick="subChangePage('end','${vix}/system/employeeAction!goSubSingleList.action?temp=1');">尾页</a></b>
		</span>
	</div>
</div>