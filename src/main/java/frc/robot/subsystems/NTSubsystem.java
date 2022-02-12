package frc.robot.subsystems;

import java.util.logging.Level;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class NTSubsystem extends LoggingSubsystem {

  protected NetworkTable m_table;
  /** Creates a new NTsubsystem.
   * @param name sets name of table
   * @param logLevel set level for subsystem to display logs at (default: WARNING)
  */
  public NTSubsystem(String tableName, Level logLevel) {
    super(tableName, logLevel);
    m_table = NetworkTableInstance.getDefault().getTable(tableName);
  }
  
  /** Creates a new NTsubsystem.
   * @param tableName sets name of table
  */
  public NTSubsystem(String tableName) {
    super(tableName);
    m_table = NetworkTableInstance.getDefault().getTable(tableName);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
