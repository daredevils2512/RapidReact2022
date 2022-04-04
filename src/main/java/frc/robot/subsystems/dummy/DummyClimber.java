package frc.robot.subsystems.dummy;

import frc.robot.subsystems.interfaces.Climber;

public class DummyClimber implements Climber {

  @Override
  public void setClimbSpeed(double speed) { }

  @Override
  public boolean getExtended() { return false; }

  @Override
  public void setExtended(boolean wantsExtended) { }

  @Override
  public void toggleExtended() { }
  
}
