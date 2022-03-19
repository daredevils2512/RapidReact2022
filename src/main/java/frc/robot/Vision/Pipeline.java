package frc.robot.vision;

public enum Pipeline {
  N_E_D(0);

  private int m_id;

  private Pipeline(int id) {
    m_id = id;
  }

  public int getID() {
    return m_id;
  }
}
