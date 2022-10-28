package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.vision.Aim;
import frc.robot.commands.vision.FindRange;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.subsystems.interfaces.Limelight;
import frc.robot.subsystems.vision.LimelightLEDMode;

public final class VisionCommands {
  private VisionCommands() { }

  /** Aims the robot left and right with the goal
   * @param drivetrain The drivetrain subsystem to use.
   * @return The command to run.
   */
  public static Command aim(Drivetrain drivetrain) {
    return new Aim(drivetrain);
  }

  /** Gets the robot within range to shoot
   * @param drivetrain The drivetrain subsystem to use.
   * @return The command to run.
   */
  public static Command findRange(Drivetrain drivetrain) {
    return new FindRange(drivetrain);
  }

  /** Turns the Limelight off
   * @param limelight The limelight to use.
   * @return The command to run.
   */
  public static Command limelightOff(Limelight limelight) {
    return new InstantCommand(() -> { limelight.setLEDMode(LimelightLEDMode.OFF); });
  }

  /** Turns the limelight on
   * @param limelight The limelight to use.
   * @return The command to run.
   */
  public static Command limelightOn(Limelight limelight) {
    return new InstantCommand(() -> { limelight.setLEDMode(LimelightLEDMode.ON); });
  }
  
}
