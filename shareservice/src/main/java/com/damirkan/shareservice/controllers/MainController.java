package com.damirkan.shareservice.controllers;

import com.damirkan.shareservice.exeptions.ShareNotFoundException;
import com.damirkan.shareservice.model.Share;
import com.damirkan.shareservice.model.Shares;
import com.damirkan.shareservice.service.ShareService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.core.LastInvocationAware;
import org.springframework.hateoas.server.core.MethodInvocation;
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;


@RestController
public class MainController implements LastInvocationAware {

    @Override
    public Iterator<Object> getObjectParameters() {
        return null;
    }

    @Override
    public MethodInvocation getLastInvocation() {
        return null;
    }

    private  final ShareService shareService;

    public MainController(ShareService shareService) {
        this.shareService = shareService;
    }

    @GetMapping("/shares/{id}")
    public Shares retrive(@PathVariable String id) {
        Shares shares = shareService.getShares(id)
                .orElseThrow(() -> new ShareNotFoundException(id));

        return shares;
    }

    @GetMapping("/shares")
    public List<EntityModel<Share>> retrivee() {
        Shares shares = shareService.findAll()
                .orElseThrow(() -> new ShareNotFoundException());

        List<EntityModel<Share>> shares_list = shares.getData().stream().map(share -> EntityModel.of(share,
                        WebFluxLinkBuilder.linkTo(MethodInvocation.class).withSelfRel().toMono().block()
                ))
                .collect(Collectors.toList());

        return shares_list;
    }
}







