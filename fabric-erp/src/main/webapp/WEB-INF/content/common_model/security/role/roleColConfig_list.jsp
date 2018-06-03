<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#colConfigName').val();
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
});
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/common/select/commonSelectColConfigAction!goList.action',
		  data:{chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 760,
					height : 520,
					title:"选择列权限配置",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var selectIds = new Array();
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										//selectIds = v[0];
										selectIds.push(v[0]);
										//selectNames = v[1];
									}
								}
								//$("#bizOrgIds").val(selectIds);
								//$("#bizOrgNames").val(selectNames);
								if(selectIds.length>0 && confirm("确定要添加列权限配置项吗?")){
									$.post('${vix}/common/security/roleAction!saveForAddColConfig.action',
										"roleId=${roleId}&colConfigIds="+selectIds.join(),
										function(result){
											showMessage(result);
										  	setTimeout("", 1000);
											pager("start","${vix}/common/security/roleAction!goColConfigList.action?roleId=${roleId}&colConfigName="+name,'roleColConfig');
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
};

$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});

function deleteRoleColConfig(id){
	$.ajax({
		  url:'${vix}/common/security/roleAction!deleteForAddColConfig.action?roleId=${roleId}&colConfigId='+id,
		  cache: false,
		  success: function(result){
			  	showMessage(result);
			  	setTimeout("", 1000);
				pager("start","${vix}/common/security/roleAction!goColConfigList.action?roleId=${roleId}&colConfigName="+name,'roleColConfig');
		  }
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/common/security/roleAction!goColConfigList.action?roleId=${roleId}&colConfigName="+name,'roleColConfig');
}
 

//载入分页列表数据
pager("start","${vix}/common/security/roleAction!goColConfigList.action?roleId=${roleId}&colConfigName="+name,'roleColConfig');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#roleOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/common/security/roleAction!goColConfigList.action?roleId=${roleId}&orderField="+orderField+"&orderBy="+orderBy+"&colConfigName="+name,'roleColConfig');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/common/security/roleAction!goColConfigList.action?roleId=${roleId}&colConfigName="+name,'roleColConfig');
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
				<li><a href="#"><img src="${vix}/common/img/system/sec_role.png" alt="" />
					<s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_org" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sec" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sec_role" /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /></span></a> <a href="#" onclick="loadContent('${vix}/common/security/roleAction!goList.action');"><span>返回</span></a>
			<%-- <sec:canview functionCode="0010010012_wuliao">AAAAAA</sec:canview> --%>
			<%-- <a href="#" onclick="deleteByIds();"><span><s:text name='cmn_delete'/></span></a> --%>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="colConfigName" value="${colConfigName}"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" />
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="roleColConfigInfo"></b> <s:text name='cmn_to' /> <b class="roleColConfigTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="roleColConfig" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="roleColConfigInfo"></b> <s:text name='cmn_to' /> <b class="roleColConfigTotalCount"></b>)
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