package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.interfaces.Limelight;
import frc.robot.subsystems.vision.LimelightLEDMode;

public class LimelightOn extends CommandBase {
  private final Limelight limelight;

  public LimelightOn(Limelight limelight) {
    this.limelight = limelight;
  }

  @Override
  public void execute() {
    limelight.setLEDMode(LimelightLEDMode.ON);
  }

}
