<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="hidden" id="categoryTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="categoryPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="categoryTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="categoryOrderField" value="${pager.orderField}" />
<input type="hidden" id="categoryOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="categoryInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />

<!-- 列表上面功能区  -->
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

<!-- 列表区  -->
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="50">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkCategoryIds" onchange="chooseAll(this)"> </label>
						<ul>
							<li><a href="#" onclick="chooseAll();">所有</a></li>
							<li><a href="#">其他</a></li>
							<li><a href="#">式样</a></li>
							<li><a href="#">关闭</a></li>
						</ul>
					</div>
				</div>
			</th>
			<th style="width: 55px; text-align: center;">业务</th>
			<th><s:text name="eam_equipmentcode" /></th>
			<th><s:text name="eam_devicename" /></th>
			<th>任务名称</th>
			<th>计划周期</th>
			<th>状态</th>
			<th>估算成本</th>
			<th>负责人</th>
			<th style="width: 65px; text-align: center;"><s:text name="cmn_operate" /></th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<%
				count++;
			%>
			<tr>
				<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<!--  	<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>-->
				<td>
					<div class="pagelist drop">
						<ul>
							<li class="tp"><a href="#">业务</a>
								<ul>
									<li><a href="javascript:void(0);" onclick="_tabShow('功能1','${vix}/eam/faultReportAction!eqMove.action?id=${entity.id}','_p_eq_move')" href="javascript:void(0);" title="">功能1</a></li>
									<li><a href="javascript:void(0);" onclick="_tabShow('功能2','${vix}/eam/faultReportAction!eqRepair.action?id=${entity.id}','_p_eq_repair')" href="javascript:void(0);" title="">功能2</a></li>
									<li><a href="javascript:void(0);" onclick="_tabShow('功能3','${vix}/eam/faultReportAction!eqRemove.action?id=${entity.id}','_p_eq_remove')" href="javascript:void(0);" title="">功能3</a></li>
								</ul></li>
						</ul>
					</div>
				</td>
				<td><span style="color: gray;">1A2B3C4D</span></td>
				<td><span style="color: gray;">设备名称</span></td>
				<td><span style="color: gray;">任务名称</span></td>
				<td><span style="color: gray;">计划周期</span></td>
				<td><span style="color: gray;">状态</span></td>
				<td><span style="color: gray;">估算成本</span></td>
				<td><span style="color: gray;">负责人</span></td>
				<td style="padding-top: 2px;"><a href="javascript:void(0);" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="javascript:void(0);" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_12.png" />
				</a>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#"></a> </i> <i><a href="#" title="<s:text name='cmn_show'/>"></a> </i> <i><a href="#" onclick="saveOrUpdate(${entity.id},$('#parentId').val());" title="<s:text name='cmn_update'/>"></a> </i> <b>${entity.name}</b>
							</strong>
							<p></p>
						</div>
					</div></td>
			</tr>
		</s:iterator>
		<%
			/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			com.vix.core.web.Pager pager = (com.vix.core.web.Pager) request
					.getAttribute("pager");
			count = pager.getPageSize() - count;
			request.setAttribute("count", count);
		%>
		<c:forEach begin="1" end="${count}">
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<!-- 列表下面功能区  -->
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


<script type="text/javascript">
//自定义列表下拉菜单
bindPageDropMenu();

$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
 
function deleteEntity(id){
	asyncbox.confirm('确定要删除该分类么?','提示信息',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}

function chooseAll(chk){
	if(null == chk){
		$("input[name='chkCategoryId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkCategoryId']").attr("checked", true);
		}else{
			$("input[name='chkCategoryId']").attr("checked", false);
		}
	}
	selectCount();
}

function selectCount(){
	var selectCount = 0;
	$.each($("input[name='chkCategoryId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectCategoryCount1").html(selectCount);
	$("#selectCategoryCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkCategoryIds']").attr("checked", false);
	}else{
		$("input[name='chkCategoryIds']").attr("checked", true);
	}
}

$(".untitled").hover(
	function () {
		$(this).css({ "position": "relative"});
		$(this).children('.popup').css({ "display": "block"});
		var bh = $("body").height(); 
		var pt = $(this).offset();
		if(( bh - pt.top) < 165){$(this).children('.popup').css({ "bottom": "0"});}else{$(this).children('.popup').css({ "top": "-7px"});};
  	},
	function () {
		$(this).css({ "position": "static"});
		$(this).children('.popup').css({ "display": "none"});
 	}
);
$(".close").click(
	function () {
		$(this).parent().parent().css({ "display": "none"});
	}
);
</script>