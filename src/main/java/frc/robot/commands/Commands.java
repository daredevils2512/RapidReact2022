package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.interfaces.Climber;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.subsystems.interfaces.Intake;
import frc.robot.subsystems.interfaces.LEDManager;
import frc.robot.subsystems.interfaces.Magazine;
import frc.robot.subsystems.interfaces.Shooter;

public final class Commands {
  private Commands() {}
  
  /** Runs the intake shifters
   * @param intake The intake subsystem to use.
   * @return The command to be used when called.
   */
  public static Command intakeShifters(Intake intake) {
    return new InstantCommand(() -> intake.toggleExtended(), intake); // TODO Lambda can be replaced with method reference (i.e. intake::toggleExtended)
  }

  /** Runs the intake motors
   * @param intake The intake subsystem to use.
   * @param speed The speed to go with the motors.
   * @return The command to be used when called.
   */
  public static Command runIntake(Intake intake, DoubleSupplier speed) {
    return new FunctionalCommand(() -> { }, () -> intake.setIntake(speed.getAsDouble()), (interrupted) -> intake.setIntake(0.0), () -> false, intake);
  }

  /** Toggles the climber shifters
   * @param climber The climber subsystem to use.
   * @return The command to be used when called.
   */
  public static Command climbingShifters(Climber climber) {
    return new InstantCommand(() -> climber.toggleExtended(), climber); // TODO Lambda can be replaced with method reference
  }

  /** Runs the climber
   * @param climber The climbing subsystem to use.
   * @param speed the speed to run the climber. Positive for up, negative for down.
   * @return The command to be used when called.
   */
  public static Command runClimber(Climber climber, double speed) {
    return new FunctionalCommand(() -> { }, () -> climber.setClimbSpeed(speed), (interrupted) -> climber.setClimbSpeed(0.0), () -> false, climber);
  }

  /** Runs the driving shifters
   * @param drivetrain The drivetrain subsystem to use.
   * @return The command to be used when called.
   */
  public static Command driveShifters(Drivetrain drivetrain) {
    return new InstantCommand(() -> drivetrain.toggleShifters(), drivetrain); // TODO Lambda can be replaced with method reference
  }

  /** Drives the robot
   * @param drivetrain The drivetrain subsystem to use.
   * @param move The speed to move the robot forwards (positive) and backwards (negative).
   * @param turn The speed to turn the robot left and right.
   * @return The command to be used when called.
   */
  public static Command drive(Drivetrain drivetrain, DoubleSupplier move, DoubleSupplier turn) {
    return new FunctionalCommand(() -> { }, () -> drivetrain.arcadeDrive(move.getAsDouble(), turn.getAsDouble()), (interrupted) -> drivetrain.arcadeDrive(0.0, 0.0), () -> false, drivetrain);
  }

  /** Revs the shooter (and shoots balls)
   * @param shooter The shooter subsystem to use.
   * @param speed The speed to move the motor.
   * @return The command to be used when called.
   */
  public static Command revShooter(Shooter shooter, double speed) {
    return new FunctionalCommand(() -> { }, () -> shooter.spitBalls(speed), (interrupted) -> shooter.setRPM(0.0), () -> false, shooter);
  }

  /** Runs the magazine motors
   * @param mag The magazine subsystem to use.
   * @param speed The speed to move the magazine motors.
   * @return The command to be used when called.
   */
  public static Command runMag(Magazine mag, DoubleSupplier speed) {
    return new FunctionalCommand(() -> { }, () -> mag.moveBalls(speed.getAsDouble()), (interrputed) -> mag.moveBalls(0.0), () -> false, mag);
  }

  /** Toggles the LEDs on and off
   * @param LED The LED subsystem to use.
   * @return The command to be used when called.
   */
  public static Command toggleLEDs(LEDManager LED) {
    return new RunCommand(() -> LED.toggleLEDs(), LED); // TODO Lambda can be replaced with method reference
  }
}
