package ua.lpnu.students.labs.decoration.model.shared;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Class which hold 3 dimesions size of an object.
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Size {
    public int width;
    public int height;
    public int depth;

    /**
     * Parse string to `Size`.
     * Using "[%d,%d,%d]" pattern
     *
     * @param string given string
     * @return result Size
     */
    public static Size parseSize(String string) {
        string = string.trim();
        string = string.substring(1, string.length() - 1);
        var dimentionsString = string.split("x");
        int[] dimentions = new int[3];
        for (int i = 0; i < 3; i++) {
            dimentions[i] = Integer.parseInt(dimentionsString[i]);
        }
        return new Size(dimentions[0], dimentions[1], dimentions[2]);
    }

    @Override
    public String toString() {
        return String.format("[%dx%dx%d]", this.width, this.height, this.depth);
    }
}
