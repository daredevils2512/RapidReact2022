package frc.robot.subsystems;

import java.util.logging.Level;
import java.util.logging.Logger;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LoggingSubsystem extends SubsystemBase {
  protected Logger logger;
  public String name;

  /** Creates a new LoggingSubsystem. 
   * @param name Sets name of logger
   * @param logLevel Set level for subsystem to display logs at (default: WARNING)
  */
  public LoggingSubsystem(String name, Level logLevel) {
    this.name = name;
    logger = Logger.getLogger("subsystem." + name);
    logger.setParent(Logger.getGlobal());
    logger.log(Level.INFO, logger.getName() + " initialized");
    if (RobotBase.isSimulation()) { // TODO: Redundant if statement??
      logger.setLevel(logLevel);
    } else {
      logger.setLevel(logLevel);
    }
    logger.log(Level.INFO, name + " logger started, level: " + logger.getLevel().toString());
  }
  
  /** Creates a new LoggingSubsystem. 
   * @param name Sets name of logger
  */
  public LoggingSubsystem(String name) {
    this.name = name;
    logger = Logger.getLogger("subsystem." + name);
    logger.setParent(Logger.getGlobal());
    logger.log(Level.INFO, logger.getName() + " initialized");
    if (RobotBase.isSimulation()) {
      logger.setLevel(Level.FINEST);
    } else {
      logger.setLevel(Level.WARNING);
    }
    logger.log(Level.INFO, name + " logger started, level: " + logger.getLevel().toString());
  }

  @Override
  /** Periodically runs code */
  public void periodic() { }
}