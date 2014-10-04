<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Cadastro</title>

        <script type="text/javascript">
            function Letra(l){
                var caractervalido = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
                var letras = true;
                var caracter;
                for (i = 0; i < l.length && letras == true; i++) { 
                    caracter = l.charAt(i); 
                    if (caractervalido.indexOf(caracter) == -1) {
                        letras = false;
                    }
                }
                return letras;
            }
            
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
                if (!Letra(form.nome.value)) {
                    alert("Preencha o nome apenas com letras");
                    form.nome.focus();
                    return false;
                }
          
                if (!Letra(form.cidade.value)) {
                    alert("Preencha a cidade apenas com letras");
                    form.cidade.focus();
                    return false;
                }
                
                var telefone = /\(\d{2}\)\ \d{4}-\d{4}/;
                if (!telefone.test(form.telefone.value)) {
                    alert("Preencha o telefone apenas com numeros e no formato (XX)XXXX-XXXX");
                    form.telefone.focus();
                    return false;
                }
                
                var senha = /^.*(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).*$/;
                if (!senha.test(form.password.value)) {
                    alert("A senha deve conter pelo menos 1 letra maiúscula, 1 caractere especial (não alfanumérico), 1 dígito e 1 letra minúscula");
                    form.password.focus();
                    return false;
                }
                
                if (form.telefone.value.length < 10) {
                    alert("O telefone deve ter mais caracteres");
                    form.telefone.focus();
                    return false;
                }

                var filtroemail = /^.+@\w+\.\w{2,3}$/;
                if (!filtroemail.test(form.login.value)) {
                    alert("Preencha o e-mail corretamente.");
                    form.login.focus();
                    return false;
                }

                if (form.password.value.length < 5) {
                    alert("A senha deve ter no mínimo cinco caracteres");
                    form.password.focus();
                    return false;
                }

                if (form.password.value != form.cpassword.value) {
                    alert("A senha e a confirmação devem ser iguais.");
                    form.cpassword.focus();
                    return false;
                }
            }
        </script>        

    </head>
    <body>

        <jsp:useBean id="mensagem" type="String" scope="request"/>
    <center>
        <h1>${mensagem}</h1>

        <form action="Servlet?acao=cadastrar" method="POST" onsubmit="return valida(this);">
            <table>
                <tr>
                    <td>Nome completo:</td>
                    <td><input type="text" name="nome" onkeyup="this.value = this.value.toUpperCase()" required="true" value="${currentUser.nome}"></td>
                </tr>

                <tr>
                    <td>Cidade:</td>
                    <td><input type="text" name="cidade" onkeyup="this.value = this.value.toUpperCase()" required="true" value="${currentUser.cidade}"></td>
                </tr>

                <tr>
                    <td>Estado:</td>
                    <td>
                        <select name="estado">
                            <option value="AC" label="AC">AC</option>
                            <option value="AL" label="AL">AL</option>
                            <option value="AP" label="AP">AP</option>
                            <option value="AM" label="AM">AM</option>
                            <option value="BA" label="BA">BA</option>
                            <option value="CE" label="CE">CE</option>
                            <option value="DF" label="DF">DF</option>
                            <option value="ES" label="ES">ES</option>
                            <option value="GO" label="GO">GO</option>
                            <option value="MA" label="MA">MA</option>
                            <option value="MT" label="MT">MT</option>
                            <option value="MS" label="MS">MS</option>
                            <option value="MG" label="MG">MG</option>
                            <option value="PA" label="PA">PA</option>
                            <option value="PB" label="PB">PB</option>
                            <option value="PR" label="PR">PR</option>
                            <option value="PE" label="PE">PE</option>
                            <option value="PI" label="PI">PI</option>
                            <option value="RJ" label="RJ">RJ</option>
                            <option value="RN" label="RN">RN</option>
                            <option value="RS" label="RS">RS</option>
                            <option value="RO" label="RO">RO</option>
                            <option value="RR" label="RR">RR</option>
                            <option value="SC" label="SC">SC</option>
                            <option value="SP" label="SP">SP</option>
                            <option value="SE" label="SE">SE</option>
                            <option value="TO" label="TO">TO</option>           
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>Telefone:</td>
                    <td><input type="text" name="telefone" maxlength="14"  placeholder="(XX) XXXX-XXXX" required="true" value="${currentUser.telefone}"></td>
                </tr>

                <tr>
                    <td>Login:</td>
                    <td><input type="text" name="login" placeholder="seu email aqui" required="true" value="${currentUser.login}"></td>
                </tr>

                <tr>
                    <td>Senha:</td>
                    <td><input type="password" name="password" required="true" maxlength="8"></td>
                </tr>

                <tr>
                    <td>Confirmacao de Senha</td>
                    <td><input type="password" name="cpassword" required="true" maxlength="8"></td>
                </tr>

            </table>
            <input type="submit" name="submit" value="Cadastrar">
        </form>

        <form action="Servlet?acao=voltar" method="POST">
            <input type="submit" name="voltar" value="voltar">
        </form>
    </center> 
</body>
</html>
