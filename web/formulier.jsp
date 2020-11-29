<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:if test="${not empty errors}">
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </c:if>
    </section>
    <section>
        <h2>Mensen toevoegen aan je bubbel</h2>
        <p>Hier hou je bij met wie je contact hebt gehad.</p>
    </section>
    <form method="post" action="KotInfo?command=formulier" novalidate>
        <label for="naam">Je eigen naam: </label>
        <input type="text" name="naam" id="naam" value="" required placeholder="Naam">

        <label for="contact">Naam van de indringer: </label>
        <input type="text" name="contact" id="contact" value="" required placeholder="andereNaam">

        <label for="datum">Datum van contact: </label>
        <input type="date" name="datum" id="datum" required="" placeholder="datum">

        <label for="knopform"></label>
        <input type="submit" value="Versturen" id="knopform">
    </form>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>