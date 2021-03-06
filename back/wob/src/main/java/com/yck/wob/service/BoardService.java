package com.yck.wob.service;

import java.util.List;

import com.yck.wob.dao.BoardDao;
import com.yck.wob.dto.BoardDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    BoardDao boardDao;

    // 게시판 생성 등등등
    /** 
     * status 20~30의 게시판을 가져온다
    */
    public List<BoardDTO> getPermitedBoardList() {
        return boardDao.selectPermitedBoards();
    }
    public List<BoardDTO> getBoardList(){
        return boardDao.selectBoards();
    }
    /**
     * 게시판 생성 요청 
     * @param boardName
     * @param boardDesc
     * @param userNo
     * @return boardStatus가 0인 게시판 정보 생성(허가필요)
     */
    public boolean askToAddingBoard(String boardName, String boardDesc, int userNo){
        BoardDTO board = new BoardDTO();
        board.setBoardName(boardName);
        board.setBoardDesc(boardDesc);
        board.setBoardProposer(userNo);
        board.setBoardStatus(0);
        try{
            boardDao.insertBoard(board);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 허가가 필요한 게시판을 실제 생성 - create 테이블까지 해줌
     * @param boardNo 요청게시판의 boardNo
     * @return
     */
    public boolean createAskedBoard(int boardNo){
        BoardDTO board = new BoardDTO();
        board.setBoardNo(boardNo);
        BoardDTO dbBoard = boardDao.selectBoardByNo(board);
        dbBoard.setBoardStatus(20);
        boardDao.updateBoard(dbBoard);

        boardDao.createMainBoard(dbBoard);
        boardDao.createSubBoard(dbBoard);
        return true;
    }

 /*    public boolean createBoard(String boardName, String boardDesc, int userNo){
        BoardDTO board = new BoardDTO();
        board.setBoardName(boardName);
        board.setBoardDesc(boardDesc);
        board.setBoardProposer(userNo);
        board.setBoardStatus(20);
        try{
            boardDao.insertBoard(board);
            boardDao.createMainBoard(board);
            boardDao.createSubBoard(board);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    } 폐기 이 기능은 위에거 두개로 충당 가능*/

    public BoardDTO getBoardInfo(int boardNo){
        BoardDTO board = new BoardDTO();
        board.setBoardNo(boardNo);
        return boardDao.selectBoardByNo(board);
    }

    public BoardDTO getBoardInfoByName(String boardName){
        BoardDTO board = new BoardDTO();
        board.setBoardName(boardName);
        return boardDao.selectBoardByName(board);
    }




    
    // void requestDelete(){}
}