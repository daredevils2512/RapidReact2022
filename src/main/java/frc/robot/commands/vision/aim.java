package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.utils.Constants;
import frc.robot.vision.Limelight;

public class Aim extends CommandBase {


  private final Drivetrain m_drivetrain;
  
  
  private Limelight m_limelight;
  
  
  
  public Aim(Drivetrain drivetrain) {
    

    m_drivetrain = drivetrain;
  }

  /** Called when the command is initially scheduled. */
  @Override
  public void initialize() {
   
  }

  /** Called every time the scheduler runs while the command is scheduled. */
  @Override
  public void execute() {
    
    double m_aimAjust = Constants.K_P * m_limelight.tx(); 
   m_drivetrain.arcadeDrive(0, m_aimAjust);
  }

  /** Called once the command ends or is interrupted. */
  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      m_drivetrain.arcadeDrive(0.0, 0.0);
    }
  }

  /** Returns true when the command should end. */
  @Override
  public boolean isFinished() {
    return false;
  }
}