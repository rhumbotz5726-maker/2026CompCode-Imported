package frc.robot.Sequences;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;

public abstract class Sequences {
    
    Subsystem subsystem;

    public Command sequence;
    public Command endSeq;



    public Command getSeq(){
        return sequence;
    }

     public Command getEndSeq(){
        return endSeq;
    }




}
