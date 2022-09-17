package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.Commands;
import frc.robot.subsystems.interfaces.Magazine;
import frc.robot.subsystems.interfaces.Shooter;

public class AutoShoot extends CommandBase {
  private final Shooter shooter;
  private final Magazine mag;
  private final double speed;

  public AutoShoot(Shooter shooter, Magazine mag, double speed) {
    this.shooter = shooter;
		this.mag = mag;
    this.speed = speed;
    addRequirements(shooter, mag);
  }

  @Override
  public void initialize() { }

  @Override
  public void execute() {
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
