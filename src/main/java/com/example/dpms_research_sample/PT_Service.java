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
public class PT_Service {

    @Autowired
    private PTRepository ptrepo;


    public List<PT> plistAll(User username) {

        return ptrepo.psearch(username);
    }
    public void psave(PT pt) {
        ptrepo.save(pt);
    }

    public PT pget(long id) {
        return ptrepo.findById(id).get();
    }

    public void pdelete(long id) {
        ptrepo.deleteById(id);
    }
    public Page<PT> pfindlist(int pageNum, User user) {
        int pageSize = 5;

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        return ptrepo.psearchList(user,pageable);
    }

}
