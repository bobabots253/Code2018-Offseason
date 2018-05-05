package frc.team253.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team253.robot.Constants;
import frc.team253.robot.RobotMap;
import frc.team253.robot.commands.drive;

public class driveTrain extends Subsystem {

    private final TalonSRX leftBack = RobotMap.driveTrainLeftBack,
            leftFront = RobotMap.driveTrainLeftFront,
            rightFront = RobotMap.driveTrainRightFront,
            rightBack = RobotMap.driveTrainRightBack;

    public driveTrain() {
        leftFront.follow(leftBack);
        rightBack.follow(rightFront);

        leftFront.configPeakCurrentLimit(0, 10);
        rightBack.configPeakCurrentLimit(0, 10);
        leftBack.configPeakCurrentLimit(0, 10);
        rightFront.configPeakCurrentLimit(0, 10);

        leftFront.configContinuousCurrentLimit(45, 10);
        rightBack.configContinuousCurrentLimit(45, 10);
        leftBack.configContinuousCurrentLimit(45, 10);
        rightFront.configContinuousCurrentLimit(45, 10);

        leftFront.setInverted(true);
        rightFront.setInverted(false);
        leftBack.setInverted(true);
        rightBack.setInverted(false);

        leftBack.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 1, 10);
        leftBack.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

        rightFront.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 1, 10);
        rightFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

        leftFront.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 1, 10);
        leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);

        leftBack.configVoltageCompSaturation(12, 10);
        rightFront.configVoltageCompSaturation(12, 10);

        leftBack.enableVoltageCompensation(true);
        rightFront.enableVoltageCompensation(true);

        leftBack.setSensorPhase(false);
        rightFront.setSensorPhase(false);
        leftFront.setSensorPhase(false);

        leftBack.configNeutralDeadband(Constants.kNeutralDeadband, Constants.kTimeoutMs);
        rightFront.configNeutralDeadband(Constants.kNeutralDeadband, Constants.kTimeoutMs);


    }

    public void initDefaultCommand() {
        setDefaultCommand(new drive());
    }

    public void drive(double leftspeed, double rightspeed) {// We are setting the speed controllers to speed

        leftspeed = Math.copySign(Math.pow(leftspeed, 2), leftspeed);
        rightspeed = Math.copySign(Math.pow(rightspeed, 2), rightspeed);

        if (leftspeed > 0.01 && rightspeed > 0.01) {
            leftspeed = (((leftspeed * Constants.kRobotVmax) / Constants.kVeloCharSlopeL) + Math.copySign(Constants.kVeloCharInterceptL, leftspeed)) / 12;
            rightspeed = (((rightspeed * Constants.kRobotVmax) / Constants.kVeloCharSlopeR) + Math.copySign(Constants.kVeloCharInterceptR, rightspeed)) / 12;
        }

        leftBack.set(ControlMode.PercentOutput, -leftspeed);
        rightFront.set(ControlMode.PercentOutput, -rightspeed);


    }

}