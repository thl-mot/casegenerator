package casegenerator;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.lauerbach.casegenerator.Drill;
import com.lauerbach.casegenerator.Material;
import com.lauerbach.casegenerator.edgegenerator.EdgeFactory.EdgeType;
import com.lauerbach.casegenerator.panel.MixerBoxSide;
import com.lauerbach.casegenerator.panel.Panel;
import com.lauerbach.casegenerator.svg.Svg;
import com.lauerbach.casegenerator.svg.UnitType;

public class Tobias {

	@Test
	public void test() {
		Panel.debugPanelResult = false;
		Panel.debugPanelOutline = false;

		Drill drill = new Drill(0.2, UnitType.MM, "red");
		Panel pult = new Panel(45, 30, 20, UnitType.MM, EdgeType.NOSE, EdgeType.NOSE, EdgeType.LID, EdgeType.LID,
				new Material(3, UnitType.MM));

		Svg svg = new Svg("300mm", "300mm");
		svg.add(pult.generate(10, 10, UnitType.MM, drill, drill));

		svg.write(new File("tobias.svg"), true);
	}

}
