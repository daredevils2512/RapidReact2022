package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.interfaces.Limelight;
import frc.robot.subsystems.vision.LimelightLEDMode;

public class LimelightOff extends CommandBase {
  private final Limelight limelight;

  public LimelightOff(Limelight limelight) {
    this.limelight = limelight;
  }

  @Override
  public void execute() {
    limelight.setLEDMode(LimelightLEDMode.OFF);
  }

}
