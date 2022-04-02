package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.vision.Limelight;
import frc.robot.vision.LimelightLEDMode;
import frc.robot.subsystems.Drivetrain;
import frc.robot.utils.Constants;

public class FindRange extends CommandBase {
  private final Drivetrain m_drivetrain;
  private final NetworkTable m_limelightTable;
  private final NetworkTableEntry m_ty;
  private Limelight m_limelight;
  private Double driveAjust;
  //private final Double angleToGoalDegrees;
  
  private final Double Kpd;

    public FindRange(Drivetrain drivetrain){
      m_drivetrain = drivetrain; 
      Kpd= 0.2;
      m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
      m_ty = m_limelightTable.getEntry("ty");
     
    }
/** Called when the command is initially scheduled. */
@Override
public void initialize() {}

/** Called every time the scheduler runs while the command is scheduled. */
@Override
public void execute() {
  m_limelight.setLEDMode(LimelightLEDMode.ON);
  double angleToGoalDegrees = Constants.limelightMountAngleDegrees + m_ty.getDouble(0.0);
  double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
  //whatever we want the distance to be. 
  

  //calculate distance
  double currentDistance = (Constants.goalHeightInches - Constants.limelightLensHeightInches)/Math.tan(angleToGoalRadians);
  double distanceVariation = Constants.desiredDistance - currentDistance;
  driveAjust= Kpd*distanceVariation;
 m_drivetrain.arcadeDrive(driveAjust, 0);
}

/** Called once the command ends or is interrupted. */
@Override
public void end(boolean interrupted) {
  m_limelight.setLEDMode(LimelightLEDMode.OFF);
  m_drivetrain.arcadeDrive(0, 0);

}

/** Returns true when the command should end. */
@Override
public boolean isFinished() {
  return false;
}    
}
