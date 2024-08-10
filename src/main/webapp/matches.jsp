<%@ page import="persistence.entity.Match" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="css/style.css"%></style>
<html>
    <head>
        <title>Matches</title>
    </head>
    <body class="sheet">
        <jsp:include page="top-panel.html"/>

        <% List<Match> matches = (List<Match>) request.getAttribute("matches"); %>
        <% int numberOfPages = (int) request.getAttribute("numberOfPages"); %>
        <% String name = (String) request.getAttribute("filter_by_player_name"); %>
        <% if (name == null) name = ""; %>
        <form action="matches" method="GET">
            Найти матчи по имени игрока:
            <input type="hidden" name="page" value="0">
            <br><input type='text' name='filter_by_player_name'><br>
            <br><input type='submit' value='Найти'><br>
        </form>
        <% String message = (String) request.getAttribute("message"); %>
        <% if (message != null) { %>
        <%=message%>
        <% } else {%>
            <table>
                <% for (Match match : matches) { %>
                    <tr>
                        <td>Игрок 1: <%=match.getPlayerOne().getName()%></td>
                        <td>Игрок 2: <%=match.getPlayerTwo().getName()%></td>
                        <td>Победитель: <%=match.getWinner().getName()%></td>
                    </tr>
                <% } %>
            </table>
            <% for (int i = 0; i < numberOfPages; i++) {%>
            <a href="matches?page=<%=i%>&filter_by_player_name=<%=name%>"><%=i + 1%></a>
            <% } %>
        <%}%>
    </body>
</html>
