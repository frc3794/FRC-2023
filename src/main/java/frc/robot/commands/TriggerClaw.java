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

    private Claw m_claw;

    public TriggerClaw(Claw claw) {
      this.m_claw = claw;
    }

  @Override
  public void initialize() {}

  @Override
  public void execute(){
    rightSubsystemTrigger.whileTrue(this.m_claw.openClaw());
    leftSubsystemTrigger.whileTrue(this.m_claw.closeClaw());

  }

  @Override
  public void end(boolean interrupted) {
    this.m_claw.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
    
}
