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


var posName = "";
function loadPosName(){
	posName = $("#posName").val();
	posName = encodeURI(posName);
}



function searchForSelectEpmContent(){
	loadPosName();
	pager("start","${vix}/common/select/commonSelectPosAction!goSingleList.action?tag=${tag}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&posName="+posName,'commonPosSelect');
}

loadposName();
//载入分页列表数据
pager("start","${vix}/common/select/commonSelectPosAction!goSingleList.action?tag=${tag}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}","commonPosSelect");



function commonPosSelectPager(tag,entity){
	loadPosName();
	if(entity == 'commonPosSelect'){
		pager(tag,"${vix}/common/select/commonSelectPosAction!goSingleList.action?tag=${tag}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&posName="+posName+'&treeId='+$('#cmnEmOrgselectId').val(),entity);
	}
	/* if(entity == 'brand'){
		pager(tag,"${vix}/template/simpleGridAction!goSubSingleList.action?tag=list&commonPosSelectId="+$('#cmnEmOrgselectId').val(),entity);
	} */
}


//排序 
function orderBy(orderField){
	loadPosName();
	var orderBy = $("#commonPosSelectOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/common/select/commonSelectPosAction!goSingleList.action?tag=${tag}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&orderField="+orderField+"&orderBy="+orderBy+"&posName="+posName+"&treeId="+$("#cmnEmOrgselectId").val(),'commonPosSelect');
}


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
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="posName" value="${posName}"></label>
			<%-- <label><s:text name="cmn_my_item"/><input type="checkbox" value="" name=""></label> --%>
			<label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForSelectEpmContent();" />
			</label>
		</div>
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
						url:"${vix}/common/select/commonSelectPosAction!findOrgAndUnitTreeToJson.action",
						autoParam:["treeId","treeType"]
					},
					callback: {
						onClick: onClickZTreeEmp
					}
				};
				function onClickZTreeEmp(event, treeId, treeNode, clickFlag) {
					$("#cmnEmOrgselectId").val(treeNode.treeId);
					$("#cmnEmOrgselectIdTreeId").val(treeNode.tId);
					//alert(treeNode.tId+ "--" + treeNode.treeType );
					pager("start","${vix}/common/select/commonSelectPosAction!goSingleList.action?chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&treeId="+treeNode.treeId+"&treeType="+treeNode.treeType,"commonPosSelect");
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
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="commonPosSelectPager('start','commonPosSelect');"></a></span> <span><a class="previous" onclick="commonPosSelectPager('previous','commonPosSelect');"></a></span> <em>(<b class="commonPosSelectInfo"></b> <s:text name='cmn_to' /> <b class="commonPosSelectTotalCount"></b>)
						</em> <span><a class="next" onclick="commonPosSelectPager('next','commonPosSelect');"></a></span> <span><a class="end" onclick="commonPosSelectPager('end','commonPosSelect');"></a></span>
					</div>
				</div>
				<div id="commonPosSelect" class="table"></div>
				<div class="pagelist drop clearfix">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a> <%-- <ul>
								<li><a href="#"><s:text name='cmn_delete'/></a></li>
								<li><a href="#"><s:text name='cmn_email'/></a></li>
								<li><a href="#"><s:text name="merger"/></a></li>
								<li><a href="#"><s:text name="export"/></a></li>
							</ul> --%></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="commonPosSelectPager('start','commonPosSelect');"></a></span> <span><a class="previous" onclick="commonPosSelectPager('previous','commonPosSelect');"></a></span> <em>(<b class="commonPosSelectInfo"></b> <s:text name='cmn_to' /> <b class="commonPosSelectTotalCount"></b>)
						</em> <span><a class="next" onclick="commonPosSelectPager('next','commonPosSelect');"></a></span> <span><a class="end" onclick="commonPosSelectPager('end','commonPosSelect');"></a></span>
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