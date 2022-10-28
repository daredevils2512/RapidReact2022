package frc.robot.subsystems.dummy;

import frc.robot.subsystems.interfaces.Shooter;

public class DummyShooter implements Shooter {

  @Override
  public void spitBalls(double speed) { }

  @Override
  public void setVoltage(double voltage) { }

  @Override
  public void setRPM(double RPM) { }

  @Override
  public void setRPMPID(double setpoint) { }

  @Override
  public double velocityToRPM(double velocity) { return 0; }

  @Override
  public double get() { return 0; }

  @Override
  public double getRPM() { return 0; }
  
}
