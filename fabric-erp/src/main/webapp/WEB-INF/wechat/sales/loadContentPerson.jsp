<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<strong id="contactPersonSelect">
	<s:select id="contactPersonId" headerKey="" headerValue="--请选择--" list="%{contactPersonList}" listValue="name" listKey="id" name="salesOrder.contactPerson.id" value="%{salesOrder.contactPerson.id}" multiple="false" theme="simple"></s:select>
</strong>
					