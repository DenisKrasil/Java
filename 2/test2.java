import java.util.Arrays;

public class test2 {
    public static void main(String[] args) {
        int[] array = {3, 1, 11, 7, 4, 22, 15, 9};
System.out.println(Arrays.toString(array));
for (int left = 0; left < array.length; left++) {
	int value = array[left];
	int i = left - 1;
	for (; i >= 0; i--) {
		if (value < array[i]) {
			array[i + 1] = array[i];
		} else {
			break;
		}
	}
	array[i + 1] = value;
}
System.out.println(Arrays.toString(array));
    }
}
