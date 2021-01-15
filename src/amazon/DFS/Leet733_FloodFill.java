package amazon.DFS;

public class Leet733_FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        /*
        start from <sr, sc>, change all reachable 1 to new Color
        */
        int color = image[sr][sc];
        dfs(image, sr, sc, newColor, color);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int newColor, int color) {
        if (sr < 0 || sr == image.length || sc < 0 || sc == image[0].length || image[sr][sc] == newColor || image[sr][sc] != color) {
            return;
        }
        image[sr][sc] = newColor;
        dfs(image, sr + 1, sc, newColor, color);
        dfs(image, sr - 1, sc, newColor, color);
        dfs(image, sr, sc + 1, newColor, color);
        dfs(image, sr, sc - 1, newColor, color);
    }
}
