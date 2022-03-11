package frc.robot;

import java.util.Optional;
import java.util.logging.Level;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Vision.Limelight;
import frc.robot.Vision.Limelight.Pipeline;
import frc.robot.commands.Autonomous;
import frc.robot.commands.ActuateShiftCommand;
import frc.robot.commands.Aim;
import frc.robot.commands.ClimberCommand;
import frc.robot.commands.DriveBackAutoCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.RevShooterAutoCommand;
import frc.robot.io.NTButton;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.commands.RunFlywheelCommand;
import frc.robot.commands.RunMagCommand;
import frc.robot.commands.ShootLowGoalCommand;
import frc.robot.commands.DriveShiftCommand;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.commands.RevShooterCommand;
import frc.robot.commands.FindRange;
import frc.robot.io.ControlBoard;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;
import frc.robot.utils.Constants;
import frc.robot.utils.LoggingManager;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

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
  private final NetworkTableEntry m_useNTShooterControlEntry;
  private final NetworkTableEntry m_shooterSpeedEntry;

  // Logging
  private final LoggingManager m_logManager;

  // Subsystems
  private final Optional<Drivetrain> m_drivetrainSub;
  private final Optional<Climber> m_climber;
  private final Optional<Intake> m_intakeSub;
  private final Optional<Magazine> m_magazine;
  private final Optional<Shooter> m_shooter;
  
  
  // Commands
  private final Command m_intakeShift;
  private final Command m_climberUpComamnd;
  private final Command m_climberDownComamnd;
  private final Command m_autoDriveBack;
  private final Command m_autoDriveBackAndShoot;
  private final Command m_driveShift;
  private final Command m_drivetrainCommand;
  private final Command m_intakeCommand;
  private final Command m_revShooter;
  private final Command m_revShooter2;
  // private final Command m_runFlywheel;
  private Command m_runMag;
  private final Command m_shootLowGoal;

  // private final Command m_aim;
  // private final Command m_FindRange;

  private final Limelight m_limelight;

  // Controls
  private final ControlBoard m_controlBoard;

  public enum Axis {
    kLeftX(0),
    kRightX(4),
    kLeftY(1),
    kRightY(5),
    kLeftTrigger(2),
    kRightTrigger(3);

    @SuppressWarnings("MemberName")
    public final int value;

    Axis(int value) {
      this.value = value;
    }
  }

  /** @return Left Stick y-Axis */
  public double getMove() {
    return m_controlBoard.xboxController.getYAxisLeft();
  }
