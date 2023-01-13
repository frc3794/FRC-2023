// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.DriveTrainOmni;

public class MoveDrivetrain extends CommandBase {
  
  private final DriveTrainOmni m_drivetrain;

  private final CommandXboxController m_driverController =
    new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public MoveDrivetrain(DriveTrainOmni drivetrain) {
    this.m_drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double fwd = 0, rot = 0;

    double rightTrigger = Math.abs(m_driverController.getRightTriggerAxis());
    double leftTrigger = Math.abs(m_driverController.getLeftTriggerAxis());

    fwd = rightTrigger > 0.2 ? rightTrigger : -leftTrigger;

    double rightJoystick = m_driverController.getRightX();
    double leftJoystick = m_driverController.getLeftX();

    rot = Math.abs (rightJoystick) > 0.2 ? (rightJoystick * 0.8) : (leftJoystick);

    fwd *= DrivetrainConstants.kSensibilityPercent;
    rot *= DrivetrainConstants.kSensibilityPercent * -0.8;

    m_drivetrain.arcadeDrive (fwd, rot);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
