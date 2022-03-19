package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.auto.autoDriveBack;
import frc.robot.commands.auto.autoShoot;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.subsystems.interfaces.Intake;
import frc.robot.subsystems.interfaces.Magazine;
import frc.robot.subsystems.interfaces.Shooter;

public final class AutoCommands {
  private AutoCommands() {}

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
  public static Command fullAuto(Drivetrain drivetrain, double driveSpeed, double driveTime, Shooter shooter, Magazine mag, Intake intake, double shootSpeed) {
    return new autoDriveBack(drivetrain, driveSpeed, driveTime).andThen(new autoShoot(shooter, mag, intake, shootSpeed));
  }
}