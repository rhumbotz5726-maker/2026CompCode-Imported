package frc.robot.Sequences;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;

public abstract class Sequences {
    
    Subsystem subsystem;

    public static Command sequence;
    public static Command endSeq;



    public static Command getSeq(){
        return sequence;
    }

     public static Command getEndSeq(){
        return endSeq;
    }




}
