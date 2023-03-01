package frc.robot;

import frc.robot.commands.Autos;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {

  private final Autos m_auto = new Autos ();

  public RobotContainer() {

  }

  public Command getAutonomousCommand() {
    int level = 0;

    //boolean buttonValue = SmartDashboard.getBoolean("DB/Button 0", false);
    //boolean buttonValue1 = SmartDashboard.getBoolean("DB/Button 1", false);

    boolean buttonValue = true;
    boolean buttonValue1 = false;

    if (buttonValue && !buttonValue1)
      level = 1;
    else if (!buttonValue && buttonValue1)
      level = 2;

    return m_auto.autoCommand(level);
  }
}
