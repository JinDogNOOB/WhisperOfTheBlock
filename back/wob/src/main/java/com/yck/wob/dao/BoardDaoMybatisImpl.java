package com.yck.wob.dao;

import java.util.List;

import com.yck.wob.dto.BoardDTO;
import com.yck.wob.dto.PostDTO;
import com.yck.wob.dto.PostSubDTO;
import com.yck.wob.dto.PostSubwUserDTO;
import com.yck.wob.dto.PostwUserDTO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoMybatisImpl implements BoardDao {

    @Autowired
    private SqlSession sqlSession;

        private static final String namespace = "BoardMapper";

       // 게시판인덱스 관리
       /**
        *  모든 보드 가져온다
        */
        @Override
        public List<BoardDTO> selectBoards(){
        return sqlSession.selectList(namespace + ".selectBoards");
       }
       /**
        * boardStatus 20~30 (허가된 게시판) 만 가져온다
        */
       @Override
       public List<BoardDTO> selectPermitedBoards(){
        return sqlSession.selectList(namespace + ".selectPermitedBoards");
       }
       @Override
       public int countBoards(){
        return sqlSession.selectOne(namespace + ".countBoards");
       }
       @Override
       public BoardDTO selectBoardByNo(BoardDTO boardDTO){
        return sqlSession.selectOne(namespace + ".selectBoardByNo", boardDTO);
       }
       @Override
       public BoardDTO selectBoardByName(BoardDTO boardDTO){
        return sqlSession.selectOne(namespace + ".selectBoardByName", boardDTO);
       }
       @Override
       public int insertBoard(BoardDTO boardDTO){
        return sqlSession.insert(namespace + ".insertBoard", boardDTO);
       }
       @Override
       public int updateBoard(BoardDTO boardDTO){
        return sqlSession.update(namespace + ".updateBoard", boardDTO);
       }
       @Override
       public void deleteBoard(BoardDTO boardDTO){
        sqlSession.delete(namespace + ".deleteBoard", boardDTO);
       }

   
       /**
        * 실제 글이 올라갈 테이블 생성
        */
       @Override
       public void createMainBoard(BoardDTO boardDTO){
        sqlSession.update(namespace + ".createMainBoard", boardDTO);
       }
       /**
        * 실제 댓글이 올라갈 테이블 생성
        */
       @Override
       public void createSubBoard(BoardDTO boardDTO){
        sqlSession.update(namespace + ".createSubBoard", boardDTO);
       }
   
       // 게시글
       @Override
       public List<PostwUserDTO> selectPosts(PostDTO postDTO){
        return sqlSession.selectList(namespace + ".selectPosts", postDTO);
       }
       @Override
       public int countPosts(PostDTO postDTO){
        return sqlSession.selectOne(namespace + ".countPosts",postDTO);
       }
       @Override
       public PostwUserDTO selectPostByNo(PostDTO postDTO){
        return sqlSession.selectOne(namespace + ".selectPostByNo",postDTO);
       }
       @Override
       public int insertPost(PostDTO postDTO){
        return sqlSession.insert(namespace + ".insertPost",postDTO);
       }
       @Override
       public int updatePost(PostDTO postDTO){
        return sqlSession.update(namespace + ".updatePost",postDTO);
       }
       @Override
       public void deletePost(PostDTO postDTO){
        sqlSession.delete(namespace + ".deletePost",postDTO);
       }
   
       // 게시글 댓글
       @Override
       public List<PostSubwUserDTO> selectPostSubs(PostSubDTO postSubDTO){
        return sqlSession.selectList(namespace + ".selectPostSubs", postSubDTO);
       }
       @Override
       public int countPostSubs(PostSubDTO postSubDTO){
        return sqlSession.selectOne(namespace + ".countPostSubs", postSubDTO);
       }
       @Override
       public PostSubwUserDTO selectPostSubByNo(PostSubDTO postSubDTO){
        return sqlSession.selectOne(namespace + ".selectPostSubByNo", postSubDTO);
       }
       @Override
       public int insertPostSub(PostSubDTO postSubDTO){
        return sqlSession.insert(namespace + ".insertPostSub", postSubDTO);
       }
       @Override
       public int updatePostSub(PostSubDTO postSubDTO){
        return sqlSession.update(namespace + ".updatePostSub", postSubDTO);
       }
       @Override
       public void deletePostSub(PostSubDTO postSubDTO){
        sqlSession.delete(namespace + ".deletePostSub", postSubDTO);
       }
    
}