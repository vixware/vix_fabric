<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<table class="table table-striped table-forum">
		<thead>
				<th colspan="8" style="text-align: center;">${titleDate }商品销量排行 TOP10</th>
		</thead>
		<tbody>
			<tr>
				<td class="text-center" style="width: 60px;">排名</td>
				<td class="text-center">商品名称 </td>
				<td class="text-center hidden-xs hidden-sm">数量</td>
				<td class="text-center hidden-xs hidden-sm">金额(元)</td>
				<td class="text-center hidden-xs hidden-sm">较昨日销售金额</td>
			</tr>
			<s:if test="null==tBeanEArr || tBeanEArr.isEmpty()">  
				<tr>
				  <td colspan="8" style="text-align: center;color:red;"> 暂无数据 </td>
				</tr>
			</s:if>
			<s:else>
				<s:iterator value="tBeanEArr" var="tBean" status="vs">	  
					 <tr>
						<td class="text-center" style="width: 60px;">${vs.index+1 }</td>
						<td class="text-center">${tBean.colA} </td>
						<td class="text-center hidden-xs hidden-sm"> ${tBean.colB} </td>
						<td class="text-center hidden-xs hidden-sm">  ${tBean.colC} </td>
						<td class="text-center hidden-xs hidden-sm">  ${tBean.colD}% </td>
					</tr>		
				</s:iterator>
			</s:else>
		</tbody>
</table>	



<!-- <td class="text-center hidden-xs hidden-sm">较昨日销售金额</td>	 -->							
