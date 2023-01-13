public class Main {
    public static void main(String[] args) {
        int[][] array = {
                {1, 1, 0, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 1, 0, 1},
                {0, 1, 1, 0, 1, 1, 1, 1},
                {1, 1, 0, 1, 1, 1, 0, 1},
                {1, 1, 1, 0, 1, 1, 1, 0},
                {1, 1, 0, 0, 1, 1, 0, 1},
        };
        int[] start = {1, 4};
        int[] end = {5, 2};
        System.out.println(shortestWay(array, start, end));
    }


    public static int shortestWay(int[][] array, int[] start, int[] end)
    {

        int a = array.length;
        int b = array[0].length;
        boolean[][] visited = new boolean[a][b];

        for (int i = 0; i < a; i++)
        {
            for (int j = 0; j < b; j++)
            {
                if (array[i][j] == 0) visited[i][j] = true;
                else
                    visited[i][j] = false;
            }
        }

        return checkCell(array, start, end, visited, Integer.MAX_VALUE, 0);
    }

    private static boolean Movable(int[][] array, int x, int y, boolean[][] visited)
    {
        if (x >= 0 && x < array.length && y >= 0 && y < array[0].length && array[x][y] == 1 && !visited[x][y]) return true;
        return false;
    }

    private static int checkCell(int[][] array, int[] start, int[] end, boolean[][] visited, int shortest, int distance)
    {
        int a = start[0], A = start[1];
        int b = end[0], B = end[1];

        if (array[a][A] == 0 || array[b][B] == 0 || !Movable(array, a, A, visited)) return Integer.MAX_VALUE;

        if (a == b && A == B) return Math.min(distance, shortest);

        visited[a][A] = true;

        if (Movable(array, a - 1, A, visited)) shortest = checkCell(array, new int[]{a - 1, A}, end, visited, shortest, distance + 1);
        if (Movable(array, a + 1, A, visited)) shortest = checkCell(array, new int[]{a + 1, A}, end, visited, shortest, distance + 1);
        if (Movable(array, a, A - 1, visited)) shortest = checkCell(array, new int[]{a, A - 1}, end, visited, shortest, distance + 1);
        if (Movable(array, a, A + 1, visited)) shortest = checkCell(array, new int[]{a, A + 1}, end, visited, shortest, distance + 1);

        visited[a][A] = false;
        return shortest;
    }
}