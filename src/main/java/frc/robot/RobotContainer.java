package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.DriveTrainOmni;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  private final DriveTrainOmni m_drive = new DriveTrainOmni();

  public RobotContainer() {

  }

  public Command getAutonomousCommand() {
    return null;
  }

  public DriveTrainOmni gDriveTrainOmni () {
    return m_drive;
  }
}
