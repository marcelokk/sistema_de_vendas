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
                    <td>${p.produto.nome}</td> 
                    <td>${p.quantidade}</td>                    
                    <td>${p.produto.valor}</td>
                    <td><a href="Servlet?acao=remover&index=${i.index}">remover</a></td>
                </tr>
            </c:forEach>   

        </table>

        <p>Valor Total a ser pago: ${valor}</p>
        <c:if test="${aux == 1}">
          <p><a href="Servlet?acao=compra_finalizada">Efetuar Compra</a></p>  
        </c:if>
        
        <p><a href="home.jsp">Voltar e Continuar Comprando</a></p>
    </center>
</body>
</html>