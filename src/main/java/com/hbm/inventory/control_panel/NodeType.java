package com.hbm.inventory.control_panel;

public enum NodeType {
	//Input for the system, such as the in variables for the event or a single value
	INPUT(new float[]{0, 1, 0}),
	//Intermediate node for math. Has a drop down menu for operations like add, multiply, sin, abs, etc.
	MATH(new float[]{1, 1, 0}),
	//Intermediate nodes for logic such as for loops and if statements. Each contains child node systems, which possibly produce outputs.
	LOGIC(new float[]{0, 0, 1}),
	//The outputs that get evaluated, such as broadcasting a new event or setting an arbitrary variable
	OUTPUT(new float[]{1, 0, 0});
	
	private final float[] color;
	
	NodeType(float[] color){
		this.color = color;
	}
	
	public float[] getColor(){
		return color;
	}
}
