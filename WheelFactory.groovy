import com.neuronrobotics.sdk.addons.kinematics.DHLink
import com.neuronrobotics.sdk.addons.kinematics.DHParameterKinematics
import com.neuronrobotics.sdk.addons.kinematics.LinkConfiguration
import com.neuronrobotics.sdk.addons.kinematics.MobileBase

import javafx.scene.transform.Affine
if(args==null) {
	args= new HashMap<String,Double>()
	args.put("diameter",101.6)
	args.put("numRollers",13)
	args.put("HubHeight",24)
	args.put("rollerHeight", 12.5)
	args.put("rollerDiameter",8)
	args.put("numberOfRows",2)
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
DHLink dh = new DHLink(args.diameter/2, 0, 0, 0);
dh.setListener(new Affine());
wheelLimb.addNewLink(wheelConfig, dh)

return generated