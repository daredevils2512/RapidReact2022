package frc.robot.subsystems;

import java.util.logging.Logger;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.utils.Constants;
import edu.wpi.first.wpilibj.Compressor;

public class CompresserManager  extends NTSubsystem {
  private final NetworkTable m_networkTable;
  private final NetworkTableEntry m_isRunningEntry;
  private final NetworkTableEntry m_closedLoopControlEntry;

  private Compressor m_compressor = new Compressor(Constants.pneumaticsModuleType);
 
  public CompresserManager() {
    super("Compressor");

    m_networkTable = NetworkTableInstance.getDefault().getTable(getName());
    m_isRunningEntry = m_networkTable.getEntry("Is running");
    m_closedLoopControlEntry = m_networkTable.getEntry("closed loop control");
  }
    
  // public void periodic() {
  //   Logger.getGlobal().info("Switch: " + (m_compressor.getPressureSwitchValue() ? "low" : "high"));
  // }
      
  public void setClosedLoopControl(boolean wantsClosedLoopControl) {
    // m_compressor.setClosedLoopControl(wantsClosedLoopControl);
    if (wantsClosedLoopControl)  m_compressor.enableDigital(); else m_compressor.disable();
    m_logger.fine("Compressor closed loop control: " + getClosedLoopControl());
  }
    
  public boolean getClosedLoopControl() {
    return m_compressor.enabled();
  }

  public void toggleCompressor() {
    if (getClosedLoopControl())  m_compressor.disable() ; else m_compressor.enableDigital();
  }
}