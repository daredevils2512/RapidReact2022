package frc.robot.subsystems.physical;

import edu.wpi.first.networktables.NetworkTableEntry;
import frc.robot.subsystems.NTSubsystem;
import frc.robot.subsystems.interfaces.CompresserManager;
import frc.robot.utils.Constants;
import edu.wpi.first.wpilibj.Compressor;

public class PhysicalCompressor extends NTSubsystem implements CompresserManager {
  private final NetworkTableEntry isRunningEntry;
  private final NetworkTableEntry closedLoopControlEntry;

  private Compressor compressor = new Compressor(Constants.PNEUMATICS_MODULE_TYPE);
 
  public PhysicalCompressor() {
    super("Compressor");
    isRunningEntry = table.getEntry("Is running");
    closedLoopControlEntry = table.getEntry("closed loop control");
  }

  @Override
  public void periodic() {}
      
  @Override
  public void setClosedLoopControl(boolean wantsClosedLoopControl) {
    if (wantsClosedLoopControl) {
      compressor.enableDigital();
    } else {
      compressor.disable();
    }
    logger.fine("Compressor closed loop control: " + getClosedLoopControl());
  }
    
  @Override
  public boolean getClosedLoopControl() {
    return compressor.enabled();
  }

  @Override
  public void toggleCompressor() {
    if (getClosedLoopControl()) {
      compressor.disable(); 
    } else {
      compressor.enableDigital();
    }
  }
}