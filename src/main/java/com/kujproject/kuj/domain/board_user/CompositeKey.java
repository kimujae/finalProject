package com.kujproject.kuj.domain.board_user;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CompositeKey implements Serializable {
    private Long boardId;
    private String userId;
}
