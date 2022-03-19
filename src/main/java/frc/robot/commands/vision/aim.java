package frc.robot.commands.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.utils.Constants;
import frc.robot.vision.Limelight;
import frc.robot.vision.LimelightLEDMode;

public class aim extends CommandBase {
  private final NetworkTable m_limelightTable;

  private final Limelight m_limelight;
  private final Drivetrain m_drivetrain;

  private double m_tx;
  private double m_aim;
  
  public aim(Limelight limelight, Drivetrain drivetrain) {
    m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

    m_limelight = limelight;
    m_drivetrain = drivetrain;
  }

  @Override
  public void initialize() {
    m_limelight.setLEDMode(LimelightLEDMode.ON);

    m_tx = m_limelightTable.getEntry("tx").getDouble(0);
    m_aim = m_tx * Constants.K_P;
  }

  @Override
  public void execute() {
    m_drivetrain.arcadeDrive(0.0, m_aim);
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      m_limelight.setLEDMode(LimelightLEDMode.OFF);
    }
  }
  
  @Override
  public boolean isFinished() {
    return false;
  }

}
