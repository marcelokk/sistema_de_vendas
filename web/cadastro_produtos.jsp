<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Produtos</title>
        <script type="text/javascript">            
            function Numero(n){
                var caractervalido = "0123456789";
                var numeros = true;
                var caracter;
                for (i = 0; i < n.length && numeros == true; i++) { 
                    caracter = n.charAt(i); 
                    if (caractervalido.indexOf(caracter) == -1) {
                        numeros = false;
                    }
                }
                return numeros;
            }
           
            function valida(form) {
                if (!Numero(form.quantidade.value)) {
                    alert("Preencha a quantidade apenas com números");
                    form.quantidade.focus();
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <jsp:useBean id="currentProduto" type="model.Componente" scope="session"/>            
        <jsp:useBean id="mensagem" type="String" scope="request"/>

    <center>
        <h1>${mensagem}</h1>

        <form method="POST" action="Servlet?acao=cadastrar_produtos"  onsubmit="return valida(this);">
            <table border="1">            
                <tr>
                    <td>Nome</td>
                    <td><input type="text" required="true" name="nome" value="${currentProduto.nome}"></td>
                </tr>

                <tr>
                    <td>Quantidade em Estoque</td>
                    <td><input type="text" required="true" name="quantidade" value="${currentProduto.quantidade}"></td>
                </tr>

                <tr>
                    <td>Descricao Geral</td>
                    <td><input type="text" required="true" name="descricao" value="${currentProduto.descricao}"></td>
                </tr>

                <tr>
                    <td>Valor</td>
                    <td><input type="text" required="true" name="valor" value="${currentProduto.valor}"></td>
                </tr>         

                <tr>
                    <td>Status</td>
                    <td>
                        <select name="status">
                            <option value="1" label="Disponivel" ${currentProduto.status == "1" ? 'selected="true"' : '' }>Disponível</option>
                            <option value="0" label="Finalizado" ${currentProduto.status == "0" ? 'selected="true"' : '' }>Finalizado</option>                                
                        </select>
                    </td>
                </tr>            
            </table>
            <input type="submit" value="salvar">
        </form>

        <a href="Servlet?acao=voltar">voltar</a>         

    </center>
</body>
</html>
