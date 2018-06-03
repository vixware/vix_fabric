<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$.returnValue = "";
function chkChange(chk,id,name){
	 if(chk.checked){
		 $.returnValue = $.returnValue +","+id+":"+name;
	 }else{
		 $.returnValue = $.returnValue.replace(","+id+":"+name,"");
	 }
} 
pager("start","${vix}/hr/salary/salaryProjectEmpAction!goSelectSalaryProjectList.action?tag=${tag}&chkStyle=${chkStyle}","metaDataChk");
//排序 
function subOrderBy(orderField){
	var orderBy = $("#subBrandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/hr/salary/salaryProjectEmpAction!goSelectSalaryProjectList.action?tag=${tag}&chkStyle=${chkStyle}&orderField="+orderField+"&orderBy="+orderBy,"metaDataChk");
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/hr/salary/salaryProjectEmpAction!goSelectSalaryProjectList.action?tag=${tag}&chkStyle=${chkStyle}&name="+name,'metaDataChk');
}
</script>
<div class="table" style="margin: 5px;">
	<div id="metaDataChk"></div>
	<div class="pagelist">
		<div>
			<span> <span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="metaDataChkInfo"></b>到 <b class="metaDataChkTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
			</span>
		</div>
	</div>
</div>