package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AI.TensorSense;
import org.firstinspires.ftc.teamcode.Robot.Arm;
import org.firstinspires.ftc.teamcode.Robot.DriveTrain;
import org.firstinspires.ftc.teamcode.Robot.IMU;

@Autonomous(name = "Autonomous Red NO REPO")
public class AutoNoRepoRED extends LinearOpMode {


    public void runOpMode() {

        DriveTrain driveTrain = new DriveTrain(telemetry, hardwareMap, gamepad1);
        TensorSense sense = new TensorSense(telemetry, hardwareMap);
        IMU imu = new IMU(telemetry, hardwareMap, driveTrain);
        Arm arm = new Arm(telemetry, hardwareMap, gamepad1, driveTrain);
        sense.setupTfod();
        imu.imuSetup();

        waitForStart();

        driveTrain.driveAndArm(24,.4,arm.Intake,true);
        driveTrain.strafe(2,.4);

        while(sense.stonePosition == -100) {
            arm.openArm();
            sense.runTfod();
            telemetry.addData("Position", sense.stonePosition);
            telemetry.update();
            sleep(1000);
        }

        for (int i = 0; i < 100; i++) {
            sense.runTfod();
            telemetry.clear();
            telemetry.addData("Position", sense.stonePosition);
            telemetry.update();
        }
        switch (sense.stonePosition) {
            case (-1) :
                driveTrain.strafe(-7, 0.4);
                break;
            case (1) :
                driveTrain.strafe(8, 0.4);
                break;
            case (0) :
                driveTrain.strafe(-2, .4);
        }


        //  grab stone and pull out and turn
        driveTrain.drive(15,.3);
        arm.closeArm();
        sleep(700);
        driveTrain.drive(-10,.5);
        imu.proportionalIMU(90, false);
        arm.closeArm();


        //drive depending on where the skystone was
        switch(sense.stonePosition)
        {
            case (-1) :
                driveTrain.drive(48, .3);
                break;
            case (0) :
                driveTrain.drive(64, .3);
                break;
            case (1) :
                driveTrain.drive(70,.3);
                break;

        }

        // TODO: 1/8/2020 Change this into light mode because it is impossible to read
        // TODO: 1/8/2020 Servos are great


        arm.openArm();
        sleep(200);

        switch(sense.stonePosition)
        {
            case (-1) :
                driveTrain.drive(-68, .4);
                break;
            case (0) :
                driveTrain.drive(-61, .4);
                break;

        }
        if(!(sense.stonePosition == 1))
        {
            driveTrain.spin(-780,.4);
            driveTrain.drive(20,.4);
            arm.closeArm();
            sleep(700);
            driveTrain.drive(-15,.5);
            imu.proportionalIMU(90, false);
            arm.closeArm();

            switch(sense.stonePosition)
            {
                case (-1) :
                    driveTrain.drive(69, .5);
                    break;
                case (0) :
                    driveTrain.drive(65, .5);
                    break;

            }
            arm.openArm();
            sleep(300);



        }
        driveTrain.drive(-12,.6);










    }
}
