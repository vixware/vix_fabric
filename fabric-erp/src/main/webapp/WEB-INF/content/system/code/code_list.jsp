<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
/* 设置编码*/
function saveOrUpdateBillsType(id,billTypeCode){
	$.ajax({
		  url:'${vix}/system/codeAction!goSaveOrUpdateEncoding.action?id='+id+"&billTypeCode="+billTypeCode,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 700,
					height : 435,
					title:"编码设置",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#encodingRulesTableInTheMiddleForm').validationEngine('validate')){
							$.post('${vix}/system/codeAction!saveOrUpdate.action',
								 {
								  'encodingRulesTableInTheMiddle.id':$("#encodingRulesTableInTheMiddleId").val(),
								  'encodingRulesTableInTheMiddle.billType':$("#billType").val(),
								  'encodingRulesTableInTheMiddle.codeLength':$("#codeLength").val(),
								  'encodingRulesTableInTheMiddle.enableSeries':$("#enableSeries").val(),
								  'encodingRulesTableInTheMiddle.level1Length':$("#level1Length").val(),
								  'encodingRulesTableInTheMiddle.level2Length':$("#level2Length").val(),
								  'encodingRulesTableInTheMiddle.level3Length':$("#level3Length").val(),
								  'encodingRulesTableInTheMiddle.level4Length':$("#level4Length").val(),
								  'encodingRulesTableInTheMiddle.level5Length':$("#level5Length").val(),
								  'encodingRulesTableInTheMiddle.level6Length':$("#level6Length").val(),
								  'encodingRulesTableInTheMiddle.level7Length':$("#level7Length ").val(),
								  'encodingRulesTableInTheMiddle.level8Length':$("#level8Length ").val(),
								  'encodingRulesTableInTheMiddle.level9Length':$("#level9Length ").val(),
								  'encodingRulesTableInTheMiddle.level10Length':$("#level10Length ").val(),
								  'encodingRulesTableInTheMiddle.level11Length':$("#level11Length ").val(),
								  'encodingRulesTableInTheMiddle.level12Length':$("#level12Length ").val(),
								  'encodingRulesTableInTheMiddle.codeType':$("#codeType ").val(),
								  'encodingRulesTableInTheMiddle.level1type' : $ ("#level1type").val () ,
								  'encodingRulesTableInTheMiddle.level2type' : $ ("#level2type").val () ,
								  'encodingRulesTableInTheMiddle.level3type' : $ ("#level3type").val () ,
								  'encodingRulesTableInTheMiddle.level4type' : $ ("#level4type").val () ,
								  'encodingRulesTableInTheMiddle.level5type' : $ ("#level5type").val () ,
								  'encodingRulesTableInTheMiddle.level6type' : $ ("#level6type").val () ,
								  'encodingRulesTableInTheMiddle.level7type' : $ ("#level7type").val () ,
								  'encodingRulesTableInTheMiddle.level8type' : $ ("#level8type").val () ,
								  'encodingRulesTableInTheMiddle.level9type' : $ ("#level9type").val () ,
								  'encodingRulesTableInTheMiddle.level10type' : $ ("#level10type").val () ,
								  'encodingRulesTableInTheMiddle.level11type' : $ ("#level11type").val () ,
								  'encodingRulesTableInTheMiddle.level12type' : $ ("#level12type").val () ,
								  'encodingRulesTableInTheMiddle.level1value' : $ ("#level1value").val () ,
								  'encodingRulesTableInTheMiddle.level2value' : $ ("#level2value").val () ,
								  'encodingRulesTableInTheMiddle.level3value' : $ ("#level3value").val () ,
								  'encodingRulesTableInTheMiddle.level4value' : $ ("#level4value").val () ,
								  'encodingRulesTableInTheMiddle.level5value' : $ ("#level5value").val () ,
								  'encodingRulesTableInTheMiddle.level6value' : $ ("#level6value").val () ,
								  'encodingRulesTableInTheMiddle.level7value' : $ ("#level7value").val () ,
								  'encodingRulesTableInTheMiddle.level8value' : $ ("#level8value").val () ,
								  'encodingRulesTableInTheMiddle.level9value' : $ ("#level9value").val () ,
								  'encodingRulesTableInTheMiddle.level10value' : $ ("#level10value").val () ,
								  'encodingRulesTableInTheMiddle.level11value' : $ ("#level11value").val () ,
								  'encodingRulesTableInTheMiddle.level12value' : $ ("#level12value").val () ,
								  'encodingRulesTableInTheMiddle.timeType' : $ ("#timeType").val () ,
								  'encodingRulesTableInTheMiddle.isOpenTime' : $ ("#isOpenTime").val () ,
								  'encodingRulesTableInTheMiddle.timeFormat' : $ ("#timeFormat").val () ,
								  'encodingRulesTableInTheMiddle.serialNumberBegin' : $ ("#serialNumberBegin").val () ,
								  'encodingRulesTableInTheMiddle.serialNumberEnd' : $ ("#serialNumberEnd").val () ,
								  'encodingRulesTableInTheMiddle.serialNumberStep' : $ ("#serialNumberStep").val () 
								},
								function(result){
									  showMessage(result);
									  setTimeout("", 1000);
									  loadContent('${vix}/system/codeAction!goList.action');
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
		  url:'${vix}/system/codeAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/system/codeAction!goSingleList.action?name="+name,'encodingRulesTableInTheMiddle');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/system/codeAction!goSingleList.action?name="+name,'encodingRulesTableInTheMiddle');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/system/codeAction!goSingleList.action?name="+name,'encodingRulesTableInTheMiddle');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/system/codeAction!goSingleList.action?name=",'encodingRulesTableInTheMiddle');
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
	pager("start","${vix}/system/codeAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'encodingRulesTableInTheMiddle');
}
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/system/codeAction!goSingleList.action?name="+name,'encodingRulesTableInTheMiddle');
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sys_settings.png" alt="" />系统管理</a></li>
				<li><a href="#">基础设置 </a></li>
				<li><a href="#">编码管理 </a></li>
				<li><a href="#">编码级数管理 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdateBillsType(0,$('#selectIdTreeCode').val());"><span>新增编码规则 </span> </a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_unapproved_plan" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_approval_by_plan" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_approval_in" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name="cmn_content" />: <input type="text" class="int" id="nameS"> </label> <label> <input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();"> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""
				onclick="resetForContent();">
			</label> <strong id="lb_search_advanced"><s:text name="cmn_advance_search" /> </strong>
		</div>
		<div class="search_advanced">
			<label>商品编码: <input id="itemCode" type="text" class="int" />
			</label><label>商品名称: <input id="itemName" type="text" class="int" />
			</label> <label> <input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();"> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();">
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="channelDistributorList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.code}</a></li>
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
			    var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/system/billTypeManagementAction!findOrgAndUnitTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					$("#selectIdTreeCode").val(treeNode.code);
					pager("start","${vix}/system/codeAction!goSingleList.action?billTypeCode="+treeNode.code,"encodingRulesTableInTheMiddle");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" /><input type="hidden" id="selectIdTreeCode" name="selectIdTreeCode" />
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><img src="img/mail.png" alt="" />编码</li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_add' /> </a></li>
								<li><a href="#"><s:text name='cmn_update' /> </a></li>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="encodingRulesTableInTheMiddleInfo"></b> <s:text name='cmn_to' /> <b class="encodingRulesTableInTheMiddleTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="encodingRulesTableInTheMiddle" class="table"></div>
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
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="encodingRulesTableInTheMiddleInfo"></b> <s:text name='cmn_to' /> <b class="encodingRulesTableInTheMiddleTotalCount"></b>)
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