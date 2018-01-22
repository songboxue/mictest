<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>MICCN Test Framework</h1>
<div class="lists">
    <c:forEach items="${mcList}" var="mc">
        <span>ID:${mc.caseId}</span>
        <span>标题：${mc.caseName}</span>
        <span>描述：${mc.caseDesc}</span>
        <span>url：${mc.dataUrl}</span>
        <span>发送请求：${mc.dataSend}</span>
    </c:forEach>
</div>
</body>
</html>
