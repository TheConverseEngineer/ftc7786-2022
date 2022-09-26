package org.firstinspires.ftc.teamcode.virtual;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.virtual.implemetations.VirtualCRServo;
import org.firstinspires.ftc.teamcode.virtual.implemetations.VirtualDcMotorEx;
import org.firstinspires.ftc.teamcode.virtual.implemetations.VirtualServo;
import org.firstinspires.ftc.teamcode.virtual.implemetations.VirtualVoltageSensor;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/** Override HardwareMap to redirect get requests to virtual hardware
 * Currently supports DcMotorEx, Servo, CRServo, and Voltage Sensor */
public class VirtualHardwareMapFactory {

    public static final String MOTOR_TAG_NAME = "Motor";
    public static final String SERVO_TAG_NAME = "Servo";
    public static final String CRSERVO_TAG_NAME = "CRServo";


    /** Create a hardwareMap instance mapped to virtual implementations. Requires an XML file consisting of the hardware map */
    public static HardwareMap createVirtualHardwareMap(String filename) {
        HardwareMap hardwareMap = new HardwareMap(null);
        Document mapping = createXMLDocument(filename);

        assert Objects.equals(mapping.getDocumentElement().getTagName(), "HardwareMap");

        addAllMotors(mapping, hardwareMap);
        addAllServos(mapping, hardwareMap);

        hardwareMap.voltageSensor.put("Voltage Sensor", new VirtualVoltageSensor());

        return hardwareMap;
    }

    private static void addAllServos(Document doc, HardwareMap hardwareMap) {
        NodeList servos = doc.getElementsByTagName(SERVO_TAG_NAME);

        for (int i = 0; i < servos.getLength(); i++) {
            String name = servos.item(i).getAttributes().getNamedItem("name").getNodeValue();
            final VirtualServo servo = new VirtualServo(name, i);
            hardwareMap.put(name, servo);
            hardwareMap.servo.put(name, servo);
        }

        NodeList CRServos = doc.getElementsByTagName(CRSERVO_TAG_NAME);

        for (int i = 0; i < CRServos.getLength(); i++) {
            String name = CRServos.item(i).getAttributes().getNamedItem("name").getNodeValue();
            final VirtualCRServo CRServo = new VirtualCRServo(name, i);
            hardwareMap.put(name, CRServo);
            hardwareMap.crservo.put(name, CRServo);
        }
    }


    private static void addAllMotors(Document doc, HardwareMap hardwareMap) {
        NodeList motors = doc.getElementsByTagName(MOTOR_TAG_NAME);

        for (int i = 0; i < motors.getLength(); i++) {
            NamedNodeMap info = motors.item(i).getAttributes();
            String name = info.getNamedItem("name").getNodeValue();
            final VirtualDcMotorEx motor = new VirtualDcMotorEx(
                    name,
                    i,
                    Integer.parseInt(info.getNamedItem("rpm").getNodeValue()),
                    Double.parseDouble(info.getNamedItem("cpr").getNodeValue()));

            hardwareMap.put(name, motor);
            hardwareMap.dcMotor.put(name, motor);
        }
    }

    private static Document createXMLDocument(String filename) {
        File xmlFile = new File(filename);
        assert xmlFile.exists();
        assert xmlFile.canRead();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(xmlFile);
        } catch (Exception e) {
            // Swallow exception and replace it with a RuntimeException
            throw new RuntimeException("VHMapFactory: error reading XML document");
        }
    }
}
