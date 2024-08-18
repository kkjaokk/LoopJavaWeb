/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.entidade;

/**
 *
 * @author João Henrique
 */
public class Forum {
    
    private Integer idForum;
    private Usuario userF = new Usuario();
    private Game gameF = new Game();
    
    private String titulo, descricao;

    public Integer getIdForum() {
        return idForum;
    }

    public void setIdForum(Integer idForum) {
        this.idForum = idForum;
    }

    public Usuario getUserF() {
        return userF;
    }

    public void setUserF(Usuario userF) {
        this.userF = userF;
    }

    public Game getGameF() {
        return gameF;
    }

    public void setGameF(Game gameF) {
        this.gameF = gameF;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
}
