package frc.robot.io;

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
      topRed = new JoystickButton(m_joystick, 6);
      middleWhite = new JoystickButton(m_joystick, 8);
      middleRed = new JoystickButton(m_joystick, 4);
      bottomWhite = new JoystickButton(m_joystick, 5);
      bottomRed = new JoystickButton(m_joystick, 16);
      bigWhite = new JoystickButton(m_joystick, 3);
      bigRed = new JoystickButton(m_joystick, 14);
      green = new JoystickButton(m_joystick, 7);
      yellow = new JoystickButton(m_joystick, 15);
    }
}
