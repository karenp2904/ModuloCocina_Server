package Dominio;

public class Factura {
    Pedido pedido;
    Cliente cliente;
    String id;

    public Factura(Pedido pedido, Cliente cliente,String id) {
        this.pedido = pedido;
        this.cliente = cliente;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Factura {" +
                "pedido=" + pedido.getProductoNombre() +" - "+ pedido.getCantidad() +
                ", cliente=" + cliente.getNombreCliente() +" - "+ cliente.getTelefono()+" - "+cliente.getDireccionCliente() +" - "+ cliente.getTipoCuenta() +
                '}';
    }
}
