package com.monocept.Computer;

public class RuleBasedAI {

    public static int findBestMove(Board board,char ai,char human){

        int win = findWinningMove(board,ai);
        if(win!=-1) return win;

        int block = findWinningMove(board,human);
        if(block!=-1) return block;

        if(board.isCellEmpty(4))
            return 4;

        if(board.isCellEmpty(0)) return 0;
        if(board.isCellEmpty(2)) return 2;
        if(board.isCellEmpty(6)) return 6;
        if(board.isCellEmpty(8)) return 8;

        if(board.isCellEmpty(1)) return 1;
        if(board.isCellEmpty(3)) return 3;
        if(board.isCellEmpty(5)) return 5;
        if(board.isCellEmpty(7)) return 7;

        return -1;
    }

     static int findWinningMove(Board board,char symbol){

        if(board.getCell(0)==symbol && board.getCell(1)==symbol && board.isCellEmpty(2)) return 2;
        if(board.getCell(1)==symbol && board.getCell(2)==symbol && board.isCellEmpty(0)) return 0;
        if(board.getCell(0)==symbol && board.getCell(2)==symbol && board.isCellEmpty(1)) return 1;

        if(board.getCell(3)==symbol && board.getCell(4)==symbol && board.isCellEmpty(5)) return 5;
        if(board.getCell(4)==symbol && board.getCell(5)==symbol && board.isCellEmpty(3)) return 3;
        if(board.getCell(3)==symbol && board.getCell(5)==symbol && board.isCellEmpty(4)) return 4;

        if(board.getCell(6)==symbol && board.getCell(7)==symbol && board.isCellEmpty(8)) return 8;
        if(board.getCell(7)==symbol && board.getCell(8)==symbol && board.isCellEmpty(6)) return 6;
        if(board.getCell(6)==symbol && board.getCell(8)==symbol && board.isCellEmpty(7)) return 7;

        if(board.getCell(0)==symbol && board.getCell(3)==symbol && board.isCellEmpty(6)) return 6;
        if(board.getCell(3)==symbol && board.getCell(6)==symbol && board.isCellEmpty(0)) return 0;
        if(board.getCell(0)==symbol && board.getCell(6)==symbol && board.isCellEmpty(3)) return 3;

        if(board.getCell(1)==symbol && board.getCell(4)==symbol && board.isCellEmpty(7)) return 7;
        if(board.getCell(4)==symbol && board.getCell(7)==symbol && board.isCellEmpty(1)) return 1;
        if(board.getCell(1)==symbol && board.getCell(7)==symbol && board.isCellEmpty(4)) return 4;

        if(board.getCell(2)==symbol && board.getCell(5)==symbol && board.isCellEmpty(8)) return 8;
        if(board.getCell(5)==symbol && board.getCell(8)==symbol && board.isCellEmpty(2)) return 2;
        if(board.getCell(2)==symbol && board.getCell(8)==symbol && board.isCellEmpty(5)) return 5;

        if(board.getCell(0)==symbol && board.getCell(4)==symbol && board.isCellEmpty(8)) return 8;
        if(board.getCell(4)==symbol && board.getCell(8)==symbol && board.isCellEmpty(0)) return 0;
        if(board.getCell(0)==symbol && board.getCell(8)==symbol && board.isCellEmpty(4)) return 4;

        if(board.getCell(2)==symbol && board.getCell(4)==symbol && board.isCellEmpty(6)) return 6;
        if(board.getCell(4)==symbol && board.getCell(6)==symbol && board.isCellEmpty(2)) return 2;
        if(board.getCell(2)==symbol && board.getCell(6)==symbol && board.isCellEmpty(4)) return 4;

        return -1;
    }
}
