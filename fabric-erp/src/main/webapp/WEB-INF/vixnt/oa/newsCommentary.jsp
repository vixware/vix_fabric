<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<li>
	<div class="chat-footer">
		<form id="postilForm" action="" method="post">
			<input type="hidden" id="trendsId" name="trends.id" value="${trends.id}" /> <input type="hidden" id="evaluationReviewId" name="evaluationReview.id" value="${evaluationReview.id }" />
			<div class="textarea-div">
				<div class="typearea">
					<textarea id="textarea-expand" placeholder="输入你的评论信息…" name="evaluationReview.evaluationContent" class="custom-scroll" onkeydown="return submitkeyclick(this,event)"></textarea>
				</div>
			</div>
		</form>
		<span class="textarea-controls">
			<button class="btn btn-sm btn-primary pull-right" onclick="saveOrUpdateComments()">评论</button>
		</span>
	</div>
</li>
<script type="text/javascript">
function saveOrUpdateComments() {
	var commentscontent = $("#textarea-expand").val();

	// 验证
	if (!commentscontent || $.trim(commentscontent).length <= 0) {
		alert("请输入评论信息!");
		return;
	}
	if (commentscontent.length > 50) {
		alert("评论,请不要超过50个字！");
		return;
	}

	$("#postilForm").ajaxSubmit({
	type : "post",
	url : "${nvix}/nvixnt/newsAdministrationAction!saveOrUpdateComments.action",
	dataType : "text",
	success : function(id) {
		loadContent('postil', '${nvix}/nvixnt/newsAdministrationAction!goNewsDetail.action?id=' + id);
	}
	});
};

/**
 * 回车发送消息
 */
function submitkeyclick(obj, evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "")
	keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which : evt.charCode);
	if (keyCode == 13) {
		saveOrUpdateComments();
	}
}
</script>