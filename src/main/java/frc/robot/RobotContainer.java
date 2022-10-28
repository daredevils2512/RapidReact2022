package frc.robot;

import java.util.logging.Level;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.io.ControlBoard;
import frc.robot.io.NTButton;
import frc.robot.utils.Constants;
import frc.robot.utils.LoggingManager;
import frc.robot.commands.Commands;
import frc.robot.commands.VisionCommands;
import frc.robot.commands.AutoCommands;
import frc.robot.subsystems.dummy.DummyDrivetrain;
import frc.robot.subsystems.dummy.DummyIntake;
import frc.robot.subsystems.dummy.DummyLEDManager;
import frc.robot.subsystems.dummy.DummyLimelight;
import frc.robot.subsystems.dummy.DummyMagazine;
import frc.robot.subsystems.dummy.DummyShooter;
import frc.robot.subsystems.interfaces.Climber;
import frc.robot.subsystems.interfaces.CompresserManager;
import frc.robot.subsystems.interfaces.Drivetrain;
import frc.robot.subsystems.interfaces.Intake;
import frc.robot.subsystems.interfaces.LEDManager;
import frc.robot.subsystems.interfaces.Limelight;
import frc.robot.subsystems.interfaces.Magazine;
import frc.robot.subsystems.interfaces.Shooter;
import frc.robot.subsystems.dummy.DummyClimber;
import frc.robot.subsystems.dummy.DummyCompressor;
import frc.robot.subsystems.physical.PhysicalLEDManager;
import frc.robot.subsystems.physical.PhysicalLimelight;
import frc.robot.subsystems.physical.PhysicalClimber;
import frc.robot.subsystems.physical.PhysicalCompressor;
import frc.robot.subsystems.physical.PhysicalDrivetrain;
import frc.robot.subsystems.physical.PhysicalIntake;
import frc.robot.subsystems.physical.PhysicalMagazine;
import frc.robot.subsystems.physical.PhysicalShooter;
import frc.robot.subsystems.vision.Pipeline;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Network table stuff
  private final NetworkTableEntry useNTShooterControlEntry;
  private final NetworkTableEntry shooterSpeedEntry;

  // Logging
  private final LoggingManager logManager;

  // Subsystems
  private final Climber climber;
  private final CompresserManager compressor;
  private final Drivetrain drivetrain;
  private final Intake intake;
  private final LEDManager LED;
  private final Limelight limelight;
  private final Magazine magazine;
  private final Shooter shooter;

  // Commands
    // Autos
  private final Command autoDrive;
  private final Command autoShoot;
  private final Command autoFull;
    // Climber
  private final Command climberUp;
  private final Command climberDown;
    // Drive
  private final Command drive;
  private final Command driveShift;
    // Intake
  private final Command takeBalls;
  private final Command intakeShift;
    // Vision
  private final Command aim;
  private final Command FindRange;
    // LEDs
  private final Command LEDToggle;
    // Magazine
  private final Command runMag;
  private final Command runMagBack;
    // Shooter
  private final Command revShooterFast;
  private final Command revShooterSlow;

  private final Command limelightOn;
  private final Command limelightOff;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Other Stuff
    logManager = new LoggingManager();

    // Define Subsystems
    LED = Constants.LED_ENABLED ? new PhysicalLEDManager() : new DummyLEDManager();
    climber = Constants.CLIMBER_ENABLED ? new PhysicalClimber() : new DummyClimber();
    compressor = Constants.COMPRESSOR_ENABLED ? new PhysicalCompressor() : new DummyCompressor();
    drivetrain = Constants.DRIVETRAIN_ENABLED ? new PhysicalDrivetrain() : new DummyDrivetrain();
    intake = Constants.INTAKE_ENABLED ? new PhysicalIntake() : new DummyIntake();
    limelight = Constants.LIMELIGHT_ENABLED ? new PhysicalLimelight(Pipeline.N_E_D) : new DummyLimelight();
    magazine = Constants.MAGAZINE_ENABLED ? new PhysicalMagazine() : new DummyMagazine();
    shooter = Constants.SHOOTER_ENABLED ? new PhysicalShooter() : new DummyShooter();

    // Commands
      // Autos
    autoDrive = AutoCommands.autoDriveBack(drivetrain, Constants.AUTO_DRIVE_SPEED, Constants.AUTO_DRIVE_BACK_DISTANCE);
    autoShoot = AutoCommands.autoShoot(shooter, magazine, Constants.AUTO_SHOOT_SPEED);
    autoFull = AutoCommands.fullAuto(drivetrain, Constants.AUTO_DRIVE_SPEED, Constants.AUTO_DRIVE_BACK_DISTANCE, shooter, magazine, Constants.AUTO_SHOOT_SPEED);
      // Compressor
    compressor.setClosedLoopControl(true);
      // Climber
    climberUp = Commands.runClimber(climber, -Constants.CLIMBER_SPEED);
    climberDown = Commands.runClimber(climber, Constants.CLIMBER_SPEED);
      // Drive
    drive = Commands.drive(drivetrain, () -> ControlBoard.xboxController.getYAxisLeft(), () -> ControlBoard.xboxController.getXAxisRight());
    driveShift = Commands.driveShifters(drivetrain);
      // Intake
    takeBalls = Commands.runIntake(intake, () -> Constants.TAKE_BALLS_SPEED);
    intakeShift = Commands.intakeShifters(intake);
      // Vision
    aim = VisionCommands.aim(drivetrain);
    FindRange = VisionCommands.findRange(drivetrain);
      // LEDs
    LEDToggle = Commands.toggleLEDs(LED);
      // Magazine
    runMag = Commands.runMag(magazine, () -> Constants.MAG_SPEED);
    runMagBack = Commands.runMag(magazine, () -> -Constants.MAG_SPEED);
      // Shooter
    revShooterFast = Commands.revShooter(shooter, Constants.SHOOTER_FAST_SPEED);
    revShooterSlow = Commands.revShooter(shooter, Constants.SHOOTER_SLOW_SPEED);
      // Limelight
    limelightOn = VisionCommands.limelightOn(limelight);
    limelightOff = VisionCommands.limelightOff(limelight);

    // Configure the button bindings
    if (RobotBase.isSimulation()) logManager.robotLogger.setLevel(Level.FINER);

    // start camera server
    UsbCamera camera = CameraServer.startAutomaticCapture();
    camera.setResolution(640, 480);
    
    configureButtonBindings();

    // Network Table stuff
    NTButton.startConcurrentHandling();
    useNTShooterControlEntry = NetworkTableInstance.getDefault().getEntry("Use network tables for shooter control");
    shooterSpeedEntry = NetworkTableInstance.getDefault().getEntry("Shooter set speed");
    useNTShooterControlEntry.setBoolean(false);
    shooterSpeedEntry.setDouble(0);

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Button Bindings
      // Climbers
    ControlBoard.extreme.joystickTopLeft.whileHeld(climberUp);
    ControlBoard.extreme.joystickTopRight.whileHeld(climberDown);
      // Drive
    drivetrain.setDefaultCommand(drive);
    ControlBoard.xboxController.rightBumper.whenPressed(driveShift);
      // Intake
    ControlBoard.extreme.baseMiddleRight.whileHeld(takeBalls);
    ControlBoard.extreme.baseMiddleLeft.whenPressed(intakeShift);
      // Aim
    ControlBoard.extreme.joystickBottomLeft.whenPressed(limelightOn);
    ControlBoard.extreme.joystickBottomLeft.whileHeld(aim);
    ControlBoard.extreme.joystickBottomLeft.whenReleased(limelightOff);
      // LEDs
      ControlBoard.extreme.baseFrontLeft.whenPressed(LEDToggle);
      // Find Range
    ControlBoard.extreme.joystickBottomRight.whenPressed(limelightOn);
    ControlBoard.extreme.joystickBottomRight.whileHeld(FindRange);
    ControlBoard.extreme.joystickBottomRight.whenReleased(limelightOff);
      // Magazine
    ControlBoard.extreme.trigger.whileHeld(runMag);
    ControlBoard.extreme.baseBackRight.whileHeld(runMagBack);
      // Shooter
    ControlBoard.extreme.sideButton.whileHeld(revShooterFast);
    ControlBoard.extreme.baseBackLeft.whileHeld(revShooterSlow);
  }

  /** Use this to pass the autonomous command to the main {@link Robot} class.
   * @return The command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoFull;
  }
} 