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


args.put("diameter",82.55)
args.put("numRollers",11)
args.put("shaftSize","3_75InchOmni")
three = ScriptingEngine.gitScriptRun("https://github.com/CommonWealthRobotics/HolonomicWheels.git", "WheelFactory.groovy",[args])


args.put("diameter",101.6)
args.put("numRollers",13)
args.put("shaftSize","4InchOmni")
four = ScriptingEngine.gitScriptRun("https://github.com/CommonWealthRobotics/HolonomicWheels.git", "WheelFactory.groovy",[args])

return four
