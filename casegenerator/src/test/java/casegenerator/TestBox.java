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
import com.lauerbach.casegenerator.panel.Panel;
import com.lauerbach.casegenerator.svg.UnitType;

public class TestBox {

	@Test
	public void test() {
		Panel.debugPanelResult= true;
		Panel.debugPanelOutline= true;
		
		Drill drill= new Drill(1, UnitType.MM, "red");
		Box box= new Box( 200, 100, 50, UnitType.MM, new Material(3),true);
		box.generate( drill).write( new File("results/test.svg"), true);
	}

}
