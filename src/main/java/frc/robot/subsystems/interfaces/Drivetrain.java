package frc.robot.subsystems.interfaces;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface Drivetrain extends Subsystem {
  /** Runs the arcade drive 
   * @param move Speed for forward/backward movement
   * @param turn Speed for left/right movement
  */
  void arcadeDrive(double move, double turn);

  /** Sets low gear only if it wants to
   * @param wantsLowGear if it wants to set low gear
   */
  void setLowGear(boolean wantsLowGear);

  /** Toggles the shifters */
  void toggleShifters();

  /** @return Left encoder */
  int getLeftEncoder();

  /** @return Right encoder */
  int getRightEncoder();

  /** @return Right distance */
  double getRightDistance();

  /** @return Left distance */
  double getLeftDistance();

  /** @return True if shifter are in low gear */
  boolean getLowGear();

  /** @return Distance that the drivetrain has moved */
  double getDistance();
}
