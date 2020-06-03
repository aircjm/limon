package com.aircjm.project.card.service;

import com.julienvey.trello.domain.Board;
import com.aircjm.project.card.vo.request.GetBoardRequest;

import java.util.List;

/**
 * 卡片service接口
 *
 * @author aircjm
 */
public interface BoardService {

    /**
     * 获取board集合
     *
     * @param request 请求参数
     * @return boardList
     */
    List<Board> getBoardList(GetBoardRequest request);


    /**
     * 获取单个board
     *
     * @param request 请求参数
     * @return Board
     */
    Board getBoardDetail(GetBoardRequest request);

}
