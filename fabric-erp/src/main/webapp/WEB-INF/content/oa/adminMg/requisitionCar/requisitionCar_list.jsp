<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
var loadModel = "";
var loadPlateNumber ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadModel(){
	model = $('#requisition_model').val();
	model = Url.encode(model);
	model = Url.encode(model);
}
function loadPlateNumber(){
	plateNumber = $('#requisition_plateNumber').val();
	plateNumber = Url.encode(plateNumber);
	plateNumber = Url.encode(plateNumber);
}
function loadEngineNumber(){
	engineNumber = $('#requisition_engineNumber').val();
	engineNumber = Url.encode(engineNumber);
	engineNumber = Url.encode(engineNumber);
}
function loadPrice(){
	price = $('#requisition_price').val();
	price = Url.encode(price);
	price = Url.encode(price);
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
		$("#requisition_model").val("");
		$("#requisition_plateNumber").val("");
		$("#requisition_engineNumber").val("");
		$("#requisition_price").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	if(i == 0){
		pager("start","${vix}/oa/requisitionCarAction!goSearchList.action?i="+i+"&model="+name,'category');
	}
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/oa/requisitionCarAction!goSearch.action',
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
				pager("start", "${vix}/oa/requisitionCarAction!goSearchList.action?model=" + $('#model').val() + "&plateNumber=" + $('#plateNumber').val() + "&engineNumber=" + $('#engineNumber').val() + "&price=" + $('#price').val(), 'category');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

//状态
 function saleOrderStatus(status){
 	pager("start","${vix}/oa/requisitionCarAction!goSingleList.action?status="+status,'category');
 }

$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').animate({height: 'toggle', opacity: 'toggle'},500,function(){$('#number').css('overflow','visible');});
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

function saveOrUpdate(id,parentId){
	$.ajax({
		  url:'${vix}/oa/adminMg/requisitionCar/requisitionCarAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 420,
					title:"车辆信息",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$.post('${vix}/oa/adminMg/requisitionCar/requisitionCarAction!saveOrUpdate.action',
								 {'vehicleRequest.id':$("#id").val(),
								  'vehicleRequest.parentCategory.id':$("#parentId").val(),
								  'vehicleRequest.model':$("#model").val(),
								  'vehicleRequest.vehicleRequest.id':$("#vehicleRequestId").val(),
								  'vehicleRequest.status': $(":radio[name=status][checked]").val(),
								  'vehicleRequest.plateNumber':$("#plateNumber").val(),
								  'vehicleRequest.engineNumber':$("#engineNumber").val(),
								  'vehicleRequest.price':$("#price").val(),
								  'vehicleRequest.acquiredDate':$("#acquiredDate").val(),
								  'vehicleRequest.vehicleType.id':$("#vehicleTypeId").val(),
								  'vehicleRequest.vehicleColor.id':$("#vehicleColorId").val(),
								  'vehicleRequest.transmissionType.id':$("#transmissionTypeId").val(),
								  'vehicleRequest.engineType.id':$("#engineTypeId").val(),
								  'vehicleRequest.displacementType.id':$("#displacementTypeId").val(),
								  'vehicleRequest.createTime':$("#createTime").val(),
								  'vehicleRequest.memo':$("#memo").val()								 
								},
								function(result){
									asyncbox.success(result,"<s:text name='车辆信息'/>",function(action){
										var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
										if(null != treeNode){
											treeNode.isParent = true;
										}
										zTree.reAsyncChildNodes(treeNode, "refresh");
										pager("start","${vix}/oa/adminMg/requisitionCar/requisitionCarAction!goSingleList.action?name="+name+"&id="+$("#selectId").val(),'category');
									});
								}
							 );
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};

function deleteById(id){
	$.ajax({
		  url:'${vix}/oa/adminMg/requisitionCar/requisitionCarAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='车辆信息'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/oa/adminMg/requisitionCar/requisitionCarAction!goSingleList.action?name="+name,'category');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/oa/adminMg/requisitionCar/requisitionCarAction!goSingleList.action?name="+name,'category');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/oa/adminMg/requisitionCar/requisitionCarAction!goSingleList.action?name="+name,'category');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/oa/adminMg/requisitionCar/requisitionCarAction!goSingleList.action?name=",'category');
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
	pager("start","${vix}/oa/adminMg/requisitionCar/requisitionCarAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'category');
}
bindSearch();
bindSwitch();
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

//加载顶部工具栏
/* loadTopDynamicMenuContent('${vix}/oa/adminMg/requisitionCar/requisitionCarAction!goTopDynamicMenuContent.action'); */
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/mdm_vehicleRequest.png" alt="" /> <s:text name="协同办公" /> </a></li>
				<li><a href="#"><s:text name="行政办公" /> </a></li>
				<li><a href="#"><s:text name="车辆信息管理" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/requisitionCarAction!goList.action?pageNo=${pageNo}');"><s:text name="新增车辆信息" /> </a></li>
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
		<div class="untitled">
			<b><img src="${vix}/common/img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('0')"><img alt="" src="img/uncommitted.png"> <s:text name="未禁用" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/unaudited.png"> <s:text name="已禁用" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>型号: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="vehicleRequestList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());"><span style="display: none;">${c.chineseFirstLetter}</span>${c.model}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();"><s:text name="车辆信息" /></a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/oa/adminMg/requisitionCar/requisitionCarAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/oa/adminMg/requisitionCar/requisitionCarAction!goSingleList.action?parentId="+treeNode.id,"category");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><img src="${vix}/common/img/mail.png" alt="" />
					<s:text name="车辆信息" /></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_add' /></a></li>
								<li><a href="#"><s:text name='cmn_update' /></a></li>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="categoryPager('start','category');"></a></span> <span><a class="previous" onclick="categoryPager('previous','category');"></a></span> <em>(<b class="categoryInfo"></b> <s:text name='cmn_to' /> <b class="categoryTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next','category');"></a></span> <span><a class="end" onclick="categoryPager('end','category');"></a></span>
					</div>
				</div>
				<div id="category" class="table" style="overflow-x: auto; width: 100%; height: 492px;"></div>
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
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>