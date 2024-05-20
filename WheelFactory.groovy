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

MobileBase generated = new MobileBase();
generated.setMassKg(0.001)

// Create the limb for the wheel
DHParameterKinematics wheelLimb = new DHParameterKinematics();
generated.getDrivable().add(wheelLimb);

LinkConfiguration wheelConfig = new LinkConfiguration();
wheelConfig.addVitamin(new VitaminLocation("electroMechanical", 
											args.electroMechanicalType.toString(),
											args.electroMechanicalSize.toString(),
											  new TransformNR()))
wheelConfig.addVitamin(new VitaminLocation("shaft",
	args.shaftType.toString(),
	args.shaftSize.toString(),
	  new TransformNR()))
	  
DHLink dh = new DHLink(args.diameter/2, 0, 0, 0);
dh.setListener(new Affine());
wheelLimb.addNewLink(wheelConfig, dh)

return generated