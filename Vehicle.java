public class Vehicle {
  private static final float FUEL_COST = 0.55f;
  private static final String[] MODEL_ONE = {
    "    ____\n",
    " __/  |_ \\_\n",
    "|  _     _``-.\n",
    "'-(_)---(_)--'\n\n"
  };

  private static final String[] MODEL_TWO = {
    "  ______\n",
    " /|_||_\\`.__\n",
    "(   _    _ _\\ \n",
    "=`-(_)--(_)-'\n\n"
  };

  private final String[] MODEL;
  private final Tire[] TIRES;
  private final int ID;

  private float fuel;
  private int distance;
  private boolean hasIPVA;

  public Vehicle() {
    this.fuel = 2.5f;
    this.distance = 0;
    this.TIRES = new Tire[4];
    this.hasIPVA = (Math.random() > 0.5);
    this.MODEL = (Math.random() > 0.5) ? Vehicle.MODEL_ONE : Vehicle.MODEL_TWO;

    for(int i=0; i<4; i++) {
      this.TIRES[i] = new Tire();
    }

    this.ID = (this.TIRES.hashCode() * this.getHashSum()) % 100000;
  }

  public void move() {
    if(!this.canMove()) {
      return;
    }

    this.distance += 1;
  }

  private boolean hasEnoughFuel() {
    return this.fuel >= Vehicle.FUEL_COST;
  }

  private boolean isFullyCalibrated() {
    for(int i=0; i<4; i++) {
      if(!this.TIRES[i].getCalibration()) {
        return false;
      }
    }

    return true;
  }

  private boolean canMove() {
    return !(this.hasEnoughFuel() && this.isFullyCalibrated() && this.hasIPVA);
  }

  public void draw() {
    String blocksMoved = "";

    for(int i=0; i<distance; i++) {
      blocksMoved += "     ";
    }

    for(int i=0; i<4; i++) {
      System.out.print(blocksMoved + this.MODEL[i]);
    }
  }

  private int getHashSum() {
    int sum = 0;
    Tire currentTire;

    for(int i=0; i<4; i++) {
      currentTire = this.TIRES[i];
      sum += currentTire.hashCode() * (currentTire.getCalibration() ? 1 : 0);
    }

    return sum;
  }
}
