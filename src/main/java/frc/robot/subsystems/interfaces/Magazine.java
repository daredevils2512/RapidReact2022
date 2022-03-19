package frc.robot.subsystems.interfaces;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface Magazine extends Subsystem {
  /** Runs the magazine motors
   * @param speed The speed to set the motors at
   */
  void moveBalls(double speed);
}
