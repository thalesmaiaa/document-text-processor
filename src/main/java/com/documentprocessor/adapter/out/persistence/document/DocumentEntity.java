package com.documentprocessor.adapter.out.persistence.document;

import com.documentprocessor.adapter.out.persistence.user.UserEntity;
import com.documentprocessor.core.domain.DocumentProcessingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "documents")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String url;

    @Enumerated(EnumType.STRING)
    private DocumentProcessingStatus status;

    private String originalName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @LastModifiedDate
    private ZonedDateTime uploadedAt;
}
