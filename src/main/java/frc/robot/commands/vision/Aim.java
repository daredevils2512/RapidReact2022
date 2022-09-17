package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.subsystems.interfaces.Limelight;
import frc.robot.utils.Constants;

public class Aim extends CommandBase {
  private final Drivetrain drivetrain;
  
  private Limelight limelight;
  
  public Aim(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;
  }

  /** Called when the command is initially scheduled. */
  @Override
  public void initialize() {
   
  }

  /** Called every time the scheduler runs while the command is scheduled. */
  @Override
  public void execute() {
    
    double aimAjust = Constants.K_P * limelight.tx(); 
    drivetrain.arcadeDrive(0, aimAjust);
  }

  /** Called once the command ends or is interrupted. */
  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      drivetrain.arcadeDrive(0.0, 0.0);
    }
  }

  /** Returns true when the command should end. */
  @Override
  public boolean isFinished() {
    return false;
  }
}
