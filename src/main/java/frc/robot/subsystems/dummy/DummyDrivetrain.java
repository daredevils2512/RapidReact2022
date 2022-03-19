package frc.robot.subsystems.dummy;

import javax.measure.Quantity;
import javax.measure.quantity.Length;

import frc.robot.subsystems.interfaces.Drivetrain;

public class DummyDrivetrain implements Drivetrain {

  @Override
  public void arcadeDrive(double move, double turn) { }

  @Override
  public void setLowGear(boolean wantsLowGear) { }

  @Override
  public void toggleShifters() { }

  @Override
  public Quantity<Length> getRightDistance() { return null; }

  @Override
  public Quantity<Length> getLeftDistance() { return null; }

  @Override
  public boolean getLowGear() { return false; }

  @Override
  public int getLeftEncoder() { return 0; }

  @Override
  public int getRightEncoder() { return 0; }

  @Override
  public Quantity<Length> getDistance() { return null; }
  
}
