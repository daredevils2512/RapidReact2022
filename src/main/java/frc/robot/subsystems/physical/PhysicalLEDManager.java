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
  private final AddressableLED m_LED;
  private final AddressableLEDBuffer m_LEDBuffer;

  // Network table
  private final NetworkTable m_table;
  private final NetworkTableEntry m_LEDColor;

  // Time
  private double startTime;

  // Enabled
  private boolean enabled;

  // Saturation
  private int m_saturation =  255;

  // HSV Values
  private int m_hue = 180;
  private int m_value = 255;

  public PhysicalLEDManager() {
    super("LEDManager");

    // Network table
    m_table = NetworkTableInstance.getDefault().getTable("LEDs");
    m_LEDColor = m_table.getEntry("LED color");

    // LED
    m_LED = new AddressableLED(Constants.LED_PORT);
    m_LED.setLength(Constants.LED_LENGTH);

    // LED Buffer
    m_LEDBuffer = new AddressableLEDBuffer(Constants.LED_LENGTH);

    // Time
    startTime = Timer.getFPGATimestamp();

    // Enabled
    enabled = false;
  }
  
  @Override
  public void periodic() {
    if (enabled) {
      double colorSet = (Math.cos(Timer.getFPGATimestamp() - startTime) / 2 + 0.5);
      setColor(colorSet, m_hue, m_value);
      m_LEDColor.setValue(colorSet);
      m_LED.setData(m_LEDBuffer);
    }
  }

  @Override
  public Color getColor() {
    return m_LEDBuffer.getLED(1);
  }

  @Override
  public Color getColor(int i) {
    return m_LEDBuffer.getLED(i);
  }

  @Override
  public void setColor(double saturation, int hue, int value) {
    for (int i = 0; i <= Constants.LED_LENGTH; i++) {
      m_saturation = Constants.LED_MIN_S + (int)((Constants.LED_MAX_S - Constants.LED_MIN_S) * saturation);
      
      m_LEDBuffer.setHSV(i, hue, m_saturation, value);
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
      m_LED.start();
    } else {
      enabled = false;
      m_LED.stop();
    }
  }

  @Override
  public void setHue(int newHue) {
    m_hue = newHue;
  }

  @Override
  public void setValue(int newValue) {
    m_value = newValue;
  }
  
}
