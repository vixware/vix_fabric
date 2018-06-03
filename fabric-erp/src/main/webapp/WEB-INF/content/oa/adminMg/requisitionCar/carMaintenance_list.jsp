<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
var maintenanceReason = "";
var uploadPersonName ="";
var maintenanceCost ="";
var maintenance ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadmaintenanceReason(){
	maintenanceReason = $('#car_maintenanceReason').val();
	maintenanceReason = Url.encode(maintenanceReason);
	maintenanceReason = Url.encode(maintenanceReason);
}
function loadUploadPersonName(){
	uploadPersonName = $('#car_uploadPersonName').val();
	uploadPersonName = Url.encode(uploadPersonName);
	uploadPersonName = Url.encode(uploadPersonName);
}
function loadMaintenanceCost(){
	maintenanceCost = $('#car_maintenanceCost').val();
	maintenanceCost = Url.encode(maintenanceCost);
	maintenanceCost = Url.encode(maintenanceCost);
}
function loadMaintenance(){
	maintenance = $('#car_maintenance').val();
	maintenance = Url.encode(maintenance);
	maintenance = Url.encode(maintenance);
}

/*判断搜索内容是否为空*/
function validateSearch(temp){
	if(null == temp || "" == temp){
		return false;
	}
	return true;
}

/*重置搜索*/
function resetForContent(i){
	if(i == 0){
		$("#nameS").val("");
	}
	else{
		$("#car_maintenanceReason").val("");
		$("#car_uploadPersonName").val("");
		$("#car_maintenanceCost").val("");
		$("#car_maintenance").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	if(i == 0){
		pager("start","${vix}/oa/carMaintenanceAction!goSearchList.action?i="+i+"&maintenanceReason="+name,'car');
	}
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/oa/carMaintenanceAction!goSearch.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 300,
		title : "查询条件",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				pager("start", "${vix}/oa/carMaintenanceAction!goSearchList.action?maintenanceReason=" + $('#maintenanceReason').val() + "&uploadPersonName=" + $('#uploadPersonName').val() + "&maintenanceCost=" + $('#maintenanceCost').val() + "&maintenance=" + $('#maintenance').val(), 'car');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
}; 

//最近使用
function leastRecentlyUsed(maintenanceDate){
 	pager("start","${vix}/oa/carMaintenanceAction!goCarMaintenance.action?maintenanceDate="+maintenanceDate,'car');
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
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});

function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/oa/carMaintenanceAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 730,
					height : 280,
					title:"新建车辆维护信息",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#carForm').validationEngine('validate')){
								$.post('${vix}/oa/carMaintenanceAction!saveOrUpdate.action',
									{'carMaintenance.id':$("#id").val(),	
									  'carMaintenance.vehicleRequest.id':$("#vehicleRequestId").val(),
									  'carMaintenance.maintenanceDate':$("#maintenanceDate").val(),	
									  'carMaintenance.maintenanceType.id':$("#maintenanceTypeId").val(),
									  'carMaintenance.carSituation' : $("#carSituation").val(),
									  'carMaintenance.maintenanceReason':$("#maintenanceReason").val()	
									},
									function(result){
										asyncbox.success(result,"<s:text name='车辆维护'/>",function(action){
											pager("current","${vix}/oa/carMaintenanceAction!goCarMaintenance.action?name="+name,'car');
										});
									}
								 ); 
			  				}else{
			  					return false;
			  				}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};

function saveOrUpdateReturn(id){
	$.ajax({
		  url:'${vix}/oa/carMaintenanceAction!goSaveOrUpdateReturn.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 730,
					height : 450,
					title:"车辆维修情况",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#carForm').validationEngine('validate')){
								$.post('${vix}/oa/carMaintenanceAction!saveOrUpdateReturn.action',
									{'carMaintenance.id':$("#id").val(),	
									  'carMaintenance.vehicleRequest.id':$("#vehicleRequestId").val(),
									  'carMaintenance.maintenanceDate':$("#maintenanceDate").val(),	
									  'carMaintenance.maintenanceType.id':$("#maintenanceTypeId").val(),
									  'carMaintenance.maintenanceCost':$("#maintenanceCost").val(),		
									  'carMaintenance.maintenanceReason':$("#maintenanceReason").val(),		
									  'carMaintenance.maintenance':$("#maintenance").val(),		
									  'carMaintenance.remark':$("#remark").val()		
									},
									function(result){
										asyncbox.success(result,"<s:text name='车辆维修情况'/>",function(action){
											pager("current","${vix}/oa/carMaintenanceAction!goCarMaintenance.action?name="+name,'car');
										});
									}
								 ); 
			  				}else{
			  					return false;
			  				}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};

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
		  url:'${vix}/oa/carMaintenanceAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/oa/carMaintenanceAction!goCarMaintenance.action?name="+name,'car');
			});
		  }
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/oa/carMaintenanceAction!goCarMaintenance.action?name="+name,'car');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/oa/carMaintenanceAction!goCarMaintenance.action?name="+name,'car');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#carOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/carMaintenanceAction!goCarMaintenance.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'car');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/oa/carMaintenanceAction!goCarMaintenance.action?name="+name,'car');
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
loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/mdm_vehicleRequest.png" alt="" /> 协同办公</a></li>
				<li><a href="#">行政办公</a></li>
				<li><a href="#">车辆申请安排</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/carMaintenanceAction!goList.action?pageNo=${pageNo}');">车辆维护管理</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="javascript:void(0);" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name='cmn_add' /></span></a>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="最近维修" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>维护原因: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="carMaintenanceList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());"><span style="display: none;">${c.chineseFirstLetter}</span>${c.maintenanceReason}</a></li>
			</s:iterator>
		</ul>
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
								<li><a href="#"><s:text name='cmn_mail' /></a></li>
								<li><a href="#"><s:text name="cmn_merger" /></a></li>
								<li><a href="#"><s:text name="cmn_export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="carInfo"></b> <s:text name='cmn_to' /> <b class="carTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="car" class="table" style="overflow-x: auto; width: 100%;"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_mail' /></a></li>
								<li><a href="#"><s:text name="cmn_merger" /></a></li>
								<li><a href="#"><s:text name="cmn_export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="carInfo"></b> <s:text name='cmn_to' /> <b class="carTotalCount"></b>)
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