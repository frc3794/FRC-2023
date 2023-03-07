package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.MStageConstants;
import frc.robot.commands.MoveMovileStage;

public class MovileStage extends SubsystemBase {
  private static final TalonSRX m_movileStageMotor = new TalonSRX(9);
  private final static DigitalInput m_forwardLimit = new DigitalInput(1);
  private final static DigitalInput m_reverseLimit = new DigitalInput(0);

  private final Timer m_timer = new Timer ();

  public static boolean isPressed;

  public MovileStage() {
    this.setDefaultCommand(new MoveMovileStage(this));
  }

//Movile Stage Movement
  public static Command extendMovileStage(){
    if (m_forwardLimit.get() || m_reverseLimit.get()){
      isPressed = true;
      SmartDashboard.putString("DB/String 0", "Pressed");
    } else {
      isPressed = false;
      SmartDashboard.putString("DB/String 0", "Not Pressed");
    }
    
    /*if (isPressed){
      SmartDashboard.putString("DB/String 0", "Pressed");
      m_movileStageMotor.set(ControlMode.PercentOutput, 0);
    } else {
      m_movileStageMotor.set(ControlMode.PercentOutput, MStageConstants.kMovileStageSpeed);
      SmartDashboard.putString("DB/String 0", "Not Pressed");
    }*/

    return null;
  }

  public static Command retractMovileStage(){
    if (m_forwardLimit.get() || m_reverseLimit.get()){
      isPressed = true;
    } else {
      isPressed = false;
    }
    
    if (isPressed){
      m_movileStageMotor.set(ControlMode.PercentOutput, 0);
    } else {
      m_movileStageMotor.set(ControlMode.PercentOutput, -MStageConstants.kMovileStageSpeed);
    }

    return null;
  }

  public void testMotor () {
    m_timer.reset();
    m_timer.start();

    while (m_timer.get () < 1) {
      m_movileStageMotor.set(ControlMode.PercentOutput, 0.2);
    }

    m_movileStageMotor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    
  }
//Sus ඞ
  public static Command stopMotors(){
    m_movileStageMotor.set(ControlMode.Disabled, 0);
    return null;
  }
}