package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.TriggerClaw;

public class Claw extends SubsystemBase{

    private final Solenoid m_solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
    
    public Claw()  {
        this.setDefaultCommand(new TriggerClaw (this));
    }
    //Claw Movement 
    public void openClaw(){
        if (m_solenoid.get())
            m_solenoid.set (false);
    }

    public void closeClaw(){
        if (!m_solenoid.get ())
            m_solenoid.set(true);
    }

    @Override
    public void periodic() {

    }

}








