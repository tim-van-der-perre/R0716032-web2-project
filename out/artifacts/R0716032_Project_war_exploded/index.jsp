<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:when test="${not empty kotgenoten}">
      <section>
        <h2>Kot gaat corona proof</h2>
        <p>Deze website is bedoeld om de contacten van iedereen op kot bij te houden.
          Zo kan je zien wie in wiens bubbel zit en hoe mensen zich aan de maatregelen houden.</p>
      </section>
      <table>
        <thead>
          <tr>
            <th> naam </th>
            <th> laatste contact </th>
            <th> datum </th>
            <th> bubbelgrootte </th>
        </thead>
        <tbody>
        <c:forEach var="kotgenoot" items="${kotgenoten}">
        <tr>
          <td>${kotgenoot.naam}</td>
          <td>${kotgenoot.laatsteContact.naam}</td>
          <td>${kotgenoot.laatsteContact.datum}</td>
          <td>${kotgenoot.bubbelGrootte}</td>
        </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:when>

    <c:otherwise>
      <p>Er zijn geen kotgenoten.</p>
    </c:otherwise>

  </c:choose>
  <p>Degene met de grootste bubbel op dit moment is: ${grootstebubbel}</p>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>