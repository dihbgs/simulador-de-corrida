import java.io.Serializable;

public class Tire implements Serializable {
    private boolean isCalibrated;

    public Tire() {
        this.isCalibrated = (Math.random() > 0.5);
    }

    public void setCalibration(boolean isCalibrated) {
        this.isCalibrated = isCalibrated;
    }

    public boolean isCalibrated() {
        return isCalibrated;
    }

    public String toString() {
        return "Roda " + (isCalibrated ? "calibrada." : "nao calibrada.");
    }
}
