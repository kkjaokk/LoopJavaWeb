/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.dao;

import com.mycompany.loopparte1.modelo.entidade.Pedido_Itens;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author João Henrique
 */
public class Pedido_ItensDAO  extends GenericoDAO<Pedido_Itens>{
    
        public void salvar(Pedido_Itens d){
        String insert = "INSERT INTO PEDIDO_ITENS(PEDIDO_ID,GAME_ID,QUANTIDADE,VALOR) VALUES (?,?,?,?)";
        save(insert, d.getPedido_id().getIdPedido(), d.getGame_id().getIdGame(), d.getQuantidade(), d.getValor() );
    }
    
    public void alterar(Pedido_Itens d){
        String update = "UPDATE PEDIDO_ITENS SET PEDIDO_ID = ?,GAME_ID=?,QUANTIDADE=?,VALOR=? WHERE IDITEM=?";
        save(update, d.getPedido_id().getIdPedido(), d.getGame_id().getIdGame(), d.getQuantidade(), d.getValor(), d.getIdItem());
    }
    
    public void excluir(Pedido_Itens d){
        String delete = "DELETE FROM PEDIDO_ITENS WHERE IDITEM = ?";
        save(delete, d.getIdItem());
    }
    
    public Pedido_Itens buscarPorId(int id){
        String select = "SELECT * FROM PEDIDO_ITENS WHERE IDITEM=?";
        return buscarPorId(select, new Pedido_ItensRowMapper(), id);
    }
    
    public List<Pedido_Itens> buscarTodas(){
        String select = "SELECT *  FROM PEDIDO_ITENS";
        return buscarTodos(select, new Pedido_ItensRowMapper());
    }
    
    public static class Pedido_ItensRowMapper implements RowMapper<Pedido_Itens>{
        
        PedidoDAO pedidoDAO = new PedidoDAO();
        GameDAO gameDAO = new GameDAO();
        
        @Override
        public Pedido_Itens mapRow(ResultSet rs) throws SQLException{
            Pedido_Itens item = new Pedido_Itens();
            item.setIdItem(rs.getInt("IDITEM"));
            item.setPedido_id(pedidoDAO.buscarPorId(rs.getInt("PEDIDO_ID")));
            item.setGame_id(gameDAO.buscarPorId(rs.getInt("GAME_ID")));
            item.setQuantidade(rs.getInt("QUANTIDADE"));
            item.setValor(rs.getDouble("VALOR"));
            return item;
        }
        
    }
    
}
