package frc.robot.io;

import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.utils.Constants;

public class XboxController {
  // Joystick
  private final edu.wpi.first.wpilibj.XboxController controller;

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
    controller = new edu.wpi.first.wpilibj.XboxController(port);

    // Buttons
    aButton = new Button(controller::getAButton);
    bButton = new Button(controller::getBButton);
    xButton = new Button(controller::getXButton);
    yButton = new Button(controller::getYButton);
    leftBumper = new Button(controller::getLeftBumper);
    rightBumper = new Button(controller::getRightBumper);
    backArrow = new Button(controller::getBackButton);
    startArrow = new Button(controller::getStartButton);
    leftStickButton = new Button(controller::getLeftStickButton);
    rightStickButton = new Button(controller::getRightStickButton);
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
    return controller.getLeftX();
  }

  /** @return YAxisLeft */
  public double getYAxisLeft() {
    return controller.getLeftY();
  }

  /** @return XAxisRight */
  public double getXAxisRight() {
    return controller.getRightX();
  }

  /** @return YAxisRight */
  public double getYAxisRight() {
    return controller.getRightY();
  }

  /** @return Left Trigger */
  public double getLeftTrigger() {
    return controller.getLeftTriggerAxis();
  }

  /** @return Right Trigger */
  public double getRightTrigger() {
    return controller.getRightTriggerAxis();
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

  /** Sets the right and left rumble
   * @param amount The amount to rumble between 0 (inclusive) and 1 (inclusive)
   */
  public void setRumble(double amount) {
    setLeftRumble(amount);
    setRightRumble(amount);
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

  /** @deprecated
   * @return True if the DPad is being pressed on the top. */
  public boolean getDPadTop() {
    return getDPad() == 0;
  }

  /** @deprecated
   * @return True if the DPad is being pressed on the top right. */
  public boolean getDPadTopRight() {
    return getDPad() == 45;
  }

  /** @deprecated
   * @return True if the DPad is being pressed on the right. */
  public boolean getDPadRight() {
    return getDPad() == 90;
  }

  /** @deprecated
   * @return True if the DPad is being pressed on the bottom right. */
  public boolean getDPadBottomRight() {
    return getDPad() == 135;
  }

  /** @deprecated
   * @return True if the DPad is being pressed on the bottom. */
  public boolean getDPadBottom() {
    return getDPad() == 180;
  }

  /** @deprecated
   * @return True if the DPad is being pressed on the bottom left. */
  public boolean getDPadBottomLeft() {
    return getDPad() == 225;
  }

  /** @deprecated
   * @return True if the DPad is being pressed on the left. */
  public boolean getDPadLeft() {
    return getDPad() == 270;
  }

  /** @deprecated
   * @return True if the DPad is being pressed on the top left. */
  public boolean getDPadTopLeft() {
    return getDPad() == 315;
  }

  /** @deprecated
   * @return True if the DPad is not being pressed. */
  public boolean getDPadReleased() {
    return getDPad() == -1;
  }

  /** @deprecated
   * @return The angle of the DPad in degrees.
   * The angle is represented in 8 degrees (right being 90, left being 270).
   * If none are being pressed, -1 is returned.
   */
  public double getDPad() {
    return controller.getPOV();
  }

}