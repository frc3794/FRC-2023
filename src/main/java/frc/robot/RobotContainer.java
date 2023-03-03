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

  private final Autos m_auto = new Autos ();
  private final DriveTrain m_drivetrain = new DriveTrain();
  private final Elevator m_Elevator = new Elevator();
  /*private final MovileStage m_movStage = new MovileStage();
  private final Claw m_claw = new Claw ();
  private final ArmJoint m_armJoint = new ArmJoint();*/

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

    return m_auto.autoCommand(m_drivetrain, 0);
  }
}
