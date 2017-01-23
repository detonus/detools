package com.detoworks.dto;

import com.detoworks.model.RegExp;

/**
 * Created by Banach on 2017-01-23.
 */
public class RegExpDto {

    private long id;
    private int subid;
    private String input;
    private String findexp;
    private String replexp;
    private String output;
    private String outputTree;
    private String messages;

    public RegExpDto() {}

    public RegExpDto(RegExp regExp) {
        id = regExp.getId();
        subid = regExp.getSubid();
        input = regExp.getInput();
        findexp = regExp.getFindexp();
        replexp = regExp.getReplexp();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSubid() {
        return subid;
    }

    public void setSubid(int subid) {
        this.subid = subid;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getFindexp() {
        return findexp;
    }

    public void setFindexp(String findexp) {
        this.findexp = findexp;
    }

    public String getReplexp() {
        return replexp;
    }

    public void setReplexp(String replexp) {
        this.replexp = replexp;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getOutputTree() {
        return outputTree;
    }

    public void setOutputTree(String outputTree) {
        this.outputTree = outputTree;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messges) {
        this.messages = messages;
    }

    public RegExpDto setFromModel(RegExp regExp) {
        this.setId(regExp.getId());
        this.setSubid(regExp.getSubid());
        return this;
    }
}
