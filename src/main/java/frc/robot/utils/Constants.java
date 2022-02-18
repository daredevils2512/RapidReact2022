package frc.robot.utils;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  // Auto
  public static final double DRIVE_AUTO_SPEED = 0.5; // TODO set value
  public static final double AUTO_DRIVE_BACK_DISTANCE = 150; // TODO find value

  // Drivetrain
  public static final int drivetrainEncoderResolution = 1; // TODO find value
  public static final double drivetrainRateLimNUM = 0.5; // TODO find best value
  public static final double drivetrainMaxSpeed = 0.5; // TODO find best value
  public static final double drivetrainMaxTurn = 0.5; // TODO find best value
  public static final double drivetrainGearRatio = 1; // TODO find value
  public static final double drivetrainWheelDiameter = 6;
  public static final double drivetrainWheelCircumference =  drivetrainWheelDiameter * Math.PI;
  public static final double drivetrainDistancePerPulse = drivetrainWheelCircumference / drivetrainGearRatio / drivetrainEncoderResolution;

  // Shooter
  public static final int shooterForwardChannel = 0; // TODO find value
  public static final int shooterBackwardChannel = 1; // TODO find value
  public static final double shooterRateLimNUM = 0.5; // TODO find best value

  // Motor IDs
  public static final int drivetrainLeftID1 = 0; 
  public static final int drivetrainLeftID2 = 1; 
  public static final int drivetrainRightID1 = 2;
  public static final int drivetrainRightID2 = 3;
  public static final int intake1ID = 4; 
  public static final int intake2ID = 5; 
  public static final int magID = 6;
  public static final int climber1ID = 7;
  public static final int climber2ID = 8;
  public static final int shooterID = 9; 

  // Encoder IDs
  public static final int drivetrainLeftEncoderChannelA = 0; // TODO find value
  public static final int drivetrainLeftEncoderChannelB = 0; // TODO find value
  public static final int drivetrainRightEncoderChannelA = 0; // TODO find value
  public static final int drivetrainRightEncoderChannelB = 0; // TODO find value
  public static final int shooterEncoderChannelA = 0; // TODO find value
  public static final int shooterEncoderChannelB = 0; // TODO find value

  // Pneumatics
  public static final int drivetrainLeftForwardChannel = 0; // TODO find value
  public static final int drivetrainLeftBackwardChannel = 0; // TODO find value
  public static final int drivetrainRightForwardChannel = 0; // TODO find value
  public static final int drivetrainRightBackwardChannel = 0; // TODO find value
  public static final int intakeShifter1ForwardID = 0; // TODO find value
  public static final int intakeShifter1BackwardID = 0; // TODO find value
  public static final int intakeShifter2ForwardID = 0; // TODO find value
  public static final int intakeShifter2BackwardID = 0; // TODO find value
  public static final Value intakeExtendedValue = Value.kForward; // TODO find value
  public static final Value intakeRetractedValue = Value.kReverse; // TODO find value
  public static final Value drivetrainLowGearValue = Value.kForward; // TODO find value
  public static final Value drivetrainHighGearValue = Value.kReverse; // TODO find value
  public static final PneumaticsModuleType pneumaticsModuleType = PneumaticsModuleType.CTREPCM;

  // LEDs
  public static final int LEDPort = 0; // TODO: find value
  public static final int LEDLength = 15; // TODO: find value
  public static int LED_S = 255;

  private Constants() {}
}
