package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.Commands;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

public class autoShoot extends CommandBase {
  private final Shooter m_shooter;
  private final Magazine m_mag;
	private final Intake m_intake;
  private final double m_speed;

  public autoShoot(Shooter shooter, Magazine mag, Intake intake, double speed) {
    m_shooter = shooter;
		m_mag = mag;
		m_intake = intake;
    m_speed = speed;
  }

  @Override
  public void initialize() { }

  @Override
  public void execute() {
    Commands.revShooter(m_shooter, m_speed).withTimeout(6).andThen(Commands.runMag(m_mag, () -> m_speed).withTimeout(5).alongWith(Commands.runIntake(m_intake, () -> m_speed).withTimeout(5))).andThen(Commands.revShooter(m_shooter, 0));
  }

  @Override
  public void end(boolean interrupted) {
    Commands.revShooter(m_shooter, 0.0);
  }

  @Override
  public boolean isFinished() {
		return false;
  }
}
