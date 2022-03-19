package frc.robot.subsystems.dummy;

import frc.robot.subsystems.interfaces.Intake;

public class DummyIntake implements Intake {

  @Override
  public void setExtended(boolean wantsExtended) { }

  @Override
  public void toggleExtended() { }

  @Override
  public void setIntake(double speed) { }

  @Override
  public boolean getExtended() { return false; }
  
}
