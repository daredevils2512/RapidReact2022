package frc.robot.utils;

import javax.measure.Quantity;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import javax.measure.quantity.Time;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import si.uom.SI;
import systems.uom.common.USCustomary;
import tech.units.indriya.quantity.Quantities;

/** The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  // 
  public static final boolean SHOOTER_ENABLED = true;
  public static final boolean MAGAZINE_ENABLED = true;
  public static final boolean INTAKE_ENABLED = true;
  public static final boolean CLIMBER_ENABLED = true;
  public static final boolean DRIVETRAIN_ENABLED = true;
  public static final boolean SPARK_DRIVETRAIN_ENABLED = false;
  public static final boolean COMPRESSOR_ENABLED = true;
  public static final boolean LIMELIGHT_ENABLED = false;
  public static final boolean LED_ENABLED = true;

  // Auto
  public static final double DRIVE_AUTO_SPEED = 0.5; // TODO: Find value
  public static final double SHOOT_AUTO_SPEED = 1; // TODO: Find value
  public static final Quantity<Length> AUTO_DRIVE_BACK_DISTANCE = Quantities.getQuantity(150, SI.METRE); // Inches // TODO: No longer used
  public static final Quantity<Time> AUTO_DRIVE_BACK_TIME = Quantities.getQuantity(0, SI.SECOND); // TODO: calculate time based on distance and speed!!!!!!!!!

  // Drivetrain
  public static final int DRIVETRAIN_ENCODER_RESOLUTION = 1; // TODO find value
  public static final double DRIVETRAIN_RATELIM_VALUE = 15;
  public static final double DRIVETRAIN_MAX_SPEED = .75; 
  public static final double DRIVETRAIN_MAX_TURN = .75; 
  public static final double DRIVETRAIN_GEAR_RATIO = 1; // TODO find value
  public static final Quantity<Length> DRIVETRAIN_WHEEL_DIAMETER = Quantities.getQuantity(6, USCustomary.INCH);
  public static final Quantity<Length> DRIVETRAIN_WHEEL_CIRCUMFERENCE =  DRIVETRAIN_WHEEL_DIAMETER.multiply(Math.PI);
  public static final Quantity<Length> DRIVETRAIN_DISTANCE_PER_PULSE = DRIVETRAIN_WHEEL_CIRCUMFERENCE.divide(DRIVETRAIN_GEAR_RATIO).divide(DRIVETRAIN_ENCODER_RESOLUTION);

  // Shooter
  public static final int SHOOTER_ENCODER_RESOLUTION = 0; // TODO: find value
  public static final int SHOOTER_ENCODER_CHANNEL_A = 0; // TODO: Find value
  public static final int SHOOTER_ENCODER_CHANNEL_B = 1; // TODO: Find value
  public static final int SHOOTER_FORWARD_CHANNEL = 0; // TODO find value
  public static final int SHOOTER_BACKWARD_CHANNEL = 1; // TODO find value
  public static final double SHOOTER_RATELIM_VALUE = 0.5; // TODO find best value
  public static final double SHOOTER_FAST_SPEED = .75;
  public static final double SHOOTER_SLOW_SPEED = .25;
  public static final double SHOOTER_P = 0; // TODO: Find value
  public static final double SHOOTER_I = 0; // TODO: Find value
  public static final double SHOOTER_D = 0; // TODO: Find value

  // climber
  public static final double CLIMBER_SPEED = 1;

  // Motor IDs
  public static final int DRIVETRAIN_LEFT_ID1 = 10; 
  public static final int DRIVETRAIN_LEFT_ID2 = 11; 
  public static final int DRIVETRAIN_RIGHT_ID1 = 2;
  public static final int DRIVETRAIN_RIGHT_ID2 = 3;
  public static final int INTAKE_1ID = 6; 
  public static final int INTAKE_2ID = 5;


  public static final int MAG_ID = 4;
  public static final int CLIMBER_1ID = 7;
  public static final int CLIMBER_2ID = 8;
  public static final int SHOOTER_ID = 9; 

  // Vision, Baby! 
  // how many degrees back is your limelight rotated from perfectly vertical? TODO: FIX THIS STUFF
  public static final Quantity<Angle> LIMELIGHT_MOUNT_ANGLE = Quantities.getQuantity(26.39, USCustomary.DEGREE_ANGLE);
  // distance from the center of the Limelight lens to the floor TODO: THIS STUFF WACK
  public static final Quantity<Length> LIMELIGHT_LENS_HEIGHT = Quantities.getQuantity(26, USCustomary.INCH);
  // distance from the target to the floor
  public static final Quantity<Length> GOAL_HEIGHT = Quantities.getQuantity(104, USCustomary.INCH); 
  //desired distance from the target
  public static final Quantity<Length> DESIRED_DISTANCE = Quantities.getQuantity(66, USCustomary.INCH);
  public static final double K_P = 0.2;

  // Encoder IDs
  public static final int DRIVETRAIN_LEFT_ENCODER_A = 1; 
  public static final int DRIVETRAIN_LEFT_ENCODER_B = 2; 
  public static final int DRIVETRAIN_RIGHT_ENCODER_A = 3;
  public static final int DRIVETRAIN_RIGHT_ENCODER_B = 4;

  // Pneumatics
  public static final int DRIVETRAIN_LEFT_FORWARD_CHANNEL = 0; 
  public static final int DRIVETRAIN_LEFT_BACKWARD_CHANNEL = 1; 
  public static final int DRIVETRAIN_RIGHT_FORWARD_CHANNEL = 2; 
  public static final int DRIVETRAIN_RIGHT_BACKWARD_CHANNEL = 3;
  public static final int INTAKE_SHIFTER_FORWARD_ID1 = 4; 
  public static final int INTAKE_SHIFTER_BACKWARD_ID1 = 5;
  public static final int INTAKE_SHIFTER_FORWARD_ID2= 0; // TODO find value
  public static final int INTAKE_SHIFTER_BACKWARD_ID2 = 0; // TODO find value
  public static final Value INTAKE_EXTENDED_VALUE = Value.kForward; 
  public static final Value INTAKE_RETRACTED_VALUE = Value.kReverse;
  public static final Value DRIVETRAIN_LOW_GEAR_VALUE = Value.kForward; 
  public static final Value DRIVETRAIN_HIGH_GEAR_VALUE = Value.kReverse;
  public static final PneumaticsModuleType PNEUMATICS_MODULE_TYPE = PneumaticsModuleType.CTREPCM;

  // LEDs
  public static final int LED_PORT = 0; // TODO: find value
  public static final int LED_LENGTH = 15; // TODO: find value
  public static final int LED_MIN_S = 0;
  public static final int LED_MAX_S = 255;

  private Constants() { }
}
