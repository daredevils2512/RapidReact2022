package frc.robot.utils;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

/** The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  private Constants() { }

  // Auto
  public static final double AUTO_DRIVE_SPEED = 0.5; // TODO: Find value
  public static final double AUTO_SHOOT_SPEED = .7; // TODO: Find value
  public static final double AUTO_DRIVE_BACK_DISTANCE = 150; // Inches 

  // Climber
  public static final int CLIMBER_BOTTOM_LIMIT_SWITCH_PORT = 8;
  public static final int CLIMBER_REVERSED = 1; // -1 for true, 1 for false
  public static final int CLIMBER_TOP_LIMIT_SWITCH_PORT = 7;
  public static final int CLIMBER_1ID = 7;
  public static final int CLIMBER_2ID = 8;
  public static final double CLIMBER_SPEED = .85;

  // Compressor
  public static final boolean COMPRESSOR_CLOSED_LOOP_CONTROL_ENABLED = true;
  public static final PneumaticsModuleType PNEUMATICS_MODULE_TYPE = PneumaticsModuleType.CTREPCM;

  // Drivetrain
  public static final int DRIVETRAIN_ENCODER_RESOLUTION = 1; // TODO find value
  public static final int DRIVETRAIN_LEFT_ID1 = 10; 
  public static final int DRIVETRAIN_LEFT_ID2 = 11; 
  public static final int DRIVETRAIN_RIGHT_ID1 = 2;
  public static final int DRIVETRAIN_RIGHT_ID2 = 3;
  public static final int DRIVETRAIN_LEFT_FORWARD_CHANNEL = 0; 
  public static final int DRIVETRAIN_LEFT_BACKWARD_CHANNEL = 1; 
  public static final int DRIVETRAIN_RIGHT_FORWARD_CHANNEL = 2; 
  public static final int DRIVETRAIN_RIGHT_BACKWARD_CHANNEL = 3;
  public static final int DRIVETRAIN_LEFT_ENCODER_A = 1; 
  public static final int DRIVETRAIN_LEFT_ENCODER_B = 2; 
  public static final int DRIVETRAIN_RIGHT_ENCODER_A = 3;
  public static final int DRIVETRAIN_RIGHT_ENCODER_B = 4;
  public static final double DRIVETRAIN_RATELIM_VALUE = 15;
  public static final double DRIVETRAIN_MAX_SPEED = .75; 
  public static final double DRIVETRAIN_MAX_TURN = .75; 
  public static final double DRIVETRAIN_MOVE_REVERSED = -1; // -1 for true, 1 for false
  public static final double DRIVETRAIN_TURN_REVERSED = 1; // -1 for true, 1 for false
  public static final double DRIVETRAIN_GEAR_RATIO = 1; // TODO find value
  public static final double DRIVETRAIN_WHEEL_DIAMETER = 6;
  public static final double DRIVETRAIN_WHEEL_CIRCUMFERENCE =  DRIVETRAIN_WHEEL_DIAMETER * Math.PI;
  public static final double DRIVETRAIN_DISTANCE_PER_PULSE = DRIVETRAIN_WHEEL_CIRCUMFERENCE / DRIVETRAIN_GEAR_RATIO / DRIVETRAIN_ENCODER_RESOLUTION;
  public static final Value DRIVETRAIN_LOW_GEAR_VALUE = Value.kForward; 
  public static final Value DRIVETRAIN_HIGH_GEAR_VALUE = Value.kReverse;

  // Intake
  public static final int INTAKE_REVERSED = 1; // -1 for true, 1 for false
  public static final int INTAKE_SHIFTER_FORWARD_ID1 = 4; 
  public static final int INTAKE_SHIFTER_BACKWARD_ID1 = 5;
  public static final int INTAKE_1ID = 6; 
  public static final int INTAKE_2ID = 5;
  public static final Value INTAKE_EXTENDED_VALUE = Value.kForward; 
  public static final Value INTAKE_RETRACTED_VALUE = Value.kReverse;

  // LEDs
  public static final int LED_PORT = 0; 
  public static final int LED_LENGTH = 15; 
  public static final int LED_MIN_S = 0;
  public static final int LED_MAX_S = 255;

  // Vision, Baby! 
  public static final double LIMELIGHT_MOUNT_ANGLE_DEGREES = 26.39; // how many degrees back is your limelight rotated from perfectly vertical? TODO:FIX THIS STUFF
  public static final double LIMELIGHT_LENS_HEIGHT = 26; // inches, distance from the center of the Limelight lens to the floor TODO: THIS STUFF WACK
  public static final double GOAL_HEIGHT = 104; // inches, distance from the target to the floor
  public static final double DESIRED_DISTANCE = 66; // desired distance from the target
  public static final double K_P = 0.2;

  // Magazine
  public static final int MAG_ID = 4;
  public static final double TAKE_BALLS_SPEED = 1;
  public static final double MAG_SPEED = 1;

  // Shooter
  public static final int SHOOTER_ENCODER_RESOLUTION = 0; // TODO: find value
  public static final int SHOOTER_ENCODER_CHANNEL_A = 0; // TODO: Find value
  public static final int SHOOTER_ENCODER_CHANNEL_B = 1; // TODO: Find value
  public static final int SHOOTER_K_S = 0; // TODO find value
  public static final int SHOOTER_K_V = 1; // TODO find value
  public static final int SHOOTER_ID = 9; 
  public static final double SHOOTER_P = 0; // TODO: Find value
  public static final double SHOOTER_I = 0; // TODO: Find value
  public static final double SHOOTER_D = 0; // TODO: Find value
  public static final double SHOOTER_RATELIM_VALUE = 0.5; // TODO find best value
  public static final double SHOOTER_FAST_SPEED = .75;
  public static final double SHOOTER_SLOW_SPEED = .25;

  // Interfaces
  public static final boolean SHOOTER_ENABLED = true;
  public static final boolean MAGAZINE_ENABLED = true;
  public static final boolean INTAKE_ENABLED = true;
  public static final boolean CLIMBER_ENABLED = true;
  public static final boolean DRIVETRAIN_ENABLED = true;
  public static final boolean COMPRESSOR_ENABLED = true;
  public static final boolean LIMELIGHT_ENABLED = false;
  public static final boolean LED_ENABLED = false;

  // Control Board
  public static final int XBOX_CONTROLLER_PORT = 0;
  public static final int EXTREME_PORT = 1;
  public static final int BUTTON_BOX_PORT = 2;

  // Xbox Controller
  public static final int XBOX_POV_UP_DEGREES = 0;
  public static final int XBOX_POV_UP_RIGHT_DEGREES = 45;
  public static final int XBOX_POV_RIGHT_DEGREES = 90;
  public static final int XBOX_POV_DOWN_RIGHT_DEGREES = 135;
  public static final int XBOX_POV_DOWN_DEGREES = 180;
  public static final int XBOX_POV_DOWN_LEFT_DEGREES = 225;
  public static final int XBOX_POV_LEFT_DEGREES = 270;
  public static final int XBOX_POV_UP_LEFT_DEGREES = 315;
  public static final int XBOX_POV_RELEASED_DEGREES = -1;
  public static final RumbleType XBOX_LEFT_RUMBLE = RumbleType.kLeftRumble;
  public static final RumbleType XBOX_RIGHT_RUMBLE = RumbleType.kRightRumble;

  // Extreme
  public static final int EXTREME_TRIGGER_PORT = 1;
  public static final int EXTREME_SIDE_BUTTON_PORT = 2;
  public static final int EXTREME_JOYSTICK_BOTTOM_LEFT_PORT = 3;
  public static final int EXTREME_JOYSTICK_BOTTOM_RIGHT_PORT = 4;
  public static final int EXTREME_JOYSTICK_TOP_LEFT_PORT = 5;
  public static final int EXTREME_JOYSTICK_TOP_RIGHT_PORT = 6;
  public static final int EXTREME_BASE_FRONT_LEFT_PORT = 7;
  public static final int EXTREME_BASE_FRONT_RIGHT_PORT = 8;
  public static final int EXTREME_BASE_MIDDLE_LEFT_PORT = 9;
  public static final int EXTREME_BASE_MIDDLE_RIGHT_PORT = 10;
  public static final int EXTREME_BASE_BACK_LEFT_PORT = 11;
  public static final int EXTREME_BASE_BACK_RIGHT_PORT = 12;
  public static final int EXTREME_STICK_X_AXIS_ID = 0;
  public static final int EXTREME_STICK_Y_AXIS_ID = 1;
  public static final int EXTREME_STICK_Z_AXIS_ID = 2;
  public static final int EXTREME_SLIDER_AXIS_ID = 3;
  public static final int EXTREME_POV_UP_DEGREES = 0;
  public static final int EXTREME_POV_UP_RIGHT_DEGREES = 45;
  public static final int EXTREME_POV_RIGHT_DEGREES = 90;
  public static final int EXTREME_POV_DOWN_RIGHT_DEGREES = 135;
  public static final int EXTREME_POV_DOWN_DEGREES = 180;
  public static final int EXTREME_POV_DOWN_LEFT_DEGREES = 225;
  public static final int EXTREME_POV_LEFT_DEGREES = 270;
  public static final int EXTREME_POV_UP_LEFT_DEGREES = 315;

  // Button Box
  public static final int BUTTON_BOX_TOP_WHITE_PORT = 2;
  public static final int BUTTON_BOX_BIG_WHITE_PORT = 3;
  public static final int BUTTON_BOX_MIDDLE_RED_PORT = 4;
  public static final int BUTTON_BOX_BOTTOM_WHITE_PORT = 5;
  public static final int BUTTON_BOX_TOP_RED_PORT = 6;
  public static final int BUTTON_BOX_GREEN_PORT = 7;
  public static final int BUTTON_BOX_MIDDLE_WHITE_PORT = 8;
  public static final int BUTTON_BOX_BIG_RED_PORT = 14;
  public static final int BUTTON_BOX_YELLOW_PORT = 15;
  public static final int BUTTON_BOX_BOTTOM_RED_PORT = 16;
}