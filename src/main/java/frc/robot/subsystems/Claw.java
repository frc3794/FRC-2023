package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.Robot;
import frc.robot.Constants.DrivetrainConstants;

public class Claw extends SubsystemBase{
    private static final VictorSPX m_clawMotor = new VictorSPX(DrivetrainConstants.kMotorPorts[5]);
    private static final CommandXboxController m_subsystemController = Robot.getSubsystemController();
    private static double rightTrigger = m_subsystemController.getRightTriggerAxis();
    private static double leftTrigger = m_subsystemController.getLeftTriggerAxis();
    
public Claw()  {}
//Claw Movement 

public static Command OpenClaw(){
    m_clawMotor.set(ControlMode.PercentOutput, rightTrigger * 0.3);
    return null;
}

public static Command CloseClaw(){
    m_clawMotor.set(ControlMode.PercentOutput, leftTrigger * -0.3);
    return null;
}

@Override
public void periodic() {

}

public static Command StopMotors(){
    m_clawMotor.set(ControlMode.Disabled, 0);
    return null;
}
}








