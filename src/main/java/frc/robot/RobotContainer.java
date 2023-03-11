package frc.robot;

import frc.robot.commands.Autos;
import frc.robot.subsystems.ArmJoint;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.MovileStage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;


public class RobotContainer {

  private final DriveTrain m_drivetrain = new DriveTrain();
  private final Autos m_auto = new Autos (m_drivetrain);
  //private final Elevator m_Elevator = new Elevator();
  //private final MovileStage m_movStage = new MovileStage();
  //private final Claw m_claw = new Claw ();
  //private final ArmJoint m_armJoint = new ArmJoint();

  public RobotContainer() {

  }

  public Command getAutonomousCommand() {
    int level = 0;

    boolean buttonValue = SmartDashboard.getBoolean("DB/Button 0", false);
    boolean buttonValue1 = SmartDashboard.getBoolean("DB/Button 1", false);
    boolean buttonValue2 = SmartDashboard.getBoolean("DB/Button 2", false);
    boolean buttonValue3 = SmartDashboard.getBoolean("DB/Button 3", false);

    if (buttonValue && !buttonValue1 && !buttonValue2 && !buttonValue3) {
      SmartDashboard.putString("DB/String 3", "Level 0");
      level = 0;
    } else if (!buttonValue && buttonValue1 && !buttonValue2 && !buttonValue3) {
      SmartDashboard.putString("DB/String 3", "Level 1");
      level = 1;
    } else if (!buttonValue && !buttonValue1 && buttonValue2 && !buttonValue3) {
      SmartDashboard.putString("DB/String 3", "Level 2");
      level = 2;
    } else if (!buttonValue && !buttonValue1 && !buttonValue2 && buttonValue3) {
      SmartDashboard.putString("DB/String 3", "Level 3");
      level = 3;
    } else {
      SmartDashboard.putString("DB/String 3", "Not value on button");
      level = 0;
    }

    return m_auto.autoCommand(level);
  }
}
