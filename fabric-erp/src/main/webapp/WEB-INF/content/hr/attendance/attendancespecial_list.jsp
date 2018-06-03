<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var code = "";
var name = "";
var contacts ="";
function loadCode(){ 
	code = $('#srm_code').val();
	code=Url.encode(code);
	code=Url.encode(code);
} 
function loadName(){
	name = $('#srm_name').val();
	name=Url.encode(name);
	name=Url.encode(name);
} 
function loadContacts(){
	contacts = $('#srm_contacts').val();
	contacts=Url.encode(contacts);
	contacts=Url.encode(contacts);
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
function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/hr/attendanceTypeAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 700,
					height : 400,
					title:"新增考勤班组分类",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#supplierCategoryForm').validationEngine('validate')){
								$.post('${vix}/hr/attendanceTypeAction!saveOrUpdate.action?parentId='+$("#newScId").val(), {
										'attendanceCategory.id' : $("#newId").val(),
										'attendanceCategory.categoryCode' : $("#newCode").val(),
										'attendanceCategory.categoryName' : $("#newName").val()
										}, function(result){
											showMessage(result);
											setTimeout("",1000);
											loadContent('${vix}/hr/attendanceTypeAction!goList.action');
										});
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

function deleteById(id){
	$.ajax({
		  url:'${vix}/hr/attendanceTypeAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/hr/attendanceTypeAction!goSingleList.action?name="+name,'category');
			});
		  }
	});
}
/*改变搜索按钮的显示*/
function changeDisplay(){
	var divText = $("#lb_search_advanced").text();
	if(divText == "高级搜索"){
		$("#srm_code").attr({disabled:'true'});
		$("#simpleSearch").hide();
		$("#simpleReset").hide();
	}
	else{
		$("#srm_code").removeAttr("disabled");
		$("#simpleSearch").show();
		$("#simpleReset").show();
	}
}
/*搜索*/
function searchForContent(i){
	loadCode();
	loadName();
	loadContacts();
	if(i == 0){
		pager("start","${vix}/hr/attendanceTypeAction!goSearchList.action?i="+i+"&searchContent="+code,'category');
	}
	else{
		pager("start","${vix}/hr/attendanceTypeAction!goSearchList.action?i="+i+"&contacts="+contacts+"&name="+name,'category');
	}
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
		$("#srm_code").val("");
	}
	else{
		$("#srm_name").val("");
		$("#srm_contacts").val("");
	}
}

loadCode();
//载入分页列表数据
pager("start","${vix}/hr/attendanceTypeAction!goSingleList.action?code="+code+"&pageNo=${pageNo}",'category');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/hr/attendanceTypeAction!goSingleList.action?name=",'category');
}
//排序 
function orderBy(orderField){
	loadCode();
	var orderBy = $("#categoryOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/hr/attendanceTypeAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'category');
}

function categoryPager(tag,entity){
	loadCode();
	if(entity == 'category'){
		pager(tag,"${vix}/hr/attendanceTypeAction!goSingleList.action?name="+name+'&parentId='+$('#selectId').val(),entity);
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
//状态
function srmStatus(status){
	pager("start","${vix}/hr/attendanceTypeAction!goSingleList.action?status="+status,'category');
}
//最近使用
function srmRecent(rencentDate){
	pager("start","${vix}/hr/attendanceTypeAction!goSingleList.action?updateTime="+rencentDate,'category');
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/cmn_setting.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#">考勤管理</a></li>
				<li><a href="#">考勤设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/attendanceSpecialAction!goList.action');">特殊考勤制度</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#"><span><s:text name="cmn_add" /></span></a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" />
				<s:text name="cmn_index" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_mode" /></a>
				<ul>
					<li><a href="#" onclick="srmStatus('S1')"><img alt="" src="${vix}/common/img/icon_10.png">待确认</a></li>
					<li><a href="#" onclick="srmStatus('S2')"><img alt="" src="${vix}/common/img/icon_10.png">审批中</a></li>
					<li><a href="#" onclick="srmStatus('S3')"><img alt="" src="${vix}/common/img/icon_10.png">已发货</a></li>
					<li><a href="#" onclick="srmStatus('S4')"><img alt="" src="${vix}/common/img/icon_10.png">已完成</a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_recently_used" /></a>
				<ul>
					<li><a href="#" onclick="srmRecent('T1')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_days" /></a></li>
					<li><a href="#" onclick="srmRecent('T2')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_seven_days" /></a></li>
					<li><a href="#" onclick="srmRecent('T3')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_month" /></a></li>
					<li><a href="#" onclick="srmRecent('T4')"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<div>
			<label>编码:<input id="srm_code" name="" type="text" class="int" /></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(0);" />
			</label> <strong id="lb_search_advanced" onclick="changeDisplay();"><s:text name='cmn_advance_search' /></strong>
		</div>
		<div class="search_advanced">
			<label>名称:<input id="srm_name" name="" type="text" class="int" /></label> <label>编码:<input id="srm_contacts" name="" type="text" class="int" /></label> <label> <input name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(1);" /> <input name="" type="button" class="btn"
				value="<s:text name='cmn_reset'/>" onclick="resetForContent(1);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="attendanceCategoriesList" var="s">
				<li><a href="#" onclick="saveOrUpdate(${s.id});">${s.categoryName}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/hr/attendanceTypeAction!findTreeToJson.action",
						autoParam:["id"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/hr/attendanceTypeAction!goSingleList.action?id="+treeNode.id,"category");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div style="overflow: hidden; padding: 10px 0 0;">
				<ul class="np_tab clearfix">
					<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np',event)">特殊考勤制度</a></li>
				</ul>
				<div class="np_clist" id="np1">
					<div class="nt">
						<div class="nt_title">基本信息</div>
						<div class="nt_line">
							<table>
								<tr>
									<td>类别编码</td>
									<td><input type="text" name="" value="" class="ipt" /></td>
									<td>类别名称</td>
									<td><input type="text" name="" value="" class="ipt" /></td>
								</tr>
								<tr>
									<td>工时制度</td>
									<td><select name="">
											<option>标准工时制度</option>
											<option>自由工时制度</option>
									</select></td>
									<td>休息日方案</td>
									<td><select name="">
											<option>中国大陆地区</option>
											<option>国外地区</option>
									</select></td>
								</tr>
								<tr>
									<td>班次</td>
									<td><select name="">
											<option>正常班</option>
											<option>白班</option>
											<option>晚班</option>
									</select></td>
									<td>加班时间少于<input type="text" name="" value="60" class="ipt" />分钟不计加班
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="nt">
						<div class="nt_title">加班扣除剩余时间</div>
						<div class="nt_line">
							<table width="900" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>开始扣除时间</td>
									<td>结束扣除时间</td>
									<td>扣除小时</td>
									<td>备注</td>
								</tr>
								<tr>
									<td>2014-05-20 18：05</td>
									<td>2014-05-20 19：05</td>
									<td>1</td>
									<td>无</td>
								</tr>
								<tr>
									<td>2014-05-20 19：30</td>
									<td>2014-05-20 21：30</td>
									<td>1.5</td>
									<td>无</td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</table>
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