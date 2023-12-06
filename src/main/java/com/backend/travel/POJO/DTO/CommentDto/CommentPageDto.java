package com.backend.travel.POJO.DTO.CommentDto;

import com.backend.travel.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode( callSuper = true )
@Data
public class CommentPageDto extends PageRequest {
    private Long queryTravelId;
    private Long queryAccountId;
}
