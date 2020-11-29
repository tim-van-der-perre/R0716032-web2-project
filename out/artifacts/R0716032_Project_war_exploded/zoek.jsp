<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.model.Contact" %>
<%@ page import="domain.db.kotgenotenDB" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="domain.model.Kotgenoot" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Collection" %>

<%--
  Created by IntelliJ IDEA.
  User: timva
  Date: 4/10/2020
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>


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
        <c:if test="${not empty error}">
            <ul>
                <li>${error}</li>
            </ul>
        </c:if>
    </section>
    <section>
        <h2>contacten opzoeken</h2>
        <p>Hier kan je contacten opzoeken en er wat praktische info over terugvinden.</p>
    </section>

    <form method="post" action="KotInfo?command=zoek" novalidate>
        <label for="contactnaam">naam van het contact: </label>
        <input type="text" name="contactnaam" id="contactnaam" value="" required placeholder="Naam">

        <label for="knopform"></label>
        <input type="submit" value="zoek" id="knopform">
    </form>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>
