import static com.neuronrobotics.sdk.addons.kinematics.VitaminFrame.*

import com.neuronrobotics.sdk.addons.kinematics.AbstractLink
import com.neuronrobotics.sdk.addons.kinematics.DHLink
import com.neuronrobotics.sdk.addons.kinematics.DHParameterKinematics
import com.neuronrobotics.sdk.addons.kinematics.LinkConfiguration
import com.neuronrobotics.sdk.addons.kinematics.MobileBase
import com.neuronrobotics.sdk.addons.kinematics.VitaminLocation
import com.neuronrobotics.sdk.addons.kinematics.math.RotationNR
import com.neuronrobotics.sdk.addons.kinematics.math.TransformNR

import javafx.scene.transform.Affine
if(args==null) {
	args= new HashMap<String,Object>()
	args.put("diameter",69.85)
	args.put("numRollers",9)
	args.put("HubHeight",24)
	args.put("rollerHeight", 12.5)
	args.put("rollerDiameter",8)
	args.put("numberOfRows",2)
	args.put("electroMechanicalType","vexMotor")
	args.put("electroMechanicalSize","v5_11w")
	args.put("shaftType","vexWheels")
	args.put("shaftSize","2_75InchOmni")
	args.put("wheelName", "uniqueName")
	MobileBase g = new MobileBase();
	g.setGitSelfSource(["https://github.com/CommonWealthRobotics/HolonomicWheels.git","2.75InchOmniExample.xml"]as String[])
	g.setMassKg(0.001)
	g.setScriptingName("OmniWheelGenerated")
	g.setGitWalkingEngine(["https://github.com/CommonWealthRobotics/BowlerStudioExampleRobots.git","exampleWalking.groovy"]as String[])
	g.setGitCadEngine(["https://github.com/CommonWealthRobotics/BowlerStudioExampleRobots.git","exampleCad.groovy"]as String[])
	args.put("base", g)
}

MobileBase generated = args.base
for(String key:args.keySet()) {
	//println "Key "+key+" value "+args.get(key)
}

MobileBase RollerBase = new MobileBase();
RollerBase.setGitCadEngine(["https://github.com/CommonWealthRobotics/BowlerStudioExampleRobots.git","exampleCad.groovy"]as String[])
RollerBase.setGitWalkingEngine(["https://github.com/CommonWealthRobotics/BowlerStudioExampleRobots.git","exampleWalking.groovy"]as String[])
RollerBase.setMassKg(0.001)
RollerBase.setScriptingName("RollerBase")
RollerBase.setRobotToFiducialTransform(new TransformNR(-args.diameter/2,0,0))
double increment = 360/args.numRollers
int totalRollers=0;
for(double j=0;j<args.numberOfRows;j++)
for(double i=0;i<args.numRollers;i++) {
	totalRollers++;
	DHParameterKinematics rollerLimb = new DHParameterKinematics();
	rollerLimb.setGitCadEngine(["https://github.com/CommonWealthRobotics/BowlerStudioExampleRobots.git","exampleCad.groovy"]as String[])
    rollerLimb.setGitDhEngine(["https://github.com/CommonWealthRobotics/BowlerStudioExampleRobots.git","exampleKinematics.groovy"]as String[])
    
	String _roller_TotalRollers = args.wheelName+"_roller_"+totalRollers
	println "Adding Limb "+_roller_TotalRollers
	rollerLimb.setScriptingName(_roller_TotalRollers)
	double rotationAngle =(increment)*i +(j*increment/2)
	TransformNR rotation = new TransformNR(0,0,0,RotationNR.getRotationZ(rotationAngle))
	TransformNR displacemtn = new TransformNR(args.diameter/2,0,args.rollerDiameter/2 +((j*args.HubHeight) - j*args.rollerDiameter))
	TransformNR rollerLocation = rotation
				.times(displacemtn)
				.times(new TransformNR(0,0,0,RotationNR.getRotationX(90)))
	
	rollerLimb.setRobotToFiducialTransform(rollerLocation)
	
	LinkConfiguration rollerConfig = new LinkConfiguration();
	rollerConfig.setPassive(true)
	VitaminLocation rollerShaft = new VitaminLocation("electroMechanical",
			"steelPin",
			"2x10mm",
			new TransformNR())
	VitaminLocation roller = new VitaminLocation("shaft",
		"omniWheelRoller",
		"4inch13Roller",
		new TransformNR())
	
	rollerConfig.setUpperVelocity(1200)
	rollerConfig.setDeviceScriptingName("rollers")
	rollerShaft.setFrame(previousLinkTip)
	roller.setFrame(LinkOrigin)
	rollerConfig.addVitamin(rollerShaft)
	rollerConfig.addVitamin(roller)
	
	rollerConfig.setName(_roller_TotalRollers)
	
	DHLink dhRoller = new DHLink(0, 0, 0, 0);
	dhRoller.setListener(new Affine());
	rollerLimb.addNewLink(rollerConfig, dhRoller)
	RollerBase.getDrivable().add(rollerLimb);
}

// Create the limb for the wheel
DHParameterKinematics wheelLimb = new DHParameterKinematics();
wheelLimb.setGitCadEngine(["https://github.com/CommonWealthRobotics/BowlerStudioExampleRobots.git","exampleCad.groovy"]as String[])
wheelLimb.setGitDhEngine(["https://github.com/CommonWealthRobotics/BowlerStudioExampleRobots.git","exampleKinematics.groovy"]as String[])
wheelLimb.setScriptingName(args.wheelName)
wheelLimb.setRobotToFiducialTransform(new TransformNR(0,100,0,new RotationNR(0,90,90)))

generated.getDrivable().add(wheelLimb);

LinkConfiguration wheelConfig = new LinkConfiguration();
wheelConfig.setName(args.wheelName+"_hub")
VitaminLocation local = new VitaminLocation("electroMechanical",
		args.electroMechanicalType.toString(),
		args.electroMechanicalSize.toString(),
		new TransformNR())
VitaminLocation local2 = new VitaminLocation("shaft",
	args.shaftType.toString(),
	args.shaftSize.toString(),
	new TransformNR())
wheelConfig.setDeviceTheoreticalMax(Integer.MAX_VALUE-1)
wheelConfig.setDeviceTheoreticalMin(-Integer.MAX_VALUE+1)

wheelConfig.setUpperVelocity(1200)
wheelConfig.setDeviceScriptingName("virtualDev")
local.setFrame(previousLinkTip)
local2.setFrame(LinkOrigin)
wheelConfig.addVitamin(local)
wheelConfig.addVitamin(local2)
	  
DHLink dh = new DHLink(0, 0, args.diameter/2, 0);
dh.setListener(new Affine());
dh.setSlaveMobileBase(RollerBase)

wheelLimb.addNewLink(wheelConfig, dh)

return generated