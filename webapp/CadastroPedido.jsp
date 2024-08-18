<%-- 
    Document   : CadastroPedido
    Created on : 11 de ago de 2024, 16:08:45
    Author     : João Henrique
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.UsuarioDAO"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.MetodoPagamentoDAO"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.Usuario"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.MetodoPagamento"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.Pedido"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.PedidoDAO"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Pedido</title>
    </head>
    
    
    <body>
        <h1>Cadastro Pedido</h1>
        <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PedidoControlador" method="get">
            <input type="hidden" name="opcao" value="${opcao}" />
            <input type="hidden" name="idPedido" value="${idPedido}" />
            
            <p><label>Usuario: </label>
                    <select name="user_id">
                     <c:forEach var="usuario" items="${usuarios}">
                         <c:choose> 
                            
                            <c:when test="${usuario.idUsuario eq user_id}">
                                <option selected value="${usuario.idUsuario}">${usuario.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${usuario.idUsuario}">${usuario.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </p>
                
                  <p><label>Metodo Pagamento: </label>
                    <select name="pagamento_id">
                     <c:forEach var="pagamento" items="${pagamentos}">
                         <c:choose> 
                            
                            <c:when test="${pagamento.idPaga eq pagamento_id}">
                                <option selected value="${pagamento.idPaga}">${pagamento.nomePg}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${pagamento.idPaga}">${pagamento.nomePg}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </p>
                
               <p><label>Data de Registro do Pedido: </label><input type="date" name="data_pedido" value="${data_pedido}" size="40" /> </p>
            
            <td>
                <input type="submit"  value="Salvar" name="Salvar" />
            </td>
            
        </form>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PedidoControlador" method="get">
                <td>
                    <input type="submit"  value="Cancelar" name="Cancelar" />
                </td>
                <input type="hidden" name="opcao" value="${opcao}" />
            </form>
        ${mensagem}

        <table border =" 1">
            <c:if test="${not empty pedidos}">
                  <tr>
                      <th>Loop Pedido ID</th>
                      <th>Usuario</th>
                      <th>Metodo Pagamento</th>
                      <th>Data do Pedido</th>
                      <th>Alterar</th>
                      <th>Excluir</th>
                  </tr>
            </c:if>
            <c:forEach var="pedido" items="${pedidos}">
                <tr>
                    <td>${pedido.idPedido}</td>
                    <td>${pedido.user_id.nome}</td>
                    <td>${pedido.pagamento_id.nomePg}</td>
                    <td>${pedido.data_PedidoFormatado}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PedidoControlador" method="get">
                            <input type="hidden" name="idPedido" value="${pedido.idPedido}">
                            <input type="hidden" name="user_id" value="${pedido.user_id.idUsuario}">
                            <input type="hidden" name="pagamento_id" value="${pedido.pagamento_id.idPaga}">
                            <input type="hidden" name="data_pedido" value="${pedido.data_PedidoFormatado}">
                            <input type="hidden" name="opcao" value="editar">
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PedidoControlador" method="get">
                            <input type="hidden" name="idPedido" value="${pedido.idPedido}">
                            <input type="hidden" name="user_id" value="${pedido.user_id.idUsuario}">
                            <input type="hidden" name="pagamento_id" value="${pedido.pagamento_id.idPaga}">
                            <input type="hidden" name="data_pedido" value="${pedido.data_PedidoFormatado}">
                            <input type="hidden" name="opcao" value="excluir">
                            <button type="submit">Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
