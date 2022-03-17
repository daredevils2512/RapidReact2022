package frc.robot.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class PhysicalDigitalInput implements DareDigitalInput {
  private DigitalInput digitalInput;

  public PhysicalDigitalInput(int channel) {
    digitalInput = new DigitalInput(channel);
  }

  @Override
  public boolean get() {
    return digitalInput.get();
  }
  
}
