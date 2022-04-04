package frc.robot.subsystems.interfaces;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface Climber extends Subsystem {
  /** Sets the speed of the climbers
   * @param speed The speed to set the motors
   */
  void setClimbSpeed(double speed);

  /** @return If the climbers are extended */
  boolean getExtended();

  /** Sets the shifters to the value you want
   * @param wantsExtended Whether you want the shifters extended or retracted
   */
  void setExtended(boolean wantsExtended);

  /** Toggle the shifters to be extended/retracted */
  void toggleExtended();
}

