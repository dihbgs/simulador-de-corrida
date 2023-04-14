public class Vehicle {
  private static final float fuelCost = 0.55f;

  private final Tire[] tires;
  private final int id;

  private float fuel;
  private int distance;
  private boolean hasIPVA;

  public Vehicle() {
    this.fuel = 2.5f;
    this.distance = 0;
    this.tires = new Tire[4];
    this.hasIPVA = (Math.random() > 0.5);
    this.id = (this.tires.hashCode() * this.getHashSum()) % 100000;
  }

  public static void main(String[] args) {
    Vehicle test = new Vehicle();

    test.
  }

  public get

  private int getHashSum() {
    int sum = 0;
    Tire currentTire;

    for(int i=0; i<4; i++) {
      currentTire = this.tires[i];
      sum += currentTire.hashCode() * currentTire.isCalibrated;
    }

    return sum;
  }
}
