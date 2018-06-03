<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
loadMenuContent('${vix}/hr/hrMainAction!goMenuContent.action');

/* 搜索功能 */
var name = "";
var theme = "";
var employeeName ="";
var uploadPersonName ="";
var probationPost ="";
var afterThePromotionDepartment ="";
var afterThePromotionPost ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadTheme(){
	theme = $('#hr_theme').val();
	theme = Url.encode(theme);
	theme = Url.encode(theme);
}
function loadEmployeeName(){
	employeeName = $('#hr_employeeName').val();
	employeeName = Url.encode(employeeName);
	employeeName = Url.encode(employeeName);
}
function loadUploadPersonName(){
	uploadPersonName = $('#hr_uploadPersonName').val();
	uploadPersonName = Url.encode(uploadPersonName);
	uploadPersonName = Url.encode(uploadPersonName);
}
function loadProbationPost(){
	probationPost = $('#hr_probationPost').val();
	probationPost = Url.encode(probationPost);
	probationPost = Url.encode(probationPost);
}
function loadAfterThePromotionDepartment(){
	afterThePromotionDepartment = $('#hr_afterThePromotionDepartment').val();
	afterThePromotionDepartment = Url.encode(afterThePromotionDepartment);
	afterThePromotionDepartment = Url.encode(afterThePromotionDepartment);
}
function loadAfterThePromotionPost(){
	afterThePromotionPost = $('#hr_afterThePromotionPost').val();
	afterThePromotionPost = Url.encode(afterThePromotionPost);
	afterThePromotionPost = Url.encode(afterThePromotionPost);
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
		$("#hr_theme").val("");
		$("#hr_employeeName").val("");
		$("#hr_uploadPersonName").val("");
		$("#hr_probationPost").val("");
		$("#hr_afterThePromotionDepartment").val("");
		$("#hr_afterThePromotionPost").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	pager("start","${vix}/hr/probationAction!goSearchList.action?i="+i+"&theme="+name,'category');
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/hr/probationAction!goSearch.action',
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
				pager("start", "${vix}/hr/probationAction!goSearchList.action?theme=" + $('#theme').val() + "&employeeName=" + $('#employeeName').val() + "&uploadPersonName=" + $('#uploadPersonName').val() + "&probationPost=" + $('#probationPost').val() + "&afterThePromotionDepartment=" + $('#afterThePromotionDepartment').val() + "&afterThePromotionPost=" + $('#afterThePromotionPost').val(), 'category');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

//状态
function saleOrderStatus(transactionState){
	pager("start","${vix}/hr/probationAction!goSingleList.action?transactionState="+transactionState,'category');
}
//最近使用
function leastRecentlyUsed(licenseDate){
	pager("start","${vix}/hr/probationAction!goSingleList.action?licenseDate="+licenseDate,'category');
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

/* 修改 HrBecomeRegular*/
function saveOrUpdate(id,pageNo,biztype){
	  if(biztype==1){
		  saveOrUpdateHrBecomeRegular(id,pageNo);
      }
	  else if(biztype==0){
    	  saveOrUpdateXresign(id,pageNo);
      }
	  else if (biztype==2){
    	   saveOrUpdateXtaffTurnover(id,pageNo);
      }
	  else if (biztype==3){
    	  saveOrUpdateStaffSecondments(id,pageNo);
      }
	  else if (biztype==4){
		  saveOrUpdateAskForLeave(id,pageNo);
      }
	  else if (biztype==5){
		  saveOrUpdateDismiss(id,pageNo);
      }
	  else if (biztype==6){
		  saveOrUpdateRetirement(id,pageNo);
      }
	  else if (biztype==7){
		  saveOrUpdateBridget(id,pageNo);
      }
 }
 /* 新增 HrBecomeRegular*/
function saveOrUpdateHrBecomeRegular(id,parentId){
	$.ajax({
		  url:'${vix}/hr/probationAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};
 /* 异动*/
function saveOrUpdateXtaffTurnover(id,parentId){
	$.ajax({
		  url:'${vix}/hr/probationAction!goSaveOrUpdateXtaffTurnover.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};
 /* 离职*/
function saveOrUpdateXresign(id,parentId){
	$.ajax({
		  url:'${vix}/hr/probationAction!goSaveOrUpdateXresign.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};
 /* 借调*/
function saveOrUpdateStaffSecondments(id,parentId){
	$.ajax({
		  url:'${vix}/hr/probationAction!goSaveOrUpdateStaffSecondments.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};
 /* 请假*/
function saveOrUpdateAskForLeave(id,parentId){
	$.ajax({
		  url:'${vix}/hr/probationAction!goSaveOrUpdateAskForLeave.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};
 /* 辞退*/
function saveOrUpdateDismiss(id,parentId){
	$.ajax({
		  url:'${vix}/hr/probationAction!goSaveOrUpdateDismiss.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};
 /* 离退休*/
function saveOrUpdateRetirement(id,parentId){
	$.ajax({
		  url:'${vix}/hr/probationAction!goSaveOrUpdateRetirement.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};
 /* 返聘*/
function saveOrUpdateBridget(id,parentId){
	$.ajax({
		  url:'${vix}/hr/probationAction!goSaveOrUpdateBridget.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
};

function deleteById(id){
	$.ajax({
		  url:'${vix}/hr/probationAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/hr/probationAction!goSingleList.action?name="+name,'category');
			});
		  }
	});
}

loadName();
//载入分页列表数据
pager("start","${vix}/hr/probationAction!goSingleList.action?name="+name,'category');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/hr/probationAction!goSingleList.action?name=",'category');
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
	pager("start","${vix}/hr/probationAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'category');
}

function categoryPager(tag,entity){
	loadName();
	if(entity == 'category'){
		pager(tag,"${vix}/hr/probationAction!goSingleList.action?name="+name+'&parentId='+$('#selectId').val(),entity);
	}
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

/* 加载顶部工具栏 */
loadTopDynamicMenuContent('${vix}/hr/probationAction!goTopDynamicMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/hr_empma.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#"><s:text name="hr_staff_manage" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/probationAction!goList.action');">人事事务管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/probationAction!goList.action');">列表</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
		<ul>
			<li><a href="#"><span>新增来源</span></a>
				<ul>
					<li><a href="#" onclick="saveOrUpdateHrBecomeRegular(0);">转正</a></li>
					<li><a href="#" onclick="saveOrUpdateXtaffTurnover(0);">异动</a></li>
					<li><a href="#" onclick="saveOrUpdateXresign(0);">离职</a></li>
					<li><a href="#" onclick="saveOrUpdateStaffSecondments(0);">借调</a></li>
					<li><a href="#" onclick="saveOrUpdateAskForLeave(0);">请假</a></li>
					<li><a href="#" onclick="saveOrUpdateDismiss(0);">辞退</a></li>
					<li><a href="#" onclick="saveOrUpdateRetirement(0);">离退休</a></li>
					<li><a href="#" onclick="saveOrUpdateBridget(0);">返聘</a></li>
				</ul></li>
		</ul>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="事物状态" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/uncommitted.png"> <s:text name="通过" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('2')"><img alt="" src="img/unaudited.png"> <s:text name="未通过" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('3')"><img alt="" src="img/unaudited.png"> <s:text name="待议" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="最近事物" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>事物主题: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="hrBecomeRegularList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());"><span style="display: none;">${c.chineseFirstLetter}</span>${c.theme}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable switch" style="height: 500px; width: 7px;">
			<div id="switch_box" class="switch_btn off"></div>
			<div class="left_content current" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();"><s:text name='cmn_rootDirectory' /></a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/hr/probationAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/hr/probationAction!goSingleList.action?parentId="+treeNode.id,"category");
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
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'category')"><img src="img/common_listx16.png" alt="" />列表</a></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
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
								<li><a href="#"><s:text name='cmn_mail' /></a></li>
								<li><a href="#"><s:text name="cmn_merger" /></a></li>
								<li><a href="#"><s:text name="cmn_export" /></a></li>
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