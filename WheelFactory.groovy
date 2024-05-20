import static com.neuronrobotics.sdk.addons.kinematics.VitaminFrame.LinkOrigin
import static com.neuronrobotics.sdk.addons.kinematics.VitaminFrame.previousLinkTip

import com.neuronrobotics.sdk.addons.kinematics.AbstractLink
import com.neuronrobotics.sdk.addons.kinematics.DHLink
import com.neuronrobotics.sdk.addons.kinematics.DHParameterKinematics
import com.neuronrobotics.sdk.addons.kinematics.LinkConfiguration
import com.neuronrobotics.sdk.addons.kinematics.MobileBase
import com.neuronrobotics.sdk.addons.kinematics.VitaminLocation
import com.neuronrobotics.sdk.addons.kinematics.math.TransformNR

import javafx.scene.transform.Affine
if(args==null) {
	args= new HashMap<String,Object>()
	args.put("diameter",101.6)
	args.put("numRollers",13)
	args.put("HubHeight",24)
	args.put("rollerHeight", 12.5)
	args.put("rollerDiameter",8)
	args.put("numberOfRows",2)
	args.put("electroMechanicalType","vexMotor")
	args.put("electroMechanicalSize","v5_11w")
	args.put("shaftType","vexWheels")
	args.put("shaftSize","4InchOmni")
}
for(String key:args.keySet()) {
	println "Key "+key+" value "+args.get(key)
}

MobileBase RollerBase = new MobileBase();
RollerBase.setMassKg(0.001)
RollerBase.setScriptingName("RollerBase")
MobileBase generated = new MobileBase();
generated.setMassKg(0.001)
generated.setScriptingName("OmniWheelGenerated")
// Create the limb for the wheel
DHParameterKinematics wheelLimb = new DHParameterKinematics();
generated.getDrivable().add(wheelLimb);

LinkConfiguration wheelConfig = new LinkConfiguration();

VitaminLocation local = new VitaminLocation("electroMechanical",
		args.electroMechanicalType.toString(),
		args.electroMechanicalSize.toString(),
		new TransformNR())
VitaminLocation local2 = new VitaminLocation("shaft",
	args.shaftType.toString(),
	args.shaftSize.toString(),
	new TransformNR())

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