<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var name = "";
    function loadName() {
	    name = $('#nameS').val();
	    name = Url.encode(name);
	    name = Url.encode(name);
    }

    function saveOrUpdate(id,parentId,parentName) {
	    $.ajax({
	    url : '${vix}/common/measureUnitAction!goSaveOrUpdate.action?id=' + id+"&parentId="+parentId,
	    cache : false,
	    success : function(html) {
		    asyncbox.open({
		    modal : true,
		    width : 680,
		    height : 250,
		    title : "计量单位",
		    html : html,
		    callback : function(action) {
			    if (action == 'ok') {
				    if ($('#measureUnitForm').validationEngine('validate')) {
					    $.post('${vix}/common/measureUnitAction!saveOrUpdate.action', {
					    'measureUnit.id' : $("#id").val(),
					    'measureUnit.organization.id' : $("#organizationId").val(),
					    'measureUnit.companyCode' : $("#companyCode").val(),
					    'measureUnit.code' : $("#code").val(),
					    'measureUnit.name' : $("#name").val(),
					    'measureUnit.measureUnitType.id' : $("#measureUnitTypeId").val()
					    }, function(result) {
						    showMessage(result);
						    setTimeout("", 1000);
						    loadContent('${vix}/common/measureUnitAction!goList.action');
					    });
				    } else {
					    return false;
				    }
			    }
		    },
		    btnsbar : $.btn.OKCANCEL
		    });
	    }
	    });
    };
    function saveOrUpdateUnitType() {
	    $.ajax({
	    url : '${vix}/common/measureUnitAction!goSaveOrUpdateUnitType.action',
	    cache : false,
	    success : function(html) {
		    asyncbox.open({
		    modal : true,
		    width : 680,
		    height : 250,
		    title : "单位类型",
		    html : html,
		    callback : function(action) {
			    if (action == 'ok') {
				    if ($('#measureUnitTypeForm').validationEngine('validate')) {
					    $.post('${vix}/common/measureUnitAction!saveOrUpdateUnitType.action', {
					    'measureUnitType.id' : $("#measureUnitTypeId").val(),
					    'measureUnitType.code' : $("#measureUnitTypeCode").val(),
					    'measureUnitType.name' : $("#measureUnitTypeName").val(),
					    }, function(result) {
						    showMessage(result);
						    setTimeout("", 1000);
						    loadContent('${vix}/common/measureUnitAction!goList.action');
					    });
				    } else {
					    return false;
				    }
			    }
		    },
		    btnsbar : $.btn.OKCANCEL
		    });
	    }
	    });
    };
    function deleteByIds() {
	    var ids = '';
	    $("[name='chkId']").each(function() {
		    if ($(this).attr('checked')) {
			    ids += $(this).val() + ",";
		    }
	    });
	    asyncbox.success(ids, "选中的id");
    }

    function deleteById(id) {
	    $.ajax({
	    url : '${vix}/common/measureUnitAction!deleteById.action?id=' + id,
	    cache : false,
	    success : function(html) {
		    asyncbox.success(html, "提示信息", function(action) {
			    loadContent('${vix}/common/measureUnitAction!goList.action');
		    });
	    }
	    });
    }

    function searchForContent() {
	    loadName();
	    pager("start", "${vix}/common/measureUnitAction!goListContent.action?name=" + name, 'measureUnit');
    }

    loadName();
    //载入分页列表数据
    pager("start", "${vix}/common/measureUnitAction!goListContent.action?name=" + name, 'measureUnit');
    //排序 
    function orderBy(orderField) {
	    loadName();
	    var orderBy = $("#currencyTypeOrderBy").val();
	    if (orderBy == 'desc') {
		    orderBy = "asc";
	    } else {
		    orderBy = 'desc';
	    }
	    pager("start", "${vix}/common/measureUnitAction!goListContent.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name, 'measureUnit');
    }

    function currentPager(tag) {
	    loadName();
	    pager(tag, "${vix}/common/measureUnitAction!goListContent.action?name=" + name, 'measureUnit');
    }
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sys_settings.png" alt="" />系统管理</a></li>
				<li><a href="#">基础设置</a></li>
				<li><a href="#">字典管理</a></li>
				<li><a href="#">计量单位</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name='cmn_add' /> </span> </a>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"> </b>
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
					<li><a href="#" onclick="saleOrderStatus('0')"><img alt="" src="img/uncommitted.png"> <s:text name="cmn_uncommitted" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/unaudited.png"> <s:text name="cmn_unapproved" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('2')"><img alt="" src="img/verifying.png"> <s:text name="cmn_approval_in" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('3')"><img alt="" src="img/approved.png"> <s:text name="cmn_approval" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name="cmn_content" />:<input type="text" class="int" id="nameS"> </label> <label><input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);"> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>" class="btn"
				onclick="resetForContent(0)"> </label> <strong id="lb_search_advanced" onclick="changeDisplay();"><s:text name="cmn_advance_search" /> </strong>
		</div>
		<div class="search_advanced">
			<label>单号:<input type="text" class="int" name="code1" id="code1">
			</label> <label> <s:text name="wim_warehouse1" /> <input type="text" class="int" name="warehousecode" id="warehousecode1">
			</label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(1);"> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(1)">
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="wimStockrecordsList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.code}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
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
						url:"${vix}/drp/distributionSystemRelationShipAction!findTreeToJson.action",
						autoParam:["id","treeType"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdType").val(treeNode.treeType);
					pager("start","${vix}/common/measureUnitAction!goListContent.action?id="+treeNode.id+"&treeType="+treeNode.treeType,"measureUnit");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" />
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="merger" /> </a></li>
								<li><a href="#"><s:text name="export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="measureUnitInfo"></b> <s:text name='cmn_to' /> <b class="measureUnitTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="measureUnit" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="merger" /> </a></li>
								<li><a href="#"><s:text name="export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="measureUnitInfo"></b> <s:text name='cmn_to' /> <b class="measureUnitTotalCount"></b>)
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