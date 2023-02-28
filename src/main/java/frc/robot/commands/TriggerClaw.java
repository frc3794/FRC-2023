package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Claw;
import frc.robot.Robot;

public class TriggerClaw extends CommandBase {

    private final CommandXboxController m_subsystemController = Robot.getSubsystemController();
    private final Trigger leftSubsystemTrigger = m_subsystemController.leftTrigger();
    private final Trigger rightSubsystemTrigger = m_subsystemController.rightTrigger();

    public TriggerClaw() {}

  @Override
  public void initialize() {}

  @Override
  public void execute(){
    rightSubsystemTrigger.whileTrue(Claw.openClaw());
    leftSubsystemTrigger.whileTrue(Claw.closeClaw());

  }

  @Override
  public void end(boolean interrupted) {
    Claw.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
    
}
