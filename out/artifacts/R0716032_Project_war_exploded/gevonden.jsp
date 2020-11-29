<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.model.Kotgenoot" %>
<%@ page import="java.util.Collection" %>
<%@ page import="domain.model.Contact" %><%--
  Created by IntelliJ IDEA.
  User: timva
  Date: 4/10/2020
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width= device-width, initial-scale= 1.0">
    <link rel="stylesheet"  type="text/css" href="css/style.css">
    <title>Tracker - Home</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <c:choose>
        <c:when test="${gevondencontact == null}">
            <h2>Helaas!</h2>
            <p>We hebben het contact met naam "${param.contactnaam}" niet kunnen terugvinden.
        </c:when>
        <c:otherwise>
            <h2>Gevonden!</h2>
            <p>Het contact dat u zocht is: ${gevondencontact.naam} </p>
            <p>Hier wat algemene info over hem/haar: </p>
            <table>
                <thead>
                <tr>
                    <th> contact </th>
                    <th> contact van </th>
                    <th> datum van contact </th>
                    <th>laatste contact? </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${gevondencontact.naam}</td>
                    <td>${gevondenkotgenoot.naam}</td>
                    <td>${gevondencontact.datum}</td>
                    <td>${gevondenkotgenoot.laatsteContact eq gevondencontact? "ja": "nee"}</td>
                </tr>
                </tbody>

            </table>
        </c:otherwise>
    </c:choose>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>