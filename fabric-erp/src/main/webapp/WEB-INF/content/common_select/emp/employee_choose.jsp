<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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


var epmName = "";
function loadEpmName(){
	epmName = $("#epmName").val();
	epmName= Url.encode(epmName);
	epmName = encodeURI(epmName);
	
}



function searchForSelectEpmContent(){
	loadEpmName();
	pager("start","${vix}/common/select/commonSelectEmpAction!goSingleList.action?tag=${tag}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&empName="+epmName,'commonEmpSelect');
}

loadEpmName();
//载入分页列表数据
pager("start","${vix}/common/select/commonSelectEmpAction!goSingleList.action?tag=${tag}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}","commonEmpSelect");



function commonEmpSelectPager(tag,entity){
	loadName();
	if(entity == 'commonEmpSelect'){
		pager(tag,"${vix}/common/select/commonSelectEmpAction!goSingleList.action?tag=${tag}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&empName="+epmName+'&id='+$('#cmnEmOrgselectId').val(),entity);
	}
	/* if(entity == 'brand'){
		pager(tag,"${vix}/template/simpleGridAction!goSubSingleList.action?tag=list&commonEmpSelectId="+$('#cmnEmOrgselectId').val(),entity);
	} */
}


//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#commonEmpSelectOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/common/select/commonSelectEmpAction!goSingleList.action?tag=${tag}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&orderField="+orderField+"&orderBy="+orderBy+"&empName="+epmName+"&id="+$("#cmnEmOrgselectId").val(),'commonEmpSelect');
}

function resetSelectEmp(){
	$('#epmName').val('');
};

bindSearch();
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
</script>
<div class="content" style="background: #DCE7F1">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b></b>
				</strong>
				<p></p>
			</div>
		</div>
		<!-- <ul>
			<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
			<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
			<li class="fly">
				<a href="#"><img src="img/icon_10.png" alt="" />information</a>
				<ul>
					<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
				</ul>
			</li> 
		</ul> -->
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="epmName" value="${empName}"></label>
			<%-- <label><s:text name="cmn_my_item"/><input type="checkbox" value="" name=""></label> --%>
			<label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForSelectEpmContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetSelectEmp();" /></label>
			<%-- <strong id="lb_search_advanced"><s:text name="cmn_advance_search"/></strong> --%>
		</div>
		<%-- <div class="search_advanced">
			<label><s:text name="cmn_name"/><input type="text" class="int" name=""></label>
			<label><s:text name="cmn_my_item"/><input type="checkbox" value="" name=""></label>
			<label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""></label>
			<label><s:text name="cmn_name"/><input type="text" class="int" name=""></label>
			<label><s:text name="cmn_my_item"/><input type="checkbox" value="" name=""></label>
			<label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""></label>
		</div> --%>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div class="switch_btn" id="switch_box"></div>
			<div class="left_content" id="tree_00">
				<div id="empSelect_tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				<!--
				var zTreeEmp;			
				var settingZTreeEmp = {
					async: {
						enable: true,
						url:"${vix}/common/select/commonSelectEmpAction!findOrgAndUnitTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClickZTreeEmp
					}
				};
				function onClickZTreeEmp(event, treeId, treeNode, clickFlag) {
					$("#cmnEmOrgselectId").val(treeNode.id);
					$("#cmnEmOrgselectIdTreeId").val(treeNode.tId);
					//alert(treeNode.tId+ "--" + treeNode.treeType );
					pager("start","${vix}/common/select/commonSelectEmpAction!goSingleList.action?chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&id="+treeNode.id+"&treeType="+treeNode.treeType,"commonEmpSelect");
				}
				zTreeEmp = $.fn.zTree.init($("#empSelect_tree_root"), settingZTreeEmp);
				//-->
			</script>
		</div>
		<input type="hidden" id="cmnEmOrgselectId" name="cmnEmOrgselectId" value="${parentId}" /> <input type="hidden" id="cmnEmOrgselectIdTreeId" name="cmnEmOrgselectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop clearfix">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a> <!-- <ul>
								<li><a href="#">Actions</a></li>
							</ul> --></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectcommonEmpSelectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="commonEmpSelectPager('start','commonEmpSelect');"></a></span> <span><a class="previous" onclick="commonEmpSelectPager('previous','commonEmpSelect');"></a></span> <em>(<b class="commonEmpSelectInfo"></b> <s:text name='cmn_to' /> <b class="commonEmpSelectTotalCount"></b>)
						</em> <span><a class="next" onclick="commonEmpSelectPager('next','commonEmpSelect');"></a></span> <span><a class="end" onclick="commonEmpSelectPager('end','commonEmpSelect');"></a></span>
					</div>
				</div>
				<div id="commonEmpSelect" class="table"></div>
				<div class="pagelist drop clearfix">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a> <%-- <ul>
								<li><a href="#"><s:text name='cmn_delete'/></a></li>
								<li><a href="#"><s:text name='cmn_email'/></a></li>
								<li><a href="#"><s:text name="merger"/></a></li>
								<li><a href="#"><s:text name="export"/></a></li>
							</ul> --%></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectcommonEmpSelectCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="commonEmpSelectPager('start','commonEmpSelect');"></a></span> <span><a class="previous" onclick="commonEmpSelectPager('previous','commonEmpSelect');"></a></span> <em>(<b class="commonEmpSelectInfo"></b> <s:text name='cmn_to' /> <b class="commonEmpSelectTotalCount"></b>)
						</em> <span><a class="next" onclick="commonEmpSelectPager('next','commonEmpSelect');"></a></span> <span><a class="end" onclick="commonEmpSelectPager('end','commonEmpSelect');"></a></span>
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