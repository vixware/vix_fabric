<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("table tr").mouseover(function() {
		$(this).addClass("over");
	}).mouseout(function() {
		$(this).removeClass("over");
	});
	$("table tr:even").addClass("alt");
	$(".list_check").bind('mouseover', function() {
		$(".list_check").addClass('posr');
		$(".list_check ul").css('display', 'block');
	}).bind('mouseleave', function() {
		$(".list_check").removeClass('posr');
		$(".list_check ul").css('display', 'none');
	});

	function deleteEntity(id) {
		asyncbox.confirm('确定要删除数据吗?', '提示信息', function(action) {
			if (action == 'ok') {
				deleteById(id);
			}
		});
	}

	function chooseAll(chk) {
		if (null == chk) {
			$("input[name='chkCategoryId']").attr("checked", true);
		} else {
			if ($(chk).attr('checked')) {
				$("input[name='chkCategoryId']").attr("checked", true);
			} else {
				$("input[name='chkCategoryId']").attr("checked", false);
			}
		}
		selectCount();
	}

	function selectCount() {
		var selectCount = 0;
		$.each($("input[name='chkCategoryId']"), function(i, n) {
			if ($(n).attr('checked')) {
				selectCount++;
			}
		});
		$("#selectCategoryCount1").html(selectCount);
		$("#selectCategoryCount2").html(selectCount);
		if (selectCount == 0) {
			$("input[name='chkCategoryIds']").attr("checked", false);
		} else {
			$("input[name='chkCategoryIds']").attr("checked", true);
		}
	}

	$(".untitled").hover(function() {
		$(this).css({
			"position" : "relative"
		});
		$(this).children('.popup').css({
			"display" : "block"
		});
		var bh = $("body").height();
		var pt = $(this).offset();
		if ((bh - pt.top) < 165) {
			$(this).children('.popup').css({
				"bottom" : "0"
			});
		} else {
			$(this).children('.popup').css({
				"top" : "-7px"
			});
		}
		;
	}, function() {
		$(this).css({
			"position" : "static"
		});
		$(this).children('.popup').css({
			"display" : "none"
		});
	});
	$(".close").click(function() {
		$(this).parent().parent().css({
			"display" : "none"
		});
	});
</script>
<input type="hidden" id="categoryTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="categoryPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="categoryTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="categoryOrderField" value="${pager.orderField}" />
<input type="hidden" id="categoryOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="categoryInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr>
			<td>班次编码：</td>
			<td><input id="newScId" name="attendanceCategory.attendanceCategory.categoryCode" value="${attendanceCategory.categoryCode}" type="text" /></td>
			<td>班次名称</td>
			<td><input name="newScName" id="newCategoryName" type="text" size="30" value="${attendanceCategory. categoryName}" /></td>
		</tr>
		<tr>
			<td>应出勤(小时)</td>
			<td><input name="activityAddress" id="activityAddress" type="text" size="30" value="${hrRecruitactivitity.activityAddress }" /></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>应出勤(天数)</td>
			<td><input name="activityAddress" id="activityAddress" type="text" size="30" value="${hrRecruitactivitity.activityAddress }" /></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>应出勤(分钟)</td>
			<td><input name="activityAddress" id="activityAddress" type="text" size="30" value="${hrRecruitactivitity.activityAddress }" /></td>
			<td><input type="checkbox" name="selectFlag"></td>
			<td>启用班次</td>
		</tr>
		<tr>
			<td>迟到时间：不足</td>
			<td><input name="activityAddress" id="activityAddress" type="text" size="15" value="0" />分钟忽略不计；超过<input name="activityAddress" id="activityAddress" type="text" size="15" value="0" />分钟计为旷工</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>早退时间：不足</td>
			<td><input name="activityAddress" id="activityAddress" type="text" size="15" value="0" />分钟忽略不计；超过<input name="activityAddress" id="activityAddress" type="text" size="15" value="0" />分钟计为旷工</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>班段1</td>
			<td><input type="checkbox" name="selectFlag"></td>
			<td>加班段</td>
			<td></td>
			<td></td>
		</tr>
	</tbody>
</table>