<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Historico</title>
    </head>
    <body>
        <jsp:useBean id="produtos" type="java.util.List" scope="request"/>   
        <jsp:useBean id="valores" type="java.util.List" scope="request"/>   
        
        <h1>Seu Historico de Compras</h1>

        <table style="border-style: solid">
            <tr>
                <th>Nome</th>
                <th>Quantidade</th>
                <th>Valor</th>
            </tr>

            <c:forEach items="${produtos}" var="p" varStatus="i">
                <tr>
                    <td>${p}</td>
                    <td>1</td>                            
                    <td>${valores[i]}</td>
                </tr>                       
            </c:forEach>            
        </table>

        <a href="Servlet?acao=voltar">voltar</a>         

    </body>
</html>
