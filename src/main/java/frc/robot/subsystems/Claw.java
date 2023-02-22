package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax; 
import frc.robot.Constants.DrivetrainConstants;
import com.revrobotics.CANSparkMaxLowLevel;

public class Claw extends SubsystemBase{

    private static final CANSparkMax m_clawMotor = new CANSparkMax(DrivetrainConstants.kMotorPorts[5],CANSparkMaxLowLevel.MotorType.kBrushless);
    
public Claw()  {}
//Claw Movement 

public static Command OpenClaw(){
    m_clawMotor.set(0.5);
    return null;
}

public static Command CloseClaw(){
    m_clawMotor.set(-0.5);
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








