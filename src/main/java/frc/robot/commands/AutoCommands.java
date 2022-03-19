package frc.robot.commands;

import javax.measure.Quantity;
import javax.measure.quantity.Time;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.subsystems.interfaces.Intake;
import frc.robot.subsystems.interfaces.Magazine;
import frc.robot.subsystems.interfaces.Shooter;
import si.uom.SI;

public final class AutoCommands {
  private AutoCommands() {}

  /** Auto command that only drives back.
   * @param drivetrain The drivetrain subsystem to use
   * @param speed The speed to go during autonomous (should be negative to go backwards)
   * @param driveTime The amount of time to drive backwards. It should be calculated based off of the length to go and the speed to go there.
   * @return The command to be used when called.
   */
  public static Command autoDriveBack(Drivetrain drivetrain, double speed, Quantity<Time> driveTime) {
    return new RunCommand(() -> Commands.drive(drivetrain, () -> speed, () -> 0.0).withTimeout(driveTime.to(SI.SECOND).getValue().doubleValue()).andThen(Commands.drive(drivetrain, () -> 0, () -> 0)));
  }

  /** Auto command that only shoots.
   * @param shooter Shooter subsystem to use.
   * @param mag Magazine subsystem to use.
   * @param intake Intake subsystem to use.
   * @param speed The speed for everything.
   * @return The command to be used when called.
   */
  public static Command autoShoot(Shooter shooter, Magazine mag, Intake intake, double speed) {
    return new RunCommand(() -> Commands.revShooter(shooter, speed).withTimeout(6).andThen(Commands.runMag(mag, () -> speed).withTimeout(5).alongWith(Commands.runIntake(intake, () -> speed).withTimeout(5))).andThen(Commands.revShooter(shooter, 0)));
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
  public static Command fullAuto(Drivetrain drivetrain, double driveSpeed, Quantity<Time> driveTime, Shooter shooter, Magazine mag, Intake intake, double shootSpeed) {
    return autoDriveBack(drivetrain, driveSpeed, driveTime).andThen(autoShoot(shooter, mag, intake, shootSpeed));
  }
}