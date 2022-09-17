package frc.robot.subsystems.interfaces;

import frc.robot.subsystems.vision.LimelightLEDMode;
import frc.robot.subsystems.vision.Pipeline;

/**
 * returned 228 in at 208 inches
 */

/**
 * <h1> Limelight manager for power cell target tracking
 */
public interface Limelight {

  public Pipeline getDefaultPipeline(); // Modifier 'public' is redundant for interface members
  public void setPipeline(Pipeline pipeline);
  public void setLEDMode(LimelightLEDMode ledMode);
  public LimelightLEDMode getLEDMode();
  public boolean hasTarget();
  public double tx();
  public double ty();
  public double ta();
  public double ts();
  public double tl();
  public int tshort();
  public int tlong();
  public int thor();
  public int tvert();
  public double getLastPosition();

}