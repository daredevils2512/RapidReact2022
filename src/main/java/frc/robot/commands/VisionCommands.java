package frc.robot.commands;

import javax.measure.Quantity;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.utils.Constants;
import frc.robot.vision.Limelight;
import frc.robot.vision.LimelightLEDMode;
import si.uom.SI;
import systems.uom.common.USCustomary;
import tech.units.indriya.quantity.Quantities;

public final class VisionCommands {
  private VisionCommands() {}

  // Limelight network table
  static NetworkTable m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

  /** Aims the robot automatically
   * @param drivetrian The drivetrain subsystem to use.
   * @return The command to be used when called.
   */
  public static Command Aim(Drivetrain drivetrain) {
    double tx = m_limelightTable.getEntry("tx").getDouble(0);
    double turnAdjust = Constants.K_P * tx;

    return Commands.drive(drivetrain, () -> 0.0, () -> turnAdjust);
  }

  /** Automatically moves the robot to a certain distance away from the goalpost
   * <p>distanceFromGoal = (goal height - limelight lens height) / tangent of the angle to the goal
   * @param drivetrain The drivetrain subsystem to use.
   * @return The command to be used when called.
   */
  public static Command findRange(Drivetrain drivetrain) {
    Quantity<Angle> ty = Quantities.getQuantity(m_limelightTable.getEntry("ty").getDouble(0), USCustomary.DEGREE_ANGLE);
    Quantity<Angle> angleToGoal = Constants.LIMELIGHT_MOUNT_ANGLE.add(ty);
    Quantity<Length> distanceFromGoal = (Constants.GOAL_HEIGHT.subtract(Constants.LIMELIGHT_LENS_HEIGHT)).divide(Math.tan(angleToGoal.to(SI.RADIAN).getValue().doubleValue()));
    Quantity<Length> distanceVariation = Constants.DESIRED_DISTANCE.subtract(distanceFromGoal);
    double moveAjust = Constants.K_P * distanceVariation.to(USCustomary.INCH).getValue().doubleValue();

    return Commands.drive(drivetrain, () -> moveAjust, () -> 0.0);
  }

  /** Command that turns the limelight on
   * @param limelight The limelight subsystem to use.
   * @return The command to be used when called.
   */
  public static Command turnOnLimelight(Limelight limelight) {
    return new RunCommand(() -> limelight.setLEDMode(LimelightLEDMode.ON)); // TODO This should only run once so consider replacing with InstantCommand
  }

  /** Command that turns the limelight off
   * @param limelight The limelight subsystem to use.
   * @return The command to be used when called.
   */
  public static Command turnOffLimelight(Limelight limelight) {
    return new RunCommand(() -> limelight.setLEDMode(LimelightLEDMode.OFF)); // TODO Should replace with InstantCommand
  }
}
