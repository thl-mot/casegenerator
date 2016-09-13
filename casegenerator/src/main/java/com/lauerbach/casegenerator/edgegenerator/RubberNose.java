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

import java.util.List;

import com.lauerbach.casegenerator.Material;
import com.lauerbach.casegenerator.edgegenerator.TemplatePoint.Position;
import com.lauerbach.casegenerator.svg.UnitCalculator;
import com.lauerbach.casegenerator.svg.UnitType;

public class RubberNose implements NoseGenerator {

	double length; // px
	double thickness; // px

	double rubberStrength;
	double savety;
	double trapez;

	public RubberNose(double noseLength, double thicknessPX, double rubberStrength) {
		this.length = noseLength;
		this.thickness = thicknessPX;
		this.rubberStrength = rubberStrength;
		this.trapez = rubberStrength * 0.2;
	}

	public RubberNose(double noseLength, double rubberStrength, UnitType unit, Material material) {
		this.length = UnitCalculator.getPX(noseLength, unit);
		this.thickness = material.getThickness();
		this.rubberStrength = UnitCalculator.getPX(rubberStrength, unit);
		this.savety = this.rubberStrength;
		this.trapez = this.rubberStrength * 0.2;
	}

	@Override
	public void addNose(List<EdgeTemplateElement> list, double pos, int noseIdx, int noseCount) {
		list.add(new TemplatePoint(pos, thickness, Position.NW));
		list.add(new TemplatePoint(pos, 0, Position.NW));

		if (noseIdx == 1 || noseIdx == noseCount) {
			list.add(new TemplatePoint(pos + rubberStrength - trapez, 0 - trapez, Position.NW));
			list.add(new TemplatePoint(pos + rubberStrength - trapez, -rubberStrength + trapez, Position.SW));
			list.add(new TemplatePoint(pos, -rubberStrength, Position.SW));
			list.add(new TemplatePoint(pos, -rubberStrength - savety, Position.NW));

			list.add(new TemplatePoint(pos + length, -rubberStrength - savety, Position.NE));
			list.add(new TemplatePoint(pos + length, -rubberStrength, Position.SE));
			list.add(new TemplatePoint(pos + length - rubberStrength + trapez, -rubberStrength + trapez, Position.SE));
			list.add(new TemplatePoint(pos + length - rubberStrength + trapez, 0 - trapez, Position.NE));
		}

		list.add(new TemplatePoint(pos + length, 0, Position.NE));
		list.add(new TemplatePoint(pos + length, thickness, Position.NE));
	}

	@Override
	public void addStart(List<EdgeTemplateElement> list, double pos) {
		list.add(new TemplatePoint(pos, thickness, Position.NW));
	}

	@Override
	public void addEnd(List<EdgeTemplateElement> list, double pos) {
		list.add(new TemplatePoint(pos, thickness, Position.NE));
	}

}
