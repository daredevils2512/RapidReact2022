package frc.robot.commands.auto;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.interfaces.Drivetrain;

public class AutoDriveBack extends CommandBase {
  private final Drivetrain drivetrain;
  private double initialDistance = 0;
  private final double distance;
  private final double speed;

  public AutoDriveBack(Drivetrain drivetrain, double speed, double distance) {
    this.drivetrain = drivetrain;
    this.speed = speed;
    this.distance = distance;
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    initialDistance = drivetrain.getDistance();
  }

  @Override
  public void execute() {
    drivetrain.arcadeDrive(speed, 0);
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    if (distance < 0) {
      return drivetrain.getDistance() - initialDistance < distance;
    } else {
      return drivetrain.getDistance() - initialDistance > distance;
    }
  }
}
