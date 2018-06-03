<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
var logisticsName = "";
function loadLogisticsName(){
	logisticsName = $('#logisticsName').val();
	logisticsName=Url.encode(logisticsName);
	logisticsName=Url.encode(logisticsName);
}
var deliveryAreaName = "";
function loadDeliveryAreaName(){
	deliveryAreaName = $('#deliveryAreaName').val();
	deliveryAreaName=Url.encode(deliveryAreaName);
	deliveryAreaName=Url.encode(deliveryAreaName);
}
function saveOrUpdate(id,parentId){
	$.ajax({
		  url:'${vix}/business/expressFeeRulesAction!goSaveOrUpdate.action?id='+id+"&parentId="+parentId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 455,
					title:"规则",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#expressFeeRulesForm').validationEngine('validate')){
							$.post('${vix}/business/expressFeeRulesAction!saveOrUpdate.action',
									 {
									  'expressFeeRules.id':$("#expressFeeRulesId").val(),
									  'expressFeeRules.logistics.id':$("#logisticsId").val(),
									  'expressFeeRules.deliveryArea.id':$("#deliveryAreaId").val(),
									  'expressFeeRules.firstWeight':$("#firstWeight").val(),
									  'expressFeeRules.firstCost':$("#firstCost").val(),
									  'expressFeeRules.perWeight':$("#perWeight").val(),
									  'expressFeeRules.perCost':$("#perCost").val(),
									  'updateField' : updateField,
									  'expressFeeRules.memo':$("#memo").val()
									},
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadContent('${vix}/business/expressFeeRulesAction!goList.action');
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
function searchForContent(){
	loadLogisticsName();
	loadDeliveryAreaName();
	pager("start","${vix}/business/expressFeeRulesAction!goSingleList.action?logisticsName="+logisticsName+"&deliveryAreaName="+deliveryAreaName,'expressFeeRules');
	resetForContent();
}
function resetForContent(){
	 $('#logisticsName').val('');
	 $('#deliveryAreaName').val('');
}
//载入分页列表数据
pager("start","${vix}/business/expressFeeRulesAction!goSingleList.action?1=1",'expressFeeRules');
/* 点击根节点列表页显示的数据 */
function loadRoot(){
	$('#selectId').val("");
	pager("start","${vix}/business/expressFeeRulesAction!goSingleList.action?1=1",'expressFeeRules');
}
//排序 
function orderBy(orderField){
	var orderBy = $("#expressFeeRulesOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/business/expressFeeRulesAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'expressFeeRules');
}

function expressFeeRulesPager(tag){
	pager(tag,"${vix}/business/expressFeeRulesAction!goSingleList.action?1=1",'expressFeeRules');
}
bindSearch();
//批量删除
function deleteEntitys() {
	var ids = '';
	$("[name='chkId']").each(function() {
		if ($(this).attr('checked')) {
			ids += $(this).val() + ",";
		}
	});
	$.ajax({
		url : '${vix}/business/expressFeeRulesAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/business/expressFeeRulesAction!goSingleList.action?1=1", 'expressFeeRules');
			});
		}
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_inbound.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">网店管理 </a></li>
				<li><a href="#">物流管理 </a></li>
				<li><a href="#">规则设置 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span>新增</span>
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
				<p>规则设置</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
		</ul>
		<div>
			<label>公司:<input type="text" class="int" id="logisticsName" placeholder="公司"></label> <label>区域:<input type="text" class="int" name="" id="deliveryAreaName" placeholder="区域"></label><label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /><input type="button"
				value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="expressFeeRulesList" var="c">
				<li><a href="#" onclick="goSaveOrUpdate(${c.id});">${c.code}</a></li>
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
					url:"${vix}/business/expressFeeRulesAction!findLogisticsTreeToJson.action",
					autoParam:["id","treeType"]
				},
				callback: {
					onClick: onClick
				}
			};
			function onClick(event, treeId, treeNode, clickFlag) {
				$("#selectId").val(treeNode.id);
				pager("start","${vix}/business/expressFeeRulesAction!goSingleList.action?parentId="+treeNode.id,"expressFeeRules");
			}
			zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" />
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="merger" /> </a></li>
								<li><a href="#"><s:text name="export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="expressFeeRulesPager('start');"></a> </span> <span><a class="previous" onclick="expressFeeRulesPager('previous');"></a> </span> <em>(<b class="expressFeeRulesInfo"></b> <s:text name='cmn_to' /> <b class="expressFeeRulesTotalCount"></b>)
						</em> <span><a class="next" onclick="expressFeeRulesPager('next');"></a> </span> <span><a class="end" onclick="expressFeeRulesPager('end');"></a> </span>
					</div>
				</div>
				<div id="expressFeeRules" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="merger" /> </a></li>
								<li><a href="#"><s:text name="export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="expressFeeRulesPager('start');"></a> </span> <span><a class="previous" onclick="expressFeeRulesPager('previous');"></a> </span> <em>(<b class="expressFeeRulesInfo"></b> <s:text name='cmn_to' /> <b class="expressFeeRulesTotalCount"></b>)
						</em> <span><a class="next" onclick="expressFeeRulesPager('next');"></a> </span> <span><a class="end" onclick="expressFeeRulesPager('end');"></a> </span>
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