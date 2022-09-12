package frc.robot.commands.vision;

import javax.measure.Quantity;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.Commands;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.utils.Constants;
import frc.robot.vision.Limelight;
import frc.robot.vision.LimelightLEDMode;
import si.uom.SI;
import systems.uom.common.USCustomary;
import tech.units.indriya.quantity.Quantities;


public class FindRange extends CommandBase {
  private final NetworkTable m_limelightTable;

  private final Drivetrain m_drivetrain;

  private Quantity<Angle> m_ty;
  private Quantity<Angle> m_angleToGoal;
  private Quantity<Length> m_distanceFromGoal;
  private Quantity<Length> m_distanceVariation;
  private double m_driveAdjust;
  private Limelight m_limelight;
  
  public FindRange(Drivetrain drivetrain) {
    m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    
    m_drivetrain = drivetrain;
  }

  @Override
  public void initialize() {
    m_limelight.setLEDMode(LimelightLEDMode.ON);
  }

  @Override
  public void execute() {
    m_ty = Quantities.getQuantity(m_limelightTable.getEntry("ty").getDouble(0), USCustomary.DEGREE_ANGLE);
    m_angleToGoal = Constants.LIMELIGHT_MOUNT_ANGLE.add(m_ty);
    m_distanceFromGoal = (Constants.GOAL_HEIGHT.subtract(Constants.LIMELIGHT_LENS_HEIGHT)).divide(Math.tan(m_angleToGoal.to(SI.RADIAN).getValue().doubleValue()));
    m_distanceVariation = Constants.DESIRED_DISTANCE.subtract(m_distanceFromGoal);
    m_driveAdjust = Constants.K_P * m_distanceVariation.to(USCustomary.INCH).getValue().doubleValue();

    Commands.drive(m_drivetrain, () -> m_driveAdjust, () -> 0.0);
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      m_limelight.setLEDMode(LimelightLEDMode.OFF);
      m_drivetrain.arcadeDrive(0.0, 0.0);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
