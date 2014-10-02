<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
<head>
    
<jsp:useBean id="titulo" type="String" scope="session"/>
<jsp:useBean id="nome_do_site" type="String" scope="session"/>
    
<meta charset="utf-8">
<title>${titulo}</title>
                
        <script type="text/javascript">	
            function valida(form) {	 	
                var senha = /^.*(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).*$/;
                if (!senha.test(form.password.value)) {
                    alert("A senha deve conter pelo menos 1 letra maiúscula, 1 caractere especial (não alfanumérico), 1 dígito e 1 letra minúscula");
                    form.password.focus();
                    return false;
                }
                else if (form.password.value.length < 5) {
                	alert("A senha deve ter no mínimo cinco caracteres");
                    form.password.focus();
                    return false;
                }

                var filtro_mail	= /^.+@\w+\.\w{2,3}$/;
                if(!filtro_mail.test(form.login.value))	{	
                    alert("Preencha o e-mail corretamente.");	
                    form.login.focus();	
                    return false;	
                }	
                return true;	
            }	
        </script>		
</head>
<body>

    <center>
        <h1>${nome_do_site}</h1>        
        
        <form action="Servlet?acao=logar" method="POST" onsubmit="return valida(this);">
        <table>
            <tr>
                <td>Login</td>
                <td><input type="text" name="login" required="true"></td>
            </tr>

            <tr>
                <td>Senha</td>
                <td><input type="password" name="password" required="true" maxlength="8"></td>
            </tr>
        </table>
            <input type="submit" name="submit" value="enviar">
        </form>
            
        <p>Ainda nao e' cadastrado? Entao cadastre-se em <a href="Servlet?acao=cadastro">Cadastro</a></p>
    </center>
</body>
</html>
