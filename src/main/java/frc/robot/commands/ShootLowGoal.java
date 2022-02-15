package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;

public class ShootLowGoal extends ParallelCommandGroup {
  public ShootLowGoal(Shooter shooter, Magazine magazine){
    addRequirements(shooter, magazine);
  }
}
