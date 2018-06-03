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
	epmName = encodeURI(epmName);
}



function searchForSelectEpmContent(){
	loadEpmName();
	pager("start","${vix}/hr/salary/salaryProjectEmpAction!goSingleList.action?tag=${tag}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&empName="+epmName,'category');
}

loadEpmName();
//载入分页列表数据
pager("start","${vix}/hr/salary/salaryProjectEmpAction!goSingleList.action?tag=${tag}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}","category");



function categoryPager(tag,entity){
	loadName();
	if(entity == 'category'){
		pager(tag,"${vix}/hr/salary/salaryProjectEmpAction!goSingleList.action?tag=${tag}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&empName="+epmName+'&id='+$('#selectId').val(),entity);
	}
	/* if(entity == 'brand'){
		pager(tag,"${vix}/template/simpleGridAction!goSubSingleList.action?tag=list&categoryId="+$('#selectId').val(),entity);
	} */
}


//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#categoryOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/hr/salary/salaryProjectEmpAction!goSingleList.action?tag=${tag}&chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&orderField="+orderField+"&orderBy="+orderBy+"&empName="+epmName+"&id="+$("#selectId").val(),'category');
}


/**
 * 设定职员的工资类别
 */
function setEmpSalaryProject(id){
	//alert("设置职员的工资类别！");
	
	$.ajax({
		  url:'${vix}/hr/salary/salaryProjectEmpAction!goSelectSalaryProject.action',
		  data:{},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 860,
					height : 550,
					title:"工资类别选择",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//alert(returnValue);
								var selectIds = "";
								var selectNames = "";

								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										selectIds = v[0];
										selectNames = v[1];
									}
								}
								//alert("empId:"+id+",selectIds:"+selectIds);
								
								if(selectIds!="" && id!=""){
									var spEmpParam = "salaryProjectId=" + selectIds + "&empId="+id; 
									$.post('${vix}/hr/salary/salaryProjectEmpAction!addSalaryProjectForEmp.action',
										spEmpParam,
										function(result){
											showMessage(result);
											setTimeout("", 1000);
											//asyncbox.success(result,"<s:text name='cmn_message'/>",function(action){
												var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
												if(null != treeNode){
													treeNode.isParent = true;
												}
												zTree.reAsyncChildNodes(treeNode, "refresh");
												//pager("start","${vix}/common/org/organizationUnitAction!goSingleList.action?fullName="+name+"&id="+$("#selectId").val()+"&treeType="+treeNode.treeType,'category');
												pager("start","${vix}/hr/salary/salaryProjectEmpAction!goSingleList.action?chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&id="+treeNode.id+"&treeType="+treeNode.treeType,"category");
												
											//});
										}
									 );
								}
								
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
	
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
			<label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForSelectEpmContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" /></label> <strong id="lb_search_advanced"><s:text name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><s:text name="cmn_my_item" /><input type="checkbox" value="" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>"
				class="btn" name=""></label> <label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><s:text name="cmn_my_item" /><input type="checkbox" value="" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button"
				value="<s:text name='cmn_reset'/>" class="btn" name=""></label>
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
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/hr/salary/salaryProjectEmpAction!findOrgAndUnitTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					//alert(treeNode.tId+ "--" + treeNode.treeType );
					pager("start","${vix}/hr/salary/salaryProjectEmpAction!goSingleList.action?chkStyle=${chkStyle}&checkedObjIds=${checkedObjIds}&id="+treeNode.id+"&treeType="+treeNode.treeType,"category");
				}
				zTree = $.fn.zTree.init($("#empSelect_tree_root"), setting);
				//-->
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop clearfix">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a> <!-- <ul>
								<li><a href="#">Actions</a></li>
							</ul> --></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','category');"></a></span> <span><a class="previous" onclick="categoryPager('previous','category');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a></span> <span><a class="end" onclick="categoryPager('end','category');"></a></span>
					</div>
				</div>
				<div id="category" class="table"></div>
				<div class="pagelist drop clearfix">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a> <%-- <ul>
								<li><a href="#"><s:text name='cmn_delete'/></a></li>
								<li><a href="#"><s:text name='cmn_email'/></a></li>
								<li><a href="#"><s:text name="merger"/></a></li>
								<li><a href="#"><s:text name="export"/></a></li>
							</ul> --%></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','category');"></a></span> <span><a class="previous" onclick="categoryPager('previous','category');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a></span> <span><a class="end" onclick="categoryPager('end','category');"></a></span>
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