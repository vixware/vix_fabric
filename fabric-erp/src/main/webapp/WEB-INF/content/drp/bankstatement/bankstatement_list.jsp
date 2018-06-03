<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
	loadMenuContent('${vix}/drp/drpMainAction!goMenuContent.action');
	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}
	function saveOrUpdate(id) {
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
		url : '${vix}/drp/tocargoPlanAction!deleteById.action?id=' + id,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/drp/tocargoPlanAction!goSingleList.action?name=" + name, 'brand');
			});
		}
		});
	}
	function searchForContent() {
		loadName();
		pager("start", "${vix}/drp/tocargoPlanAction!goSingleList.action?name=" + name, 'brand');
	}
	loadName();
	//载入分页列表数据
	pager("start", "${vix}/drp/tocargoPlanAction!goSingleList.action?name=" + name, 'brand');
	//排序 
	function orderBy(orderField) {
		loadName();
		var orderBy = $("#brandOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/drp/tocargoPlanAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name, 'brand');
	}
	bindSearch();
	function currentPager(tag) {
		loadName();
		pager(tag, "${vix}/drp/tocargoPlanAction!goSingleList.action?name=" + name, 'brand');
	}
	$(".drop>ul>li").bind('mouseover', function() {
		$(this).children('ul').delay(400).slideDown('fast');
	}).bind('mouseleave', function() {
		$(this).children('ul').slideDown('fast').stop(true, true);
		$(this).children('ul').slideUp('fast');
	});
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_distributor.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">经销商管理 </a></li>
				<li><a href="#">对账单 </a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div class="ct_title">
			<div class="r ct_title_r">
				<select name="">
					<option>采购对账</option>
					<option>销售对账</option>
				</select>
			</div>
			<strong>2007年-月度对账表</strong>
		</div>
		<div class="table">
			<table class="list">
				<tr>
					<th width="10"></th>
					<th>月</th>
					<th>日期</th>
					<th>交付</th>
					<th>回款</th>
					<th>回款 - 交付</th>
				</tr>
				<tr>
					<td></td>
					<td>1</td>
					<td>续上月</td>
					<td>36670.00</td>
					<td>165000.00</td>
					<td>167707.00</td>
				</tr>
				<tr>
					<td></td>
					<td>2</td>
					<td>续上月</td>
					<td>36670.00</td>
					<td>165000.00</td>
					<td>167707.00</td>
				</tr>
				<tr>
					<td></td>
					<td>3</td>
					<td>续上月</td>
					<td>36670.00</td>
					<td>165000.00</td>
					<td>167707.00</td>
				</tr>
				<tr>
					<td></td>
					<td>4</td>
					<td>续上月</td>
					<td>36670.00</td>
					<td>165000.00</td>
					<td>167707.00</td>
				</tr>
				<tr>
					<td></td>
					<td>5</td>
					<td>续上月</td>
					<td>36670.00</td>
					<td>165000.00</td>
					<td>167707.00</td>
				</tr>
				<tr>
					<td></td>
					<td>6</td>
					<td>续上月</td>
					<td>36670.00</td>
					<td>165000.00</td>
					<td>167707.00</td>
				</tr>
				<tr>
					<td></td>
					<td>7</td>
					<td>续上月</td>
					<td>36670.00</td>
					<td>165000.00</td>
					<td>167707.00</td>
				</tr>
				<tr>
					<td></td>
					<td>8</td>
					<td>续上月</td>
					<td>36670.00</td>
					<td>165000.00</td>
					<td>167707.00</td>
				</tr>
				<tr>
					<td></td>
					<td>9</td>
					<td>续上月</td>
					<td>36670.00</td>
					<td>165000.00</td>
					<td>167707.00</td>
				</tr>
				<tr>
					<td></td>
					<td>10</td>
					<td>续上月</td>
					<td>36670.00</td>
					<td>165000.00</td>
					<td>167707.00</td>
				</tr>
				<tr>
					<td></td>
					<td>11</td>
					<td>续上月</td>
					<td>36670.00</td>
					<td>165000.00</td>
					<td>167707.00</td>
				</tr>
				<tr>
					<td></td>
					<td>12</td>
					<td>续上月</td>
					<td>36670.00</td>
					<td>165000.00</td>
					<td>167707.00</td>
				</tr>
			</table>
		</div>
	</div>

	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>