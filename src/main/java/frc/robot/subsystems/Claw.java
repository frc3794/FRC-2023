package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Robot;

public class Claw extends SubsystemBase{
    private static final TalonSRX m_clawMotor = new TalonSRX(8);
    private static final CommandXboxController m_subsystemController = Robot.getSubsystemController();
    private static double rightTrigger = m_subsystemController.getRightTriggerAxis();
    private static double leftTrigger = m_subsystemController.getLeftTriggerAxis();
    
    public Claw()  {}
    //Claw Movement 
    public static Command openClaw(){
        m_clawMotor.set(ControlMode.PercentOutput, rightTrigger * 0.3);
        return null;
    }

    public static Command closeClaw(){
        m_clawMotor.set(ControlMode.PercentOutput, leftTrigger * -0.3);
        return null;
    }

    @Override
    public void periodic() {

    }

    public static Command stopMotors(){
        m_clawMotor.set(ControlMode.Disabled, 0);
        return null;
    }
}








