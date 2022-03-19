package frc.robot.subsystems.dummy;

import frc.robot.subsystems.interfaces.CompresserManager;

public class DummyCompressor implements CompresserManager {

  @Override
  public void setClosedLoopControl(boolean wantsClosedLoopControl) { }

  @Override
  public boolean getClosedLoopControl() { return false; }

  @Override
  public void toggleCompressor() { }
  
}
