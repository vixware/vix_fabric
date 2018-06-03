<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<li>
	<div class="chat-footer">
		<form id="postilForm" action="" method="post">
			<input type="hidden" id="evaluationReviewId" name="subevaluationReview.id" value="${subevaluationReview.id }" /> <input type="hidden" id="parentEvaluationReviewId" name="subevaluationReview.parentEvaluationReview.id" value="${subevaluationReview.parentEvaluationReview.id}" />
			<div class="textarea-div">
				<div class="typearea">
					<textarea id="textarea-expand" placeholder="输入你的回复信息…" name="subevaluationReview.evaluationContent" class="custom-scroll" onkeydown="return submitkeyclick(this,event)"></textarea>
				</div>
			</div>
		</form>
		<span class="textarea-controls">
			<button class="btn btn-sm btn-primary pull-right" onclick="saveOrUpdateCommentss()">回复</button>
		</span>
	</div>
</li>
<script type="text/javascript">
function saveOrUpdateCommentss() {
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
	url : "${nvix}/nvixnt/newsAdministrationAction!saveOrUpdateCommentss.action",
	dataType : "text",
	/* success : function(id) {
		loadContent('postil', '${nvix}/nvixnt/newsAdministrationAction!goComment.action?id=' + id);
	} */
	});
};

/**
 * 回车发送消息
 */
function submitkeyclick(obj, evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "")
	keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which : evt.charCode);
	if (keyCode == 13) {
		saveOrUpdateCommentss();
	}
}
</script>