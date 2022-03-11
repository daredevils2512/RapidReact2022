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
  public static final double DRIVE_AUTO_SPEED = 0.80; // TODO set value
  public static final double AUTO_DRIVE_BACK_DISTANCE = 150; // TODO find value

  // Drivetrain
  public static final int drivetrainEncoderResolution = 1; // TODO find value
  public static final double drivetrainRateLimNUM = 15;
  public static final double drivetrainMaxSpeed = .75; 
  public static final double drivetrainMaxTurn = .75; 
  public static final double drivetrainGearRatio = 1; // TODO find vale
  public static final double drivetrainWheelDiameter = 6;
  public static final double drivetrainWheelCircumference =  drivetrainWheelDiameter * Math.PI;
  public static final double drivetrainDistancePerPulse = drivetrainWheelCircumference / drivetrainGearRatio / drivetrainEncoderResolution;

  // Shooter
  public static final int shooterForwardChannel = 0; // TODO find value
  public static final int shooterBackwardChannel = 1; // TODO find value
  public static final double shooterRateLimNUM = 0.5; // TODO find best value

  // climber
  public static final double climberSpeed = 1;
  // Motor IDs
  public static final int drivetrainLeftID1 = 10; 
  public static final int drivetrainLeftID2 = 11; 
  public static final int drivetrainRightID1 = 2;
  public static final int drivetrainRightID2 = 3;
  public static final int intake1ID = 6; 
  public static final int intake2ID = 5;


  public static final int magID = 4;
  public static final int climber1ID = 7;
  public static final int climber2ID = 8;
  public static final int shooterID = 9; 

  // Vision, Baby! 
  // how many degrees back is your limelight rotated from perfectly vertical? TODO:FIX THIS SHIT
public static final double limelightMountAngleDegrees = 26.39;
// distance from the center of the Limelight lens to the floor TODO: THIS SHIT WACK
public static final double limelightLensHeightInches = 26;
// distance from the target to the floor
public static final double goalHeightInches = 104;
//desired distance from the target
public static final double desiredDistance = 66.;


  // Encoder IDs
  public static final int drivetrainLeftEncoderChannelA = 1; 
  public static final int drivetrainLeftEncoderChannelB = 2; 
  public static final int drivetrainRightEncoderChannelA = 3;
  public static final int drivetrainRightEncoderChannelB = 4;
  // public static final int shooterEncoderChannelA = 0; // TODO find value
  // public static final int shooterEncoderChannelB = 0; // TODO find value

  // Pneumatics
  public static final int drivetrainLeftForwardChannel = 0; // TODO find value
  public static final int drivetrainLeftBackwardChannel = 1; // TODO find value
  public static final int drivetrainRightForwardChannel = 2; // TODO find value
  public static final int drivetrainRightBackwardChannel = 3; // TODO find value
  public static final int intakeShifter1ForwardID = 4; // TODO find value
  public static final int intakeShifter1BackwardID = 5; // TODO find value
  public static final int intakeShifter2ForwardID = 0; // TODO find value
  public static final int intakeShifter2BackwardID = 0; // TODO find value
  public static final Value intakeExtendedValue = Value.kForward; // TODO find value
  public static final Value intakeRetractedValue = Value.kReverse; // TODO find value
  public static final Value drivetrainLowGearValue = Value.kForward; // TODO find value
  public static final Value drivetrainHighGearValue = Value.kReverse; // TODO find value
  public static final PneumaticsModuleType pneumaticsModuleType = PneumaticsModuleType.CTREPCM;

  private Constants() { }
}
