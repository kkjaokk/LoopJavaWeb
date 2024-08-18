<%-- 
    Document   : CadastroForum
    Created on : 11 de ago de 2024, 11:47:18
    Author     : João Henrique
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.Forum"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.ForumDAO"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.UsuarioDAO"%>
<%@page import="com.mycompany.loopparte1.modelo.dao.GameDAO"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.Usuario"%>
<%@page import="com.mycompany.loopparte1.modelo.entidade.Game"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Forum</title>
    </head>
    
    
    <body>
        <h1>Cadastro Forum</h1>
        <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ForumControlador" method="get">
            <input type="hidden" name="opcao" value="${opcao}" />
            <input type="hidden" name="idForum" value="${idForum}" />
            
            
                 <p><label>Game Forum: </label>
                    <select name="gameF">
                     <c:forEach var="gameobj" items="${games}">
                         <c:choose> 
                            
                            <c:when test="${gameobj.idGame eq gameF}">
                                <option selected value="${gameobj.idGame}">${gameobj.titulo}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${gameobj.idGame}">${gameobj.titulo}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </p>
            
             <p><label>Usuario Criador:</label>
                    <select name="userF">
                     <c:forEach var="usuario" items="${usuarios}">
                         <c:choose> 
                            
                            <c:when test="${usuario.idUsuario eq userF}">
                                <option selected value="${usuario.idUsuario}">${usuario.nome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${usuario.idUsuario}">${usuario.nome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                </p>
                
                
            <p><label>Titulo: </label><input type="text" name="titulo" value="${titulo}" size="40" /> </p>
            <p><label>Descricao: </label><input type="text" name="descricao" value="${descricao}" size="40" /> </p>
            
            <td>
                <input type="submit"  value="Salvar" name="Salvar" />
            </td>
            
        </form>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ForumControlador" method="get">
                <td>
                    <input type="submit"  value="Cancelar" name="Cancelar" />
                </td>
                <input type="hidden" name="opcao" value="${opcao}" />
            </form>
        ${mensagem}

        <table border =" 1">
            <c:if test="${not empty forums}">
                  <tr>
                      <th>Loop Forum ID</th>
                      <th>Game Forum</th>
                      <th>Usuario Criador</th>
                      <th>Titulo</th>
                      <th>Descricao</th>
                      <th>Alterar</th>
                      <th>Excluir</th>
                  </tr>
            </c:if>
            <c:forEach var="forum" items="${forums}">
                <tr>
                    <td>${forum.idForum}</td>
                    <td>${forum.userF.nome}</td>
                    <td>${forum.gameF.titulo}</td>
                    <td>${forum.titulo}</td>
                    <td>${forum.descricao}</td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ForumControlador" method="get">
                            <input type="hidden" name="idForum" value="${forum.idForum}">
                            <input type="hidden" name="userF" value="${forum.userF.idUsuario}">
                            <input type="hidden" name="gameF" value="${forum.gameF.idGame}">
                            <input type="hidden" name="titulo" value="${forum.titulo}">
                            <input type="hidden" name="descricao" value="${forum.descricao}">
                            <input type="hidden" name="opcao" value="editar">
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                    <td>
                        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ForumControlador" method="get">
                            <input type="hidden" name="idForum" value="${forum.idForum}">
                            <input type="hidden" name="userF" value="${forum.userF.idUsuario}">
                            <input type="hidden" name="gameF" value="${forum.gameF.idGame}">
                            <input type="hidden" name="titulo" value="${forum.titulo}">
                            <input type="hidden" name="descricao" value="${forum.descricao}">
                            <input type="hidden" name="opcao" value="excluir">
                            <button type="submit">Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
