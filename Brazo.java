public class Brazo
{
    private boolean estado=false;// atributo tipo boolean para saber el estado de los brazos
    private boolean estado_arma=false;//atributo tipo boolean para saber el estado del brazo que tiene el arma

    public Brazo() {
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isEstado_arma() {
        return estado_arma;
    }

    public void setEstado_arma(boolean estado_arma) {
        this.estado_arma = estado_arma;
    }
}
