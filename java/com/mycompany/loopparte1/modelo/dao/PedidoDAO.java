/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.dao;

import com.mycompany.loopparte1.modelo.entidade.Pedido;
import com.mycompany.loopparte1.servico.ConverteData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author João Henrique
 */
public class PedidoDAO  extends GenericoDAO<Pedido>{
    
    public void salvar(Pedido d){
        String insert = "INSERT INTO PEDIDO(USER_ID,PAGAMENTO_ID,DATA_PEDIDO) VALUES (?,?,?)";
        save(insert, d.getUser_id().getIdUsuario(), d.getPagamento_id().getIdPaga(), d.getData_pedido());
    }
    
    public void alterar(Pedido d){
        String update = "UPDATE PEDIDO SET USER_ID = ?,PAGAMENTO_ID=?,DATA_PEDIDO=? WHERE IDPEDIDO=?";
        save(update,d.getUser_id().getIdUsuario(), d.getPagamento_id().getIdPaga(), d.getData_pedido(), d.getIdPedido());
    }
    
    public void excluir(Pedido d){
        String delete = "DELETE FROM PEDIDO WHERE IDPEDIDO=?";
        save(delete, d.getIdPedido());
    }
    
    public Pedido buscarPorId(int id){
        String select = "SELECT * FROM PEDIDO WHERE IDPEDIDO=?";
        return buscarPorId(select, new PedidoRowMapper(), id);
    }
    
    public List<Pedido> buscarTodas(){
        String select = "SELECT *  FROM PEDIDO";
        return buscarTodos(select, new PedidoRowMapper());
    }
    
    public static class PedidoRowMapper implements RowMapper<Pedido>{
        
        ConverteData converte = new ConverteData();
        UsuarioDAO userDAO = new UsuarioDAO();
        MetodoPagamentoDAO pagamentoDAO = new MetodoPagamentoDAO();
        
        @Override
        public Pedido mapRow(ResultSet rs) throws SQLException{
            Pedido pedido = new Pedido();
            pedido.setIdPedido(rs.getInt("IDPEDIDO"));
            pedido.setUser_id(userDAO.buscarPorId(rs.getInt("USER_ID")));
            pedido.setPagamento_id(pagamentoDAO.buscarPorID(rs.getInt("PAGAMENTO_ID")));
            pedido.setData_pedido(converte.converteCalendario(rs.getDate("DATA_PEDIDO")));
            return pedido;
        }
        
    }
    
}
