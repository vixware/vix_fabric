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
pager("start","${vix}/common/security/dataResRowPolicyObjSelectAction!goChooseCommonBizList.action?id=${id}&tag=${tag}","chk");
//排序 
function subOrderBy(orderField){
	var orderBy = $("#subBrandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/common/security/dataResRowPolicyObjSelectAction!goChooseCommonBizList.action?id=${id}&tag=${tag}&orderField="+orderField+"&orderBy="+orderBy,"chk");
}

function currentPager(tag){
	//loadName();
	pager(tag,"${vix}/common/security/dataResRowPolicyObjSelectAction!goChooseCommonBizList.action?id=${id}&tag=${tag}",'chk');
}
</script>
<div class="table" style="margin: 5px;">
	<div id="chk"></div>
	<div class="pagelist">
		<div>
			<span> <span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="chkInfo"></b>到 <b class="chkTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
			</span>
		</div>
	</div>
</div>