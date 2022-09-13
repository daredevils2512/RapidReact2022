package frc.robot.subsystems.physical;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.subsystems.NTSubsystem;
import frc.robot.subsystems.interfaces.Climber;
import frc.robot.utils.Constants;

public class PhysicalClimber extends NTSubsystem implements Climber {
  // Motor stuff
  private final TalonSRX rightMotor;
  private final TalonSRX leftMotor;
  DigitalInput toplimitSwitch = new DigitalInput(7); // TODO Hardcoded value; also incorrect capitalization (topLimitSwitch); also member can be final
  DigitalInput bottomlimitSwitch = new DigitalInput(6); // TODO Hardcoded value; also incorrect capitalization (bottomLimitSwitch); also member can be final

  // Shifters
  // private final DoubleSolenoid m_leftShifter;
  // private final DoubleSolenoid m_rightShifter;

  public PhysicalClimber() {
    super("ClimberSubsystem");

    // Make Motors
    rightMotor = new TalonSRX(Constants.CLIMBER_1ID);
    leftMotor = new TalonSRX(Constants.CLIMBER_2ID);
    leftMotor.follow(rightMotor);
    leftMotor.setInverted(true);

    // Shifters
    // m_leftShifter = new DoubleSolenoid(Constants.PNEUMATICS_MODULE_TYPE, Constants.CLIMBER_LEFT_FORWARD_CHANNEL, Constants.CLIMBER_LEFT_BACKWARD_CHANNEL);
    // m_rightShifter = new DoubleSolenoid(Constants.PNEUMATICS_MODULE_TYPE, Constants.CLIMBER_RIGHT_FORWARD_CHANNEL, Constants.CLIMBER_RIGHT_BACKWARD_CHANNEL);
  }

  @Override
  public void setClimbSpeed(double speed) { 
    if (toplimitSwitch.get() && speed < 0) {
      rightMotor.set(ControlMode.PercentOutput, 0);
    } else if (!bottomlimitSwitch.get() && speed > 0) { 
      rightMotor.set(ControlMode.PercentOutput, 0);
    } else {
      rightMotor.set(ControlMode.PercentOutput, speed);
      logger.fine("setclimbspeed: " + speed);
    }
  }
}
