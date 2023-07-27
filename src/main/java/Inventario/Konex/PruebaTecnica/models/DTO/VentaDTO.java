package Inventario.Konex.PruebaTecnica.models.DTO;

public class VentaDTO {

    private Long medicamentoId;
    private int cantidad;
    private double valorUnitario;
    private double valorTotal;

    public VentaDTO(Long medicamentoId, int cantidad, double valorUnitario, double valorTotal) {
        this.medicamentoId = medicamentoId;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
    }

    public Long getMedicamentoId() {
        return medicamentoId;
    }

    public void setMedicamentoId(Long medicamentoId) {
        this.medicamentoId = medicamentoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
