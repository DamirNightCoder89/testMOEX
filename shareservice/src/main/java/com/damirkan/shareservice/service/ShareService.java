package com.damirkan.shareservice.service;

import com.damirkan.shareservice.model.Share;
import com.damirkan.shareservice.model.Shares;

import java.util.Optional;

public interface ShareService {


    Optional<Shares> findAll();

    Optional<Share> getShare(String id);
}
