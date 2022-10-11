package com.example.dpms_research_sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WT_Service {
    @Autowired
    private WTRepository wtrepo;
    public Page<WT> findlist(int pageNum, User user) {
        int pageSize = 5;

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        return wtrepo.WsearchList(user,pageable);
    }

    public WT wget(long id) {
        return wtrepo.findById(id).get();
    }

    public void wdelete(long id) {
        wtrepo.deleteById(id);
    }

    public void wsave(WT wt) {
        wtrepo.save(wt);
    }


    public List<WT> wlistAll(User username) {

        return wtrepo.wsearch(username);
    }
}
