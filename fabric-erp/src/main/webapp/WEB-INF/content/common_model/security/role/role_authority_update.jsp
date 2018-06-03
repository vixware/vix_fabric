<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').stop().animate({height: 'toggle', opacity: 'toggle'},500,function(){
		$('#number').css('overflow','visible');
	});
	return false;
});

var zTree;

function initAuthorityTree(){
	var bizTypeTmp = $("#authorityBizType").val();//var bizType = $("#authorityBizType").val();
	var setting = {
		check: {
			enable: true,
			chkStyle: "checkbox",
			radioType: "level"
		},	
		async: {
			enable: true,
			url:"${vix}/common/security/role/roleAction!findAuthorityTree.action?roleId=${id}&bizType="+bizTypeTmp,
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
		pager("start","${vix}/common/security/role/roleAction!goAuthorityTreeSingleList.action?roleId=${id}&treeId="+treeNode.treeId+"&bizType="+treeNode.bizType,"category");
	}
	zTree = $.fn.zTree.init($("#tree_root"), setting);
	
	$("#category").empty();
}


$(function(){
	/* $( "#left" ).resizable({
		maxHeight: 650,
		minHeight: 650,
		maxWidth: 400,
		minWidth: 120,
		handles: 'e'
	}); */
	initAuthorityTree();
});
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});

//载入分页列表数据
//pager("start","${vix}/common/security/authority/authorityAction!goSingleList.action?authorityName="+name,'category');

function toLoadSubAuthority(){
	$.ajax({
		  url:"${vix}/common/security/roleAction!goAuthorityTreeSingleList.action?roleId=${roleId}&treeId=${treeId}&bizType=${bizType}",
		  cache: false,
		  success: function(html){
			  $("#category").html(html);
		  }
	});
}


function saveForAuthority(){
	
	var zTreeTmp = $.fn.zTree.getZTreeObj("tree_root");
	var nodes = zTreeTmp.getCheckedNodes(true);
	var objIds ="";
	for (var i=0, l=nodes.length; i<l; i++) {
		objIds += nodes[i].id + ",";
	}
	if (objIds.length > 0 ) objIds = objIds.substring(0, objIds.length-1);
	
	var bizTypeTmp = $("#authorityBizType").val();
	var checkValArray = "bizType="+bizTypeTmp+"&topAuthorityIds="+objIds;
	 $('input[name="checkAuthority"]:checked').each(function(){
		 checkValArray+="&checkAuthority="+$(this).val();
	 });
	 
	 
	  $.post('${vix}/common/security/roleAction!saveForAuthority.action?roleId=${id}&treeId='+$("#selectId").val(),
		checkValArray,
		function(result){
		  showMessage(result);
		  	setTimeout("", 1000);
				//loadContent('${vix}/common/security/dataColPolicyAction!goList.action?name=${name}&dataConfigId=${dataConfigId}');
				//accept();
		}
	 );
}


function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	//pager("start","${vix}/common/security/authority/authorityAction!goSingleList.action?authorityName=",'category');
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
				<li><a href="#"><img src="${vix}/common/img/system/sec_role.png" alt="" />
					<s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_org" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sec" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sec_authority" /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveForAuthority();"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/common/security/roleAction!goList.action');"><span>返回</span></a>
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
			<label>权限类别A：<s:select list="bizTypeMap" id="authorityBizType" listKey="key" listValue="value" onchange="initAuthorityTree();" theme="simple"></s:select>
			</label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="initAuthorityTree();" />
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<li><a href="#">111</a></li>
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="categoryList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.name}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<%-- <div style="padding-left:4px;"><img src="${vix}/common/img/file.png" style="padding-right:5px;"/><a href="#" onclick="loadRoot();"><s:text name='rootDirectory'/></a></div> --%>
				<div id="tree_root" class="ztree" style="padding: 0; height: 500px; overflow: scroll;"></div>
			</div>
			<script type="text/javascript">
				
				
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" /> <input type="hidden" id="selectBizType" name="selectBizType" />


		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'category')"><img src="img/mail.png" alt="" />功能点授权</a></li>
					<%-- <li><a onclick="categoryTab(3,2,'a',event,'brand')"><img src="img/mail.png" alt="" /><s:text name="brand"/></a></li> --%>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<%-- 	<div class="pagelist drop">
					<ul>
						<li class="tp">
							<a href="#"><s:text name='cmn_operate'/></a>
							<ul>
								<li><a href="#">Actions</a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectCategoryCount1">0</span></strong>
				</div> --%>

				<div id="category" class="table"></div>

			</div>
			<!-- right -->
		</div>
		<div class="c_foot">
			<span class="left_bg"></span> <span class="right_bg"></span>
		</div>
		<!-- c_foot -->
	</div>