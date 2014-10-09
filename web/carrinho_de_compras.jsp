<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrinho de Compras</title>
    </head>
    <body>

        <jsp:useBean id="valor" type="String" scope="session"/>       
        <jsp:useBean id="listaCompras" type="java.util.List" scope="session"/>   
        <jsp:useBean id="aux" type="Integer" scope="session"/>

    <center>
        <h1>Seu Carrinho de Compras</h1>

        <table border="1">
            <tr>
                <th>Nome</th>
                <th>Quantidade</th>
                <th>Valor</th>
                <th></th>
            </tr>

            <c:forEach items="${listaCompras}" var="p" varStatus="i">
                <tr>
                    <td>${p.descricao()}</td> 
                    <td>1</td>
                    <td>${p.custo()}</td>
                    <td>
                        <form action="Servlet?acao=remover&index=${i.index}" method="POST">
                            <input type="submit" name="submit" value="remover">    
                        </form>
                    </td>
                </tr>
            </c:forEach>   

        </table>

        <p>Valor Total a ser pago: ${valor}</p>
        <c:if test="${aux == 1}">
            <form action="Servlet?acao=compra_finalizada" method="POST">
                <input type="submit" value="Efetuar Compra">
            </form>
        </c:if>

        <form action="Servlet?acao=voltar" method="POST">
            <input type="submit" name="submit" value="Voltar e Continuar Comprando">
        </form>  

    </center>
</body>
</html>
