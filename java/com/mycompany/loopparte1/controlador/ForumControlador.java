/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.controlador;


import com.mycompany.loopparte1.modelo.dao.ForumDAO;
import com.mycompany.loopparte1.modelo.dao.GameDAO;
import com.mycompany.loopparte1.modelo.dao.UsuarioDAO;
import com.mycompany.loopparte1.modelo.entidade.Forum;
import com.mycompany.loopparte1.modelo.entidade.Game;
import com.mycompany.loopparte1.modelo.entidade.Usuario;
import com.mycompany.loopparte1.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author João Henrique
 */

@WebServlet(WebConstantes.BASE_PATH+"/ForumControlador")
public class ForumControlador extends HttpServlet{
    
private ForumDAO forumDAO;
private Forum forum;
private Game gameobj;
private GameDAO gameDAO;
private Usuario userobj;
private UsuarioDAO userDAO;
String idForum = "";
String gameF = "";
String userF = "";
String titulo = "";
String descricao = "";
String opcao = "";

    @Override
    public void init() throws ServletException {
       userDAO  = new UsuarioDAO();
       userobj = new Usuario();
       gameobj = new Game();
       gameDAO = new GameDAO();
       forum = new Forum();
       forumDAO = new ForumDAO();
    }
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            
            opcao = request.getParameter("opcao");
            idForum = request.getParameter("idForum");
            gameF = request.getParameter("gameF");
            userF = request.getParameter("userF");
            titulo = request.getParameter("titulo");
            descricao = request.getParameter("descricao");
            
            
            if(opcao == null || opcao.isEmpty()){
                opcao="cadastrar";
            }
            switch (opcao) {
                case "cadastrar": cadastrar(request, response);break;
                case "editar": editar(request, response);break;
                case "confirmarEditar": confirmarEditar(request, response);break;
                 case "excluir": excluir(request, response);break;
                case "confirmarExcluir": confirmarExcluir(request, response);break;
                case "cancelar": cancelar(request, response);break;
                default:
                    throw new IllegalArgumentException("Opcao Invalida " +opcao);
            }
            
        }catch(NumberFormatException e){
            response.getWriter().println("Erro: um ou mais parâmetros não são números válidos");
        }catch(IllegalArgumentException e){
            response.getWriter().println("Erro: "+e.getMessage());
        }
    }
    
    private void cadastrar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        validaCampos();
        forum.getGameF().setIdGame(Integer.valueOf(gameF));
        forum.getUserF().setIdUsuario(Integer.valueOf(userF));
        forum.setTitulo(titulo);
        forum.setDescricao(descricao);
        forumDAO.salvar(forum);
        encaminharParaPagina(request, response);
    }
    
      private void editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idForum", idForum);
       request.setAttribute("opcao", "confirmarEditar");
       request.setAttribute("gameF", gameF);
       request.setAttribute("userF", userF);
       request.setAttribute("titulo", titulo);
       request.setAttribute("descricao", descricao);
       request.setAttribute("mensagem", "Edite os dados e clique em Salvar");
        encaminharParaPagina(request, response);
    }
      
      private void excluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idForum", idForum);
       request.setAttribute("opcao", "confirmarExcluir");
       request.setAttribute("gameF", gameF);
       request.setAttribute("userF", userF);
       request.setAttribute("titulo", titulo);
       request.setAttribute("descricao", descricao);
       request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }
      
      private void confirmarEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       validaCampos();
       forum.setIdForum(Integer.valueOf(idForum));
       forum.getGameF().setIdGame(Integer.valueOf(gameF));
       forum.getUserF().setIdUsuario(Integer.valueOf(userF));
       forum.setTitulo(titulo);
       forum.setDescricao(descricao);
       forumDAO.alterar(forum);
       cancelar(request, response);
    }
      private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       forum.setIdForum(Integer.valueOf(idForum));
       forum.getGameF().setIdGame(Integer.valueOf(gameF));
       forum.getUserF().setIdUsuario(Integer.valueOf(userF));
       forum.setTitulo(titulo);
       forum.setDescricao(descricao);
       forumDAO.excluir(forum);
       cancelar(request, response);
    }
      private void cancelar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idForum", "0");
       request.setAttribute("opcao", "cadastrar");
       request.setAttribute("gameF", "");
       request.setAttribute("userF", "");
       request.setAttribute("titulo", "");
       request.setAttribute("descricao", "");
        encaminharParaPagina(request, response);
    }
      
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        
        List<Forum> forums = forumDAO.buscarTodas();
        request.setAttribute("forums", forums);
        
        List<Game> games = gameDAO.buscarTodas();
        request.setAttribute("games", games);
        
        List<Usuario> usuarios = userDAO.buscarTodas();
        request.setAttribute("usuarios", usuarios);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroForum.jsp");
        dispatcher.forward(request, response);
    }
    
  
    public void validaCampos(){
        if(gameF==null || gameF.isEmpty()
                || userF == null || userF.isEmpty()
                || titulo == null || titulo.isEmpty()
                || descricao == null || descricao.isEmpty()
            ){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
    
}
