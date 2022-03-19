package frc.robot.subsystems.physical;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.subsystems.NTSubsystem;
import frc.robot.subsystems.interfaces.Climber;
import frc.robot.utils.Constants;

public class PhysicalClimber extends NTSubsystem implements Climber {
  // Motor stuff
  private final TalonSRX m_rightMotor;
  private final TalonSRX m_leftMotor;
  DigitalInput toplimitSwitch = new DigitalInput(7);
  DigitalInput bottomlimitSwitch = new DigitalInput(6);

  public PhysicalClimber() {
    super("ClimberSubsystem");

    // Make Motors
    m_rightMotor = new TalonSRX(Constants.CLIMBER_1ID);
    m_leftMotor = new TalonSRX(Constants.CLIMBER_2ID);
    m_leftMotor.follow(m_rightMotor);
    m_leftMotor.setInverted(true);
  }

  @Override
  public void setClimbSpeed(double speed){ 
    if (toplimitSwitch.get() == true && speed < 0){
      m_rightMotor.set(ControlMode.PercentOutput, 0);
    } else if (bottomlimitSwitch.get() == false && speed > 0) {
      m_rightMotor.set(ControlMode.PercentOutput, 0);
    } else {
      m_rightMotor.set(ControlMode.PercentOutput, speed);
      m_logger.fine("setclimbspeed: " + speed);
    }
    
  }
}
