<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Workshop Registration</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 24px; }
    .error { color: #c00; }
    .ok { color: #090; }
    table { border-collapse: collapse; }
    th, td { border: 1px solid #ccc; padding: 6px 10px; }
    a, button { margin-right: 8px; }
  </style>
</head>
<body>

<c:if test="${not empty msg}">
  <p class="ok">${msg}</p>
</c:if>

<c:if test="${view eq 'register'}">
 <form:form method="post" modelAttribute="registrant" action="${pageContext.request.contextPath}/register">
  <p>Full Name:<br/>
    <form:input path="fullName"
                required="required"
                minlength="3" maxlength="60"
                pattern="[\p{L} .'-]{3,60}"/>
    <br/><form:errors path="fullName" cssClass="error"/>
  </p>

  <p>Email:<br/>
    <form:input path="email" type="email"
                required="required" maxlength="120"/>
    <br/><form:errors path="email" cssClass="error"/>
  </p>

  <p>Workshop Title:<br/>
    <form:select path="workshopTitle" required="required">
      <form:option value="" label="-- Choose workshop --"/>
      <form:options items="${workshopTitles}"/>
    </form:select>
    <br/><form:errors path="workshopTitle" cssClass="error"/>
  </p>
  <button type="submit">Submit</button>
</form:form>

</c:if>

<c:if test="${view eq 'confirm'}">
  <h2>Thank you for registering for the workshop</h2>
  <ul>
    <li><b>Full Name:</b> ${saved.fullName}</li>
    <li><b>Email:</b> ${saved.email}</li>
    <li><b>Workshop:</b> ${saved.workshopTitle}</li>
  </ul>
  <p>
    <a href="<c:url value='/registrants'/>">Go to list</a> |
    <a href="<c:url value='/register'/>">Register another</a>
  </p>
</c:if>

<c:if test="${view eq 'list'}">
  <h2>All Registrants</h2>
  <table>
    <tr>
      <th>ID</th><th>Full Name</th><th>Email</th><th>Workshop</th><th>Actions</th>
    </tr>
    <c:forEach var="r" items="${items}">
      <tr>
        <td>${r.id}</td>
        <td>${r.fullName}</td>
        <td>${r.email}</td>
        <td>${r.workshopTitle}</td>
        <td>
          <a href="<c:url value='/registrants/edit/${r.id}'/>">Edit</a>
          <form action="<c:url value='/registrants/delete/${r.id}'/>" method="post" style="display:inline">
            <button type="submit" onclick="return confirm('Delete this registrant?')">Delete</button>
          </form>
        </td>
      </tr>
    </c:forEach>
  </table>
  <p><a href="<c:url value='/register'/>">New Registration</a></p>
</c:if>

<c:if test="${view eq 'edit'}">
  <h2>Edit Registrant</h2>
  <form:form method="post" modelAttribute="registrant" action="${pageContext.request.contextPath}/register">
  <p>Full Name:<br/>
    <form:input path="fullName"
                required="required"
                minlength="3" maxlength="60"
                pattern="[\p{L} .'-]{3,60}"/>
    <br/><form:errors path="fullName" cssClass="error"/>
  </p>

  <p>Email:<br/>
    <form:input path="email" type="email"
                required="required" maxlength="120"/>
    <br/><form:errors path="email" cssClass="error"/>
  </p>

  <p>Workshop Title:<br/>
    <form:select path="workshopTitle" required="required">
      <form:option value="" label="-- choose workshop --"/>
      <form:options items="${workshopTitles}"/>
    </form:select>
    <br/><form:errors path="workshopTitle" cssClass="error"/>
  </p>
  <button type="submit">Submit</button>
</form:form>

</c:if>

</body>
</html>
