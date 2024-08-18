<%-- 
    Document   : Cadastro_Game_Associa_Categoria
    Created on : 4 de ago de 2024, 10:50:07
    Author     : João Henrique
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.Game_Associa_Categoria"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.Game_Associa_CategoriaDAO"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.CategoriaDAO"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.GameDAO"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.Categoria"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.Game"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Categoria Game</title>
    </head>
    
    
    <body>
        <h1>Cadastro Categoria Game</h1>
        <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/Game_Associa_CategoriaControlador" method="get">
            <input type="hidden" name="opcao" value="${opcao}" />
            <input type="hidden" name="idAssocia" value="${idAssocia}" />
            
             <p><label>Game:</label>
                    <select name="gameAssocia">
                     <c:forEach var="game" items="${games}">
                         <c:choose> 
                            
                            <c:when test="${game.idGame eq gameAssocia}">
                                <option selected value="${game.idGame}">${game.titulo}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${game.idGame}">${game.titulo}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </p>
                
                
                 <p><label>Categoria: </label>
                    <select name="categoriaAssocia">
                     <c:forEach var="categoria" items="${categorias}">
                         <c:choose> 
                            
                            <c:when test="${categoria.idCategoria eq categoriaAssocia}">
                                <option selected value="${categoria.idCategoria}">${categoria.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${categoria.idCategoria}">${categoria.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </p>
                
            <p><label>Quantidade de Players: </label><input type="number" name="qtdPlayers" value="${qtdPlayers}" size="40" /> </p>
            
            <td>
                <input type="submit"  value="Salvar" name="Salvar" />
            </td>
            
        </form>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/Game_Associa_CategoriaControlador" method="get">
                <td>
                    <input type="submit"  value="Cancelar" name="Cancelar" />
                </td>
                <input type="hidden" name="opcao" value="${opcao}" />
            </form>
        ${mensagem}

        <table border =" 1">
            <c:if test="${not empty game_associa_categorias}">
                  <tr>
                      <th>Game Categoria ID</th>
                      <th>Game</th>
                      <th>Categoria</th>
                      <th>Quantidade de Players</th>
                      <th>Alterar</th>
                      <th>Excluir</th>
                  </tr>
            </c:if>
            <c:forEach var="game_associa_categoria"  items="${game_associa_categorias}">
                <tr>
                    <td>${game_associa_categoria.idAssocia}</td>
                    <td>${game_associa_categoria.gameAssocia.titulo}</td>
                    <td>${game_associa_categoria.categoriaAssocia.nome}</td>
                    <td>${game_associa_categoria.qtdPlayers}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/Game_Associa_CategoriaControlador" method="get">
                            <input type="hidden" name="idAssocia" value="${game_associa_categoria.idAssocia}">
                            <input type="hidden" name="gameAssocia" value="${game_associa_categoria.gameAssocia.idGame}">
                            <input type="hidden" name="categoriaAssocia" value="${game_associa_categoria.categoriaAssocia.idCategoria}">
                            <input type="hidden" name="qtdPlayers" value="${game_associa_categoria.qtdPlayers}">
                            <input type="hidden" name="opcao" value="editar">
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/Game_Associa_CategoriaControlador" method="get">
                            <input type="hidden" name="idAssocia" value="${game_associa_categoria.idAssocia}">
                            <input type="hidden" name="gameAssocia" value="${game_associa_categoria.gameAssocia.idGame}">
                            <input type="hidden" name="categoriaAssocia" value="${game_associa_categoria.categoriaAssocia.idCategoria}">
                            <input type="hidden" name="qtdPlayers" value="${game_associa_categoria.qtdPlayers}">
                            <input type="hidden" name="opcao" value="excluir">
                            <button type="submit">Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
                

        </table>

    </body>
</html>

