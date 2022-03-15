package frc.robot.subsystems.physical;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.subsystems.interfaces.NTSubsystem;
import frc.robot.subsystems.interfaces.Shooter;
import frc.robot.utils.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class PhysicalShooter extends NTSubsystem implements Shooter {
  // Motor stuff
  private final WPI_TalonFX m_motor;
  private final SlewRateLimiter m_limiter;
  private final SimpleMotorFeedforward feedforward;
  private final PIDController PID;

  // Network table stuff
  private final NetworkTableEntry m_speed;

  public PhysicalShooter() {
    super("Shooter");

    PID = new PIDController(Constants.SHOOTER_P, Constants.SHOOTER_I, Constants.SHOOTER_D);
    
    m_speed = NetworkTableInstance.getDefault().getTable("Test").getEntry("Speed");
    m_speed.setDouble(0);

    m_motor = new WPI_TalonFX(Constants.SHOOTER_ID);
      
    m_limiter = new SlewRateLimiter(Constants.SHOOTER_RATELIM_VALUE);
    feedforward = new SimpleMotorFeedforward(Constants.SHOOTER_FORWARD_CHANNEL, Constants.SHOOTER_BACKWARD_CHANNEL);
  }

  /** spits balls
   * @param speed the speed at which to spit said balls
   */
  public void spitBalls(double speed) {
    speed = m_limiter.calculate(speed);
    m_motor.set(speed);
    m_logger.fine("set: " + get());
  }  
  
  /** sets the voltage of the shooter
   * @param voltage the voltage to set the shooter
   */
  public void setVoltage(double voltage) {
    m_motor.setVoltage(voltage);
    m_logger.fine("set: " + get());
  }
  
  /** sets the RPM of the shooter
   * @param RPM the rpm to set the shooter to
   */
  public void setRPM(double RPM) {
    double voltage = feedforward.calculate(RPM);
    m_motor.setVoltage(voltage);
    m_logger.fine("set: " + get());
  }

  public void setRPMPID(double setpoint) {
    m_motor.set(PID.calculate(velocityToRPM(m_motor.getSelectedSensorVelocity()), setpoint));
  }

  public double velocityToRPM(double velocity) {
    velocity /= Constants.SHOOTER_ENCODER_RESOLUTION;
    return velocity * 600;
  }
  
  /** @return returns the shooter motor */
  public double get() {
    return m_motor.get(); 
  }

  public double getRPM() {
    return velocityToRPM(m_motor.getSelectedSensorVelocity());
  }
  
}
