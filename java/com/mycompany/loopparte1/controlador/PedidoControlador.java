/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.controlador;

import com.mycompany.loopparte1.modelo.dao.MetodoPagamentoDAO;
import com.mycompany.loopparte1.modelo.dao.PedidoDAO;
import com.mycompany.loopparte1.modelo.dao.UsuarioDAO;
import com.mycompany.loopparte1.modelo.entidade.MetodoPagamento;
import com.mycompany.loopparte1.modelo.entidade.Pedido;
import com.mycompany.loopparte1.modelo.entidade.Usuario;
import com.mycompany.loopparte1.servico.ConverteData;
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
@WebServlet(WebConstantes.BASE_PATH+"/PedidoControlador")
public class PedidoControlador extends HttpServlet{
    
private PedidoDAO pedidoDAO;
private Pedido pedido;
private MetodoPagamento pagamento;
private MetodoPagamentoDAO pagamentoDAO;
private Usuario usuario;
private UsuarioDAO usuarioDAO;
String idPedido = "";
String user_id = "";
String pagamento_id = "";
String data_pedido = "";
String opcao = "";
private ConverteData converte = new ConverteData();

    @Override
    public void init() throws ServletException {
       pagamentoDAO  = new MetodoPagamentoDAO();
       pagamento = new MetodoPagamento();
       pedido = new Pedido();
       pedidoDAO = new PedidoDAO();
       usuario = new Usuario();
       usuarioDAO = new UsuarioDAO();
    }
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            opcao = request.getParameter("opcao");
            idPedido = request.getParameter("idPedido");
            user_id = request.getParameter("user_id");
            pagamento_id = request.getParameter("pagamento_id");
            data_pedido = request.getParameter("data_pedido");     
            
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
        pedido.getUser_id().setIdUsuario(Integer.valueOf(user_id));
        pedido.getPagamento_id().setIdPaga(Integer.valueOf(pagamento_id));
        pedido.setData_pedido(converte.converteCalendario(data_pedido));
        pedidoDAO.salvar(pedido);
        encaminharParaPagina(request, response);
    }
    
      private void editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idPedido", idPedido);
       request.setAttribute("opcao", "confirmarEditar");
       request.setAttribute("user_id", user_id);
       request.setAttribute("pagamento_id", pagamento_id);
       request.setAttribute("data_pedido", ConverteData.convertDateFormat(data_pedido));
       request.setAttribute("mensagem", "Edite os dados e clique em Salvar");
        encaminharParaPagina(request, response);
    }
      
      private void excluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idPedido", idPedido);
       request.setAttribute("opcao", "confirmarExcluir");
       request.setAttribute("user_id", user_id);
       request.setAttribute("pagamento_id", pagamento_id);
       request.setAttribute("data_pedido", ConverteData.convertDateFormat(data_pedido));
       request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }
      
      private void confirmarEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       validaCampos();
       pedido.setIdPedido(Integer.valueOf(idPedido));
       pedido.getUser_id().setIdUsuario(Integer.valueOf(user_id));
       pedido.getPagamento_id().setIdPaga(Integer.valueOf(pagamento_id));
       pedido.setData_pedido(converte.converteCalendario(data_pedido));
       pedidoDAO.alterar(pedido);
       cancelar(request, response);
    }
      private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       pedido.setIdPedido(Integer.valueOf(idPedido));
       pedido.getUser_id().setIdUsuario(Integer.valueOf(user_id));
       pedido.getPagamento_id().setIdPaga(Integer.valueOf(pagamento_id));
       pedido.setData_pedido(converte.converteCalendario(data_pedido));
       pedidoDAO.excluir(pedido);
       cancelar(request, response);
    }
      private void cancelar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idPedido", "0");
       request.setAttribute("opcao", "cadastrar");
       request.setAttribute("user_id", "");
       request.setAttribute("pagamento_id", "");
       request.setAttribute("data_pedido", "");
        encaminharParaPagina(request, response);
    }
      
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        
        List<Pedido> pedidos = pedidoDAO.buscarTodas();
        request.setAttribute("pedidos", pedidos);
        
        List<Usuario> usuarios = usuarioDAO.buscarTodas();
        request.setAttribute("usuarios", usuarios);
        
        List<MetodoPagamento> pagamentos = pagamentoDAO.buscarTodas();
        request.setAttribute("pagamentos", pagamentos);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroPedido.jsp");
        dispatcher.forward(request, response);
    }
    
  
    public void validaCampos(){
        if(user_id==null || user_id.isEmpty()
                || pagamento_id == null || pagamento_id.isEmpty()
                || data_pedido == null || data_pedido.isEmpty()
            ){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
    
}
