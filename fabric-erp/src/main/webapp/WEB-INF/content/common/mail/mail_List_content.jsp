<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该品牌么?', '<s:text name='vix_message'/>', function(action) {
			if (action == 'ok') {
				deleteById(id);
			}
		});
	}
	function chooseAll(chk) {
		if (null == chk) {
			$("input[name='chkId']").attr("checked", true);
		} else {
			if ($(chk).attr('checked')) {
				$("input[name='chkId']").attr("checked", true);
			} else {
				$("input[name='chkId']").attr("checked", false);
			}
		}
		selectCount();
	}
	function selectCount() {
		var selectCount = 0;
		$.each($("input[name='chkId']"), function(i, n) {
			if ($(n).attr('checked')) {
				selectCount++;
			}
		});
		$("#selectCount1").html(selectCount);
		$("#selectCount2").html(selectCount);
		if (selectCount == 0) {
			$("input[name='chkIds']").attr("checked", false);
		} else {
			$("input[name='chkIds']").attr("checked", true);
		}
	}
</script>
<input type="hidden" id="mailFileTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="mailFilePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="mailFileTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="mailFileOrderField" value="${pager.orderField}" />
<input type="hidden" id="mailFileOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="mailFileInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tr>
		<th width="5%">
			<div class="list_check">
				<div class="drop">
					<label><input name="" type="checkbox" value="" /></label>
					<ul>
						<li><a href="#">所有</a></li>
						<li><a href="#">其他</a></li>
						<li><a href="#">式样</a></li>
						<li><a href="#">关闭</a></li>
					</ul>
				</div>
			</div>
		</th>
		<th width="10%"><img src="img/mail_8.png" /></th>
		<th width="10%">发件人<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
		<th width="60%">主题<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
		<th width="10%">时间<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
		<th width="5%"></th>
	</tr>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<tr>
			<td><input name="" type="checkbox" value="" /></td>
			<td><img src="${vix}/common/img/mail_4.png" /></td>
			<td><div class="nowrap">
					<a href="#"><b>${entity.fromMail }</b></a>
				</div></td>
			<td><div class="nowrap">
					<a href="#"><b>${entity.subject }</span></a>
				</div></td>
			<td><fmt:formatDate value="${entity.mailReceiveDate }" pattern="yyyy-MM-dd hh:mm:ss" /></td>
			<td><img src="${vix}/common/img/mail_7.png" /></td>
		</tr>
	</s:iterator>
</table>