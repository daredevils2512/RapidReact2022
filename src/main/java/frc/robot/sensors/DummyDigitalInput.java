package frc.robot.sensors;

public class DummyDigitalInput implements DareDigitalInput {
  public DummyDigitalInput() {}

  @Override
  public boolean get() {
    return false;
  }
  
}
