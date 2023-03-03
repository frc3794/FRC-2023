package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Robot;
import frc.robot.commands.TriggerClaw;

public class Claw extends SubsystemBase{
    private static final TalonSRX m_clawMotor = new TalonSRX(10);
    private static final CommandXboxController m_subsystemController = Robot.getSubsystemController();
    private static double rightTrigger = m_subsystemController.getRightTriggerAxis();
    private static double leftTrigger = m_subsystemController.getLeftTriggerAxis();

    private final Timer m_timer = new Timer ();
    
    public Claw()  {
        this.setDefaultCommand(new TriggerClaw (this));
    }
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

    public void testMotor () {
        m_timer.reset();
        m_timer.start();

        while (m_timer.get () < 1) {
            m_clawMotor.set(ControlMode.PercentOutput, 1);
        }

        m_clawMotor.set(ControlMode.PercentOutput, 0);
    }

    public static Command stopMotors(){
        m_clawMotor.set(ControlMode.Disabled, 0);
        return null;
    }
}








