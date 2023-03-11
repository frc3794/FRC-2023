package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveTrain;

public final class Autos {

  private DriveTrain m_drivetrain;

  public Autos(DriveTrain drive) {
    this.m_drivetrain = drive;
  }

  public Command autoCommand (int level) {
    this.m_drivetrain.resetNavX();

    switch (level) {
      case 0: level0(); break;
      case 1: level1 (); break;
      case 2: level2 (); break;
      case 3: level3 (); break;
      default: level1 (); break;
    }

    return new WaitCommand(0.0000);
  }

  private void level0 () { // Out of Zone
    this.m_drivetrain.moveToDistance(491, 0.5, 14.5);
  }

  private void level1 () { // Charging station
    this.m_drivetrain.moveToDistance(208, 0.5, 14.5);
  }

  private void level2 () { // Cubito y Charging Station
    this.m_drivetrain.moveToDistance(569, 0.5, 14.5);
  }

  private void level3 () { // Cubito y fuera de la zone

  }
}
