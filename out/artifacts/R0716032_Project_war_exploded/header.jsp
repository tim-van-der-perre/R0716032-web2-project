<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <h1>CoronaTracker</h1>
    <nav>
        <ul>
            <li ${param.actual eq 'Home'?"class =current":""}>
                <a href="KotInfo?command=home">Home</a></li>
            <li ${param.actual eq 'formulier'?"class = current":""}>
                <a href="formulier.jsp">Voeg Toe</a></li>
            <li ${param.actual eq 'Overzicht'?"class = current":""}>
                <a href="KotInfo?command=overzicht">Overzicht</a></li>
            <li ${param.actual eq 'zoek'?"class = current":""}>
                <a href="zoek.jsp">Zoek</a></li>
        </ul>
    </nav>
</header>