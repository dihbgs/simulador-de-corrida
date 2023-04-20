public class Tire {
  private boolean isCalibrated;

  public Tire() {
    this.isCalibrated = (Math.random() > 0.5);
  }

  public void setCalibration(boolean isCalibrated) {
    this.isCalibrated = isCalibrated;
  }

  public boolean getCalibration() {
    return this.isCalibrated;
  }

  public String toString() {
    return "Roda " + (isCalibrated ? "calibrada." : "não calibrada.");
  }
}
