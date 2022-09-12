package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.Commands;
import frc.robot.subsystems.interfaces.Intake;
import frc.robot.subsystems.interfaces.Magazine;
import frc.robot.subsystems.interfaces.Shooter;

public class AutoShoot extends CommandBase {
  private final Shooter m_shooter;
  private final Magazine m_mag;
	private final Intake m_intake;
  private final double m_speed;

  public AutoShoot(Shooter shooter, Magazine mag, Intake intake, double speed) {
    m_shooter = shooter;
		m_mag = mag;
		m_intake = intake;
    m_speed = speed;
    addRequirements(shooter, mag, intake);
  }

  @Override
  public void initialize() { }

  @Override
  public void execute() {
    // Commands.revShooter(m_shooter, m_speed).withTimeout(6).andThen(Commands.runMag(m_mag, () -> m_speed).withTimeout(5).alongWith(Commands.runIntake(m_intake, () -> m_speed).withTimeout(5))).andThen(Commands.revShooter(m_shooter, 0));
    Commands.revShooter(m_shooter, m_speed)
    .withTimeout(3)
    .andThen(Commands.revShooter(m_shooter, m_speed))
    .alongWith(Commands.runMag(m_mag, () -> 1.0))
    .withTimeout(6);
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
