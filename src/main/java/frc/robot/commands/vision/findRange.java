package frc.robot.commands.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.subsystems.interfaces.Limelight;
import frc.robot.subsystems.vision.LimelightLEDMode;
import frc.robot.utils.Constants;


public class FindRange extends CommandBase {
  private final NetworkTable limelightTable;

  private final Drivetrain drivetrain;

  private double ty;
  private double angleToGoalDegrees;
  private double angleToGoalRadians;
  private double currentDistance;
  private double distanceVariation;
  private double driveAjust;
  private Limelight limelight;
  
  public FindRange(Drivetrain drivetrain) {
    limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    
    this.drivetrain = drivetrain;
  }

  @Override
  public void initialize() {
    limelight.setLEDMode(LimelightLEDMode.ON);
  }

  @Override
  public void execute() {
    ty = limelightTable.getEntry("ty").getDouble(0);
    angleToGoalDegrees = Constants.LIMELIGHT_MOUNT_ANGLE_DEGREES + ty;
    angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
    currentDistance = (Constants.GOAL_HEIGHT - Constants.LIMELIGHT_LENS_HEIGHT) / Math.tan(angleToGoalRadians);
    distanceVariation = Constants.DESIRED_DISTANCE - currentDistance;
    driveAjust = Constants.K_P * distanceVariation;
    
    drivetrain.arcadeDrive(driveAjust, 0.0);
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      limelight.setLEDMode(LimelightLEDMode.OFF);
      drivetrain.arcadeDrive(0.0, 0.0);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
