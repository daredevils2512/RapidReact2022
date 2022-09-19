package frc.robot.subsystems.physical;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.subsystems.NTSubsystem;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.utils.Constants;

public class PhysicalDrivetrain extends NTSubsystem implements Drivetrain {
  // Motor stuff
  private final WPI_TalonFX frontLeft; 
  private final WPI_TalonFX backLeft; 
  private final WPI_TalonFX frontRight; 
  private final WPI_TalonFX backRight;
  private final MotorControllerGroup left; 
  private final MotorControllerGroup right;
  private final DifferentialDrive drive;

  // Network table stuff
  private final NetworkTable table; 
  private final NetworkTableEntry leftDistanceEntry;
  private final NetworkTableEntry rightDistanceEntry;
  private final NetworkTableEntry leftEncoderEntry;
  private final NetworkTableEntry rightEncoderEntry;
  private final NetworkTableEntry getLowGearEntry;
  private final Encoder leftEncoder; 
  private final Encoder rightEncoder;

  // Shifting
  private final DoubleSolenoid leftShifter;
  private final DoubleSolenoid rightShifter;

  // Rate limiter
  private final SlewRateLimiter rateLim;
  private final SlewRateLimiter rateLimTurn;

  public PhysicalDrivetrain() {
    super("DrivetrainSub");
    table = NetworkTableInstance.getDefault().getTable("Drive Train");

    // Motor stuff
    frontLeft = new WPI_TalonFX(Constants.DRIVETRAIN_LEFT_ID1); 
    backLeft = new WPI_TalonFX(Constants.DRIVETRAIN_LEFT_ID2);
    left = new MotorControllerGroup(frontLeft, backLeft);
    frontRight = new WPI_TalonFX(Constants.DRIVETRAIN_RIGHT_ID1);
    backRight = new WPI_TalonFX(Constants.DRIVETRAIN_RIGHT_ID2);
    right = new MotorControllerGroup(frontRight, backRight);
    right.setInverted(true);
    drive = new DifferentialDrive(left, right);

    // Network table stuff
    leftEncoder = new Encoder(Constants.DRIVETRAIN_LEFT_ENCODER_A, Constants.DRIVETRAIN_LEFT_ENCODER_B);
    rightEncoder = new Encoder(Constants.DRIVETRAIN_RIGHT_ENCODER_A, Constants.DRIVETRAIN_RIGHT_ENCODER_B);
    leftEncoderEntry = table.getEntry("Left encoder distance"); 
    rightEncoderEntry = table.getEntry("Right encoder distance");
    leftEncoder.setDistancePerPulse(Constants.DRIVETRAIN_DISTANCE_PER_PULSE);
    leftEncoder.setReverseDirection(true);
    rightEncoder.setDistancePerPulse(Constants.DRIVETRAIN_DISTANCE_PER_PULSE);
    leftDistanceEntry = table.getEntry("Left distance entry"); 
    rightDistanceEntry = table.getEntry("Right distance entry"); 
    getLowGearEntry = table.getEntry("Low gear entry");

    // Shifting
    leftShifter = new DoubleSolenoid(Constants.PNEUMATICS_MODULE_TYPE, Constants.DRIVETRAIN_LEFT_FORWARD_CHANNEL, Constants.DRIVETRAIN_LEFT_BACKWARD_CHANNEL);
    rightShifter = new DoubleSolenoid(Constants.PNEUMATICS_MODULE_TYPE, Constants.DRIVETRAIN_RIGHT_FORWARD_CHANNEL, Constants.DRIVETRAIN_RIGHT_BACKWARD_CHANNEL);

    // Rate limiter
    rateLim = new SlewRateLimiter(Constants.DRIVETRAIN_RATELIM_VALUE);
    rateLimTurn = new SlewRateLimiter(Constants.DRIVETRAIN_RATELIM_VALUE);
  }

  @Override
  public void arcadeDrive(double move, double turn) { 
    move = rateLim.calculate(move);
    turn = rateLimTurn.calculate(turn);
    drive.arcadeDrive(move * Constants.DRIVETRAIN_MAX_SPEED, turn * Constants.DRIVETRAIN_MAX_TURN);
  }

  @Override
  public int getLeftEncoder() { 
    return leftEncoder.get();
  }

  @Override
  public int getRightEncoder() { 
    return rightEncoder.get();
  }

  @Override
  public double getLeftDistance() { 
    return leftEncoder.getDistance();
  }

  @Override
  public double getRightDistance() { 
    return rightEncoder.getDistance();
  }

  @Override
  public void setLowGear(boolean wantsLowGear) {
    leftShifter.set(wantsLowGear ? Constants.DRIVETRAIN_LOW_GEAR_VALUE : Constants.DRIVETRAIN_HIGH_GEAR_VALUE);
    rightShifter.set(wantsLowGear ? Constants.DRIVETRAIN_LOW_GEAR_VALUE : Constants.DRIVETRAIN_HIGH_GEAR_VALUE);
    logger.fine("set low gear: " + wantsLowGear);
  }

  @Override
  public boolean getLowGear() {
    logger.fine("get low gear: " + (leftShifter.get() == Constants.DRIVETRAIN_LOW_GEAR_VALUE));
    return leftShifter.get() == Constants.DRIVETRAIN_LOW_GEAR_VALUE;
  }

  @Override
  public void toggleShifters() {
    setLowGear(!getLowGear());
  }
  
  @Override
  public double getDistance() {
    return (getLeftDistance() + getRightDistance()) / 2;
  }
  
  @Override
  public void periodic() { 
    leftEncoderEntry.setNumber(getLeftEncoder());
    rightEncoderEntry.setNumber(getRightEncoder());

    leftDistanceEntry.setNumber(getLeftDistance());
    rightDistanceEntry.setNumber(getRightDistance());

    getLowGearEntry.setBoolean(getLowGear());
  }
}