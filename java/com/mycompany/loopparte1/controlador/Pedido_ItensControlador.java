/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.controlador;

import com.mycompany.loopparte1.modelo.dao.GameDAO;
import com.mycompany.loopparte1.modelo.dao.PedidoDAO;
import com.mycompany.loopparte1.modelo.dao.Pedido_ItensDAO;
import com.mycompany.loopparte1.modelo.entidade.Game;
import com.mycompany.loopparte1.modelo.entidade.Pedido;
import com.mycompany.loopparte1.modelo.entidade.Pedido_Itens;
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

@WebServlet(WebConstantes.BASE_PATH+"/Pedido_ItensControlador")
public class Pedido_ItensControlador extends HttpServlet{
    
private Pedido_ItensDAO pedido_itensDAO;
private Pedido_Itens pedido_itens;
private Game game;
private GameDAO gameDAO;
private Pedido pedido;
private PedidoDAO pedidoDAO;
String idItem = "";
String pedido_id = "";
String game_id = "";
String quantidade = "";
String valor = "";
String opcao = "";

    @Override
    public void init() throws ServletException {
      pedido_itensDAO  = new Pedido_ItensDAO();
      pedido_itens = new Pedido_Itens();
      game = new Game();
      gameDAO = new GameDAO();
      pedido = new Pedido();
      pedidoDAO = new PedidoDAO();
    }
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            opcao = request.getParameter("opcao");
            idItem = request.getParameter("idItem");
            pedido_id = request.getParameter("pedido_id");
            game_id = request.getParameter("game_id");
            quantidade = request.getParameter("quantidade");
            valor = request.getParameter("valor");
            
            
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
        pedido_itens.getPedido_id().setIdPedido(Integer.valueOf(pedido_id));
        pedido_itens.getGame_id().setIdGame(Integer.valueOf(game_id));
        pedido_itens.setQuantidade(Integer.valueOf(quantidade));
        pedido_itens.setValor(Double.valueOf(valor));
        pedido_itensDAO.salvar(pedido_itens);
        encaminharParaPagina(request, response);
    }
    
      private void editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idItem", idItem);
       request.setAttribute("opcao", "confirmarEditar");
       request.setAttribute("pedido_id", pedido_id);
       request.setAttribute("game_id", game_id);
       request.setAttribute("quantidade", quantidade);
       request.setAttribute("valor", valor);
       request.setAttribute("mensagem", "Edite os dados e clique em Salvar");
        encaminharParaPagina(request, response);
    }
      
      private void excluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idItem", idItem);
       request.setAttribute("opcao", "confirmarExcluir");
       request.setAttribute("pedido_id", pedido_id);
       request.setAttribute("game_id", game_id);
       request.setAttribute("quantidade", quantidade);
       request.setAttribute("valor", valor);
       request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }
      
      private void confirmarEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       validaCampos();
       pedido_itens.setIdItem(Integer.valueOf(idItem));
       pedido_itens.getPedido_id().setIdPedido(Integer.valueOf(pedido_id));
       pedido_itens.getGame_id().setIdGame(Integer.valueOf(game_id));
       pedido_itens.setQuantidade(Integer.valueOf(quantidade));
       pedido_itens.setValor(Double.valueOf(valor));
       pedido_itensDAO.alterar(pedido_itens);
       cancelar(request, response);
    }
      private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       pedido_itens.setIdItem(Integer.valueOf(idItem));
       pedido_itens.getPedido_id().setIdPedido(Integer.valueOf(pedido_id));
       pedido_itens.getGame_id().setIdGame(Integer.valueOf(game_id));
       pedido_itens.setQuantidade(Integer.valueOf(quantidade));
       pedido_itens.setValor(Double.valueOf(valor));
       pedido_itensDAO.excluir(pedido_itens);
       cancelar(request, response);
    }
      private void cancelar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idItem", "0");
       request.setAttribute("opcao", "cadastrar");
       request.setAttribute("pedido_id", "");
       request.setAttribute("game_id", "");
       request.setAttribute("quantidade", "");
       request.setAttribute("valor", "");
        encaminharParaPagina(request, response);
    }
      
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        
        List<Pedido_Itens> pedido_items = pedido_itensDAO.buscarTodas();
        request.setAttribute("pedido_items", pedido_items);
        
        List<Game> games = gameDAO.buscarTodas();
        request.setAttribute("games", games);
        
        List<Pedido> pedidos = pedidoDAO.buscarTodas();
        request.setAttribute("pedidos", pedidos);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroPedido_Itens.jsp");
        dispatcher.forward(request, response);
    }
    
  
    public void validaCampos(){
        if(pedido_id==null || pedido_id.isEmpty()
                || game_id == null || game_id.isEmpty()
                || quantidade == null || quantidade.isEmpty()
                || valor == null || valor.isEmpty()
            ){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
    
}
