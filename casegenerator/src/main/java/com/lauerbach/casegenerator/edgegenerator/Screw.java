package com.lauerbach.casegenerator.edgegenerator;
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

public class Screw {
	double m;
	double length;
	double nutDiameter;
	double nutHeight;

	public Screw(double m, double length, double nutDiameter, double nutHeight) {
		super();
		this.m = UnitCalculator.getPX( m, UnitType.MM);
		this.length = UnitCalculator.getPX(length, UnitType.MM);
		this.nutDiameter = UnitCalculator.getPX(nutDiameter,UnitType.MM);
		this.nutHeight = UnitCalculator.getPX(nutHeight, UnitType.MM);
	}

	public double getM() {
		return m;
	}

	public void setM(double m) {
		this.m = m;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getNutDiameter() {
		return nutDiameter;
	}

	public void setNutDiameter(double nutDiameter) {
		this.nutDiameter = nutDiameter;
	}

	public double getNutHeight() {
		return nutHeight;
	}

	public void setNutHeight(double nutHeight) {
		this.nutHeight = nutHeight;
	}

	public static Screw getScrew(int m, double length) {
		switch (m) {
		case 3:
			return new Screw(m, length, 0, 0);
		case 4:
			return new Screw(m, length, 0, 0);
		}
		return null;
	}

}
