package frc.robot.subsystems.interfaces;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface Intake extends Subsystem {
  /** Sets shifters to extend or not
   * @param wantsExtended Whether to extend shifters
   */
  void setExtended(boolean wantsExtended);

  /** Toggles shifters on / off */
  void toggleExtended();

  /** Sets the intake to a certain speed
   * @param speed The speed to set the motors at
   */
  void setIntake(double speed);

  /** @return True if the shifters are extended */
  boolean getExtended();
}
