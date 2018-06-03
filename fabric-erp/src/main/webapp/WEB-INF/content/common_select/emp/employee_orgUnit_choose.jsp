<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$.returnValue = "";
function empChange(chk,id,name){
	 if(chk.checked){
		 $.returnValue = $.returnValue +","+id+":"+name;
	 }else{
		 $.returnValue = $.returnValue.replace(","+id+":"+name,"");
	 }
} 

var empName = "";
function loadEmpName(){
	empName = $('#empName').val();
	empName= Url.encode(empName);
	empName = encodeURI(empName);
}

pager("start","${vix}/common/select/commonSelectEmpByOrgAction!goSingleList.action?orgUnitId=${orgUnitId}&tag=${tag}&chkStyle=${chkStyle}","emp");
//排序 
function subOrderBy(orderField){
	var orderBy = $("#subBrandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/common/select/commonSelectEmpByOrgAction!goSingleList.action?orgUnitId=${orgUnitId}tag=${tag}&chkStyle=${chkStyle}&name="+empName+"&orderField="+orderField+"&orderBy="+orderBy,"emp");
}

function currentPager(tag){
	loadEmpName();
	pager(tag,"${vix}/common/select/commonSelectEmpByOrgAction!goSingleList.action?orgUnitId=${orgUnitId}tag=${tag}&chkStyle=${chkStyle}&name="+empName,'emp');
}
</script>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<%-- <ul>
			<li><a href="#" id="numBtn"><img src="${vix}/common/img/icon_10.png" alt="" /><s:text name="cmn_index"/></a></li>
			<li><a href="#"><img alt="" src="${vix}/common/img/icon_10.png"><s:text name="cmn_category"/></a></li>
		</ul> --%>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="empName" value="${empName}"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="currentPager('start');" /> </label>
		</div>

	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_email' /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="empInfo"></b> <s:text name='cmn_to' /> <b class="empTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="emp" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_email' /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="empInfo"></b> <s:text name='cmn_to' /> <b class="empTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>