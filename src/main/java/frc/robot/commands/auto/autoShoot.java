package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.Commands;
import frc.robot.subsystems.interfaces.Intake;
import frc.robot.subsystems.interfaces.Magazine;
import frc.robot.subsystems.interfaces.Shooter;

public class AutoShoot extends CommandBase {
  private final Shooter shooter;
  private final Magazine mag;
	private final Intake intake;
  private final double speed;

  public AutoShoot(Shooter shooter, Magazine mag, Intake intake, double speed) {
    this.shooter = shooter;
		this.mag = mag;
		this.intake = intake;
    this.speed = speed;
    addRequirements(shooter, mag, intake);
  }

  @Override
  public void initialize() { }

  @Override
  public void execute() {
    // TODO: What is this on the next line? Can it be deleted?
    // Commands.revShooter(m_shooter, m_speed).withTimeout(6).andThen(Commands.runMag(m_mag, () -> m_speed).withTimeout(5).alongWith(Commands.runIntake(m_intake, () -> m_speed).withTimeout(5))).andThen(Commands.revShooter(m_shooter, 0));
    Commands.revShooter(shooter, speed)
    .withTimeout(3)
    .andThen(Commands.revShooter(shooter, speed))
    .alongWith(Commands.runMag(mag, () -> 1.0))
    .withTimeout(6);
  }

  @Override
  public void end(boolean interrupted) {
    Commands.revShooter(shooter, 0.0);
  }

  @Override
  public boolean isFinished() {
		return false;
  }
}
