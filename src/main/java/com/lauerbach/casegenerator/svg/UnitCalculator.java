package com.lauerbach.casegenerator.svg;
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


public class UnitCalculator {

	private UnitType unitType;

	public UnitCalculator(UnitType unitType) {
		this.unitType = unitType;
	}

	public double getPX(double v) {
		return getPX(v, this.unitType);
	}

	public static double getPX(double v, UnitType unitType) {
		if (unitType==null) {
			return v;
		}
		switch (unitType) {
		case PX:
			return v;
		case PT:
			return v * 1.25;
		case PICA:
			return v * 15;
		case MM:
			return v * 3.543307;
		case CM:
			return v * 35.43307;
		case INCH90:
			return v * 90;
		case INCH96:
			return v * 96;
		}
		return 0;
	}
}
