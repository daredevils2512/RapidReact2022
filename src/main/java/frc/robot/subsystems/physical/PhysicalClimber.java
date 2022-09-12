package frc.robot.subsystems.physical;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.subsystems.NTSubsystem;
import frc.robot.subsystems.interfaces.Climber;
import frc.robot.utils.Constants;

public class PhysicalClimber extends NTSubsystem implements Climber {
  // Motor stuff
  private final TalonSRX m_rightMotor;
  private final TalonSRX m_leftMotor;
  DigitalInput toplimitSwitch = new DigitalInput(7); // TODO Hardcoded value; also incorrect capitalization (topLimitSwitch); also member can be final
  DigitalInput bottomlimitSwitch = new DigitalInput(6); // TODO Hardcoded value; also incorrect capitalization (bottomLimitSwitch); also member can be final

  // Shifters
  private final DoubleSolenoid m_leftShifter;
  private final DoubleSolenoid m_rightShifter;

  public PhysicalClimber() {
    super("ClimberSubsystem");

    // Make Motors
    m_rightMotor = new TalonSRX(Constants.CLIMBER_1ID);
    m_leftMotor = new TalonSRX(Constants.CLIMBER_2ID);
    m_leftMotor.follow(m_rightMotor);
    m_leftMotor.setInverted(true);

    // Shifters
    m_leftShifter = new DoubleSolenoid(Constants.PNEUMATICS_MODULE_TYPE, Constants.CLIMBER_LEFT_FORWARD_CHANNEL, Constants.CLIMBER_LEFT_BACKWARD_CHANNEL);
    m_rightShifter = new DoubleSolenoid(Constants.PNEUMATICS_MODULE_TYPE, Constants.CLIMBER_RIGHT_FORWARD_CHANNEL, Constants.CLIMBER_RIGHT_BACKWARD_CHANNEL);
  }

  @Override
  public void setClimbSpeed(double speed) { 
    if (toplimitSwitch.get() == true && speed < 0){ // TODO "x == true" and "x == false" are always redundant
      m_rightMotor.set(ControlMode.PercentOutput, 0);
    } else if (bottomlimitSwitch.get() == false && speed > 0) { // TODO Redundant equality check
      m_rightMotor.set(ControlMode.PercentOutput, 0);
    } else {
      m_rightMotor.set(ControlMode.PercentOutput, speed);
      m_logger.fine("setclimbspeed: " + speed);
    }
  }

  @Override 
  public boolean getExtended() {
    return m_leftShifter.get() == Constants.CLIMBER_EXTENDED_VALUE;
  }

  @Override
  public void setExtended(boolean wantsExtended) {
    m_leftShifter.set(wantsExtended ? Constants.CLIMBER_EXTENDED_VALUE : Constants.CLIMBER_RETRACTED_VALUE);
    m_rightShifter.set(wantsExtended ? Constants.CLIMBER_EXTENDED_VALUE : Constants.CLIMBER_RETRACTED_VALUE);
  }

  @Override
  public void toggleExtended() {
    setExtended(!getExtended());
  }
}
