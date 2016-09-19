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

public class NoseTCut implements NoseGenerator {

	double length; // px
	double thickness; // px
	double screwWidth;
	double screwLength;
	double nutWidth;
	double nutHeight;

	public NoseTCut(double noseLength, UnitType unit, Material material, int screwM, int screwL) {
		this.length = UnitCalculator.getPX(noseLength, unit);
		this.thickness = material.getPxThickness();
		this.screwWidth = UnitCalculator.getPX(screwM, UnitType.MM);
		this.nutWidth = UnitCalculator.getPX(screwM, UnitType.MM) * 2;
		this.nutHeight = UnitCalculator.getPX(screwM, UnitType.MM);
		this.screwLength = UnitCalculator.getPX(screwL, UnitType.MM) - thickness;
	}

	@Override
	public void addNose(List<EdgeTemplateElement> list, double pos, int noseIdx, int noseCount) {
		list.add(new TemplatePoint(pos, (double) 0, Position.NE));
		list.add(new TemplatePoint(pos, thickness, Position.NE));

		if (noseIdx == 1 || noseIdx == noseCount) {

			double center = length / 2;

			double m1 = thickness + nutHeight;
			double m2 = thickness + nutHeight + nutHeight;

			list.add(new TemplatePoint(pos + center - screwWidth / 2, thickness, Position.NE));
			list.add(new TemplatePoint(pos + center - screwWidth / 2, m1, Position.SE));
			list.add(new TemplatePoint(pos + center - nutWidth / 2, m1, Position.SE));
			list.add(new TemplatePoint(pos + center - nutWidth / 2, m2, Position.NE));
			list.add(new TemplatePoint(pos + center - screwWidth / 2, m2, Position.NE));
			list.add(new TemplatePoint(pos + center - screwWidth / 2, screwLength, Position.NE));
			list.add(new TemplatePoint(pos + center + screwWidth / 2, screwLength, Position.NW));
			list.add(new TemplatePoint(pos + center + screwWidth / 2, m2, Position.NW));
			list.add(new TemplatePoint(pos + center + nutWidth / 2, m2, Position.NW));
			list.add(new TemplatePoint(pos + center + nutWidth / 2, m1, Position.SW));
			list.add(new TemplatePoint(pos + center + screwWidth / 2, m1, Position.SW));
			list.add(new TemplatePoint(pos + center + screwWidth / 2, thickness, Position.NW));
		}

		list.add(new TemplatePoint(pos + length, thickness, Position.NW));
		list.add(new TemplatePoint(pos + length, 0, Position.NW));
	}

	@Override
	public void addStart(List<EdgeTemplateElement> list, double pos) {
		list.add(new TemplatePoint(pos, (double) 0, Position.NE));
	}

	@Override
	public void addEnd(List<EdgeTemplateElement> list, double pos) {
		list.add(new TemplatePoint(pos, (double) 0, Position.NW));
	}

}
