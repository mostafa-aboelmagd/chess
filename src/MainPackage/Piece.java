package MainPackage;

import java.util.ArrayList;

public abstract class Piece {
    protected ChessBoard Board;
    protected boolean pieceSide;
    protected String position;

    //Function provides the new position of a piece
    protected static String move(String pos , int vertical, int horizontal) {
        String posLetter = String.valueOf((char)(pos.charAt(0)+horizontal));
        String posNumber = String.valueOf((char)(pos.charAt(1)+vertical));
        String newPos = posLetter + posNumber;
        if((newPos.charAt(0)<'A') || (newPos.charAt(0)>'H') || (newPos.charAt(1)<'1') || (newPos.charAt(1)>'8'))
            return null;
        return newPos;
    }
}

class bishop extends Piece {
    public bishop(boolean side, String pos, ChessBoard cb) {
        this.Board = cb;
        this.pieceSide = side;
        this.position = pos;
    }

    //This function returns all moves that the bishop can move
    public ArrayList<String> ValidMoves() {
        //This array list will contain all the valid moves
        ArrayList<String> result = new ArrayList<>();
        //Checking for the horizontal move
        String horizontalRight = move(position, 0, 1);
        String horizontalLeft = move(position, 0, -1);
        if ((horizontalRight != null)) {
            result.add(horizontalRight);
        }
        if ((horizontalLeft != null)) {
            result.add(horizontalLeft);
        }
        //Checking for diagonal moves
        for (int i = 1; i <= 3; i++) {
            String firstDiagonalRight = move(position, i, i);
            String firstDiagonalLeft = move(position, -i, -i);
            String secondDiagonalRight = move(position, -i, i);
            String secondDiagonalLeft = move(position, i, -i);
            if (firstDiagonalRight != null) {
                result.add(firstDiagonalRight);
            }
            if (firstDiagonalLeft != null) {
                result.add(firstDiagonalLeft);
            }
            if (secondDiagonalRight != null) {
                result.add(secondDiagonalRight);
            }
            if (secondDiagonalLeft != null) {
                result.add(secondDiagonalLeft);
            }
        }
        return result;
    }

    public void Select() {
        ArrayList<String> availableMoves = ValidMoves();
        for (String move: availableMoves) {
            Board.boardLight(move, true);
        }
    }

    public void Unselect() {
        ArrayList<String> availableMoves = ValidMoves();
        for (String move: availableMoves) {
            Board.boardLight(move, false);
        }
    }
}

class test{
    public static void main(String []args )
    {
        ChessBoard cb = new ChessBoard();
        bishop b = new bishop(true, "A3",cb);
        cb.move_piece("C1","A3");
        bishop c = new bishop(true, "B3", cb);
        cb.move_piece("F1", "B3");
        b.Select();
    }
}


