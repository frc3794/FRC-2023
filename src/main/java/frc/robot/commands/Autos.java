package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveTrain;

public final class Autos {

  //private final DriveTrainOmni m_drive = new DriveTrainOmni();

  private int level = 1;
  private DriveTrain m_drivetrain;

  public Autos() {}

  public Command autoCommand (DriveTrain drive, int level) {
    this.m_drivetrain = drive;

    switch (level) {
      case 1: level1 ();
      case 2: level2 ();
      default: level1 ();
    }

    return new WaitCommand(0.0000);
  }

  private void level1 () {
    this.m_drivetrain.moveToDistance(150, 0.5, 12);
  }

  private void level2 () {
    this.m_drivetrain.moveToDistance(569, 0.5, 5);

  }
}
