/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.controlador;

/**
 *
 * @author Quiqu
 */

import com.mycompany.loopparte1.modelo.dao.MetodoPagamentoDAO;
import com.mycompany.loopparte1.modelo.entidade.MetodoPagamento;
import com.mycompany.loopparte1.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH+"/MetodoPagamentoControlador")
public class MetodoPagamentoControlador extends HttpServlet{
    
    private MetodoPagamentoDAO metodoPagamentoDao;
    private MetodoPagamento metodoPagamento;
    String nomePg="";
    String descricao="";
    String taxa="";
    String idPaga="";
    String opcao="";
    
    @Override
    public void init() throws ServletException {
       metodoPagamentoDao  = new MetodoPagamentoDAO();
       metodoPagamento = new MetodoPagamento();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try{
            String opcao = request.getParameter("opcao");
            idPaga = request.getParameter("idPaga");
            nomePg = request.getParameter("nomePg");
            descricao = request.getParameter("descricao");
            taxa = request.getParameter("taxa");
            
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
        metodoPagamento.setNomePg(nomePg);
        metodoPagamento.setDescricao(descricao);
        metodoPagamento.setTaxa(Double.valueOf(taxa));
        metodoPagamentoDao.salvar(metodoPagamento);
        encaminharParaPagina(request, response);
    }
    
    private void editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idPaga", idPaga);
       request.setAttribute("opcao", "confirmarEditar");
       request.setAttribute("nomePg", nomePg);
       request.setAttribute("descricao", descricao);
       request.setAttribute("taxa", taxa);
       request.setAttribute("mensagem", "Edite os dados e clique em Salvar");
        encaminharParaPagina(request, response);
    }
    
    private void excluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idPaga", idPaga);
       request.setAttribute("opcao", "confirmarExcluir");
       request.setAttribute("nomePg", nomePg);
       request.setAttribute("descricao", descricao);
       request.setAttribute("taxa", taxa);
       request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }
    
      private void confirmarEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       validaCampos();
       metodoPagamento.setIdPaga(Integer.valueOf(idPaga));
       metodoPagamento.setNomePg(nomePg);
       metodoPagamento.setDescricao(descricao);
       metodoPagamento.setTaxa(Double.valueOf(taxa));
       metodoPagamentoDao.alterar(metodoPagamento);
        cancelar(request, response);
    }
    
       private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       metodoPagamento.setIdPaga(Integer.valueOf(idPaga));
       metodoPagamento.setNomePg(nomePg);
       metodoPagamento.setDescricao(descricao);
       metodoPagamento.setTaxa(Double.valueOf(taxa));
       metodoPagamentoDao.alterar(metodoPagamento);
       metodoPagamentoDao.excluir(metodoPagamento);
        cancelar(request, response);
    }
       
       private void cancelar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       request.setAttribute("idPaga", "0");
       request.setAttribute("opcao", "cadastrar");
       request.setAttribute("nomePg", "");
       request.setAttribute("descricao", "");
       request.setAttribute("taxa", "");
        encaminharParaPagina(request, response);
    }
       
       private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        List<MetodoPagamento> metodos = metodoPagamentoDao.buscarTodas();
        request.setAttribute("metodosPagamento", metodos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroMetodosPagamentos.jsp");
            dispatcher.forward(request, response);
    }
       
       public void validaCampos(){
        if(nomePg==null || nomePg.isEmpty() || descricao == null || descricao.isEmpty() || taxa == null || taxa.isEmpty()){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}
