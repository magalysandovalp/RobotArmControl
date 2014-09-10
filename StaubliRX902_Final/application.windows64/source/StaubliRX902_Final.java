import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import controlP5.*; 
import processing.serial.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class StaubliRX902_Final extends PApplet {

/**
*Interfaz Gr\u00e1fica
* Por: Magaly Sandoval /Israel Chaves
*Versi\u00f3n 2.0
*17 de Junio de 2014
*/
//---------------------------------Bibliotecas-----------------------------------------------//




ControlP5 cp5;

//------------------------------Variables Globales--------------------------------------------------//

Textlabel Titulo;
Textfield equis1, equis2, equis3, equis4, ye1, ye2, ye3, ye4, zeta1, zeta2, zeta3, zeta4, Advertencia;

String textValue = "";

String x1Str,y1Str,z1Str, x2Str, y2Str, z2Str, x3Str, y3Str, z3Str, x4Str, y4Str, z4Str;

float x1, y1, z1, x2, y2, z2, x3, y3, z3, x4, y4, z4;

int contador1, contador2, contador3, contador4;

int contador_final1 = 0;
int contador_final2 = 0;
int contador_final3 = 0;
int contador_final4 = 0;

float c1,c2,c3,c4;

float ang1, ang2, ang3, ang4;
String ang1Str, ang2Str, ang3Str, ang4Str;

PImage img;

Serial PuertoSerie; 
int ValorPuerto;

float Velocidad;
Slider vel;

Button Salida;
int exit = 0;

//--------------------------------------------------------------------------------------------//

public void setup() {
  
  size(600,600);
  
  cp5 = new ControlP5(this);
  
  img = loadImage("D:/Downloads/RX90.png"); //Imagen
  
//**************************Puerto Serial********************************

  String PortName = Serial.list()[0];
  PuertoSerie = new Serial(this, "COM1", 9600); // Cambiar el COM
  delay(1000);
  PuertoSerie.write("en po");
  PuertoSerie.write(0x0D);

  
//**************************T\u00edtulos**************************************

  Titulo = cp5.addTextlabel("Titulo")
                    .setText("Control de Brazo Rob\u00f3tico \n           Staubli RX90")
                    .setPosition(105,35)
                    .setColorValue(0xffffffff)
                    .setFont(createFont("Lucida Sans",30))
                    ;
  cp5.addTextlabel("Instrucciones")
     .setText(" Las coordenadas se ingresan en mm en el orden X1,Y1,Z1, (...), Z4,Y4,Z4. \n Presionar Enter al ingresar cada valor. El valor no debe ser de m\u00e1s de 3 cifras significativas")
     .setPosition(30, 140)
     .setColorValue(0x00000000)
     .setFont(createFont("Lucida Sans",12))
     ;
     
//**************************PUNTO 1***************************************

  cp5.addTextlabel("Punto 1")
     .setText("Coordenadas Punto 1")
     .setPosition(123, 180)
     .setColorValue(0x00000000)
     .setFont(createFont("Lucida Sans",14))
     ;
     
  equis1 = cp5.addTextfield("X1") 
     .setPosition(120,200)
     .setSize(60,20)
     .setFont(createFont("Lucida Sans",15))
     .setFocus(true)
     .setColor(color(0,0,0))
     .setColorBackground(color(255,255,255))
     .setColorCaptionLabel(0)
     .setAutoClear(false)
     ;
     
  ye1 = cp5.addTextfield("Y1") 
     .setPosition(180,200)
     .setSize(60,20)
     .setFont(createFont("Lucida Sans",15))
     .setFocus(true)
     .setColor(color(0,0,0))
     .setColorBackground(color(255,255,255))
     .setColorCaptionLabel(0)
     .setAutoClear(false)
     ;

  zeta1 = cp5.addTextfield("Z1") 
     .setPosition(240,200)
     .setSize(60,20)
     .setFont(createFont("Lucida Sans",15))
     .setFocus(true)
     .setColor(color(0,0,0))
     .setColorBackground(color(255,255,255))
     .setColorCaptionLabel(0)
     .setAutoClear(false)
     ;

//**************************PUNTO 2***************************************

  cp5.addTextlabel("Punto 2")
     .setText("Coordenadas Punto 2")
     .setPosition(350, 180)
     .setColorValue(0x00000000)
     .setFont(createFont("Lucida Sans",14))
     ;
     
  equis2 = cp5.addTextfield("X2") 
     .setPosition(350,200)
     .setSize(60,20)
     .setFont(createFont("Lucida Sans",15))
     .setFocus(true)
     .setColor(color(0,0,0))
     .setColorBackground(color(255,255,255))
     .setColorCaptionLabel(0)
     .setAutoClear(false)
     ;
     
  ye2 = cp5.addTextfield("Y2") 
     .setPosition(410,200)
     .setSize(60,20)
     .setFont(createFont("Lucida Sans",15))
     .setFocus(true)
     .setColor(color(0,0,0))
     .setColorBackground(color(255,255,255))
     .setColorCaptionLabel(0)
     .setAutoClear(false)
     ;

  zeta2 = cp5.addTextfield("Z2") 
     .setPosition(470,200)
     .setSize(60,20)
     .setFont(createFont("Lucida Sans",15))
     .setFocus(true)
     .setColor(color(0,0,0))
     .setColorBackground(color(255,255,255))
     .setColorCaptionLabel(0)
     .setAutoClear(false)
     ;
     
//**************************PUNTO 3***************************************

  cp5.addTextlabel("Punto 3")
     .setText("Coordenadas Punto 3")
     .setPosition(123, 250)
     .setColorValue(0x00000000)
     .setFont(createFont("Lucida Sans",14))
     ;
     
  equis3 = cp5.addTextfield("X3") 
     .setPosition(120,270)
     .setSize(60,20)
     .setFont(createFont("Lucida Sans",15))
     .setFocus(true)
     .setColor(color(0,0,0))
     .setColorBackground(color(255,255,255))
     .setColorCaptionLabel(0)
     .setAutoClear(false)
     ;
     
  ye3 = cp5.addTextfield("Y3") 
     .setPosition(180,270)
     .setSize(60,20)
     .setFont(createFont("Lucida Sans",15))
     .setFocus(true)
     .setColor(color(0,0,0))
     .setColorBackground(color(255,255,255))
     .setColorCaptionLabel(0)
     .setAutoClear(false)
     ;

  zeta3 = cp5.addTextfield("Z3") 
     .setPosition(240,270)
     .setSize(60,20)
     .setFont(createFont("Lucida Sans",15))
     .setFocus(true)
     .setColor(color(0,0,0))
     .setColorBackground(color(255,255,255))
     .setColorCaptionLabel(0)
     .setAutoClear(false)
     ;

//**************************PUNTO 4***************************************

  cp5.addTextlabel("Punto 4")
     .setText("Coordenadas Punto 4")
     .setPosition(350, 250)
     .setColorValue(0x00000000)
     .setFont(createFont("Lucida Sans",14))
     ;
     
  equis4 = cp5.addTextfield("X4") 
     .setPosition(350,270)
     .setSize(60,20)
     .setFont(createFont("Lucida Sans",15))
     .setFocus(true)
     .setColor(color(0,0,0))
     .setColorBackground(color(255,255,255))
     .setColorCaptionLabel(0)
     .setAutoClear(false)
     ;
     
  ye4 = cp5.addTextfield("Y4") 
     .setPosition(410,270)
     .setSize(60,20)
     .setFont(createFont("Lucida Sans",15))
     .setFocus(true)
     .setColor(color(0,0,0))
     .setColorBackground(color(255,255,255))
     .setColorCaptionLabel(0)
     .setAutoClear(false)
     ;

  zeta4= cp5.addTextfield("Z4") 
     .setPosition(470,270)
     .setSize(60,20)
     .setFont(createFont("Lucida Sans",15))
     .setFocus(true)
     .setColor(color(0,0,0))
     .setColorBackground(color(255,255,255))
     .setColorCaptionLabel(0)
     .setAutoClear(false)
     ;
     
//**************************CR\u00c9DITOS***************************************

   cp5.addTextlabel("TEC")
     .setText("Tecnol\u00f3gico de Costa Rica\nRob\u00f3tica")
     .setPosition(175, 570)
     .setColorValue(0x00000000)
     .setFont(createFont("Lucida Sans",10))
     ;
     
   cp5.addTextlabel("Creditos")
     .setText("Israel Chaves Arbaiza\nMagaly Sandoval Pichardo")
     .setPosition(320, 570)
     .setColorValue(0x00000000)
     .setFont(createFont("Lucida Sans",10))
     ;
     
//**************************ADVERTENCIAS***************************************

     
    Advertencia = cp5.addTextfield("Advertencia") 
         .setPosition(350,340)
         .setSize(220,20)
         .setFont(createFont("Lucida Sans",12))
         .setFocus(true)
         .setColor(100)
         .setColorBackground(color(255,255,255))
         .setColorCaptionLabel(color(255,0,0))
         .lock()
         .setText("           Preparado")
         ;
//**************************Boton***************************************


   Salida = cp5.addButton("Salir")
      .setValue(0)
      .setPosition(0,0)
      .setSize(40,20)
      ;

//**************************SLIDER********************************

cp5.addTextlabel("Velocidad Robot")
     .setText("Velocidad Brazo")
     .setPosition(5,180)
     .setColorValue(0x00000000)
     .setFont(createFont("Lucida Sans",14))
     ;
vel = cp5.addSlider("V ")
      .setPosition(45,200)
      .setSize(20,100)
      .setRange(35,65)
      .setNumberOfTickMarks(12)
      .setColorTickMark(0)
      .setColorBackground(color(84,166,253)) 
      .setColorActive(color(2,62,127))
      .setColorCaptionLabel(0)
      .setColorValueLabel(225)  
      
      ;

  
//**************************POSICION BASE ROBOT*********************************


     delay(5000);  
     PuertoSerie.write("do ready");
     PuertoSerie.write(0x0D);
     delay(2000);  
     PuertoSerie.write("speed 60");
     PuertoSerie.write(0x0D);
     delay(1500);        
     PuertoSerie.write("do drive 2, 15, 40");
     PuertoSerie.write(0x0D);
     delay(2000);
     PuertoSerie.write("do drive 5, -25, 30");
     PuertoSerie.write(0x0D);
     delay(2000);
     PuertoSerie.write("do drive 3, 70, 30");
     PuertoSerie.write(0x0D);   
     delay(2000);  
     PuertoSerie.write("here #home"); 
     PuertoSerie.write(0x0D);
     delay(1000);
     PuertoSerie.write(0x0D);
     delay(1000);
  
}


public void draw() {

  background(225);
  
  stroke(3000);  
  fill(200);
  rect(0,0, 600, 120); //Gris intermedio
  
  fill(80);
  rect(0,0, 600, 20);//Gris oscuro
  
  fill(0xff20C8FF);
  rect(0,120,600,2); //Celeste
  
  image(img,-20,380,img.width/1.4f, img.height/1.8f);
  
  fill(200);
  rect(0,570, 600, 30);//Celeste final
  
}

//**************************LECTURA DATOS*********************************

public void controlEvent (ControlEvent Evento){
  
  float raiz1;
  float raiz2;
  float raiz3;
  float raiz4;
  
  if (exit==2){
    println("Saliendo");
    delay(1000);
    PuertoSerie.write("do ready");
    PuertoSerie.write(0x0D);
    delay(3000);  
    PuertoSerie.write("dis po");
    PuertoSerie.write(0x0D);
    exit();
    
  }
  

    
  if (Evento.isAssignableFrom(Textfield.class)){
    println("Coordenadas para "+Evento.getName()+": "+Evento.getStringValue());
    
  //**************************LECTURA PUNTO 1*********************************
      if (Evento.getName() == "X1"){
        x1Str = Evento.getStringValue(); 
        x1 = Float.parseFloat(x1Str); 
        equis1.setValueLabel(x1Str);
      }
      if (Evento.getName() == "Y1"){
        y1Str = Evento.getStringValue();
        y1 = Float.parseFloat(y1Str);
      }
      if (Evento.getName() == "Z1"){
        z1Str = Evento.getStringValue();
        z1 = 250+Float.parseFloat(z1Str);
        contador1 =3; 
      }
      
    //**************************LECTURA PUNTO 2*********************************    
      if (Evento.getName() == "X2"){
        x2Str = Evento.getStringValue(); 
        x2 = Float.parseFloat(x2Str); 
        contador2 = 1;
      }
      if (Evento.getName() == "Y2"){
        y2Str = Evento.getStringValue();
        y2 = Float.parseFloat(y2Str);
        contador2 = 2;
      }
      if (Evento.getName() == "Z2"){
        z2Str = Evento.getStringValue();
        z2 = 250+Float.parseFloat(z2Str);
        contador2 =3;
        
      }
    //**************************LECTURA PUNTO 3*********************************
    if (Evento.getName() == "X3"){
        x3Str = Evento.getStringValue(); 
        x3 = Float.parseFloat(x3Str); 
        contador3 = 1;
      }
      if (Evento.getName() == "Y3"){
        y3Str = Evento.getStringValue();
        y3 = Float.parseFloat(y3Str);
        contador3 = 2;
      }
      if (Evento.getName() == "Z3"){
        z3Str = Evento.getStringValue();
        z3 = 250+Float.parseFloat(z3Str);
        contador3 =3;
        
      }
     //**************************LECTURA PUNTO 4*********************************
    if (Evento.getName() == "X4"){
        x4Str = Evento.getStringValue(); 
        x4 = Float.parseFloat(x4Str); 
        contador4 = 1;
      }
      if (Evento.getName() == "Y4"){
        y4Str = Evento.getStringValue();
        y4 = Float.parseFloat(y4Str);
        contador4 = 2;
      }
      if (Evento.getName() == "Z4"){
        z4Str = Evento.getStringValue();
        z4 = 250+Float.parseFloat(z4Str);
        contador4 =3;      
      }
      
     //**************************COMPROBACION PUNTO 1*********************************
      if (contador1==3){
        
        raiz1=sqrt(x1*x1 + y1*y1 + z1*z1);
       
        
        //*************************CALCULO DE ANGULO XY*****************************
         c1=(atan(y1/x1))*180/PI;
        if (x1<0 && y1<0){
          ang1=-(180-c1);
        }
        else if (x1<0 && y1>0){
          ang1=180 + c1;
        }
        else if (x1>0 && y1<0){
          ang1=c1;
        }
        else if (x1>0 && y1>0){
          ang1=c1;
        }
        else if (x1==0 && y1>0){
          ang1=90;
        }
        else if (x1==0 && y1<0){
          ang1=-90;
        }
        else if (x1>0 && y1==0){
          ang1=0;
        }
        else if (x1<0 && y1==0){
          ang1=180;
        }
        
  
          if ((raiz1 <= 300)||(ang1>160)||(ang1<-160)||(raiz1 >1100)){
          println("Fuera de rango");
         
         Advertencia.setText("Fuera de Rango: Corregir Punto 1");
         equis1.clear();
         ye1.clear();
         zeta1.clear();
         
        }
        else{
          contador_final1 = 1;
          ang1Str=String.valueOf(ang1);
          Advertencia.setText("Punto 1: No hay advertencias");
        }
     }
     
  
     //**************************COMPROBACION PUNTO 2*********************************
      if (contador2==3){
        
        raiz2=sqrt(x2*x2 + y2*y2 + z2*z2);
        
        //*************************CALCULO DE ANGULO XY*****************************
        c2=(atan(y2/x2))*180/PI;
        if (x2<0 && y2<0){
          ang2=-(180-c2);
        }
        else if (x2<0 && y2>0){
          ang2=180 + c2;
        }
        else if (x2>0 && y2<0){
          ang2=c2;
        }
        else if (x2>0 && y2>0){
          ang2=c2;
        }
        else if (x2==0 && y2>0){
          ang2=90;
        }
        else if (x2==0 && y2<0){
          ang2=-90;
        }
        else if (x2>0 && y2==0){
          ang2=0;
        }
        else if (x2<0 && y2==0){
          ang2=180;
        }
        
        
          if ((raiz2 <= 300)||(ang2>160)||(ang2<-160)||(raiz2 >1100)){
          println("Fuera de rango");
         
         Advertencia.setText("Fuera de Rango: Corregir Punto 2");
         equis2.clear();
         ye2.clear();
         zeta2.clear();
        }
        else{
          contador_final2 = 1;
          ang2Str=String.valueOf(ang2);
          Advertencia.setText("Punto 2: No hay advertencias");

        }
     }
     
        //**************************COMPROBACION PUNTO 3*********************************
        
        if (contador3==3){
        
          raiz3=sqrt(x3*x3 + y3*y3 + z3*z3);
          
                  //*************************CALCULO DE ANGULO XY*****************************
         c3=(atan(y3/x3))*180/PI;
        if (x3<0 && y3<0){
          ang3=-(180-c3);
        }
        else if (x3<0 && y3>0){
          ang3=180 + c3;
        }
        else if (x3>0 && y3<0){
          ang3=c3;
        }
        else if (x3>0 && y3>0){
          ang3=c3;
        }
        else if (x3==0 && y3>0){
          ang3=90;
        }
        else if (x3==0 && y3<0){
          ang3=-90;
        }
        else if (x3>0 && y3==0){
          ang3=0;
        }
        else if (x3<0 && y3==0){
          ang3=180;
        }
  
            if ((raiz3 <= 300)||(ang3>160)||(ang3<-160)||(raiz3 >1100)){
          println("Fuera de rango");
         
         Advertencia.setText("Fuera de Rango: Corregir Punto 3");
         equis3.clear();
         ye3.clear();
         zeta3.clear();
          }
          else{
          contador_final3 = 1;
          ang3Str=String.valueOf(ang3);
          Advertencia.setText("Punto 3: No hay advertencias");
        }
     }
     
           //**************************COMPROBACION PUNTO 4*********************************      
        if (contador4==3){
        
        raiz4=sqrt(x4*x4 + y4*y4 + z4*z4);
  
                    //*************************CALCULO DE ANGULO XY*****************************
         c4=(atan(y4/x4))*180/PI;
        if (x4<0 && y4<0){
          ang4=-(180-c4);
        }
        else if (x4<0 && y4>0){
          ang4=180 + c4;
        }
        else if (x4>0 && y4<0){
          ang4=c4;
        }
        else if (x4>0 && y4>0){
          ang4=c4;
        }
        else if (x4==0 && y4>0){
          ang4=90;
        }
        else if (x4==0 && y4<0){
          ang4=-90;
        }
        else if (x4>0 && y4==0){
          ang4=0;
        }
        else if (x4<0 && y4==0){
          ang4=180;
        }
        
            if ((raiz4 <= 300)||(ang4>160)||(ang4<-160)||(raiz4 >1100)){
            println("Fuera de rango");         
         Advertencia.setText("Fuera de Rango: Corregir Punto 4");
         equis4.clear();
         ye4.clear();
         zeta4.clear();
         
         x1 = 0;
         y1 = 0;
         z1 = 0;
                 
          }
          else{
          contador_final4 = 1;
          ang4Str=String.valueOf(ang4);
          Advertencia.setText("           Preparado");
          Velocidad = vel.getValue();
          println(Velocidad);
        }
     }
     
     if ((contador_final1 == 1)&& (contador_final2 == 1)&&(contador_final3 == 1)&& (contador_final4 == 1)){
                
         delay(2000);
         PuertoSerie.write("speed "+Velocidad);
         PuertoSerie.write(0x0D);
         delay(2000);
         
         
         PuertoSerie.write("do move trans("+ x1 +","+ y1 +","+ z1 +","+ ang1Str+", 60, 0)");
         PuertoSerie.write(0x0D);
         delay(8000);
         
         PuertoSerie.write("do drive 5, 87, 30");
         PuertoSerie.write(0x0D);
         delay(8000);
         
         PuertoSerie.write("do drive 5, -149, 30");
         PuertoSerie.write(0x0D);
         delay(8000);
         
         
         PuertoSerie.write("do move #home");
         PuertoSerie.write(0x0D);
         delay(8000);
         
         PuertoSerie.write("do move trans("+ x2 +","+ y2 +","+ z2 +","+ ang2Str+", 60, 0)");
         PuertoSerie.write(0x0D);
         delay(8000);
         
         PuertoSerie.write("do drive 5, 87, 30");
         PuertoSerie.write(0x0D);
         delay(8000);
         
         PuertoSerie.write("do drive 5, -149, 30");
         PuertoSerie.write(0x0D);
         delay(8000);
         
         
         PuertoSerie.write("do move #home");
         PuertoSerie.write(0x0D);
         delay(8000);  
         
         PuertoSerie.write("do move trans("+ x3 +","+ y3 +","+ z3 +","+ ang3Str+", 60, 0)");
         PuertoSerie.write(0x0D);
         delay(8000);
         
         PuertoSerie.write("do drive 5, 87, 30");
         PuertoSerie.write(0x0D);
         delay(8000);

         PuertoSerie.write("do drive 5, -149, 30");
         PuertoSerie.write(0x0D);
         delay(8000);
         
         
         PuertoSerie.write("do move #home");
         PuertoSerie.write(0x0D);
         delay(8000); 
         
         PuertoSerie.write("do move trans("+ x4 +","+ y4 +","+ z4 +","+ ang4Str+", 60, 0)");
         PuertoSerie.write(0x0D);
         delay(8000);  
         
         PuertoSerie.write("do drive 5, 87, 30");
         PuertoSerie.write(0x0D);
         delay(8000);
         
         PuertoSerie.write("do drive 5, -149, 30");
         PuertoSerie.write(0x0D);
         delay(8000);
         
         
         PuertoSerie.write("do move #home");
         PuertoSerie.write(0x0D);
         delay(250);
   
//*************************REINICIO VARIABLES*********************************      
      
         contador_final1=0;
         contador_final2=0;
         contador_final3=0;
         contador_final4=0;
         
         contador1 =0;
         contador2 =0;
         contador3 =0;
         contador4 =0;
         
         raiz1=0;
         raiz2=0;
         raiz3=0;
         raiz4=0;
                 
         x1 = 0;
         x2 = 0;
         x3 = 0;
         x4 = 0;
         
         y1 = 0;
         y2 = 0;
         y3 = 0;
         y4 = 0;
         
         z1 = 0;
         z2 = 0;
         z3 = 0;
         z4 = 0;
         
         x1Str = "0";
         x2Str = "0";
         x3Str = "0";
         x4Str = "0";
         
         y1Str = "0";
         y2Str = "0";
         y3Str = "0";
         y4Str = "0";
         
         z1Str = "0";
         z2Str = "0";
         z3Str = "0";
         z4Str = "0";
         
         equis1.clear();
         equis2.clear();
         equis3.clear();
         equis4.clear();
         
         ye1.clear();
         ye2.clear();
         ye3.clear();
         ye4.clear();
         
         zeta1.clear();
         zeta2.clear();
         zeta3.clear();
         zeta4.clear();
                    
     }   
     
    }
}

public void Salir(int theValue) {
  exit = exit +1;  
}



  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "StaubliRX902_Final" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
