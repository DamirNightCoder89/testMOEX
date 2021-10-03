package com.damirkan.shareservice.controllers;

import com.damirkan.shareservice.exeptions.ShareNotFoundException;
import com.damirkan.shareservice.model.ResponseOfShare;
import com.damirkan.shareservice.model.ResponseOfShares;
import com.damirkan.shareservice.model.Share;
import com.damirkan.shareservice.model.Shares;
import com.damirkan.shareservice.service.ShareService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.core.LastInvocationAware;
import org.springframework.hateoas.server.core.MethodInvocation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder;
import org.springframework.web.bind.annotation.*;
import reactor.netty.http.client.HttpClient;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/shares")
public class MainController  {

    private  final ShareService shareService;

    public MainController(ShareService shareService) {
        this.shareService = shareService;
    }

    @GetMapping("/{id}")
    public EntityModel<ResponseOfShare> shares(@PathVariable String id) throws NoSuchMethodException {
        Share share = shareService.getShare(id)
                .orElseThrow(() -> new ShareNotFoundException(id));

        Link selfLink = WebMvcLinkBuilder.linkTo(MainController.class, MainController.class.getMethod("shares")).slash(share.getTicker()).withSelfRel();

        return EntityModel.of(new ResponseOfShare(share.add(selfLink)),
                WebMvcLinkBuilder.linkTo(MainController.class, MainController.class.getMethod("shares"))
                        .withRel("shares"),
                WebMvcLinkBuilder.linkTo(MainController.class).slash("histshares")
                        .slash("fromDate").slash("toDate").withRel("historyShares")
                );   // EntityModel.of(shares, link)
    }

    @GetMapping("/")
    public EntityModel<ResponseOfShares> shares() throws NoSuchMethodException {

        Shares shares = shareService.findAll()
                .orElseThrow(() -> new ShareNotFoundException());
        for (Share share : shares.getData()) {
            share.add(WebMvcLinkBuilder.linkTo(MainController.class, MainController.class.getMethod("shares")).slash(share.getTicker()).withSelfRel());
        }

        Link selfLink = WebMvcLinkBuilder.linkTo(MainController.class, MainController.class.getMethod("shares")).withSelfRel();

        return EntityModel.of(new ResponseOfShares(shares.add(selfLink)),
                WebMvcLinkBuilder.linkTo(MainController.class).slash("histshares")
                        .slash("fromDate").slash("toDate").withRel("historyShares"));   // EntityModel.of(shares, link)

    }
}







