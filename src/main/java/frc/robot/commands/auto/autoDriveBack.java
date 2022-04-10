package frc.robot.commands.auto;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.interfaces.Drivetrain;

public class AutoDriveBack extends CommandBase {
  private final Drivetrain m_drivetrain;
  private double m_initialDistance = 0;
  private final double m_distance;
  private final double m_speed;

  public AutoDriveBack(Drivetrain drivetrain, double speed, double distance) {
    m_drivetrain = drivetrain;
    m_speed = speed;
    m_distance = distance;
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    m_initialDistance = m_drivetrain.getDistance();
  }

  @Override
  public void execute() {
    m_drivetrain.arcadeDrive(m_speed, 0);
  }

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    if (m_distance < 0) {
      return m_drivetrain.getDistance() - m_initialDistance < m_distance;
    } else {
      return m_drivetrain.getDistance() - m_initialDistance > m_distance;
    }
  }
}
