package casegenerator;

import java.io.File;

import org.junit.Test;

import com.lauerbach.casegenerator.Cardbox;
import com.lauerbach.casegenerator.Drill;
import com.lauerbach.casegenerator.Material;
import com.lauerbach.casegenerator.Papersize;
import com.lauerbach.casegenerator.panel.Panel;
import com.lauerbach.casegenerator.svg.UnitType;

public class TestCardbox {

	@Test
	public void test() {
		Panel.debugPanelResult = false;
		Panel.debugPanelOutline = false;

		Drill drill = new Drill(0.2, UnitType.MM, "red");
		Drill decoDrill = new Drill(0.2, UnitType.MM, "blue");
		
		Cardbox box= new Cardbox( Papersize.A8, 250, new Material(3, UnitType.MM));
		box.generate( drill, decoDrill).write( new File("results/cardbox.svg"), true);

	}

}
