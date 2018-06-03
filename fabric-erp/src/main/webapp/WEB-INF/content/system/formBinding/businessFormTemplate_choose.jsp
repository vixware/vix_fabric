<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	pager("start", "${vix}/system/formBindingAction!goBusinessFormTemplateList.action?1=1", "radio");
	$.returnValue = "";
	function chkChange(chk, id) {
		if (chk.checked) {
			$.returnValue = id;
		}
	}
</script>
<div class="content" style="background: #DCE7F1">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<div class="box">
		<div id="right">
			<div id="radio"></div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>