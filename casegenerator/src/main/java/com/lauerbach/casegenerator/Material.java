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

public class Material {

	double   thickness; // px
	UnitType unit;

	public Material() {
	}

	public Material(double value) {
		this.thickness= value;
	}

	public Material(double value, UnitType unit) {
		this.thickness= value;
		this.unit= unit;
	}
	
	public double getPxThickness() {
		return UnitCalculator.getPX( thickness, unit);
	}
	
	public double getUnitThickness() {
		return thickness;
	}
	
}
