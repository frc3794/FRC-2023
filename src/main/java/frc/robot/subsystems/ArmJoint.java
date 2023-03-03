// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants.ArmJointConstants;
import frc.robot.commands.ExtendArmJoint;

public class ArmJoint extends SubsystemBase {
  public static final TalonSRX m_armJointMotor = new TalonSRX(9);
  private static final CommandXboxController m_subsystemController = Robot.getSubsystemController();
  private static double speed = m_subsystemController.getRightY();

  private final Timer m_timer = new Timer ();

  private static double xs = 0.25;

  public ArmJoint() {
    this.setDefaultCommand(new ExtendArmJoint (this));
  }

  //Arm Joint Movement
  public void extendArmJoint(){
    m_armJointMotor.set(ControlMode.PercentOutput , speed * xs);
  }

  public void testMotor () {
    m_timer.reset();
    m_timer.start();

    while (m_timer.get () < 1) {
      m_armJointMotor.set(ControlMode.PercentOutput, 1);
    }

    m_armJointMotor.set(ControlMode.PercentOutput, 0);
  }

  public void flexArmJoint(){
    m_armJointMotor.set(ControlMode.PercentOutput, speed * -xs);
  }
 
  @Override
  public void periodic() {}

  public static void stopMotors(){
    m_armJointMotor.set(ControlMode.Disabled, 0);
  }
}
