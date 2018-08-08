package frc.team253.robot;


import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team253.robot.pathing.pathFollow;
import frc.team253.robot.subsystems.Drivetrain.DrivetrainSubsystem;
import frc.team253.robot.subsystems.Elevator;
import frc.team253.robot.subsystems.Limelight;
import frc.team253.robot.subsystems.Pneumatics;
import jaci.pathfinder.Waypoint;

import static frc.team253.robot.subsystems.Drivetrain.DrivetrainSubsystem.setBrakeMode;

public class Robot extends TimedRobot {

    public static OI oi;
    public static DrivetrainSubsystem drivetrain;
    public static Pneumatics pneumatics;
    public static Limelight limelight;
    public static Elevator elevator;

    @Override
    public void robotInit() {
        RobotMap.init();
        drivetrain = DrivetrainSubsystem.getInstance();
        elevator = Elevator.getInstance();
        pneumatics = Pneumatics.getInstance();
        limelight = Limelight.getInstance();
        oi = new OI();

        drivetrain.resetEncoders();
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void autonomousInit() {
        Waypoint[] runPoints = new Waypoint[]{ //TEMPORARY
                new Waypoint(0, 0, 0),
                new Waypoint(3, 0, 0)
        };
        new pathFollow(runPoints).start();
    }

    @Override
    public void teleopInit() {
        setBrakeMode();
    }

    @Override
    public void testInit() {
    }


    @Override
    public void disabledPeriodic() {
        NetworkTableInstance.getDefault().getTable("Limelight").getEntry("ledMode").setNumber(1);

        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopPeriodic() {
        //NetworkTableInstance.getDefault().getTable("Limelight").getEntry("ledMode").setNumber(1);
        Scheduler.getInstance().run();
    }

    @Override
    public void testPeriodic() {
    }
}