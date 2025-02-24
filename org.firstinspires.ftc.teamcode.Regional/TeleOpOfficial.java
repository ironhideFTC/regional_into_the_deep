package org.firstinspires.ftc.teamcode.Regional;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp

public class TeleOpOfficial extends LinearOpMode{

    //Definindo Motores e Servo
    private DcMotor right;
    private DcMotor left;
    private DcMotor arm;
    private DcMotor slide;
    private Servo claw;
    
    //Movimentação do Drive Train
    public void movement(){
        right.setPower(gamepad1.right_stick_y * 0.75);
        left.setPower(gamepad1.left_stick_y * 0.75);
        
        if(gamepad1.right_bumper){
            right.setPower(1);
            left.setPower(-1);
        } else if(gamepad1.left_bumper){
            right.setPower(-1);
            left.setPower(1);
        }
    }
    
    //Movimentação do Braço
    public void arm(){
        arm.setPower(gamepad2.right_stick_y);
    }
    
    //Extensão e retração do Slider
    public void slide(){
        slide.setPower(gamepad2.left_stick_y);
    }
    
    //Funcionamento da Garra
    public void claw(){
        if (gamepad2.right_bumper) {
            claw.setPosition(0);
        } else if (gamepad2.left_bumper) {
            claw.setPosition(1);
        }
    }
    
    @Override
    public void runOpMode(){

        //Mapeamento dos Motores e Servo
        right = hardwareMap.get(DcMotor.class, "right");
        left = hardwareMap.get(DcMotor.class, "left");
        arm = hardwareMap.get(DcMotor.class, "arm");
        slide = hardwareMap.get(DcMotor.class, "slide");
        claw = hardwareMap.get(Servo.class, "claw");
        
        //Direção dos Motores
        right.setDirection(DcMotor.Direction.FORWARD);
        left.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.REVERSE);
        slide.setDirection(DcMotor.Direction.REVERSE);
        
        waitForStart();
        
        while (opModeIsActive()){

            //Chamando funções dentro do Loop
            movement();
            
            arm();
            
            slide();
            
            claw();
        }
        
    }
    
    
}
