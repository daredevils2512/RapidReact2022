package frc.robot.subsystems;

import java.util.logging.Level;
import java.util.logging.Logger;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LoggingSubsystem extends SubsystemBase {
  protected Logger m_logger;
  public String name;

  /** Creates a new LoggingSubsystem. 
   * @param name Sets name of logger
   * @param logLevel Set level for subsystem to display logs at (default: WARNING)
  */
  public LoggingSubsystem(String name, Level logLevel) {
    this.name = name;
    m_logger = Logger.getLogger("subsystem." + name);
    m_logger.setParent(Logger.getGlobal());
    m_logger.log(Level.INFO, m_logger.getName() + " initialized");
    if (RobotBase.isSimulation()) {m_logger.setLevel(logLevel);} else {m_logger.setLevel(logLevel);}
    m_logger.log(Level.INFO, name + " logger started, level: " + m_logger.getLevel().toString());
  }
  
  /** Creates a new LoggingSubsystem. 
   * @param name Sets name of logger
  */
  public LoggingSubsystem(String name) {
    this.name = name;
    m_logger = Logger.getLogger("subsystem." + name);
    m_logger.setParent(Logger.getGlobal());
    m_logger.log(Level.INFO, m_logger.getName() + " initialized");
    if (RobotBase.isSimulation()) {
      m_logger.setLevel(Level.FINEST);
    } else {
      m_logger.setLevel(Level.WARNING);
    }
    m_logger.log(Level.INFO, name + " logger started, level: " + m_logger.getLevel().toString());
  }

  @Override
  /** Periodically runs code */
  public void periodic() { }
}