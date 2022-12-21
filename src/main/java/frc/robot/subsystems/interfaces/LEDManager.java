package frc.robot.subsystems.interfaces;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Subsystem;

public interface LEDManager extends Subsystem {
  /** @return The color value of the LEDs */
  Color getColor();

  /** @param i The index of the light to get the color
   * @return The color value of the LEDs at the index */
  Color getColor(int i);

  /** Sets the color values of the LEDs
   * @param saturation The saturation for the 
   */
  void setColor(double saturation, int hue, int value);

  /** Toggles the LEDs */
  void toggleLEDs();

  /** Enables / Disables the LEDs
   * @param wantsEnabled whether to enable the LEDs
   */
  void setEnabled(boolean wantsEnabled);

  /** Sets the new hue value for HSV
   * @param hue The new hue value
   */
  void setHue(int hue);

  /** Sets the new value for HSV
   * @param value The new value
   */
  void setValue(int value);

  /** Sets the new saturation for HSV
   * @param saturation The new saturation
   */
  void setSaturation(int saturation);
}
