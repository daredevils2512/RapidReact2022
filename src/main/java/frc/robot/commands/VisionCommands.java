package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.utils.Constants;
import frc.robot.vision.Limelight;
import frc.robot.vision.LimelightLEDMode;

public final class VisionCommands {
  private VisionCommands() {}

  // Limelight network table
  static NetworkTable m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

  /** Aims the robot automatically
   * @param drivetrian The drivetrain subsystem to use.
   * @return The command to be used when called.
   */
  public static Command Aim(Drivetrain drivetrain) {
    double tx = m_limelightTable.getEntry("tx").getDouble(0);
    double turnAdjust = Constants.K_P * tx;

    return Commands.drive(drivetrain, () -> 0.0, () -> turnAdjust);
  }

  /** Automatically moves the robot to a certain distance away from the goalpost
   * @param drivetrain The drivetrain subsystem to use.
   * @return The command to be used when called.
   */
  public static Command findRange(Drivetrain drivetrain) {
    double ty = m_limelightTable.getEntry("ty").getDouble(0);
    double angleToGoalDegrees = Constants.LIMELIGHT_MOUNT_ANGLE_DEGREES + ty;
    double angleToGoalRadians = angleToGoalDegrees * (Math.PI / 180.0); // TODO Consider replacing with Math.toRadians(angleToGoalDegrees)
    double currentDistance = (Constants.GOAL_HEIGHT - Constants.LIMELIGHT_LENS_HEIGHT) / Math.tan(angleToGoalRadians);
    double distanceVariation = Constants.DESIRED_DISTANCE - currentDistance;
    double moveAjust = Constants.K_P * distanceVariation; // TODO Misspelled adjust

    return Commands.drive(drivetrain, () -> moveAjust, () -> 0.0);
  }

  /** Command that turns the limelight on
   * @param limelight The limelight subsystem to use.
   * @return The command to be used when called.
   */
  public static Command turnOnLimelight(Limelight limelight) {
    return new RunCommand(() -> limelight.setLEDMode(LimelightLEDMode.ON)); // TODO This should only run once so consider replacing with InstantCommand
  }

  /** Command that turns the limelight off
   * @param limelight The limelight subsystem to use.
   * @return The command to be used when called.
   */
  public static Command turnOffLimelight(Limelight limelight) {
    return new RunCommand(() -> limelight.setLEDMode(LimelightLEDMode.OFF)); // TODO Should replace with InstantCommand
  }
}
