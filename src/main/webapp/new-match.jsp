<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="css/style.css"%></style>
<html>
    <head>
        <title>New match</title>
    </head>
    <body class="sheet">
        <jsp:include page="top-panel.html" />
        <% String message = (String) request.getAttribute("message"); %>

        <div class="start-form">
            <form action="new-match" method="POST">
                <h3>Новый матч</h3>
                Первый игрок:
                <br><input type='text' name='first-name'><br>
                Второй игрок:
                <br><input type='text' name='second-name'><br>
                <br><input type='submit' value='Начать'><br>
                <br><% if(message != null) { %> <%=message%> <% } %><br>
            </form>
        </div>
    </body>
</html>
