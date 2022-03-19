package frc.robot.commands.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.utils.Constants;
import frc.robot.vision.Limelight;
import frc.robot.vision.LimelightLEDMode;

public class findRange extends CommandBase {
  private final NetworkTable m_limelightTable;

  private final Limelight m_limelight;
  private final Drivetrain m_drivetrain;

  private double m_ty;
  private double m_angleToGoalDegrees;
  private double m_angleToGoalRadians;
  private double m_currentDistance;
  private double m_distanceVariation;
  private double m_driveAjust;
  
  public findRange(Limelight limelight, Drivetrain drivetrain) {
    m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

    m_limelight = limelight;
    m_drivetrain = drivetrain;
  }

  @Override
  public void initialize() {
    m_ty = m_limelightTable.getEntry("ty").getDouble(0);
    m_angleToGoalDegrees = Constants.LIMELIGHT_MOUNT_ANGLE_DEGREES + m_ty;
    m_angleToGoalRadians = m_angleToGoalDegrees * (3.14159 / 180.0);
    m_currentDistance = (Constants.GOAL_HEIGHT - Constants.LIMELIGHT_LENS_HEIGHT) / Math.tan(m_angleToGoalRadians);
    m_distanceVariation = Constants.DESIRED_DISTANCE - m_currentDistance;
    m_driveAjust = Constants.K_P * m_distanceVariation;
  }

  @Override
  public void execute() {
    m_limelight.setLEDMode(LimelightLEDMode.ON);

    m_drivetrain.arcadeDrive(m_driveAjust, 0.0);
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      m_drivetrain.arcadeDrive(0.0, 0.0);
      m_limelight.setLEDMode(LimelightLEDMode.OFF);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
