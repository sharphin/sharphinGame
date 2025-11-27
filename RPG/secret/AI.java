package secret;

public class AI {
    private int x,y;
    private Game_logic gl;
    public AI(Game_logic gl) {
        this.gl = gl;
    }
    public void coords_calc() {
        int max = 0;
        for(int y = 0;y < GameUtil.MASU;y++) {
            for(int x = 0;x < GameUtil.MASU;x++) {
                if(Game_logic.BOARD[y][x] != 0)continue;
                gl.put_stone(false, x, y);
                int count = gl.sum_flip_count();
                if(max < count) {
                    max = count;
                    this.x = x;
                    this.y = y;
                }
            }
        }
        gl.put_stone(true, x,y);
    }
    public int x() {
        return x;
    }
    public int y(){
        return y;
    }
}
