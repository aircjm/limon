package com.aircjm.limon.business.card.service;

import com.aircjm.limon.business.card.vo.request.GetBoardRequest;
import com.julienvey.trello.domain.Board;

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
