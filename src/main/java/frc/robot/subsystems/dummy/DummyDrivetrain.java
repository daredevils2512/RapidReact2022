package frc.robot.subsystems.dummy;

import frc.robot.subsystems.interfaces.Drivetrain;

public class DummyDrivetrain implements Drivetrain {

  @Override
  public void arcadeDrive(double move, double turn) { }

  @Override
  public void setLowGear(boolean wantsLowGear) { }

  @Override
  public void toggleShifters() { }

  @Override
  public double getRightDistance() {return 0; }

  @Override
  public double getLeftDistance() { return 0; }

  @Override
  public boolean getLowGear() { return false; }

  @Override
  public int getLeftEncoder() { return 0; }

  @Override
  public int getRightEncoder() { return 0; }

  @Override
  public double getDistance() { return 0; }
  
}
