package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Claw;
import frc.robot.Robot;
import frc.robot.subsystems.ArmJoint;

public class TriggerClaw extends CommandBase {

    public static final CommandXboxController m_subsystemController = Robot.getSubsystemController();

    public TriggerClaw() {}

  @Override
  public void initialize() {
    ArmJoint.WristMotorFollow();  
  }

  @Override
  public void execute() {
    Trigger leftSubsystemTrigger = m_subsystemController.leftTrigger();
    Trigger rightSubsystemTrigger = m_subsystemController.rightTrigger();

    rightSubsystemTrigger.whileTrue(Claw.OpenClaw());
    leftSubsystemTrigger.whileTrue(Claw.CloseClaw());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Claw.StopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
    
}
