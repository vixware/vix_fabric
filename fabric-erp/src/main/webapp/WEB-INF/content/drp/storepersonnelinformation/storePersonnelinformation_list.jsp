<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	loadMenuContent('${vix}/drp/drpMainAction!goMenuContent.action');
	var name = "";
	function loadName(){
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}
	var employeeCode = "";
	function loadEmployeeCode(){
		employeeCode = $('#employeeCode').val();
		employeeCode = Url.encode(employeeCode);
		employeeCode = Url.encode(employeeCode);
	}
	var employeeName = "";
	function loadEmployeeName(){
		employeeName = $('#employeeName').val();
		employeeName = Url.encode(employeeName);
		employeeName = Url.encode(employeeName);
	}
	/*保存*/
	function saveOrUpdate(id,pageNo){
		$.ajax({
			  url:'${vix}/drp/storePersonnelinformationAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo+"&source="+'${source}'+"&parentId="+$("#selectId").val()+"&treeType="+$("#selectTreeType").val(),
			  cache: false,
			  success: function(html){
			  	$("#mainContent").html(html);
			  }
		});
	};
	//批量删除
	function deleteEntitys() {
		var ids = '';
		$("[name='chkId']").each(function() {
			if ($(this).attr('checked')) {
				ids += $(this).val() + ",";
			}
		});
		$.ajax({
			url : '${vix}/drp/storePersonnelinformationAction!deleteByIds.action?ids=' + ids,
			cache : false,
			success : function(html) {
				asyncbox.success(html, "提示信息", function(action) {
					pager("start", "${vix}/drp/storePersonnelinformationAction!goSingleList.action?1=1", 'distributorEmployee');
				});
			}
		});
	}

	/*搜索*/ 
	function searchForContent(){
		loadName();
		loadEmployeeCode();
		loadEmployeeName();
		pager("start","${vix}/drp/storePersonnelinformationAction!goSingleList.action?name="+name+"&employeeCode="+employeeCode+'&employeeName='+employeeName,'distributorEmployee');
	}
	function resetForContent(){
		$("#nameS").val('');
		$("#employeeCode").val('');
		$("#employeeName").val('');
	}
	loadName();
	//载入分页列表数据
	pager("start","${vix}/drp/store/storePersonnelinformationAction!goSingleList.action?name="+name,'distributorEmployee');
	//分页
	function currentPager(tag){
		loadName();
		pager(tag,"${vix}/drp/store/storePersonnelinformationAction!goSingleList.action?name="+name,'distributorEmployee');
	}
	
	//排序 
	function orderBy(orderField){
		var orderBy = $("#distributorEmployeeOrderBy").val();
		if(orderBy == 'desc'){
			orderBy = "asc";
		}else{
			orderBy = 'desc';
		}
		pager("start","${vix}/drp/storePersonnelinformationAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'distributorEmployee');
	}
	function saveOrUpdateUserAccount(id,employeeId){
		$.ajax({
			  url:'${vix}/drp/accountManagementAction!goSaveOrUpdate.action?id='+id+"&parentId="+employeeId,
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 660,
						height : 380,
						title:"账号",
						html:html,
						callback : function(action){
							if(action == 'ok'){
								$("#userAccountForm").validationEngine('validate');
								var queryString = $('#userAccountForm').formSerialize(); 
								$.post('${vix}/drp/accountManagementAction!saveOrUpdate.action',
									queryString,
									function(result){
									showMessage (result);
									setTimeout ("" , 1000);
									loadContent ('${vix}/drp/storePersonnelinformationAction!goList.action');
									}
								 );
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	};
	bindSearch();
	/** 状态 */
	function listbystatus(status) {
		pager("start", "${vix}/drp/storePersonnelinformationAction!goSingleList.action?status=" + status, 'distributorEmployee');
	}
	/* 最近使用 */
	function leastRecentlyUsed(days) {
		pager("start", "${vix}/drp/storePersonnelinformationAction!goSingleList.action?days=" + days, 'distributorEmployee');
	}
	function goSearch() {
		$.ajax({
		url : '${vix}/drp/storePersonnelinformationAction!goSearch.action',
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
					loadcode();
					loadselectname();
					pager("start", "${vix}/drp/storePersonnelinformationAction!goSingleList.action?code=" + code + "&name=" +selectname, 'distributorEmployee');
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
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<s:if test="source =='drp' ">
					<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
					<li><a href="#" onclick="loadContent('${vix}/drp/drpAction!goList.action','bg_02');"><s:text name="drp_management" /> </a></li>
					<li><a href="#"><s:text name="drp_store_management" /> </a></li>
					<li><a href="#"><s:text name="drp_store_personnel_information" /> </a></li>
				</s:if>
				<s:else>
					<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
					<li><a href="#" onclick="loadContent('${vix}/drp/drpAction!goList.action','bg_02');">连锁经营管理 </a></li>
					<li><a href="#">门店管理 </a></li>
					<li><a href="#">门店人员信息</a></li>
				</s:else>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /> </span> </a>
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
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>内容:<input type="text" class="int" id="nameS" placeholder="名称"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" /></label> <label> <input
				type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="employeeList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});"><span style="display: none;">${c.chineseCharacter}</span>${c.name}</a></li>
			</s:iterator>
		</ul>
	</div>
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
						url:"${vix}/drp/distributerManagementAction!findOrgAndUnitTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectTreeType").val(treeNode.treeType);
					pager("start","${vix}/drp/storePersonnelinformationAction!goSingleList.action?id="+treeNode.id+"&treeType="+treeNode.treeType,"distributorEmployee");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="${treeType}" />
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><img src="img/mail.png" alt="" /> <s:text name="drp_store_personnel_information" /></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_add' /> </a></li>
								<li><a href="#"><s:text name='cmn_update' /> </a></li>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="distributorEmployeeInfo"></b> <s:text name='cmn_to' /> <b class="distributorEmployeeTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="distributorEmployee" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="distributorEmployeeInfo"></b> <s:text name='cmn_to' /> <b class="distributorEmployeeTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>