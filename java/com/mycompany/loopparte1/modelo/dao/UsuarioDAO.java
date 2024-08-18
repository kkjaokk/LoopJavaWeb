/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.dao;

/**
 *
 * @author João Henrique
 */
import com.mycompany.loopparte1.modelo.entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author 10414032675
 */

public class UsuarioDAO extends GenericoDAO<Usuario>{
    
    public Usuario getUserbyUsername(String nome){
        String sql = "SELECT nome, senha FROM usuario WHERE nome=? ";
        try(Connection con = ConnectionFactory.getInstance().getConnection(); 
             PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Usuario(rs.getString("nome"), rs.getString("senha"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void registrarUsuario(String username, String email, String plainPassword, String pais, Double pontos){
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        
        String sql = "INSERT INTO usuario (nome, email, senha, pais, pontos) VALUES (?,?,?,?,?)";
        try(Connection con = ConnectionFactory.getInstance().getConnection(); 
             PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, hashedPassword);
            stmt.setString(4, pais);
            stmt.setDouble(5, pontos);
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void salvar(Usuario u){
        String insert = "INSERT INTO USUARIO(NOME,EMAIL,SENHA,PAIS,PONTOS) VALUES (?,?,?,?,?)";
        save(insert, u.getNome(), u.getEmail(), u.getSenha(), u.getPais(), u.getPontos());
    }
    
    public void alterar(Usuario u){
        String update = "UPDATE USUARIO SET NOME = ?,EMAIL=?,SENHA=?,PAIS=?,PONTOS=? WHERE IDUSER=?";
        save(update, u.getNome(), u.getEmail(), u.getSenha(), u.getPais(), u.getPontos(), u.getIdUsuario());
    }
    
    public void excluir(Usuario u){
        String delete = "DELETE FROM USUARIO WHERE IDUSER = ?";
        save(delete, u.getIdUsuario());
    }
    
    public Usuario buscarPorId(int id){
        String select = "SELECT * FROM USUARIO WHERE IDUSER=?";
        return buscarPorId(select, new UsuarioRowMapper(), id);
    }
    
    public List<Usuario> buscarTodas(){
        String select = "SELECT *  FROM USUARIO";
        return buscarTodos(select, new UsuarioRowMapper());
    }
    
    public static class UsuarioRowMapper implements RowMapper<Usuario>{
        
        @Override
        public Usuario mapRow(ResultSet rs) throws SQLException{
            Usuario user = new Usuario();
            user.setIdUsuario(rs.getInt("IDUSER"));
            user.setNome(rs.getString("NOME"));
            user.setEmail(rs.getString("EMAIL"));
            user.setSenha(rs.getString("SENHA"));
            user.setPais(rs.getString("PAIS"));
            user.setPontos(rs.getDouble("PONTOS"));
            return user;
        }
        
    }
    
}
