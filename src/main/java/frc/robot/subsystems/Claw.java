package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import com.revrobotics.CANSparkMax;

import frc.robot.Robot;
import frc.robot.Constants.DrivetrainConstants;
import com.revrobotics.CANSparkMaxLowLevel;

public class Claw extends SubsystemBase{
    private static final CANSparkMax m_clawMotor = new CANSparkMax(DrivetrainConstants.kMotorPorts[5],CANSparkMaxLowLevel.MotorType.kBrushless);
    private static final CommandXboxController m_subsystemController = Robot.getSubsystemController();
    private static double rightTrigger = m_subsystemController.getRightTriggerAxis();
    private static double leftTrigger = m_subsystemController.getLeftTriggerAxis();
    
public Claw()  {}
//Claw Movement 

public static Command OpenClaw(){
    m_clawMotor.set(rightTrigger * 0.3);
    return null;
}

public static Command CloseClaw(){
    m_clawMotor.set(leftTrigger * -0.3);
    return null;
}

@Override
public void periodic() {

}

public static Command StopMotors(){
    m_clawMotor.stopMotor();
    return null;
}
}








