package frc.robot.subsystems.physical;

import javax.measure.Quantity;
import javax.measure.quantity.Length;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import frc.robot.subsystems.NTSubsystem;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.utils.Constants;
import si.uom.SI;
import tech.units.indriya.quantity.Quantities;

public class PhysicalSparkDrivetrain extends NTSubsystem implements Drivetrain {
  // Motor stuff
  private final PWMSparkMax m_frontLeft; 
  private final PWMSparkMax m_backLeft; 
  private final PWMSparkMax m_frontRight; 
  private final PWMSparkMax m_backRight;
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

  public PhysicalSparkDrivetrain() {
    super("Drivetrain");
    m_table = NetworkTableInstance.getDefault().getTable("Drive Train");

    // Motor stuff
    m_frontLeft = new PWMSparkMax(Constants.DRIVETRAIN_LEFT_ID1); 
    m_backLeft = new PWMSparkMax(Constants.DRIVETRAIN_LEFT_ID2);
    m_left = new MotorControllerGroup(m_frontLeft, m_backLeft);
    m_frontLeft.setInverted(true);
    m_frontRight = new PWMSparkMax(Constants.DRIVETRAIN_RIGHT_ID1);
    m_backRight = new PWMSparkMax(Constants.DRIVETRAIN_RIGHT_ID2);
    m_right = new MotorControllerGroup(m_frontRight, m_backRight);
    m_drive = new DifferentialDrive(m_left, m_right); 

    // Network table stuff
    m_leftEncoder = new Encoder(Constants.DRIVETRAIN_LEFT_ENCODER_A, Constants.DRIVETRAIN_LEFT_ENCODER_B);
    m_rightEncoder = new Encoder(Constants.DRIVETRAIN_RIGHT_ENCODER_A, Constants.DRIVETRAIN_RIGHT_ENCODER_B);
    m_leftEncoderEntry = m_table.getEntry("Left encoder distance"); 
    m_rightEncoderEntry = m_table.getEntry("Right encoder distance");
    m_leftEncoder.setDistancePerPulse(Constants.DRIVETRAIN_DISTANCE_PER_PULSE.to(SI.METRE).getValue().doubleValue());
    m_leftEncoder.setReverseDirection(true);
    m_rightEncoder.setDistancePerPulse(Constants.DRIVETRAIN_DISTANCE_PER_PULSE.to(SI.METRE).getValue().doubleValue());
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

  @Override
  public void arcadeDrive(double move, double turn) {
    move = m_rateLim.calculate(move);
    turn = m_rateLimTurn.calculate(turn);
    m_drive.arcadeDrive((move) * Constants.DRIVETRAIN_MAX_SPEED, (turn) * Constants.DRIVETRAIN_MAX_TURN);
  }

  @Override
  public void setLowGear(boolean wantsLowGear) {
    m_leftShifter.set(wantsLowGear ? Constants.DRIVETRAIN_LOW_GEAR_VALUE : Constants.DRIVETRAIN_HIGH_GEAR_VALUE);
    m_rightShifter.set(wantsLowGear ? Constants.DRIVETRAIN_LOW_GEAR_VALUE : Constants.DRIVETRAIN_HIGH_GEAR_VALUE);
    m_logger.fine("set low gear: " + wantsLowGear);
  }

  @Override
  public void toggleShifters() {
    setLowGear(!getLowGear());
  }

  @Override
  public Quantity<Length> getRightDistance() {
    return Quantities.getQuantity(m_rightEncoder.getDistance(), SI.METRE);
  }

  @Override
  public Quantity<Length> getLeftDistance() {
    return Quantities.getQuantity(m_leftEncoder.getDistance(), SI.METRE);
  }

  @Override
  public boolean getLowGear() {
    m_logger.fine("get low gear: " + (m_leftShifter.get() == Constants.DRIVETRAIN_LOW_GEAR_VALUE));
    return m_leftShifter.get() == Constants.DRIVETRAIN_LOW_GEAR_VALUE;
  }
  
  @Override
  public void periodic() { 
    m_leftEncoderEntry.setNumber(getLeftEncoder());
    m_rightEncoderEntry.setNumber(getRightEncoder());

    m_leftDistanceEntry.setNumber(getLeftDistance().to(SI.METRE).getValue());
    m_rightDistanceEntry.setNumber(getRightDistance().to(SI.METRE).getValue());

    m_getLowGearEntry.setBoolean(getLowGear());
  }

  @Override
  public int getLeftEncoder() {
    return m_leftEncoder.get();
  }

  @Override
  public int getRightEncoder() {
    return m_rightEncoder.get();
  }

  @Override
  public Quantity<Length> getDistance() { 
    return (getLeftDistance().add(getLeftDistance()).divide(2));
  }
  
}
