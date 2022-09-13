package frc.robot.io;
// TODO: Fix for new button box!!!!
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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

    topWhite = new JoystickButton(joystick, 2);
    bigWhite = new JoystickButton(joystick, 3);
    middleRed = new JoystickButton(joystick, 4);
    bottomWhite = new JoystickButton(joystick, 5);
    topRed = new JoystickButton(joystick, 6);
    green = new JoystickButton(joystick, 7);
    middleWhite = new JoystickButton(joystick, 8);
    bigRed = new JoystickButton(joystick, 14);
    yellow = new JoystickButton(joystick, 15);
    bottomRed = new JoystickButton(joystick, 16);
  }

}
