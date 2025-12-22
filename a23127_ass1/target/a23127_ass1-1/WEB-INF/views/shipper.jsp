<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head><title>Shipper Management</title></head>
    <body>
        <h2>Shipper Management</h2>

        <!-- Form Search -->
        <form method="post" action="${pageContext.request.contextPath}/shippers/search">
            Name: <input type="text" name="name"/>
            Phone: <input type="text" name="phone"/>
            <button type="submit">Search</button>
            <a href="${pageContext.request.contextPath}/shippers">Reset</a>
        </form>
        <hr/>

        <!-- Form Add / Edit -->
        <h3><c:if test="${shipper.shipperID == null}">Add New Shipper</c:if>
            <c:if test="${shipper.shipperID != null}">Edit Shipper</c:if></h3>
        <hr/>

        <!-- List -->
        <h3>Shippers List</h3>
        <table border="1" cellpadding="6" cellspacing="0">
            <table border="1">
                <tr><th>ID</th><th>Name</th><th>Description</th><th>Actions</th></tr>
                        <c:forEach items="${list}" var="c">
                    <tr>
                        <td>${s.shipperID}</td>
                        <td>${s.shipperName}</td>
                        <td>${s.phone}</td>
                    </tr>
                </c:forEach>
            </table>

    </body>
</html>
