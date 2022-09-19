package frc.robot.io;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.utils.Constants;

public class Extreme {
  // Joystick
  private final Joystick joystick;

  // Buttons
  public final Button trigger;
  public final Button sideButton;
  public final Button joystickBottomLeft;
  public final Button joystickBottomRight;
  public final Button joystickTopLeft;
  public final Button joystickTopRight;
  public final Button baseFrontLeft;
  public final Button baseFrontRight;
  public final Button baseMiddleLeft;
  public final Button baseMiddleRight;
  public final Button baseBackLeft;
  public final Button baseBackRight;
  public final Button joystickUp;
  public final Button joystickUpRight;
  public final Button joystickRight;
  public final Button joystickDownRight;
  public final Button joystickDown;
  public final Button joystickDownLeft; 
  public final Button joystickLeft;
  public final Button joystickUpLeft;

  public Extreme(int port) {
    joystick = new Joystick(port);

    trigger = new JoystickButton(joystick, Constants.EXTREME_TRIGGER_PORT);
    sideButton = new JoystickButton(joystick, Constants.EXTREME_SIDE_BUTTON_PORT);
    joystickBottomLeft = new JoystickButton(joystick, Constants.EXTREME_JOYSTICK_BOTTOM_LEFT_PORT);
    joystickBottomRight = new JoystickButton(joystick, Constants.EXTREME_JOYSTICK_BOTTOM_RIGHT_PORT);
    joystickTopLeft = new JoystickButton(joystick, Constants.EXTREME_JOYSTICK_TOP_LEFT_PORT);
    joystickTopRight = new JoystickButton(joystick, Constants.EXTREME_JOYSTICK_TOP_RIGHT_PORT);
    baseFrontLeft = new JoystickButton(joystick, Constants.EXTREME_BASE_FRONT_LEFT_PORT);
    baseFrontRight = new JoystickButton(joystick, Constants.EXTREME_BASE_FRONT_RIGHT_PORT);
    baseMiddleLeft = new JoystickButton(joystick, Constants.EXTREME_BASE_MIDDLE_LEFT_PORT);
    baseMiddleRight = new JoystickButton(joystick, Constants.EXTREME_BASE_MIDDLE_RIGHT_PORT);
    baseBackLeft = new JoystickButton(joystick, Constants.EXTREME_BASE_BACK_LEFT_PORT);
    baseBackRight = new JoystickButton(joystick, Constants.EXTREME_BASE_BACK_RIGHT_PORT);
    joystickUp = new POVButton(joystick, Constants.EXTREME_POV_UP_DEGREES);
    joystickUpRight = new POVButton(joystick, Constants.EXTREME_POV_UP_RIGHT_DEGREES);
    joystickRight = new POVButton(joystick, Constants.EXTREME_POV_RIGHT_DEGREES);
    joystickDownRight = new POVButton(joystick, Constants.EXTREME_POV_DOWN_RIGHT_DEGREES);
    joystickDown = new POVButton(joystick, Constants.EXTREME_POV_DOWN_DEGREES);
    joystickDownLeft = new POVButton(joystick, Constants.EXTREME_POV_DOWN_LEFT_DEGREES);
    joystickLeft = new POVButton(joystick, Constants.EXTREME_POV_LEFT_DEGREES);
    joystickUpLeft = new POVButton(joystick, Constants.EXTREME_POV_UP_LEFT_DEGREES);
  }

  /** @return StickX */
  public double getStickX() {
    return joystick.getRawAxis(Constants.EXTREME_STICK_X_AXIS_ID);
  }

  /** @return StickY */
  public double getStickY() {
    return joystick.getRawAxis(Constants.EXTREME_STICK_Y_AXIS_ID);
  }

  /** @return StickZ */
  public double getStickZ() {
    return joystick.getRawAxis(Constants.EXTREME_STICK_Z_AXIS_ID);
  }

  /** @return Slider */
  public double getSlider() {
    return joystick.getRawAxis(Constants.EXTREME_SLIDER_AXIS_ID);
  }

}  