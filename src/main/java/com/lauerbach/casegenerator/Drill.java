package com.lauerbach.casegenerator;
/**
 * Copyright 2016 Thomas Lauerbach
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


import com.lauerbach.casegenerator.svg.UnitCalculator;
import com.lauerbach.casegenerator.svg.UnitType;

public class Drill {
	double width;
	UnitType unit;

	String color = "black";

	public Drill(double width, UnitType unit, String color) {
		this(width, unit);
		this.color = color;
	}

	public Drill(double widthPX, String color) {
		this.color = color;
		this.width = widthPX;
	}

	public Drill(double width, UnitType unit) {
		this.width = UnitCalculator.getPX(width, unit);
		this.unit = unit;
	}

	public double getDiameterPX() {
		return width;
	}

	public double getRadius() {
		return width / 2;
	}

	public String getColor() {
		return color;
	}

	static Drill getDebugDrill() {
		return new Drill( 0.1, "green");
	}

	public String getStroke() {
		return width+"";
	}
	
}
