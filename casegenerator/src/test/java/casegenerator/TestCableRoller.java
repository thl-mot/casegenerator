package casegenerator;

import java.io.File;

import org.junit.Test;

import com.lauerbach.casegenerator.CableRoller;
import com.lauerbach.casegenerator.Drill;
import com.lauerbach.casegenerator.Material;
import com.lauerbach.casegenerator.panel.Panel;
import com.lauerbach.casegenerator.svg.UnitType;

public class TestCableRoller {

	@Test
	public void test() {
		Panel.debugPanelResult = false;
		Panel.debugPanelOutline = false;

		Drill drill = new Drill(0.2, UnitType.MM, "red");
		Drill decoDrill = new Drill(0.2, UnitType.MM, "blue");

		CableRoller pult = new CableRoller(30, 30, 70, UnitType.MM, new Material(3, UnitType.MM));
		pult.generate(drill, decoDrill).write(new File("results/cableRoller_30.svg"), false);
		
		pult = new CableRoller(60, 30, 70, UnitType.MM, new Material(3, UnitType.MM));
		pult.generate(drill, decoDrill).write(new File("results/cableRoller_60.svg"), false);

		pult = new CableRoller(80, 30, 70, UnitType.MM, new Material(3, UnitType.MM));
		pult.generate(drill, decoDrill).write(new File("results/cableRoller_80.svg"), false);

	}

}
