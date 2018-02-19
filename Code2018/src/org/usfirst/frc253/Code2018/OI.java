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
import edu.wpi.first.wpilibj.XboxController;


import org.usfirst.frc253.Code2018.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc253.Code2018.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	
	//making objects for joystick buttons and xBox controller :) 
	public Joystick buttonBoard; 
	public XboxController xboxController;
    public JoystickButton ButtonA;
    public JoystickButton ButtonB; 
    public JoystickButton ButtonX;
    public JoystickButton ButtonY;
    public JoystickButton ButtonRB;
    public JoystickButton ButtonLB;
    public JoystickButton ButtonRT;
    public JoystickButton ButtonLT; 
    public JoystickButton ButtonStart;
    public JoystickButton ButtonSelect; 
    public JoystickButton ButtonL6;
    public JoystickButton ButtonL7;
    public JoystickButton ButtonBoard1;
    public JoystickButton ButtonBoard8;
    public JoystickButton ButtonBoard4;
    public JoystickButton ButtonBoard5;
    public JoystickButton ButtonBoard9;
    public JoystickButton ButtonBoard11;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    	xboxController = new XboxController(0);
    	buttonBoard = new Joystick(1);
    	//assigning where the buttons are 
        ButtonA = new JoystickButton(xboxController, 1); 
        ButtonB = new JoystickButton(xboxController, 2);
        ButtonX = new JoystickButton(xboxController, 3); 
        ButtonY = new JoystickButton(xboxController, 4);
        ButtonRB = new JoystickButton(xboxController, 5);
        ButtonLB = new JoystickButton(xboxController, 6);
        ButtonRT = new JoystickButton(xboxController, 7);
        ButtonLT = new JoystickButton(xboxController, 8);
        ButtonStart = new JoystickButton(xboxController, 9);
        ButtonSelect = new JoystickButton(xboxController, 10);
        ButtonBoard1 = new JoystickButton(buttonBoard, 1);
        ButtonBoard8 = new JoystickButton(buttonBoard, 8);
        ButtonBoard4 = new JoystickButton(buttonBoard, 4);
        ButtonBoard5 = new JoystickButton(buttonBoard, 5);
        ButtonBoard9 = new JoystickButton(buttonBoard, 9);
        ButtonBoard11 = new JoystickButton(buttonBoard, 11);
        // SmartDashboard Buttons

      //giving each xBox button a command
        ButtonX.whileHeld(new TurnLeft());  
        ButtonA.whileHeld(new DriveBackwards());
        ButtonB.whileHeld(new turnRight());
        ButtonBoard8.whileHeld(new climbUp());
        ButtonBoard1.whileHeld(new climbDown());
        ButtonRT.whileHeld(new elevatorUp()); 
        ButtonLT.whileHeld(new elevatorDown());
        //button board start
        ButtonBoard11.whileHeld(new swingIntake());
        ButtonBoard9.whileHeld(new swingEject());
      //  ButtonBoard1.whileHeld(new spinIntake());
      //  ButtonBoard8.whileHeld(new spinEject());
        ButtonBoard5.whileHeld(new insideIntake());
        ButtonBoard4.whileHeld(new insideEject());
        //button board end
        ButtonY.whileHeld(new StraightDrive());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
    
  //making method to get the button board later 
    public Joystick getButtonBoard(){  
    	return buttonBoard;
    }
    public double getLeftJoystickY(){
    	return xboxController.getY(GenericHID.Hand.kLeft)*0.90;
    }
    public double getRightJoystickX(){
    	return xboxController.getX(GenericHID.Hand.kRight)*0.90;
    }
    public double getRightJoystickY(){
    	return xboxController.getY(GenericHID.Hand.kRight)*0.90;
    }
       

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

