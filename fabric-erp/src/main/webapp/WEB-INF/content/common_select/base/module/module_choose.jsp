<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$.returnValue = "";
function moduleSelectChange(chk,id,name){
	 if(chk.checked){
		 $.returnValue = $.returnValue +","+id+":"+name;
	 }else{
		 $.returnValue = $.returnValue.replace(","+id+":"+name,"");
	 }
} 

var moduleName = "";
function loadModuleName(){
	moduleName = $('#moduleName').val();
	moduleName= Url.encode(moduleName);
	moduleName = encodeURI(moduleName);
}

pager("start","${vix}/common/select/commonSelectModuleAction!goSingleList.action?tag=${tag}&industryMgtId=${industryMgtId}&chkStyle=${chkStyle}","moduleSelect");
//排序 
function subOrderBy(orderField){
	var orderBy = $("#moduleSelectOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/common/select/commonSelectModuleAction!goSingleList.action?tag=${tag}&industryMgtId=${industryMgtId}&chkStyle=${chkStyle}&orderField="+orderField+"&orderBy="+orderBy,"moduleSelect");
}

function currentPager(tag){
	loadModuleName();
	pager(tag,"${vix}/common/select/commonSelectModuleAction!goSingleList.action?tag=${tag}&industryMgtId=${industryMgtId}&chkStyle=${chkStyle}&moduleName="+moduleName,'moduleSelect');
}
</script>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="moduleName" value="${moduleName}"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="currentPager('start');" />
			</label>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="moduleSelectInfo"></b> <s:text name='cmn_to' /> <b class="moduleSelectTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="moduleSelect" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="moduleSelectInfo"></b> <s:text name='cmn_to' /> <b class="moduleSelectTotalCount"></b>)
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