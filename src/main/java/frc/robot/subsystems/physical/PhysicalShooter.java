package frc.robot.subsystems.physical;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.subsystems.NTSubsystem;
import frc.robot.subsystems.interfaces.Shooter;
import frc.robot.utils.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class PhysicalShooter extends NTSubsystem implements Shooter {
  // Motor stuff
  private final WPI_TalonFX motor;
  private final SlewRateLimiter limiter;
  private final SimpleMotorFeedforward feedforward;
  private final PIDController PID;

  // Network table stuff
  private final NetworkTableEntry speed;

  public PhysicalShooter() {
    super("Shooter");

    PID = new PIDController(Constants.SHOOTER_P, Constants.SHOOTER_I, Constants.SHOOTER_D);
    
    speed = NetworkTableInstance.getDefault().getTable("Test").getEntry("Speed");
    speed.setDouble(0);

    motor = new WPI_TalonFX(Constants.SHOOTER_ID);
      
    limiter = new SlewRateLimiter(Constants.SHOOTER_RATELIM_VALUE);
    feedforward = new SimpleMotorFeedforward(Constants.SHOOTER_K_S, Constants.SHOOTER_K_V); 
  }

  @Override
  public void spitBalls(double speed) {
    speed = limiter.calculate(speed);
    motor.set(speed);
    logger.fine("set: " + get());
  }  
  
  @Override
  public void setVoltage(double voltage) {
    motor.setVoltage(voltage);
    logger.fine("set: " + get());
  }
  
  @Override
  public void setRPM(double RPM) {
    double voltage = feedforward.calculate(RPM);
    motor.setVoltage(voltage);
    logger.fine("set: " + get());
  }

  @Override
  public void setRPMPID(double setpoint) {
    motor.set(PID.calculate(velocityToRPM(motor.getSelectedSensorVelocity()), setpoint));
  }

  @Override
  public double velocityToRPM(double velocity) {
    velocity /= Constants.SHOOTER_ENCODER_RESOLUTION; // TODO This is still a divide by zero because we still have to find it because build
    return velocity * 600;
  }
  
  @Override
  public double get() {
    return motor.get(); 
  }

  @Override
  public double getRPM() {
    return velocityToRPM(motor.getSelectedSensorVelocity());
  }
  
}
