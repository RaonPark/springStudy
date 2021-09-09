package com.ssuzalal.project.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementVo {
    @JsonProperty("announcedType")
    @NotNull
    public String announcedType;

    @JsonProperty("announcedDate")
    @NotNull
    public String announcedDate;

    @JsonProperty("title")
    @NotNull
    public String title;

    @JsonProperty("link")
    @NotNull
    public String link;

    @JsonProperty("isCompleted")
    @NotNull
    public boolean isCompleted;
}
