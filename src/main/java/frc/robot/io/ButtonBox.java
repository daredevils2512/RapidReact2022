package frc.robot.io;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.utils.Constants;

public class ButtonBox {
  private final Joystick joystick;

  public final Button topWhite;
  public final Button topRed;
  public final Button middleWhite;
  public final Button middleRed;
  public final Button bottomWhite;
  public final Button bottomRed;
  public final Button green;
  public final Button yellow;
  public final Button bigWhite;
  public final Button bigRed;
  
  public ButtonBox(int port) {
    joystick = new Joystick(port);

    topWhite = new JoystickButton(joystick, Constants.BUTTON_BOX_TOP_WHITE_PORT);
    bigWhite = new JoystickButton(joystick, Constants.BUTTON_BOX_BIG_WHITE_PORT);
    middleRed = new JoystickButton(joystick, Constants.BUTTON_BOX_MIDDLE_RED_PORT);
    bottomWhite = new JoystickButton(joystick, Constants.BUTTON_BOX_BOTTOM_WHITE_PORT);
    topRed = new JoystickButton(joystick, Constants.BUTTON_BOX_TOP_RED_PORT);
    green = new JoystickButton(joystick, Constants.BUTTON_BOX_GREEN_PORT);
    middleWhite = new JoystickButton(joystick, Constants.BUTTON_BOX_MIDDLE_WHITE_PORT);
    bigRed = new JoystickButton(joystick, Constants.BUTTON_BOX_BIG_RED_PORT);
    yellow = new JoystickButton(joystick, Constants.BUTTON_BOX_YELLOW_PORT);
    bottomRed = new JoystickButton(joystick, Constants.BUTTON_BOX_BOTTOM_RED_PORT);
  }

}
