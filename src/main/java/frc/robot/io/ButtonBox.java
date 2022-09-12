package frc.robot.io;
// TODO: Fix for new button box!!!!
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class ButtonBox {
  private final Joystick m_joystick;

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
    m_joystick = new Joystick(port);

    topWhite = new JoystickButton(m_joystick, 2);
    bigWhite = new JoystickButton(m_joystick, 3);
    middleRed = new JoystickButton(m_joystick, 4);
    bottomWhite = new JoystickButton(m_joystick, 5);
    topRed = new JoystickButton(m_joystick, 6);
    green = new JoystickButton(m_joystick, 7);
    middleWhite = new JoystickButton(m_joystick, 8);
    bigRed = new JoystickButton(m_joystick, 14);
    yellow = new JoystickButton(m_joystick, 15);
    bottomRed = new JoystickButton(m_joystick, 16);
  }

}
