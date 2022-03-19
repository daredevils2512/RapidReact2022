package frc.robot.subsystems.interfaces;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface Shooter extends Subsystem {
  /** Runs the shooter motors
   * @param speed The speed to move the motors
   */
  void spitBalls(double speed);

  /** Sets the voltage of the motors
   * @param voltage The voltage to set the motors to
   */
  void setVoltage(double voltage);

  /** Sets the RPM of the motors
   * @param RPM The RPM to set the motors at
   */
  void setRPM(double RPM);

  /** Sets the RPM with PID enabled
   * @param setpoint The setpoint for the motors
   */
  void setRPMPID(double setpoint);

  /** Converts velocity to RPM
   * @param velocity The velocity to convert to RPM
   * @return The velocity converted to RPM
   */
  double velocityToRPM(double velocity);

  /** @return The current speed of the shooter motor */
  double get();

  /** @return The current RPM of the motors */
  double getRPM();
}
