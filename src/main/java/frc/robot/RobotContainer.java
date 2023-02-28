package frc.robot;

import frc.robot.commands.Autos;
import frc.robot.subsystems.DriveTrainOmni;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {

  private final DriveTrainOmni m_drive = new DriveTrainOmni();
  private final Autos m_auto = new Autos ();

  public RobotContainer() {

  }

  public Command getAutonomousCommand() {
    int level = 0;

    boolean buttonValue = SmartDashboard.getBoolean("DB/Button 0", false);
    boolean buttonValue1 = SmartDashboard.getBoolean("DB/Button 1", false);

    if (buttonValue && !buttonValue1)
      level = 1;
    else if (!buttonValue && buttonValue1)
      level = 2;

    m_auto.setLevel(level);
    return m_auto.autoCommand();
  }

  public DriveTrainOmni gDriveTrainOmni () {
    return m_drive;
  }
}
