package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ArmJoint;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveTrain;

public final class Autos {

  private DriveTrain m_drivetrain;
  private ArmJoint m_armJoint;
  private Claw m_claw;

  public Autos(DriveTrain drive, ArmJoint arm, Claw claw) {
    this.m_drivetrain = drive;
    this.m_armJoint = arm;
    this.m_claw = claw;
  }

  public Command autoCommand (int level) {
    this.m_drivetrain.resetNavX();

    switch (level) {
      case 0: level0(); break;
      case 1: level1 (); break;
      case 2: level2 (); break;
      case 3: level3 (); break;
      default: level0 (); break;
    }

    return new WaitCommand(0.0000);
  }

  private void level0 () { // Out of Zone
    this.m_drivetrain.pastMove (218, 0.5, 7.25);
    this.m_drivetrain.pastMove (224, 0.35, 7.25);
  }

  private void level1 () { // Charging station
    this.m_drivetrain.pastMove(218, 0.5, 14.5);
    //this.m_armJoint.watchMe(0.4, 5);
  }

  private void level2 () { // Cubito y Charging Station
    this.m_claw.closeClaw();
    this.m_armJoint.extendArm(1);
    this.m_drivetrain.moveToDis(1.65, 0.5, 4);
    this.m_claw.openClaw();
    this.m_drivetrain.moveToDis(-1, 0.5, 4);
    this.m_drivetrain.rotateAngle(180, 0.25);
    this.m_drivetrain.moveToDis(0.65, 5, 5);
    this.m_drivetrain.pastMove(218, 0.5, 8); 
  }

  private void level3 () { // Cubito  del piso y Charging
    this.m_claw.closeClaw();
    this.m_armJoint.extendArm (1);
    this.m_drivetrain.moveToDis2(0.55, 0.5, 5, 1);
    this.m_claw.openClaw();
    this.m_drivetrain.moveToDis2(-0.325, 0.4, 4, 0.5);
    this.m_armJoint.extendArm(0);
    this.m_drivetrain.moveToDis(-0.325, 0.4, 4);
    this.m_drivetrain.moveToDis(-1.2, 0.5, 4);
  }
}
