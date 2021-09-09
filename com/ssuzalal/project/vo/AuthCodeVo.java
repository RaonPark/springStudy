package com.ssuzalal.project.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record AuthCodeVo(
        @JsonProperty("email") String email,
        @JsonProperty("code") String code
) implements Serializable
{ }
