package frc.robot.subsystems.vision;

public enum Pipeline {
  N_E_D(0);

  private final int id; 

  Pipeline(int id) { 
    this.id = id;
  }

  public int getID() {
    return id;
  }
}
