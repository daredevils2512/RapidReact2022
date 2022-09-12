package frc.robot.commands;

import javax.measure.Quantity;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.vision.Aim;
import frc.robot.commands.vision.FindRange;
import frc.robot.commands.vision.LimelightOff;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.vision.Limelight;
import frc.robot.vision.LimelightLEDMode;
import si.uom.SI;
import systems.uom.common.USCustomary;
import tech.units.indriya.quantity.Quantities;

public final class VisionCommands {
  private VisionCommands() {}

  /** Aims the robot left and right with the goal
   * @param drivetrain The drivetrain subsystem to use.
   * @return The command to run.
   */
  public static Command aim(Drivetrain drivetrain) {
    return new Aim(drivetrain);
  }

  /** Automatically moves the robot to a certain distance away from the goalpost
   * <p>distanceFromGoal = (goal height - limelight lens height) / tangent of the angle to the goal
  /** Gets the robot within range to shoot
   * @param drivetrain The drivetrain subsystem to use.
   * @return The command to run.
   */
  public static Command findRange(Drivetrain drivetrain) {
    return new FindRange(drivetrain);
  }

  /** Turns the Limelight off
   * @param limelight The limelight to use.
   * @return The command to run.
   */
  public static Command limelightOff(Limelight limelight) {
    return new LimelightOff(limelight);
  }

  /** Turns the limelight on
   * @param limelight The limelight to use.
   * @return The comamnd to run.
   */
  public static Command limelightOn(Limelight limelight) {
    return new LimelightOff(limelight);
  }
  
}
