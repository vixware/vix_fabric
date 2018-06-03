<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<s:if test="specificationList.size > 0">
	<div class="nt">
		<div class="nt_title">
			<label>规格属性</label>
		</div>
		<table>
			<s:iterator var="spec" value="specificationList" status="sp">
				<tr>
					<td>
						<div class="nt">
							<div class="nt_title">
								<label>${spec.name }</label>
							</div>
							<table>
								<s:iterator var="ef" value="loadSpecificationDetail(#spec.id)" status="s">
									<s:if test="#s.count%4 == 1 ">
										<tr height="30">
									</s:if>
									<td align="center"><input id="append_${ef.name}" name="${ef.id}" type="checkbox" value="" />${ef.name}</td>
									<s:if test="#s.count%2 == 0 ">
										</tr>
									</s:if>
								</s:iterator>
							</table>
						</div>
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="2" align="center"><input name="" type="button" value="生成规格" class="btn" onclick="updateSpecification();" /></td>
			</tr>
		</table>
	</div>
</s:if>
<s:else>
	<div class="nt">
		<div class="nt_title">
			<label>销售属性</label>
		</div>
		<table>
			<tr height="30">
				<td width="90" align="right">SKU编码：</td>
				<td><input type="text" name="skuCode" id="skuCode" value="${inventoryCurrentStock.skuCode }" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				<td width="90" align="right">数量：</td>
				<td><input type="text" name="quantity" id="quantity" value="${inventoryCurrentStock.quantity }" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
			</tr>
			<tr height="30">
				<td width="90" align="right">价格：</td>
				<td><input type="text" name="price" id="price" value="${inventoryCurrentStock.price }" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
			</tr>
		</table>
	</div>
</s:else>