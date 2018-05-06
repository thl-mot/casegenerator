package com.lauerbach.casegenerator;

import com.lauerbach.casegenerator.svg.Svg;

public interface SvgGenerator {
	public Svg generate(Drill drill, Drill decoDrill);
}
