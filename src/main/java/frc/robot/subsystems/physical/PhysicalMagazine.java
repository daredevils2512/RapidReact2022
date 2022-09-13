package frc.robot.subsystems.physical;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//special hole for cale
import frc.robot.subsystems.NTSubsystem;
import frc.robot.subsystems.interfaces.Magazine;
import frc.robot.utils.Constants;

public class PhysicalMagazine extends NTSubsystem implements Magazine {
  // Motors
  private final WPI_TalonSRX magMotor;

  public PhysicalMagazine() {
    super("Magazine");

    // Assign motors
    magMotor = new WPI_TalonSRX(Constants.MAG_ID);
  }

  @Override
  public void moveBalls(double magSpeed) {
    magMotor.set(ControlMode.PercentOutput, - magSpeed);
    logger.fine("move balls: " + magMotor.get());
  }
}