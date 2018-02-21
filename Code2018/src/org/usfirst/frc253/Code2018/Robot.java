// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc253.Code2018;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import org.usfirst.frc253.Code2018.commands.*;
import org.usfirst.frc253.Code2018.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
//Robot gets general structure from IterativeRobot 
public class Robot extends IterativeRobot {

	//declarations; you're making space for objects (having computer clear memory for objects)
    Command autonomousCommand;
    Command resetElevator;
    SendableChooser<Position> positionChooser;
    SendableChooser<Enemy> enemyChooser;
    SendableChooser<Ally> allyChooser;

    public static NetworkTableInstance table;
    //more declarations; public static=you don't need a robot object to use them, anything can use 
    //them; space in computer is reserved for this  
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static Sensors sensors;
    public static Pneumatics pneumatics;
    public static Elevator elevator;
    public static Intake intake;
    
    //another declaration 
    public String gameData;
    
    //enum = mini object; object that represents data type 
    public enum Position{
    	LEFT('L'), CENTER('C'), RIGHT('R');
    	
    	//field = variable or object covers entire class; declaration; private = only accessible 
    	//within class; final = specifies values that can't be changed 
    	private final char pos;
    	
    	//constructor for position
    	private Position(char pos){
    		this.pos = pos;
    	}
    	
    	//method that returns row bow's character of position to be compared to other things  
    	public char getPos(){
    		return pos;
    	}
    }
    
    //declaring the enemy's potential actions 
    public enum Enemy{
    	SCALE, DEFEND
    }
    
    //declaring ally's potential actions 
    public enum Ally{
    	SWITCH, SCALEORDEFEND
    }
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    //declares camaraserver object; camaraserver = object used for camera  
    CameraServer camera;
    //what happens when robot starts 
    public void robotInit() {
    //initiates RobotMap 
    RobotMap.init(); 
    //camera.getInstance = method that checks if camera exists; startAutomaticCapture = starts 
    //camera 
    table = NetworkTableInstance.getDefault();
    
    camera.getInstance().startAutomaticCapture();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    	//refer line 41, now we're putting these new objects into the space that was reserved 
        driveTrain = new DriveTrain();
        sensors = new Sensors();
        pneumatics = new Pneumatics();
        elevator = new Elevator();
        intake = new Intake();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        //The choosers got data from the smart dashboard 
        positionChooser = new SendableChooser();
        positionChooser.addDefault("Center", Position.CENTER);
        positionChooser.addObject("Left", Position.LEFT);
        positionChooser.addObject("Right", Position.RIGHT);
        enemyChooser = new SendableChooser();
        enemyChooser.addDefault("Enemy can do scale", Enemy.SCALE);
        enemyChooser.addObject("Enemy can defending", Enemy.DEFEND);
        allyChooser = new SendableChooser();
        allyChooser.addDefault("Ally going to the switch", Ally.SWITCH);
        allyChooser.addObject("Ally is going to null zone", Ally.SCALEORDEFEND);
        
        
        
        
        
    	
    	
        
        
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
//    	resetElevator = new ResetElevator();
//    	if (resetElevator != null) resetElevator.start();
    }
    
    public class ResetElevator extends CommandGroup{
    	private class ElevatorLower extends Command{
    		private ElevatorLower() {
    			requires(Robot.elevator);
    			setTimeout(10);
    		}
    	
    		protected void execute(){
    			Robot.elevator.move(0.15);
    		}
    	
    		@Override
    		protected boolean isFinished() {
    			// TODO Auto-generated method stub
    			return isTimedOut();
    		}
    		
    		protected void interrupted(){
    			Robot.elevator.move(0);
    		}
    	}
    	
    	private ResetElevator(){
    		requires(Robot.elevator);

        	addSequential(new ElevatorLower());
    	}    
    }

    public void disabledPeriodic() {
    	Scheduler.getInstance().run();
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        
    }

    public void autonomousInit() {
    	if(resetElevator != null) resetElevator.cancel();
    	//receives data about colors 
    	gameData = DriverStation.getInstance().getGameSpecificMessage();

    	//gets data from choosers 
    	Position position = (Position) positionChooser.getSelected();
    	char switchSide = gameData.charAt(0);
    	char scaleSide = gameData.charAt(1);
    	Enemy canDo = (Enemy) enemyChooser.getSelected();
    	Ally isDoing = (Ally) allyChooser.getSelected();
    	
    	//starts auto by using info compiled from line 157 
    	autonomousCommand = new AutonomousCommand(position, switchSide, scaleSide, canDo, isDoing);
        // schedule the autonomous command (example)
        if (autonomousCommand != null){
        	autonomousCommand.start();
        }
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	if (resetElevator != null) resetElevator.cancel();
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
}
