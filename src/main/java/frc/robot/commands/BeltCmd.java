package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.BeltSubsystem;

public class BeltCmd extends Command{
    private BeltSubsystem beltSub;
    private double speed;

    public BeltCmd(BeltSubsystem beltSub, double speed){
        this.beltSub = beltSub;
        this.speed = speed;
    }

    @Override
    public void execute() {
        beltSub.setSpeed(speed);
    }

}
