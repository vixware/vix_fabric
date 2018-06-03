<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $("#businessName").val();
	name= Url.encode(name);
	name = encodeURI(name);
}
/* $('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').stop().animate({height: 'toggle', opacity: 'toggle'},500,function(){
		$('#number').css('overflow','visible');
	});
	return false;
}); */
$(function(){
/* 	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
	$( "#left" ).resizable({
		maxHeight: 650,
		minHeight: 650,
		maxWidth: 400,
		minWidth: 120,
		handles: 'e'
	}); */
	
});
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
function saveOrUpdate(id,parentId){
	$.ajax({
		  url:'${vix}/common/org/businessOrgAction!goSaveOrUpdate.action?id='+id+'&parentIdStr='+parentId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 320,
					title:"业务组织",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($("#businessOrgForm").validationEngine('validate')){
								var queryString = $('#businessOrgForm').formSerialize(); 
								$.post('${vix}/common/org/businessOrgAction!saveOrUpdate.action',
									queryString,
									function(result){
										showMessage(result);
										setTimeout("", 1000);
									 	loadContent('${vix}/common/org/businessOrgAction!goList.action');
										/* var resObj = $.parseJSON(result);
										var isSuccess = resObj.success;
										var msg = resObj.message;
										var jsonObject = $.parseJSON(resObj.jsonObject);
										if(isSuccess){
											//var treeObj = $.fn.zTree.getZTreeObj("tree");
											var treeParObj = jsonObject.parentObj;
											var treeObj = jsonObject.obj;
											debugger;
											var treeObj = $.fn.zTree.getZTreeObj("tree_root");
											treeObj.addNodes(treeParObj,treeObj,true);
										}
										asyncbox.success(msg,"<s:text name='message'/>"); */
											
											/*  var treeObj = $.fn.zTree.getZTreeObj("tree_root");
											 debugger;
											 treeObj.refresh();
											 alert("vvv"); */
											//zTree.refresh();
											/* vixAjaxSend("${vix}/common/org/businessOrgAction!findOrgAndUnitTreeToJson.action","a=1",function(data){
												var treeData = $.parseJSON(data);
												zTree = $.fn.zTree.init($("#tree_root"), setting,treeData);
											});  */
											//pager("start","${vix}/common/org/businessOrgAction!goSingleList.action?businessName="+name+"&treeId="+$("#selectId").val(),'bizOrg');
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

function deleteById(id){
	$.ajax({
		  url:'${vix}/common/org/businessOrgAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			  loadContent('${vix}/common/org/businessOrgAction!goList.action');
			/*asyncbox.success(html,"<s:text name='message'/>",function(action){
				vixAjaxSend("${vix}/common/org/businessOrgAction!findOrgAndUnitTreeToJson.action","a=1",function(data){
					var treeData = $.parseJSON(data);
					zTree = $.fn.zTree.init($("#tree_root"), setting,treeData);
				});
				pager("start","${vix}/common/org/businessOrgAction!goSingleList.action?businessName="+name+"&id="+$("#selectId").val(),'bizOrg');
			}); */
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/common/org/businessOrgAction!goSingleList.action?businessName="+name,'bizOrg');
}

loadName();
//载入分页列表数据
pager("start","${vix}/common/org/businessOrgAction!goSingleList.action?businessName="+name,'bizOrg');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/common/org/businessOrgAction!goSingleList.action?businessName="+name,'bizOrg');
}
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#bizOrgOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/common/org/businessOrgAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&businessName="+name+"&parentId="+$("#selectId").val(),'bizOrg');
}

function bizOrgPager(tag,entity){
	loadName();
	if(entity == 'bizOrg'){
		pager(tag,"${vix}/common/org/businessOrgAction!goSingleList.action?businessName="+name+'&parentId='+$('#selectId').val(),entity);
	}
	/* if(entity == 'brand'){
		pager(tag,"${vix}/template/simpleGridAction!goSubSingleList.action?tag=list&bizOrgId="+$('#selectId').val(),entity);
	} */
}

/**
 * 设置业务组织  的 组织
 */
function toSaveUnit(boId){
	$.ajax({
		  url:'${vix}/common/org/businessOrgAction!goSaveOrUpdateDetail.action?id='+boId,
		  cache: false,
		  success: function(html){
			$("#mainContent").html(html);
		  }
	});
}





/* 
bindSearch();
bindSwitch();
function bizOrgTab(num,befor,id,e,entity){
	var el=e.target?e.target.parentNode:e.srcElement.parentNode;
	var pa=el.parentNode.getElementsByTagName("li");
	for(var i=0;i<pa.length;i++){
		pa[i].className="";
	}
	el.className="current";
	for(i=1;i<=num;i++){
		try{
			if(i==befor){
				document.getElementById(id+i).style.display="block";
			}else{
				document.getElementById(id+i).style.display="none";
			}
		}catch(e){ }
	}
	if(entity != undefined){
		bizOrgPager('start',entity);
	}
}
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
//面包屑
if($('.sub_menu').length)
{
	$("#breadCrumb").jBreadCrumb();
} */
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><img src="${vix}/common/img/hr_org.png" alt="" /><a href="#"><s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_org" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sys" /></a></li>
				<li><a href="#">业务组织</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name="cmn_add" /></span></a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<%-- <div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong>
					<i class="close"><a href="#"></a></i>
					<i><a href="#"></a></i>
					<i><a href="#"></a></i>
					<b>帮助</b>				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" /><s:text name="cmn_index"/></a></li>
			<li>
				<a href="#"><img src="img/icon_10.png" /><s:text name="cmn_category"/></a>
			</li>
			<li class="fly_th">
				<a href="#"><img src="img/icon_10.png" alt="" /><s:text name="information"/></a>
				<ul>
					<li>
                    	<dl>
							<dt><s:text name="cmn_category"/></dt>
                        	<dd>
                            	<a href="#"><img src="img/icon_10.png" alt="" /><s:text name="cmn_category"/></a>
                            	<a href="#"><img src="img/icon_10.png" alt="" /><s:text name="cmn_category"/></a>
                            	<a href="#"><img src="img/icon_10.png" alt="" /><s:text name="cmn_category"/></a>
                            	<a href="#"><img src="img/icon_10.png" alt="" /><s:text name="cmn_category"/></a>
                            	<a href="#"><img src="img/icon_10.png" alt="" /><s:text name="cmn_category"/></a>
                            </dd>
						</dl>
					</li>
					<li>
                    	<dl>
							<dt><s:text name="cmn_category"/></dt>
                        	<dd>
                            	<a href="#"><img src="img/icon_10.png" alt="" /><s:text name="cmn_category"/></a>
                            	<a href="#"><img src="img/icon_10.png" alt="" /><s:text name="cmn_category"/></a>
                            	<a href="#"><img src="img/icon_10.png" alt="" /><s:text name="cmn_category"/></a>
                            	<a href="#"><img src="img/icon_10.png" alt="" /><s:text name="cmn_category"/></a>
                            	<a href="#"><img src="img/icon_10.png" alt="" /><s:text name="cmn_category"/></a>
                            </dd>
						</dl>
					</li>
					<li>
                    	<dl>
							<dt><s:text name="cmn_category"/></dt>
                        	<dd>
                            	<a href="#"><img src="img/icon_10.png" alt="" /><s:text name="cmn_category"/></a>
                            	<a href="#"><img src="img/icon_10.png" alt="" /><s:text name="cmn_category"/></a>
                            	<a href="#"><img src="img/icon_10.png" alt="" /><s:text name="cmn_category"/></a>
                            	<a href="#"><img src="img/icon_10.png" alt="" /><s:text name="cmn_category"/></a>
                            	<a href="#"><img src="img/icon_10.png" alt="" /><s:text name="cmn_category"/></a>
                            </dd>
						</dl>
					</li>
				</ul>
			</li>
		</ul> --%>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="businessName" value=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent();" /> </label>
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
	<%--  <div id="number">
		<span class="num_left_bg"></span>
		<span class="num_right_bg"></span>
        <ul id="numBox" class="numBox">
       		<li><a href="#">111</a></li>
        	<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
        	<s:iterator value="bizOrgList" var="c">
        		<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.name}</a></li>
        	</s:iterator>
        </ul>
    </div> --%>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<%-- <div style="padding-left:4px;"><img src="${vix}/common/img/file.png" style="padding-right:5px;"/><a href="#" onclick="loadRoot();"></a></div> --%>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				<!--
			/* 	var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/common/org/businessOrgAction!findOrgAndUnitTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					},
					data: {
						simpleData: {
							enable: true,
							idKey: "id",
							pIdKey: "pId",
							rootPId: 0
						}
					}
				};
				
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/common/org/businessOrgAction!goSingleList.action?id="+treeNode.id,"bizOrg");
				}
				
				
				$(document).ready(function(){
					vixAjaxSendHtml("${vix}/common/org/businessOrgAction!findTreeToJson.action","a=1",function(data){
						var treeData = $.parseJSON(data);
						zTree = $.fn.zTree.init($("#tree_root"), setting,treeData);
					});
					
				});  */
				
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/common/org/businessOrgAction!findOrgAndUnitTreeToJson.action",
						autoParam:["treeId","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.treeId);
					$("#selectIdTreeId").val(treeNode.tId);
					//alert(treeNode.tId+ "--" + treeNode.treeType );
					pager("start","${vix}/common/org/businessOrgAction!goSingleList.action?treeId="+treeNode.treeId+"&treeType="+treeNode.treeType,"bizOrg");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="bizOrgTab(3,1,'a',event,'bizOrg')"><img src="img/mail.png" alt="" />业务组织</a></li>
					<%-- <li><a onclick="bizOrgTab(3,2,'a',event,'brand')"><img src="img/mail.png" alt="" /><s:text name="brand"/></a></li> --%>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#">Actions</a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectBizOrgCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="bizOrgPager('start','bizOrg');"></a></span> <span><a class="previous" onclick="bizOrgPager('previous','bizOrg');"></a></span> <em>(<b class="bizOrgInfo"></b> <s:text name='cmn_to' /> <b class="bizOrgTotalCount"></b>)
						</em> <span><a class="next" onclick="bizOrgPager('next','bizOrg');"></a></span> <span><a class="end" onclick="bizOrgPager('end','bizOrg');"></a></span>
					</div>
				</div>
				<div id="bizOrg" class="table"></div>
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
					<strong><s:text name="cmn_selected" />:<span id="selectBizOrgCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="bizOrgPager('start','bizOrg');"></a></span> <span><a class="previous" onclick="bizOrgPager('previous','bizOrg');"></a></span> <em>(<b class="bizOrgInfo"></b> <s:text name='cmn_to' /> <b class="bizOrgTotalCount"></b>)
						</em> <span><a class="next" onclick="bizOrgPager('next','bizOrg');"></a></span> <span><a class="end" onclick="bizOrgPager('end','bizOrg');"></a></span>
					</div>
				</div>
			</div>

			<%--<div class="right_content" id="a2" style="display:none;">
				<div class="pagelist drop">
					<ul>
						<li class="tp">
							<a href="#"><s:text name='cmn_operate'/></a>
							<ul>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectBrandCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="bizOrgPager('start','brand');"></a></span>
						<span><a class="previous" onclick="bizOrgPager('previous','brand');"></a></span>
						<em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to'/> <b class="brandTotalCount">${pager.totalCount}</b>)</em>
						<span><a class="next" onclick="bizOrgPager('next','brand');"></a></span>
						<span><a class="end" onclick="bizOrgPager('end','brand');"></a></span>
					</div>
				</div>
				<div id="brand" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed">
							<a href="#"><s:text name="cmn_choose"/></a>
							<ul>
								<li><a href="#"><s:text name="cmn_delete"/></a></li>
								<li><a href="#"><s:text name="cmn_email"/></a></li>
								<li><a href="#"><s:text name="merger"/></a></li>
								<li><a href="#"><s:text name="export"/></a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectBrandCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="bizOrgPager('start','brand');"></a></span>
						<span><a class="previous" onclick="bizOrgPager('previous','brand');"></a></span>
						<em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to"/> <b class="brandTotalCount">${pager.totalCount}</b>)</em>
						<span><a class="next" onclick="bizOrgPager('next','brand');"></a></span>
						<span><a class="end" onclick="bizOrgPager('end','brand');"></a></span>
					</div>
				</div>
			</div>--%>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>