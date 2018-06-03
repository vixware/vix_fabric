<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>客户 : <input type="text" name="subjectAS" id="subjectAS" class="int" />
				</td>
				<td>主题 : <input type="text" name="customerAccountNameAS" id="customerAccountNameAS" class="int" />
				</td>
				<td>${vv:varView('vix_mdm_item')}:<input type="text" name="itemNameAS" id="itemNameAS" class="int" />
				</td>
			</tr>
		</table>
	</div>
</div>