// MARIOOOO NOOOOOOOO
  /** @return Right Stick x-Axis */
  public double getTurn() {
    return m_controlBoard.xboxController.getXAxisRight();
  }

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Define optionals
    m_climber = Optional.of( new Climber());
    m_drivetrainSub = Optional.of(new Drivetrain());
    m_intakeSub = Optional.of( new Intake());
    m_magazine = Optional.of( new Magazine());
    m_shooter = Optional.of(new Shooter());
    m_limelight = new Limelight(Pipeline.N_E_D);
    // m_shooter = Optional.empty();



    // Define commands
    m_intakeShift = m_intakeSub.isPresent() ? new ActuateShiftCommand(m_intakeSub.get()) : null;
    m_climberUpComamnd = m_climber.isPresent() ? new ClimberCommand(m_climber.get(), Constants.climberSpeed): null;
    m_climberDownComamnd = m_climber.isPresent() ? new ClimberCommand(m_climber.get(), -Constants.climberSpeed) : null;
    m_driveShift = m_drivetrainSub.isPresent() ? new DriveShiftCommand(m_drivetrainSub.get()) : null;
    m_drivetrainCommand = m_drivetrainSub.isPresent() ? new DrivetrainCommand(m_drivetrainSub.get(), () -> { return getMove(); }, () -> { return getTurn(); }) : null;
    m_runMag = m_magazine.isPresent() ? new RunMagCommand(m_magazine.get(), () -> 1) : null;
    // m_auto = m_drivetrainSub.isPresent() ? new Autonomous(m_drivetrainSub.get(), Constants.DRIVE_AUTO_SPEED, Constants.AUTO_DRIVE_BACK_DISTANCE, m_runFlywheel, m_runMag, 234) : null; //TODO change shooter value 
    
    m_autoDriveBackAndShoot = m_drivetrainSub.isPresent() && m_shooter.isPresent() && m_magazine.isPresent() ?
      new RevShooterAutoCommand(m_shooter.get()).withTimeout(5).andThen(new RunMagCommand(m_magazine.get(),()-> 1).withTimeout(2).alongWith(new IntakeCommand(m_intakeSub.get(), ()-> 1)).withTimeout(2)).andThen(new DriveShiftCommand(m_drivetrainSub.get() )).andThen( new DriveBackAutoCommand(m_drivetrainSub.get(), Constants.DRIVE_AUTO_SPEED, Constants.AUTO_DRIVE_BACK_DISTANCE)) : null;  
   
      m_autoDriveBack = m_drivetrainSub.isPresent() ? new DriveBackAutoCommand(m_drivetrainSub.get(), Constants.DRIVE_AUTO_SPEED, Constants.AUTO_DRIVE_BACK_DISTANCE) : null;
    m_intakeCommand = m_intakeSub.isPresent() ? new IntakeCommand(m_intakeSub.get(), ()-> 1) : null;
    m_revShooter = m_shooter.isPresent() ? new RevShooterCommand(m_shooter.get(), .75) : null;
    m_revShooter2 = m_shooter.isPresent()?new RevShooterCommand(m_shooter.get(), .25):null;
    // m_runFlywheel = m_shooter.isPresent() ? new RunFlywheel(m_shooter.get()) : null;
    m_runMag = m_magazine.isPresent() ? new RunMagCommand(m_magazine.get(), () -> 1) : null;
    m_shootLowGoal = null; // TODO: idk what this is

    // m_aim = m_drivetrainSub.isPresent() ? new Aim(m_drivetrainSub.get(), m_limelight):null;
    // m_FindRange = m_drivetrainSub.isPresent() ? new FindRange(m_drivetrainSub.get()) :null;

    // m_runFlywheel = m_shooter.isPresent() ? new RunFlywheelCommand(m_shooter.get()) : null;
    

    
    

    // Define
    m_logManager = new LoggingManager();
    m_controlBoard = new ControlBoard();

    // Configure the button bindings
    if (RobotBase.isSimulation())
      m_logManager.robotLogger.setLevel(Level.FINER);
    configureButtonBindings();

    // Network Table stuff
    NTButton.startConcurrentHandling();
    m_useNTShooterControlEntry = NetworkTableInstance.getDefault().getEntry("Use network tables for shooter control");
    m_shooterSpeedEntry = NetworkTableInstance.getDefault().getEntry("Shooter set speed");
    m_useNTShooterControlEntry.setBoolean(false);
    m_shooterSpeedEntry.setDouble(0);

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
    // TODO Make correct controls
    if (m_intakeSub.isPresent()) m_controlBoard.extreme.baseMiddleLeft.whenPressed(m_intakeShift);
    if (m_intakeSub.isPresent()) m_controlBoard.extreme.baseMiddleRight.whileHeld(m_intakeCommand);

    if (m_climber.isPresent()) m_controlBoard.extreme.joystickTopLeft.whileHeld(m_climberUpComamnd);
    if (m_climber.isPresent()) m_controlBoard.extreme.joystickTopRight.whileHeld(m_climberDownComamnd);
    // m_auto command here
    if (m_drivetrainSub.isPresent()) m_controlBoard.xboxController.rightBumper.whenPressed(m_driveShift);
    if (m_drivetrainSub.isPresent()) m_drivetrainSub.get().setDefaultCommand(m_drivetrainCommand);
    
    if (m_shooter.isPresent()) m_controlBoard.extreme.sideButton.whileHeld(m_revShooter);
    if (m_shooter.isPresent()) m_controlBoard.extreme.baseBackLeft.whileHeld(m_revShooter2);
    // if (m_shooter.isPresent()) m_controlBoard.buttonBox.topWhite.whileHeld(m_runFlywheel);
    if (m_magazine.isPresent()) m_controlBoard.extreme.trigger.whileHeld(m_runMag);

    // if (m_drivetrainSub.isPresent()) m_controlBoard.extreme.joystickBottomLeft.whileHeld(m_aim);
    // if (m_drivetrainSub.isPresent()) m_controlBoard.extreme.joystickBottomRight.whileHeld(m_FindRange); 
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * 
   * @return The command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autoDriveBackAndShoot;
  }
} 