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
    <section>
        <h2>Hier vind je een klein overzicht van ieders persoonlijke bubbel</h2>
        <p>Je ziet hier wie zich het meest en minst houd aan de opgelegde maatregelen. Na 2 weken verdwijnen mensen automatisch uit je overzicht.</p>
    </section>

    <form method="post" action = "KotInfo?command=switchKotgenoot" name="switchKotgenoot">
        <label for="kotgenootkeuze" place>wie wil je tonen in het overzicht: </label>
        <select onchange="switchKotgenoot.submit()" id="kotgenootkeuze" name="kotgenootkeuze">
            <option value="placeholder">${placeholder}</option>
            <option value="">iedereen</option>
            <option value="tim">tim</option>
            <option value="craenen">craenen</option>
            <option value="elisa">elisa</option>
            <option value="marie">marie</option>
            <option value="ali">ali</option>
            <option value="alix">alix</option>
        </select>
    </form>

    <c:choose>
        <c:when test="${not empty kotgenoten}">
            <table>
                <thead>
                <tr>
                    <th> naam </th>
                    <th> contact </th>
                    <th> datum </th>
                    <th> verwijderen </th>
                    <th> update </th>
                </tr>
                </thead>
                <c:choose>
                    <c:when test="${not empty requestcookie}">
                        <tbody>
                        <c:forEach var="contact" items="${requestcookie.contacten}">
                            <tr>
                                <td>${requestcookie.naam}</td>
                                <td>${contact.naam}</td>
                                <td>${contact.datum}</td>
                                <td><a href="KotInfo?command=verwijderNavraag&naam=${requestcookie.naam}&contact=${contact.naam}">Verwijder</a></td>
                                <td><a href="KotInfo?command=updateNaVraag&naam=${requestcookie.naam}&contact=${contact.naam}">update</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </c:when>
                    <c:otherwise>
                        <tbody>
                        <c:forEach var="kotgenoot" items="${kotgenoten}">
                            <c:forEach var="contact" items="${kotgenoot.contacten}">
                                <tr>
                                    <td>${kotgenoot.naam}</td>
                                    <td>${contact.naam}</td>
                                    <td>${contact.datum}</td>
                                    <td><a href="KotInfo?command=verwijderNavraag&naam=${kotgenoot.naam}&contact=${contact.naam}">Verwijder</a></td>
                                    <td><a href="KotInfo?command=updateNaVraag&naam=${kotgenoot.naam}&contact=${contact.naam}">update</a></td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                        </tbody>
                    </c:otherwise>
                </c:choose>
            </table>
        </c:when>
        <c:otherwise>
            <p>Blijbaar zijn er geen kotgenoten.</p>
        </c:otherwise>
    </c:choose>
    <section>
        <p>Degene met de grootste bubbel op dit moment is: ${grootstebubbel}</p>
    </section>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>
