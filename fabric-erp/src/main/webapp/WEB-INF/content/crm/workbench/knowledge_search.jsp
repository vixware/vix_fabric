<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var kmQuestion = "";
var knowledgeCategory = "";
var subject = "";
function loadConditions(){
	kmQuestion = $('#kmQuestion').val();
	kmQuestion=Url.encode(kmQuestion);
	kmQuestion=Url.encode(kmQuestion);
	knowledgeCategory = $('#knowledgeCategory').val();
	knowledgeCategory=Url.encode(knowledgeCategory);
	knowledgeCategory=Url.encode(knowledgeCategory);
	subject = $('#subject').val();
	subject=Url.encode(subject);
	subject=Url.encode(subject);
}
loadConditions();
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>编码 : <input type="text" name="kmQuestion" id="kmQuestion" class="int" />
				</td>
				<td>分类 : <input type="text" name="knowledgeCategory" id="knowledgeCategory" class="int" />
				</td>
				<td>主题 : <input type="text" name="subject" id="subject" class="int" />
				</td>
			</tr>

		</table>
	</div>
</div>