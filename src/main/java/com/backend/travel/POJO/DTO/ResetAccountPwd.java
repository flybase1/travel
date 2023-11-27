package com.backend.travel.POJO.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResetAccountPwd implements Serializable {
    private static final long serialVersionUID = -1577346140074090269L;
    private Long accountId;
    private String oldPwd;
    private String newPwd;
    private String confirmPwd;

}
