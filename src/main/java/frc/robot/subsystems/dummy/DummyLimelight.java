package frc.robot.subsystems.dummy;

import frc.robot.subsystems.vision.LimelightLEDMode;
import frc.robot.subsystems.vision.Pipeline;

public class DummyLimelight implements frc.robot.subsystems.interfaces.Limelight {

  @Override
  public Pipeline getDefaultPipeline() { return Pipeline.N_E_D; } 

  @Override
  public void setPipeline(Pipeline pipeline) { }

  @Override
  public void setLEDMode(LimelightLEDMode ledMode) { }

  @Override
  public LimelightLEDMode getLEDMode() { return LimelightLEDMode.OFF; } 

  @Override
  public boolean hasTarget() { return false; }

  @Override
  public double tx() { return 0; }

  @Override
  public double ty() { return 0; }

  @Override
  public double ta() { return 0; }

  @Override
  public double ts() { return 0; }

  @Override
  public double tl() { return 0; }

  @Override
  public int tshort() { return 0; }

  @Override
  public int tlong() { return 0; }

  @Override
  public int thor() { return 0; }

  @Override
  public int tvert() { return 0; }

  @Override
  public double getLastPosition() { return 0; }
  
}
