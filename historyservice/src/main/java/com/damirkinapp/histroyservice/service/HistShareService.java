package com.damirkinapp.histroyservice.service;

import com.damirkinapp.histroyservice.model.HistShares;

import java.util.Optional;

public interface HistShareService {
    Optional<HistShares> getHistShares(String id, String dateFrom, String dateTo);
}
