/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.dao;

import com.mycompany.loopparte1.modelo.entidade.Forum;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author João Henrique
 */
public class ForumDAO extends GenericoDAO<Forum>{
    
        public void salvar(Forum d){
        String insert = "INSERT INTO FORUM  (GAMEF,USERF,TITULOFORUM,DESCRICAO) VALUES (?,?,?,?)";
        save(insert, d.getGameF().getIdGame(), d.getUserF().getIdUsuario(), d.getTitulo(), d.getDescricao());
    }
    
    public void alterar(Forum d){
        String update = "UPDATE FORUM SET GAMEF = ?,USERF=?,TITULOFORUM=?,DESCRICAO=? WHERE IDFORUM=?";
        save(update, d.getGameF().getIdGame(), d.getUserF().getIdUsuario(), d.getTitulo(), d.getDescricao(), d.getIdForum());
    }
    
    public void excluir(Forum d){
        String delete = "DELETE FROM FORUM WHERE IDFORUM = ?";
        save(delete, d.getIdForum());
    }
    
    public Forum buscarPorId(int id){
        String select = "SELECT * FROM FORUM WHERE IDFORUM=?";
        return buscarPorId(select, new ForumRowMapper(), id);
    }
    
    public List<Forum> buscarTodas(){
        String select = "SELECT *  FROM FORUM";
        return buscarTodos(select, new ForumRowMapper());
    }
    
    public static class ForumRowMapper implements RowMapper<Forum>{
        
        GameDAO gameDAO = new GameDAO();
        UsuarioDAO userDAO = new UsuarioDAO();
        
        @Override
        public Forum mapRow(ResultSet rs) throws SQLException{
            Forum forum = new Forum();
            forum.setIdForum(rs.getInt("IDFORUM"));
            forum.setGameF(gameDAO.buscarPorId(rs.getInt("GAMEF")));
            forum.setUserF(userDAO.buscarPorId(rs.getInt("USERF")));
            forum.setTitulo(rs.getString("TITULOFORUM"));
            forum.setDescricao(rs.getString("DESCRICAO"));
            return forum;
        }
        
    }
    
}
