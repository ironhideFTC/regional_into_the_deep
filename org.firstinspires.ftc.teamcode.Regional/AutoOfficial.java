package org.firstinspires.ftc.teamcode.Regional;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class AutoOfficial extends LinearOpMode {
    
    //Definindo Motores e Servo
    private DcMotor right;
    private DcMotor left;
    private DcMotor arm;
    private DcMotor slide;
    private Servo claw;
    
    //Criando variáveis de posição
    private int rightPos;
    private int leftPos;
    private int armPos;
    private int slidePos;
    
    //Função de movimento do Drive Train
    
    /* Ao chamar a função, é setada a posição desejada e a potência colocando o
    valor entre seus parênteses, na ordem em que está programada: rightTarget,
    leftTarget e speed */
    public void drive(int rightTarget,int leftTarget, double speed) {
        
        //Definindo a variável Posição como a variável Target
        rightPos += rightTarget;
        leftPos += leftTarget;
        
        //Definindo a posição desejada com o valor da variável Posição
        right.setTargetPosition(rightPos);
        left.setTargetPosition(leftPos);
        
        //Setando o modo do Motor para ir à posição setada no TargetPosition
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        //Definindo a velocidade/potência do motor com o valor da variável Speed
        left.setPower(speed);
        right.setPower(speed);
        
        while (opModeIsActive() && right.isBusy() && left.isBusy()) {
            idle();
        }
        
    }
    
    //A descrição acima também serve para as funções arm() e slide() abaixo
    
    public void arm(int armTarget, double speed) {
        
        armPos += armTarget;
        
        arm.setTargetPosition(armPos);
        
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        arm.setPower(speed);
        
        while (opModeIsActive() && arm.isBusy()) {
            idle();
        }
        
    }
    
    public void slide(int slideTarget, double speed) {
        
        slidePos += slideTarget;
        
        slide.setTargetPosition(slidePos);
        
        slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        slide.setPower(speed);
        
        while (opModeIsActive() && slide.isBusy()) {
            idle();
        }
        
    }
    
    //Função para o robô parar
    public void driveStop() {
        
        right.setPower(0);
        left.setPower(0);
    
    }

    @Override
    public void runOpMode() {
        
        //Mapeamento dos Motores e Servo
        right = hardwareMap.get(DcMotor.class, "right");
        left = hardwareMap.get(DcMotor.class, "left");
        arm = hardwareMap.get(DcMotor.class, "arm");
        slide = hardwareMap.get(DcMotor.class, "slide");
        claw = hardwareMap.get(Servo.class, "claw");
        
        //Direção dos Motores
        right.setDirection(DcMotor.Direction.REVERSE);
        left.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotor.Direction.REVERSE);
        slide.setDirection(DcMotor.Direction.FORWARD);
        
        //Setando modo dos Motores para usarem o Encoder
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        //Definição das variáveis de posição
        rightPos = 0;
        leftPos = 0;
        armPos = 0;
        slidePos = 0;
        
        waitForStart();
        //Agarra o espécime e levanta o braço
        claw.setPosition(1);
        arm(-3500, 1);
        
        //Movimenta para frente em 725 rotações
        drive(720, 720, 0.3);
        driveStop();
        
        /*Extensão do slide e movimento para baixo da garra, prendendo
        na barra alta*/
        slide(360, 1);
        arm(250, 1);
        slide(25, 1);
        arm(3500, 1);
        arm(-3500, 1);
        
        //Soltando o espécime e retraindo o slide
        claw.setPosition(0);
        sleep(1000);
        slide(-325, 1);
        
        //Ré e curva para que o robô vá até a frente das amostras vermelhas
        drive(-650, -650, 0.2);
        arm(3000, 1);
        sleep(1000);
        drive(110, -75, 0.3);
        sleep(1000);
        drive(1650, 1650, 0.3);
        
        /*Curva e ré para que o robô leve as amostras até o jogador humano,
        estacionando depois*/
        sleep(500);
        drive(-175, 125, 0.3);
        driveStop();
        sleep(1000);
        drive(-1200, -1200, 0.4);
        driveStop();
        drive(1300, 1300, 0.5);
        sleep(1000);
        drive(-85, 220, 0.7);
        driveStop();
        drive(210, 210, 0.5);
        driveStop();
        arm(-3000, 1);
        slide(325, 1);
    }

}
