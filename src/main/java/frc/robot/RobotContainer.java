package frc.robot;

import frc.robot.subsystems.DriveTrainOmni;
import edu.wpi.first.wpilibj2.command.Command;

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
