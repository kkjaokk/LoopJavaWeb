/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.modelo.entidade;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author João Henrique
 */
public class Pedido {
    
    private Integer idPedido;
    private Usuario user_id = new Usuario();
    private MetodoPagamento pagamento_id = new MetodoPagamento();
    private Calendar data_pedido;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Usuario getUser_id() {
        return user_id;
    }

    public void setUser_id(Usuario user_id) {
        this.user_id = user_id;
    }

    public MetodoPagamento getPagamento_id() {
        return pagamento_id;
    }

    public void setPagamento_id(MetodoPagamento pagamento_id) {
        this.pagamento_id = pagamento_id;
    }

    public Calendar getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(Calendar data_pedido) {
        this.data_pedido = data_pedido;
    }

        public String getData_PedidoFormatado() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data_pedido.getTime());

    }
    
}
