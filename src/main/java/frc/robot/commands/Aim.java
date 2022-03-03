package frc.robot.commands;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Vision.Limelight;
import frc.robot.Vision.LimelightLEDMode;
import frc.robot.subsystems.Drivetrain;

public class Aim extends CommandBase{
  private final Drivetrain m_drivetrain;
  private final NetworkTable m_limelightTable;
  private final NetworkTableEntry m_tx; 
  private Limelight m_limelight;
  
  private final Double Kp; 
  


  public Aim(Drivetrain drivetrain){
    m_drivetrain = drivetrain; 
    Kp = 0.2;
    m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    m_tx = m_limelightTable.getEntry("tx");
   

  }

  /** Called when the command is initially scheduled. */
  @Override
  public void initialize() {
  m_limelight.setLEDMode(LimelightLEDMode.ON);

  }

  /** Called every time the scheduler runs while the command is scheduled. */
  @Override
  public void execute() {
    double m_aimAjust = Kp * m_tx.getDouble(0); 
   m_drivetrain.arcadeDrive(0, m_aimAjust);
  }

  /** Called once the command ends or is interrupted. */
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.arcadeDrive(0,0);
    m_limelight.setLEDMode(LimelightLEDMode.OFF);
  }

  /** Returns true when the command should end. */
  @Override
  public boolean isFinished() {
    return false;
  }
}
