package frc.robot.io;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.utils.Constants;

public class XboxController {
  // Joystick
  private final Joystick m_controller;

  // Buttons 
  public final Button aButton;
  public final Button bButton;
  public final Button xButton;
  public final Button yButton;
  public final Button leftBumper;
  public final Button rightBumper;
  public final Button backArrow;
  public final Button startArrow;
  public final Button leftStickButton;
  public final Button rightStickButton;
  public final POVButton dPadUp;
  public final POVButton dPadUpRight;
  public final POVButton dPadRight;
  public final POVButton dPadDownRight;
  public final POVButton dPadDown;
  public final POVButton dPadDownLeft;
  public final POVButton dPadLeft;
  public final POVButton dPadUpLeft;
  
  public XboxController(int port) {
    // Joystick 
    m_controller = new Joystick(port);

    // Buttons
    aButton = new JoystickButton(m_controller, Constants.XBOX_A_BUTTON_PORT);
    bButton = new JoystickButton(m_controller, Constants.XBOX_B_BUTTON_PORT);
    xButton = new JoystickButton(m_controller, Constants.XBOX_X_BUTTON_PORT);
    yButton = new JoystickButton(m_controller, Constants.XBOX_Y_BUTTON_PORT);
    leftBumper = new JoystickButton(m_controller, Constants.XBOX_LEFT_BUMPER_PORT);
    rightBumper = new JoystickButton(m_controller, Constants.XBOX_RIGHT_BUMPER_PORT);
    backArrow = new JoystickButton(m_controller, Constants.XBOX_BACK_ARROW_PORT);
    startArrow = new JoystickButton(m_controller, Constants.XBOX_START_ARROW_PORT);
    leftStickButton = new JoystickButton(m_controller, Constants.XBOX_LEFT_STICK_BUTTON_PORT);
    rightStickButton = new JoystickButton(m_controller, Constants.XBOX_RIGHT_STICK_BUTTON_PORT);
    dPadUp = new POVButton(m_controller, Constants.XBOX_POV_UP_DEGREES);
    dPadUpRight = new POVButton(m_controller, Constants.XBOX_POV_UP_RIGHT_DEGREES);
    dPadRight = new POVButton(m_controller, Constants.XBOX_POV_RIGHT_DEGREES);
    dPadDownRight = new POVButton(m_controller, Constants.XBOX_POV_DOWN_RIGHT_DEGREES);
    dPadDown = new POVButton(m_controller, Constants.XBOX_POV_DOWN_DEGREES);
    dPadDownLeft = new POVButton(m_controller, Constants.XBOX_POV_DOWN_LEFT_DEGREES);
    dPadLeft = new POVButton(m_controller, Constants.XBOX_POV_LEFT_DEGREES);
    dPadUpLeft = new POVButton(m_controller, Constants.XBOX_POV_UP_LEFT_DEGREES);
  
  }

  /** @return XAxisLeft */
  public double getXAxisLeft() {
    return m_controller.getRawAxis(Constants.XBOX_X_AXIS_LEFT_PORT);
  }

  /** @return YAxisLeft */
  public double getYAxisLeft() {
    return m_controller.getRawAxis(Constants.XBOX_Y_AXIS_LEFT_PORT);
  }

  /** @return XAxisRight */
  public double getXAxisRight() {
    return m_controller.getRawAxis(Constants.XBOX_X_AXIS_RIGHT_PORT);
  }

  /** @return YAxisRight */
  public double getYAxisRight() {
    return m_controller.getRawAxis(Constants.XBOX_Y_AXIS_RIGHT_PORT);
  }

  /** @return Left Trigger */
  public double getLeftTrigger() {
    return m_controller.getRawAxis(Constants.XBOX_LEFT_TRIGGER_PORT);
  }

  /** @return Right Trigger */
  public double getRightTrigger() {
    return m_controller.getRawAxis(Constants.XBOX_RIGHT_TRIGGER_PORT);
  }

  /** Sets the left rumble
   * @param amount The amount to rumble between 0 (inclusive) and 1 (inclusive)
   */
  public void setLeftRumble(double amount) {
    m_controller.setRumble(Constants.XBOX_LEFT_RUMBLE, amount);
  }

  /** Sets the right rumble
   * @param amount The amount to rumble between 0 (inclusive) and 1 (inclusive)
   */
  public void setRightRumble(double amount) {
    m_controller.setRumble(Constants.XBOX_RIGHT_RUMBLE, amount);
  }

  /** Stops left rumble */
  public void stopLeftRumble() {
    m_controller.setRumble(Constants.XBOX_LEFT_RUMBLE, 0.0);
  }

  /** Stops right rumble */
  public void stopRightRumble() {
    m_controller.setRumble(Constants.XBOX_RIGHT_RUMBLE, 0.0);
  }

  /** Stops rumble */
  public void stopRumble() {
    stopLeftRumble();
    stopRightRumble();
  }

}