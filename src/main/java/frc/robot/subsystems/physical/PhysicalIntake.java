package frc.robot.subsystems.physical;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.subsystems.NTSubsystem;
import frc.robot.subsystems.interfaces.Intake;
import frc.robot.utils.Constants;

public class PhysicalIntake extends NTSubsystem implements Intake {
  // Motor stuff
  private final WPI_TalonSRX m_intake1;
  private final WPI_TalonSRX m_intake2;

  // Shifters
  private final DoubleSolenoid m_shifter;

  public PhysicalIntake() {
    super("IntakeSub");

    // Sets IDs for motors
    m_intake1 = new WPI_TalonSRX(Constants.INTAKE_1ID);
    m_intake2 = new WPI_TalonSRX(Constants.INTAKE_2ID);
        
    // Sets up inversions, etc.
    m_intake1.setInverted(false);
    m_intake2.setInverted(true);
    m_intake2.follow(m_intake1);

    // Shifters
    m_shifter = new DoubleSolenoid(Constants.PNEUMATICS_MODULE_TYPE, Constants.INTAKE_SHIFTER_FORWARD_ID1, Constants.INTAKE_SHIFTER_BACKWARD_ID1);
    // m_rightShifter = new DoubleSolenoid(Constants.PNEUMATICS_MODULE_TYPE, Constants.INTAKE_SHIFTER_FORWARD_ID1, Constants.INTAKE_SHIFTER_BACKWARD_ID2);
  }

  @Override
  public void setExtended(boolean wantsExtended) {
    m_shifter.set(wantsExtended ? Constants.INTAKE_EXTENDED_VALUE : Constants.INTAKE_RETRACTED_VALUE);
    //m_rightShifter.set(wantsExtended ? Constants.intakeExtendedValue : Constants.intakeRetractedValue);
    m_logger.info("set extended: " + wantsExtended);
  }

  @Override
  public boolean getExtended() {
    m_logger.fine("get extended: " + (m_shifter.get() == Constants.INTAKE_EXTENDED_VALUE));
    return m_shifter.get() == Constants.INTAKE_EXTENDED_VALUE;
  }

  @Override
  public void toggleExtended() {
    setExtended(!getExtended());
  }
    
  @Override
  public void setIntake(double speed) {
    m_intake1.set(-speed);
    m_logger.fine("set intake speed: " + speed);
  }
}