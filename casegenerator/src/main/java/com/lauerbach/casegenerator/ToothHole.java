package com.lauerbach.casegenerator;
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


import java.util.ArrayList;
import java.util.List;

public class ToothHole {
	double pos;
	double width;

	public ToothHole(double pos, double width) {
		this.pos = pos;
		this.width = width;
	}

	public static List<ToothHole> calculate(double toothWidth, double length) {
		List<ToothHole> l = new ArrayList<>();

		int count = (int) (length / toothWidth);
		if (count % 2 == 0) {
			count--;
		}

		double gap = (length - (toothWidth * count)) / 2;

		for (int i = 1; i < count; i = i + 2) {
			l.add(new ToothHole(gap + i * toothWidth, toothWidth));
		}
		return l;
	}

}
