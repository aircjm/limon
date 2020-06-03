package com.aircjm.project.card.service.impl;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Member;
import com.aircjm.project.card.service.BoardService;
import com.aircjm.project.card.vo.request.GetBoardRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Resource
    private Trello trello;

    @Override
    public List<Board> getBoardList(GetBoardRequest request) {
        Member member = trello.getMemberInformation("chenjiaming");
        // 需要添加参数
        return trello.getMemberBoards(member.getId());
    }

    @Override
    public Board getBoardDetail(GetBoardRequest request) {
        return null;
    }
}
