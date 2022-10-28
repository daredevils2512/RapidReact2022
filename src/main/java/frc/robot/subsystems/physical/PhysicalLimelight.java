package frc.robot.subsystems.physical;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.subsystems.interfaces.Limelight;
import frc.robot.subsystems.vision.LimelightLEDMode;
import frc.robot.subsystems.vision.Pipeline;

public class PhysicalLimelight implements Limelight {
  private static Logger logger = Logger.getLogger(Limelight.class.getName());
  // Center is (0,0)
  public static final double RANGE_X_DEGREES = 29.8;
  public static final double RANGE_Y_DEGREES = 24.85;
  // TODO: Unused variables??
  private final double angle = 20;
  private final double heightOffset = 98.25 - 19.25;
  private final Pipeline pipeline;

  private NetworkTable table;

  private double lastPostion;

  public PhysicalLimelight(Pipeline defaultPipeline) {
    pipeline = defaultPipeline;
    table = NetworkTableInstance.getDefault().getTable("limelight");
    this.setPipeline(defaultPipeline);
    lastPostion = 1.0;
  }

  public Pipeline getDefaultPipeline() {
    logger.log(Level.FINE, "default pipeline = ", pipeline);
    return pipeline;
  }

  //Limelight table getters

  public void setPipeline(Pipeline pipeline) {
    table.getEntry("pipeline").setNumber(pipeline.getID());
  }

  public void setLEDMode(LimelightLEDMode ledMode) {
    table.getEntry("ledMode").setNumber(ledMode.getIntValue());
  }

  public LimelightLEDMode getLEDMode() {
    int intValue = table.getEntry("ledMode").getNumber(0).intValue();
    return LimelightLEDMode.fromIntValue(intValue);
  }

  public boolean hasTarget() {
    return table.getEntry("tv").getNumber(0).intValue() == 1;
  }

  public double tx() {
    return table.getEntry("tx").getNumber(0).doubleValue();
  }

  public double ty() {
    return table.getEntry("ty").getNumber(0).doubleValue();
  }

  public double ta() {
    return table.getEntry("ta").getNumber(0).doubleValue();
  }

  public double ts() {
    return table.getEntry("ts").getNumber(0).doubleValue();
  }

  public double tl() {
    return table.getEntry("tl").getNumber(0).doubleValue();
  }

  public int tshort() {
    return table.getEntry("tshort").getNumber(0).intValue();
  }

  public int tlong() {
    return table.getEntry("tlong").getNumber(0).intValue();
  }

  public int thor() {
    return table.getEntry("thor").getNumber(0).intValue();
  }

  public int tvert() {
    return table.getEntry("tvert").getNumber(0).intValue();
  }

  public double getLastPosition() {
    if (tx() != 0) { 
      lastPostion = tx(); 
    }
    logger.log(Level.FINER, "last pos", lastPostion);
    return lastPostion;
  }

}
