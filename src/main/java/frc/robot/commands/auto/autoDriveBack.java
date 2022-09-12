package frc.robot.commands.auto;


import javax.measure.Quantity;
import javax.measure.quantity.Length;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.interfaces.Drivetrain;
import systems.uom.common.USCustomary;
import tech.units.indriya.quantity.Quantities;

public class AutoDriveBack extends CommandBase {
  private final Drivetrain m_drivetrain;
  private Quantity<Length> m_initialDistance = Quantities.getQuantity(0, USCustomary.FOOT);
  private final Quantity<Length> m_distance;
  private final double m_speed;

  public AutoDriveBack(Drivetrain drivetrain, double speed, Quantity<Length> distance) {
    m_drivetrain = drivetrain;
    m_speed = speed;
    m_distance = distance;
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    m_initialDistance = m_drivetrain.getDistance();
  }

  @Override
  public void execute() {
    m_drivetrain.arcadeDrive(m_speed, 0);
  }

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    if (m_distance.getValue().doubleValue() < 0) {
      return (m_drivetrain.getDistance().subtract(m_initialDistance)).getValue().doubleValue() < m_distance.getValue().doubleValue();
    } else {
      return (m_drivetrain.getDistance().subtract(m_initialDistance)).getValue().doubleValue() > m_distance.getValue().doubleValue();
    }
  }
}
