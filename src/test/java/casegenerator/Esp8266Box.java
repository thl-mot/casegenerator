package casegenerator;
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

import java.io.File;

import org.junit.Test;

import com.lauerbach.casegenerator.Box;
import com.lauerbach.casegenerator.Drill;
import com.lauerbach.casegenerator.Material;
import com.lauerbach.casegenerator.PanelDecorator;
import com.lauerbach.casegenerator.panel.Panel;
import com.lauerbach.casegenerator.svg.Circle;
import com.lauerbach.casegenerator.svg.Group;
import com.lauerbach.casegenerator.svg.Point;
import com.lauerbach.casegenerator.svg.Rectangle;
import com.lauerbach.casegenerator.svg.UnitType;

public class Esp8266Box {

	@Test
	public void test() {
		Panel.debugPanelResult = true;
		Panel.debugPanelOutline = true;

		Drill drill = new Drill(0.2, UnitType.MM, "red");
		Box box = new Box(180, 120, 40, UnitType.MM, new Material(3, UnitType.MM), false);
		box.setBasePanelDecorator(new PanelDecorator() {
			@Override
			public void addDecoration(Group group, Drill decorationDrill) {
				double x=18-15;
				double y=12-9;
				group.add( new Rectangle( new Point(x,y, UnitType.CM), new Point(x+13.7, y+7.9, UnitType.CM), "green",
						decorationDrill.getStroke()));
				x+=0.8;
				y+=0.6;
				group.add(new Circle(new Point(x+12.45, y+0, UnitType.CM), 1.4, UnitType.MM, decorationDrill.getColor(),
						decorationDrill.getStroke()));
				group.add(new Circle(new Point(x+12.45, y+6.85, UnitType.CM), 1.4, UnitType.MM, decorationDrill.getColor(),
						decorationDrill.getStroke()));
				group.add(new Circle(new Point(x+0, y+6.85, UnitType.CM), 1.4, UnitType.MM, decorationDrill.getColor(),
						decorationDrill.getStroke()));
				group.add(new Circle(new Point(x+0, y+0, UnitType.CM), 1.4, UnitType.MM, decorationDrill.getColor(),
						decorationDrill.getStroke()));
			}
		});

		box.generate(drill).write(new File("results/esp8266_hdf3.svg"), true);
	}

}
