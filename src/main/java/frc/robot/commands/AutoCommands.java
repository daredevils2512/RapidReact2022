package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.auto.autoDriveBack;
import frc.robot.commands.auto.autoShoot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

public final class AutoCommands extends CommandBase {
  private AutoCommands() {}

  public static Command fullAuto(Drivetrain drivetrain, double driveSpeed, double driveTime, Shooter shooter, Magazine mag, Intake intake, double shootSpeed) {
    return new autoDriveBack(drivetrain, driveSpeed, driveTime).andThen(new autoShoot(shooter, mag, intake, shootSpeed));
  }
}