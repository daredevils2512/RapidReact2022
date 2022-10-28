package frc.robot.subsystems.vision;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum LimelightLEDMode {
  PIPELINE(0),
  OFF(1),
  BLINK(2),
  ON(3);

  private static final Map<Integer, LimelightLEDMode> modeMap;

  private final int intValue;

  static {
    modeMap = new HashMap<>();
    for (LimelightLEDMode mode : EnumSet.allOf(LimelightLEDMode.class)) {
      modeMap.put(mode.getIntValue(), mode);
    }
  }

  public static LimelightLEDMode fromIntValue(int intValue) {
    return modeMap.get(intValue);
  }

  private LimelightLEDMode(int intValue) {
    this.intValue = intValue;
  }

  public int getIntValue() {
    return intValue;
  }
}