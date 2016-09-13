package casegenerator;

import java.io.File;

import org.junit.Test;

import com.lauerbach.casegenerator.CollectablesBoard;
import com.lauerbach.casegenerator.Drill;
import com.lauerbach.casegenerator.Material;
import com.lauerbach.casegenerator.panel.Panel;
import com.lauerbach.casegenerator.svg.UnitType;

public class TestCollectablesBoard {

	@Test
	public void test() {
		Panel.debugPanelOutline= true;
		Panel.debugPanelResult= true;
		
		Drill drill = new Drill(0.18, UnitType.MM, "red");
		Drill decorationDrill = new Drill(0.18, UnitType.MM, "blue");
		
		Material material= new Material( 5, UnitType.MM);
		
		CollectablesBoard board = new CollectablesBoard(300, 60*4+5, 30, 3, UnitType.MM, material, material);
		board.generate(drill, decorationDrill).write(new File("lego-regal.svg"), true);
	}

}
