package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Size {
    public int width;
    public int height;
    public int depth;

	public static Size parseSize(String csv) {
		csv = csv.trim();
		csv = csv.substring(1, csv.length()-1);
		var dimentionsString = csv.split(",");
		int[] dimentions = new int[3];
		for (int i = 0; i< 3; i++) {
			dimentions[i] = Integer.parseInt(dimentionsString[i]);
		}
		return new Size(dimentions[0],dimentions[1],dimentions[2]);
	}

	@Override
	public String toString(){
		return String.format("[%d,%d,%d]", this.width,this.height,this.depth);
	}
}
