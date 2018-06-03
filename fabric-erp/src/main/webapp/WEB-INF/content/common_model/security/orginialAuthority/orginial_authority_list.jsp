<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $("#authorityName").val();
	name= Url.encode(name);
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
	$( "#left" ).resizable({
		maxHeight: 650,
		minHeight: 650,
		maxWidth: 400,
		minWidth: 120,
		handles: 'e'
	});
	
	$("#authorityForm").validationEngine();
});
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
function saveOrUpdate(id,parentId){
	if(parentId!=null && parentId!=""){
		$.ajax({
			  url:'${vix}/common/security/authority/orginialAuthorityAction!goSaveOrUpdate.action?id='+id+"&parentTreeId="+parentId,
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 720,
						height : 480,
						title:"权限",
						html:html,
						callback : function(action){
							if(action == 'ok'){
								if($("#authorityForm").validationEngine('validate')){
									if(confirm("确定要保存权限吗?")){
										var queryString = $('#authorityForm').formSerialize(); 
										$.post('${vix}/common/security/authority/orginialAuthorityAction!saveOrUpdate.action',
											queryString,
											function(result){
												showMessage(result);
												setTimeout("", 1000);
												//alert($("#selectIdTreeId").val());
												var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
												if(null != treeNode){
													treeNode.isParent = true;
												}
												zTree.reAsyncChildNodes(treeNode, "refresh");
												pager("start","${vix}/common/security/authority/orginialAuthorityAction!goSingleList.action?authorityName="+name+"&treeId="+$("#selectId").val()+"&bizType="+$("#selectBizType").val(),'category');
											}
										 );
									}
								}else{
									return false;
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}else{
		showMessage("请选择权限节点！");
		setTimeout("", 1000);
	}
	
};

function deleteById(id){
	$.ajax({
		  url:'${vix}/common/security/authority/orginialAuthorityAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(result){
			  showMessage(result);
			  setTimeout("", 1000);
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/common/security/authority/orginialAuthorityAction!goSingleList.action?authorityName="+name+"&treeId="+$("#selectId").val()+"&bizType="+$("#selectBizType").val(),'category');
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/common/security/authority/orginialAuthorityAction!goSingleList.action?authorityName="+name,'category');
}

loadName();
//载入分页列表数据
pager("start","${vix}/common/security/authority/orginialAuthorityAction!goSingleList.action?authorityName="+name,'category');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/common/security/authority/orginialAuthorityAction!goSingleList.action?authorityName=",'category');
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
	pager("start","${vix}/common/security/authority/orginialAuthorityAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&authorityName="+name+"&treeId="+$("#selectId").val()+"&bizType="+$("#selectBizType").val(),'category');
}

function categoryPager(tag,entity){
	loadName();
	if(entity == 'category'){
		pager(tag,"${vix}/common/security/authority/orginialAuthorityAction!goSingleList.action?authorityName="+name+"&treeId="+$("#selectId").val()+"&bizType="+$("#selectBizType").val(),entity);
	}
	/* if(entity == 'brand'){
		pager(tag,"${vix}/template/simpleGridAction!goSubSingleList.action?tag=list&categoryId="+$('#selectId').val(),entity);
	} */
}


function importXlsFile(){
	$.ajaxFileUpload({
		url : '${vix}/common/security/orginialAuthorityAction!importFile.action',//用于文件上传的服务器端请求地址
		secureuri : true,//是否安全提交,设置为true;设置为false，则出现乱码
		fileElementId : 'fileToUpload',//文件上传空间的id属性  <input type="file" id="file" name="file" />
		dataType : 'text',//返回值类型 ,可以使xml、text、json、html
		success : function(data, status){ //服务器成功响应处理函数
			var data = $.parseJSON(data);
			if(data.success=='1'){
				showMessage(data.msg);
				setTimeout("", 1000);
				//pager("start","${vix}/common/security/authority/orginialAuthorityAction!goSingleList.action?authorityName="+name+"&id="+$("#selectId").val(),'category');
				//debugger;
				//var treeObj = $.fn.zTree.getZTreeObj("tree_root");
				//treeObj.refresh();
				//debugger;
				//zTree.refresh();
				loadContent('${vix}/common/security/orginialAuthorityAction!goList.action');
			}else{
				if(typeof(data.error) != 'undefined'){
					if(data.error != ''){
						showErrorMessage(data.error);
						setTimeout("", 1000);
					}else{
						showMessage(data.msg);
						setTimeout("", 1000);
					}
				}
			}
         },
         error : function(data, status, e){//服务器响应失败处理函数
        	 showErrorMessage("上传错误!");
			 setTimeout("", 1000);
         }
 	});
}

bindSearch();
bindSwitch();
function categoryTab(num,befor,id,e,entity){
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
		categoryPager('start',entity);
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
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/system/sec_authority.png" alt="" />
					<s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_org" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sec" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sec_authority" /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name="cmn_add" /></span></a> <a href="#" onclick="$('#fileToUpload').click()"><span>导入</span></a>
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
			<label><s:text name="cmn_name" /><input type="text" class="int" id="authorityName" value=""></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent();" />
			</label>
		</div>
	</div>
	<%--  <div id="number">
		<span class="num_left_bg"></span>
		<span class="num_right_bg"></span>
        <ul id="numBox" class="numBox">
       		<li><a href="#">111</a></li>
        	<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
        	<s:iterator value="categoryList" var="c">
        		<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.name}</a></li>
        	</s:iterator>
        </ul>
    </div> --%>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<%-- <div style="padding-left:4px;"><img src="${vix}/common/img/file.png" style="padding-right:5px;"/><a href="#" onclick="loadRoot();"><s:text name='rootDirectory'/></a></div> --%>
				<div id="tree_root" class="ztree" style="padding: 0; height: 500px; overflow: scroll;"></div>
			</div>
			<script type="text/javascript">
				<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/common/security/authority/orginialAuthorityAction!findTreeToJson.action",
						autoParam:["treeId","bizType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					//alert("treeNode.tId:"+treeNode.tId);
					$("#selectId").val(treeNode.treeId);
					$("#selectIdTreeId").val(treeNode.tId);
					$("#selectBizType").val(treeNode.bizType);//&bizType=
					pager("start","${vix}/common/security/authority/orginialAuthorityAction!goSingleList.action?treeId="+treeNode.treeId+"&bizType="+treeNode.bizType,"category");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" /> <input type="hidden" id="selectBizType" name="selectBizType" /> <input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />

		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'category')"><img src="img/mail.png" alt="" />权限</a></li>
					<%-- <li><a onclick="categoryTab(3,2,'a',event,'brand')"><img src="img/mail.png" alt="" /><s:text name="brand"/></a></li> --%>
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
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','category');"></a></span> <span><a class="previous" onclick="categoryPager('previous','category');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a></span> <span><a class="end" onclick="categoryPager('end','category');"></a></span>
					</div>
				</div>
				<div id="category" class="table"></div>
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
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','category');"></a></span> <span><a class="previous" onclick="categoryPager('previous','category');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a></span> <span><a class="end" onclick="categoryPager('end','category');"></a></span>
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
						<span><a class="start" onclick="categoryPager('start','brand');"></a></span>
						<span><a class="previous" onclick="categoryPager('previous','brand');"></a></span>
						<em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to'/> <b class="brandTotalCount">${pager.totalCount}</b>)</em>
						<span><a class="next" onclick="categoryPager('next','brand');"></a></span>
						<span><a class="end" onclick="categoryPager('end','brand');"></a></span>
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
						<span><a class="start" onclick="categoryPager('start','brand');"></a></span>
						<span><a class="previous" onclick="categoryPager('previous','brand');"></a></span>
						<em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to"/> <b class="brandTotalCount">${pager.totalCount}</b>)</em>
						<span><a class="next" onclick="categoryPager('next','brand');"></a></span>
						<span><a class="end" onclick="categoryPager('end','brand');"></a></span>
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