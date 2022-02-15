package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class RunFlywheel extends CommandBase {
  private final Shooter m_flywheel;
  private final DoubleSupplier m_speed;

  public RunFlywheel(Shooter flywheel, DoubleSupplier speed) {
    m_flywheel = flywheel;
    m_speed = speed;
  
    addRequirements(flywheel);
  }

  /** Called when the command is initially scheduled. */
  @Override
  public void initialize() {}

  /** Called every time the scheduler runs while the command is scheduled. */
  @Override
  public void execute() {
    m_flywheel.setRPM(m_speed.getAsDouble());
  }

  /** Called once the command ends or is interrupted. */
  @Override
  public void end(boolean interrupted) {}

  /** Returns true when the command should end. */
  @Override
  public boolean isFinished() {
    return false;
  }
}
