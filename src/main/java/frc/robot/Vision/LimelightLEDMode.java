package frc.robot.vision;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum LimelightLEDMode {
  PIPELINE(0),
  OFF(1),
  BLINK(2),
  ON(3);

  private static final Map<Integer, LimelightLEDMode> m_modeMap;

  private final int m_intValue;

  static {
    m_modeMap = new HashMap<>();
    for (LimelightLEDMode mode : EnumSet.allOf(LimelightLEDMode.class)) {
      m_modeMap.put(mode.getIntValue(), mode);
    }
  }

  public static LimelightLEDMode fromIntValue(int intValue) {
    return m_modeMap.get(intValue);
  }

  private LimelightLEDMode(int intValue) {
    m_intValue = intValue;
  }

  public int getIntValue() {
    return m_intValue;
  }
}