package frc.robot.io;

import frc.robot.utils.Constants;

public class ControlBoard {
    public static final XboxController xboxController = new XboxController(Constants.XBOX_CONTROLLER_PORT);
    public static final Extreme extreme = new Extreme(Constants.EXTREME_PORT);
    public static final ButtonBox buttonBox = new ButtonBox(Constants.BUTTON_BOX_PORT);
}
