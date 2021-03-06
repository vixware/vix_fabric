<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#businessViewTitle').val();
	name = encodeURI(name);
}
$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').stop().animate({height: 'toggle', opacity: 'toggle'},500,function(){
		$('#number').css('overflow','visible');
	});
	return false;
});
$(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
});

function saveOrUpdate(id,parentId){
	if(parentId ==0 || parentId == "" ){
		//asyncbox.alert("请先选择上级（集团）公司！","提示");
		showMessage("请先选择上级（集团）公司！");
		setTimeout("", 1000);
		return;
	}
	$.ajax({
		  url:'${vix}/common/org/businessViewAction!goSaveOrUpdate.action?id='+id+"&companyId="+parentId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 360,
					title:"业务视图",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							/* if($("#empId").val()==""){
								asyncbox.success("职员必须选择！","错误" );
								return;
							} */
							if($("#businessViewForm").validationEngine('validate')){
								var queryString = $('#businessViewForm').formSerialize(); 
								$.post('${vix}/common/org/businessViewAction!saveOrUpdate.action',
									queryString,
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										pager("start","${vix}/common/org/businessViewAction!goSingleList.action?orgName="+name+"&companyId="+$("#selectId").val(),'businessView');
									}
								 );
							}else {
								return false;
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};


/* function saveOrUpdateNews(){
	asyncbox.confirm('确定要保存么?','提示信息',function(action){
		if(action == 'ok'){
			
			$("#newsContentHidden").val(newsEditor.html());
			
			var queryString = $('#newsForm').formSerialize(); 

			$.post('${vix}/common/org/businessViewAction!saveOrUpdate.action',
				queryString,
				function(result){
					asyncbox.success(result,"<s:text name='message'/>",function(action){
						if(action == 'ok'){
							loadContent('${vix}/common/org/businessViewAction!goList.action?title=${title}');
						}
						
					});
				}
			 );
		}
	});
} */

$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
function deleteByIds(){
	var ids = '';
	$("[name='chkId']").each(function(){  
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	asyncbox.success(ids,"选中的id");
}

function deleteById(id){
	$.ajax({
		url:'${vix}/common/org/businessViewAction!deleteById.action?id='+id,
		cache: false,
		success: function(html){
			showMessage(html);
			setTimeout("", 1000);
			pager("start","${vix}/common/org/businessViewAction!goSingleList.action?businessViewName="+name+"&companyId="+$("#selectId").val(),'businessView');
		}
	});
	
}

function searchForContent(){
	loadName();
	//'&id='+$('#selectId').val()+"&treeType="+$("#selectIdType").val()
	pager("start","${vix}/common/org/businessViewAction!goSingleList.action?businessViewName="+name,'businessView');
}
 

//载入分页列表数据
pager("start","${vix}/common/org/businessViewAction!goSingleList.action?businessViewName="+name,'businessView');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#roleOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/common/org/businessViewAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&businessViewName="+name,'businessView');
}

function createDefaultBvRel(){
	var msgTmp = "";
	var confirmMsg = "确认要创建企业职员上下级关系吗?";
	$.ajax({
		url:"${vix}/common/org/businessViewAction!checkBizViewHasDefaultRelation.action",
		cache: false,
		async:false,
		dataType : "json",
		success: function(data){
			//var resData = $.parseJSON(data);
			isSuccess = data.isSuccess;
			msgTmp = data.msg;
		}
	});
	/* if(!isSuccess){
		showMessage(msgTmp);
		setTimeout("", 1000);
		return;
	} */
	if(isSuccess){
		confirmMsg = "确认要重建企业职员上下级关系吗?"
	}
	if(confirm(confirmMsg)){
		$.post('${vix}/common/org/businessViewAction!createBizViewHasDefaultRelation.action',
			"",
			function(result){
				showMessage(result);
				setTimeout("", 1000);
				pager("start","${vix}/common/org/businessViewAction!goSingleList.action?orgName="+name+"&companyId="+$("#selectId").val(),'businessView');
			}
		 );
	}
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/common/org/businessViewAction!goSingleList.action?businessViewName="+name,'businessView');
}

$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><img src="${vix}/common/img/hr_org.png" alt="" /><a href="#"><s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_org" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sys" /></a></li>
				<li><a href="#">业务组织视图</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name='cmn_add' /></span></a>
			<s:if test="%{isTenantAdmin}">
				<a href="#" onclick="createDefaultBvRel();"><span>创建默认上下级关系</span></a>
			</s:if>
			<%-- <a href="#" onclick="deleteByIds();"><span><s:text name='cmn_delete'/></span></a> --%>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<%-- <div class="untitled">
			<b><img alt="" src="${vix}/common/img/icon_11.png"></b>
			<div class="popup">
				<strong>
					<i class="close"><a href="#"></a></i>
					<i><a href="#"></a></i>
					<i><a href="#"></a></i>
					<b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="${vix}/common/img/icon_10.png" alt="" /><s:text name="cmn_index"/></a></li>
			<li><a href="#"><img alt="" src="${vix}/common/img/icon_10.png"><s:text name="cmn_category"/></a></li>
			<li class="fly">
				<a href="#"><img alt="" src="${vix}/common/img/icon_10.png"><s:text name="cmn_category"/></a>
				<ul>
					<li><a href="#"><img alt="" src="${vix}/common/img/icon_10.png"><s:text name="information"/></a></li>
					<li><a href="#"><img alt="" src="${vix}/common/img/icon_10.png"><s:text name="information"/></a></li>
					<li><a href="#"><img alt="" src="${vix}/common/img/icon_10.png"><s:text name="information"/></a></li>
					<li><a href="#"><img alt="" src="${vix}/common/img/icon_10.png"><s:text name="information"/></a></li>
				</ul>
			</li>
		</ul> --%>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="businessViewTitle" value="${businessViewTitle}"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" />
		</div>
	</div>
	<%-- <div id="number">
		<span class="num_left_bg"></span>
		<span class="num_right_bg"></span>
        <ul id="numBox" class="numBox">
        	<li><a href="#">a11</a></li>
        	<li><a href="#">v11</a></li>
        	<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
        	<s:iterator value="employeeList" var="c">
        		<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.name}</a></li>
        	</s:iterator>
        </ul>
    </div> --%>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div id="tree_root" class="ztree" style="padding: 0; height: 500px; overflow: scroll;"></div>
			</div>
			<script type="text/javascript">
				<!--
				var zTreeBv;			
				var settingBv = {
					async: {
						enable: true,
						url:"${vix}/common/org/organizationAction!findTreeToJson.action",
						autoParam:["id","treeType"]
						//url:"${vix}/common/select/commonSelectOrgAction!findOrgAndUnitTreeToJson.action",
						//autoParam:["treeId","treeType"]
					},
					callback: {
						onClick: bvOnClick
					}
				};
				function bvOnClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					//$("#selectIdType").val(treeNode.treeType);
					$("#selectIdTreeId").val(treeNode.tId);
					//pager("start","${vix}/common/org/organizationUnitAction!goSingleList.action?id="+treeNode.id+"&treeType="+treeNode.treeType,"category");
					pager("start","${vix}/common/org/businessViewAction!goSingleList.action?companyId="+treeNode.id+"&businessViewName="+name,"businessView");
				}
				zTreeBv = $.fn.zTree.init($("#tree_root"), settingBv);
				//-->
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${companyId}" /> <input type="hidden" id="selectIdType" name="selectIdType" value="${treeType}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />


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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="businessViewInfo"></b> <s:text name='cmn_to' /> <b class="businessViewTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="businessView" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="businessViewInfo"></b> <s:text name='cmn_to' /> <b class="businessViewTotalCount"></b>)
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