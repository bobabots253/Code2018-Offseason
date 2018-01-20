package org.usfirst.frc253.Code2018.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class cycleSwitchToPortal extends CommandGroup {

    public cycleSwitchToPortal() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	for(int i = 0; i <= 4; i++){
    		//move to switch area
    		addSequential(new SwitchScore());
    		//move back to portal
    		addSequential(new grab());
    		//repeat
    	}
    }
}
