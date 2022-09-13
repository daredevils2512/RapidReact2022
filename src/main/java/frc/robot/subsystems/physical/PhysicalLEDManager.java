package frc.robot.subsystems.physical;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.subsystems.NTSubsystem;
import frc.robot.subsystems.interfaces.LEDManager;
import frc.robot.utils.Constants;

public class PhysicalLEDManager extends NTSubsystem implements LEDManager {
  // LED stuffs
  private final AddressableLED LED;
  private final AddressableLEDBuffer LEDBuffer;

  // Network table
  private final NetworkTable table;
  private final NetworkTableEntry LEDColor;

  // Time
  private double startTime;

  // Enabled
  private boolean enabled;

  // Saturation
  private int saturation =  255;

  // HSV Values
  private int hue = 180;
  private int value = 255;

  public PhysicalLEDManager() {
    super("LEDManager");

    // Network table
    table = NetworkTableInstance.getDefault().getTable("LEDs");
    LEDColor = table.getEntry("LED color");

    // LED
    LED = new AddressableLED(Constants.LED_PORT);
    LED.setLength(Constants.LED_LENGTH);

    // LED Buffer
    LEDBuffer = new AddressableLEDBuffer(Constants.LED_LENGTH);

    // Time
    startTime = Timer.getFPGATimestamp();

    // Enabled
    enabled = false;
  }
  
  @Override
  public void periodic() {
    if (enabled) {
      double colorSet = (Math.cos(Timer.getFPGATimestamp() - startTime) / 2 + 0.5);
      setColor(colorSet, hue, value);
      LEDColor.setValue(colorSet);
      LED.setData(LEDBuffer);
    }
  }

  @Override
  public Color getColor() {
    return LEDBuffer.getLED(1);
  }

  @Override
  public Color getColor(int i) {
    return LEDBuffer.getLED(i);
  }

  @Override
  public void setColor(double saturation, int hue, int value) {
    for (int i = 0; i <= Constants.LED_LENGTH; i++) {
      this.saturation = Constants.LED_MIN_S + (int)((Constants.LED_MAX_S - Constants.LED_MIN_S) * saturation);
      
      LEDBuffer.setHSV(i, hue, this.saturation, value);
    }
  }

  @Override
  public void toggleLEDs() {
    setEnabled(!enabled);
  }

  @Override
  public void setEnabled(boolean wantsEnabled) {
    if (wantsEnabled) {
      enabled = true;
      LED.start();
    } else {
      enabled = false;
      LED.stop();
    }
  }

  @Override
  public void setHue(int newHue) {
    hue = newHue;
  }

  @Override
  public void setValue(int newValue) {
    value = newValue;
  }
  
}
