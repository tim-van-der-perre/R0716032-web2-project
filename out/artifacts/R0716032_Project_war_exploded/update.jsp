<%--
  Created by IntelliJ IDEA.
  User: timva
  Date: 25-11-2020
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width= device-width, initial-scale= 1.0">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Tracker - Home</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <section class="error">
        <c:if test="${not empty errors}">
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </c:if>
    </section>
    <h2>Datum van "  ${param.contact} " updaten</h2>
    <form action="KotInfo?command=update&naam=${param.naam}&contact=${param.contact}" method="POST" novalidate>
        <label for="datum">nieuwe datum: </label>
        <input type="date" name="datum" id="datum" required="" placeholder="datum">
        <input type="submit" value="Ja" id="knopform"/>
    </form>
    <p><a href="KotInfo?command=overview">Cancel</a> indien je ${param.contact} niet wil updaten</p>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>
