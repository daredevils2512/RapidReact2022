package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.vision.Limelight;
import frc.robot.vision.LimelightLEDMode;

public class LimelightOn extends CommandBase {
  private final Limelight m_limelight;

  public LimelightOn(Limelight limelight) {
    m_limelight = limelight;
  }

  @Override
  public void execute() {
    m_limelight.setLEDMode(LimelightLEDMode.ON);
  }

}
