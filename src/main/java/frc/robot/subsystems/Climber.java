package frc.robot.subsystems;

import java.util.logging.Level;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.utils.Constants;

public class Climber extends NTSubsystem {
  // Motor stuff
  private final TalonSRX m_rightMotor;
  private final TalonSRX m_leftMotor;
  DigitalInput toplimitSwitch = new DigitalInput(7);
  DigitalInput bottomlimitSwitch = new DigitalInput(6);

  public Climber (){
    super("ClimberSubsystem");

    // Make Motors
    m_rightMotor = new TalonSRX(Constants.climber1ID);
    m_leftMotor = new TalonSRX(Constants.climber2ID);
    m_leftMotor.follow(m_rightMotor);
    m_leftMotor.setInverted(true);
  }

  /** Sets the speed of the climbing motors
   * @param speed The speed of the motors
   */
  public void setClimbSpeed(double speed){ 
    if(toplimitSwitch.get() == true && speed < 0){
      m_rightMotor.set(ControlMode.PercentOutput, 0);
      m_logger.log(Level.ALL, "limit switch triped while going up");
    } else if (bottomlimitSwitch.get() == false && speed > 0) {
      m_rightMotor.set(ControlMode.PercentOutput, 0);
      m_logger.log(Level.ALL, "limit switch tried while trying to go down");
    } else {
      m_rightMotor.set(ControlMode.PercentOutput, speed);
      m_logger.fine("setclimbspeed: " + speed); 
      m_logger.log(Level.ALL, "" + speed );
    
  }
}
    
}

