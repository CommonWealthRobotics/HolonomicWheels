import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine

HashMap<String,Object> args= new HashMap<String,Object>()

args.put("HubHeight",24)
args.put("rollerHeight", 12.5)
args.put("rollerDiameter",8)
args.put("numberOfRows",2)
args.put("electroMechanicalType","vexMotor")
args.put("electroMechanicalSize","v5_11w")
args.put("shaftType","vexWheels")
args.put("wheelName", "omniWheel")


args.put("diameter",69.85)
args.put("numRollers",9)
args.put("shaftSize","2_75InchOmni")
two = ScriptingEngine.gitScriptRun("https://github.com/CommonWealthRobotics/HolonomicWheels.git", "WheelFactory.groovy",[args])


return null
