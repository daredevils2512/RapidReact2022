package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.auto.AutoDriveBack;
import frc.robot.commands.auto.AutoShoot;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.subsystems.interfaces.Magazine;
import frc.robot.subsystems.interfaces.Shooter;

public final class AutoCommands {
  private AutoCommands() {}
  
  /** Auto command that only drives back.
  * @param drivetrain The drivetrain subsystem to use
  * @param speed The speed to go during autonomous (should be negative to go backwards)
  * @param driveDistance The distance to drive backwards
  * @return The command to be used when called.
  */
 public static Command autoDriveBack(Drivetrain drivetrain, double speed, double driveDistance) {
   return new AutoDriveBack(drivetrain, speed, driveDistance);
 }

 /** Auto command that only shoots.
  * @param shooter Shooter subsystem to use.
  * @param mag Magazine subsystem to use.
  * @param intake Intake subsystem to use.
  * @param speed The speed for everything.
  * @return The command to be used when called.
  */
  public static Command autoShoot(Shooter shooter, Magazine mag, double shootSpeed) {
    // TODO: Can this code be deleted?
    // return Commands.revShooter(shooter, shootSpeed)
    // .withTimeout(3)
    // .andThen(Commands.revShooter(shooter, shootSpeed))
    // .alongWith(Commands.runMag(magazine, () -> 1.0))
    // .withTimeout(6);
    return new AutoShoot(shooter, mag, shootSpeed);
  }

  /** Auto comamnd that drives back and then shoots.
   * @param drivetrain Drivetrain subsystem to use.
   * @param driveSpeed The speed to drive during autonomous (should be negative to go backwards)
   * @param driveTime The amount of time to drive backwards. It should be calculated based off of the length to go and the speed to go there.
   * @param shooter Shooter subsystem to use.
   * @param mag Magazine subsystem to use.
   * @param intake Intake subsystem to use.
   * @param shootSpeed The speed to shoot the balls
   * @return The command to be used when called.
   */
  public static Command fullAuto(Drivetrain drivetrain, double driveSpeed, double driveTime, Shooter shooter, Magazine mag, double shootSpeed) {
    return autoShoot(shooter, mag, shootSpeed)
    .andThen(autoDriveBack(drivetrain, driveSpeed, driveTime)
    .withTimeout(3));
  }
}