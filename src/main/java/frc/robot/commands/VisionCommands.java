package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.vision.Limelight;
import frc.robot.vision.LimelightLEDMode;
import frc.robot.subsystems.Drivetrain;
import frc.robot.utils.Constants;

public final class VisionCommands {
  private VisionCommands() {}

  static NetworkTable m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

  public static Command Aim(Drivetrain drivetrain, Limelight limelight) {
    limelight.setLEDMode(LimelightLEDMode.ON);

    double tx = m_limelightTable.getEntry("tx").getDouble(0);

    return new RunCommand(() -> drivetrain.arcadeDrive(0, Constants.K_P * tx));
  }

  public static Command findRange(Drivetrain drivetrain, Limelight limelight) {
    limelight.setLEDMode(LimelightLEDMode.ON);

    double ty = m_limelightTable.getEntry("ty").getDouble(0);
    double angleToGoalDegrees = Constants.LIMELIGHT_MOUNT_ANGLE_DEGREES + ty;
    double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
    double currentDistance = (Constants.GOAL_HEIGHT - Constants.LIMELIGHT_LENS_HEIGHT) / Math.tan(angleToGoalRadians);
    double distanceVariation = Constants.DESIRED_DISTANCE - currentDistance;
    double driveAjust = Constants.K_P * distanceVariation;

    return new RunCommand(() -> drivetrain.arcadeDrive(driveAjust, 0));
  }
}
