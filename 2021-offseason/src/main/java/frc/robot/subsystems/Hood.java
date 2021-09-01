package frc.robot.subsystems;

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.team254.lib.util.InterpolatingDouble;
import com.team254.lib.util.InterpolatingTreeMap;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.Constants;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;

public class Hood extends SubsystemBase {
    private final CANSparkMax hoodMain;
    private final CANEncoder hoodEncoder;
    private final CANPIDController hoodController;
    private InterpolatingTreeMap closeTreeMap;
    private InterpolatingTreeMap farTreeMap;
    private PhotonCamera camera;
    private double currentDegree = 0;
    private final DigitalInput limitswitch = new DigitalInput(Constants.kHood.LIMIT_PORT);
    private boolean isTaring = false;
    private double initialSetpoint;
    public boolean isAiming = true;
    
    public Hood() {
        SmartDashboard.putNumber("kP", Constants.kHood.kP);
        SmartDashboard.putNumber("kD", Constants.kHood.kD);
        this.hoodMain = new CANSparkMax(Constants.kHood.MOTOR_ID, Constants.kHood.MOTOR_TYPE);
        hoodMain.setIdleMode(CANSparkMax.IdleMode.kCoast);
        this.hoodEncoder = this.hoodMain.getEncoder();
        this.hoodController = this.hoodMain.getPIDController();
        this.hoodController.setOutputRange(-Constants.kHood.MAX_SPEED, Constants.kHood.MAX_SPEED);
        this.hoodController.setP(Constants.kHood.kP);
        this.hoodController.setI(Constants.kHood.kI);
        this.hoodController.setD(Constants.kHood.kD);
        this.camera = new PhotonCamera("myCamera");
        initialSetpoint = hoodEncoder.getPosition();
        setZero();
        startAligning();
    }
    @Override
    public void periodic() {
        SmartDashboard.putNumber("hood applied output", hoodMain.getAppliedOutput());
        SmartDashboard.putNumber("hood encoder position", hoodEncoder.getPosition());
        if (isTaring) tareHoodPeriodic();
        if (isAiming) alignToTarget();
    }

    public void setZero() {
        isTaring = true;
    }

    public void startAligning() {
        isAiming = true;
    }

    public void stopAligning() {
        isAiming = false;
    }

    private void tareHoodPeriodic() {
        if (limitswitch.get()) {
            hoodMain.set(0);
            currentDegree = 0;
            isTaring = false;
            initialSetpoint = hoodEncoder.getPosition();
        }
        else hoodMain.set(Constants.kHood.MAX_SPEED);
    }

    public void set(double setpoint) {
        this.hoodController.setReference(setpoint, ControlType.kPosition);
    }
    public void incrementUp() {
        increaseSetpoint(Constants.kHood.HOOD_INCREMENT);
    }
    public void incrementDown() {
        increaseSetpoint(-Constants.kHood.HOOD_INCREMENT);
    }
    public void increaseSetpoint(double amount) {
        currentDegree+=amount;
        currentDegree=Math.max(0, currentDegree);
        set(currentDegree+initialSetpoint);
    }
    public void setSetpoint(double setpoint) {
        increaseSetpoint(setpoint-currentDegree);
    }
    public void alignToTarget() {
        var result = camera.getLatestResult();
        if (result.hasTargets()) {
            double range = PhotonUtils.calculateDistanceToTargetMeters(
                Constants.kHood.CAMERA_HEIGHT, 
                Constants.kHood.TARGET_HEIGHT,
                Constants.kHood.PITCH_RADIANS,
                Units.degreesToRadians(result.getBestTarget().getPitch()));
            set(Constants.kHood.hoodAngleTreeMap.get(new InterpolatingDouble(range)).value);
        }
    }
}
