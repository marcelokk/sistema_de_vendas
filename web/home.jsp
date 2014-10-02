<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    
    <body>
       <jsp:useBean id="currentUser" type="model.Usuario" scope="session"/>
       <jsp:useBean id="produtos" type="java.util.List" scope="session"/>
      
        <a href="Servlet?acao=logout">Logout</a>
    <center>
        <h1>Ola, ${currentUser.nome}</h1>
        
        <a href="Servlet?acao=carrinho">Carrinho de compras</a>
        <a href="Servlet?acao=editar_dados_pessoais">Editar dados pessoais</a>
        
        <c:if test="${currentUser.administrador == 1}">
            <a href="Servlet?acao=estoque">Estoque</a>
            <a href="Servlet?acao=cadastrar_produtos">Cadastro de Produtos</a>
        </c:if>
        
        <h2>Produtos disponiveis</h2>
        <table style="border-style: solid">
            <tr>
                <th>Categoria</th>
                <th>Nome do Fabricante</th>
                <th>Valor</th>
                <th></th>
            </tr>
            
            <c:forEach items="${produtos}" var="p">
            	<c:if test="${p.quantidade > 0 && p.status == '1'}">
	                <tr>
	                    <td>${p.categoria}</td>
	                    <td>${p.nome}</td>
	                    <td>${p.valor}</td>
	                    <td><a href="Servlet?acao=detalhes&id=${p.id}">Detalhes</a> <a href="Servlet?acao=alterar_imagem&id=${p.id}">Alterar imagem</a></td>
	                </tr>
                </c:if>

            </c:forEach>            

        </table>      
        
    </center>    
</body>
</html>
