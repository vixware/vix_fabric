<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<c:set var="vix" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	function loadContent(url) {
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	}
</script>
