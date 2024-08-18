<%-- 
    Document   : menu
    Created on : 28 de jul de 2024, 17:19:41
    Author     : João Henrique
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        
        <link rel = "shortcut icon" type="image/png" href="${pageContext.request.contextPath}/Assets/FAVICON_LOOP.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/estilo.css?v=${System.currentTimeMillis()}">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema Loop</title>
    </head>
    
    <body>
        <nav>
            <ul class="menu">
                <img class= "logonav" src="${pageContext.request.contextPath}/Assets/Logo_Loop.svg" alt="Logo_Loop">
                <li>
                    <a href="${pageContext.request.contextPath}/index.jsp"> Home</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}${URL_BASE}/UsuarioControlador?opcao=cancelar">Usuario</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}${URL_BASE}/GameControlador?opcao=cancelar">Game</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}${URL_BASE}/AvaliacoesControlador?opcao=cancelar">Avaliacoes</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}${URL_BASE}/DesenvolvedoraControlador?opcao=cancelar">Desenvolvedora</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}${URL_BASE}/MetodoPagamentoControlador?opcao=cancelar">Metodo Pagamento</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}${URL_BASE}/CategoriaControlador?opcao=cancelar">Categoria</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}${URL_BASE}/Game_Associa_CategoriaControlador?opcao=cancelar">Game Categoria</a>
                </li>
                 <li>
                    <a href="${pageContext.request.contextPath}${URL_BASE}/ForumControlador?opcao=cancelar"> Foruns</a>
                </li>
                 <li>
                    <a href="${pageContext.request.contextPath}${URL_BASE}/PedidoControlador?opcao=cancelar"> Pedido</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}${URL_BASE}/Pedido_ItensControlador?opcao=cancelar"> Pedido Item</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/login.jsp"> Login</a>
                </li>
            </ul>
        </nav>
    </body>
</html>
