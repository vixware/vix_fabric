<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#indexPdcName').val();
	name= Url.encode(name);
	name = encodeURI(name);
}

function deleteRolePdc(pdcId){
	asyncbox.confirm('确定要删除角色的配置么?','<s:text name='cmn_message'/>',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/common/security/roleAction!deleteForAddIndexPdc.action?roleId=${roleId}&pdcId='+pdcId,
				  cache: false,
				  success: function(result){
					  showMessage(result);
					  setTimeout("", 1000);
					  pager("start","${vix}/common/security/roleAction!goIndexPdcList.action?roleId=${roleId}&indexPdcName="+name,'indexPdc');
				  }
			});
		}
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/common/security/roleAction!goIndexPdcList.action?roleId=${roleId}&indexPdcName="+name,'indexPdc');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/common/security/roleAction!goIndexPdcList.action?roleId=${roleId}&indexPdcName="+name,'indexPdc');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#brandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/common/security/roleAction!goIndexPdcList.action?roleId=${roleId}&indexPdcName="+name+"&orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'indexPdc');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/common/security/roleAction!goIndexPdcList.action?roleId=${roleId}&indexPdcName="+name,'indexPdc');
}
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/common/select/commonSelectIndexPdcAction!goList.action',
		  data:{roleId:'${roleId}',chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 760,
					height : 520,
					title:"选择页面配置",
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
								if(selectIds.length>0 && confirm("确定要添加选择的个性化页面配置项吗?")){
									$.post('${vix}/common/security/roleAction!saveForAddIndexPdc.action',
										"roleId=${roleId}&pdcIds="+selectIds.join(),
										function(result){
											showMessage(result);
										  	setTimeout("", 1000);
											pager("start","${vix}/common/security/roleAction!goIndexPdcList.action?roleId=${roleId}&indexPdcName="+name,'indexPdc');
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

</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sys_settings.png" alt="" />
					<s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_org" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sec" /></a></li>
				<li><a href="#">角色管理</a></li>
				<li><a href="#">角色个性化配置</a></li>
			</ul>
		</div>
	</h2>

	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);">增加</span>
			</a>
		</p>
		<p>
			<a href="#" onclick="loadContent('${vix}/common/security/roleAction!goList.action');"><span>返回</span></a>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="indexPdcName"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();">
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
						<li class="tp"><a href="#"><s:text name='cmn_choose' /> </a> <%-- <ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul> --%></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="indexPdcInfo"></b> <s:text name='cmn_to' /> <b class="indexPdcTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="indexPdc" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="indexPdcInfo"></b> <s:text name='cmn_to' /> <b class="indexPdcTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
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