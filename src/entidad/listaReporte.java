/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

/**
 *
 * @author LENOVO
 */
public class listaReporte {

    private String cantidad;
    private String producto;
    private String fecha;
    
    public listaReporte() {
        
    }

    public listaReporte(String cantidad, String producto, String fecha) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.fecha = fecha;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
