package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

public final class Commands {
  private Commands() {}
  
  public static Command intakeShifters(Intake intake) {
    return new RunCommand(() -> intake.toggleExtended());
  }

  public static Command runIntake(Intake intake, DoubleSupplier speed) {
    return new FunctionalCommand(() -> { }, () -> intake.setIntake(speed.getAsDouble()), (interrupted) -> intake.setIntake(0.0), () -> false, intake);
  }

  public static Command runClimber(Climber climber, double speed) {
    return new FunctionalCommand(() -> { }, () -> climber.setClimbSpeed(speed), (interrupted) -> climber.setClimbSpeed(0.0), () -> false, climber);
  }

  public static Command driveShifters(Drivetrain drivetrain) {
    return new InstantCommand(() -> drivetrain.toggleShifters());
  }

  public static Command drive(Drivetrain drivetrain, DoubleSupplier move, DoubleSupplier turn) {
    return new FunctionalCommand(() -> { }, () -> drivetrain.arcadeDrive(move.getAsDouble(), turn.getAsDouble()), (interrupted) -> drivetrain.arcadeDrive(0.0, 0.0), () -> false, drivetrain);
  }

  public static Command revShooter(Shooter shooter, double speed) {
    return new FunctionalCommand(() -> { }, () -> shooter.setRPM(speed), (interrupted) -> shooter.setRPM(0.0), () -> false, shooter);
  }

  public static Command runMag(Magazine mag, DoubleSupplier speed) {
    return new FunctionalCommand(() -> { }, () -> mag.moveBalls(speed.getAsDouble()), (interrputed) -> mag.moveBalls(0.0), () -> false, mag);
  }
}
