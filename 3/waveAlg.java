import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class waveAlg {

    public static void main(String[] args) {

        int[][] field = new int[10][10];
        WaveGuide waveGuide = new WaveGuide(field, new Point(9,0), new Point(0,5));
        waveGuide.setWalls(
                new Point(2,0),
                new Point(2,1),
                new Point(2,2),
                new Point(1,2),
                new Point(5,1),
                new Point(5,2),
                new Point(5,3),
                new Point(5,4),
                new Point(0,4),
                new Point(1,4),
                new Point(2,4),
                new Point(2,5),
                new Point(2,6),
                new Point(2,7),
                new Point(2,8)
        );
        waveGuide.moveWave();
        waveGuide.getVisualizer().printBoard();
    }
}

//###
class WaveGuide {
    private int[][] field;
    private Visualizer visualizer;
    private Point startPoint;
    private Point stopPoint;
    private List<WaveFrontPoint> currentFrontPoints;
    private List<WaveFrontPoint> lastFront = new LinkedList<>();


    public WaveGuide(int[][] fieldParam, Point startPoint, Point stopPoint) {
        this.field = fieldParam;
        this.visualizer = new Visualizer(field);
        this.startPoint = startPoint;
        this.stopPoint = stopPoint;
        this.visualizer.put(startPoint.y, startPoint.x, -1);
        this.currentFrontPoints = new LinkedList<>();
        currentFrontPoints.add(new WaveFrontPoint(startPoint.x, startPoint.y , 0));

    }

    public void moveWave() {
        do {
            lastFront.clear();
            lastFront = currentFrontPoints.stream()
                    .flatMap(wfp -> moveFront(wfp, (wfp.val + 1), 0, false).stream())
                    .collect(Collectors.toList());
            currentFrontPoints.clear();
            currentFrontPoints.addAll(lastFront);
        }while (lastFront.size() > 0);
        getWay();
        this.visualizer.put(stopPoint.y, stopPoint.x, -1);
    }

    public void getWay() {
        WaveFrontPoint backWavePoint = new WaveFrontPoint(stopPoint.x, stopPoint.y, field[stopPoint.y][stopPoint.x]);
        do {
            backWavePoint = moveFront(backWavePoint, -2, backWavePoint.val - 1, true).get(0);
        }while (backWavePoint.val > 1);
    }

    private List<WaveFrontPoint> moveFront(WaveFrontPoint currPoint, int setNewVel, int likeVal, boolean back) {
        List<WaveFrontPoint> ret = new LinkedList<>();
        int curRow = currPoint.y;
        int curClmn = currPoint.x;
        // up
        if (visualizer.isCellValid( curRow + 1, curClmn) && field[curRow + 1][curClmn] == likeVal) {
            field[curRow + 1][curClmn] = setNewVel;
            if (!back)
                ret.add(new WaveFrontPoint(curClmn, curRow + 1, setNewVel));
            else { ret.add(new WaveFrontPoint(curClmn, curRow + 1, likeVal));
                return ret;
            }
        }
        // down
        if (visualizer.isCellValid( curRow - 1, curClmn) && field[curRow - 1][curClmn] == likeVal) {
            field[curRow - 1][curClmn] = setNewVel;
            if (!back)
                ret.add(new WaveFrontPoint(curClmn, curRow - 1, setNewVel));
            else {
                ret.add(new WaveFrontPoint(curClmn, curRow - 1, likeVal));
                return ret;
            }
        }
        // left
        if (visualizer.isCellValid(curRow, curClmn - 1) && field[curRow][curClmn - 1] == likeVal) {
            field[curRow][curClmn - 1] = setNewVel;
            if (!back)
                ret.add(new WaveFrontPoint(curClmn - 1, curRow, setNewVel));
            else { ret.add(new WaveFrontPoint(curClmn - 1, curRow, likeVal));
                return ret;
            }
        }
        // righte
        if (visualizer.isCellValid( curRow, curClmn + 1) && field[curRow][curClmn + 1] == likeVal) {
            field[curRow][curClmn + 1] = setNewVel;
            if (!back)
            ret.add(new WaveFrontPoint(curClmn + 1, curRow, setNewVel));
            else {ret.add(new WaveFrontPoint(curClmn + 1, curRow, likeVal));
                return ret;
            }
        }
        return ret;
    }

    public void setWalls(Point... sellsAsWall) {
        for (Point elWalls : sellsAsWall)
            visualizer.put(elWalls.y, elWalls.x, -3);
    }

    public Visualizer getVisualizer() {
        return visualizer;
    }

    private class WaveFrontPoint {
        int x;
        int y;
        int val;

        public WaveFrontPoint(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
}
//###
class Visualizer {
    private int[][] board;

    public Visualizer(int[][] board) {
        this.board = board;
    }

    public Visualizer(int row, int column) {
        this.board = new int[row][column];
        for (int[] aRow : board)
            Arrays.fill(aRow, 0);
    }

    public boolean put(int row, int column, int playerMarker) {
        boolean retVal = false;
        if (isCellValid(row, column)) {
            board[row][column] = playerMarker;
            retVal = true;
        }
        return retVal;
    }

    public boolean isCellValid(int row, int column) {
        return (row >= 0 && row < board.length) && (column >= 0 && column < board[0].length);
    }

    public void printBoard() {
        printHeader();
        int rowCounter = -1;
        for (int[] ints : board) {
            if (rowCounter++ < 9)
                System.out.print("  " + rowCounter +"  ");
            else System.out.print("  " + rowCounter +" ");
            for (int j = 0; j < board[0].length; j++) {
                if (ints[j] > -1)
                    System.out.print("|    ");
                else if (ints[j] == -1)
                    System.out.print("| ╧  ");
                else if (ints[j] == -2)
                    System.out.print("| ░  ");
                else if (ints[j] == -3)
                    System.out.print("| █  ");
            }
            System.out.print("|\n" + getLineSeparator('-', (board[0].length + 1) * 5) + '\n');
        }
        System.out.println(getLineSeparator('=', (board[0].length + 1) * 5));
    }

    private void printHeader() {
        System.out.print("     ");
        for (int i = 0; i < board[0].length; i++) {
            if (i < 10)
                System.out.print("| " + i +"  ");
            else System.out.print("| " + i +" ");
        }
        System.out.print("|\n" + getLineSeparator('-', (board[0].length + 1) * 5) + '\n');
    }

    private String getLineSeparator(char chr, int length) {
        StringBuilder builder = new StringBuilder();
        while (length-- > -1)
            builder.append(chr);
        return builder.toString();
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
}