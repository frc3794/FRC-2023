package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax; 
import frc.robot.Constants.DrivetrainConstants;
import com.revrobotics.CANSparkMaxLowLevel;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

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

public static boolean isPressed;

private static final VictorSPX O_CLAW_VICTOR_SPX = new VictorSPX(0)

public static Command ExtendMovileStage(){
    m_movileStageMotor.set(ControlMode.PercentOutput, .75);
    return null;
  }
  public static Command RetractMovileStage(){
    m_movileStageMotor.set(ControlMode.PercentOutput, -.75);
    return null;
  }
  public static void GetLimits(){

    if (m_forwardLimit.get() || m_reverseLimit.get()){
      isPressed = true;

    } else {
      isPressed = false;
    }
    
    if (isPressed){
      m_movileStageMotor.set(ControlMode.PercentOutput, 0);
    } 
  }
}








