package frc.robot.subsystems.physical;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.NTSubsystem;
import frc.robot.utils.Constants;

public class PhysicalMagazine extends NTSubsystem implements Magazine {
  // Motors
  private final WPI_TalonSRX m_magMotor;

  public PhysicalMagazine() {
    super("Magazine");

    // Assign motors
    m_magMotor = new WPI_TalonSRX(Constants.magID);
  }

  public void moveBalls (double magSpeed) {
    m_magMotor.set(ControlMode.PercentOutput, magSpeed);
    m_logger.fine("move balls: " + m_magMotor.get());
  }
}