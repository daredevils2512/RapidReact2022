package frc.robot.io;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.utils.Constants;

public class XboxController {
  // Joystick
  private final Joystick controller;

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
    controller = new Joystick(port);

    // Buttons
    aButton = new JoystickButton(controller, Constants.XBOX_A_BUTTON_PORT);
    bButton = new JoystickButton(controller, Constants.XBOX_B_BUTTON_PORT);
    xButton = new JoystickButton(controller, Constants.XBOX_X_BUTTON_PORT);
    yButton = new JoystickButton(controller, Constants.XBOX_Y_BUTTON_PORT);
    leftBumper = new JoystickButton(controller, Constants.XBOX_LEFT_BUMPER_PORT);
    rightBumper = new JoystickButton(controller, Constants.XBOX_RIGHT_BUMPER_PORT);
    backArrow = new JoystickButton(controller, Constants.XBOX_BACK_ARROW_PORT);
    startArrow = new JoystickButton(controller, Constants.XBOX_START_ARROW_PORT);
    leftStickButton = new JoystickButton(controller, Constants.XBOX_LEFT_STICK_BUTTON_PORT);
    rightStickButton = new JoystickButton(controller, Constants.XBOX_RIGHT_STICK_BUTTON_PORT);
    dPadUp = new POVButton(controller, Constants.XBOX_POV_UP_DEGREES);
    dPadUpRight = new POVButton(controller, Constants.XBOX_POV_UP_RIGHT_DEGREES);
    dPadRight = new POVButton(controller, Constants.XBOX_POV_RIGHT_DEGREES);
    dPadDownRight = new POVButton(controller, Constants.XBOX_POV_DOWN_RIGHT_DEGREES);
    dPadDown = new POVButton(controller, Constants.XBOX_POV_DOWN_DEGREES);
    dPadDownLeft = new POVButton(controller, Constants.XBOX_POV_DOWN_LEFT_DEGREES);
    dPadLeft = new POVButton(controller, Constants.XBOX_POV_LEFT_DEGREES);
    dPadUpLeft = new POVButton(controller, Constants.XBOX_POV_UP_LEFT_DEGREES);
  
  }

  /** @return XAxisLeft */
  public double getXAxisLeft() {
    return controller.getRawAxis(Constants.XBOX_X_AXIS_LEFT_PORT);
  }

  /** @return YAxisLeft */
  public double getYAxisLeft() {
    return controller.getRawAxis(Constants.XBOX_Y_AXIS_LEFT_PORT);
  }

  /** @return XAxisRight */
  public double getXAxisRight() {
    return controller.getRawAxis(Constants.XBOX_X_AXIS_RIGHT_PORT);
  }

  /** @return YAxisRight */
  public double getYAxisRight() {
    return controller.getRawAxis(Constants.XBOX_Y_AXIS_RIGHT_PORT);
  }

  /** @return Left Trigger */
  public double getLeftTrigger() {
    return controller.getRawAxis(Constants.XBOX_LEFT_TRIGGER_PORT);
  }

  /** @return Right Trigger */
  public double getRightTrigger() {
    return controller.getRawAxis(Constants.XBOX_RIGHT_TRIGGER_PORT);
  }

  /** Sets the left rumble
   * @param amount The amount to rumble between 0 (inclusive) and 1 (inclusive)
   */
  public void setLeftRumble(double amount) {
    controller.setRumble(Constants.XBOX_LEFT_RUMBLE, amount);
  }

  /** Sets the right rumble
   * @param amount The amount to rumble between 0 (inclusive) and 1 (inclusive)
   */
  public void setRightRumble(double amount) {
    controller.setRumble(Constants.XBOX_RIGHT_RUMBLE, amount);
  }

  /** Stops left rumble */
  public void stopLeftRumble() {
    controller.setRumble(Constants.XBOX_LEFT_RUMBLE, 0.0);
  }

  /** Stops right rumble */
  public void stopRightRumble() {
    controller.setRumble(Constants.XBOX_RIGHT_RUMBLE, 0.0);
  }

  /** Stops rumble */
  public void stopRumble() {
    stopLeftRumble();
    stopRightRumble();
  }

  /** @return True if the DPad is being pressed on the top. */
  public boolean getDPadTop() {
    return getDPad() == 0;
  }

  /** @return True if the DPad is being pressed on the top right. */
  public boolean getDPadTopRight() {
    return getDPad() == 45;
  }

  /** @return True if the DPad is being pressed on the right. */
  public boolean getDPadRight() {
    return getDPad() == 90;
  }

  /** @return True if the DPad is being pressed on the bottom right. */
  public boolean getDPadBottomRight() {
    return getDPad() == 135;
  }

  /** @return True if the DPad is being pressed on the bottom. */
  public boolean getDPadBottom() {
    return getDPad() == 180;
  }

  /** @return True if the DPad is being pressed on the bottom left. */
  public boolean getDPadBottomLeft() {
    return getDPad() == 225;
  }

  /** @return True if the DPad is being pressed on the left. */
  public boolean getDPadLeft() {
    return getDPad() == 270;
  }

  /** @return True if the DPad is being pressed on the top left. */
  public boolean getDPadTopLeft() {
    return getDPad() == 315;
  }

  /** @return True if the DPad is not being pressed. */
  public boolean getDPadReleased() {
    return getDPad() == -1;
  }

  /** @return The angle of the DPad in degrees.
   * The angle is represented in 8 degrees (right being 90, left being 270).
   * If none are being pressed, -1 is returned.
   */
  public double getDPad() {
    return controller.getPOV();
  }

}