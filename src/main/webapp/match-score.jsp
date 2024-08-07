<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="persistence.entity.Match" %>
<%@ page import="java.util.UUID" %>
<style><%@include file="css/style.css"%></style>
<html>
    <head>
        <title>Score of match</title>
    </head>
    <body class="sheet">
        <jsp:include page="top-panel.html" />
        <% Match match = (Match) request.getAttribute("match"); %>
        <% UUID uuid = (UUID) request.getAttribute("uuid"); %>
        <% if (!match.getMatchScore().isGameOver()) { %>
            <table>
                <thead>
                    <tr>
                        <td>Игрок</td>
                        <td>Score</td>
                        <td>Point</td>
                        <td>Game</td>
                        <td>Set</td>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td><%= match.getPlayerOne().getName()%></td>
                        <td><%=match.getMatchScore().getScore().first()%></td>
                        <td><%=match.getMatchScore().getPoint().first()%></td>
                        <td><%=match.getMatchScore().getGame().first()%></td>
                        <td><%=match.getMatchScore().getSet().first()%></td>
                        <td><form action="match-score?uuid=<%=uuid%>" method="POST">
                            <button name="player" value="FIRST">Засчитать балл первому игроку</button>
                        </form></td>
                    </tr>
                    <tr>
                        <td><%= match.getPlayerTwo().getName()%></td>
                        <td><%= match.getMatchScore().getScore().second()%></td>
                        <td><%= match.getMatchScore().getPoint().second()%></td>
                        <td><%= match.getMatchScore().getGame().second()%></td>
                        <td><%= match.getMatchScore().getSet().second()%></td>
                        <td><form action="match-score?uuid=<%=uuid%>" method="POST">
                            <button name="player" value="SECOND" >Засчитать балл второму игроку</button>
                        </form></td>
                    </tr>
                </tbody>
            </table>
        <%} else {%>
        <h4>Со счетом <%= match.getMatchScore().getSet().first()%>:<%=match.getMatchScore().getSet().second() %>
            победил игрок <%= match.getWinner().getName() %></h4>
        <%}%>
    </body>
</html>
