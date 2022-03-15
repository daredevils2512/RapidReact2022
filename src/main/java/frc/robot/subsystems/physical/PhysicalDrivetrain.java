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
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.NTSubsystem;
import frc.robot.utils.Constants;

public class PhysicalDrivetrain extends NTSubsystem implements Drivetrain {
  // Motor stuff
  private final WPI_TalonFX m_frontLeft; 
  private final WPI_TalonFX m_backLeft; 
  private final WPI_TalonFX m_frontRight; 
  private final WPI_TalonFX m_backRight;
  private final MotorControllerGroup m_left; 
  private final MotorControllerGroup m_right;
  private final DifferentialDrive m_drive;

  // Network table stuff
  private final NetworkTable m_table; 
  private final NetworkTableEntry m_leftDistanceEntry;
  private final NetworkTableEntry m_rightDistanceEntry;
  private final NetworkTableEntry m_leftEncoderEntry;
  private final NetworkTableEntry m_rightEncoderEntry;
  private final NetworkTableEntry m_getLowGearEntry;
  private final Encoder m_leftEncoder; 
  private final Encoder m_rightEncoder;

  // Shifting
  private final DoubleSolenoid m_leftShifter;
  private final DoubleSolenoid m_rightShifter;

  // Rate limiter
  private final SlewRateLimiter m_rateLim;
  private final SlewRateLimiter m_rateLimTurn;

  public PhysicalDrivetrain() {
    super("DrivetrainSub");
    m_table = NetworkTableInstance.getDefault().getTable("Drive Train");

    // Motor stuff
    m_frontLeft = new WPI_TalonFX(Constants.DRIVETRAIN_LEFT_ID1); 
    m_backLeft = new WPI_TalonFX(Constants.DRIVETRAIN_LEFT_ID2);
    m_left = new MotorControllerGroup(m_frontLeft, m_backLeft);
    m_left.setInverted(true);
    m_frontRight = new WPI_TalonFX(Constants.DRIVETRAIN_RIGHT_ID1);
    m_backRight = new WPI_TalonFX(Constants.DRIVETRAIN_RIGHT_ID2);
    m_right = new MotorControllerGroup(m_frontRight, m_backRight);
    m_drive = new DifferentialDrive(m_left, m_right); 

    // Network table stuff
    m_leftEncoder = new Encoder(Constants.DRIVETRAIN_LEFT_ENCODER_A, Constants.DRIVETRAIN_LEFT_ENCODER_B);
    m_rightEncoder = new Encoder(Constants.DRIVETRAIN_RIGHT_ENCODER_A, Constants.DRIVETRAIN_RIGHT_ENCODER_B);
    m_leftEncoderEntry = m_table.getEntry("Left encoder distance"); 
    m_rightEncoderEntry = m_table.getEntry("Right encoder distance");
    m_leftEncoder.setDistancePerPulse(Constants.DRIVETRAIN_DISTANCE_PER_PULSE);
    m_leftEncoder.setReverseDirection(true);
    m_rightEncoder.setDistancePerPulse(Constants.DRIVETRAIN_DISTANCE_PER_PULSE);
    m_leftDistanceEntry = m_table.getEntry("Left distance entry"); 
    m_rightDistanceEntry = m_table.getEntry("Right distance entry"); 
    m_getLowGearEntry = m_table.getEntry("Low gear entry");

    // Shifting
    m_leftShifter = new DoubleSolenoid(Constants.PNEUMATICS_MODULE_TYPE, Constants.DRIVETRAIN_LEFT_FORWARD_CHANNEL, Constants.DRIVETRAIN_LEFT_BACKWARD_CHANNEL);
    m_rightShifter = new DoubleSolenoid(Constants.PNEUMATICS_MODULE_TYPE, Constants.DRIVETRAIN_RIGHT_FORWARD_CHANNEL, Constants.DRIVETRAIN_RIGHT_BACKWARD_CHANNEL);

    // Rate limiter
    m_rateLim = new SlewRateLimiter(Constants.DRIVETRAIN_RATELIM_VALUE);
    m_rateLimTurn = new SlewRateLimiter(Constants.DRIVETRAIN_RATELIM_VALUE);
  }

  /** Runs the arcade drive 
   * @param move Speed for forward/backward movement
   * @param turn Speed for left/right movement
  */
  public void arcadeDrive(double move, double turn) { 
    move = m_rateLim.calculate(move);
    turn = m_rateLimTurn.calculate(turn);
    m_drive.arcadeDrive((move) * Constants.DRIVETRAIN_MAX_SPEED, (turn) * Constants.DRIVETRAIN_MAX_TURN);
  }

  /** 
   * @return Left encoder
  */
  public int getLeftEncoder() { 
    return m_leftEncoder.get();
  }

  /** 
   * @return Right encoder
  */
  public int getRightEncoder() { 
    return m_rightEncoder.get();
  }

  /** 
   * @return Left distance
   */
  public double getLeftDistance() { 
    return m_leftEncoder.getDistance();
  }

  /** 
   * @return Right distance
   */
  public double getRightDistance() { 
    return m_rightEncoder.getDistance();
  }

  /** Sets low gear only if it wants to
   * @param wantsLowGear if it wants to set low gear
   */
  public void setLowGear(boolean wantsLowGear) {
    m_leftShifter.set(wantsLowGear ? Constants.DRIVETRAIN_LOW_GEAR_VALUE : Constants.DRIVETRAIN_HIGH_GEAR_VALUE);
    m_rightShifter.set(wantsLowGear ? Constants.DRIVETRAIN_LOW_GEAR_VALUE : Constants.DRIVETRAIN_HIGH_GEAR_VALUE);
    m_logger.fine("set low gear: " + wantsLowGear);
  }

  /** @return true if shifter are in low gear */
  public boolean getLowGear() {
    m_logger.fine("get low gear: " + (m_leftShifter.get() == Constants.DRIVETRAIN_LOW_GEAR_VALUE));
    return m_leftShifter.get() == Constants.DRIVETRAIN_LOW_GEAR_VALUE;
  }

  /** toggles the shifters */
  public void toggleShifters() {
    setLowGear(!getLowGear());
  }
  
  /** @return distance that the drivetrain has moved */
  public double getDistance() {
    return (getLeftDistance() + getRightDistance()) / 2;
  }
  
  /** Periodically runs code */
  @Override
  public void periodic() { 
    m_leftEncoderEntry.setNumber(getLeftEncoder());
    m_rightEncoderEntry.setNumber(getRightEncoder());

    m_leftDistanceEntry.setNumber(getLeftDistance());
    m_rightDistanceEntry.setNumber(getRightDistance());

    m_getLowGearEntry.setBoolean(getLowGear());
  }
}