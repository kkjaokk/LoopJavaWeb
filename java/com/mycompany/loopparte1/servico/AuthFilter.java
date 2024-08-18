/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.servico;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Quiqu
 */
@WebFilter(urlPatterns = {"/*"})
public class AuthFilter implements Filter{
    public AuthFilter(){
    
}
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException{
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
    
       if(path.startsWith("/public/") || path.equalsIgnoreCase("/login.jsp/") 
               || path.equalsIgnoreCase("/com/mycompany/loopparte1/controlador/UsuarioControlador") 
               || path.equalsIgnoreCase("/com/mycompany/loopparte1/controlador/Pedido_ItensControlador") 
               || path.equalsIgnoreCase("/com/mycompany/loopparte1/controlador/ForumControlador") 
               || path.equalsIgnoreCase("/com/mycompany/loopparte1/controlador/PedidoControlador") 
               || path.equalsIgnoreCase("/com/mycompany/loopparte1/controlador/AvaliacoesControlador")
               || path.equalsIgnoreCase("/com/mycompany/loopparte1/controlador/CategoriaControlador")
               || path.equalsIgnoreCase("/com/mycompany/loopparte1/controlador/DesenvolvedoraControlador")
               || path.equalsIgnoreCase("/com/mycompany/loopparte1/controlador/GameControlador")
               || path.equalsIgnoreCase("/com/mycompany/loopparte1/controlador/Game_Associa_CategoriaControlador")
               || path.equalsIgnoreCase("/com/mycompany/loopparte1/controlador/MetodoPagamentoControlador")
               || path.equalsIgnoreCase("/index.jsp") || path.equalsIgnoreCase("/menu.jsp") || path.equalsIgnoreCase("/CadastroUsuario.jsp") 
               || path.equalsIgnoreCase("/CadastroForum.jsp") || path.equalsIgnoreCase("/CadastroPedido.jsp") 
               || path.equalsIgnoreCase("/CadastroPedido_Itens.jsp") 
               || path.equalsIgnoreCase("/CadastroAvaliacoes.jsp") || path.equalsIgnoreCase("/CadastroCategorias.jsp") 
               || path.equalsIgnoreCase("/CadastroDesenvolvedora.jsp") || path.equalsIgnoreCase("/CadastroGame.jsp") 
               || path.equalsIgnoreCase("/CadastroMetodosPagamentos.jsp") || path.equalsIgnoreCase("/Cadastro_Game_Associa_Categoria.jsp")
               || path.equalsIgnoreCase("/login.jsp")|| path.contains("/Assets")
               || path.contains("/Assets")|| path.contains("/CSS/") || path.contains("/js/") || path.contains("/imagens/")){
            chain.doFilter(request, response);
        }else{
            if(httpRequest.getSession().getAttribute("user") == null){
                httpResponse.sendRedirect("/loopparte1/login.jsp");
            }else{
                chain.doFilter(request, response);
            }
        }
    }
    
    @Override
    public void destroy(){
        
    }

   
    
}


