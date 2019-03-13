package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem{
    private int aStart, aEnd, bStart, bEnd, cStart, cEnd;
    private String aString, bString, cString;

    private Notifier m_t_update;
    private Boolean JevoisConnected = false;
    private String m_data = "none";
    private SerialPort m_cameraSerial;

    private TalonSRX ledRing = new TalonSRX(7);

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
                System.out.println("No jevois pluggen in... check init.cfg and make sure it's plugged in");
            }
            if(JevoisConnected){
                if(m_cameraSerial.getBytesReceived() > 0){
                    m_data = m_cameraSerial.readString();
                }
            }
        });
        m_t_update.startPeriodic(0.005);
        ledRing.enableVoltageCompensation(true);
        ledRing.set(ControlMode.PercentOutput, 1.0);
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
        if (aStart!=-1 && bStart!=-1 && cStart!=-1 && aEnd!=-1 && bEnd!= -1 && cEnd!=-1){
            return true;
        }
        return false;
    }

    //public double getTrueAttackAngle()

    @Override
    protected void initDefaultCommand() {

    }

}