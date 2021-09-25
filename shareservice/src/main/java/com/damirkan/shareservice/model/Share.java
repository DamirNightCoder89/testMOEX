package com.damirkan.shareservice.model;

import org.springframework.lang.NonNull;


public class Share{

    private String boardId;
    private String ticker;
    private String lastPrice;

    public Share() {
    }

    public Share(String boardId, String ticker, String lastPrice) {
        this.boardId = boardId;
        this.ticker = ticker;
        this.lastPrice = lastPrice;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }
}
