package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Intake extends NTSubsystem {
  // IDs TODO: change to correct values!
  private final int k_intake1ID = 04;
  private final int k_intake2ID = 05;
  private final int k_shifter1ForwardID = 01;
  private final int k_shifter1BackwardID = 01;
  private final int k_shifter2ForwardID = 02;
  private final int k_shifter2BackwardID = 02;
  private final PneumaticsModuleType k_pneumaticsModuleType = PneumaticsModuleType.CTREPCM;

  // Motor stuff
  private final WPI_TalonSRX m_intake1;
  private final WPI_TalonSRX m_intake2;

  // Shifters
  private final DoubleSolenoid m_leftShifter;
  private final DoubleSolenoid m_rightShifter;

  public Intake() {
    super("IntakeSub");

    // Sets IDs for motors
    m_intake1 = new WPI_TalonSRX(k_intake1ID);
    m_intake2 = new WPI_TalonSRX(k_intake2ID);
        
    // Sets up inversions, etc.
    m_intake1.setInverted(false);
    m_intake2.setInverted(true);
    m_intake2.follow(m_intake1);

    // Shifters
    m_leftShifter = new DoubleSolenoid(k_pneumaticsModuleType, k_shifter1ForwardID, k_shifter1BackwardID);
    m_rightShifter = new DoubleSolenoid(k_pneumaticsModuleType, k_shifter2ForwardID, k_shifter2BackwardID);
  }

  /** Sets gears to proper value */
  public void setExtended(boolean wantsExtended) {
    m_leftShifter.set(wantsExtended ? Value.kForward : Value.kReverse);
    m_rightShifter.set(wantsExtended ? Value.kForward : Value.kReverse);
    m_logger.info("set extended: " + wantsExtended);
  }

  /** @return true if shifters are in low gear */
  public boolean getExtended() {
    m_logger.fine("get extended: " + (m_leftShifter.get() == Value.kForward ? true : false));
    return m_leftShifter.get() == Value.kForward ? true : false;
  }

  /** Toggles the shifters on/off */
  public void toggleExtended() {
    setExtended(!getExtended());
  }
    
  /** Runs intake motors
   * @param speed Speed for intake motors
   */
  public void setIntake(double speed) {
    m_intake1.set(speed);
    m_logger.fine("set intake speed: " + speed);
  }
}