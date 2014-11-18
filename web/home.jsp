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
        <jsp:useBean id="lista_acai" type="java.util.List" scope="session"/>

    <center>
        <header id="top">
            <h1>Ola, ${currentUser.nome}</h1>

            <c:if test="${currentUser.administrador == 0}">
                <a href="Servlet?acao=carrinho">Carrinho de Compras</a>
                <a href="Servlet?acao=historico">Historico de Compras</a>
            </c:if>
            <a href="Servlet?acao=cadastro">Editar dados pessoais</a>

            <c:if test="${currentUser.administrador == 1}">
                <a href="Servlet?acao=estoque">Estoque</a>
                <a href="Servlet?acao=cadastrar_produtos">Cadastro de Produtos</a>
                <a href="Servlet?acao=usuarios">Usarios Cadastrados</a>
            </c:if>
            <a href="Servlet?acao=logout">Logout</a>      
        </header>           

        <c:if test="${currentUser.administrador == 1}">
            <h2>Produtos no Estoque</h2>
        </c:if>

        <c:if test="${currentUser.administrador == 0}">
            <h2>Personalize seu Produto</h2>
        </c:if>        

        <c:if test="${currentUser.administrador == 1}">
            <form method="POST" action="Servlet?acao=sugestao">             
            </c:if>

            <c:if test="${currentUser.administrador == 0}">
                <form method="POST" action="Servlet?acao=comprar">             
                </c:if>

                <h3>Sabor do Acai</h3>
                <c:forEach items="${lista_acai}" var="p" varStatus="i">
                    <c:if test="${i.index == '0'}">
                        <input type="radio" name="acai" value="${p.id}" checked="">${p.sabor}
                    </c:if>
                    <c:if test="${i.index != '0'}">
                        <input type="radio" name="acai" value="${p.id}">${p.sabor}
                    </c:if>
                </c:forEach>

                <h3>Adicionais</h3>
                <table style="border-style: solid">
                    <tr>
                        <th>Nome</th>
                        <c:if test="${currentUser.administrador == 1}">
                            <th>Quantidade</th>    
                        </c:if>
                        <th>Valor</th>
                        <c:if test="${currentUser.administrador == 1}">
                            <th>Status</th>    
                        </c:if>         
                        <c:if test="${currentUser.administrador == 0}">
                            <th>Descricao</th>
                        </c:if>                            
                        <th></th>
                        <c:if test="${currentUser.administrador == 1}">
                            <td>Remover Produto</td>
                        </c:if>                
                    </tr>

                    <c:forEach items="${produtos}" var="p" varStatus="i">
                        <c:if test="${p.quantidade > 0 && p.status == '0'}">                 
                            <tr>
                                <td>${p.nome}</td>
                                <c:if test="${currentUser.administrador == 1}">
                                    <td>${p.quantidade}</td>                           
                                </c:if>
                                <td>${p.valor}</td>
                                <c:if test="${currentUser.administrador == 1}">
                                    <td>${p.status}</td>
                                </c:if>

                                <c:if test="${currentUser.administrador == 0}">
                                    <td>${p.descricao}</td>
                                </c:if>

                                <td><input type="checkbox" name="checkbox${p.id}"></td>
                                    <c:if test="${currentUser.administrador == 1}">
                                    <td>
                                        <a href="Servlet?acao=descadastrar_produto&index=${p.id}">remover</a>
                                    </td>
                                </c:if>
                            </tr>                       
                        </c:if>
                    </c:forEach>            
                </table>
                <c:if test="${currentUser.administrador == 1}">
                    <input type="submit" value="Criar Sugestao" name="submit">                
                </c:if>
                <c:if test="${currentUser.administrador == 0}">
                    <input type="submit" value="Adicionar no Carrinho" name="submit">                
                </c:if>
            </form>
    </center>    
</body>
</html>
