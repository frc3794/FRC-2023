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
    this.m_drivetrain.pastMove (208, 0.5, 14.5);
    //this.m_drivetrain.moveToDistance(491, 0.5, 14.7);
    /*this.m_drivetrain.moveToDistance(120, 0.5, 7);
    new WaitCommand(1);
    this.m_drivetrain.moveToDistance(-120, 0.5, 7);*/
  }

  private void level1 () { // Charging station
    this.m_drivetrain.moveToDistance(100, 0.5, 9.7);
    //this.m_armJoint.watchMe(0.4, 5);
  }

  private void level2 () { // Cubito y Charging Station
    this.m_drivetrain.moveToDistance(-100, 0.5, 5);
    this.m_drivetrain.moveToDistance(200, 0.5, 9.7);
  }

  private void level3 () { // Cubito  del piso y Charging
    this.m_claw.openClaw();
    this.m_drivetrain.moveToDistance(100, 0.75, 5);
    this.m_claw.closeClaw();
    this.m_drivetrain.rotateAngle(180, 0.5);
    this.m_drivetrain.moveToDistance(100, 0.5, 5);
    this.m_armJoint.extendArm(1);
    this.m_claw.openClaw();
    this.m_claw.closeClaw();
    this.m_armJoint.extendArm(0);

    this.m_drivetrain.moveToDistance(-200, 0.5, 5);
  }
}
