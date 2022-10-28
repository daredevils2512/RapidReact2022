package frc.robot.subsystems.interfaces;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface Climber extends Subsystem {
  /** Sets the speed of the climbers
   * @param speed The speed to set the motors
   */
  void setClimbSpeed(double speed);
}

