<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estoque</title>
    </head>
    <body>
    <jsp:useBean id="produtos" type="java.util.List" scope="session"/>   

    <center>
        <h1>Produtos no Estoque</h1>

        <table border="1">
            <tr>
                <th>Nome</th>
                <th>Quantidade</th>
                <th>Valor</th>
            </tr>

            <c:forEach items="${produtos}" var="p" varStatus="i">
                <tr>
                    <td>${p.nome}</td> 
                    <td>${p.quantidade}</td>
                    <td>${p.valor}</td>
                </tr>
            </c:forEach>   

        </table>

        <a href="Servlet?acao=voltar">voltar</a>         

    </center>
</body>
</html>
