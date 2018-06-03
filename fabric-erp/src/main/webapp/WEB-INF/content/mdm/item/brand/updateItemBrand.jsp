<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#itemBrandForm").validationEngine();
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="itemBrandForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="id" name="itemBrand.id" value="%{itemBrand.id}" theme="simple" />
				<tr height="30">
					<td align="right">编码:&nbsp;</td>
					<td><input id="code" name="itemBrand.code" value="${itemBrand.code}" /></td>
					<td align="right">名称:&nbsp;</td>
					<td><input id="name" name="itemBrand.name" value="${itemBrand.name}" /></td>
				</tr>
				<tr height="30">
					<td align="right">公司名称:&nbsp;</td>
					<td><input id="brandCompany" name="itemBrand.brandCompany" value="${itemBrand.brandCompany}" /></td>
					<td align="right">公司地址:&nbsp;</td>
					<td><input id="companyAddress" name="itemBrand.companyAddress" value="${itemBrand.companyAddress}" /></td>
				</tr>
				<tr height="30">
					<td align="right">品牌排序:&nbsp;</td>
					<td><input id="orderBy" name="itemBrand.orderBy" value="${itemBrand.orderBy}" /></td>
					<td align="right">品牌描述:&nbsp;</td>
					<td><input id="memo" name="itemBrand.memo" value="${itemBrand.memo}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>