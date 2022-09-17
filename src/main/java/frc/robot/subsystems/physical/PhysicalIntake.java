package frc.robot.subsystems.physical;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.subsystems.NTSubsystem;
import frc.robot.subsystems.interfaces.Intake;
import frc.robot.utils.Constants;

public class PhysicalIntake extends NTSubsystem implements Intake {
  // Motor stuff
  private final WPI_TalonSRX intake1;
  private final WPI_TalonSRX intake2;

  // Shifters
  private final DoubleSolenoid shifter;

  public PhysicalIntake() {
    super("IntakeSub");

    // Sets IDs for motors
    intake1 = new WPI_TalonSRX(Constants.INTAKE_1ID);
    intake2 = new WPI_TalonSRX(Constants.INTAKE_2ID);
        
    // Sets up inversions, etc.
    intake1.setInverted(false);
    intake2.setInverted(true);
    intake2.follow(intake1);

    // Shifters
    shifter = new DoubleSolenoid(Constants.PNEUMATICS_MODULE_TYPE, Constants.INTAKE_SHIFTER_FORWARD_ID1, Constants.INTAKE_SHIFTER_BACKWARD_ID1);
  }

  @Override
  public void setExtended(boolean wantsExtended) {
    shifter.set(wantsExtended ? Constants.INTAKE_EXTENDED_VALUE : Constants.INTAKE_RETRACTED_VALUE);
    //m_rightShifter.set(wantsExtended ? Constants.intakeExtendedValue : Constants.intakeRetractedValue);
    logger.info("set extended: " + wantsExtended);
  }

  @Override
  public boolean getExtended() {
    logger.fine("get extended: " + (shifter.get() == Constants.INTAKE_EXTENDED_VALUE));
    return shifter.get() == Constants.INTAKE_EXTENDED_VALUE;
  }

  @Override
  public void toggleExtended() {
    setExtended(!getExtended());
  }
    
  @Override
  public void setIntake(double speed) {
    intake1.set(-speed);
    logger.fine("set intake speed: " + speed);
  }
}