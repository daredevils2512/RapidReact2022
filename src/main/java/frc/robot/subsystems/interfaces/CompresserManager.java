package frc.robot.subsystems.interfaces;

public interface CompresserManager {
  /** Enables closed loop control
   * @param wantsClosedLoopControl Whether to enable closed loop control
   */
  void setClosedLoopControl(boolean wantsClosedLoopControl);

  /** @return If closed loop control is enabled */
  boolean getClosedLoopControl();

  /** Toggles the compressor */
  void toggleCompressor();
}