package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.ArmJoint;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;

public final class Autos {

  private final DriveTrain m_drivetrain;
  private final ArmJoint m_armJoint;
  private final Claw m_claw;
  private final Indexer m_indexer;

  private final AutoConstants kConstants = new Constants.AutoConstants();

  public Autos (DriveTrain drive, ArmJoint arm, Claw claw, Indexer indexer) {
    this.m_drivetrain = drive;
    this.m_armJoint = arm;
    this.m_claw = claw;
    this.m_indexer = indexer;
  }

  public Command autoCommand (int level) {
    this.m_drivetrain.resetNavX();

    switch (level) {
      case 0: level0 (); break;
      case 1: level1 (); break;
      case 2: level2 (); break;
      case 3: level3 (); break;
      case 4: level4 (); break;
      default: level1 (); break;
    }

    return new WaitCommand (0.0000);
  }

  private void level0 () { // Out of Zone
    //this.m_drivetrain.moveToDistance (kConstants.kMOutFP, 0.5, 14.5, 0);
    this.m_drivetrain.moveToDistance(2.25, 0.5, 14, 0);
  }

  private void level1 () { // Charging station
    this.m_drivetrain.moveToDistance (kConstants.kMChargeFP, 0.5, 14.5, 0);
    //this.m_armJoint.watchMe (5);
  }

  private void level2 () { // Cubito y Charging Station
    this.m_claw.closeClaw ();
    this.m_armJoint.extendArm (1);
    this.m_drivetrain.moveToDistance (kConstants.kMLoadFP, 0.5, 5, 1);
    this.m_claw.openClaw ();
    this.m_drivetrain.moveToDistance (-kConstants.kMLoadFP, 0.4, 4, 0.5);
    this.m_armJoint.extendArm (0);
    this.m_drivetrain.moveToDistance (-kConstants.kMChargeFP, 0.4, 4, 0);
  }

  private void level3 () { // Cubito  del piso y Charging
    this.m_drivetrain.moveToDistance (kConstants.kMLoadFP, 0.5, 5, 0);
    this.m_indexer.ejectAuto (0.5, 1);
    this.m_drivetrain.moveToDistance (-kConstants.kMChargeSP, 0.5, 7, 0.6);
  }

  private void level4 () { // Master
    this.m_claw.closeClaw ();
    this.m_armJoint.extendArm (1);
    this.m_drivetrain.moveToDistance (kConstants.kMLoadFP, 0.5, 5, 1);
    this.m_claw.openClaw ();
    this.m_drivetrain.moveToDistance (-kConstants.kMLoadFP, 0.4, 4, 0.5);
    this.m_armJoint.extendArm (0);
    this.m_drivetrain.moveToDistance (-kConstants.kMOutFP, 0.4, 4, 0.1);
    this.m_drivetrain.moveToDistance (kConstants.kMChargeTP, 0.4, 4, 0.1);
    //this.m_armJoint.watchMe (5);
  }
}
