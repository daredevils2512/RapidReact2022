package frc.robot.vision;

public enum Pipeline {
  N_E_D(0);

  private int m_id; // TODO can be final

  private Pipeline(int id) { // TODO Modifier 'private' is redundant for enum constructors
    m_id = id;
  }

  public int getID() {
    return m_id;
  }
}
