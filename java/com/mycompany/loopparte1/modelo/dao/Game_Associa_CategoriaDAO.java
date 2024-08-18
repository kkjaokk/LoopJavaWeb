/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.dao;


import com.mycompany.loopparte1.modelo.entidade.Game_Associa_Categoria;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 10698079663
 */
public class Game_Associa_CategoriaDAO extends GenericoDAO<Game_Associa_Categoria>{
    
        public void salvar(Game_Associa_Categoria d){
        String insert = "INSERT INTO GAME_ASSOCIA_CATEGORIA(GAME_IDGAME,CATEGORIA_IDCATEGORIA,QTDPLAYERS) VALUES (?,?,?)";
        save(insert, d.getGameAssocia().getIdGame(), d.getCategoriaAssocia().getIdCategoria(), d.getQtdPlayers());
    }
    
    public void alterar(Game_Associa_Categoria d){
        String update = "UPDATE GAME_ASSOCIA_CATEGORIA SET GAME_IDGAME=?, CATEGORIA_IDCATEGORIA=?, QTDPLAYERS=? WHERE IDASSOCIA=?";
        save(update, d.getGameAssocia().getIdGame(), d.getCategoriaAssocia().getIdCategoria(), d.getQtdPlayers(), d.getIdAssocia());
    }
    
    public void excluir(Game_Associa_Categoria d){
        String delete = "DELETE FROM GAME_ASSOCIA_CATEGORIA WHERE IDASSOCIA = ?";
        save(delete, d.getIdAssocia());
    }
    
    public Game_Associa_Categoria buscarPorId(int id){
        String select = "SELECT * FROM GAME_ASSOCIA_CATEGORIA WHERE IDASSOCIA=?";
        return buscarPorId(select, new Game_Associa_CategoriaRowMapper(), id);
    }
    
    public List<Game_Associa_Categoria> buscarTodas(){
        String select = "SELECT *  FROM GAME_ASSOCIA_CATEGORIA";
        return buscarTodos(select, new Game_Associa_CategoriaRowMapper());
    }
    
    public static class Game_Associa_CategoriaRowMapper implements RowMapper<Game_Associa_Categoria>{
        
        GameDAO gameDAO = new GameDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        
        @Override
        public Game_Associa_Categoria mapRow(ResultSet rs) throws SQLException{
            Game_Associa_Categoria associa = new Game_Associa_Categoria();
            associa.setIdAssocia(rs.getInt("IDASSOCIA"));
            associa.setGameAssocia(gameDAO.buscarPorId(rs.getInt("GAME_IDGAME")));
            associa.setCategoriaAssocia(categoriaDAO.buscarPorID(rs.getInt("CATEGORIA_IDCATEGORIA")));
            associa.setQtdPlayers(rs.getInt("QTDPLAYERS"));
            return associa;
        }
        
    }
    
}
