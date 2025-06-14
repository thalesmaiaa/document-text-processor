package com.documentprocessor.adapter.out.persistence;

import com.documentprocessor.adapter.out.persistence.document.DocumentEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "process_history")
public class ProcessHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String step;

    private String status;

    private String message;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private DocumentEntity document;
}
