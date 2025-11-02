import java.io.Serializable;

/**
 * Classe que representa a roda de um veiculo.
 * 
 * @see Vehicle
 * @see Serializable
 */
public class Tire implements Serializable {
    private boolean isCalibrated;

    /**
     * Construtor padrao.
     */
    public Tire() {
        this.isCalibrated = (Math.random() > 0.5);
    }

    /**
     * Metodo que registra a calibragem da roda.
     * 
     * @param isCalibrated - calibragem.
     */
    public void setCalibration(boolean isCalibrated) {
        this.isCalibrated = isCalibrated;
    }

    /**
     * Metodo que retorna a calibragem da roda.
     * 
     * @return boolean - calibragem da roda.
     */
    public boolean isCalibrated() {
        return isCalibrated;
    }

    /**
     * Metodo que calibra a roda.
     */
    public void calibrate() {
        this.isCalibrated = true;
    }

    /**
     * Metodo que esvazia a roda.
     */
    public void empty() {
        this.isCalibrated = false;
    }

    /**
     * Metodo que retorna a representacao do objeto em texto.
     */
    public String toString() {
        return "Roda " + (isCalibrated ? "calibrada" : "nao calibrada");
    }
}
