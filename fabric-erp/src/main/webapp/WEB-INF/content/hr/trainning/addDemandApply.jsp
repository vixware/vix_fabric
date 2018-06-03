<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$.returnValue = "";
function chkChange(chk,id,applicationName,applicationDepartment){
	 if(chk.checked){
		 $.returnValue = id+","+applicationName+","+applicationDepartment;
	 }
} 
pager("start","${vix}/hr/demandSummaryAction!goDemandApplyLists.action?temp=1","radio");
//排序 
function subOrderBy(orderField){
	var orderBy = $("#subBrandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/hr/demandSummaryAction!goDemandApplyLists.action?orderField="+orderField+"&orderBy="+orderBy,"radio");
}

function currentPager(tag){
	pager(tag,"${vix}/hr/demandSummaryAction!goDemandApplyLists.action?code="+code+"&pageNo=${pageNo}",'radio');
}
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
</script>
<div class="content" style="background: #DCE7F1">
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div id="radio"></div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>