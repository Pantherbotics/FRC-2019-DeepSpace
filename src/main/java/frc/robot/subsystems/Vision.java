package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;

public class Vision extends Subsystem{
    private int aStart, aEnd, bStart, bEnd, cStart, cEnd;
    private String aString, bString, cString;

    private static double kOffsetX = 4;
    private static double kOffsetY = 0;

    private Notifier m_t_update;
    private Boolean JevoisConnected = false;
    private String m_data = "none";
    private SerialPort m_cameraSerial;

    private TalonSRX ledRing = new TalonSRX(Constants.jeVoisID);

    public Vision(int baudRate, SerialPort.Port serialPort){
        JevoisConnected = false;
        m_t_update = new Notifier(() -> {
            try{
                if(!JevoisConnected){
                    m_cameraSerial = new SerialPort(baudRate, serialPort);
                    JevoisConnected = true;
                }
            }
            catch(Exception e){
                System.out.println("ERROR: No JeVois plugged in... check init.cfg and make sure it's plugged in");
            }
            if(JevoisConnected){
                if(m_cameraSerial.getBytesReceived() > 0){
                    m_data = m_cameraSerial.readString();
                }
            }
        });
        m_t_update.startPeriodic(0.005);
        ledRing.enableVoltageCompensation(true);

    }

    public String getSerialData() {
        return m_data;
    }

    public double getAttackAngle() {
        aStart = m_data.indexOf("/A");
        aEnd = m_data.indexOf("/B");
        if (aStart != -1 && aEnd != -1){
            aString = m_data.substring(aStart+2, aEnd);
            try{
                return Double.parseDouble(aString);
            }
            catch(Exception e){
                return 0;
            }
        }
        return 0;
    }

    public double getDistance() {
        bStart = m_data.indexOf("/B");
        bEnd = m_data.indexOf("/C");
        if (bStart != -1 && bEnd != -1){
            bString = m_data.substring(bStart+2, bEnd);
            try{
                return Double.parseDouble(bString);
            }
            catch(Exception e){
                return 0;
            }
        }
        return 0;
    }

    public double getRobotAttackAngle(){
        double my_r, my_theta, polarX, polarY, rectX, rectY;

        polarX = getDistance();
        polarY = getAttackAngle()+(Math.PI/2.0);

        rectX = polarX*Math.cos(polarY);
        rectY = polarX*Math.sin(polarY);

        rectX = rectX - kOffsetX;
        rectY = rectY + kOffsetY;

        my_r=Math.sqrt(Math.pow(rectX , 2) + Math.pow(rectY , 2));

        my_theta = (Math.atan(rectY/rectX));

        if(rectX<0){
            my_theta = my_theta+(Math.PI/2.0);
        }else{
            my_theta = -((Math.PI/2.0)-my_theta);
        }
        return my_theta;
    }

    public String getSideDeviation() {
        cStart = m_data.indexOf("/C");
        cEnd = m_data.indexOf("/D");
        if (cStart != -1 && cEnd != -1){
            cString = m_data.substring(cStart+2, cEnd);
            return cString;
        }
        return "1:1";
    }

    public Boolean isTarget(){
        aStart = m_data.indexOf("/A");
        aEnd = m_data.indexOf("/B");
        bStart = m_data.indexOf("/B");
        bEnd = m_data.indexOf("/C");
        cStart = m_data.indexOf("/C");
        cEnd = m_data.indexOf("/D");
        return aStart != -1 && bStart != -1 && cStart != -1 && aEnd != -1 && bEnd != -1 && cEnd != -1;
    }

    private double convertToRobotAngle(double jevoisAngle, double distance){
        if(jevoisAngle > 0){
            return Math.PI/2.0 - Math.atan((distance * Math.cos(jevoisAngle))/ (distance * Math.sin(jevoisAngle - 4)));
        }
        else
            return Math.atan((distance * Math.sin(jevoisAngle)+4)/(distance * Math.cos(jevoisAngle)));

    }

    /*public double getRobotAttackAngle(){
        return convertToRobotAngle(getAttackAngle(), getDistance());
    }*/

    public void enableLEDs(){
        ledRing.set(ControlMode.PercentOutput, 1.0);
    }

    public void disableLEDs(){
        ledRing.set(ControlMode.PercentOutput, 0);
    }
    //public double getTrueAttackAngle()

    @Override
    protected void initDefaultCommand() {

    }

}