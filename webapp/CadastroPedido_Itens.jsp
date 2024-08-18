<%-- 
    Document   : CadastroPedido_Itens
    Created on : 11 de ago de 2024, 18:59:35
    Author     : João Henrique
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.PedidoDAO"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.GameDAO"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.Pedido"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.Game"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.Pedido_ItensDAO"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.Pedido_Itens"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Itens do Pedido</title>
    </head>


    <body>
        <h1>Cadastro Itens do Pedido</h1>
        <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/Pedido_ItensControlador" method="get">
            <input type="hidden" name="opcao" value="${opcao}" />
            <input type="hidden" name="idItem" value="${idItem}" />

            <p><label>Pedido:</label>
                <select name="pedido_id">
                    <c:forEach var="pedido" items="${pedidos}">
                        <c:choose> 

                            <c:when test="${pedido.idPedido eq pedido_id}">
                                <option selected value="${pedido.idPedido}">${pedido.idPedido}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${pedido.idPedido}">${pedido.idPedido}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </p>

            <p><label>Game:</label>
                <select name="game_id">
                    <c:forEach var="game" items="${games}">
                        <c:choose> 

                            <c:when test="${game.idGame eq game_id}">
                                <option selected value="${game.idGame}">${game.titulo}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${game.idGame}">${game.titulo}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </p>


            <p><label>Quantidade: </label><input type="text" name="quantidade" value="${quantidade}" size="40" /> </p>
            <p><label>Valor: </label><input type="text" name="valor" value="${valor}" size="40" /> </p>

            <td>
                <input type="submit"  value="Salvar" name="Salvar" />
            </td>

        </form>
        <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/Pedido_ItensControlador" method="get">
            <td>
                <input type="submit"  value="Cancelar" name="Cancelar" />
            </td>
            <input type="hidden" name="opcao" value="${opcao}" />
        </form>
        ${mensagem}

        <table border =" 1">
            <c:if test="${not empty pedido_items}">
                <tr>
                    <th>Loop Pedido Item ID</th>
                    <th>Codigo do Pedido</th>
                    <th>Game</th>
                    <th>Quantidade</th>
                    <th>Valor</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </c:if>
            <c:forEach var="pedido_item" items="${pedido_items}">
                <tr>
                    <td>${pedido_item.idItem}</td>
                    <td>${pedido_item.pedido_id.idPedido}</td>
                    <td>${pedido_item.game_id.titulo}</td>
                    <td>${pedido_item.quantidade}</td>
                    <td>${pedido_item.valor}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/Pedido_ItensControlador" method="get">
                            <input type="hidden" name="idItem" value="${pedido_item.idItem}">
                            <input type="hidden" name="pedido_id" value="${pedido_item.pedido_id.idPedido}">
                            <input type="hidden" name="game_id" value="${pedido_item.game_id.idGame}">
                            <input type="hidden" name="quantidade" value="${pedido_item.quantidade}">
                            <input type="hidden" name="valor" value="${pedido_item.valor}">
                            <input type="hidden" name="opcao" value="editar">
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/Pedido_ItensControlador" method="get">
                            <input type="hidden" name="idItem" value="${pedido_item.idItem}">
                            <input type="hidden" name="pedido_id" value="${pedido_item.pedido_id.idPedido}">
                            <input type="hidden" name="game_id" value="${pedido_item.game_id.idGame}">
                            <input type="hidden" name="quantidade" value="${pedido_item.quantidade}">
                            <input type="hidden" name="valor" value="${pedido_item.valor}">
                            <input type="hidden" name="opcao" value="excluir">
                            <button type="submit">Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>