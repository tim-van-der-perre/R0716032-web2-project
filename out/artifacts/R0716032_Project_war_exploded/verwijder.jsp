<%--
  Created by IntelliJ IDEA.
  User: timva
  Date: 28-10-2020
  Time: 16:30
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
    <h2>Bevestiging</h2>
    <p>Ben je zeker dat contact:  ${param.contact} verwijderd mag worden?</p>
    <form action="KotInfo?command=delete&naam=${param.naam}&contact=${param.contact}" method="POST">
        <input type="submit" value="Ja"/>
    </form>
    <p><a href="KotInfo?command=overview">Cancel</a> indien je ${param.contact} niet wil verwijderen</p>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>