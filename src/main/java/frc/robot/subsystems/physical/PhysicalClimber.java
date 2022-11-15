package frc.robot.subsystems.physical;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.subsystems.NTSubsystem;
import frc.robot.subsystems.interfaces.Climber;
import frc.robot.utils.Constants;

public class PhysicalClimber extends NTSubsystem implements Climber {
  // Motor Stuff
  private final TalonSRX rightMotor;
  private final TalonSRX leftMotor;

  // Digital Inputs
  private final DigitalInput topLimitSwitch = new DigitalInput(Constants.CLIMBER_TOP_LIMIT_SWITCH_PORT);
  private final DigitalInput bottomLimitSwitch = new DigitalInput(Constants.CLIMBER_BOTTOM_LIMIT_SWITCH_PORT);

  public PhysicalClimber() {
    super("ClimberSubsystem");

    // Make Motors
    rightMotor = new TalonSRX(Constants.CLIMBER_1ID);
    leftMotor = new TalonSRX(Constants.CLIMBER_2ID);
    leftMotor.follow(rightMotor);
    leftMotor.setInverted(true);
  }

  @Override
  public void setClimbSpeed(double speed) { 
    speed = speed * Constants.CLIMBER_REVERSED;
    if (topLimitSwitch.get() && speed < 0) {
      rightMotor.set(ControlMode.PercentOutput, 0);
    } else if (bottomLimitSwitch.get() && speed > 0) { 
      rightMotor.set(ControlMode.PercentOutput, 0);
    } else {
      rightMotor.set(ControlMode.PercentOutput, speed);
      logger.fine("setclimbspeed: " + speed);
    }
  }
}
