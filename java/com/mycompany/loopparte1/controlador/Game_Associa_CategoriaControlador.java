/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.controlador;

import com.mycompany.loopparte1.modelo.dao.CategoriaDAO;
import com.mycompany.loopparte1.modelo.dao.GameDAO;
import com.mycompany.loopparte1.modelo.dao.Game_Associa_CategoriaDAO;
import com.mycompany.loopparte1.modelo.entidade.Categoria;
import com.mycompany.loopparte1.modelo.entidade.Game;
import com.mycompany.loopparte1.modelo.entidade.Game_Associa_Categoria;
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
 * @author 10698079663
 */

@WebServlet(WebConstantes.BASE_PATH+"/Game_Associa_CategoriaControlador")
public class Game_Associa_CategoriaControlador extends HttpServlet{

private Game_Associa_Categoria game_associa_categoria;
private Game_Associa_CategoriaDAO game_associa_categoriaDAO;
private GameDAO gameDAO;
private Game game;
private Categoria categoria;
private CategoriaDAO categoriaDAO;
String idAssocia = "";
String gameAssocia="";
String categoriaAssocia="";
String qtdPlayers="";
String opcao = "";

    @Override
    public void init() throws ServletException {
       
       categoria = new Categoria();
       categoriaDAO = new CategoriaDAO(); 
       gameDAO = new GameDAO();
       game = new Game();
       game_associa_categoriaDAO = new Game_Associa_CategoriaDAO();
       game_associa_categoria = new Game_Associa_Categoria();
       
    }
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            opcao = request.getParameter("opcao");
            idAssocia = request.getParameter("idAssocia");
            gameAssocia = request.getParameter("gameAssocia");
            categoriaAssocia = request.getParameter("categoriaAssocia");
            qtdPlayers = request.getParameter("qtdPlayers");
            
            
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
        game_associa_categoria.getGameAssocia().setIdGame(Integer.valueOf(gameAssocia));
        game_associa_categoria.getCategoriaAssocia().setIdCategoria(Integer.valueOf(categoriaAssocia));
        game_associa_categoria.setQtdPlayers(Integer.valueOf(qtdPlayers));
        game_associa_categoriaDAO.salvar(game_associa_categoria);
        encaminharParaPagina(request, response);
    }
    
      private void editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idAssocia", idAssocia);
       request.setAttribute("opcao", "confirmarEditar");
       request.setAttribute("gameAssocia", gameAssocia);
       request.setAttribute("categoriaAssocia", categoriaAssocia);
       request.setAttribute("qtdPlayers", qtdPlayers);
       request.setAttribute("mensagem", "Edite os dados e clique em Salvar");
        encaminharParaPagina(request, response);
    }
      
      private void excluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idAssocia", idAssocia);
       request.setAttribute("opcao", "confirmarExcluir");
       request.setAttribute("gameAssocia", gameAssocia);
       request.setAttribute("categoriaAssocia", categoriaAssocia);
       request.setAttribute("qtdPlayers", qtdPlayers);
       request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }
      
      private void confirmarEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       validaCampos();
       game_associa_categoria.setIdAssocia(Integer.valueOf(idAssocia));
       game_associa_categoria.getGameAssocia().setIdGame(Integer.valueOf(gameAssocia));
       game_associa_categoria.getCategoriaAssocia().setIdCategoria(Integer.valueOf(categoriaAssocia));
       game_associa_categoria.setQtdPlayers(Integer.valueOf(qtdPlayers));
       game_associa_categoriaDAO.alterar(game_associa_categoria);
       cancelar(request, response);
    }
      private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       game_associa_categoria.setIdAssocia(Integer.valueOf(idAssocia));
       game_associa_categoria.getGameAssocia().setIdGame(Integer.valueOf(gameAssocia));
       game_associa_categoria.getCategoriaAssocia().setIdCategoria(Integer.valueOf(categoriaAssocia));
       game_associa_categoria.setQtdPlayers(Integer.valueOf(qtdPlayers));
       game_associa_categoriaDAO.excluir(game_associa_categoria);
       cancelar(request, response);
    }
      private void cancelar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idAssocia", "0");
       request.setAttribute("opcao", "cadastrar");
       request.setAttribute("gameAssocia", "");
       request.setAttribute("categoriaAssocia", "");
       request.setAttribute("qtdPlayers", "");
       encaminharParaPagina(request, response);
    }
      
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        
        List<Game_Associa_Categoria> game_associa_categorias = game_associa_categoriaDAO.buscarTodas();
        request.setAttribute("game_associa_categorias", game_associa_categorias);
        
        List<Game> games = gameDAO.buscarTodas();
        request.setAttribute("games", games);
        
        List<Categoria> categorias = categoriaDAO.buscarTodas();
        request.setAttribute("categorias", categorias);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Cadastro_Game_Associa_Categoria.jsp");
        dispatcher.forward(request, response);
    }
    
  
    public void validaCampos(){
        if(gameAssocia==null || gameAssocia.isEmpty()
                || categoriaAssocia == null || categoriaAssocia.isEmpty()
                || qtdPlayers == null || qtdPlayers.isEmpty()
            ){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
    
}
