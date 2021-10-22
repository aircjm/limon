package com.aircjm.limon.business.card.service.impl;

import com.aircjm.limon.business.card.service.BoardService;
import com.aircjm.limon.business.card.vo.request.GetBoardRequest;
import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Resource
    private Trello trello;

    @Override
    public List<Board> getBoardList(GetBoardRequest request) {
        Member member = trello.getMemberInformation("测试用户");
        // 需要添加参数
        return trello.getMemberBoards(member.getId());
    }

    @Override
    public Board getBoardDetail(GetBoardRequest request) {
        return null;
    }
}
