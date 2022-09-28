package frc.robot.subsystems.physical;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
<<<<<<< HEAD
//special hole for cale
=======

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.sensors.DareDigitalInput;
import frc.robot.sensors.DummyDigitalInput;
import frc.robot.sensors.PhysicalDigitalInput;
>>>>>>> origin/photo-eye
import frc.robot.subsystems.NTSubsystem;
import frc.robot.subsystems.interfaces.Magazine;
import frc.robot.utils.Constants;

public class PhysicalMagazine extends NTSubsystem implements Magazine {
  // Motors
  private final WPI_TalonSRX magMotor;

  private final DareDigitalInput photoeye;

  private final NetworkTable table;
  private final NetworkTableEntry photoEyeEntry;

  public PhysicalMagazine() {
    super("Magazine");

    table = NetworkTableInstance.getDefault().getTable("magazine");
  photoEyeEntry = table.getEntry("mag photo-eye");

    // Assign motors
<<<<<<< HEAD
    magMotor = new WPI_TalonSRX(Constants.MAG_ID);
=======
    m_magMotor = new WPI_TalonSRX(Constants.MAG_ID);

    photoeye = Constants.PHOTO_EYE_ENABLED ? new PhysicalDigitalInput(Constants.PHOTO_EYE_PORT) : new DummyDigitalInput();
  }

  @Override
  public void periodic() {
    photoEyeEntry.setBoolean(photoeye.get());
>>>>>>> origin/photo-eye
  }

  @Override
  public void moveBalls(double magSpeed) {
    magMotor.set(ControlMode.PercentOutput, - magSpeed);
    logger.fine("move balls: " + magMotor.get());
  }
}