<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}
	function searchForRightContent() {
		loadName();
		pager("start", "${vix}/inventory/inboundWarehouseAction!goChooseListContent.action?name=" + name + "&categoryId=" + $("#selectId").val(), 'itemForChoose');
	}
	loadName();
	//载入分页列表数据
	pager("start", "${vix}/inventory/inboundWarehouseAction!goChooseListContent.action?name=" + name, 'itemForChoose');
	$.returnValue = "";
	var itemId = "";
	function chkChange(chk, id, code, name) {
		if (chk.checked) {
			itemId = id;
			$.ajax({
			url : '${vix}/inventory/inboundWarehouseAction!chooseSpecification.action?id=' + id,
			cache : false,
			success : function(html) {
				$("#itemSpecDetail_" + id).html(html);
				if (html.length < 20) {
					$.returnValue = id;
				}
			}
			});
		}
	}

	function specChange() {
		specificationDetailIds = "";
		$("input[id^='specification_" + itemId + "_']").each(function() {
			if ($(this).attr("checked") == "checked") {
				specificationDetailIds += $(this).attr("value") + ",";
			}
		});
		$.returnValue = itemId + "," + specificationDetailIds;
	}
	function currentPagerForItemChoose(tag) {
		loadName();
		pager(tag, "${vix}/inventory/inboundWarehouseAction!goChooseListContent.action?name=" + name + '&orderStatus=' + orderStatus, 'itemForChoose');
	}
</script>
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
			<li><a href="#" id="numBtn"><img src="${vix}/common/img/icon_10.png" alt="" /> <s:text name="cmn_index" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="订单状态" /></a>
				<ul>
					<li><a href="#"><img alt="" src="${vix}/common/img/icon_10.png">待确认</a></li>
					<li><a href="#"><img alt="" src="${vix}/common/img/icon_10.png">审批中</a></li>
					<li><a href="#"><img alt="" src="${vix}/common/img/icon_10.png">已发货</a></li>
					<li><a href="#"><img alt="" src="${vix}/common/img/icon_10.png">已完成</a></li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="nameS"></label> <label><s:text name="cmn_my_item" /><input type="checkbox" value="" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent();" /><input type="button"
				value="<s:text name='cmn_reset'/>" class="btn" name="" /></label> <strong id="lb_search_advanced"><s:text name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""></label> <label><s:text name="cmn_name" /><input type="text" class="int"
				name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<li><a href="#">1</a></li>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box" style="height: 455px;">
		<div id="left" class="ui-resizable" style="width: 180px; height: 400px;">
			<div class="left_content">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();">根目录</a>
				</div>
				<div id="chooseItemTree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" />
		<!-- left -->
		<div id="right">
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPagerForItemChoose('start');"></a></span> <span><a class="previous" onclick="currentPagerForItemChoose('previous');"></a></span> <em>(<b class="itemForChooseInfo"></b> <s:text name='cmn_to' /> <b class="itemForChooseTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPagerForItemChoose('next');"></a></span> <span><a class="end" onclick="currentPagerForItemChoose('end');"></a></span>
					</div>
				</div>
				<div id="itemForChoose" style="overflow-x: auto; overflow-y: hidden; width: 100%;"></div>
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPagerForItemChoose('start');"></a></span> <span><a class="previous" onclick="currentPagerForItemChoose('previous');"></a></span> <em>(<b class="itemForChooseInfo"></b> <s:text name='cmn_to' /> <b class="itemForChooseTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPagerForItemChoose('next');"></a></span> <span><a class="end" onclick="currentPagerForItemChoose('end');"></a></span>
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