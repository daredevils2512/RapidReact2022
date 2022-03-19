package frc.robot.subsystems.dummy;

import edu.wpi.first.wpilibj.util.Color;
import frc.robot.subsystems.interfaces.LEDManager;

public class DummyLEDManager implements LEDManager {

  @Override
  public Color getColor() { return new Color(0, 0, 0); }

  @Override
  public void setColor(double saturation, int hue, int value) { }

  @Override
  public void toggleLEDs() { }

  @Override
  public void setEnabled(boolean wantsEnabled) { }

  @Override
  public void setHue(int newHue) { }

  @Override
  public void setValue(int newValue) { }

  @Override
  public Color getColor(int i) { return new Color(0, 0, 0); }
  
}
