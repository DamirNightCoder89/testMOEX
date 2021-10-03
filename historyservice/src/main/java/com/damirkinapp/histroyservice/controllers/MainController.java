package com.damirkinapp.histroyservice.controllers;

import com.damirkinapp.histroyservice.exeptions.ShareNotFoundException;
import com.damirkinapp.histroyservice.model.HistShare;
import com.damirkinapp.histroyservice.model.HistShares;
import com.damirkinapp.histroyservice.model.ResponseOfHistShares;
import com.damirkinapp.histroyservice.service.HistShareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Arrays;


@RestController
@Slf4j
public class MainController {

    @Autowired
    private HistShareService histShareService;

    @GetMapping("/histshares/{id}/{dateFrom}/{dateTo}")
    public EntityModel<ResponseOfHistShares> sharesHistory(@PathVariable String id,
                                                           @PathVariable String dateFrom,
                                                           @PathVariable String dateTo) throws NoSuchMethodException {
        log.info("this endpoint work");
        HistShares histShares = histShareService.getHistShares(id, dateFrom, dateTo)
                .orElseThrow(() -> new ShareNotFoundException(id));

        Link selfLink = WebMvcLinkBuilder.linkTo(MainController.class)
                .slash("histshares").slash(id).slash(dateFrom).slash(dateTo)
                .withSelfRel();

        return  EntityModel.of(new ResponseOfHistShares(histShares),
                selfLink,
                WebMvcLinkBuilder.linkTo(MainController.class)
                        .slash("shares").withRel("shares"));
    }

}







