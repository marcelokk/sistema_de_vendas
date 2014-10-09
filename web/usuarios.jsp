<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
    </head>

    <body>
        <jsp:useBean id="usuarios" type="java.util.List" scope="request"/>

    <center>
        <h1>Lista de Usuarios</h1>
            <table style="border-style: solid">
                <tr>
                    <th>Nome</th>
                    <th>Login</th>
                    <th>Telefone</th>
                    <th>Administrador</th>                
                    <th>Remover Usuario</th>
                </tr>

                <c:forEach items="${usuarios}" var="u" varStatus="i">
                    <tr>
                        <td>${u.nome}</td>
                        <td>${u.login}</td>                            
                        <td>${u.telefone}</td>
                        <td>${u.administrador}</td>
                        <td><a href="Servlet?acao=removeUsuario&index=${u.id}">remover</a></td>
                    </tr>                       
              </c:forEach>            
        </table>
        
        <a href="Servlet?acao=voltar">voltar</a>         
</center>    
</body>
</html>
