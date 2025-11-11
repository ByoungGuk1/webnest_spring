package com.app.webnest.service;

import com.app.webnest.domain.dto.PostSearchDTO;
import com.app.webnest.domain.vo.QuizVO;
import com.app.webnest.domain.vo.UserVO;
import com.app.webnest.repository.SearchDAO;
import com.app.webnest.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@ToString
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final SearchDAO searchDAO;

    @Override
    public List<PostSearchDTO> getOpenPostBySearchQuery(String searchQuery) {
        List<PostSearchDTO> foundOpenPosts = searchDAO.findSearchOpenPosts(searchQuery);
        if(foundOpenPosts.size() == 0){
            return new ArrayList<>();
        }
        return foundOpenPosts;
    }

    @Override
    public List<PostSearchDTO> getQuestionPostBySearchQuery(String searchQuery) {
        List<PostSearchDTO> foundQuestionPosts = searchDAO.findSearchQuestionPosts(searchQuery);
        if(foundQuestionPosts.size() == 0){
            return new ArrayList<>();
        }
        return foundQuestionPosts;
    }

    @Override
    public List<QuizVO> getQuizBySearchQuery(String searchQuery) {
        List<QuizVO> foundQuizzes = searchDAO.findSearchQuizzes(searchQuery);
        if(foundQuizzes.size() == 0){
            return new ArrayList<>();
        }
        return foundQuizzes;
    }

    @Override
    public List<UserVO> getUserBySearchQuery(String searchQuery) {
        List<UserVO> foundUsers = searchDAO.findSearchUsers(searchQuery);
        if(foundUsers.size() == 0){
            return new ArrayList<>();
        }
        return foundUsers;
    }
}
